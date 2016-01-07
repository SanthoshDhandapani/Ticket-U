package com.ticketu.ui.activity;

/**
 * Created by hemeanand on 05/01/16.
 */
public class Movies {
    String movieName;
    int  movieImage;
    int movieTicket;
    String movietimings;
    public Movies(String movieName,int movieImage,int movieTicket,String movietimings) {
        this.movieName = movieName;
        this.movieImage = movieImage;
        this.movieTicket = movieTicket;
        this.movietimings = movietimings;
    }
}
