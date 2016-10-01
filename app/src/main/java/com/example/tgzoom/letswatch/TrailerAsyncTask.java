package com.example.tgzoom.letswatch;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by tgzoom on 9/30/16.
 */
public class TrailerAsyncTask extends AsyncTask<String,ArrayList<Trailer>,ArrayList<Trailer>> {
    private Context context;
    private LinearLayout trailerLinearLayout;
    private LayoutInflater inflater;
    private static String TRAILER_LABEL = "Trailer";

    TrailerAsyncTask(Context context, LinearLayout trailerLinearLayout){
        this.trailerLinearLayout = trailerLinearLayout;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    protected ArrayList<Trailer> doInBackground(String... id_movie) {
        try {
            NetworkRequest networkRequest = new NetworkRequest();
            String json = networkRequest.getTrailerJsonString(id_movie);
            ArrayList<Trailer> trailerArrayList = TrailerJSONParser.parseJSON(json);
            return trailerArrayList;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<Trailer> trailerArrayList) {
        super.onPostExecute(trailerArrayList);
        int i = 0;
        for (final Trailer trailer:trailerArrayList){
            View trailer_list_item = inflater.inflate(R.layout.trailer_list_item,null);

            ImageButton trailer_imagebutton = (ImageButton) trailer_list_item.findViewById(R.id.trailer_imagebutton);
            trailer_imagebutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent play_trailer_intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:"+trailer.getKey()));
                    context.startActivity(play_trailer_intent);
                }
            });

            TextView trailer_label = (TextView) trailer_list_item.findViewById(R.id.trailer_label_textview);
            trailer_label.setText(TRAILER_LABEL + " " +String.valueOf(i));

            trailerLinearLayout.addView(trailer_list_item);
            i++;
        }
    }
}
