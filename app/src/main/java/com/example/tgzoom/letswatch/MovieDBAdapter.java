package com.example.tgzoom.letswatch;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tgzoom on 9/29/16.
 */
public class MovieDBAdapter extends RecyclerView.Adapter<MovieDBAdapter.MovieDBHolder> {
    private ArrayList<MovieDB> movieDBList;

    public MovieDBAdapter(ArrayList<MovieDB> movieDBList){
        this.movieDBList = movieDBList;
    }

    public static class MovieDBHolder extends RecyclerView.ViewHolder{
        public ImageView imageView;
        public Context context;
        public MovieDBHolder(View view, final Context context) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.movie_imageview);
            this.context = context;
        }
    }

    @Override
    public MovieDBHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.moviedb_list_item,parent,false);
        return new MovieDBHolder(view,parent.getContext());
    }

    @Override
    public void onBindViewHolder(final MovieDBHolder holder, int position) {
        final MovieDB movieDB = this.movieDBList.get(position);
        Picasso.with(holder.context)
                .load(movieDB.getPoster_path())
                .priority(Picasso.Priority.HIGH)
                .placeholder(android.R.drawable.arrow_up_float)
                .into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.context,DetailActivity.class);
                intent.setAction(Intent.ACTION_VIEW);
                intent.putExtra("MovieDBObject",movieDB);
                holder.context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(this.movieDBList != null){
            return this.movieDBList.size();
        }
        return 0;
    }

    public void updateMovieDBAdapter(ArrayList<MovieDB> movieDBList){
        if(this.movieDBList != null){
            this.movieDBList.addAll(movieDBList);
            notifyDataSetChanged();
        }
    }
}
