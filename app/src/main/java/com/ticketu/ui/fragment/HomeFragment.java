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
import com.ticketu.model.Ticket;
import com.ticketu.ui.activity.BaseActivity;
import com.ticketu.ui.adapters.SectionsPagerAdapter;
import com.ticketu.ui.presenter.BasePresenter;
import com.ticketu.ui.presenter.home.HomePresenterImpl;
import com.ticketu.ui.utils.PrintFontIconDrawable;
import com.ticketu.ui.viewmodel.TicketViewModel;

import java.util.List;

/**
 * Created by santhoshd on 30-12-2015.
 */
public class HomeFragment extends BaseFragment implements HomePresenterImpl.View, TicketViewModel.Listener {

    HomePresenterImpl  homePresenter;

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
                ((BaseActivity)getActivity()).addFragment(new PostFragment());
            }
        });
        Drawable drawable = PrintFontIconDrawable.getInstance(getActivity()).getDrawableFontIcon(R.string.ic_add, android.R.color.white, R.dimen._30sdp);
        fab.setImageDrawable(drawable);
    }

    @Override
    public void setTicketsPager() {
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getChildFragmentManager(), getResources().getStringArray(R.array.tab_titles));
        TabLayout tabLayout = (TabLayout) getView().findViewById(R.id.tabs);
        ViewPager viewPager = (ViewPager) getView().findViewById(R.id.pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
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
    public void getTicketsDataFromLocal() {

    }

    @Override
    public void getTicketsDataFromBackendLess() {

    }

    @Override
    public void onTicketsDataLoaded(List<Ticket> ticketsList) {

    }

    @Override
    public void onEmptyScreenVisibilityChanged(boolean visible) {

    }

    @Override
    public void onLoadVisibilityChanged(boolean visible) { }

    @Override
    public void onTicketsNotFound() { }
}
