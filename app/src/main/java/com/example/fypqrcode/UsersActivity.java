package com.example.fypqrcode;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class UsersActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.users_layout);
        populateTable();
    //logout
    Button logoutB = findViewById(R.id.logoutBtn);
        logoutB.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(UsersActivity.this, LoginActivity.class);
            startActivity(i);
            finish();            }
    });

    }
//    private void populateTable() {
//
//        TableLayout headerT = findViewById(R.id.usersHeaderTable);
//        TableRow headerR = (TableRow) headerT.getChildAt(0);
//        TableLayout tableLayout = findViewById(R.id.userTable);
//        String[][] users = {{"1", "Pamela","Nohra","71893615","Admin","Engineer","pamela.nohra@outlook.com"}};
//        // Iterate through the data and create rows and cells
//        for (int i = 0; i < users.length; i++) {
//            TableRow tableRow = new TableRow(this);
//            tableRow.setLayoutParams(new TableRow.LayoutParams(
//                    TableRow.LayoutParams.MATCH_PARENT,
//                    TableRow.LayoutParams.WRAP_CONTENT));
//
//            for (int j = 0; j < users[i].length; j++) {
//                TextView textView = new TextView(this);
//                textView.setText(users[i][j]);
//                View headerView = headerR.getChildAt(j);
//                textView.setWidth(headerView.getWidth());
//                tableRow.addView(textView);
//            }
//
//            tableLayout.addView(tableRow);
//        }
//    }
private void populateTable() {
    TableLayout headerT = findViewById(R.id.usersHeaderTable);
    TableRow headerR = (TableRow) headerT.getChildAt(0);
    TableLayout tableLayout = findViewById(R.id.userTable);
    String[][] users = {{"1", "Pamela", "Nohra", "71893615", "Admin", "Engineer", "pamela.nohra@outlook.com"},{"1", "Pamela", "Nohra", "71893615", "Admin", "Engineer", "pamela.nohra@outlook.com"}, {"1", "Pamela","Nohra","71893615","Admin","Engineer","pamela.nohra@outlook.com"},{"1", "Pamela","Nohra","71893615","Admin","Engineer","pamela.nohra@outlook.com"},{"1", "Pamela","Nohra","71893615","Admin","Engineer","pamela.nohra@outlook.com"}, {"1", "Pamela","Nohra","71893615","Admin","Engineer","pamela.nohra@outlook.com"},{"1", "Pamela","Nohra","71893615","Admin","Engineer","pamela.nohra@outlook.com"},{"1", "Pamela","Nohra","71893615","Admin","Engineer","pamela.nohra@outlook.com"}, {"1", "Pamela","Nohra","71893615","Admin","Engineer","pamela.nohra@outlook.com"},{"1", "Pamela","Nohra","71893615","Admin","Engineer","pamela.nohra@outlook.com"},{"1", "Pamela","Nohra","71893615","Admin","Engineer","pamela.nohra@outlook.com"}, {"1", "Pamela","Nohra","71893615","Admin","Engineer","pamela.nohra@outlook.com"},{"1", "Pamela","Nohra","71893615","Admin","Engineer","pamela.nohra@outlook.com"},{"1", "Pamela","Nohra","71893615","Admin","Engineer","pamela.nohra@outlook.com"}, {"1", "Pamela","Nohra","71893615","Admin","Engineer","pamela.nohra@outlook.com"},{"1", "Pamela","Nohra","71893615","Admin","Engineer","pamela.nohra@outlook.com"},{"1", "Pamela","Nohra","71893615","Admin","Engineer","pamela.nohra@outlook.com"}, {"1", "Pamela","Nohra","71893615","Admin","Engineer","pamela.nohra@outlook.com"},{"1", "Pamela","Nohra","71893615","Admin","Engineer","pamela.nohra@outlook.com"},{"1", "Pamela","Nohra","71893615","Admin","Engineer","pamela.nohra@outlook.com"}, {"1", "Pamela","Nohra","71893615","Admin","Engineer","pamela.nohra@outlook.com"},{"1", "Pamela","Nohra","71893615","Admin","Engineer","pamela.nohra@outlook.com"},{"1", "Pamela","Nohra","71893615","Admin","Engineer","pamela.nohra@outlook.com"}, {"1", "Pamela","Nohra","71893615","Admin","Engineer","pamela.nohra@outlook.com"},{"1", "Pamela","Nohra","71893615","Admin","Engineer","pamela.nohra@outlook.com"},{"1", "Pamela","Nohra","71893615","Admin","Engineer","pamela.nohra@outlook.com"}, {"1", "Pamela","Nohra","71893615","Admin","Engineer","pamela.nohra@outlook.com"},{"1", "Pamela","Nohra","71893615","Admin","Engineer","pamela.nohra@outlook.com"},{"1", "Pamela","Nohra","71893615","Admin","Engineer","pamela.nohra@outlook.com"}, {"1", "Pamela","Nohra","71893615","Admin","Engineer","pamela.nohra@outlook.com"},{"1", "Pamela","Nohra","71893615","Admin","Engineer","pamela.nohra@outlook.com"},{"1", "Pamela","Nohra","71893615","Admin","Engineer","pamela.nohra@outlook.com"}, {"1", "Pamela","Nohra","71893615","Admin","Engineer","pamela.nohra@outlook.com"},{"1", "Pamela","Nohra","71893615","Admin","Engineer","pamela.nohra@outlook.com"},{"1", "Pamela","Nohra","71893615","Admin","Engineer","pamela.nohra@outlook.com"}, {"1", "Pamela","Nohra","71893615","Admin","Engineer","pamela.nohra@outlook.com"},{"1", "Pamela","Nohra","71893615","Admin","Engineer","pamela.nohra@outlook.com"},{"1", "Pamela","Nohra","71893615","Admin","Engineer","pamela.nohra@outlook.com"}, {"1", "Pamela","Nohra","71893615","Admin","Engineer","pamela.nohra@outlook.com"},{"1", "Pamela","Nohra","71893615","Admin","Engineer","pamela.nohra@outlook.com"},{"1", "Pamela","Nohra","71893615","Admin","Engineer","pamela.nohra@outlook.com"}, {"1", "Pamela","Nohra","71893615","Admin","Engineer","pamela.nohra@outlook.com"},{"1", "Pamela","Nohra","71893615","Admin","Engineer","pamela.nohra@outlook.comlast"}};


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

}