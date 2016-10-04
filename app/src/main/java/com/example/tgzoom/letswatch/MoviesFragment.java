package com.example.tgzoom.letswatch;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MoviesFragment extends Fragment {

    private RecyclerView recyclerView;
    private MovieDBAdapter movieDBAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private Context context;
    private final String KEY_LAYOUT_STATE = "layoutState";
    private static Bundle recyclerViewState;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onPause() {
        super.onPause();

        recyclerViewState = new Bundle();
        Parcelable listState = recyclerView.getLayoutManager().onSaveInstanceState();
        recyclerViewState.putParcelable(KEY_LAYOUT_STATE,listState);
    }

    @Override
    public void onResume() {
        super.onResume();
        if(recyclerViewState != null){
            Parcelable listState = recyclerViewState.getParcelable(KEY_LAYOUT_STATE);
            recyclerView.getLayoutManager().onRestoreInstanceState(listState);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_movies, container, false);
        context = getContext();
        recyclerView = (RecyclerView) rootView.findViewById(R.id.moviedb_recyclerview);
        recyclerView.setHasFixedSize(true);

        //Setting gridLayout manager with 2 columns
        if(getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            layoutManager = new GridLayoutManager(context,2);
        }
        else{
            layoutManager = new GridLayoutManager(context,4);
        }

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener((GridLayoutManager) layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                updateMovieDBList(page);
            }
        });

        //Setting our custom adapter extended from RecyclerView.Adapter
        movieDBAdapter = new MovieDBAdapter(new ArrayList<MovieDB>());
        recyclerView.setAdapter(movieDBAdapter);
        updateMovieDBList(1);
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
                Intent settings_intent = new Intent(context,SettingsActivity.class);
                settings_intent.setAction(Intent.ACTION_VIEW);
                startActivity(settings_intent);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }


    public void updateMovieDBList(int page){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String api_path = sharedPreferences.getString(getString(R.string.pref_order_key),getString(R.string.pref_order_default_value));
        new MovieDBAsyncTask(context,movieDBAdapter,page).execute(api_path);
    }
}
