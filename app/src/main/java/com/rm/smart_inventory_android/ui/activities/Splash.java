package com.rm.smart_inventory_android.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.rm.smart_inventory_android.R;
import com.rm.smart_inventory_android.io.Preferences;

import java.util.Objects;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        Objects.requireNonNull(getSupportActionBar()).hide();

        new Handler().postDelayed(() -> {
            if(!Objects.equals(Preferences.get(Splash.this, "token"), "")){
                startActivity(new Intent(Splash.this, Center.class));
            }
            else{
                startActivity(new Intent(Splash.this, Login.class));
            }
            finish();
        },3000);
    }

}