package com.ticketu.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ticketu.R;
import com.ticketu.interfaces.listeners.OnBackPressedListener;
import com.ticketu.ui.activity.BaseActivity;
import com.ticketu.ui.presenter.BasePresenter;

/**
 * Base fragment created to be extended by every fragment in this application. This class provides
 * dependency injection configuration, ButterKnife Android library configuration and some methods
 * common to every fragment.
 *
 */

public abstract class BaseFragment extends Fragment implements OnBackPressedListener{

  BasePresenter presenter;
  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    ((BaseActivity)getActivity()).onBackPressedListener = this;
    return inflater.inflate(getFragmentLayout(), container, false);
  }

  @Override public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    onContentViewCreated(view,savedInstanceState);
    this.presenter = getPresenter();
  }

  @Override public void onResume() {
    super.onResume();
    if(null!=presenter) presenter.resume();
  }

  @Override public void onPause() {
    super.onPause();
    if(null!=presenter) presenter.pause();
  }


  public void setToggle(Toolbar toolbar) {
    DrawerLayout drawer =  (DrawerLayout)getActivity().findViewById(R.id.drawer_layout);
    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            getActivity(), drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    drawer.setDrawerListener(toggle);
    toggle.syncState();
  }

  /**
   * Every fragment has to inflate a layout in the onCreateView method. We have added this method to
   * avoid duplicate all the inflate code in every fragment. You only have to return the layout to
   * inflate in this method when extends BaseFragment.
   */
  protected abstract int getFragmentLayout();

  protected abstract void onContentViewCreated(View view, Bundle savedInstanceState);

  protected abstract BasePresenter getPresenter();
  @Override
  public boolean doBack() {
    boolean proceedActivityBackAction = (null==presenter);
    proceedActivityBackAction = (!proceedActivityBackAction)?presenter.doBack():proceedActivityBackAction;
    return proceedActivityBackAction;
  }
}
