package com.example.fypqrcode;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fypqrcode.htpp.UserRequests;
import com.example.fypqrcode.htpp.requests.LoginRequest;
import com.example.fypqrcode.htpp.responses.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://10.0.2.2:80/php/controllers/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        UserRequests userRequests  = retrofit.create(UserRequests.class);

    // login Button
        Button loginB = findViewById(R.id.login);
        loginB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    login(userRequests);
                           }
        });
    // exit Button
        Button exitB = findViewById(R.id.exit);
        exitB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Close the current activity
                System.exit(0); // Exit the application
            }
        });
    }
    public void login(UserRequests userRequests) {
        String email = ((EditText) findViewById(R.id.email)).getText().toString();
        String password = ((EditText) findViewById(R.id.password)).getText().toString();
        LoginRequest request = new LoginRequest(email, password);

        userRequests.login(request).enqueue(new Callback<LoginResponse>() {

            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    LoginResponse result = response.body();
                    Toast.makeText(getApplicationContext(), "Logged in!", Toast.LENGTH_LONG).show();
                    SharedPreferences sharedPreferences = getSharedPreferences("App", Context.MODE_PRIVATE);
                    SharedPreferences.Editor sharedPreferencesEdit = sharedPreferences.edit();
                    sharedPreferencesEdit.putString("email", email);
                    sharedPreferencesEdit.commit();
                    nextScreen();


                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Wrong Credentials, Try Again.", Toast.LENGTH_LONG).show();
            }
        });
    }
    void nextScreen(){
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(i);
                    finish();}

}
