package com.example.fypqrcode;

import static android.view.View.GONE;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fypqrcode.classes.DataItem;
import com.example.fypqrcode.http.UserRequests;
import com.example.fypqrcode.http.requests.UserRequest;
import com.example.fypqrcode.http.responses.ErrorResponse;
import com.example.fypqrcode.http.responses.SuccessResponse;
import com.example.fypqrcode.http.responses.UserResponse;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UsersActivity extends AppCompatActivity {
    private Boolean isSelected = false;

    private UserResponse user= new UserResponse();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.users_layout);

    //logout
    Button logoutB = findViewById(R.id.logoutBtn);
        logoutB.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(UsersActivity.this, LoginActivity.class);
            startActivity(i);
            finish();            }
    });

        //Back
        Button backB = findViewById(R.id.backBtn);
        backB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UsersActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        // add
        Button addB = findViewById(R.id.addBtn);
        addB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crud(v, "ADD");
            }
        });

        //edit
        Button editB = findViewById(R.id.editBtn);
        editB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isSelected == false) {
                    Toast.makeText(getApplicationContext(), "No row selected.", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    crud(v, "EDIT");
                }
            }
        });

        // delete
        Button deleteB = findViewById(R.id.deleteBtn);
        deleteB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isSelected == false) {
                    Toast.makeText(getApplicationContext(), "No row selected.", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    crud(v, "DELETE");
                }
            }
        });

        populateTable();
    }


    private void populateTable() {
        TableLayout tableLayout = findViewById(R.id.userTable);
        tableLayout.removeAllViews();
    TableLayout headerT = findViewById(R.id.usersHeaderTable);
    TableRow headerR = (TableRow) headerT.getChildAt(0);

    Retrofit retrofit = new Retrofit.Builder().baseUrl("http://10.0.2.2:80/php/controllers/")
            .addConverterFactory(GsonConverterFactory.create()).build();
    UserRequests userRequests = retrofit.create(UserRequests.class);

    userRequests.getUsers().enqueue(new Callback<UserResponse[]>() {
        @Override
        public void onResponse(Call<UserResponse[]> call, Response<UserResponse[]> response) {
            if (response.isSuccessful()) {
                UserResponse[] result = response.body();

                // Iterate through the data and create rows and cells
                for (int i = 0; i < result.length; i++) {
                    TableRow tableRow = new TableRow(UsersActivity.this);
                    tableRow.setLayoutParams(new TableRow.LayoutParams(
                            TableRow.LayoutParams.MATCH_PARENT,
                            TableRow.LayoutParams.WRAP_CONTENT));

                    TextView textView = new TextView(UsersActivity.this);
                    textView.setText(result[i].getId().toString());
                    tableRow.addView(textView);

                    TextView textView2 = new TextView(UsersActivity.this);
                    textView2.setText(result[i].getFirstName());
                    tableRow.addView(textView2);

                    TextView textView3 = new TextView(UsersActivity.this);
                    textView3.setText(result[i].getLastName());
                    tableRow.addView(textView3);

                    TextView textView4 = new TextView(UsersActivity.this);
                    textView4.setText(result[i].getPhoneNumber());
                    tableRow.addView(textView4);

                    TextView textView5 = new TextView(UsersActivity.this);
                    textView5.setText(result[i].getType());
                    tableRow.addView(textView5);

                    TextView textView6 = new TextView(UsersActivity.this);
                    textView6.setText(result[i].getRole());
                    tableRow.addView(textView6);

                    TextView textView7 = new TextView(UsersActivity.this);
                    textView7.setText(result[i].getEmail());
                    tableRow.addView(textView7);


                    tableRow.setClickable(true);
                    tableRow.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // Set background color of selected row
                            for (int j = 0; j < tableLayout.getChildCount(); j++) {
                                View row = tableLayout.getChildAt(j);
                                if (row instanceof TableRow) {
                                    row.setBackgroundColor(Color.TRANSPARENT);
                                }
                            }
                            tableRow.setBackgroundColor(Color.YELLOW);

                            // Retrieve the data from the clicked row
                            View ID = tableRow.getChildAt(0);
                            View FirstName = tableRow.getChildAt(1);
                            View LastName = tableRow.getChildAt(2);
                            View PhoneNumber = tableRow.getChildAt(3);
                            View Type = tableRow.getChildAt(4);
                            View Role = tableRow.getChildAt(5);
                            View Email = tableRow.getChildAt(6);

                            user.setId(Integer.valueOf((String) ((TextView) ID).getText()));
                            user.setFirstName(((TextView) FirstName).getText().toString());
                            user.setType(((TextView) Type).getText().toString());
                            user.setLastName(((TextView) LastName).getText().toString());
                            user.setPhoneNumber(((TextView) PhoneNumber).getText().toString());
                            user.setRole(((TextView) Role).getText().toString());
                            user.setEmail(((TextView) Email).getText().toString());
                            isSelected = true;
                        }
                    });


                    tableLayout.addView(tableRow);
                }
            }
            tableLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    tableLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                    for (int j = 0; j < headerR.getChildCount(); j++) {
                        View headerView = headerR.getChildAt(j);
                        int headerWidth = headerView.getWidth();

                        for (int i = 0; i < tableLayout.getChildCount(); i++) {
                            TableRow tableRow = (TableRow) tableLayout.getChildAt(i);
                            TextView textView = (TextView) tableRow.getChildAt(j);
                            textView.setLayoutParams(new TableRow.LayoutParams(
                                    headerWidth,
                                    TableRow.LayoutParams.WRAP_CONTENT));
                        }
                    }
                }
            });
        }


        @Override
        public void onFailure(Call<UserResponse[]> call, Throwable t) {

        }
    });

}

    private void crud(View v, String type) {
// Inflate the popup_layout.xml
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.user_popup_layout, null);
        TextView idLabel = popupView.findViewById(R.id.userIDLabel);
        TextView userID = popupView.findViewById(R.id.userID);
        EditText firstName = popupView.findViewById(R.id.firstName);
        Spinner userType = popupView.findViewById(R.id.typeSpinner);
        EditText lastName = popupView.findViewById(R.id.lastName);
        Spinner role = popupView.findViewById(R.id.roleSpinner);
        EditText email = popupView.findViewById(R.id.email);
        EditText phoneNumber = popupView.findViewById(R.id.phoneNumber);
        TextView passwordLabel = popupView.findViewById(R.id.passwordLabel);
        TextView retypePasswordLabel = popupView.findViewById(R.id.retypePasswordLabel);
        EditText password = popupView.findViewById(R.id.password);
        EditText retypePassword = popupView.findViewById(R.id.retypePassword);
        List<DataItem> userTypeS = new ArrayList<>();
        List<DataItem> userRoleS = new ArrayList<>();
        ArrayAdapter<DataItem> userTypeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, userTypeS);
        ArrayAdapter<DataItem> userRoleAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, userRoleS);
        DataItem selectedUserType = new DataItem();
        DataItem selectedUserRole = new DataItem();



        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }


            public void afterTextChanged(Editable s) {


                    password.setTextColor(getResources().getColor(R.color.black));
                    retypePassword.setTextColor(getResources().getColor(R.color.black));

            }

    });
        retypePassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }


            public void afterTextChanged(Editable s) {


                    password.setTextColor(getResources().getColor(R.color.black));
                    retypePassword.setTextColor(getResources().getColor(R.color.black));

            }

        });

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://10.0.2.2:80/php/controllers/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        UserRequests userRequests = retrofit.create(UserRequests.class);

        userRequests.getAllAccTypes().enqueue(new Callback<DataItem[]>() {
            @Override
            public void onResponse(Call<DataItem[]> call, Response<DataItem[]> response) {
                DataItem[] result = response.body();
                for (int i = 0; i < result.length; i++) {
                    userTypeS.add(new DataItem(result[i].getId(), result[i].getValue()));
                }
                userTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                userType.setAdapter(userTypeAdapter);
                if (type.equals("EDIT") || type.equals("DELETE")) {
                    for (int i = 0; i < userTypeAdapter.getCount(); i++) {
                        DataItem item = userTypeAdapter.getItem(i);
                        if (item.getValue().equals(user.getType())) {
                            userType.setSelection(i);
                            break;
                        }
                    }
                }
                userType.setOnItemSelectedListener  (new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        selectedUserType.setId(userTypeS.get(position).getId());
                        selectedUserType.setValue(userTypeS.get(position).getValue());
                        userTypeAdapter.notifyDataSetChanged();            }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }

            @Override
            public void onFailure(Call<DataItem[]> call, Throwable t) {

            }
        });

        userRequests.getAllRoleTypes().enqueue(new Callback<DataItem[]>() {
            @Override
            public void onResponse(Call<DataItem[]> call, Response<DataItem[]> response) {
                DataItem[] result = response.body();
                for (int i = 0; i < result.length; i++) {
                    userRoleS.add(new DataItem(result[i].getId(), result[i].getValue()));
                }
                userRoleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                role.setAdapter(userRoleAdapter);
                if (type == "EDIT" || type == "DELETE") {
                    for (int i = 0; i < userRoleAdapter.getCount(); i++) {
                        DataItem item = userRoleAdapter.getItem(i);
                        if (item.getValue().equals(user.getRole())) {
                            role.setSelection(i);
                            break;
                        }
                    }
                }
                role.setOnItemSelectedListener  (new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        selectedUserRole.setId(userRoleS.get(position).getId());
                        selectedUserRole.setValue(userRoleS.get(position).getValue());
                        userRoleAdapter.notifyDataSetChanged();            }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }

            @Override
            public void onFailure(Call<DataItem[]> call, Throwable t) {

            }
        });

        Button actionButton = popupView.findViewById(R.id.actionBtn);
        actionButton.setText(type);
        if (type == "ADD") {
            idLabel.setVisibility(GONE);
            userID.setVisibility(GONE);
        } else if (type == "EDIT" || type == "DELETE") {


            userID.setText(user.getId().toString());
            firstName.setText(user.getFirstName());
            lastName.setText(user.getLastName());
            email.setText(user.getEmail());
            phoneNumber.setText(user.getPhoneNumber());
            passwordLabel.setVisibility(GONE);
            password.setVisibility(GONE);
            retypePasswordLabel.setVisibility(GONE);
            retypePassword.setVisibility(GONE);

            if (type == "DELETE") {
                firstName.setEnabled(false);
                firstName.setFocusable(false);

                userType.setEnabled(false);
                userType.setFocusable(false);

                role.setEnabled(false);
                role.setFocusable(false);

                email.setEnabled(false);
                email.setFocusable(false);

                lastName.setEnabled(false);
                lastName.setFocusable(false);

                phoneNumber.setEnabled(false);
                phoneNumber.setFocusable(false);
            }
        }


        // Create the PopupWindow
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // Allows the popup to receive focus
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        // Show the popup at the center of the button
        popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);

        // Dismiss the popup when the button inside the popup is clicked
        Button closeButton = popupView.findViewById(R.id.backBtn);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                popupWindow.dismiss();

            }
        });


        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    if (type=="ADD"){
                    if(password.getText().toString().equals(retypePassword.getText().toString()))
                    {
                        add(new UserRequest(0,email.getText().toString(),selectedUserType.getId(),firstName.getText().toString(),lastName.getText().toString(),selectedUserRole.getId(),phoneNumber.getText().toString(),password.getText().toString()));
                        popupWindow.dismiss();
                    }
                    else
                    {
                        password.setTextColor(Color.RED);
                        retypePassword.setTextColor(Color.RED);
                        Toast.makeText(getApplicationContext(), "Passwords doesn't match", Toast.LENGTH_LONG).show();

                    }
                    }
                    if(type=="EDIT") {
                        edit(new UserRequest(Integer.parseInt(userID.getText().toString()), email.getText().toString(), selectedUserType.getId(), firstName.getText().toString(), lastName.getText().toString(), selectedUserRole.getId(), phoneNumber.getText().toString(), null));
                        popupWindow.dismiss();
                    }
                    if(type=="DELETE"){
                        delete(new UserRequest(Integer.parseInt((String) userID.getText()),null,null,null,null,null,null,null));
                        popupWindow.dismiss();
                    }
            }
        });
    }
    private void add(UserRequest userRequest) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://10.0.2.2:80/php/controllers/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        UserRequests userRequests = retrofit.create(UserRequests.class);

        userRequests.insertNewUser(userRequest).enqueue(
                new Callback<SuccessResponse>() {
                    @Override
                    public void onResponse(Call<SuccessResponse> call, Response<SuccessResponse> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "User Added", Toast.LENGTH_LONG).show();
                            populateTable();
                        }
                        else
                        {
                            Gson gson=new Gson();
                            ErrorResponse errorResponse = null;
                            try {
                                errorResponse = gson.fromJson(response.errorBody().string(), ErrorResponse.class);
                                String errorMessage = errorResponse.getError();
                                Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_LONG).show();
                            } catch (IOException e) {
                                Toast.makeText(getApplicationContext(), "Something went wrong.", Toast.LENGTH_LONG).show();
                            }

                        }

                    }

                    @Override
                    public void onFailure(Call<SuccessResponse> call, Throwable t) {

                    }
                });
    }

    private void edit(UserRequest userRequest) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://10.0.2.2:80/php/controllers/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        UserRequests userRequests = retrofit.create(UserRequests.class);

        userRequests.updateUser(userRequest).enqueue(
                new Callback<SuccessResponse>() {
                    @Override
                    public void onResponse(Call<SuccessResponse> call, Response<SuccessResponse> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "User Updated", Toast.LENGTH_LONG).show();
                            populateTable();
                        }
                        else
                        {
                            Gson gson=new Gson();
                            ErrorResponse errorResponse = null;
                            try {
                                errorResponse = gson.fromJson(response.errorBody().string(), ErrorResponse.class);
                                String errorMessage = errorResponse.getError();
                                Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_LONG).show();
                            } catch (IOException e) {
                                Toast.makeText(getApplicationContext(), "Something went wrong.", Toast.LENGTH_LONG).show();
                            }

                        }

                    }

                    @Override
                    public void onFailure(Call<SuccessResponse> call, Throwable t) {

                    }
                });
    }

    private void delete(UserRequest userRequest) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://10.0.2.2:80/php/controllers/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        UserRequests userRequests = retrofit.create(UserRequests.class);

        userRequests.deleteUser(userRequest).enqueue(
                new Callback<SuccessResponse>() {
                    @Override
                    public void onResponse(Call<SuccessResponse> call, Response<SuccessResponse> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "User Deleted", Toast.LENGTH_LONG).show();
                            populateTable();
                        }
                        else
                        {
                            Gson gson=new Gson();
                            ErrorResponse errorResponse = null;
                            try {
                                errorResponse = gson.fromJson(response.errorBody().string(), ErrorResponse.class);
                                String errorMessage = errorResponse.getError();
                                Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_LONG).show();
                            } catch (IOException e) {
                                Toast.makeText(getApplicationContext(), "Something went wrong.", Toast.LENGTH_LONG).show();
                            }

                        }

                    }

                    @Override
                    public void onFailure(Call<SuccessResponse> call, Throwable t) {

                    }
                });
    }
}

