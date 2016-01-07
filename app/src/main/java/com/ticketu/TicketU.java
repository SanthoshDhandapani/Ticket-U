package com.ticketu;

import android.app.Application;

import com.github.johnkil.print.PrintConfig;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseUser;

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

        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();
        ParseACL.setDefaultACL(defaultACL, true);

        PrintConfig.initDefault(getAssets(), "fonts/fontawesome-webfont.ttf");
    }

}
