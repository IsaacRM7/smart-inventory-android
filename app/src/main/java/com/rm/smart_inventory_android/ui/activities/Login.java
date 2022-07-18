package com.rm.smart_inventory_android.ui.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.rm.smart_inventory_android.R;
import com.rm.smart_inventory_android.io.Preferences;
import com.rm.smart_inventory_android.io.adapters.ApiRest;
import com.rm.smart_inventory_android.io.adapters.Service;
import com.rm.smart_inventory_android.io.models.login.UserRoot;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

        if(!Preferences.get(Login.this, "token").equals("")){
            Intent intent = new Intent(Login.this, Center.class);
            startActivity(intent);
            finish();
        }

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

        sendLoginData(user, password);
    }

    private void sendLoginData(String user, String password){
        Service service = ApiRest.getApi().create(Service.class);

        HashMap<String, String> params = new HashMap<>();
        params.put("user", user);
        params.put("password", password);

        Call<UserRoot> userCall = service.login(params);

        userCall.enqueue(new Callback<UserRoot>() {
            @Override
            public void onResponse(@NonNull Call<UserRoot> call, @NonNull Response<UserRoot> response) {
                if(response.isSuccessful()){
                    assert response.body() != null;
                    boolean result = response.body().getResult();
                    String message = response.body().getMessage();

                    if(result){
                        String token = response.body().getToken();
                        String userName = response.body().getData().get(0).getUser();
                        int id = response.body().getData().get(0).getId();
                        int idCount = response.body().getData().get(0).getIdCountAssigned();

                        Preferences.save(Login.this, "user", userName);
                        Preferences.save(Login.this, "password", password);
                        Preferences.save(Login.this, "token", token);
                        Preferences.save(Login.this, "user_id", String.valueOf(id));
                        Preferences.save(Login.this, "id_count_assigned", String.valueOf(idCount));

                        Toast.makeText(Login.this, message, Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(Login.this, Center.class);
                        startActivity(intent);
                        finish();
                    }
                    else{
                        Toast.makeText(Login.this, message, Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<UserRoot> call, @NonNull Throwable t) {
                t.getMessage();
            }
        });
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