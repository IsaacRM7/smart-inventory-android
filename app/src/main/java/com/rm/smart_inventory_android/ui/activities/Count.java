package com.rm.smart_inventory_android.ui.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rm.smart_inventory_android.R;
import com.rm.smart_inventory_android.io.ClickListener;
import com.rm.smart_inventory_android.io.Preferences;
import com.rm.smart_inventory_android.io.adapters.ApiRest;
import com.rm.smart_inventory_android.io.adapters.Service;
import com.rm.smart_inventory_android.io.db.counted.CountedDataBase;
import com.rm.smart_inventory_android.io.db.sendcount.SendCountDataBase;
import com.rm.smart_inventory_android.io.db.sendcount.SendCountEntity;
import com.rm.smart_inventory_android.io.models.count.CountData;
import com.rm.smart_inventory_android.io.models.count.RecountData;
import com.rm.smart_inventory_android.io.models.count.SendCountData;
import com.rm.smart_inventory_android.ui.adapters.CountAdapter;
import com.rm.smart_inventory_android.ui.adapters.CountedAdapter;
import com.rm.smart_inventory_android.ui.dialogs.LocationDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Count extends AppCompatActivity implements ClickListener {

    private RecyclerView recountRecyclerView;
    private TextView numbersBox;
    private String chain = "";
    private String operation="";
    private int dots = 0;
    private double equals;
    private double number1;
    private Object woodenPlatform;
    private Object plasticPlatform;
    private Object boxes;
    private Object units;
    private Object giroPallet;
    private Object separator;
    private Object squares;
    private Object level;
    private Object expirationDate;
    private ArrayList<CountData> countDataArrayList;
    private CountAdapter countAdapter;
    private Intent intent;
    private TextView finalLocation;
    private List<RecountData> recountDataList;
    private List<SendCountEntity> sendCountEntityList;
    private CountedAdapter countedAdapter;
    private CountedDataBase dbCounted;
    private SendCountDataBase dbSendCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count);
        dbSendCount = SendCountDataBase.getInstance(Count.this);

        recountRecyclerView = findViewById(R.id.recycler_view_recounted);
        recountRecyclerView.setHasFixedSize(true);
        recountRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        Preferences.save(Count.this, "counted_id", "0");
        Preferences.save(Count.this, "count_type", "1");
        TextView sku = findViewById(R.id.txt_sku_count);
        TextView skuName = findViewById(R.id.txt_sku_name_count);
        TextView skuFamily = findViewById(R.id.txt_family_count);
        TextView location;
        Button b0 = findViewById(R.id.button0);
        Button b1 = findViewById(R.id.button1);
        Button b2 = findViewById(R.id.button2);
        Button b3 = findViewById(R.id.button3);
        Button b4 = findViewById(R.id.button4);
        Button b5 = findViewById(R.id.button5);
        Button b6 = findViewById(R.id.button6);
        Button b7 = findViewById(R.id.button7);
        Button b8 = findViewById(R.id.button8);
        Button b9 = findViewById(R.id.button9);
        Button bClear = findViewById(R.id.clear_button);
        Button bDot = findViewById(R.id.dot_button);
        Button bEquals = findViewById(R.id.equals_button);
        Button bPlus = findViewById(R.id.plus_button);
        Button bMinus = findViewById(R.id.minus_button);
        Button bTimes = findViewById(R.id.times_button);
        Button bDivide = findViewById(R.id.divide_button);
        FloatingActionButton fab = findViewById(R.id.send_data_fab);

        numbersBox = findViewById(R.id.txt_numbers);

        location = findViewById(R.id.txt_location);

        intent = getIntent();

        sku.setText( intent.getStringExtra("sku"));
        skuName.setText( intent.getStringExtra("skuName"));
        skuFamily.setText( intent.getStringExtra("skuFamily"));

        //Adaptador de Lista de las unidades de medida
        LinearLayoutManager linearLayoutManagerSkuList = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerView = findViewById(R.id.recycler_count);
        countAdapter = new CountAdapter(Count.this, this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManagerSkuList);
        recyclerView.setAdapter(countAdapter);
        getAmountList();

        if(this.getSupportActionBar() != null){
            Objects.requireNonNull(((AppCompatActivity) this).getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        }

        b0.setOnClickListener(v -> {
            if(!chain.equals("")){
                if(chain.equals("0")){
                    chain="0";
                }
                else{
                    chain+="0";
                }
                numbersBox.setText(chain);
            }
        });

        b1.setOnClickListener(v -> {
            if(chain.equals("0")){
                chain="1";
            }
            else{
                chain+="1";
            }
            numbersBox.setText(chain);
        });

        b2.setOnClickListener(v -> {
            if(chain.equals("0")){
                chain="2";
            }
            else{
                chain+="2";
            }
            numbersBox.setText(chain);
        });

        b3.setOnClickListener(v -> {
            if(chain.equals("0")){
                chain="3";
            }
            else{
                chain+="3";
            }
            numbersBox.setText(chain);
        });

        b4.setOnClickListener(v -> {
            if(chain.equals("0")){
                chain="4";
            }
            else{
                chain+="4";
            }
            numbersBox.setText(chain);
        });

        b5.setOnClickListener(v -> {
            if(chain.equals("0")){
                chain="5";
            }
            else{
                chain+="5";
            }
            numbersBox.setText(chain);
        });

        b6.setOnClickListener(v -> {
            if(chain.equals("0")){
                chain="6";
            }
            else{
                chain+="6";
            }
            numbersBox.setText(chain);
        });

        b7.setOnClickListener(v -> {
            if(chain.equals("0")){
                chain="7";
            }
            else{
                chain+="7";
            }
            numbersBox.setText(chain);
        });

        b8.setOnClickListener(v -> {
            if(chain.equals("0")){
                chain="8";
            }
            else{
                chain+="8";
            }
            numbersBox.setText(chain);
        });

        b9.setOnClickListener(v -> {
            if(chain.equals("0")){
                chain="9";
            }
            else{
                chain+="9";
            }
            numbersBox.setText(chain);
        });

        bClear.setOnClickListener(v -> {
            numbersBox.setText("");
            numbersBox.setText("0");
            chain = "";
            dots = 0;
        });

        bDot.setOnClickListener(v -> {

            if(dots==0) {
                if (chain.equals("")) {
                    chain = "0.";
                    dots++;
                } else {
                    chain += ".";
                    dots++;
                }
                numbersBox.setText(chain);
            }
        });

        bPlus.setOnClickListener(v -> {
            if (!chain.equals("")) {
                number1 = Double.parseDouble(chain);
                numbersBox.setText(chain + "+");
                chain = "";
                operation = "plus";
                dots = 0;
            }
        });

        bMinus.setOnClickListener(v -> {
            if (!chain.equals("")) {
                number1 = Double.parseDouble(chain);
                numbersBox.setText(chain + "-");
                chain = "";
                operation = "minus";
                dots = 0;
            }
        });

        bTimes.setOnClickListener(v -> {
            if (!chain.equals("")) {
                number1 = Double.parseDouble(chain);
                numbersBox.setText(chain + "×");
                chain = "";
                operation = "times";
                dots = 0;
            }
        });

        bDivide.setOnClickListener(v -> {
            if (!chain.equals("")) {
                number1 = Double.parseDouble(chain);
                numbersBox.setText(chain + "÷");
                chain = "";
                operation = "divide";
                dots = 0;
            }
        });

        bEquals.setOnClickListener(v -> {
            double number2;

            switch (operation) {
                case "":
                    numbersBox.setText("0");
                    break;
                case "plus":
                    if(!chain.equals("")) {
                        number2 = Double.parseDouble(chain);
                        if (number2 != 0) {
                            equals = number1 + number2;
                            numbersBox.setText(String.valueOf((equals)));
                            chain = String.valueOf(equals);
                            operation = "";
                            dots = 1;
                        }
                    }
                    break;
                case "minus":
                    if(!chain.equals("")) {
                        number2 = Double.parseDouble(chain);
                        if (number2 != 0) {
                            equals = number1 - number2;
                            numbersBox.setText(String.valueOf(equals));
                            chain = String.valueOf(equals);
                            operation = "";
                            dots = 1;
                        }
                    }
                    break;
                case "times":
                    if(!chain.equals("")) {
                        number2 = Double.parseDouble(chain);
                        if (number2 != 0) {
                            equals = number1 * number2;
                            numbersBox.setText(String.valueOf(equals));
                            chain = String.valueOf(equals);
                            operation = "";
                            dots = 1;
                        }
                    }
                    break;
                case "divide":
                    if(!chain.equals("")) {
                        number2 = Double.parseDouble(chain);
                        if (number2 != 0) {
                            equals = number1 / number2;
                            numbersBox.setText(String.valueOf(equals));
                            chain = String.valueOf(equals);
                            operation = "";
                            dots = 1;
                        }
                        break;
                    }
                default: break;
            }
        });

        finalLocation = location;
        location.setOnClickListener(view -> LocationDialog.openQuestion(getResources().getString(R.string.app_name), "Ingrese su ubicación", Count.this, (i, response) -> finalLocation.setText(response)));

        fab.setOnClickListener(view -> {
            sendCountEntityList = new ArrayList<>();
            final String chain = Preferences.get(Count.this, "stateList");
            final String[] states = chain.substring(1, chain.length() - 1).split(", ");
            final String chainId = Preferences.get(Count.this, "stateIdList");
            final String[] statesId = chainId.substring(1, chainId.length() - 1).split(", ");

            AlertDialog.Builder builder = new AlertDialog.Builder(Count.this);
            builder.setTitle(Html.fromHtml("<font color='#2BA4E9'>¿Qué se contó?</font>"));
            builder.setItems(states, (dialogInterface, i) -> {
                Service service = ApiRest.getInterceptedApi().create(Service.class);
                HashMap<String, Object> params = new HashMap<>();
                String token = Preferences.get(Count.this, "token");
                ApiRest.TOKEN = token;

                String countedArticleId = Preferences.get(Count.this, "counted_id");
                String countType = Preferences.get(Count.this, "count_type");
                int articleId = intent.getIntExtra("skuId", 0);

                params.put("article_id", articleId);
                params.put("counted_article_id", countedArticleId);
                params.put("material_status_id", statesId[i]);
                params.put("wooden_platforms", woodenPlatform);
                params.put("plastic_platforms", plasticPlatform);
                params.put("boxes", boxes);
                params.put("units", units);
                params.put("frames", squares);
                params.put("separator", separator);
                params.put("giro_paleta", giroPallet);
                params.put("location", finalLocation.getText().toString());
                params.put("expiration_date", expirationDate);
                params.put("type_count", countType);
                params.put("level", level);
                sendCountEntityList.add(new SendCountEntity(countedArticleId, articleId, finalLocation.getText().toString(), countType, statesId[i], expirationDate.toString(), woodenPlatform.toString(), plasticPlatform.toString(), boxes.toString(), units.toString(), giroPallet.toString(), separator.toString(), squares.toString(), level.toString()));

                Call<SendCountData> userCall = service.sendCount(params);

                userCall.enqueue(new Callback<SendCountData>() {
                    @Override
                    public void onResponse(@NonNull Call<SendCountData> call, @NonNull Response<SendCountData> response) {
                        dialogInterface.dismiss();
                        if(response.isSuccessful()){
                            Toast.makeText(Count.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<SendCountData> call, @NonNull Throwable t) {
                        dialogInterface.dismiss();
                        dbSendCount.countedDao().insertList(sendCountEntityList);
                        Toast.makeText(Count.this, "Conteo guardado para sincronización", Toast.LENGTH_SHORT).show();
                        t.printStackTrace();
                        t.getMessage();
                    }
                });

                Preferences.delete(Count.this, "counted_id");
                finish();

            }).show();

        });

        getCountedList();

    }

    @Override
    public boolean onSupportNavigateUp() {
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
            androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(Count.this);
            builder.setMessage("Usuario: "+Preferences.get(Count.this, "user")).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void getAmountList(){
        countDataArrayList = new ArrayList<>();
        countDataArrayList.add(new CountData("Fecha", new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date())));
        countDataArrayList.add(new CountData("Tarimas Madera", 0));
        countDataArrayList.add(new CountData("Tarimas Plasticas", 0));
        countDataArrayList.add(new CountData("Cajas", 0));
        countDataArrayList.add(new CountData("Unidades", 0));
        countDataArrayList.add(new CountData("Giro Palet", 0));
        countDataArrayList.add(new CountData("Separador", 0));
        countDataArrayList.add(new CountData("Marcos", 0));
        countDataArrayList.add(new CountData("Nivel", 0));

        expirationDate = countDataArrayList.get(0).getAmount();
        woodenPlatform = countDataArrayList.get(1).getAmount();
        plasticPlatform = countDataArrayList.get(2).getAmount();
        boxes = countDataArrayList.get(3).getAmount();
        units = countDataArrayList.get(4).getAmount();
        giroPallet = countDataArrayList.get(5).getAmount();
        separator = countDataArrayList.get(6).getAmount();
        squares = countDataArrayList.get(7).getAmount();
        level = countDataArrayList.get(8).getAmount();

        countAdapter.addList(countDataArrayList);
    }

    private void getCountedList(){
        recountDataList = new ArrayList<>();
        dbCounted = CountedDataBase.getInstance(Count.this);

        Service service = ApiRest.getInterceptedApi().create(Service.class);
        String token = Preferences.get(Count.this, "token");
        int id = intent.getIntExtra("skuId", 0);
        ApiRest.TOKEN = token;

        Call<List<RecountData>> recountDataCall = service.getCountedData(id);
        recountDataCall.enqueue(new Callback<List<RecountData>>() {
            @Override
            public void onResponse(@NonNull Call<List<RecountData>> call, @NonNull Response<List<RecountData>> response) {

                try{
                    if (response.isSuccessful()) {
                        String boxes;
                        String units;
                        String plasticPlatforms;
                        String woodenPlatforms;
                        String date;
                        String user;
                        String statusName;
                        String conversionToBoxes;
                        String conversionToUnits;
                        String locationResponse;
                        String levelResponse;
                        int status;
                        int id;
                        int articleId;

                        if(response.body() != null){
                            for(int i=0;i<response.body().size();i++){
                                boxes = response.body().get(i).getBoxes();
                                units = response.body().get(i).getUnits();
                                plasticPlatforms = response.body().get(i).getPlasticPlatforms();
                                woodenPlatforms = response.body().get(i).getWoodenPlatforms();
                                date = response.body().get(i).getDate();
                                user = response.body().get(i).getUser();
                                status = response.body().get(i).getStatus();
                                id = response.body().get(i).getId();
                                statusName = response.body().get(i).getStatusName();
                                conversionToBoxes = response.body().get(i).getConversionToBoxes();
                                conversionToUnits = response.body().get(i).getConversionToUnits();
                                locationResponse = response.body().get(i).getLocation();
                                levelResponse = response.body().get(i).getLevel();
                                articleId = response.body().get(i).getArticleId();

                                recountDataList.add(new RecountData(id, articleId, user, boxes, units, woodenPlatforms, plasticPlatforms, levelResponse, status, statusName, conversionToBoxes, conversionToUnits, locationResponse, date));
                            }

                            dbCounted.countedDao().insertList(recountDataList);

                            countedAdapter = new CountedAdapter(Count.this, dbCounted.countedDao().findCountedList(Integer.parseInt(String.valueOf(intent.getIntExtra("skuId", 0)))));
                            recountRecyclerView.setAdapter(countedAdapter);
                        }
                    }
                } catch (Exception ex){
                    Toast.makeText(Count.this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<RecountData>> call, @NonNull Throwable t) {
                t.getLocalizedMessage();
                try{
                    countedAdapter = new CountedAdapter(Count.this, dbCounted.countedDao().findCountedList(Integer.parseInt(String.valueOf(intent.getIntExtra("skuId", 0)))));
                    recountRecyclerView.setAdapter(countedAdapter);
                }catch (Exception ex){
                    Toast.makeText(Count.this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Count.this, Inventory.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(int position) {
        if (position == 0) {
            Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH);
            int day = cal.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog.OnDateSetListener mDateSetListener = (datePicker, year1, month1, day1) -> {
                month1 = month1 + 1;

                String date = year1 + "-" + month1 + "-" + day1;
                expirationDate = date;
                countDataArrayList.get(0).setAmount(date);
                countAdapter.notifyItemChanged(0);
            };

            DatePickerDialog dialog = new DatePickerDialog(
                    Count.this,
                    android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                    mDateSetListener,
                    year, month, day);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();

        } else {
            if (!chain.equals("")) {
                String amountCalculator = chain;
                double number = Double.parseDouble(amountCalculator);
                countDataArrayList.get(position).setAmount(number);
                woodenPlatform = countDataArrayList.get(1).getAmount();
                plasticPlatform = countDataArrayList.get(2).getAmount();
                boxes = countDataArrayList.get(3).getAmount();
                units = countDataArrayList.get(4).getAmount();
                giroPallet = countDataArrayList.get(5).getAmount();
                separator = countDataArrayList.get(6).getAmount();
                squares = countDataArrayList.get(7).getAmount();
                level = countDataArrayList.get(8).getAmount();

                countAdapter.notifyItemChanged(position);
                numbersBox.setText("");
                numbersBox.setText("0");
                chain = "";
                dots = 0;
            } else if (numbersBox.getText().toString().equals("0")) {
                String amountCalculator = numbersBox.getText().toString();
                countDataArrayList.get(position).setAmount(Double.parseDouble(amountCalculator));
                countAdapter.notifyItemChanged(position);
                numbersBox.setText("");
                numbersBox.setText("0");
                chain = "";
                dots = 0;
            }
        }
    }
}