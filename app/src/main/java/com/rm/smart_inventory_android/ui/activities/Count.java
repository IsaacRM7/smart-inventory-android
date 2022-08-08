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

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rm.smart_inventory_android.R;
import com.rm.smart_inventory_android.io.ClickListener;
import com.rm.smart_inventory_android.io.Preferences;
import com.rm.smart_inventory_android.io.models.count.CountData;
import com.rm.smart_inventory_android.ui.adapters.CountAdapter;
import com.rm.smart_inventory_android.ui.dialogs.LocationDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Count extends AppCompatActivity implements ClickListener {

    private TextView numbersBox;
    private String chain = "";
    private String operation="";
    private int dots = 0;
    private double equals;
    private double number1;
    private ArrayList<CountData> countDataArrayList;
    private CountAdapter countAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count);

        TextView finalLocation;
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

        Intent intent = getIntent();

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
                            numbersBox.setText(String.valueOf(Math.round(equals*100.0)/100.0));
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
                            numbersBox.setText(String.valueOf(Math.round(equals*100.0)/100.0));
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
                            numbersBox.setText(String.valueOf(Math.round(equals*100.0)/100.0));
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
                            numbersBox.setText(String.valueOf(Math.round(equals*100.0)/100.0));
                            chain = String.valueOf(equals);
                            operation = "";
                            dots = 1;
                        }
                        break;
                    }
                default: break;
            }
        });

        fab.setOnClickListener(view -> {
            final String chain = Preferences.get(Count.this, "stateList");
            final String[] states = chain.substring(1, chain.length() - 1).split(", ");
            AlertDialog.Builder builder = new AlertDialog.Builder(Count.this);
            builder.setTitle(Html.fromHtml("<font color='#2BA4E9'>¿Qué se contó?</font>"));
            builder.setItems(states, (dialogInterface, i) -> {
                 dialogInterface.dismiss();
            }).show();

        });

        finalLocation = location;
        location.setOnClickListener(view -> LocationDialog.openQuestion(getResources().getString(R.string.app_name), "Ingrese su ubicación", Count.this, (i, response) -> finalLocation.setText(response)));
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
        countDataArrayList.add(new CountData("Fecha", new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault()).format(new Date()), ""));
        countDataArrayList.add(new CountData("Tarimas", 0, 0));
        countDataArrayList.add(new CountData("Cajas", 0, 0));
        countDataArrayList.add(new CountData("Unidades", 0, 0));
        countDataArrayList.add(new CountData("Giro Palet", 0, 0));
        countDataArrayList.add(new CountData("Separador", 0, 0));
        countDataArrayList.add(new CountData("Marcos", 0, 0));
        countDataArrayList.add(new CountData("Nivel", 0, 0));

        countAdapter.addList(countDataArrayList);
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

                String date = year1 + "/" + month1 + "/" + day1;
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
                countDataArrayList.get(position).setAmount(Math.round(number * 100.0) / 100.0);
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