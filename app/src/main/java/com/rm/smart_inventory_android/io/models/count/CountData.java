package com.rm.smart_inventory_android.io.models.count;

public class CountData {

    private String measure;
    private Object amount;
    private Object currentAmount;

    public CountData(String measure, Object amount, Object currentAmount){
        this.measure = measure;
        this.amount = amount;
        this.currentAmount = currentAmount;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public Object getAmount() {
        return amount;
    }

    public void setAmount(Object amount) {
        this.amount = amount;
    }

    public Object getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(Object currentAmount) {
        this.currentAmount = currentAmount;
    }
}
