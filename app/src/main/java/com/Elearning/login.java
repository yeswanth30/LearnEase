package com.Elearning;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import com.Elearning.Dbhelper.dbhelper;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.AlertDialog;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;


import java.util.List;

public class login extends AppCompatActivity {
    EditText usernameTextView;
    EditText passwordEditText;
    Button login;
    dbhelper dbhelper;
    TextView textView1,textView2;
    String name1,password1;
    RadioButton radioInstructor,radioStudent;
    private AppBarConfiguration mAppBarConfiguration;
    String selectedRole = "";
    int loggedInStudent = 0;
    int loggedInInstructor = 0;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);


        textView1 =findViewById(R.id.textView1);
        textView2 =findViewById(R.id.textView2);
        radioInstructor=findViewById(R.id.radioInstructor);
        radioStudent=findViewById(R.id.radioStudent);
        usernameTextView =findViewById(R.id.usernameTextView);
        passwordEditText =findViewById(R.id.passwordEditText);
        login=findViewById(R.id.login);

        dbhelper = new dbhelper(login.this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name1 = usernameTextView.getText().toString();
                password1 = passwordEditText.getText().toString();





                if (name1.isEmpty() || password1.isEmpty()) {
                    Toast.makeText(login.this, "Please Enter email and Password", Toast.LENGTH_LONG).show();
                } else {

                    if (radioStudent.isChecked()) {
                        selectedRole = "Student";
                    } else if (radioInstructor.isChecked()) {
                        selectedRole = "Instructor";
                    } else {
                        Toast.makeText(login.this, "Please select a role (Student or Instructor)", Toast.LENGTH_LONG).show();

                    }

                    String data = dbhelper.login(name1, password1, selectedRole);
                    if (data.isEmpty()) {
                        Toast.makeText(login.this, "Username not found. Please sign up", Toast.LENGTH_LONG).show();
                    }

                    else {
                        String fullName = dbhelper.getUserTypeFromDatabase(data); // Fetch the full name using user ID

                        if (selectedRole.equals("Student")) {
                            Intent studentIntent = new Intent(login.this, student.class);
                            studentIntent.putExtra("FULL_NAME", fullName); // Pass the full name to the student activity
                            startActivity(studentIntent);
                        } else if (selectedRole.equals("Instructor")) {
                            Intent instructorIntent = new Intent(login.this, instructor.class);
                            instructorIntent.putExtra("FULL_NAME", fullName); // Pass the full name to the instructor activity
                            startActivity(instructorIntent);
                        }


                        else {
                            SharedPreferences sharedPreferences = getSharedPreferences("my_preferences", MODE_PRIVATE);
                            SharedPreferences.Editor myedit = sharedPreferences.edit();
                            myedit.putString("userid", data);
                            myedit.commit();
                            Toast.makeText(login.this, "Successfully login" + data, Toast.LENGTH_LONG).show();
                            //Log.e("data of login",data);
                            if (selectedRole.equals("Student")) {
                                loggedInStudent = 1;
                                myedit.putInt("loggedInStudent", loggedInStudent);
                                myedit.putInt("loggedInInstructor", loggedInStudent);
                                myedit.apply();
                                Intent studentIntent = new Intent(login.this, student.class);
                                startActivity(studentIntent);
                            } else if (selectedRole.equals("Instructor")) {
                                loggedInInstructor = 1;
                                myedit.putInt("loggedInStudent", loggedInStudent);
                                myedit.putInt("loggedInInstructor", loggedInInstructor);
                                myedit.apply();
                                Intent instructorIntent = new Intent(login.this, instructor.class);
                                startActivity(instructorIntent);
                            } else {
                                Toast.makeText(login.this, "Not Found", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                }
            }
        });


        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(login.this, register.class);
                startActivity(intent2);

            }

        });

        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(login.this, register.class);
                startActivity(intent3);

            }

        });

    }


}

