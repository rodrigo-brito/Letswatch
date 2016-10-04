package com.example.tgzoom.letswatch;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class MoviesFragment extends Fragment {

    private GridView gridView;
    private MovieDBAdapter movieDBAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onStart() {
        super.onStart();
        updateMovieDBList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_movies, container, false);
        gridView = (GridView) rootView.findViewById(R.id.moviedb_gridview);
        movieDBAdapter = new MovieDBAdapter(getContext(),new ArrayList<MovieDB>());
        gridView.setAdapter(movieDBAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MovieDB movieDB = (MovieDB) parent.getItemAtPosition(position);
                Intent detail = new Intent(getContext(),DetailActivity.class);
                detail.putExtra("MovieDBObject",movieDB);
                startActivity(detail);
            }
        });

        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                MovieDB movieDB = (MovieDB) parent.getItemAtPosition(position);
                Toast.makeText(getContext(),movieDB.getOriginal_title(),Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        gridView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {
                if(i+i1 >= i2 ){
                    updateMovieDBList();
                }
            }
        });

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_movies,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.settings:
                Intent settings_intent = new Intent(getContext(),SettingsActivity.class);
                settings_intent.setAction(Intent.ACTION_VIEW);
                startActivity(settings_intent);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }


    public void updateMovieDBList(){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        String api_path = sharedPreferences.getString(getString(R.string.pref_order_key),getString(R.string.pref_order_default_value));
        new MovieDBAsyncTask(getContext(),movieDBAdapter).execute(api_path);
    }
}
