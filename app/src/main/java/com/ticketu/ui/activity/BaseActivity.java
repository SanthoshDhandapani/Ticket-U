package com.ticketu.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ticketu.interfaces.listeners.OnBackPressedListener;

/**
 * Base activity created to be extended by every activity in this application. This class provides
 * dependency injection configuration, ButterKnife Android library configuration and some methods
 * common to every activity.
 *
 */
public abstract class BaseActivity extends AppCompatActivity {

  public OnBackPressedListener onBackPressedListener;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    onCreateContentView(savedInstanceState);
  }

  protected abstract void onCreateContentView(Bundle savedInstanceState);

  @Override
  public void onBackPressed() {
    boolean proceedActivityBackAction = (null==onBackPressedListener);
    proceedActivityBackAction = (!proceedActivityBackAction)?onBackPressedListener.doBack():proceedActivityBackAction;

    if(proceedActivityBackAction) {
      super.onBackPressed();
    }
  }
}
