package com.rm.smart_inventory_android.ui.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.rm.smart_inventory_android.R;
import com.rm.smart_inventory_android.io.ClickListener;
import com.rm.smart_inventory_android.io.Preferences;
import com.rm.smart_inventory_android.io.adapters.ApiRest;
import com.rm.smart_inventory_android.io.adapters.Service;
import com.rm.smart_inventory_android.io.db.inventroy.InventoryDataBase;
import com.rm.smart_inventory_android.io.models.inventory.InventoryData;
import com.rm.smart_inventory_android.io.models.inventory.InventoryRoot;
import com.rm.smart_inventory_android.io.models.login.UserRoot;
import com.rm.smart_inventory_android.io.models.state.StateRoot;
import com.rm.smart_inventory_android.ui.adapters.InventoryAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Inventory extends AppCompatActivity implements ClickListener, SearchView.OnQueryTextListener {

    private InventoryAdapter inventoryAdapter;
    private ArrayList<InventoryData> inventoryDataArrayList;
    private List<InventoryData> inventoryDataList;
    private InventoryDataBase db;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);
        db = InventoryDataBase.getInstance(Inventory.this);

        LinearLayoutManager linearLayout = new LinearLayoutManager(this);

        RecyclerView recyclerView = findViewById(R.id.sku_recycler);
        inventoryAdapter = new InventoryAdapter(this, this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayout);
        recyclerView.setAdapter(inventoryAdapter);

        NavigationView navigationView = findViewById(R.id.navigation_view);

        searchView = findViewById(R.id.searchview);
        searchView.setOnQueryTextListener(this);
        searchView.setQueryHint("Buscar SKU");

        navigationView.setNavigationItemSelectedListener(item -> {

            int id = item.getItemId();

            switch(id){
                case R.id.logout:
                    DialogInterface.OnClickListener dialogClickListener = (dialog, which) -> {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                logout();
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                break;
                        }
                    };

                    AlertDialog.Builder builder = new AlertDialog.Builder(Inventory.this);
                    builder.setMessage("¿Desea cerrar sesión?").setPositiveButton("Sí", dialogClickListener)
                            .setNegativeButton("No", dialogClickListener).show();
                    return false;

                case R.id.sync:
                    Toast.makeText(this, "Botón sincronizar", Toast.LENGTH_SHORT).show();
                    return false;

                default:
                    return false;
            }

        });

        getInventory();
        getStates();
    }

    private void logout(){
        Service service = ApiRest.getInterceptedApi().create(Service.class);
        String idUser = Preferences.get(Inventory.this, "user_id");
        Call<UserRoot> userRootCall = service.logout(idUser);

        userRootCall.enqueue(new Callback<UserRoot>() {
            @Override
            public void onResponse(@NonNull Call<UserRoot> call, @NonNull Response<UserRoot> response) {

                if(response.isSuccessful()) {
                    assert response.body() != null;
                    String message = response.body().getMessage();
                    Preferences.delete(Inventory.this, "user_id");
                    Preferences.delete(Inventory.this, "user");
                    Preferences.delete(Inventory.this, "password");
                    Preferences.delete(Inventory.this, "id_count_assigned");
                    Preferences.delete(Inventory.this, "token");
                    Preferences.delete(Inventory.this, "warehouse_id");
                    Toast.makeText(Inventory.this, message, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Inventory.this, Login.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(@NonNull Call<UserRoot> call, @NonNull Throwable t) {

            }
        });
    }

    private void getStates(){
        Service service = ApiRest.getInterceptedApi().create(Service.class);
        Call<StateRoot> stateRootCall = service.getStates();
        stateRootCall.enqueue(new Callback<StateRoot>() {
            @Override
            public void onResponse(@NonNull Call<StateRoot> call, @NonNull Response<StateRoot> response) {

                if(response.isSuccessful()){
                    StateRoot stateRoot = response.body();
                    List<String> stateList = new ArrayList<>();
                    List<Integer> stateIdList = new ArrayList<>();

                    for(int i=0;i<stateRoot.getData().size();i++){
                        stateList.add(stateRoot.getData().get(i).getState());
                        stateIdList.add(stateRoot.getData().get(i).getId());
                    }

                    Preferences.saveIntList(Inventory.this, "stateIdList", stateIdList);
                    Preferences.saveList(Inventory.this, "stateList", stateList);
                }
            }

            @Override
            public void onFailure(@NonNull Call<StateRoot> call, @NonNull Throwable t) {

            }
        });
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
            public void onResponse(@NonNull Call<InventoryRoot> call, @NonNull Response<InventoryRoot> response) {
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

                            db.inventoryDao().insertList(inventoryDataList);
                            inventoryAdapter.addList((ArrayList<InventoryData>) db.inventoryDao().getAll());
                            //inventoryAdapter.addList(inventoryDataArrayList);
                        }
                        else{
                            Toast.makeText(Inventory.this, inventoryRoot.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }catch (Exception ex){
                    System.out.println("ERROR: "+ex);
                }
            }

            @Override
            public void onFailure(@NonNull Call<InventoryRoot> call, @NonNull Throwable t) {
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

    @Override
    public boolean onQueryTextSubmit(String query) {

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        return false;
    }
}