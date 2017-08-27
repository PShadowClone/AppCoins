package com.example.aamer.appcoins.Structure;

import org.json.JSONArray;

/**
 * Created by aamer on 8/27/2017.
 */

public interface VolleyCallback {

    public void success(JSONArray jsonArray);
    public void error(String error);

}
