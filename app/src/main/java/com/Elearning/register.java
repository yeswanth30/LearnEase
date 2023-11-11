package com.Elearning;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.Elearning.Dbhelper.dbhelper;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class register extends AppCompatActivity {
    Button registerButton;
    TextView fULLNameEditText;
    TextView RoleEditText;
    String selectedRole = "";
    TextView emailEditText;
    TextView  passwordEditText;
    RadioButton radioStudent,radioInstructor;
    dbhelper dbhelper;
    private AppBarConfiguration mAppBarConfiguration;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        registerButton=findViewById(R.id.registerButton);
        fULLNameEditText=findViewById(R.id.fULLNameEditText);
        radioStudent=findViewById(R.id.radioStudent);
        radioInstructor=findViewById(R.id.radioInstructor);
        emailEditText=findViewById(R.id.emailEditText);
        passwordEditText=findViewById(R.id.passwordEditText);


        dbhelper = new dbhelper(register.this);


        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long times = System.currentTimeMillis();
                SimpleDateFormat dates = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault());
                String formats = dates.format(new Date(times));



                if (radioStudent.isChecked()) {
                    selectedRole = "Student";
                } else if (radioInstructor.isChecked()) {
                    selectedRole = "Instructor";
                } else {
                    Toast.makeText(register.this, "Please select a role (Student or Instructor)", Toast.LENGTH_LONG).show();

                }
                dbhelper.register(
                        fULLNameEditText.getText().toString(),
                        emailEditText.getText().toString(),
                        passwordEditText.getText().toString(),
                        selectedRole,
                        formats);

                Toast.makeText(register.this,"Succesfully Registered",Toast.LENGTH_LONG).show();
                Intent intent4 = new Intent(register.this, login.class);
                startActivity(intent4);

            }

        });



    }
}