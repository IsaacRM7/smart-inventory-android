package com.rm.smart_inventory_android.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.rm.smart_inventory_android.R;

public class Count extends AppCompatActivity{

    private TextView numbersBox;
    private String chain = "";
    private String operation="";
    private int dots = 0;
    private double equals;
    private double number1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count);

        TextView skuCounted;
        TextView flavor;
        TextView presentation;
        TextView location;
        Button b0;
        Button b1;
        Button b2;
        Button b3;
        Button b4;
        Button b5;
        Button b6;
        Button b7;
        Button b8;
        Button b9;
        Button bClear;
        Button bDot;
        Button bEquals;
        Button bPlus;
        Button bMinus;
        Button bTimes;
        Button bDivide;

        numbersBox = findViewById(R.id.txt_numbers);

        b0 = findViewById(R.id.button0);
        b1 = findViewById(R.id.button1);
        b2 = findViewById(R.id.button2);
        b3 = findViewById(R.id.button3);
        b4 = findViewById(R.id.button4);
        b5 = findViewById(R.id.button5);
        b6 = findViewById(R.id.button6);
        b7 = findViewById(R.id.button7);
        b8 = findViewById(R.id.button8);
        b9 = findViewById(R.id.button9);
        bClear = findViewById(R.id.clear_button);
        bDot = findViewById(R.id.dot_button);
        bEquals = findViewById(R.id.equals_button);
        bPlus = findViewById(R.id.plus_button);
        bMinus = findViewById(R.id.minus_button);
        bTimes = findViewById(R.id.times_button);
        bDivide = findViewById(R.id.divide_button);
        location = findViewById(R.id.txt_location);

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
            double number2 = 0;

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
                            //numbersBox.setText(String.format("%.2f", equals));
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
                            //numbersBox.setText(String.format("%.2f", equals));
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
                            //numbersBox.setText(String.format("%.2f", equals));
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
                            //numbersBox.setText(String.format("%.2f", equals));
                            chain = String.valueOf(equals);
                            operation = "";
                            dots = 1;
                        }
                        break;
                    }
                default: break;
            }
        });
    }
}