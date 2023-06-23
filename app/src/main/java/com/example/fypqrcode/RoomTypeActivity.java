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
    import android.widget.Button;
    import android.widget.LinearLayout;
    import android.widget.PopupWindow;
    import android.widget.TableLayout;
    import android.widget.TableRow;
    import android.widget.TextView;
    import android.widget.Toast;

    import androidx.appcompat.app.AppCompatActivity;

    import com.example.fypqrcode.classes.DataItem;
    import com.example.fypqrcode.htpp.RoomTypeRequests;
    import com.example.fypqrcode.htpp.requests.ValueRequest;

    import com.example.fypqrcode.htpp.responses.ErrorResponse;
    import com.example.fypqrcode.htpp.responses.SuccessResponse;
    import com.google.gson.Gson;

    import java.io.IOException;

    import retrofit2.Call;
    import retrofit2.Callback;
    import retrofit2.Response;
    import retrofit2.Retrofit;
    import retrofit2.converter.gson.GsonConverterFactory;

    public class RoomTypeActivity extends AppCompatActivity {

        private String selectedID = "-1";

        private String selectedType = "Not Selected";
//        private TableLayout roomTypeT;
        public String getSelectedType() {
            return selectedType;
        }

        public void setSelectedType(String selectedType) {
            this.selectedType = selectedType;
        }


        public String getSelectedID() {
            return selectedID;
        }


        public void setSelectedID(String selectedID) {
            this.selectedID = selectedID;
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.room_type_layout);

            //logout
            Button logoutB = findViewById(R.id.logoutBtn);
            logoutB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(RoomTypeActivity.this, LoginActivity.class);
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
                    if (selectedID == "-1") {
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
                    if (selectedID == "-1") {
                        Toast.makeText(getApplicationContext(), "No row selected.", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        crud(v, "DELETE");
                    }
                }
            });

            //Back
            Button backB = findViewById(R.id.backBtn);
            backB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(RoomTypeActivity.this, MainActivity.class);
                    startActivity(i);
                    finish();
                }
            });

            populateTable();

        }

        private void populateTable() {
            TableLayout tableLayout = findViewById(R.id.roomTypeTable);
            tableLayout.removeAllViews();
            Retrofit retrofit = new Retrofit.Builder().baseUrl("http://10.0.2.2:80/php/controllers/")
                    .addConverterFactory(GsonConverterFactory.create()).build();
            RoomTypeRequests roomTypeRequests = retrofit.create(RoomTypeRequests.class);
            roomTypeRequests.getAllRoomTypes().enqueue(new Callback<DataItem[]>() {

               @Override
                public void onResponse(Call<DataItem[]> call, Response<DataItem[]> response) {
                    if (response.isSuccessful()) {
                        DataItem[] result = response.body();

                        // Iterate through the data and create rows and cells
                        for (int i = 0; i < result.length; i++) {
                            TableRow tableRow = new TableRow(RoomTypeActivity.this);
                            tableRow.setLayoutParams(new TableRow.LayoutParams(
                                    TableRow.LayoutParams.MATCH_PARENT,
                                    TableRow.LayoutParams.WRAP_CONTENT));

                                TextView textView = new TextView(RoomTypeActivity.this);
                                textView.setText(result[i].getId().toString());

                                    TableRow.LayoutParams params1 = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.2f);
                                    textView.setLayoutParams(params1);
                                    tableRow.addView(textView);
                                    TextView textView1 = new TextView(RoomTypeActivity.this);
                                    textView1.setText(result[i].getValue());
                                    TableRow.LayoutParams params2 = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.8f);
                                    textView1.setLayoutParams(params2);
                                    tableRow.addView(textView1);

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
                                    TextView idTextView = (TextView) tableRow.getChildAt(0);
                                    TextView typeTextView = (TextView) tableRow.getChildAt(1);

                                    selectedID = idTextView.getText().toString();
                                    selectedType = typeTextView.getText().toString();

                                }
                            });


                            tableLayout.addView(tableRow);
                        }

                    }
                }

                @Override
                public void onFailure(Call<DataItem[]> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Something Went Wrong", Toast.LENGTH_LONG).show();
                    System.out.println("failure");
                }
            });
        }




        private void crud(View v,String type){
    // Inflate the popup_layout.xml
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View popupView = inflater.inflate(R.layout.room_type_popup_layout, null);
            TextView idLabel = popupView.findViewById(R.id.roomTypeIDLabel);
            TextView id = popupView.findViewById(R.id.roomTypeID);
            TextView roomType = popupView.findViewById(R.id.roomType);
            Button actionButton = popupView.findViewById(R.id.actionBtn);
            actionButton.setText(type);
            if(type == "ADD") {
                idLabel.setVisibility(GONE);
                id.setVisibility(GONE);
            }
            else if (type == "EDIT" || type == "DELETE")
            {
                id.setText(selectedID);
                roomType.setText(selectedType);
                if (type=="DELETE")
                {
                    roomType.setEnabled(false);
                    roomType.setFocusable(false);
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
                        add(roomType.getText().toString());
                    }
                    if(type=="EDIT"){
                        edit(new DataItem(Integer.parseInt(id.getText().toString()),roomType.getText().toString()));
                    }
                    if(type=="DELETE"){
                        delete(new DataItem(Integer.parseInt(id.getText().toString()),roomType.getText().toString()));
                    }

                    popupWindow.dismiss();
                }
            });
    }

    private void add(String roomType) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://10.0.2.2:80/php/controllers/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        RoomTypeRequests roomTypeRequests = retrofit.create(RoomTypeRequests.class);

        roomTypeRequests.insertNewRoomType(new ValueRequest(roomType)).enqueue(
                new Callback<SuccessResponse>() {
                    @Override
                    public void onResponse(Call<SuccessResponse> call, Response<SuccessResponse> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Room type Added", Toast.LENGTH_LONG).show();
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

        private void edit(DataItem roomTypeDataItem) {
            Retrofit retrofit = new Retrofit.Builder().baseUrl("http://10.0.2.2:80/php/controllers/")
                    .addConverterFactory(GsonConverterFactory.create()).build();
            RoomTypeRequests roomTypeRequests = retrofit.create(RoomTypeRequests.class);

            roomTypeRequests.updateRoomType(roomTypeDataItem).enqueue(
                    new Callback<SuccessResponse>() {
                        @Override
                        public void onResponse(Call<SuccessResponse> call, Response<SuccessResponse> response) {
                            if (response.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Room Type Updated", Toast.LENGTH_LONG).show();
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

        private void delete(DataItem roomTypeDataItem) {
            Retrofit retrofit = new Retrofit.Builder().baseUrl("http://10.0.2.2:80/php/controllers/")
                    .addConverterFactory(GsonConverterFactory.create()).build();
            RoomTypeRequests roomTypeRequests = retrofit.create(RoomTypeRequests.class);

            roomTypeRequests.deleteRoomType(roomTypeDataItem).enqueue(
                    new Callback<SuccessResponse>() {
                        @Override
                        public void onResponse(Call<SuccessResponse> call, Response<SuccessResponse> response) {
                            if (response.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Room Type Deleted", Toast.LENGTH_LONG).show();
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
