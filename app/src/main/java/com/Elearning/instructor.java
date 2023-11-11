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
import android.widget.TextView;
import android.widget.Toast;

import com.Elearning.Dbhelper.dbhelper;
import com.Elearning.models.InstructorModel;
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


public class instructor extends AppCompatActivity {

    dbhelper dbhelper;
    RecyclerView recyclerView;
    Button addcourse;
    Button quiz;
    TextView textView,textView1;
   ImageView logoutIcon;
    String userid;
    private AppBarConfiguration mAppBarConfiguration;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instructor);
        addcourse=findViewById(R.id.addcourse);
        logoutIcon=findViewById(R.id.logoutIcon);
        textView=findViewById(R.id.textView);
        textView1=findViewById(R.id.textView1);
        quiz=findViewById(R.id.quiz);
        Intent intent = getIntent();
        String fullName = intent.getStringExtra("FULL_NAME");

        TextView fullNameTextView = findViewById(R.id.textView);
        fullNameTextView.setText(fullName);

        recyclerView = findViewById(R.id.recyclerView); // Adjust the ID as needed
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        SharedPreferences sharedpreferences = getSharedPreferences("my_preferences", MODE_PRIVATE);
        userid = sharedpreferences.getString("userid","");
        dbhelper dbHelper = new dbhelper(this);
        List<InstructorModel> instructors = dbHelper.getcorlist(userid);

        if (instructors.size() > 0) {
            InstructorAdapter instructorAdapter = new InstructorAdapter(instructors, this);
            recyclerView.setAdapter(instructorAdapter);
        } else {
            Toast.makeText(this, " ", Toast.LENGTH_SHORT).show();
        }

        addcourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(instructor.this, addcourse.class);
                startActivity(intent2);

            }

        });

        logoutIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedpreferences = getSharedPreferences("my_preferences", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.clear();
                editor.apply();

                Intent intent34 = new Intent(instructor.this, login.class);
                startActivity(intent34);
                finish();
            }
        });



    }
}



