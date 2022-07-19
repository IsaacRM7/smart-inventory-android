package com.rm.smart_inventory_android.io.db.inventroy;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.rm.smart_inventory_android.io.models.inventory.InventoryData;

import java.util.List;

@Dao
public interface InventoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(InventoryData... inventoryData);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertList(List<InventoryData> inventoryDataList);

    @Query("SELECT * FROM inventorydata")
    List<InventoryData> getAll();

    @Query("SELECT * FROM inventorydata WHERE sku LIKE '%' || :sku || '%' OR sku_name LIKE '%' || :sku || '%'")
    List<InventoryData> findBySku(String sku);

    @Delete
    void delete(InventoryData inventoryData);
}
