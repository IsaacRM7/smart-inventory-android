package com.rm.smart_inventory_android.io.models.center;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.rm.smart_inventory_android.io.Converter;

import java.util.ArrayList;
import java.util.List;

@Entity
public class CenterData {

    @PrimaryKey
    @SerializedName("id")
    @Expose
    private int id;

    @ColumnInfo(name = "code")
    @SerializedName("code")
    @Expose
    private String code;

    @ColumnInfo(name = "name")
    @SerializedName("name")
    @Expose
    private String name;

    @ColumnInfo(name = "count")
    @SerializedName("count")
    @Expose
    private int count;

    @TypeConverters(Converter.class)
    @SerializedName("warehouses")
    @Expose
    private List<WarehouseData> warehouses = new ArrayList<>();

    private boolean expandable;

    public CenterData(int id, String name, List<WarehouseData> warehouses) {
        this.id = id;
        this.name = name;
        this.warehouses = warehouses;
        expandable = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<WarehouseData> getWarehouses() {
        return warehouses;
    }

    public void setWarehouses(List<WarehouseData> warehouses) {
        this.warehouses = warehouses;
    }

    public boolean isExpandable() {
        return expandable;
    }

    public void setExpandable(boolean expandable) {
        this.expandable = expandable;
    }

}
