package com.rm.smart_inventory_android.io.models.state;

import java.util.ArrayList;
import java.util.List;

public class StateRoot {
    private List<StateData> data = new ArrayList<StateData>();
    private Boolean result;
    private String message;

    public List<StateData> getData() {
        return data;
    }
    public void setData(List<StateData> data) {
        this.data = data;
    }
    public Boolean getResult() {
        return result;
    }
    public void setResult(Boolean result) {
        this.result = result;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
