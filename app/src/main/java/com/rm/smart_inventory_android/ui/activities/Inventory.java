package com.rm.smart_inventory_android.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.rm.smart_inventory_android.R;
import com.rm.smart_inventory_android.io.ClickListener;
import com.rm.smart_inventory_android.io.Preferences;
import com.rm.smart_inventory_android.io.adapters.ApiRest;
import com.rm.smart_inventory_android.io.adapters.Service;
import com.rm.smart_inventory_android.io.models.inventory.InventoryData;
import com.rm.smart_inventory_android.io.models.inventory.InventoryRoot;
import com.rm.smart_inventory_android.ui.adapters.InventoryAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Inventory extends AppCompatActivity implements ClickListener {

    private InventoryAdapter inventoryAdapter;
    private ArrayList<InventoryData> inventoryDataArrayList;
    private List<InventoryData> inventoryDataList;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        LinearLayoutManager linearLayout = new LinearLayoutManager(this);

        recyclerView = findViewById(R.id.sku_recycler);
        inventoryAdapter = new InventoryAdapter(this, this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayout);
        recyclerView.setAdapter(inventoryAdapter);

        NavigationView navigationView = findViewById(R.id.navigation_view);

        navigationView.setNavigationItemSelectedListener(item -> {

            int id = item.getItemId();

            switch(id){
                case R.id.logout:
                    return false;

                case R.id.sync:
                    System.out.println("Botón para sincronizar");
                    return false;

                default:
                    return false;
            }

        });

        getInventory();
    }

    private void getInventory(){
        inventoryDataArrayList = new ArrayList<>();
        String idCount = Preferences.get(Inventory.this, "id_count_assigned");
        String idWarehouse = Preferences.get(Inventory.this, "warehouse_id");
        String idUser = Preferences.get(Inventory.this, "user_id");

        Service service = ApiRest.getInterceptedApi().create(Service.class);
        HashMap<String, String> params = new HashMap<>();

        params.put("id_count_assigned", idCount);
        params.put("warehouse_id", idWarehouse);
        params.put("user_id", idUser);

        Call<InventoryRoot> inventoryRootCall = service.getInventory(params);
        inventoryRootCall.enqueue(new Callback<InventoryRoot>() {
            @Override
            public void onResponse(Call<InventoryRoot> call, Response<InventoryRoot> response) {
                try{
                    if(response.isSuccessful()){
                        InventoryRoot inventoryRoot = response.body();
                        assert inventoryRoot != null;
                        if(inventoryRoot.getResult()){
                            inventoryDataList = inventoryRoot.getData();

                            for(int i=0;i<inventoryDataList.size();i++){
                                int id = inventoryDataList.get(i).getId();
                                String sku = inventoryDataList.get(i).getSku();
                                String skuName = inventoryDataList.get(i).getSkuName();
                                double theoretical = inventoryDataList.get(i).getTheoretical();
                                double physical = inventoryDataList.get(i).getPhysical();
                                double difference = theoretical-physical;

                                inventoryDataArrayList.add(new InventoryData(sku, skuName, theoretical, physical, difference));
                            }

                            inventoryAdapter.addList(inventoryDataArrayList);
                        }
                        else{
                            Toast.makeText(Inventory.this, inventoryRoot.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }catch (Exception ex){
                    System.out.println("ERRORR: "+ex);
                }
            }

            @Override
            public void onFailure(Call<InventoryRoot> call, Throwable t) {
                System.out.println("Error: "+t.getMessage());
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Inventory.this, Center.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(int position) {
        System.out.println(position);
    }
}