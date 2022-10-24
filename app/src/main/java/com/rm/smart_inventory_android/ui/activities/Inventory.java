package com.rm.smart_inventory_android.ui.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.rm.smart_inventory_android.R;
import com.rm.smart_inventory_android.io.ClickListener;
import com.rm.smart_inventory_android.io.Preferences;
import com.rm.smart_inventory_android.io.adapters.ApiRest;
import com.rm.smart_inventory_android.io.adapters.Service;
import com.rm.smart_inventory_android.io.db.centers.CenterDataBase;
import com.rm.smart_inventory_android.io.db.counted.CountedDataBase;
import com.rm.smart_inventory_android.io.db.inventroy.InventoryDataBase;
import com.rm.smart_inventory_android.io.db.sendcount.SendCountDataBase;
import com.rm.smart_inventory_android.io.models.count.SendCountData;
import com.rm.smart_inventory_android.io.models.inventory.InventoryData;
import com.rm.smart_inventory_android.io.models.inventory.InventoryRoot;
import com.rm.smart_inventory_android.io.models.login.UserRoot;
import com.rm.smart_inventory_android.io.models.state.StateRoot;
import com.rm.smart_inventory_android.ui.adapters.InventoryAdapter;
import com.rm.smart_inventory_android.ui.dialogs.Progress;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Inventory extends AppCompatActivity implements ClickListener, SearchView.OnQueryTextListener {

    private InventoryAdapter inventoryAdapter;
    private List<InventoryData> inventoryDataList;
    private InventoryDataBase dbInventory;
    private SendCountDataBase dbSendCount;
    private CenterDataBase dbCenter;
    private CountedDataBase dbCounted;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);
        dbInventory = InventoryDataBase.getInstance(Inventory.this);
        dbSendCount = SendCountDataBase.getInstance(Inventory.this);
        dbCenter = CenterDataBase.getInstance(Inventory.this);
        dbCounted = CountedDataBase.getInstance(Inventory.this);

        LinearLayoutManager linearLayout = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
                false);
        RecyclerView recyclerView = findViewById(R.id.sku_recycler);
        inventoryAdapter = new InventoryAdapter(this, this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayout);
        recyclerView.setAdapter(inventoryAdapter);

        TextView txtTheoreticalTitle = findViewById(R.id.txt_theoretical_title);
        TextView txtPhysicalTitle = findViewById(R.id.txt_physical_title);
        TextView txtDifferenceTitle = findViewById(R.id.txt_difference_title);

        NavigationView navigationView = findViewById(R.id.navigation_view);

        if(!Preferences.get(Inventory.this, "user_type").equals("1")){
            txtDifferenceTitle.setVisibility(View.INVISIBLE);
            txtTheoreticalTitle.setVisibility(View.INVISIBLE);
            txtPhysicalTitle.setVisibility(View.INVISIBLE);
        }

        if(this.getSupportActionBar() != null){
            ((AppCompatActivity) this ).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        SearchView searchView = findViewById(R.id.searchview);
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
                    syncData();
                    return false;

                default:
                    return false;
            }

        });

        getInventory();
        getStates();
    }

    @Override
    public boolean onSupportNavigateUp() {
        Intent intent = new Intent(Inventory.this, Center.class);
        deleteInvetory();
        startActivity(intent);
        finish();
        return super.onSupportNavigateUp();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.logo_item) {
            AlertDialog.Builder builder = new AlertDialog.Builder(Inventory.this);
            builder.setMessage("Usuario: "+Preferences.get(Inventory.this, "user")).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout(){
        Progress.showProgressDialog(Inventory.this);
        Service service = ApiRest.getInterceptedApi().create(Service.class);
        String token = Preferences.get(Inventory.this, "token");
        String idUser = Preferences.get(Inventory.this, "user_id");

        ApiRest.TOKEN = token;
        Call<UserRoot> userRootCall = service.logout(idUser);

        userRootCall.enqueue(new Callback<UserRoot>() {
            @Override
            public void onResponse(@NonNull Call<UserRoot> call, @NonNull Response<UserRoot> response) {

                try{
                    if(response.isSuccessful()) {
                        assert response.body() != null;
                        String message = response.body().getMessage();
                        Preferences.delete(Inventory.this, "user_id");
                        Preferences.delete(Inventory.this, "user");
                        Preferences.delete(Inventory.this, "password");
                        Preferences.delete(Inventory.this, "id_count_assigned");
                        Preferences.delete(Inventory.this, "token");
                        Preferences.delete(Inventory.this, "warehouse_id");
                        Preferences.delete(Inventory.this, "stateIdList");
                        Preferences.delete(Inventory.this, "stateList");
                        Preferences.delete(Inventory.this, "user_type");
                        Toast.makeText(Inventory.this, message, Toast.LENGTH_SHORT).show();
                        dbSendCount.clearAllTables();
                        dbInventory.clearAllTables();
                        dbCenter.clearAllTables();
                        dbCounted.clearAllTables();
                        Intent intent = new Intent(Inventory.this, Login.class);
                        startActivity(intent);
                        finish();
                    }
                    else{
                        Toast.makeText(Inventory.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception ex){
                    Toast.makeText(Inventory.this, "Ocurrió un error", Toast.LENGTH_SHORT).show();
                }

                Progress.dismissProgressDialog(Inventory.this);
            }

            @Override
            public void onFailure(@NonNull Call<UserRoot> call, @NonNull Throwable t) {
                Progress.dismissProgressDialog(Inventory.this);
            }
        });
    }

    private void getStates(){
        Service service = ApiRest.getInterceptedApi().create(Service.class);
        String token = Preferences.get(Inventory.this, "token");
        ApiRest.TOKEN = token;
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
        Progress.showProgressBar(Inventory.this);
        String idCount = Preferences.get(Inventory.this, "id_count_assigned");
        String idWarehouse = Preferences.get(Inventory.this, "warehouse_id");
        String idUser = Preferences.get(Inventory.this, "user_id");
        String token = Preferences.get(Inventory.this, "token");
        ApiRest.TOKEN = token;

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

                            dbInventory.inventoryDao().insertList(inventoryDataList);
                            inventoryAdapter.addList((ArrayList<InventoryData>) dbInventory.inventoryDao().getAll());
                        }
                        else{
                            Toast.makeText(Inventory.this, inventoryRoot.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                    Progress.dismissProgressBar();
                }catch (Exception ex){
                    Progress.dismissProgressBar();
                }
            }

            @Override
            public void onFailure(@NonNull Call<InventoryRoot> call, @NonNull Throwable t) {
                Progress.dismissProgressBar();
                inventoryAdapter.addList((ArrayList<InventoryData>) dbInventory.inventoryDao().getAll());
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getInventory();
    }

    private void syncData(){
        Progress.showProgressDialog(Inventory.this);
        dbSendCount.countedDao().getAll();

        Service service = ApiRest.getInterceptedApi().create(Service.class);
        HashMap<String, Object> params = new HashMap<>();
        String token = Preferences.get(Inventory.this, "token");
        ApiRest.TOKEN = token;

        if(dbSendCount.countedDao().getAll().size() != 0){
            for(int i=0;i<dbSendCount.countedDao().getAll().size(); i++){
                params.put("article_id", dbSendCount.countedDao().getAll().get(i).getArticleId());
                params.put("counted_article_id", dbSendCount.countedDao().getAll().get(i).getCountedArticleId());
                params.put("material_status_id", dbSendCount.countedDao().getAll().get(i).getMaterialId());
                params.put("wooden_platforms", dbSendCount.countedDao().getAll().get(i).getWoodenPlatform());
                params.put("plastic_platforms", dbSendCount.countedDao().getAll().get(i).getPlasticPlatform());
                params.put("boxes", dbSendCount.countedDao().getAll().get(i).getBoxes());
                params.put("units", dbSendCount.countedDao().getAll().get(i).getUnits());
                params.put("frames", dbSendCount.countedDao().getAll().get(i).getSquares());
                params.put("separator", dbSendCount.countedDao().getAll().get(i).getSeparator());
                params.put("giro_paleta", dbSendCount.countedDao().getAll().get(i).getGiroPallet());
                params.put("location", dbSendCount.countedDao().getAll().get(i).getLocation());
                params.put("expiration_date", dbSendCount.countedDao().getAll().get(i).getDate());
                params.put("type_count", dbSendCount.countedDao().getAll().get(i).getTypeCount());
                params.put("level", dbSendCount.countedDao().getAll().get(i).getLevel());

                Call<SendCountData> userCall = service.sendCount(params);

                int finalI = i+1;
                int finalI1 = i;
                userCall.enqueue(new Callback<SendCountData>() {
                    @Override
                    public void onResponse(@NonNull Call<SendCountData> call, @NonNull Response<SendCountData> response) {

                        try{
                            if(response.isSuccessful()){
                                assert response.body() != null;
                                Toast.makeText(Inventory.this, "Sincronización "+finalI+": "+ response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                if(dbSendCount.countedDao().getAll().size() >= finalI1){
                                    dbSendCount.clearAllTables();
                                    getInventory();
                                }
                            }
                            else{
                                Toast.makeText(Inventory.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }catch (Exception ex){
                            Toast.makeText(Inventory.this, "Error al sincronizar", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<SendCountData> call, @NonNull Throwable t) {
                        t.printStackTrace();
                        t.getMessage();
                    }
                });
            }
        }
        else{
            Toast.makeText(this, "Sin datos para sincronizar", Toast.LENGTH_SHORT).show();
        }

        Progress.dismissProgressDialog(Inventory.this);
    }

    public void deleteInvetory(){
        dbInventory.clearAllTables();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Inventory.this, Center.class);
        deleteInvetory();
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        deleteInvetory();
    }

    @Override
    public void onClick(int position) {
        String skuName = inventoryAdapter.getFilteredList().get(position).getSkuName();
        String sku = inventoryAdapter.getFilteredList().get(position).getSku();
        String skuFamily = inventoryAdapter.getFilteredList().get(position).getFamily();
        int id = inventoryAdapter.getFilteredList().get(position).getId();

        Intent intent = new Intent(Inventory.this, Count.class);
        intent.putExtra("sku", sku);
        intent.putExtra("skuName", skuName);
        intent.putExtra("skuFamily", skuFamily);
        intent.putExtra("skuId", id);
        startActivity(intent);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        dbInventory.inventoryDao().findBySku(query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        inventoryAdapter.getFilter().filter(newText);
        return false;
    }
}