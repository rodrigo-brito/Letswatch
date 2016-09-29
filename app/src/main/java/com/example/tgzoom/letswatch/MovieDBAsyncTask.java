package com.example.tgzoom.letswatch;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;

import java.util.ArrayList;


/**
 * Created by tgzoom on 9/28/16.
 */
public class MovieDBAsyncTask extends AsyncTask<Void,ArrayList<MovieDB>,ArrayList<MovieDB>> {
    private MovieDBAdapter movieDBAdapter;
    private String api_path;
    private Context context;

    MovieDBAsyncTask(Context context, MovieDBAdapter movieDBAdapter, String api_path){
        this.api_path = api_path;
        this.movieDBAdapter = movieDBAdapter;
        this.context = context;
    }

    @Override
    protected ArrayList<MovieDB> doInBackground(Void... voids) {
        NetworkRequest networkRequest = new NetworkRequest();
        String json = networkRequest.getJsonString(api_path);
        try {
            ArrayList<MovieDB> movieDBList = MovieJSONParser.parseJSON(json);
            if(movieDBList != null){
                return movieDBList;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<MovieDB> movieDBList) {
        super.onPostExecute(movieDBList);
        movieDBAdapter.updateMovieDBAdapter(movieDBList);
    }
}
