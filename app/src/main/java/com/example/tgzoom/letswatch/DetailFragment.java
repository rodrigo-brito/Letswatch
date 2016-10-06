package com.example.tgzoom.letswatch;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailFragment extends Fragment {
    private Context context;
    private View rootView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_detail, container, false);
        context = getContext();
        loadMoviedbInformation();
        return rootView;
    }

    public void updateTrailersInformation(String id_movie){
        LinearLayout linearLayout = (LinearLayout) rootView.findViewById(R.id.trailer_list_parent_layout);
        new TrailerAsyncTask(this.context,linearLayout).execute(id_movie);
    }

    public void loadMoviedbInformation(){
        MovieDB movieDB = (MovieDB) getActivity().getIntent().getExtras().getParcelable("MovieDBObject");

        TextView movie_title            = (TextView)  rootView.findViewById(R.id.movie_title_textview);
        ImageView movie_cover           = (ImageView) rootView.findViewById(R.id.movie_cover_imageview);
        TextView  movie_release_date    = (TextView)  rootView.findViewById(R.id.movie_release_date);
        TextView  movie_duration        = (TextView)  rootView.findViewById(R.id.movie_duration_textview);
        TextView  movie_rating          = (TextView)  rootView.findViewById(R.id.movie_rating_textview);
        Button favorite_button          = (Button)    rootView.findViewById(R.id.mark_favorite_button);
        TextView  movie_overview        = (TextView)  rootView.findViewById(R.id.movie_overview_textview);

        movie_title.setText(movieDB.getTitle());
        movie_release_date.setText(movieDB.getRelease_date());
        movie_rating.setText(movieDB.getVote_average().toString() + "/10");
        movie_overview.setText(movieDB.getOverview());

        Picasso.with(context)
                .load(movieDB.getPoster_path())
                .into(movie_cover);

        updateTrailersInformation(movieDB.getId());
    }
}
