package com.rm.smart_inventory_android.io.models.count;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CountedData {

    @SerializedName("message")
    @Expose
    private String message;

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
