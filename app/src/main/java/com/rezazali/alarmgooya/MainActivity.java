package com.rezazali.alarmgooya;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.rezazali.alarmgooya.adapter.AlarmAdapter;
import com.rezazali.alarmgooya.config.AppConfiguration;
import com.rezazali.alarmgooya.databinding.ActivityMainBinding;
import com.rezazali.alarmgooya.db.IListener;
import com.rezazali.alarmgooya.db.NoteDatabase;
import com.rezazali.alarmgooya.models.RepeatTaskService;
import com.rezazali.alarmgooya.service.DownloadService;
import com.rezazali.alarmgooya.ui.NoteDialog;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;

public class MainActivity extends AppCompatActivity implements IListener {


    NoteDialog noteDialog;

    ActivityMainBinding binding;

    NoteDatabase noteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        noteDialog = new NoteDialog(MainActivity.this , this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        noteDatabase = NoteDatabase.getInstance();

       /* Dexter.withContext(getApplicationContext())
                .withPermission(Manifest.permission.CAMERA)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        Log.e("","");
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                        Log.e("","");
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {

                    }
                }).check();*/


        Dexter.withContext(getApplicationContext())
                .withPermissions(Manifest.permission.READ_SMS, Manifest.permission.RECEIVE_SMS)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {
                        Log.e("", "");
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {
                        Log.e("", "");
                    }
                }).check();

        Intent intent = new Intent(getApplicationContext(), DownloadService.class);

        //  startService(intent);


        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        Intent intent1 = new Intent(getApplicationContext(), RepeatTaskService.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 1, intent1, 0);


        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 60 * 1000, pendingIntent);


        binding.fabAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                noteDialog.showDialog();

            }
        });

        loadAlarmList();

    }


    public void loadAlarmList() {

        binding.recyclerAlarm.setAdapter(new AlarmAdapter());
        binding.recyclerAlarm.setLayoutManager(new
                LinearLayoutManager(AppConfiguration.getContext(),
                RecyclerView.VERTICAL, false));

    }

    @Override
    public void onListen() {
        loadAlarmList();
    }
}