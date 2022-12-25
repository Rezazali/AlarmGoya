package com.rezazali.alarmgooya.adapter;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.rezazali.alarmgooya.R;
import com.rezazali.alarmgooya.config.AppConfiguration;
import com.rezazali.alarmgooya.db.Alarm;
import com.rezazali.alarmgooya.db.NoteDatabase;
import com.rezazali.alarmgooya.service.NotificationService;

import java.util.List;

public class AlarmAdapter extends RecyclerView.Adapter<AlarmAdapter.AlarmVH> {


    NoteDatabase noteDatabase;
    LayoutInflater inflater;
    List<Alarm> alarmList;



    public AlarmAdapter() {
        noteDatabase = NoteDatabase.getInstance();
        inflater = LayoutInflater.from(AppConfiguration.getContext());
        alarmList =noteDatabase.idao().showAll();
        Log.e("alarmList",""+alarmList.size());
    }


    @NonNull
    @Override
    public AlarmVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.alarm_row , null);
        return new AlarmVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlarmVH holder, @SuppressLint("RecyclerView") int position) {

        Alarm alarm = alarmList.get(position);

        holder.txt_hour.setText( String.valueOf(alarm.getHour()) );
        holder.txt_minute.setText( String.valueOf(alarm.getMinute()) );

        holder.img_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                AlarmManager alarmManager = (AlarmManager)
                        AppConfiguration.getContext().getSystemService(Context.ALARM_SERVICE);

                Intent intent1 = new Intent(AppConfiguration.getContext(), NotificationService.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(
                        AppConfiguration.getContext(), alarm.getId(), intent1, 0);
                alarmManager.cancel(pendingIntent);


                noteDatabase.idao().deleteAlarm(alarm.getId());

                alarmList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeRemoved(position , alarmList.size());

            }
        });


    }

    @Override
    public int getItemCount() {
        return alarmList.size();
    }

    class AlarmVH extends RecyclerView.ViewHolder {


        AppCompatTextView txt_minute,txt_hour;
        AppCompatImageView img_delete;

        public AlarmVH(@NonNull View itemView) {
            super(itemView);
            txt_minute =  itemView.findViewById(R.id.txt_minute);
            txt_hour =  itemView.findViewById(R.id.txt_hour);
            img_delete =  itemView.findViewById(R.id.img_delete);
        }
    }
}
