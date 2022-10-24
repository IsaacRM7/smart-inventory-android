package com.rm.smart_inventory_android.io.db.counted;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.rm.smart_inventory_android.io.models.count.recount.RecountData;

import java.util.List;

@Dao
public interface CountedDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(RecountData... recountData);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertList(List<RecountData> recountDataList);

    @Query("SELECT * FROM recountdata")
    List<RecountData> getAll();

    @Query("SELECT * FROM recountdata WHERE article_id = :id")
    List<RecountData> findCountedList(int id);

    @Delete
    void delete (RecountData recountData);
}
