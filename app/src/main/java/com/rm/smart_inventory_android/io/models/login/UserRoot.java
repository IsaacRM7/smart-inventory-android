package com.rm.smart_inventory_android.io.models.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class UserRoot {
    @SerializedName("data")
    @Expose
    private List<UserData> data = new ArrayList<UserData>();

    @SerializedName("status")
    @Expose
    private int status;

    @SerializedName("result")
    @Expose
    private boolean result;

    @SerializedName("token")
    @Expose
    private String token;

    @SerializedName("message")
    @Expose
    private String message;

    public List<UserData> getData() {
        return data;
    }
    public void setData(List<UserData> data) {
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
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
