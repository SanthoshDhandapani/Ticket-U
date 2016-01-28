package com.ticketu.ui.presenter.ticket;

import com.ticketu.model.Ticket;
import com.ticketu.ui.presenter.BasePresenter;

import java.util.List;

/**
 * Created by santhoshd on 09-01-2016.
 */
public class TicketPresenterImpl extends BasePresenter implements TicketPresenter{

    View view;

    @Override
    protected Object getViewObject() {
        return view;
    }

    @Override
    public Object setView(Object viewObject) {
        this.view = (View) viewObject;
        return super.setView(viewObject);
    }

    @Override
    public void initializePresenter() {
        loadTicketsContainer();
    }

    @Override
    public void loadTicketsContainer() {
        view.setEmptyScreen(false);
    }

    @Override
    public void loadTicketsList(List<Ticket> ticketsList) {
        view.setTicketsListRecycler(ticketsList);
    }

    /**
     * View interface created to abstract the view implementation used in this presenter.
     */
    public interface View {
        void setTicketsListRecycler(List<Ticket> ticketList);
        void setEmptyScreen(boolean visible);
        void updateTicketsList(List<Ticket> ticketList);
    }
}
