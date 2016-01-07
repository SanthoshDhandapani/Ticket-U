package com.ticketu.ui.activity;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;

import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ticketu.R;
import com.ticketu.ui.activity.Movies;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by sathishsr on 22/10/15.
 */
public class RecyclerAdapter
        extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private final TypedValue mTypedValue = new TypedValue();
    private int mBackground;
    private List<String> mValues;
    private Context mContext;
    private ArrayList<Movies> movies=new ArrayList<>();
    private ArrayList<Movies> moviesListView;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView movieName;
        TextView movieTicket;
        ImageView movieImage;
        TextView movieTimings;
        public final View mView = null;


        public ViewHolder(View view) {
            super(view);
            movieName = (TextView) view.findViewById(R.id.movietitle);
            movieImage = (ImageView) view.findViewById(R.id.movieimage);
            movieTicket = (TextView) view.findViewById(R.id.availabletickets);
            movieTimings = (TextView)view.findViewById(R.id.timings);

        }
    }

    public String getValueAt(int position) {
        return mValues.get(position);
    }

    public RecyclerAdapter(Context context, ArrayList<Movies> movies) {
        context.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
        mContext = context;
        this.movies = movies;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        moviesListView = new ArrayList<Movies>();
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_entry, null, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.movieName.setText(movies.get(position).movieName);
        holder.movieTicket.setText("Tickets : "+movies.get(position).movieTicket);
        holder.movieImage.setImageResource(movies.get(position).movieImage);
        holder.movieTimings.setText(movies.get(position).movietimings);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }


}