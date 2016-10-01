package com.example.tgzoom.letswatch;

import java.io.Serializable;

/**
 * Created by tgzoom on 9/29/16.
 */
public class MovieDB implements Serializable{

    private String title;
    private String original_title;
    private String poster_path;
    private int vote_count;
    private Boolean video;
    private String release_date;
    private Double vote_average;
    private String overview;
    private int Id;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
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

    public Boolean getVideo() {
        return video;
    }

    public void setVideo(Boolean video) {
        this.video = video;
    }

    public String getRelease_date() {
        return release_date.substring(0,4);
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }


}
