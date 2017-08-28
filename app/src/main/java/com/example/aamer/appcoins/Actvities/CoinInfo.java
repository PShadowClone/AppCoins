package com.example.aamer.appcoins.Actvities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.aamer.appcoins.Adapters.CoinsAdapter;
import com.example.aamer.appcoins.Adapters.MarketAdapter;
import com.example.aamer.appcoins.Constants.Coins;
import com.example.aamer.appcoins.Network.AllCoins;
import com.example.aamer.appcoins.R;
import com.example.aamer.appcoins.Structure.CoinInfoCallback;
import com.example.aamer.appcoins.Structure.VolleyCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by aamer on 8/27/2017.
 */

public class CoinInfo extends AppCompatActivity {
    AppCompatTextView price, price_before_24h, volume_first, volume_second, best_market, latest_trade_date, latest_trade_time, coinName;
    RecyclerView market_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coin_info);
        price = (AppCompatTextView) findViewById(R.id.price);
        price_before_24h = (AppCompatTextView) findViewById(R.id.price_before_24h);
        volume_first = (AppCompatTextView) findViewById(R.id.volume_first);
        volume_second = (AppCompatTextView) findViewById(R.id.volume_second);
        best_market = (AppCompatTextView) findViewById(R.id.best_market);
        latest_trade_date = (AppCompatTextView) findViewById(R.id.latest_trade_date);
        latest_trade_time = (AppCompatTextView) findViewById(R.id.latest_trade_time);
        coinName = (AppCompatTextView) findViewById(R.id.coin_name);
        try {
            market_list = (RecyclerView) findViewById(R.id.market_list);
            LinearLayoutManager llm = new LinearLayoutManager(this);
            llm.setOrientation(LinearLayoutManager.VERTICAL);
            market_list.setLayoutManager(llm);
        } catch (Exception exception) {
        }
        fillWithCoins();
    }

    private void fillWithCoins() {
        String coin_name = this.getIntent().getExtras().getString("coin_name");
        coin_name = coin_name.replace('/', '_');
        new AllCoins(this.getApplicationContext(), Coins.COIN_INFO + "/" + coin_name).getCoinInfo(new CoinInfoCallback() {
            @Override
            public void success(JSONObject jsonObject) {
                try {
                    coinName.setText(jsonObject.getString("id"));
                    price.setText(jsonObject.getString("price"));
                    price_before_24h.setText(jsonObject.getString("price_before_24h"));
                    volume_first.setText(jsonObject.getString("volume_first"));
                    volume_second.setText(jsonObject.getString("volume_second"));
                    best_market.setText(jsonObject.getString("best_market"));
                    latest_trade_date.setText(jsonObject.getString("latest_trade").split(" ")[0]);
                    latest_trade_time.setText(jsonObject.getString("latest_trade").split(" ")[1]);
                    try {
                        market_list.setAdapter(new MarketAdapter(jsonObject.getJSONArray("markets"), CoinInfo.this.getApplicationContext()));
                    } catch (Exception exception) {
                        Log.e("Recycler view Error", exception.getMessage());
                    }
                } catch (JSONException jsonException) {
                    Toast.makeText(CoinInfo.this.getApplicationContext(), "Something went wrong, please try again later", Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void error(String error) {
                Toast.makeText(CoinInfo.this.getApplicationContext(), "Check your internet connection", Toast.LENGTH_LONG).show();

            }
        });

    }
}
