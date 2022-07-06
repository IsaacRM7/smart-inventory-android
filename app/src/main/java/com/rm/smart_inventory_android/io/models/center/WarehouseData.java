package com.rm.smart_inventory_android.io.models.center;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WarehouseData {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("code")
    @Expose
    private String code;

    @SerializedName("name")
    @Expose
    private String name;

    public WarehouseData(int id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
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

}
