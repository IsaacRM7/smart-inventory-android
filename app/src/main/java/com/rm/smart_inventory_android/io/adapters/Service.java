package com.rm.smart_inventory_android.io.adapters;

import com.rm.smart_inventory_android.io.models.center.CenterRoot;
import com.rm.smart_inventory_android.io.models.inventory.InventoryRoot;
import com.rm.smart_inventory_android.io.models.login.UserRoot;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

public interface Service {
    String wsLogin = "login/mobile";
    String wsCenter = "center/data";
    String wsInventory = "master/data";

    @POST(wsLogin)
    Call<UserRoot> login(@Body Map<String, String> params);

    @GET(wsCenter)
    Call<CenterRoot> getCenters(@QueryMap Map<String, String> params);

    @GET(wsInventory)
    Call<InventoryRoot> getInventory(@QueryMap Map<String, String> params);
}
