package com.rezazali.alarmgooya.models;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import java.util.Date;

public class RepeatTaskService  extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Date myTime = new Date();

        Toast.makeText(context , "Service is running  "+ myTime.toString() , Toast.LENGTH_LONG).show();

        Log.e("Service is running" , "Time : "+ myTime.toString());


    }
}
