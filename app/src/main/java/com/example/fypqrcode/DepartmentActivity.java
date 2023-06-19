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

public class DepartmentActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.department_layout);

        //logout
        Button logoutB = findViewById(R.id.logoutBtn);
        logoutB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DepartmentActivity.this, LoginActivity.class);
                startActivity(i);
                finish();            }
        });

        TableLayout departmentT = findViewById(R.id.departmentTable);
        populateTable(departmentT);
        for (int i = 0; i < departmentT.getChildCount(); i++) {
            View row = departmentT.getChildAt(i);

            if (row instanceof TableRow) {
                TableRow tableRow = (TableRow) row;

                tableRow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        for (int i = 0; i < departmentT.getChildCount(); i++) {
                            View row = departmentT.getChildAt(i);
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
        String[][] roomTypes = {{"1", "Informatique"}, {"2", "Maths"},{"3", "Physics"},{"1", "Informatique"}, {"2", "Maths"},{"3", "Physics"},{"1", "Informatique"}, {"2", "Maths"},{"3", "Physics"},{"1", "Informatique"}, {"2", "Maths"},{"3", "Physics"},{"1", "Informatique"}, {"2", "Maths"},{"3", "Physics"},{"1", "Informatique"}, {"2", "Maths"},{"3", "Physics"},{"1", "Informatique"}, {"2", "Maths"},{"3", "Physics"},{"1", "Informatique"}, {"2", "Maths"},{"3", "Physics"},{"1", "Informatique"}, {"2", "Maths"},{"3", "Physics"},{"1", "Informatique"}, {"2", "Maths"},{"3", "Physics"},{"1", "Informatique"}, {"2", "Maths"},{"3", "Physics"},{"1", "Informatique"}, {"2", "Maths"},{"3", "Physics"},{"1", "Informatique"}, {"2", "Maths"},{"3", "Physics"},{"1", "Informatique"}, {"2", "Maths"},{"3", "Physics"},{"1", "Informatique"}, {"2", "Maths"},{"3", "Physics"},{"1", "Informatique"}, {"2", "Maths"},{"3", "Physics"},{"1", "Informatique"}, {"2", "Maths"},{"3", "Physics"},{"1", "Informatique"}, {"2", "Maths"},{"3", "Physics"},{"1", "Informatique"}, {"2", "Maths"},{"3", "Physics"},{"1", "Informatique"}, {"2", "Maths"},{"3", "Physics"},{"1", "Informatique"}, {"2", "Maths"},{"3", "Physics"},{"1", "Informatique"}, {"2", "Maths"},{"3", "Physics"},{"1", "Informatique"}, {"2", "Maths"},{"3", "Physics"},{"1", "Informatique"}, {"2", "Maths"},{"3", "Physics"},{"1", "Informatique"}, {"2", "Maths"},{"3", "Physics"},{"1", "Informatique"}, {"2", "Maths"},{"3", "Physics"},{"1", "Informatique"}, {"2", "Maths"},{"3", "Physics"},{"1", "Informatique"}, {"2", "Maths"},{"3", "Physics"},{"1", "Informatique"}, {"2", "Maths"},{"3", "Physics"},{"1", "Informatique"}, {"2", "Maths"},{"3", "Physics"},{"1", "Informatique"}, {"2", "Maths"},{"3", "Physics"},{"1", "Informatique"}, {"2", "Maths"},{"3", "Physics"},{"1", "Informatique"}, {"2", "Maths"},{"3", "Physics"},{"1", "Informatique"}, {"2", "Maths"},{"3", "Physics"},{"1", "Informatique"}, {"2", "Maths"},{"3", "Physics"},{"1", "Informatique"}, {"2", "Maths"},{"3", "Physics"},{"1", "Informatique"}, {"2", "Maths"},{"3", "Physics"},{"1", "Informatique"}, {"2", "Maths"},{"3", "Physics"},{"1", "Informatique"}, {"2", "Maths"},{"3", "Physics"},{"1", "Informatique"}, {"2", "Maths"},{"3", "Physics"},{"1", "Informatique"}, {"2", "Maths"},{"3", "Physics"},{"1", "Informatique"}, {"2", "Maths"},{"3", "Physics"},{"1", "Informatique"}, {"2", "Maths"},{"3", "Physics"},{"1", "Informatique"}, {"2", "Maths"},{"3", "Physics"},{"1", "Informatique"}, {"2", "Maths"},{"3", "Physics"},{"1", "Informatique"}, {"2", "Maths"},{"3", "Physics"}};
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
}


