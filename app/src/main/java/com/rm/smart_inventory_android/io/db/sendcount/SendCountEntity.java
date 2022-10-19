package com.rm.smart_inventory_android.io.db.sendcount;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class SendCountEntity {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;

    @ColumnInfo(name = "counted_article_id")
    @NonNull
    private String countedArticleId;

    @ColumnInfo(name = "article_id")
    private int articleId;

    @ColumnInfo(name = "location")
    private String location;

    @ColumnInfo(name = "type_count")
    private String typeCount;

    @ColumnInfo(name = "material_id")
    private String materialId;

    @ColumnInfo(name = "date")
    private String date;

    @ColumnInfo(name = "wooden_platform")
    private String woodenPlatform;

    @ColumnInfo(name = "plastic_platform")
    private String plasticPlatform;

    @ColumnInfo(name = "boxes")
    private String boxes;

    @ColumnInfo(name = "units")
    private String units;

    @ColumnInfo(name = "giro_pallet")
    private String giroPallet;

    @ColumnInfo(name = "separator")
    private String separator;

    @ColumnInfo(name = "squares")
    private String squares;

    @ColumnInfo(name = "level")
    private String level;

    public SendCountEntity(@NonNull String countedArticleId, int articleId, String location, String typeCount, String materialId, String date, String woodenPlatform, String plasticPlatform, String boxes, String units, String giroPallet, String separator, String squares, String level) {
        this.countedArticleId = countedArticleId;
        this.articleId = articleId;
        this.location = location;
        this.typeCount = typeCount;
        this.materialId = materialId;
        this.date = date;
        this.woodenPlatform = woodenPlatform;
        this.plasticPlatform = plasticPlatform;
        this.boxes = boxes;
        this.units = units;
        this.giroPallet = giroPallet;
        this.separator = separator;
        this.squares = squares;
        this.level = level;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWoodenPlatform() {
        return woodenPlatform;
    }

    public void setWoodenPlatform(String woodenPlatform) {
        this.woodenPlatform = woodenPlatform;
    }

    public String getPlasticPlatform() {
        return plasticPlatform;
    }

    public void setPlasticPlatform(String plasticPlatform) {
        this.plasticPlatform = plasticPlatform;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTypeCount() {
        return typeCount;
    }

    public void setTypeCount(String typeCount) {
        this.typeCount = typeCount;
    }

    public String getCountedArticleId() {
        return countedArticleId;
    }

    public void setCountedArticleId(String countedArticleId) {
        this.countedArticleId = countedArticleId;
    }

    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPlatform() {
        return woodenPlatform;
    }

    public void setPlatform(String woodenPlatform) {
        this.woodenPlatform = woodenPlatform;
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

    public String getGiroPallet() {
        return giroPallet;
    }

    public void setGiroPallet(String giroPallet) {
        this.giroPallet = giroPallet;
    }

    public String getSeparator() {
        return separator;
    }

    public void setSeparator(String separator) {
        this.separator = separator;
    }

    public String getSquares() {
        return squares;
    }

    public void setSquares(String squares) {
        this.squares = squares;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
