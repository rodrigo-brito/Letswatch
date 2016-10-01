package com.example.tgzoom.letswatch;

import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;


/**
 * Created by tgzoom on 9/28/16.
 */
public class MovieDBAsyncTask extends AsyncTask<String,ArrayList<MovieDB>,ArrayList<MovieDB>> {
    private MovieDBAdapter movieDBAdapter;
    private Context context;

    MovieDBAsyncTask(Context context, MovieDBAdapter movieDBAdapter){
        this.movieDBAdapter = movieDBAdapter;
        this.context = context;
    }

    @Override
    protected ArrayList<MovieDB> doInBackground(String... api_path) {
        try {
            NetworkRequest networkRequest = new NetworkRequest();
            String json = networkRequest.getMovieJsonString(api_path);
            ArrayList<MovieDB> movieDBList = MovieJSONParser.parseJSON(json);
            if(movieDBList != null){
                return movieDBList;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
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
