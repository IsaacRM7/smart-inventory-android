package com.rm.smart_inventory_android.io.models.inventory;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class InventoryRoot {

    @SerializedName("data")
    @Expose
    private List<InventoryData> data = new ArrayList<InventoryData>();

    @SerializedName("result")
    @Expose
    private boolean result;

    @SerializedName("recordsTotal")
    @Expose
    private int recordsTotal;

    @SerializedName("message")
    @Expose
    private String message;

    public List<InventoryData> getData() {
        return data;
    }
    public void setData(List<InventoryData> data) {
        this.data = data;
    }
    public boolean getResult() {
        return result;
    }
    public void setResult(boolean result) {
        this.result = result;
    }
    public int getRecordsTotal() {
        return recordsTotal;
    }
    public void setRecordsTotal(int recordsTotal) {
        this.recordsTotal = recordsTotal;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
