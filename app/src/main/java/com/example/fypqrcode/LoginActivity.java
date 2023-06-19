package com.example.fypqrcode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

//        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.BackendURL)
//                .addConverterFactory(GsonConverterFactory.create()).build();
//        UserRequests userRequests  = retrofit.create(UserRequests.class);

    // login Button
        Button loginB = findViewById(R.id.login);
        loginB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(i);
                finish();            }
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

}
