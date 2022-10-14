package com.rm.smart_inventory_android.io.models.count;

import com.google.gson.annotations.SerializedName;

public class RecountData {
    private int id;
    @SerializedName("article_id")
    private int articleId;
    private String user;
    private String boxes;
    private String units;
    @SerializedName("wooden_platforms")
    private String woodenPlatforms;
    @SerializedName("plastic_platforms")
    private String plasticPlatforms;
    private String level;
    private int status;
    @SerializedName("status_name")
    private String statusName;
    @SerializedName("conversion_to_boxes")
    private String conversionToBoxes;
    @SerializedName("conversion_to_units")
    private String conversionToUnits;
    private String location;
    private String date;

    public RecountData(String user, String boxes, String units, String woodenPlatforms, String plasticPlatforms, int status, String date) {
        this.user = user;
        this.boxes = boxes;
        this.units = units;
        this.woodenPlatforms = woodenPlatforms;
        this.plasticPlatforms = plasticPlatforms;
        this.status = status;
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
