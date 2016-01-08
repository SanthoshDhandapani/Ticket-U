package com.ticketu.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ticketu.R;
import com.ticketu.ui.adapters.RecyclerViewCardsAdapter;
import com.ticketu.ui.presenter.BasePresenter;
import com.ticketu.ui.viewmodel.CardViewBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by santhoshd on 08-01-2016.
 */
public class TicketFragment extends BaseFragment {

    private static final String MOCK_URL = "http://lorempixel.com/800/400/nightlife/";

    @Override
    protected int getFragmentLayout() {
        return R.layout.content_home;
    }

    @Override
    protected void onContentViewCreated(View view, Bundle savedInstanceState) {
        RecyclerView mRecyclerView = (RecyclerView)getView().findViewById(R.id.tickets_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(new RecyclerViewCardsAdapter(getActivity(),createMockList()));
    }

    private List<CardViewBean> createMockList() {
        List<CardViewBean> listCard = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            listCard.add(new CardViewBean(MOCK_URL + i));
        }
        return listCard;
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }
}
