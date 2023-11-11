package com.Elearning;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;


public class contentmain extends AppCompatActivity {
    ImageView myImageView;
    String userid;

    String selectedRole = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        myImageView=findViewById(R.id.myImageView);



        myImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedpreferences = getSharedPreferences("my_preferences", MODE_PRIVATE);
                userid = sharedpreferences.getString("userid","");
//                SharedPreferences sharedpreferencess = getSharedPreferences("loginPrefs", Context.MODE_PRIVATE);
//                SharedPreferences.Editor editor = sharedpreferences.edit();
                int loggedInStudent = sharedpreferences.getInt("loggedInStudent",0);
                int loggedInInstructor =sharedpreferences.getInt("loggedInInstructor",0);
//                editor.putInt("loggedInStudent", loggedInStudent);
//                editor.putInt("loggedInInstructor", loggedInInstructor);
//                editor.apply();



//                if (userid.isEmpty()) {
//                    Intent intent1 = new Intent(contentmain.this,student.class);
//                    startActivity(intent1);
//                } else {
//                    Intent intent2 = new Intent(contentmain.this, login.class);
//                    startActivity(intent2);
//                }

                if (loggedInStudent == 1) {
                    Intent studentIntent = new Intent(contentmain.this, student.class);
                    startActivity(studentIntent);
                } else if (loggedInInstructor == 1) {
                    Intent instructorIntent = new Intent(contentmain.this, instructor.class);
                    startActivity(instructorIntent);
                } else if (userid.isEmpty()) {
                    Intent intent2 = new Intent(contentmain.this, login.class);
                    startActivity(intent2);
                } else {

                    Intent intent2 = new Intent(contentmain.this, login.class);
                    startActivity(intent2);
                }





            }

        });

    }
}
