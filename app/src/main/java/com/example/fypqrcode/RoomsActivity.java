package com.example.fypqrcode;

import static android.view.View.GONE;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
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
import com.example.fypqrcode.classes.Room;

import java.util.ArrayList;
import java.util.List;

public class RoomsActivity extends AppCompatActivity {
    private Boolean isSelected = false;

    private Room room= new Room();


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
                        // Handle other actions for the selected row
                    }
                });
            }
        }
    }

    private void populateTable(TableLayout tableLayout) {
        String[][] roomsTypes = {{"1", "M123", "Conference", "40", "Informatique", "Science-Fanar"}, {"1", "M123", "Class", "40", "Maths", "Pedagogy-II"}, {"1", "M123", "Conference", "40", "Physics", "Genie-II"}, {"1", "M123", "Conference", "40", "Informatique", "Science-II"}, {"1", "M123", "Conference", "40", "Informatique", "Science-II"}, {"1", "M123", "Conference", "40", "Informatique", "Science-II"}, {"1", "M123", "Conference", "40", "Informatique", "Science-II"}, {"1", "M123", "Conference", "40", "Informatique", "Science-II"}, {"1", "M123", "Conference", "40", "Informatique", "Science-II"}, {"1", "M123", "Conference", "40", "Informatique", "Science-II"}, {"1", "M123", "Conference", "40", "Informatique", "Science-II"}, {"1", "M123", "Conference", "40", "Informatique", "Science-II"}, {"1", "M123", "Conference", "40", "Informatique", "Science-II"}, {"1", "M123", "Conference", "40", "Informatique", "Science-II"}, {"1", "M123", "Conference", "40", "Informatique", "Science-II"}, {"1", "M123", "Conference", "40", "Informatique", "Science-II"}, {"1", "M123", "Conference", "40", "Informatique", "Science-II"}, {"1", "M123", "Conference", "40", "Informatique", "Science-II"}, {"1", "M123", "Conference", "40", "Informatique", "Science-II"}, {"1", "M123", "Conference", "40", "Informatique", "Science-II"}, {"1", "M123", "Conference", "40", "Informatique", "Science-II"}, {"1", "M123", "Conference", "40", "Informatique", "Science-II"}, {"1", "M123", "Conference", "40", "Informatique", "Science-II"}, {"1", "M123", "Conference", "40", "Informatique", "Science-II"}, {"1", "M123", "Conference", "40", "Informatique", "Science-II"}, {"1", "M123", "Conference", "40", "Informatique", "Science-II"}, {"1", "M123", "Conference", "40", "Informatique", "Science-II"}, {"1", "M123", "Conference", "40", "Informatique", "Science-II"}, {"1", "M123", "Conference", "40", "Informatique", "Science-II"}, {"1", "M123", "Conference", "40", "Informatique", "Science-II"}, {"1", "M123", "Conference", "40", "Informatique", "Science-II"}, {"1", "M123", "Conference", "40", "Informatique", "Science-II"}, {"1", "M123", "Conference", "40", "Informatique", "Science-II"}, {"1", "M123", "Conference", "40", "Informatique", "Science-II"}, {"1", "M123", "Conference", "40", "Informatique", "Science-II"}, {"1", "M123", "Conference", "40", "Informatique", "Science-II"}, {"1", "M123", "Conference", "40", "Informatique", "Science-II"}, {"1", "M123", "Conference", "40", "Informatique", "Science-II"}, {"1", "M123", "Conference", "40", "Informatique", "Science-II"}, {"1", "M123", "Conference", "40", "Informatique", "Science-II"}, {"1", "M123", "Conference", "40", "Informatique", "Science-II"}, {"1", "M123", "Conference", "40", "Informatique", "Science-II"}, {"1", "M123", "Conference", "40", "Informatique", "Science-II"}, {"1", "M123", "Conference", "40", "Informatique", "Science-II"}, {"1", "M123", "Conference", "40", "Informatique", "Science-II"}, {"1", "M123", "Conference", "40", "Informatique", "Science-II"}};
        // Iterate through the data and create rows and cells
        for (int i = 0; i < roomsTypes.length; i++) {
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));

            for (int j = 0; j < roomsTypes[i].length; j++) {
                TextView textView = new TextView(this);
                textView.setText(roomsTypes[i][j]);
                if (j == 0) {
                    TableRow.LayoutParams params1 = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.08f);
                    textView.setLayoutParams(params1);

                } else if (j == 1) {
                    TableRow.LayoutParams params1 = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.15f);
                    textView.setLayoutParams(params1);

                } else if (j == 2) {
                    TableRow.LayoutParams params1 = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.2f);
                    textView.setLayoutParams(params1);

                } else if (j == 3) {
                    TableRow.LayoutParams params1 = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.12f);
                    textView.setLayoutParams(params1);

                } else if (j == 4) {
                    TableRow.LayoutParams params1 = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.22f);
                    textView.setLayoutParams(params1);

                } else if (j == 5) {
                    TableRow.LayoutParams params1 = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.23f);
                    textView.setLayoutParams(params1);

                }

                tableRow.addView(textView);
            }

            tableLayout.addView(tableRow);
        }
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
        roomTypeS.add(new DataItem(1, "Conference"));
        roomTypeS.add(new DataItem(2, "Class"));
        roomTypeS.add(new DataItem(3, "Bathroom"));

        ArrayAdapter<DataItem> roomTypeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, roomTypeS);
        roomTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        roomType.setAdapter(roomTypeAdapter);





        List<DataItem> departmentS = new ArrayList<>();
        departmentS.add(new DataItem(1, "Informatique"));
        departmentS.add(new DataItem(2, "Maths"));
        departmentS.add(new DataItem(3, "Physics"));

        ArrayAdapter<DataItem> departmentAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, departmentS);
        departmentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        department.setAdapter(departmentAdapter);



        List<DataItem> facultyS = new ArrayList<>();
        facultyS.add(new DataItem(1, "Science-II"));
        facultyS.add(new DataItem(2, "Pedagogy-II"));
        facultyS.add(new DataItem(3, "Genie-II"));

        ArrayAdapter<DataItem> facultyAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, facultyS);
        facultyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        faculty.setAdapter(facultyAdapter);





        Button actionButton = popupView.findViewById(R.id.actionBtn);
        actionButton.setText(type);
        if (type == "ADD") {
            idLabel.setVisibility(GONE);
            roomID.setVisibility(GONE);
        } else if (type == "EDIT" || type == "DELETE") {
            for (int i = 0; i < roomTypeAdapter.getCount(); i++) {
                DataItem item = roomTypeAdapter.getItem(i);
                if (item.getValue() == room.getType()) {
                    roomType.setSelection(i);
                    break;
                }
            }
            for (int i = 0; i < departmentAdapter.getCount(); i++) {
                DataItem item = departmentAdapter.getItem(i);
                if (item.getValue() == room.getDepartment()) {
                    department.setSelection(i);
                    break;
                }
            }
            for (int i = 0; i < facultyAdapter.getCount(); i++) {
                DataItem item = facultyAdapter.getItem(i);
                if (item.getValue() == room.getFaculty()) {
                    faculty.setSelection(i);
                    break;
                }
            }
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

                    department.setEnabled(false);
                    department.setFocusable(false);

                    faculty.setEnabled(false);
                    faculty.setFocusable(false);
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


            actionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popupWindow.dismiss();
                }
            });
        }
    }


