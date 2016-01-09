package com.ticketu.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.ticketu.R;
import com.ticketu.model.Ticket;

import java.util.Date;
import java.util.List;

public class RecyclerViewCardsAdapter extends RecyclerView.Adapter<RecyclerViewCardsAdapter.ViewHolder> {
    private final List<Ticket> mListItemsCard;
    private Context mContext;
    DateFormat dateLabelFormat;
    String dateLabelStringFormat = "EEE,  MMM yy";

    public RecyclerViewCardsAdapter(Context context, List<Ticket> listItemsCard) {
        this.mListItemsCard = listItemsCard;
        this.mContext = context;
        this.dateLabelFormat = new DateFormat();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_recycler_view_comp, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Ticket ticket = mListItemsCard.get(position);
        Date showDateAndTime = ticket.getShowDateTime();

        holder.itemView.setTag(ticket);
        Picasso.with(holder.imageView.getContext())
                .load(ticket.getImageURL())
                .error(R.drawable.placeholder_card_view)
                .placeholder(R.drawable.placeholder_card_view)
                .into(holder.imageView);
        holder.ticketTitle.setText(ticket.getName());
        holder.ticketGenre.setText(ticket.getTicketsGenre());
        holder.venueName.setText(ticket.getVenueName());
        holder.ticketsCount.setText(String.valueOf(ticket.getTicketsCount()));
        holder.dateLabel.setText(dateLabelFormat.format(dateLabelStringFormat, showDateAndTime));
    }

    @Override
    public int getItemCount() {
        return mListItemsCard.size();
    }

    private String toCamelCaseWithSeparator(String s, String splitString){
        splitString = splitString.replaceAll(",", ", ");
        String[] parts = s.split(splitString);
        String camelCaseString = "";
        for (String part : parts){
            camelCaseString = camelCaseString + toProperCase(part)+splitString;
        }
        camelCaseString = camelCaseString.substring(0, camelCaseString.length()-1);
        return camelCaseString.trim();
    }

    private String toProperCase(String s) {
        return ((s.length()>=1))?s.substring(0, 1).toUpperCase() +s.substring(1).toLowerCase():s.toUpperCase();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView dateLabel, ticketTitle, ticketGenre, venueName, ticketsCount;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.material_com_card_view_image);
            dateLabel = (TextView) itemView.findViewById(R.id.dateLabel);
            ticketTitle = (TextView) itemView.findViewById(R.id.ticket_title);
            ticketGenre = (TextView) itemView.findViewById(R.id.ticket_genre);
            venueName = (TextView) itemView.findViewById(R.id.venue_name);
            ticketsCount = (TextView) itemView.findViewById(R.id.ticket_count);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

    }
}
