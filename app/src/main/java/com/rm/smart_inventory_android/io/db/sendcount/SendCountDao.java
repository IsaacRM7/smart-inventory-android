package com.rm.smart_inventory_android.io.db.sendcount;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SendCountDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(SendCountEntity... sendCountEntity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertList(List<SendCountEntity> recountDataList);

    @Query("SELECT * FROM sendcountentity")
    List<SendCountEntity> getAll();

    @Delete
    void delete (SendCountEntity sendCountEntity);

}
