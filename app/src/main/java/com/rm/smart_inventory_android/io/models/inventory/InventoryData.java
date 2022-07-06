package com.rm.smart_inventory_android.io.models.inventory;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InventoryData {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("sku")
    @Expose
    private String sku;

    @SerializedName("sku_name")
    @Expose
    private String skuName;

    @SerializedName("family")
    @Expose
    private String family;

    @SerializedName("family_name")
    @Expose
    private String familyName;

    @SerializedName("theoretical")
    @Expose
    private double theoretical;

    @SerializedName("physical")
    @Expose
    private double physical;

    private double difference;

    public InventoryData(String sku, String skuName, double theoretical, double physical, double difference) {
        this.sku = sku;
        this.skuName = skuName;
        this.theoretical = theoretical;
        this.physical = physical;
        this.difference = difference;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getSku() {
        return sku;
    }
    public void setSku(String sku) {
        this.sku = sku;
    }
    public String getSkuName() {
        return skuName;
    }
    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }
    public String getFamily() {
        return family;
    }
    public void setFamily(String family) {
        this.family = family;
    }
    public String getFamilyName() {
        return familyName;
    }
    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }
    public double getTheoretical() {
        return theoretical;
    }
    public void setTheoretical(double theoretical) {
        this.theoretical = theoretical;
    }

    public double getPhysical() {
        return physical;
    }

    public void setPhysical(double physical) {
        this.physical = physical;
    }

    public double getDifference() {
        return difference;
    }

    public void setDifference(double difference) {
        this.difference = difference;
    }
}
