package com.rezazali.alarmgooya.service;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import com.rezazali.alarmgooya.R;

public class NotificationService extends BroadcastReceiver {

    public  String CHANNEL_ID="Marketing";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("NotificationService","NotificationService");
        Toast.makeText(context , "NotificationService" , Toast.LENGTH_LONG).show();


        NotificationManager manager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);


        NotificationCompat.Builder builder = new NotificationCompat.Builder(context ,CHANNEL_ID);


        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {


            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "Business", NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("Business notifications");

            manager.createNotificationChannel(channel);


        }

        builder.setContentTitle("Alarm");
        builder.setContentText("Please reply this message");
        builder.setSmallIcon(R.mipmap.ic_launcher);



        Intent intent1 = new Intent(Intent.ACTION_VIEW , Uri.parse("https://android-learn.ir"));
        PendingIntent pendingIntent = PendingIntent.getActivity(context , 1 , intent1 , 0);
        builder.setContentIntent(pendingIntent);


        int random= (int)Math.random();

        manager.notify(random , builder.build());




    }
}
