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

public class DepartmentActivity extends AppCompatActivity {
    private String selectedID="-1";

    private String selectedDepartment="Not Selected";
    public String getSelectedType() {
        return selectedDepartment;
    }

    public void setSelectedType(String selectedType) {
        this.selectedDepartment = selectedType;
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
                Intent i = new Intent(DepartmentActivity.this, MainActivity.class);
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
                        View ID = tableRow.getChildAt(0);
                        View DEPARTMENT = tableRow.getChildAt(1);

                        selectedID=((TextView) ID).getText().toString();
                        selectedDepartment=((TextView) DEPARTMENT).getText().toString();
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
    private void crud(View v,String type){
// Inflate the popup_layout.xml
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.department_popup_layout, null);
        TextView idLabel = popupView.findViewById(R.id.departmentIDLabel);
        TextView id = popupView.findViewById(R.id.departmentID);
        TextView department = popupView.findViewById(R.id.department);
        Button actionButoon = popupView.findViewById(R.id.actionBtn);
        actionButoon.setText(type);
        if(type == "ADD") {
            idLabel.setVisibility(GONE);
            id.setVisibility(GONE);
        }
        else if (type == "EDIT" || type == "DELETE")
        {
            id.setText(selectedID);
            department.setText(selectedDepartment);
            if (type=="DELETE")
            {
                department.setEnabled(false);
                department.setFocusable(false);
            }
        }


        // Create the PopupWindow
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // Allows the popup to receive focus
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        // Show the popup at the center of the button
        popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        // Dismiss the popup when the button inside the popup is clicked
        Button closeButton = popupView.findViewById(R.id.backBtn);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });


        actionButoon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }
}



