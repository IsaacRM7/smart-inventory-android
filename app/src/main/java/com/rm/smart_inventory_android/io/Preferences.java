package com.rm.smart_inventory_android.io;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.List;

public class Preferences {

    public static SharedPreferences sharedPreferences;
    public static String preferences = "SMART";

    public static void save(Activity activity, String key, String value) {
        sharedPreferences = activity.getSharedPreferences(preferences, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String get(Context context, String key) {
        sharedPreferences = context.getSharedPreferences(preferences, Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, "");
    }

    public static void saveList(Activity activity, String key, List<String> value) {
        sharedPreferences = activity.getSharedPreferences(preferences, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, String.valueOf(value));
        editor.apply();
    }

    public static String getList(Context context, String key) {
        sharedPreferences = context.getSharedPreferences(preferences, Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, "");
    }

    public static void saveIntList(Activity activity, String key, List<Integer> value) {
        sharedPreferences = activity.getSharedPreferences(preferences, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, String.valueOf(value));
        editor.apply();
    }

    public static String getIntList(Context context, String key) {
        sharedPreferences = context.getSharedPreferences(preferences, Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, "");
    }

    public static void delete(Activity activity, String key) {
        sharedPreferences = activity.getSharedPreferences(preferences, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(key);
        editor.apply();
    }

}
