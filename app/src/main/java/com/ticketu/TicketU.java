package com.ticketu;

import android.app.Application;

import com.github.johnkil.print.PrintConfig;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.ticketu.model.Ticket;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

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
        ParseObject.registerSubclass(Ticket.class);
        Parse.initialize(this);

        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();
        ParseACL.setDefaultACL(defaultACL, true);

        PrintConfig.initDefault(getAssets(), "fonts/material_icons.ttf");

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath(getString(R.string.regular_text_typeface))
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }
}
