package com.example.fypqrcode;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
//    //scan qr code button
//        Button scanQrCodeB = findViewById(R.id.scanQRCodeBtn);
//        scanQrCodeB.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(MainActivity.this, UsersActivity.class);
//                startActivity(i);
//                finish();            }
//        });


        //users button
        Button usersB = findViewById(R.id.usersBtn);
        usersB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, UsersActivity.class);
                startActivity(i);
                finish();            }
        });

        //rooms button
        Button roomsB = findViewById(R.id.roomsBtn);
        roomsB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, RoomsActivity.class);
                startActivity(i);
                finish();            }
        });

        //room type button
        Button roomTypeB = findViewById(R.id.roomTypeBtn);
        roomTypeB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, RoomTypeActivity.class);
                startActivity(i);
                finish();            }
        });

        //department button
        Button departmentB = findViewById(R.id.departmentsBtn);
        departmentB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, DepartmentActivity.class);
                startActivity(i);
                finish();            }
        });

        //logout
        Button logoutB = findViewById(R.id.logoutBtn);
        logoutB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);
                finish();            }
        });
    }
}