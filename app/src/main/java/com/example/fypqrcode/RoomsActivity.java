package com.example.fypqrcode;

import static android.view.View.GONE;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
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
import com.example.fypqrcode.http.DepartmentRequests;
import com.example.fypqrcode.http.RoomRequests;
import com.example.fypqrcode.http.RoomTypeRequests;
import com.example.fypqrcode.http.requests.RoomRequest;
import com.example.fypqrcode.http.responses.ErrorResponse;
import com.example.fypqrcode.http.responses.RoomResponse;
import com.example.fypqrcode.http.responses.SuccessResponse;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RoomsActivity extends AppCompatActivity {
    private Boolean isSelected = false;

    private RoomResponse room= new RoomResponse();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rooms_layout);

        //logout
        Button logoutB = findViewById(R.id.logoutBtn);
        logoutB.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Intent i = new Intent(RoomsActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });

        //Back
        Button backB = findViewById(R.id.backBtn);
        backB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RoomsActivity.this, MainActivity.class);
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
        TableLayout tableLayout = findViewById(R.id.roomsTable);
        tableLayout.removeAllViews();
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.0.103:80/php/controllers/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        RoomRequests roomRequests = retrofit.create(RoomRequests.class);

        roomRequests.getAllRooms().enqueue(new Callback<RoomResponse[]>() {
            @Override
            public void onResponse(Call<RoomResponse[]> call, Response<RoomResponse[]> response) {
                if (response.isSuccessful()) {
                    RoomResponse[] result = response.body();

                    // Iterate through the data and create rows and cells
                    for (int i = 0; i < result.length; i++) {
                        TableRow tableRow = new TableRow(RoomsActivity.this);
                        tableRow.setLayoutParams(new TableRow.LayoutParams(
                                TableRow.LayoutParams.MATCH_PARENT,
                                TableRow.LayoutParams.WRAP_CONTENT));
                        TableRow.LayoutParams params1 = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.08f);
                        TableRow.LayoutParams params2 = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.15f);
                        TableRow.LayoutParams params3 = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.2f);
                        TableRow.LayoutParams params4 = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.12f);
                        TableRow.LayoutParams params5 = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.22f);
                        TableRow.LayoutParams params6 = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.23f);

                        TextView textView = new TextView(RoomsActivity.this);
                        textView.setText(result[i].getId().toString());

                        textView.setLayoutParams(params1);
                        tableRow.addView(textView);
                        TextView textView1 = new TextView(RoomsActivity.this);
                        textView1.setText(result[i].getName());
                        textView1.setLayoutParams(params2);
                        tableRow.addView(textView1);

                        TextView textView3 = new TextView(RoomsActivity.this);
                        textView3.setText(result[i].getType());
                        textView3.setLayoutParams(params3);
                        tableRow.addView(textView3);

                        TextView textView4 = new TextView(RoomsActivity.this);
                        textView4.setText(result[i].getSpace().toString());
                        textView4.setLayoutParams(params4);
                        tableRow.addView(textView4);

                        TextView textView5 = new TextView(RoomsActivity.this);
                        textView5.setText(result[i].getDepartment());
                        textView5.setLayoutParams(params5);
                        tableRow.addView(textView5);

                        TextView textView6 = new TextView(RoomsActivity.this);
                        textView6.setText(result[i].getFaculty());
                        textView6.setLayoutParams(params6);
                        tableRow.addView(textView6);

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
                                View Name = tableRow.getChildAt(1);
                                View Type = tableRow.getChildAt(2);
                                View Space = tableRow.getChildAt(3);
                                View Department = tableRow.getChildAt(4);
                                View Faculty = tableRow.getChildAt(5);

                                room.setId(Integer.valueOf((String) ((TextView) ID).getText()));
                                room.setName(((TextView) Name).getText().toString());
                                room.setType(((TextView) Type).getText().toString());
                                room.setSpace(Double.valueOf(((TextView) Space).getText().toString()));
                                room.setDepartment(((TextView) Department).getText().toString());
                                room.setFaculty(((TextView) Faculty).getText().toString());
                                isSelected = true;
                            }
                        });


                        tableLayout.addView(tableRow);
                    }
                }
            }

            @Override
            public void onFailure(Call<RoomResponse[]> call, Throwable t) {

            }

        });




    }

    private void crud(View v, String type) {
// Inflate the popup_layout.xml
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.room_popup_layout, null);
        TextView idLabel = popupView.findViewById(R.id.roomIDLabel);
        TextView roomID = popupView.findViewById(R.id.roomID);
        EditText roomName = popupView.findViewById(R.id.roomName);
        Spinner roomType = popupView.findViewById(R.id.roomTypeSpinner);
        EditText space = popupView.findViewById(R.id.space);
        Spinner department = popupView.findViewById(R.id.departmentSpinner);
        Spinner faculty = popupView.findViewById(R.id.facultySpinner);
        List<DataItem> roomTypeS = new ArrayList<>();
        List<DataItem> departmentS = new ArrayList<>();
        List<DataItem> facultyS = new ArrayList<>();
         DataItem selectedRoomType = new DataItem();
         DataItem selectedDepartment = new DataItem();
        DataItem selectedFaculty = new DataItem();
        ArrayAdapter<DataItem> roomTypeAdapter = new ArrayAdapter<>(RoomsActivity.this, android.R.layout.simple_spinner_item, roomTypeS);
        ArrayAdapter<DataItem> departmentAdapter = new ArrayAdapter<>(RoomsActivity.this, android.R.layout.simple_spinner_item, departmentS);
        ArrayAdapter<DataItem> facultyAdapter = new ArrayAdapter<>(RoomsActivity.this, android.R.layout.simple_spinner_item, facultyS);

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.0.103:80/php/controllers/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        RoomTypeRequests roomTypeRequests = retrofit.create(RoomTypeRequests.class);

        roomTypeRequests.getAllRoomTypes().enqueue(new Callback<DataItem[]>() {
            @Override
            public void onResponse(Call<DataItem[]> call, Response<DataItem[]> response) {
                DataItem[] result = response.body();
                for (int i = 0; i < result.length; i++) {
                    roomTypeS.add(new DataItem(result[i].getId(), result[i].getValue()));
                }
                roomTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                roomType.setAdapter(roomTypeAdapter);
                if (type == "EDIT" || type == "DELETE") {
                    for (int i = 0; i < roomTypeAdapter.getCount(); i++) {
                        DataItem item = roomTypeAdapter.getItem(i);
                        if (item.getValue().equals(room.getType())) {
                            roomType.setSelection(i);
                            break;
                        }
                    }
                }
                roomType.setOnItemSelectedListener  (new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        selectedRoomType.setId(roomTypeS.get(position).getId());
                        selectedRoomType.setValue(roomTypeS.get(position).getValue());
                        roomTypeAdapter.notifyDataSetChanged();            }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }

            @Override
            public void onFailure(Call<DataItem[]> call, Throwable t) {

            }
        });









            DepartmentRequests departmentRequests = retrofit.create(DepartmentRequests.class);

        departmentRequests.getAllDepartments().enqueue(new Callback<DataItem[]>() {
            @Override
            public void onResponse(Call<DataItem[]> call, Response<DataItem[]> response) {
                DataItem[] result = response.body();
                for (int i = 0; i < result.length; i++) {
                    departmentS.add(new DataItem(result[i].getId(), result[i].getValue()));
                }
                departmentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                department.setAdapter(departmentAdapter);

                if (type == "EDIT" || type == "DELETE") {

                    for (int i = 0; i < departmentAdapter.getCount(); i++) {
                        DataItem item = departmentAdapter.getItem(i);
                        if (item.getValue().equals(room.getDepartment())) {
                            department.setSelection(i);
                            break;
                        }
                    }
                }
                department.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        selectedDepartment.setId(departmentS.get(position).getId());
                        selectedDepartment.setValue(departmentS.get(position).getValue());
                        departmentAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }

                });
            }

            @Override
            public void onFailure(Call<DataItem[]> call, Throwable t) {

            }
        });





        RoomRequests roomRequests = retrofit.create(RoomRequests.class);
        roomRequests.getAllFaculties().enqueue(new Callback<DataItem[]>() {
            @Override
            public void onResponse(Call<DataItem[]> call, Response<DataItem[]> response) {
                DataItem[] result = response.body();
                for (int i =0;i<result.length;i++)
                {
                    facultyS.add(new DataItem(result[i].getId(),result[i].getValue()));
                }
                facultyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                faculty.setAdapter(facultyAdapter);
                if (type == "EDIT" || type == "DELETE") {

                    for (int i = 0; i < facultyAdapter.getCount(); i++) {
                        DataItem item = facultyAdapter.getItem(i);
                        if (item.getValue().equals(room.getFaculty())) {
                            faculty.setSelection(i);
                            break;
                        }
                    }
                }
                faculty.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        selectedFaculty.setId(facultyS.get(position).getId());
                        selectedFaculty.setValue(facultyS.get(position).getValue());
                        facultyAdapter.notifyDataSetChanged();
                    }

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
            roomID.setVisibility(GONE);
        } else if (type == "EDIT" || type == "DELETE") {

            department.setEnabled(false);
            department.setFocusable(false);

            faculty.setEnabled(false);
            faculty.setFocusable(false);
                roomID.setText(room.getId().toString());
                roomName.setText(room.getName());
                space.setText(room.getSpace().toString());

                if (type == "DELETE") {
                    roomName.setEnabled(false);
                    roomName.setFocusable(false);

                    roomType.setEnabled(false);
                    roomType.setFocusable(false);

                    space.setEnabled(false);
                    space.setFocusable(false);

//                    department.setEnabled(false);
//                    department.setFocusable(false);
//
//                    faculty.setEnabled(false);
//                    faculty.setFocusable(false);
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
                        add(new RoomRequest(0,roomName.getText().toString(),selectedRoomType.getId(),Double.parseDouble(space.getText().toString()),selectedDepartment.getId(),selectedFaculty.getId()));
                    }
                    if(type=="EDIT"){
                        edit(new RoomRequest(Integer.parseInt((String) roomID.getText()),roomName.getText().toString(),selectedRoomType.getId(),Double.parseDouble(space.getText().toString()),selectedDepartment.getId(),selectedFaculty.getId()));
                    }
                    if(type=="DELETE"){
                        delete(new RoomRequest(Integer.parseInt((String) roomID.getText()),null,null,null,null,null));
                    }


                    popupWindow.dismiss();
                }
            });
        }
    private void add(RoomRequest roomRequest) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.0.103:80/php/controllers/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        RoomRequests roomRequests = retrofit.create(RoomRequests.class);

        roomRequests.insertNewRoom(roomRequest).enqueue(
                new Callback<SuccessResponse>() {
                    @Override
                    public void onResponse(Call<SuccessResponse> call, Response<SuccessResponse> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Room Added", Toast.LENGTH_LONG).show();
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

    private void edit(RoomRequest roomRequest) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.0.103:80/php/controllers/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        RoomRequests roomRequests = retrofit.create(RoomRequests.class);

        roomRequests.updateRoom(roomRequest).enqueue(
                new Callback<SuccessResponse>() {
                    @Override
                    public void onResponse(Call<SuccessResponse> call, Response<SuccessResponse> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Room Updated", Toast.LENGTH_LONG).show();
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

    private void delete(RoomRequest roomRequest) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.0.103:80/php/controllers/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        RoomRequests roomRequests = retrofit.create(RoomRequests.class);

        roomRequests.deleteRoom(roomRequest).enqueue(
                new Callback<SuccessResponse>() {
                    @Override
                    public void onResponse(Call<SuccessResponse> call, Response<SuccessResponse> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Room Deleted", Toast.LENGTH_LONG).show();
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


