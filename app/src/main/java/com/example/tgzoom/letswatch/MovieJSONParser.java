package com.example.tgzoom.letswatch;

import android.graphics.Movie;
import android.net.Uri;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by tgzoom on 9/29/16.
 */
public class MovieJSONParser {
    public static ArrayList<MovieDB> parseJSON(String json) throws JSONException {
        ArrayList<MovieDB> movideDBList = new ArrayList<MovieDB>();

        JSONObject jsonObject = new JSONObject(json);
        JSONArray results = jsonObject.getJSONArray("results");


        for (int index = 0; index < results.length();index++){
            JSONObject movie = results.getJSONObject(index);
            MovieDB movieDB = new MovieDB();

            String title            = movie.getString("title");
            String original_title   = movie.getString("original_title");
            String poster_path      = movie.getString("poster_path");
            int vote_count          = movie.getInt("vote_count");
            String release_date     = movie.getString("release_date");
            Double vote_avarage     = movie.getDouble("vote_average");
            String overview         = movie.getString("overview");
            String Id               = String.valueOf(movie.getInt("id"));

            Uri.Builder builder = new Uri.Builder();
            poster_path = poster_path.substring(1);
            poster_path = builder
                            .scheme("https")
                            .authority("image.tmdb.org")
                            .appendPath("t")
                            .appendPath("p")
                            .appendPath("w185")
                            .appendPath(poster_path).build().toString();

            movieDB.setOriginal_title(original_title);
            movieDB.setTitle(title);
            movieDB.setPoster_path(poster_path);
            movieDB.setVote_count(vote_count);
            movieDB.setRelease_date(release_date);
            movieDB.setVote_average(vote_avarage);
            movieDB.setOverview(overview);
            movieDB.setId(Id);
            movideDBList.add(movieDB);
        }

        return movideDBList;
    }
}
