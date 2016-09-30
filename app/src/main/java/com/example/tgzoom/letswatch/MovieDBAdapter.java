package com.example.tgzoom.letswatch;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CursorAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tgzoom on 9/29/16.
 */
public class MovieDBAdapter extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
    ArrayList<MovieDB> movieDBList;

    MovieDBAdapter(Context context,ArrayList<MovieDB> movieDBList){
        this.context = context;
        this.movieDBList = movieDBList;
        this.inflater = LayoutInflater.from(this.context);
    }

    @Override
    public int getCount() {
        return this.movieDBList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.movieDBList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View moviedb_list_item, ViewGroup parent) {
        if (moviedb_list_item == null) {
            moviedb_list_item = inflater.inflate(R.layout.moviedb_list_item, null);
        }
        ImageView imageView = (ImageView) moviedb_list_item.findViewById(R.id.movie_imageview);
        MovieDB movieDB = movieDBList.get(position);
        Picasso.with(context)
                .load(movieDB.getPoster_path())
                .into(imageView);
        return moviedb_list_item;
    }

    public void updateMovieDBAdapter(ArrayList<MovieDB> movieDBList){
        this.movieDBList = movieDBList;
        notifyDataSetChanged();
    }
}
