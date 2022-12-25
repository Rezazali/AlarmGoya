package com.rezazali.alarmgooya.db;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface IDAO {


    @Insert
    long add(Alarm alarm);

    @Delete
    void delete(Alarm alarm);

    @Query("delete from tbl_alarm where id= :alarmId")
    void deleteAlarm(int alarmId);



    @Query("select * from tbl_alarm")
    List<Alarm> showAll();


}
