package com.example.aamer.appcoins.Structure;

import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by aamer on 8/27/2017.
 */

public interface CoinInfoCallback {
    public void success(JSONObject jsonObject);

    public void error(String error);
}
