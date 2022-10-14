package com.rm.smart_inventory_android.io.db.counted;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.rm.smart_inventory_android.io.Converter;
import com.rm.smart_inventory_android.io.models.count.RecountData;

@Database(entities = {RecountData.class}, version = 1, exportSchema = false)
@TypeConverters({Converter.class})
public abstract class CountedDataBase extends RoomDatabase {
    public abstract CountedDao countedDao();

    private static CountedDataBase INSTANCE;

    public static CountedDataBase getInstance(Context context){

        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), CountedDataBase.class, "recountdata")
                    .allowMainThreadQueries()
                    .build();
        }

        return INSTANCE;
    }
}
