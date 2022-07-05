package com.rm.smart_inventory_android.io.adapters;

import com.rm.smart_inventory_android.io.models.login.UserRoot;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Service {
    String wsLogin = "login/mobile";

    @POST(wsLogin)
    Call<UserRoot> login(@Body Map<String, String> params);
}
