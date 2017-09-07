package com.example.aamer.appcoins.Actvities;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.aamer.appcoins.Adapters.CoinsAdapter;
import com.example.aamer.appcoins.Constants.Coins;
import com.example.aamer.appcoins.Network.AllCoins;
import com.example.aamer.appcoins.R;
import com.example.aamer.appcoins.Structure.VolleyCallback;

import org.json.JSONArray;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    Timer syncCoins;
    Handler handler;
    final static int SYNC_INTERVAL = 90000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        syncCoins = new Timer();
        handler = new Handler();
        recyclerView = (RecyclerView) findViewById(R.id.coins);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        fillWithCoins();
        refreshActivityEveryMinute();

    }

    private void fillWithCoins() {
        new AllCoins(this.getApplicationContext(), Coins.ALL_COINS).getAllCoins(new VolleyCallback() {
            @Override
            public void success(JSONArray jsonArray) {
                try {
                    recyclerView.setAdapter(new CoinsAdapter(MainActivity.this.getApplicationContext(), jsonArray));
                    Log.d("content", jsonArray.get(0).toString());
                } catch (Exception exception) {
                    Toast.makeText(MainActivity.this.getApplicationContext(), "Invalid Content", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void error(String error) {

                Toast.makeText(MainActivity.this.getApplicationContext(), "check your internet connection", Toast.LENGTH_LONG).show();

            }
        });
    }

    private void refreshActivityEveryMinute() {
        syncCoins.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        fillWithCoins();
                    }
                });
            }
        }, 0, SYNC_INTERVAL);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (syncCoins != null) {
            syncCoins.cancel();
        }
    }
}
