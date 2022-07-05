package com.rm.smart_inventory_android.io.models.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserData {
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("user_type")
    @Expose
    private int userType;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("user")
    @Expose
    private String user;

    @SerializedName("country_id")
    @Expose
    private int countryId;

    @SerializedName("active")
    @Expose
    private int active;

    @SerializedName("id_count_assigned")
    @Expose
    private int idCountAssigned;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getUserType() {
        return userType;
    }
    public void setUserType(int userType) {
        this.userType = userType;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }
    public int getCountryId() {
        return countryId;
    }
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }
    public int getActive() {
        return active;
    }
    public void setActive(int active) {
        this.active = active;
    }
    public int getIdCountAssigned() {
        return idCountAssigned;
    }
    public void setIdCountAssigned(int idCountAssigned) {
        this.idCountAssigned = idCountAssigned;
    }
}
