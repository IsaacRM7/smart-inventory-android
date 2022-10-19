package com.rm.smart_inventory_android.io.db.sendcount;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.rm.smart_inventory_android.io.Converter;

@Database(entities = {SendCountEntity.class}, version = 2, exportSchema = false)
@TypeConverters({Converter.class})
public abstract class SendCountDataBase extends RoomDatabase {
    public abstract SendCountDao countedDao();

    private static SendCountDataBase INSTANCE;

    public static SendCountDataBase getInstance(Context context){

        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), SendCountDataBase.class, "sendcountentity")
                    .allowMainThreadQueries()
                    .addMigrations(MIGRATION_1_2)
                    .build();
        }

        return INSTANCE;
    }

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("DROP TABLE sendcountentity");
            database.execSQL("CREATE TABLE IF NOT EXISTS 'sendcountentity' ('id' INTEGER NOT NULL, " +
                    "'counted_article_id' TEXT not null, 'article_id' INTEGER not null, 'location' TEXT," +
                    "'type_count' TEXT, 'material_id' TEXT, 'date' TEXT, 'wooden_platform' TEXT," +
                    "'plastic_platform' TEXT, 'boxes' TEXT, 'units' TEXT, 'giro_pallet' TEXT," +
                    "'separator' TEXT, 'squares' TEXT, 'level' TEXT," +
                    "PRIMARY KEY('id'))");
        }
    };
}
