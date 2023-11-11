package com.Elearning;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.Elearning.Dbhelper.dbhelper;
import com.Elearning.models.CourseModel;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class student extends AppCompatActivity {

    dbhelper dbhelper;
    TableRow table1;
    TextView textView,textView1;
    RecyclerView recyclerView;
    ImageView logoutIcon;
    private AppBarConfiguration mAppBarConfiguration;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student);
        logoutIcon=findViewById(R.id.logoutIcon);
        textView1=findViewById(R.id.textView1);
        Intent intent = getIntent();
        String fullName = intent.getStringExtra("FULL_NAME");

        TextView fullNameTextView = findViewById(R.id.textView);
        fullNameTextView.setText(fullName);



        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        dbhelper dbHelper = new dbhelper(this);
        List<CourseModel> courses = dbHelper.getCoursesList();

        if (courses.size() > 0) {
            CourseAdapter courseAdapter = new CourseAdapter(courses, this);
            recyclerView.setAdapter(courseAdapter);
        } else {
            Toast.makeText(this, "There are no courses in the database", Toast.LENGTH_SHORT).show();
        }


        logoutIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedpreferences = getSharedPreferences("my_preferences", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.clear();
                editor.apply();

                Intent intent43 = new Intent(student.this, login.class);
                startActivity(intent43);
                finish();
            }
        });


    }
}

