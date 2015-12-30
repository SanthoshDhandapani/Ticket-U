package com.ticketu.ui.presenter.home;

import com.ticketu.ui.presenter.BasePresenter;

public class HomePresenterImpl extends BasePresenter implements HomePresenter {

    View view;

    @Override
    public void loadHomeContainer() {
        loadHomeScreenViews();
    }

    private void loadHomeScreenViews() {
        view.setToolBarWithToggle();
        view.setFab();
    }

    @Override
    public Object setView(Object viewObject) {
        this.view = (View) viewObject;
        return super.setView(viewObject);
    }

    @Override
    protected Object getViewObject() {
        return view;
    }

    @Override
    public void initializePresenter() {
        loadHomeContainer();
    }

    @Override
    public boolean doBack() {
        return view.closeDrawer();
    }

    /**
     * View interface created to abstract the view implementation used in this presenter.
     */
    public interface View {

        void setToolBarWithToggle();

        void setFab();

        void showLoading();

        void hideLoading();

        boolean closeDrawer();

    }


}