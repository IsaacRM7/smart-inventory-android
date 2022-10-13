package com.rm.smart_inventory_android.io.models.count;

import com.google.gson.annotations.SerializedName;

public class RecountData {
    public int id;
    @SerializedName("article_id")
    public int articleId;
    public String user;
    public String boxes;
    public String units;
    @SerializedName("wooden_platforms")
    public String woodenPlatforms;
    @SerializedName("plastic_platforms")
    public String plasticPlatforms;
    public String level;
    public int status;
    @SerializedName("status_name")
    public String statusName;
    @SerializedName("conversion_to_boxes")
    public String conversionToBoxes;
    @SerializedName("conversion_to_units")
    public String conversionToUnits;
    public String location;
    public String date;

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
