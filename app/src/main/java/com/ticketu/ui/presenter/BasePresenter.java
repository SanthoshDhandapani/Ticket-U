package com.ticketu.ui.presenter;


/**
 * Created by santhoshd on 30-12-2015.
 */
public abstract class BasePresenter implements Presenter {

    @Override
    public void initialize() {
        checkViewAlreadySettled(getViewObject());
    }

    @Override
    public void resume() {}

    @Override
    public void pause() {}

    @Override
    public boolean doBack() { return true; }

    protected abstract Object getViewObject();

    public abstract void initializePresenter();

    protected Object setView(Object viewObject) {
        if (viewObject == null) {
            throw new IllegalArgumentException("You can't set a null view");
        }
        return viewObject;
    }

    private void checkViewAlreadySettled(Object viewObject) {
        if (viewObject == null) {
            throw new IllegalArgumentException("Remember to set a view for this presenter");
        }
    }

}
