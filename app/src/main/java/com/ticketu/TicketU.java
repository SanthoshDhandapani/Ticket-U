package com.ticketu;

import android.app.Application;
import android.util.Log;

import com.parse.Parse;

/**
 * Created by hp-user on 12/28/2015.
 */
public class TicketU extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initialize();
    }

    private void initialize() {
        Parse.enableLocalDatastore(this);
        Parse.initialize(this);
        Log.i("Test","Test 2");
    }

}
