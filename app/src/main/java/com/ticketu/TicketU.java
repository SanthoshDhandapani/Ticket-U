package com.ticketu;

import android.app.Application;
import android.util.Log;

import com.backendless.Backendless;
import com.github.johnkil.print.PrintConfig;
import com.ticketu.data.PaperNoDbController;

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
        String appID = getString(R.string.backendLess_app_id);
        String secretKey = getString(R.string.backendLess_secret_key);
        String version = getString(R.string.backendLess_app_version);
        Backendless.initApp( this, appID, secretKey, version );
        PrintConfig.initDefault(getAssets(), "fonts/material_icons.ttf");
        PaperNoDbController.getInstance(this).initPaper();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath(getString(R.string.regular_text_typeface))
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
        Log.i("Test","Test 2");
    }
}
