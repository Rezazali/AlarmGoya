package com.rezazali.alarmgooya.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

public class SmsReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, @NonNull Intent intent) {

        Log.e("","");

        Bundle bundle = intent.getExtras();


        if(bundle !=null){

            Object[] pdus = (Object[]) bundle.get("pdus");

           for (int i = 0; i < pdus.length; i++){

               SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) pdus[i]);

               String message = smsMessage.getDisplayMessageBody();
               String destination = smsMessage.getDisplayOriginatingAddress();

               Toast.makeText(context , destination + " \n  "+destination , Toast.LENGTH_LONG).show();


            }



            }



    }
}
