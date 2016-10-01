package com.example.tgzoom.letswatch;

import android.net.Uri;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by tgzoom on 9/30/16.
 */
public class TrailerJSONParser {
    public static ArrayList<Trailer> parseJSON(String json) throws JSONException {
        ArrayList<Trailer> trailerArrayList = new ArrayList<Trailer>();

        JSONObject jsonObject = new JSONObject(json);
        JSONArray results = jsonObject.getJSONArray("results");


        for (int index = 0; index < results.length();index++){
            JSONObject trailerObject = results.getJSONObject(index);
            Trailer trailer = new Trailer();

            String key      = trailerObject.getString("key");
            String name     = trailerObject.getString("name");
            String site     = trailerObject.getString("site");
            int size        = trailerObject.getInt("size");

            trailer.setKey(key);
            trailer.setName(name);
            trailer.setSite(site);
            trailer.setSize(size);

            trailerArrayList.add(trailer);
        }

        return trailerArrayList;
    }
}
