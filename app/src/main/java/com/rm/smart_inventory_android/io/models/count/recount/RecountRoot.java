package com.rm.smart_inventory_android.io.models.count.recount;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class RecountRoot {
    private List<RecountData> data = new ArrayList();
    @SerializedName("wooden_platforms")
    private Integer woodenPlatforms;
    @SerializedName("plastic_platforms")
    private Integer plasticPlatforms;
    private Integer boxes;
    private Integer units;
    private Integer level;
    public List<RecountData> getData() {
        return data;
    }
    public void setData(List<RecountData> data) {
        this.data = data;
    }
    public Integer getWoodenPlatforms() {
        return woodenPlatforms;
    }
    public void setWoodenPlatforms(Integer woodenPlatforms) {
        this.woodenPlatforms = woodenPlatforms;
    }
    public Integer getPlasticPlatforms() {
        return plasticPlatforms;
    }
    public void setPlasticPlatforms(Integer plasticPlatforms) {
        this.plasticPlatforms = plasticPlatforms;
    }
    public Integer getBoxes() {
        return boxes;
    }
    public void setBoxes(Integer boxes) {
        this.boxes = boxes;
    }
    public Integer getUnits() {
        return units;
    }
    public void setUnits(Integer units) {
        this.units = units;
    }
    public Integer getLevel() {
        return level;
    }
    public void setLevel(Integer level) {
        this.level = level;
    }
}
