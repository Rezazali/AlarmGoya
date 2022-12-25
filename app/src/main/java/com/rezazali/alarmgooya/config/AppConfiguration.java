package com.rezazali.alarmgooya.config;

import android.app.Application;
import android.content.Context;

public class AppConfiguration extends Application {


    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();

    }


    public static Context getContext() {

        return context;

    }

}
