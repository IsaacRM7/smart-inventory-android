package com.rm.smart_inventory_android.io.models.count;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity
public class RecountData {

    @PrimaryKey
    private int id;

    @ColumnInfo(name = "article_id")
    @SerializedName("article_id")
    private int articleId;

    @ColumnInfo(name = "user")
    private String user;

    @ColumnInfo(name = "boxes")
    private String boxes;

    @ColumnInfo(name = "units")
    private String units;

    @ColumnInfo(name = "wooden_platforms")
    @SerializedName("wooden_platforms")
    private String woodenPlatforms;

    @ColumnInfo(name = "plastic_platforms")
    @SerializedName("plastic_platforms")
    private String plasticPlatforms;

    @ColumnInfo(name = "level")
    private String level;

    @ColumnInfo(name = "status")
    private int status;

    @ColumnInfo(name = "status_name")
    @SerializedName("status_name")
    private String statusName;

    @ColumnInfo(name = "conversion_to_boxes")
    @SerializedName("conversion_to_boxes")
    private String conversionToBoxes;

    @ColumnInfo(name = "conversion_to_units")
    @SerializedName("conversion_to_units")
    private String conversionToUnits;

    @ColumnInfo(name = "location")
    private String location;

    @ColumnInfo(name = "date")
    private String date;

    @Ignore
    public RecountData(int id, String user, String boxes, String units, String woodenPlatforms, String plasticPlatforms, int status, String date) {
        this.id = id;
        this.user = user;
        this.boxes = boxes;
        this.units = units;
        this.woodenPlatforms = woodenPlatforms;
        this.plasticPlatforms = plasticPlatforms;
        this.status = status;
        this.date = date;
    }


    public RecountData(int id, int articleId, String user, String boxes, String units, String woodenPlatforms, String plasticPlatforms, String level, int status, String statusName, String conversionToBoxes, String conversionToUnits, String location, String date) {
        this.id = id;
        this.articleId = articleId;
        this.user = user;
        this.boxes = boxes;
        this.units = units;
        this.woodenPlatforms = woodenPlatforms;
        this.plasticPlatforms = plasticPlatforms;
        this.level = level;
        this.status = status;
        this.statusName = statusName;
        this.conversionToBoxes = conversionToBoxes;
        this.conversionToUnits = conversionToUnits;
        this.location = location;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getBoxes() {
        return boxes;
    }

    public void setBoxes(String boxes) {
        this.boxes = boxes;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public String getWoodenPlatforms() {
        return woodenPlatforms;
    }

    public void setWoodenPlatforms(String woodenPlatforms) {
        this.woodenPlatforms = woodenPlatforms;
    }

    public String getPlasticPlatforms() {
        return plasticPlatforms;
    }

    public void setPlasticPlatforms(String plasticPlatforms) {
        this.plasticPlatforms = plasticPlatforms;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getConversionToBoxes() {
        return conversionToBoxes;
    }

    public void setConversionToBoxes(String conversionToBoxes) {
        this.conversionToBoxes = conversionToBoxes;
    }

    public String getConversionToUnits() {
        return conversionToUnits;
    }

    public void setConversionToUnits(String conversionToUnits) {
        this.conversionToUnits = conversionToUnits;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
