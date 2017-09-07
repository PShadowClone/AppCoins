package com.example.aamer.appcoins.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.aamer.appcoins.Actvities.CoinInfo;
import com.example.aamer.appcoins.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by aamer on 8/27/2017.
 */

public class CoinsAdapter extends RecyclerView.Adapter<CoinsAdapter.ViewHolder> {

    private Context context;
    private JSONArray coins;

    public CoinsAdapter(Context context, JSONArray coins) {
        this.context = context;
        this.coins = coins;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.coin_cell, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        try {
            JSONObject coin = coins.getJSONObject(position);
            holder.coinName.setText(coin.getString("id"));
            holder.price.setText(coin.getString("price"));
            holder.tradeDate.setText(coin.getString("latest_trade"));
            holder.view.setTag(coin.getString("id"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return coins.length();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public AppCompatTextView coinName;
        public AppCompatTextView price;
        public AppCompatTextView tradeDate;
        public View view;

        ViewHolder(View itemView) {
            super(itemView);
            coinName = (AppCompatTextView) itemView.findViewById(R.id.coin_name);
            price = (AppCompatTextView) itemView.findViewById(R.id.trade_date);
            tradeDate = (AppCompatTextView) itemView.findViewById(R.id.price);
            this.view = itemView;
            toCoinInfo();
        }

        void toCoinInfo() {
            this.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, CoinInfo.class);
                    intent.putExtra("coin_name", view.getTag().toString());
                    context.startActivity(intent);

                }
            });
        }
    }
}
