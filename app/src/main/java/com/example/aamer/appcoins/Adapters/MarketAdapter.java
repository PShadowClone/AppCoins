package com.example.aamer.appcoins.Adapters;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.aamer.appcoins.R;
import com.example.aamer.appcoins.Structure.CoinInfoCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;

/**
 * Created by aamer on 8/28/2017.
 */

public class MarketAdapter extends RecyclerView.Adapter<MarketAdapter.MarketViewHolder> {
    private JSONArray markets;
    private Context context;

    public MarketAdapter(JSONArray markets, Context context) {
        this.markets = markets;
        this.context = context;
    }

    @Override
    public MarketViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.market_cell, parent, false);
        return new MarketViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MarketViewHolder holder, int position) {
        try {
            JSONObject market = markets.getJSONObject(position);
            holder.market_name.setText(market.getString("market"));
            holder.market_price.setText(market.getString("price"));
            holder.market_volume.setText(new DecimalFormat("##.##").format(Double.parseDouble(market.getString("volume"))));
            holder.view.setTag(market.getString("market"));
        } catch (JSONException jsonException) {
        }

    }

    @Override
    public int getItemCount() {
        return markets.length();
    }

    public class MarketViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView market_name, market_price, market_volume;
        View view;

        MarketViewHolder(View itemView) {
            super(itemView);
            market_name = (AppCompatTextView) itemView.findViewById(R.id.market_name);
            market_price = (AppCompatTextView) itemView.findViewById(R.id.market_price);
            market_volume = (AppCompatTextView) itemView.findViewById(R.id.market_volume);
            view = itemView;
            toMarketWebsite();
        }

        void toMarketWebsite() {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                    String keyword = view.getTag().toString();
                    intent.putExtra(SearchManager.QUERY, keyword);
                    context.startActivity(intent);
                }
            });
        }
    }
}
