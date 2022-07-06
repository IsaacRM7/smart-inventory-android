package com.rm.smart_inventory_android.io.models.center;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class CenterData {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("code")
    @Expose
    private String code;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("count")
    @Expose
    private int count;

    @SerializedName("warehouses")
    @Expose
    private List<WarehouseData> warehouses = new ArrayList<WarehouseData>();

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
