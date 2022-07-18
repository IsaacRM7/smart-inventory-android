package com.rm.smart_inventory_android.ui.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.rm.smart_inventory_android.R;
import com.rm.smart_inventory_android.io.Preferences;
import com.rm.smart_inventory_android.io.adapters.ApiRest;
import com.rm.smart_inventory_android.io.adapters.Service;
import com.rm.smart_inventory_android.io.db.centers.CenterDataBase;
import com.rm.smart_inventory_android.io.models.center.CenterData;
import com.rm.smart_inventory_android.io.models.center.CenterRoot;
import com.rm.smart_inventory_android.io.models.center.WarehouseData;
import com.rm.smart_inventory_android.ui.adapters.CenterAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Center extends AppCompatActivity {

    private RecyclerView centerRecyclerview;
    private List<CenterData> warehouseDataList;
    private CenterAdapter centerAdapter;
    private CenterDataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_center);

        warehouseDataList = new ArrayList<>();

        centerRecyclerview = findViewById(R.id.warehouse_recycler);
        centerRecyclerview.setHasFixedSize(true);
        centerRecyclerview.setLayoutManager(new LinearLayoutManager(this));

        getCenters();
    }

    private void getCenters(){
        Service service = ApiRest.getInterceptedApi().create(Service.class);
        db = CenterDataBase.getInstance(Center.this);

        String token = Preferences.get(Center.this, "token");
        String idCount = Preferences.get(Center.this, "id_count_assigned");
        String idUser = Preferences.get(Center.this, "user_id");

        HashMap<String, String> params = new HashMap<>();
        params.put("id_count_assigned", idCount);
        params.put("user_id", idUser);
        ApiRest.TOKEN = token;

        Call<CenterRoot> centerRootCall = service.getCenters(params);
        centerRootCall.enqueue(new Callback<CenterRoot>() {
            @Override
            public void onResponse(@NonNull Call<CenterRoot> call, @NonNull Response<CenterRoot> response) {
                try {
                    if (response.isSuccessful()) {
                        CenterRoot centerRoot = response.body();
                        assert centerRoot != null;
                        List<CenterData> centerDataList = centerRoot.getData();

                        for (int i = 0; i < centerDataList.size(); i++) {
                            int centerId = centerDataList.get(i).getId();
                            String centerName = centerDataList.get(i).getName();
                            String centerCode = centerDataList.get(i).getCode();

                            List<WarehouseData> warehouseList = centerDataList.get(i).getWarehouses();
                            List<WarehouseData> items = new ArrayList<>();

                            for (int j = 0; j < warehouseList.size(); j++) {
                                int warehouseId = warehouseList.get(j).getId();
                                String warehouseName = warehouseList.get(j).getName();
                                String code = warehouseList.get(j).getCode();

                                items.add(new WarehouseData(warehouseId, code, warehouseName));
                            }

                            if(centerDataList.get(i).getCount() == 1){
                                warehouseDataList.add(new CenterData(centerId, centerName+" ("+centerCode+") - ✔", items));
                                //centerData.add(new CenterData(centerId, centerName+" ("+centerCode+") - ✔", warehouseList));
                            }
                            else{
                                warehouseDataList.add(new CenterData(centerId, centerName+" ("+centerCode+")", items));
                                //centerData.add(new CenterData(centerId, centerName+" ("+centerCode+")", warehouseList));
                            }

                        }
                        db.centerDao().insertList(warehouseDataList);

                        Toast.makeText(Center.this, centerRoot.getMessage(), Toast.LENGTH_SHORT).show();

                        centerAdapter = new CenterAdapter(warehouseDataList, Center.this);
                        centerRecyclerview.setAdapter(centerAdapter);
                    }
                }catch (Exception ex){
                    Toast.makeText(Center.this, "Ocurrió un error", Toast.LENGTH_SHORT).show();
                    ex.getLocalizedMessage();
                }
            }

            @Override
            public void onFailure(@NonNull Call<CenterRoot> call, @NonNull Throwable t) {

                loadDatabaseCenters();
                t.getMessage();
            }
        });
    }

    private void deleteDatabaseCenters(){
        db = CenterDataBase.getInstance(Center.this);
        db.clearAllTables();
    }

    private void loadDatabaseCenters(){
        ApiRest.TOKEN = Preferences.get(Center.this, "token");
        db = CenterDataBase.getInstance(Center.this);
        centerAdapter = new CenterAdapter(db.centerDao().getAll(), Center.this);
        centerRecyclerview.setAdapter(centerAdapter);
    }
}