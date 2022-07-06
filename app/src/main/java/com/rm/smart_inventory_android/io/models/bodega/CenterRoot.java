package com.rm.smart_inventory_android.io.models.bodega;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class CenterRoot {

    @SerializedName("data")
    @Expose
    private List<CenterData> data = new ArrayList<CenterData>();

    @SerializedName("status")
    @Expose
    private int status;

    @SerializedName("result")
    @Expose
    private boolean result;

    @SerializedName("message")
    @Expose
    private String message;

    public List<CenterData> getData() {
        return data;
    }

    public void setData(List<CenterData> data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean getResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
