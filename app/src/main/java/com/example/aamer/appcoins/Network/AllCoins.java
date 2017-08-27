package com.example.aamer.appcoins.Network;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.aamer.appcoins.Structure.VolleyCallback;

import org.json.JSONArray;

import java.util.ArrayList;

/**
 * Created by aamer on 8/27/2017.
 */

public class AllCoins {

    private Context context;
    private String url;

    public AllCoins(Context context, String url) {
        this.context = context;
        this.url = url;
    }

    public void getAllCoins(final VolleyCallback volleyCallback) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray jsonArray) {
                        volleyCallback.success(jsonArray);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        volleyCallback.error(volleyError.getMessage());
                    }
                });
        requestQueue.add(request);
    }
}
