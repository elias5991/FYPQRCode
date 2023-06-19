package com.example.fypqrcode;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RoomsActivity extends AppCompatActivity {
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
                finish();            }
        });


        TableLayout roomsT = findViewById(R.id.roomsTable);
        populateTable(roomsT);
        for (int i = 0; i < roomsT.getChildCount(); i++) {
            View row = roomsT.getChildAt(i);

            if (row instanceof TableRow) {
                TableRow tableRow = (TableRow) row;

                tableRow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        for (int i = 0; i < roomsT.getChildCount(); i++) {
                            View row = roomsT.getChildAt(i);
                            if (row instanceof TableRow) {
                                row.setBackgroundColor(Color.TRANSPARENT);
                            }
                        }

                        // Set background color of selected row
                        tableRow.setBackgroundColor(Color.YELLOW);

                        // Handle other actions for the selected row
                    }
                });
            }
        }
    }

    private void populateTable(TableLayout tableLayout) {
        String[][] roomsTypes = {{"1", "M123","Conference","40","Informatique","Science-Fanar"},{"1", "M123","Conference","40","Informatique","Science-II"},{"1", "M123","Conference","40","Informatique","Science-II"},{"1", "M123","Conference","40","Informatique","Science-II"},{"1", "M123","Conference","40","Informatique","Science-II"},{"1", "M123","Conference","40","Informatique","Science-II"},{"1", "M123","Conference","40","Informatique","Science-II"},{"1", "M123","Conference","40","Informatique","Science-II"},{"1", "M123","Conference","40","Informatique","Science-II"},{"1", "M123","Conference","40","Informatique","Science-II"},{"1", "M123","Conference","40","Informatique","Science-II"},{"1", "M123","Conference","40","Informatique","Science-II"},{"1", "M123","Conference","40","Informatique","Science-II"},{"1", "M123","Conference","40","Informatique","Science-II"},{"1", "M123","Conference","40","Informatique","Science-II"},{"1", "M123","Conference","40","Informatique","Science-II"},{"1", "M123","Conference","40","Informatique","Science-II"},{"1", "M123","Conference","40","Informatique","Science-II"},{"1", "M123","Conference","40","Informatique","Science-II"},{"1", "M123","Conference","40","Informatique","Science-II"},{"1", "M123","Conference","40","Informatique","Science-II"},{"1", "M123","Conference","40","Informatique","Science-II"},{"1", "M123","Conference","40","Informatique","Science-II"},{"1", "M123","Conference","40","Informatique","Science-II"},{"1", "M123","Conference","40","Informatique","Science-II"},{"1", "M123","Conference","40","Informatique","Science-II"},{"1", "M123","Conference","40","Informatique","Science-II"},{"1", "M123","Conference","40","Informatique","Science-II"},{"1", "M123","Conference","40","Informatique","Science-II"},{"1", "M123","Conference","40","Informatique","Science-II"},{"1", "M123","Conference","40","Informatique","Science-II"},{"1", "M123","Conference","40","Informatique","Science-II"},{"1", "M123","Conference","40","Informatique","Science-II"},{"1", "M123","Conference","40","Informatique","Science-II"},{"1", "M123","Conference","40","Informatique","Science-II"},{"1", "M123","Conference","40","Informatique","Science-II"},{"1", "M123","Conference","40","Informatique","Science-II"},{"1", "M123","Conference","40","Informatique","Science-II"},{"1", "M123","Conference","40","Informatique","Science-II"},{"1", "M123","Conference","40","Informatique","Science-II"},{"1", "M123","Conference","40","Informatique","Science-II"},{"1", "M123","Conference","40","Informatique","Science-II"},{"1", "M123","Conference","40","Informatique","Science-II"},{"1", "M123","Conference","40","Informatique","Science-II"},{"1", "M123","Conference","40","Informatique","Science-II"},{"1", "M123","Conference","40","Informatique","Science-II"}};
        // Iterate through the data and create rows and cells
        for (int i = 0; i < roomsTypes.length; i++) {
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));

            for (int j = 0; j < roomsTypes[i].length; j++) {
                TextView textView = new TextView(this);
                textView.setText(roomsTypes[i][j]);
                if (j==0){
                    TableRow.LayoutParams params1 = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.08f);
                    textView.setLayoutParams(params1);

                }
                else if(j==1){
                    TableRow.LayoutParams params1 = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.15f);
                    textView.setLayoutParams(params1);

                }
                else if(j==2){
                    TableRow.LayoutParams params1 = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.2f);
                    textView.setLayoutParams(params1);

                }
                else if(j==3){
                    TableRow.LayoutParams params1 = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.12f);
                    textView.setLayoutParams(params1);

                }
                else if(j==4){
                    TableRow.LayoutParams params1 = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.22f);
                    textView.setLayoutParams(params1);

                }
                else if(j==5){
                    TableRow.LayoutParams params1 = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.23f);
                    textView.setLayoutParams(params1);

                }

                tableRow.addView(textView);
            }

            tableLayout.addView(tableRow);
        }
    }
}

