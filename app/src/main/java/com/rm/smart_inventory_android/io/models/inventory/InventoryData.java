package com.rm.smart_inventory_android.io.models.inventory;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class InventoryData {

    @PrimaryKey
    @SerializedName("id")
    @Expose
    private int id;

    @ColumnInfo(name = "sku")
    @SerializedName("sku")
    @Expose
    private String sku;

    @ColumnInfo(name = "sku_name")
    @SerializedName("sku_name")
    @Expose
    private String skuName;

    @ColumnInfo(name = "family")
    @SerializedName("family")
    @Expose
    private String family;

    @ColumnInfo(name = "family_name")
    @SerializedName("family_name")
    @Expose
    private String familyName;

    @ColumnInfo(name = "theoretical")
    @SerializedName("theoretical")
    @Expose
    private double theoretical;

    @ColumnInfo(name = "physical")
    @SerializedName("physical")
    @Expose
    private double physical;

    @ColumnInfo(name = "difference")
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
