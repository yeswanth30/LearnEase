package com.Elearning;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.recyclerview.widget.RecyclerView;

import com.Elearning.Dbhelper.dbhelper;

public class student123 extends AppCompatActivity {


    TextView myTextView,myTextView1,myTextView2;
    Button myButton;
    dbhelper dbhelpers;
    private AppBarConfiguration mAppBarConfiguration;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student123);

        myTextView = findViewById(R.id.myTextView);

        myTextView2 = findViewById(R.id.myTextView2);

        dbhelpers = new dbhelper(this);

        int sumOfMarks = dbhelpers.getSumOfMarksForUser();

        TextView sumTextView = findViewById(R.id.myTextView2);

      sumTextView.setText("Sum of Marks: " + sumOfMarks);

//        int corseid = getIntent().getIntExtra("courseid", -1);
//
//        Toast.makeText(student123.this, "id" + corseid, Toast.LENGTH_LONG).show();
//
//
//        return corseid;
    }
}
