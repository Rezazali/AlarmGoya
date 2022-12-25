package com.rezazali.alarmgooya.service;

import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.rezazali.alarmgooya.BuildConfig;
import com.rezazali.alarmgooya.api.ApiCaller;
import com.rezazali.alarmgooya.models.App;
import com.rezazali.alarmgooya.models.IResponseListener;

public class DownloadService  extends Service {


    ApiCaller apiCaller;
    @Override
    public void onCreate() {
        super.onCreate();

        apiCaller = new ApiCaller();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {



        apiCaller.getCurrentVersion(new IResponseListener() {
            @Override
            public void onSuccess(Object responseMessage) {

                App app = (App) responseMessage;

               int myVersion  = BuildConfig.VERSION_CODE;

               int server_version= app.getVersion();

               if(myVersion<server_version) {


                   Intent intent1 = new Intent(Intent.ACTION_VIEW, Uri.parse(app.getDownload()));
                   intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                   startActivity(intent1);

               }else {

               }




            }

            @Override
            public void onFailure(Object errorResponseMessage) {

            }
        });

        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
