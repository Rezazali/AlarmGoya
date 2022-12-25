package com.rezazali.alarmgooya.db;


import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.rezazali.alarmgooya.config.AppConfiguration;

@Database(entities = {Alarm.class}, version = 1, exportSchema = false)
public abstract class NoteDatabase extends RoomDatabase {


    public abstract IDAO idao();

    public static NoteDatabase instance = null;


    public static synchronized NoteDatabase getInstance() {


        if (instance == null) {
            instance = Room.databaseBuilder(AppConfiguration.getContext(), NoteDatabase.class,
                    "alarm.db")
                    .allowMainThreadQueries()
                    .build();
        }

        return instance;


    }


}
