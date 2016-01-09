package com.ticketu.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.ticketu.R;
import com.ticketu.ui.presenter.BasePresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class PostFragment extends BaseFragment {


    public PostFragment() {
        // Required empty public constructor
    }


    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_post;
    }

    @Override
    protected void onContentViewCreated(View view, Bundle savedInstanceState) {

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ActionBar ab = ((AppCompatActivity) getActivity()).getSupportActionBar();
        assert ab != null;
        ab.setTitle("Post a ticket");
        ab.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

}
