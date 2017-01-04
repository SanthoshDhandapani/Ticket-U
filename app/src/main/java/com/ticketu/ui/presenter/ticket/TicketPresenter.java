package com.ticketu.ui.presenter.ticket;

import com.ticketu.model.Ticket;
import com.ticketu.ui.presenter.Presenter;

import java.util.List;

/**
 * Created by santhoshd on 09-01-2016.
 */
public interface TicketPresenter extends Presenter {
    void loadTicketsContainer();
    void loadTicketsList(List<Ticket> ticketsList);
}
