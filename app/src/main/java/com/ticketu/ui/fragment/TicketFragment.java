package com.ticketu.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.github.johnkil.print.PrintView;
import com.parse.DeleteCallback;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.ticketu.R;
import com.ticketu.model.Ticket;
import com.ticketu.ui.adapters.RecyclerViewCardsAdapter;
import com.ticketu.ui.presenter.BasePresenter;
import com.ticketu.ui.presenter.ticket.TicketPresenterImpl;
import com.ticketu.ui.viewmodel.TicketViewModel;

import java.util.List;
import java.util.Random;

/**
 * Created by santhoshd on 08-01-2016.
 */
public class TicketFragment extends BaseFragment implements TicketViewModel.Listener, TicketPresenterImpl.View {

    TicketViewModel ticketViewModel;
    TicketPresenterImpl ticketPresenter;

    Handler handler;
    Runnable runnable;

    @Override
    protected int getFragmentLayout() {
        return R.layout.content_home;
    }

    @Override
    protected void onContentViewCreated(View view, Bundle savedInstanceState) {
        initializeTicketsPresenter();
        initializeTicketViewModel();
    }

    private void initializeTicketViewModel() {
        ticketViewModel = new TicketViewModel();
        ticketViewModel.setListener(this);
        ticketViewModel.initialize();
    }

    private void initializeTicketsPresenter() {
        ticketPresenter = new TicketPresenterImpl();
        ticketPresenter.setView(this);
        ticketPresenter.initializePresenter();
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void getTicketsDataFromLocal() {
        // Fetch from local
        ParseQuery<Ticket> query = ParseQuery.getQuery("Ticket");
        query.fromLocalDatastore().findInBackground(new FindCallback<Ticket>() {
            @Override
            public void done(List<Ticket> objects, ParseException e) {
                if(null!=objects && objects.size()>0) {
                    onEmptyScreenVisibilityChanged(true);
                    onTicketsDataLoaded(objects);
                }
                getTicketsDataFromParseInBackground();
            }
        });
    }

    @Override
    public void getTicketsDataFromParseInBackground() {
        ParseQuery<Ticket> query = ParseQuery.getQuery("Ticket");
        // Query for the latest objects from Parse.
        query.findInBackground(new FindCallback<Ticket>() {
            public void done(final List<Ticket> ticketsList, ParseException e) {
                if (e != null) {
                    // There was an error or the network wasn't available.
                    return;
                }

                // Release any objects previously pinned for this query.
                ParseObject.unpinAllInBackground(Ticket.class.getName(), ticketsList, new DeleteCallback() {
                    public void done(ParseException e) {
                        if (e != null) {
                            // There was some error.
                            return;
                        }

                        // Add the latest results for this query to the cache.
                        ParseObject.pinAllInBackground(Ticket.class.getName(), ticketsList);
                        onEmptyScreenVisibilityChanged(true);
                    }
                });
            }
        });
    }

    @Override
    public void onTicketsDataLoaded(List<Ticket> ticketsList) {
        ticketPresenter.loadTicketsList(ticketsList);
    }

    @Override
    public void onEmptyScreenVisibilityChanged(boolean visible) {
        if(!visible) {
            final PrintView emptyScreenIconView = (PrintView)getView().findViewById(R.id.empty_screen_icon_view);
            final Random random = new Random();
            final int[] colorsArray = {R.color.colorAccent, R.color.alphaColorAccent, R.color.nav_list_icons};
            handler = (null==handler)?new Handler():handler;
            runnable = (null==runnable)?new Runnable() {
                @Override
                public void run() {
                    emptyScreenIconView.setIconColor(colorsArray[random.nextInt(colorsArray.length)]);
                    handler.postDelayed(runnable, 500);
                }
            }:runnable;
            handler.postDelayed(runnable, 500);
        } else {
            if(null!=handler) {
                handler.removeCallbacks(runnable);
                handler = null;
                runnable = null;
            }
            View emptyScreenGroup = getView().findViewById(R.id.empty_screen_group);
            if(null!=emptyScreenGroup)emptyScreenGroup.setVisibility(View.GONE);
        }
    }

    @Override
    public void onLoadVisibilityChanged(boolean visible) {

    }

    @Override
    public void onTicketsNotFound() {

    }

    @Override
    public void setTicketsListRecycler(List<Ticket> ticketList) {
        RecyclerView mRecyclerView = (RecyclerView)getView().findViewById(R.id.tickets_recycler_view);
        mRecyclerView.setVisibility(View.VISIBLE);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(new RecyclerViewCardsAdapter(getActivity(), ticketList));
    }

    @Override
    public void setEmptyScreen(boolean visible) {
        onEmptyScreenVisibilityChanged(visible);
    }
}
