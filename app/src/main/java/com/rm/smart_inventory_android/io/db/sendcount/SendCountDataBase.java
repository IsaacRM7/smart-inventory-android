package com.rm.smart_inventory_android.io.db.sendcount;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.rm.smart_inventory_android.io.Converter;

@Database(entities = {SendCountEntity.class}, version = 1, exportSchema = false)
@TypeConverters({Converter.class})
public abstract class SendCountDataBase extends RoomDatabase {
    public abstract SendCountDao countedDao();

    private static SendCountDataBase INSTANCE;

    public static SendCountDataBase getInstance(Context context){

        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), SendCountDataBase.class, "sendcountentity")
                    .allowMainThreadQueries()
                    .build();
        }

        return INSTANCE;
    }
}
