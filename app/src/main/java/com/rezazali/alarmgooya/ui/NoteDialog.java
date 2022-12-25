package com.rezazali.alarmgooya.ui;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TimePicker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import com.rezazali.alarmgooya.R;
import com.rezazali.alarmgooya.db.Alarm;
import com.rezazali.alarmgooya.db.IListener;
import com.rezazali.alarmgooya.db.NoteDatabase;
import com.rezazali.alarmgooya.service.NotificationService;

import java.util.Calendar;

public class NoteDialog {


    Activity activity;
    int mHour;
    int mMinute;
    NoteDatabase noteDatabase;
    IListener listener;
    public NoteDialog(Activity activity ,IListener listener) {
        this.activity = activity;
        this.listener=listener;
        noteDatabase = NoteDatabase.getInstance();

    }

    public void showDialog() {

        AlertDialog alert = new AlertDialog.Builder(activity).create();

        LayoutInflater inflater = LayoutInflater.from(activity);
        View view = inflater.inflate(R.layout.add_note_layout, null);
        alert.setView(view);


        AppCompatEditText edt_title = view.findViewById(R.id.edt_title);
        AppCompatEditText edt_description = view.findViewById(R.id.edt_description);
        TimePicker time_picker = view.findViewById(R.id.time_picker);
        AppCompatButton btn_save = view.findViewById(R.id.btn_save);


        time_picker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int hour, int minute) {


                mHour = hour;

                mMinute = minute;

            }
        });


        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String title = edt_title.getText().toString();
                String description = edt_description.getText().toString();


                Alarm alarm = new Alarm(title, description, mHour, mMinute);

                long result = noteDatabase.idao().add(alarm);


                if (result > 0) {
                    Log.e("", "");

                    AlarmManager alarmManager = (AlarmManager)activity.getSystemService(Context.ALARM_SERVICE);

                    Intent intent1 = new Intent(activity, NotificationService.class);
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(activity, (int) result, intent1, 0);
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTimeInMillis(System.currentTimeMillis());
                    calendar.set(Calendar.HOUR_OF_DAY, mHour);
                    calendar.set(Calendar.MINUTE, mMinute);

                    alarmManager.set(AlarmManager.RTC_WAKEUP , calendar.getTimeInMillis() , pendingIntent);
                    listener.onListen();
                    alert.dismiss();

                } else {
                    Log.e("", "");
                }


            }
        });


        alert.show();


    }


}
