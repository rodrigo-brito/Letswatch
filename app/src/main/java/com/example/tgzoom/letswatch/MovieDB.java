package com.example.tgzoom.letswatch;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by tgzoom on 9/29/16.
 */
public class MovieDB implements Parcelable{

    private String title;
    private String original_title;
    private String poster_path;
    private int vote_count;
    private String release_date;
    private Double vote_average;
    private String overview;
    private String id;

    protected MovieDB(Parcel in) {
        title = in.readString();
        original_title = in.readString();
        poster_path = in.readString();
        release_date = in.readString();
        overview = in.readString();
        vote_count = in.readInt();
        vote_average = in.readDouble();
    }

    public MovieDB(){

    }

    public static final Creator<MovieDB> CREATOR = new Creator<MovieDB>() {
        @Override
        public MovieDB createFromParcel(Parcel in) {
            return new MovieDB(in);
        }

        @Override
        public MovieDB[] newArray(int size) {
            return new MovieDB[size];
        }
    };

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(original_title);
        parcel.writeString(poster_path);
        parcel.writeString(release_date);
        parcel.writeString(overview);
        parcel.writeInt(vote_count);
        parcel.writeDouble(vote_average);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public void setId(String ids) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Double getVote_average() {
        return vote_average;
    }

    public void setVote_average(Double vote_average) {
        this.vote_average = vote_average;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public String getRelease_date() {
        return release_date.substring(0,4);
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }
}
