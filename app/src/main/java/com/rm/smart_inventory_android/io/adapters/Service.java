package com.rm.smart_inventory_android.io.adapters;

import com.rm.smart_inventory_android.io.models.bodega.CenterRoot;
import com.rm.smart_inventory_android.io.models.login.UserRoot;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface Service {
    String wsLogin = "login/mobile";
    String wsCenter = "center/data";

    @POST(wsLogin)
    Call<UserRoot> login(@Body Map<String, String> params);

    @GET(wsCenter)
    Call<CenterRoot> postBodegas(@QueryMap Map<String, String> params);
}
