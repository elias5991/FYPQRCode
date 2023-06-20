package com.example.fypqrcode;

import static android.view.View.GONE;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
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
import com.example.fypqrcode.classes.User;

import java.util.ArrayList;
import java.util.List;

public class UsersActivity extends AppCompatActivity {
    private Boolean isSelected = false;

    private User user= new User();
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
        TableLayout usersT = findViewById(R.id.userTable);
        populateTable(usersT);
        for (int i = 0; i < usersT.getChildCount(); i++) {
            View row = usersT.getChildAt(i);

            if (row instanceof TableRow) {
                TableRow tableRow = (TableRow) row;

                tableRow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        for (int i = 0; i < usersT.getChildCount(); i++) {
                            View row = usersT.getChildAt(i);
                            if (row instanceof TableRow) {
                                row.setBackgroundColor(Color.TRANSPARENT);
                            }
                        }

                        // Set background color of selected row
                        tableRow.setBackgroundColor(Color.YELLOW);
                        View ID = tableRow.getChildAt(0);
                        View FirstName = tableRow.getChildAt(1);
                        View LastName = tableRow.getChildAt(2);
                        View PhoneNumber = tableRow.getChildAt(3);
                        View Type = tableRow.getChildAt(4);
                        View Role = tableRow.getChildAt(5);
                        View Email = tableRow.getChildAt(6);

                        user.setId(Integer.valueOf((String) ((TextView) ID).getText()));
                        user.setFirstName(((TextView) FirstName).getText().toString());
                        user.setLastName(((TextView) LastName).getText().toString());
                        user.setPhoneNumber(((TextView) PhoneNumber).getText().toString());
                        user.setType(((TextView) Type).getText().toString());
                        user.setRole(((TextView) Role).getText().toString());
                        user.setEmail(((TextView) Email).getText().toString());
                        isSelected = true;
                        // Handle other actions for the selected row
                    }
                });
            }
        }
    }


    private void populateTable(TableLayout usersT) {
    TableLayout headerT = findViewById(R.id.usersHeaderTable);
    TableRow headerR = (TableRow) headerT.getChildAt(0);
    TableLayout tableLayout = findViewById(R.id.userTable);
    String[][] users = {{"1", "Pamela", "Nohra", "71893615", "Admin", "Engineer", "pamela.nohra@outlook.com"},{"1", "Pamela", "Nohra", "71893615", "Admin", "Engineer", "pamela.nohra@outlook.com"}, {"1", "Pamela","Nohra","71893615","Admin","Engineer","pamela.nohra@outlook.com"},{"1", "Pamela","Nohra","71893615","Guest","Doctor","pamela.nohra@outlook.com"},{"1", "Pamela","Nohra","71893615","Admin","Engineer","pamela.nohra@outlook.com"}, {"1", "Pamela","Nohra","71893615","Admin","Engineer","pamela.nohra@outlook.com"},{"1", "Pamela","Nohra","71893615","Admin","Engineer","pamela.nohra@outlook.com"},{"1", "Pamela","Nohra","71893615","Admin","Engineer","pamela.nohra@outlook.com"}, {"1", "Pamela","Nohra","71893615","Admin","Engineer","pamela.nohra@outlook.com"},{"1", "Pamela","Nohra","71893615","Admin","Engineer","pamela.nohra@outlook.com"},{"1", "Pamela","Nohra","71893615","Admin","Engineer","pamela.nohra@outlook.com"}, {"1", "Pamela","Nohra","71893615","Admin","Engineer","pamela.nohra@outlook.com"},{"1", "Pamela","Nohra","71893615","Admin","Engineer","pamela.nohra@outlook.com"},{"1", "Pamela","Nohra","71893615","Admin","Engineer","pamela.nohra@outlook.com"}, {"1", "Pamela","Nohra","71893615","Admin","Engineer","pamela.nohra@outlook.com"},{"1", "Pamela","Nohra","71893615","Admin","Engineer","pamela.nohra@outlook.com"},{"1", "Pamela","Nohra","71893615","Admin","Engineer","pamela.nohra@outlook.com"}, {"1", "Pamela","Nohra","71893615","Admin","Engineer","pamela.nohra@outlook.com"},{"1", "Pamela","Nohra","71893615","Admin","Engineer","pamela.nohra@outlook.com"},{"1", "Pamela","Nohra","71893615","Admin","Engineer","pamela.nohra@outlook.com"}, {"1", "Pamela","Nohra","71893615","Admin","Engineer","pamela.nohra@outlook.com"},{"1", "Pamela","Nohra","71893615","Admin","Engineer","pamela.nohra@outlook.com"},{"1", "Pamela","Nohra","71893615","Admin","Engineer","pamela.nohra@outlook.com"}, {"1", "Pamela","Nohra","71893615","Admin","Engineer","pamela.nohra@outlook.com"},{"1", "Pamela","Nohra","71893615","Admin","Engineer","pamela.nohra@outlook.com"},{"1", "Pamela","Nohra","71893615","Admin","Engineer","pamela.nohra@outlook.com"}, {"1", "Pamela","Nohra","71893615","Admin","Engineer","pamela.nohra@outlook.com"},{"1", "Pamela","Nohra","71893615","Admin","Engineer","pamela.nohra@outlook.com"},{"1", "Pamela","Nohra","71893615","Admin","Engineer","pamela.nohra@outlook.com"}, {"1", "Pamela","Nohra","71893615","Admin","Engineer","pamela.nohra@outlook.com"},{"1", "Pamela","Nohra","71893615","Admin","Engineer","pamela.nohra@outlook.com"},{"1", "Pamela","Nohra","71893615","Admin","Engineer","pamela.nohra@outlook.com"}, {"1", "Pamela","Nohra","71893615","Admin","Engineer","pamela.nohra@outlook.com"},{"1", "Pamela","Nohra","71893615","Admin","Engineer","pamela.nohra@outlook.com"},{"1", "Pamela","Nohra","71893615","Admin","Engineer","pamela.nohra@outlook.com"}, {"1", "Pamela","Nohra","71893615","Admin","Engineer","pamela.nohra@outlook.com"},{"1", "Pamela","Nohra","71893615","Admin","Engineer","pamela.nohra@outlook.com"},{"1", "Pamela","Nohra","71893615","Admin","Engineer","pamela.nohra@outlook.com"}, {"1", "Pamela","Nohra","71893615","Admin","Engineer","pamela.nohra@outlook.com"},{"1", "Pamela","Nohra","71893615","Admin","Engineer","pamela.nohra@outlook.com"},{"1", "Pamela","Nohra","71893615","Admin","Engineer","pamela.nohra@outlook.com"}, {"1", "Pamela","Nohra","71893615","Admin","Engineer","pamela.nohra@outlook.com"},{"1", "Pamela","Nohra","71893615","Admin","Engineer","pamela.nohra@outlook.comlast"}};


    for (int i = 0; i < users.length; i++) {
        TableRow tableRow = new TableRow(this);
        tableRow.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));

        for (int j = 0; j < users[i].length; j++) {
            TextView textView = new TextView(this);
            textView.setText(users[i][j]);
            tableRow.addView(textView);
        }

        tableLayout.addView(tableRow);
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

        List<DataItem> userTypeS = new ArrayList<>();
        userTypeS.add(new DataItem(1, "Admin"));
        userTypeS.add(new DataItem(2, "Guest"));

        ArrayAdapter<DataItem> userTypeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, userTypeS);
        userTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userType.setAdapter(userTypeAdapter);





        List<DataItem> userRoleS = new ArrayList<>();
        userRoleS.add(new DataItem(1, "Engineer"));
        userRoleS.add(new DataItem(2, "Doctor"));

        ArrayAdapter<DataItem> userRoleAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, userRoleS);
        userRoleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        role.setAdapter(userRoleAdapter);




        Button actionButton = popupView.findViewById(R.id.actionBtn);
        actionButton.setText(type);
        if (type == "ADD") {
            idLabel.setVisibility(GONE);
            userID.setVisibility(GONE);
        } else if (type == "EDIT" || type == "DELETE") {
            for (int i = 0; i < userTypeAdapter.getCount(); i++) {
                DataItem item = userTypeAdapter.getItem(i);
                if (item.getValue() == user.getType()) {
                    userType.setSelection(i);
                    break;
                }
            }
            for (int i = 0; i < userRoleAdapter.getCount(); i++) {
                DataItem item = userRoleAdapter.getItem(i);
                if (item.getValue() == user.getRole()) {
                    role.setSelection(i);
                    break;
                }
            }

            userID.setText(user.getId().toString());
            firstName.setText(user.getFirstName());
            lastName.setText(user.getLastName());
            email.setText(user.getEmail());
            phoneNumber.setText(user.getPhoneNumber());

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
                popupWindow.dismiss();
            }
        });
    }
}

