package com.ticketu.ui.viewmodel;

import com.ticketu.ui.fragment.HomeFragment;

/**
 * ViewModel crated to represent a HomeView from the presentation point of view.
 *
 * This class could be a interface implementation if the HomeViewModel has more than one
 * implementation.
 *
 */
public class HomeViewModel {

  private Listener listener;

   public void initialize() {}

  public void loadTickets(final String tvShowId) {
    listener.onLoadVisibilityChanged(true);
  }

  public void setListener(HomeFragment listener) {
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

    void onTicketsDataLoaded(final Object movieDataObj);

    void onLoadVisibilityChanged(final boolean visible);

    void onTicketsNotFound();

  }
}
