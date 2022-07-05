package com.rm.smart_inventory_android.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;
import com.rm.smart_inventory_android.R;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private TextInputLayout inputUser;
    private TextInputLayout inputPassword;
    private EditText txtUser;
    private EditText txtPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inputUser = findViewById(R.id.input_user);
        inputPassword = findViewById(R.id.input_password);
        txtUser = findViewById(R.id.txt_user);
        txtPassword = findViewById(R.id.txt_password);
        btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(this);
    }

    private void logIn(){
        String user = txtUser.getText().toString();
        String password = txtPassword.getText().toString();

        if(!validateForm(user, password)){
            return;
        }
    }

    private boolean validateForm(String user, String password) {
        if (user!= null && user.isEmpty()){
            inputUser.setError("Requerido");
            return false;
        }
        else {
            inputUser.setError(null);
        }

        if (password!= null && password.isEmpty()){
            inputPassword.setError("Requerido");
            return false;
        }
        else {
            inputPassword.setError(null);
        }

        return true;
    }

    @Override
    public void onClick(View view) {
        logIn();
    }


}