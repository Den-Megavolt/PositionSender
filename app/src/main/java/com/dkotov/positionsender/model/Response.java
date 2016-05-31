package com.dkotov.positionsender.model;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dkotov on 25-May-16.
 */
public class Response {

    JSONArray jsonArray;
    JSONObject locationObject;
    ArrayList<LatLng> locations;


    public Response(JSONArray jsonArray) {
        this.jsonArray = jsonArray;
    }

    public List<LatLng> getLocations() {
        for (int i = 0; i <= jsonArray.length(); i++){
            try {
                locationObject = jsonArray.getJSONObject(i);
                locations.add(locationObject.getInt("id"), new LatLng(locationObject.getDouble("lat"),locationObject.getDouble("lon")));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return locations;
    }


}
