package com.example.fypqrcode;

import static android.view.View.GONE;

import static okhttp3.internal.Util.concat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Trace;
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
import com.example.fypqrcode.http.RoomInvRequests;
import com.example.fypqrcode.http.RoomRequests;
import com.example.fypqrcode.http.RoomTypeRequests;
import com.example.fypqrcode.http.requests.RoomInvRequest;
import com.example.fypqrcode.http.requests.RoomRequest;
import com.example.fypqrcode.http.responses.ErrorResponse;
import com.example.fypqrcode.http.responses.InventoryResponse;
import com.example.fypqrcode.http.responses.RoomInvResponse;
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

public class RoomInventoryActivity extends AppCompatActivity {
    RoomInvResponse selectedRoomInv =new RoomInvResponse();
    private Boolean isSelected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.room_inventory_layout);

        TextView roomName = findViewById(R.id.roomName);
        TextView roomSpace = findViewById(R.id.space);
        TextView roomType = findViewById(R.id.type);
        TextView roomDep = findViewById(R.id.department);
        TextView roomFaculty = findViewById(R.id.faculty);
        SharedPreferences sharedPreferences = getSharedPreferences("App", Context.MODE_PRIVATE);
        String roomID = sharedPreferences.getString("roomID","");

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.0.103:80/php/controllers/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        RoomRequests roomRequests = retrofit.create(RoomRequests.class);

        //logout
        Button logoutB = findViewById(R.id.logoutBtn);
        logoutB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RoomInventoryActivity.this, LoginActivity.class);
                startActivity(i);
                finish();            }
        });

        //Back
        Button backB = findViewById(R.id.backBtn);
        backB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RoomInventoryActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
        roomRequests.getCurrentRoom(roomID).enqueue(new Callback<RoomResponse>() {
            @Override
            public void onResponse(Call<RoomResponse> call, Response<RoomResponse> response) {
                if (response.isSuccessful()) {
                    RoomResponse result = response.body();
                    roomName.setText(result.getName().toString());
                    roomFaculty.setText(result.getFaculty());
                    roomDep.setText(result.getDepartment());
                    roomSpace.setText(result.getSpace().toString());
                    roomType.setText(result.getType());
                }
            }

            @Override
            public void onFailure(Call<RoomResponse> call, Throwable t) {

            }
        });
        roomName.setText(roomID);

        // add
        Button addB = findViewById(R.id.addBtn);
        addB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crud(v, "ADD",roomID);
                isSelected = false;
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
                    crud(v, "EDIT",roomID);
                    isSelected = false;
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
                    crud(v, "DELETE",roomID);
                    isSelected = false;
                }
            }
        });
        populateTable();
    }

    private void populateTable() {
        SharedPreferences sharedPreferences = getSharedPreferences("App", Context.MODE_PRIVATE);
        String roomID = sharedPreferences.getString("roomID","");
        TableLayout tableLayout = findViewById(R.id.inventoriesTable);
        tableLayout.removeAllViews();
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.0.103:80/php/controllers/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        RoomInvRequests roomInvRequests = retrofit.create(RoomInvRequests.class);
        roomInvRequests.getCurrentRoomInv(roomID).enqueue(new Callback<RoomInvResponse[]>()
        {
          @Override
          public void onResponse(Call<RoomInvResponse[]> call, Response<RoomInvResponse[]> response) {
              if (response.isSuccessful()) {
                  RoomInvResponse[] result = response.body();
                  for (int i = 0; i < result.length; i++) {
                      TableRow tableRow = new TableRow(RoomInventoryActivity.this);
                      tableRow.setLayoutParams(new TableRow.LayoutParams(
                              TableRow.LayoutParams.MATCH_PARENT,
                              TableRow.LayoutParams.WRAP_CONTENT));

                      TextView textView = new TextView(RoomInventoryActivity.this);
                      textView.setText(result[i].getType().toString());

                      TableRow.LayoutParams params1 = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.3f);
                      textView.setLayoutParams(params1);
                      tableRow.addView(textView);
                      TextView textView1 = new TextView(RoomInventoryActivity.this);
                      textView1.setText(result[i].getDescription());
                      TableRow.LayoutParams params2 = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.5f);
                      textView1.setLayoutParams(params2);
                      tableRow.addView(textView1);
                      TextView textView2 = new TextView(RoomInventoryActivity.this);
                      textView2.setText(result[i].getQuantity().toString());
                      TableRow.LayoutParams params3 = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.2f);
                      textView2.setLayoutParams(params3);
                      tableRow.addView(textView2);

                      TextView textView3 = new TextView(RoomInventoryActivity.this);
                      textView3.setText(result[i].getInventory_id().toString());
                      textView3.setVisibility(View.GONE);
                      tableRow.addView(textView3);

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
                              TextView typeView = (TextView) tableRow.getChildAt(0);
                              TextView descriptionView = (TextView) tableRow.getChildAt(1);
                              TextView quantityView = (TextView) tableRow.getChildAt(2);
                              TextView inventoryIdView = (TextView) tableRow.getChildAt(3);

                              isSelected = true;
                              selectedRoomInv.setType(typeView.getText().toString());
                              selectedRoomInv.setDescription(descriptionView.getText().toString());
                              selectedRoomInv.setQuantity(Integer.parseInt(quantityView.getText().toString()));
                              selectedRoomInv.setInventory_id(Integer.parseInt(inventoryIdView.getText().toString()));
                          }
                      });
                      tableLayout.addView(tableRow);
                  }

              }

              }


          @Override
          public void onFailure(Call<RoomInvResponse[]> call, Throwable t) {
              Toast.makeText(getApplicationContext(), "Something Went Wrong", Toast.LENGTH_LONG).show();
              System.out.println("failure");

          }
      }) ;


    }
    private void crud(View v, String type, String roomID) {
// Inflate the popup_layout.xml
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.room_inventory_popup_layout, null);
        EditText quantity = popupView.findViewById(R.id.quantity);
        Spinner inventory = popupView.findViewById(R.id.inventorySpinner);

        List<DataItem> inventoryS = new ArrayList<>();
        DataItem selectedInventory = new DataItem();
        ArrayAdapter<DataItem> roomInvAdapter = new ArrayAdapter<>(RoomInventoryActivity.this, android.R.layout.simple_spinner_item, inventoryS);

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.0.103:80/php/controllers/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        RoomInvRequests roomInvRequests = retrofit.create(RoomInvRequests.class);

        roomInvRequests.getAllInventories().enqueue(new Callback<InventoryResponse[]>() {
            @Override
            public void onResponse(Call<InventoryResponse[]> call, Response<InventoryResponse[]> response) {
                InventoryResponse[] result = response.body();
                for (int i = 0; i < result.length; i++) {
                    inventoryS.add(new DataItem(result[i].getId(), result[i].getType()+", "+ result[i].getDescription()));
                }
                roomInvAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                inventory.setAdapter(roomInvAdapter);
                if (type == "EDIT" || type == "DELETE") {
                    for (int i = 0; i < roomInvAdapter.getCount(); i++) {
                        DataItem item = roomInvAdapter.getItem(i);
                        if (item.getValue().equals(selectedRoomInv.getType() + ", " + selectedRoomInv.getDescription())) {
                            inventory.setSelection(i);
                            break;
                        }
                    }
                }
                inventory.setOnItemSelectedListener  (new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        selectedInventory.setId(inventoryS.get(position).getId());
                        selectedInventory.setValue(inventoryS.get(position).getValue());
                        roomInvAdapter.notifyDataSetChanged();            }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

            }



            @Override
            public void onFailure(Call<InventoryResponse[]> call, Throwable t) {

            }




        });

        Button actionButton = popupView.findViewById(R.id.actionBtn);
        actionButton.setText(type);

         if (type == "EDIT" || type == "DELETE") {
            quantity.setText(selectedRoomInv.getQuantity().toString());
            quantity.setText(selectedRoomInv.getQuantity().toString());
            inventory.setEnabled(false);
            inventory.setFocusable(false);


            if (type == "DELETE") {
                quantity.setEnabled(false);
                quantity.setFocusable(false);


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
                    add(new RoomInvRequest(selectedInventory.getId(),Integer.parseInt(roomID),Integer.parseInt(quantity.getText().toString())));
                }
                if(type=="EDIT"){
                    edit(new RoomInvRequest(selectedInventory.getId(),Integer.parseInt(roomID),Integer.parseInt(quantity.getText().toString())));
                }
                if(type=="DELETE"){
                    delete(new RoomInvRequest(selectedInventory.getId(),Integer.parseInt(roomID),null));}


                popupWindow.dismiss();
            }
        });
    }
    private void add(RoomInvRequest roomInvRequest) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.0.103:80/php/controllers/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        RoomInvRequests roomInvRequests = retrofit.create(RoomInvRequests.class);

        roomInvRequests.insertNewRoomInv(roomInvRequest).enqueue(
                new Callback<SuccessResponse>() {
                    @Override
                    public void onResponse(Call<SuccessResponse> call, Response<SuccessResponse> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Inventory Added", Toast.LENGTH_LONG).show();
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

    private void edit(RoomInvRequest roomInvRequest) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.0.103:80/php/controllers/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        RoomInvRequests roomInvRequests = retrofit.create(RoomInvRequests.class);

        roomInvRequests.updateRoomInv(roomInvRequest).enqueue(
                new Callback<SuccessResponse>() {
                    @Override
                    public void onResponse(Call<SuccessResponse> call, Response<SuccessResponse> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Inventory Updated", Toast.LENGTH_LONG).show();
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

    private void delete(RoomInvRequest roomInvRequest) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.0.103:80/php/controllers/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        RoomInvRequests roomInvRequests = retrofit.create(RoomInvRequests.class);

        roomInvRequests.deleteRoomInv(roomInvRequest).enqueue(
                new Callback<SuccessResponse>() {
                    @Override
                    public void onResponse(Call<SuccessResponse> call, Response<SuccessResponse> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Inventory Deleted", Toast.LENGTH_LONG).show();
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



