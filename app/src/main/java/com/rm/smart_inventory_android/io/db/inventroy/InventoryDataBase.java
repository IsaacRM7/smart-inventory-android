package com.rm.smart_inventory_android.io.db.inventroy;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.rm.smart_inventory_android.io.models.inventory.InventoryData;

@Database(entities = {InventoryData.class}, version = 1, exportSchema = false)
public abstract class InventoryDataBase extends RoomDatabase{
    public abstract InventoryDao inventoryDao();

    private static InventoryDataBase INSTANCE;

    public static InventoryDataBase getInstance(Context context){

        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), InventoryDataBase.class, "inventorydata")
                    .allowMainThreadQueries()
                    .build();
        }

        return INSTANCE;

    }
}
