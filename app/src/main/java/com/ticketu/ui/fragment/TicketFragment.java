package com.ticketu.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.BackendlessDataQuery;
import com.backendless.persistence.QueryOptions;
import com.github.johnkil.print.PrintView;
import com.ticketu.R;
import com.ticketu.data.PaperNoDbController;
import com.ticketu.model.Ticket;
import com.ticketu.ui.adapters.TicketsAdapter;
import com.ticketu.ui.presenter.BasePresenter;
import com.ticketu.ui.presenter.ticket.TicketPresenterImpl;
import com.ticketu.ui.viewmodel.TicketViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import io.paperdb.Paper;

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
        List<Ticket> ticketsList = PaperNoDbController.getInstance(getContext()).getListFromJsonArray(Ticket.class.getName()+" list", Ticket[].class);
        ticketsList = (null==ticketsList)?new ArrayList<Ticket>():ticketsList;
        if(ticketsList.size()>0)
            onEmptyScreenVisibilityChanged(true);
        onTicketsDataLoaded(ticketsList);
        getTicketsDataFromBackendLess();
    }

    @Override
    public void getTicketsDataFromBackendLess() {

        QueryOptions queryOptions = new QueryOptions();
        List<String> sortBy = new ArrayList<>();
        sortBy.add( "name" );
        queryOptions.setSortBy( sortBy );

        BackendlessDataQuery dataQuery = new BackendlessDataQuery();
        dataQuery.setQueryOptions( queryOptions );
        Backendless.Data.of( Ticket.class ).find(dataQuery, new AsyncCallback<BackendlessCollection<Ticket>>() {
            @Override
            public void handleResponse(BackendlessCollection<Ticket> response) {
                PaperNoDbController.getInstance(getContext()).putJSONList(Ticket.class.getName()+" list", response.getData());
                onEmptyScreenVisibilityChanged(true);
                updateTicketsList(response.getData());
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Log.e("Tickets Retrieve", fault.toString());
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

            if(null!=getView()) {
                View emptyScreenGroup = getView().findViewById(R.id.empty_screen_group);
                if (null != emptyScreenGroup) emptyScreenGroup.setVisibility(View.GONE);
            }
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
        mRecyclerView.setAdapter(new TicketsAdapter(ticketList));
    }

    @Override
    public void setEmptyScreen(boolean visible) {
        onEmptyScreenVisibilityChanged(visible);
    }

    @Override
    public void updateTicketsList(List<Ticket> ticketList) {
        if(null!=getView()) {
           RecyclerView ticketsRecyler = (RecyclerView) getView().findViewById(R.id.tickets_recycler_view);
           TicketsAdapter ticketsAdapter =(TicketsAdapter)ticketsRecyler.getAdapter();
           ticketsAdapter.updateTicketsList(ticketList);
        }
    }
}
