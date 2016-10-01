package com.example.tgzoom.letswatch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        loadMoviedbInformation();
    }

    public void updateTrailersInformation(String id_movie){
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.trailer_list_parent_layout);
        new TrailerAsyncTask(this,linearLayout).execute(id_movie);
    }

    public void loadMoviedbInformation(){
        MovieDB movieDB = (MovieDB) getIntent().getSerializableExtra("MovieDBObject");

        TextView  movie_title           = (TextView) findViewById(R.id.movie_title_textview);
        ImageView movie_cover           = (ImageView) findViewById(R.id.movie_cover_imageview);
        TextView  movie_release_date    = (TextView) findViewById(R.id.movie_release_date);
        TextView  movie_duration        = (TextView) findViewById(R.id.movie_duration_textview);
        TextView  movie_rating          = (TextView) findViewById(R.id.movie_rating_textview);
        Button    favorite_button       = (Button) findViewById(R.id.mark_favorite_button);
        TextView  movie_overview        = (TextView) findViewById(R.id.movie_overview_textview);

        movie_title.setText(movieDB.getTitle());
        movie_release_date.setText(movieDB.getRelease_date());
        movie_rating.setText(movieDB.getVote_average().toString() + "/10");
        movie_overview.setText(movieDB.getOverview());

        Picasso.with(this)
                .load(movieDB.getPoster_path())
                .into(movie_cover);

        updateTrailersInformation(String.valueOf(movieDB.getId()));
    }
}
