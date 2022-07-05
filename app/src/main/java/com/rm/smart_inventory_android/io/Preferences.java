package com.rm.smart_inventory_android.io;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class Preferences {

    public static SharedPreferences sharedPreferences;
    public static String preferences = "SMART";
    private final String SHARED_FILE = "HMPrefs";
    private final String KEY_JSON_LOGIN = "json_login";
    private final String KEY_SKU = "item_sku";
    private final String KEY_FLAVOR = "item_sabor";
    private final String KEY_PRESENTATION = "item_presentacion";
    private final String KEY_ID_INVENTORY = "id_inventario";
    private final String KEY_ID_BODEGA="id_bodega";
    private final String ID_PRODUCT="id_producto";
    private final String USERNAME="nombre_usuario";
    private final String USER="usuario";
    private final String PASSWORD="password";
    private final String WOODENPALLET="tarimasmadera";
    private final String PLASTICPALLET="tarimasplasticas";
    private final String BOXES="cajas";
    private final String UNITS="unidades";
    private final String JSON_STATUS="json_estados";
    private final String JSON_STATUS_ID="json_estados_id";
    private final String IDASSIGNMENT="id_asignacion";
    private final String IDINVENTORY="idinventario";
    private final String articleId="id_articulo";

    public static void save(Activity activity, String key, String valor) {
        sharedPreferences = activity.getSharedPreferences(preferences, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, valor);
        editor.apply();
    }

    public static String get(Context context, String key) {
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
