package com.ticketu.ui.activity;

/**
 * Created by hemeanand on 05/01/16.
 */
public class Movies {
    String movieName;
    int  movieImage;
    int movieTicket;
    String movietimings;
    String movietheatre;
    public Movies(String movieName,int movieImage,int movieTicket,String movietimings,String movietheatre) {
        this.movieName = movieName;
        this.movieImage = movieImage;
        this.movieTicket = movieTicket;
        this.movietimings = movietimings;
        this.movietheatre = movietheatre;
    }
}
