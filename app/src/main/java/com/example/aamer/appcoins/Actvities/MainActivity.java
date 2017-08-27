package com.example.aamer.appcoins.Actvities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.aamer.appcoins.Constants.Coins;
import com.example.aamer.appcoins.Network.AllCoins;
import com.example.aamer.appcoins.R;
import com.example.aamer.appcoins.Structure.VolleyCallback;

import org.json.JSONArray;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fillWithCoins();
    }

    private void fillWithCoins() {
        new AllCoins(this.getApplicationContext(), Coins.ALL_COINS).getAllCoins(new VolleyCallback() {
            @Override
            public void success(JSONArray jsonArray) {
                try {
                    Log.d("content", jsonArray.get(0).toString());
                } catch (Exception exception) {
                    Toast.makeText(MainActivity.this.getApplicationContext(), "Exception", Toast.LENGTH_LONG).show();
                }
                Toast.makeText(MainActivity.this.getApplicationContext(), "Done", Toast.LENGTH_LONG).show();
            }

            @Override
            public void error(String error) {

                Toast.makeText(MainActivity.this.getApplicationContext(), "Error", Toast.LENGTH_LONG).show();

            }
        });
    }
}
