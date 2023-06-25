package com.example.fypqrcode;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fypqrcode.http.UserRequests;
import com.example.fypqrcode.http.responses.UserResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);


        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://10.0.2.2:80/php/controllers/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        UserRequests userRequests  = retrofit.create(UserRequests.class);

        fillUser(userRequests);


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
    public void fillUser(UserRequests userRequests) {

        TextView ID = findViewById(R.id.userID);
        TextView FirstName = findViewById(R.id.firstName);
        TextView LastName = findViewById(R.id.lastName);
        TextView Email = findViewById(R.id.email);
        TextView PhoneNumber = findViewById(R.id.phoneNumber);
        TextView Type = findViewById(R.id.type);
        TextView Role = findViewById(R.id.role);
        LinearLayout btnLayout = findViewById(R.id.btnLayout);
        LinearLayout btnLayout2 = findViewById(R.id.btnLayout2);
        Button usersBtn =findViewById(R.id.usersBtn);

        SharedPreferences sharedPreferences = getSharedPreferences("App", Context.MODE_PRIVATE);
        String email = sharedPreferences.getString("email","");

        userRequests.getCurrentUser("getCurrentUser",email).enqueue(new Callback<UserResponse>() {

            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful()) {
                    UserResponse result = response.body();

                    ID.setText(result.getId().toString());
                    LastName.setText(result.getLastName());
                    FirstName.setText(result.getFirstName());
                    PhoneNumber.setText(result.getPhoneNumber());
                    Type.setText(result.getType());
                    Role.setText(result.getRole());
                    Email.setText(result.getEmail());

                    if (Type.getText().equals("Guest")){
                    btnLayout.setVisibility(View.INVISIBLE);
                    btnLayout2.setVisibility(View.INVISIBLE);
                    usersBtn.setVisibility(View.INVISIBLE);}
                }
            System.out.println("middle");
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Something Went Wrong", Toast.LENGTH_LONG).show();
                System.out.println("failure");
            }
        });
    }
}