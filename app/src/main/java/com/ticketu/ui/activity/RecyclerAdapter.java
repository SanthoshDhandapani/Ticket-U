package com.ticketu.ui.activity;

import android.media.Image;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ticketu.R;

import java.util.List;

/**
 * Created by hemeanand on 04/01/16.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.PersonViewHolder>{
    List<Movies> movies;

    public RecyclerAdapter(List<Movies> movies){
        this.movies = movies;
    }
    public static class PersonViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView movieName;
        TextView movieTicket;
        ImageView movieImage;

        PersonViewHolder(View itemView) {
            super(itemView);
            movieName = (TextView)itemView.findViewById(R.id.movietitle);
            movieImage = (ImageView)itemView.findViewById(R.id.movieimage);
            movieTicket = (TextView)itemView.findViewById(R.id.availabletickets);
        }
    }
    @Override
    public int getItemCount() {
        return movies.size();
    }
    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view_entry, viewGroup, false);
        PersonViewHolder pvh = new PersonViewHolder(v);
        return pvh;
    }
    @Override
    public void onBindViewHolder(PersonViewHolder personViewHolder, int i) {
        personViewHolder.movieName.setText(movies.get(i).movieName);
        personViewHolder.movieTicket.setText(movies.get(i).movieTicket);
        personViewHolder.movieImage.setImageResource(movies.get(i).movieImage);
    }
}