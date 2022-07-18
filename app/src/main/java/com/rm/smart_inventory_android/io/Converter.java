package com.rm.smart_inventory_android.io;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rm.smart_inventory_android.io.models.center.WarehouseData;

import java.lang.reflect.Type;
import java.util.List;

public class Converter {

    @TypeConverter
    public static List<WarehouseData> fromString(String value) {
        Type listType = new TypeToken<List<WarehouseData>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }
    @TypeConverter
    public static String fromArrayList(List<WarehouseData> list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }
}
