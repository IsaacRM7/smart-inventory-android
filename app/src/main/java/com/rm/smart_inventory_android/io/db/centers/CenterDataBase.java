package com.rm.smart_inventory_android.io.db.centers;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.rm.smart_inventory_android.io.Converter;
import com.rm.smart_inventory_android.io.models.center.CenterData;

@Database(entities = {CenterData.class}, version = 1, exportSchema = false)
@TypeConverters({Converter.class})
public abstract class CenterDataBase extends RoomDatabase {
    public abstract CenterDao centerDao();

    private static CenterDataBase INSTANCE;

    public static CenterDataBase getInstance(Context context){

        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), CenterDataBase.class, "centerdata")
                    .allowMainThreadQueries()
                    .build();
        }

        return INSTANCE;
    }

}
