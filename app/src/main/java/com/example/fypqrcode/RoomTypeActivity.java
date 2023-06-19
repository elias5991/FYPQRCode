package com.example.fypqrcode;

import static android.view.View.GONE;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RoomTypeActivity extends AppCompatActivity {

    private String selectedID="-1";

    private String selectedType="Not Selected";
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
                finish();            }
        });

        // add
        Button addB = findViewById(R.id.addBtn);
        addB.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        crud(v,"ADD");
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
                }
                else{
                crud(v,"EDIT");}
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
                }
                else{
                crud(v,"DELETE");}
            }
        });

        //Back
        Button backB = findViewById(R.id.backBtn);
        backB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RoomTypeActivity.this, MainActivity.class);
                startActivity(i);
                finish();            }
        });
        TableLayout roomTypeT = findViewById(R.id.roomTypeTable);
        populateTable(roomTypeT);
        for (int i = 0; i < roomTypeT.getChildCount(); i++) {
            View row = roomTypeT.getChildAt(i);

            if (row instanceof TableRow) {
                TableRow tableRow = (TableRow) row;

                tableRow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        for (int i = 0; i < roomTypeT.getChildCount(); i++) {
                            View row = roomTypeT.getChildAt(i);
                            if (row instanceof TableRow) {
                                row.setBackgroundColor(Color.TRANSPARENT);
                            }
                        }

                        // Set background color of selected row
                        tableRow.setBackgroundColor(Color.YELLOW);
                        View ID = tableRow.getChildAt(0);
                        View TYPE = tableRow.getChildAt(1);

                        selectedID=((TextView) ID).getText().toString();
                        selectedType=((TextView) TYPE).getText().toString();
                        // Handle other actions for the selected row
                    }
                });
            }
        }
    }

    private void populateTable(TableLayout tableLayout) {
        String[][] roomTypes = {{"1", "Bathroom"}, {"2", "Class"},{"1", "Bathroom"}, {"2", "Class"},{"1", "Bathroom"}, {"2", "Class"},{"1", "Bathroom"}, {"2", "Class"},{"1", "Bathroom"}, {"2", "Class"},{"1", "Bathroom"}, {"2", "Class"},{"1", "Bathroom"}, {"2", "Class"},{"1", "Bathroom"}, {"2", "Class"},{"1", "Bathroom"}, {"2", "Class"},{"1", "Bathroom"}, {"2", "Class"},{"1", "Bathroom"}, {"2", "Class"},{"1", "Bathroom"}, {"2", "Class"},{"1", "Bathroom"}, {"2", "Class"},{"1", "Bathroom"}, {"2", "Class"},{"1", "Bathroom"}, {"2", "Class"},{"1", "Bathroom"}, {"2", "Class"},{"1", "Bathroom"}, {"2", "Class"},{"1", "Bathroom"}, {"2", "Class"}};

        // Iterate through the data and create rows and cells
        for (int i = 0; i < roomTypes.length; i++) {
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));

            for (int j = 0; j < roomTypes[i].length; j++) {
                TextView textView = new TextView(this);
                textView.setText(roomTypes[i][j]);
                if (j==0){
                    TableRow.LayoutParams params1 = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.2f);
                    textView.setLayoutParams(params1);

                }
                else{
                    TableRow.LayoutParams params1 = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.8f);
                    textView.setLayoutParams(params1);

                }

                tableRow.addView(textView);
            }

            tableLayout.addView(tableRow);
        }
    }
    private void crud(View v,String type){
// Inflate the popup_layout.xml
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.room_type_popup_layout, null);
        TextView idLabel = popupView.findViewById(R.id.roomTypeIDLabel);
        TextView id = popupView.findViewById(R.id.roomTypeID);
        TextView roomType = popupView.findViewById(R.id.roomType);
        Button addButton = popupView.findViewById(R.id.actionBtn);
        addButton.setText(type);
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


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
}

//    private void edit(View v){
//// Inflate the popup_layout.xml
//        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View popupView = inflater.inflate(R.layout.room_type_popup_layout, null);
//        TextView id = popupView.findViewById(R.id.roomTypeID);
//        id.setText(selectedID);
//        // Create the PopupWindow
//        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
//        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
//        boolean focusable = true; // Allows the popup to receive focus
//        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
//
//        // Show the popup at the center of the button
//        popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);
//
//        // Dismiss the popup when the button inside the popup is clicked
//        Button closeButton = popupView.findViewById(R.id.backBtn);
//        closeButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                popupWindow.dismiss();
//            }
//        });
//
//        Button addButton = popupView.findViewById(R.id.addBtn);
//        addButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                popupWindow.dismiss();
//            }
//        });
//    }

}
