package com.dkotov.positionsender.model;


import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by dkotov on 25-May-16.
 */
public class Request {

    double lat;
    double lon;

    public Request(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    JSONObject jsonObject = new JSONObject();

    public JSONObject getJsonObject() {
        try {
            jsonObject.put("lat", lat);
            jsonObject.put("lon", lon);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }


}
