package com.rm.smart_inventory_android.io.db.centers;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.rm.smart_inventory_android.io.models.center.CenterData;

import java.util.List;

@Dao
public interface CenterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(CenterData... centerData);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertList(List<CenterData> centerDataList);

    @Query("SELECT * FROM centerdata")
    List<CenterData> getAll();

    @Delete
    void delete (CenterData centerData);

}
