package com.ticketu.ui.viewmodel;

import com.ticketu.model.Ticket;

import java.util.List;

/**
 * ViewModel crated to represent a TicketView from the presentation point of view.
 *
 * This class could be a interface implementation if the TicketViewModel has more than one
 * implementation.
 *
 */
public class TicketViewModel {

  private Listener listener;

   public void initialize() {
     listener.getTicketsDataFromLocal();
   }

  public void loadTickets() {
    listener.onLoadVisibilityChanged(true);
  }

  public void setListener(Listener listener) {
    this.listener = listener;
  }

  public Listener getListener() {
    return listener;
  }

  /**
   * Interface created to work as ViewModel listener.
   * Every change in the view model will be
   * notified to Listener implementation.
   */
  public interface Listener {

    void getTicketsDataFromLocal();

    void getTicketsDataFromParseInBackground();

    void onTicketsDataLoaded(List<Ticket> ticketsList);

    void onEmptyScreenVisibilityChanged(final boolean visible);

    void onLoadVisibilityChanged(final boolean visible);

    void onTicketsNotFound();

  }
}
