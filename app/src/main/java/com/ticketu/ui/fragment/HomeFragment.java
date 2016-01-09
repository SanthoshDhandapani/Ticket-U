package com.ticketu.ui.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.ticketu.R;
import com.ticketu.ui.activity.BaseActivity;
import com.ticketu.ui.activity.HomeActivity;
import com.ticketu.ui.adapters.SectionsPagerAdapter;
import com.ticketu.ui.presenter.BasePresenter;
import com.ticketu.ui.presenter.home.HomePresenterImpl;
import com.ticketu.ui.utils.PrintFontIconDrawable;
import com.ticketu.ui.viewmodel.CardViewBean;
import com.ticketu.ui.viewmodel.HomeViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by santhoshd on 30-12-2015.
 */
public class HomeFragment extends BaseFragment implements HomePresenterImpl.View, HomeViewModel.Listener {

    HomePresenterImpl  homePresenter;
    private static final String MOCK_URL = "http://lorempixel.com/800/400/nightlife/";

    @Override
    protected int getFragmentLayout() {
        return R.layout.app_bar_home;
    }

    @Override
    protected void onContentViewCreated(View view, Bundle savedInstanceState) {
        homePresenter = new HomePresenterImpl();
        homePresenter.setView(this);
        homePresenter.initializePresenter();
    }

    @Override
    protected BasePresenter getPresenter() {
        return homePresenter;
    }

    @Override
    public void setToolBarWithToggle() {
        View rootView = getView();
        BaseActivity baseActivity = ((BaseActivity)getActivity());
        Toolbar toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        baseActivity.setSupportActionBar(toolbar);
        setToggle(toolbar);
    }

    @Override
    public void setFab() {
        FloatingActionButton fab = (FloatingActionButton) getView().findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ((HomeActivity)getActivity()).replaceFragments(new PostFragment());
            }
        });
        Drawable drawable = PrintFontIconDrawable.getInstance(getActivity()).getDrawableFontIcon(R.string.ic_add, android.R.color.white, R.dimen._30sdp);
        fab.setImageDrawable(drawable);
    }

    @Override
    public void setTicketsList() {
        /*RecyclerView mRecyclerView = (RecyclerView)getView().findViewById(R.id.tickets_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(new RecyclerViewCardsAdapter(getActivity(),createMockList()));*/

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getChildFragmentManager());
        TabLayout tabLayout = (TabLayout) getView().findViewById(R.id.tabs);
        ViewPager viewPager = (ViewPager) getView().findViewById(R.id.pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private List<CardViewBean> createMockList() {
        List<CardViewBean> listCard = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            listCard.add(new CardViewBean(MOCK_URL + i));
        }
        return listCard;
    }

    @Override
    public void showLoading() { }

    @Override
    public void hideLoading() { }

    @Override
    public boolean closeDrawer() {
        DrawerLayout drawer = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);
        boolean cartIsOpen = drawer.isDrawerOpen(GravityCompat.START);
        if (cartIsOpen) {
            drawer.closeDrawer(GravityCompat.START);
        }
        return !cartIsOpen;
    }

    @Override
    public void onTicketsDataLoaded(Object movieDataObj) { }

    @Override
    public void onLoadVisibilityChanged(boolean visible) { }

    @Override
    public void onTicketsNotFound() { }
}
