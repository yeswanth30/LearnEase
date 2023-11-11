package com.Elearning;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.Elearning.Dbhelper.dbhelper;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class  addquestion extends AppCompatActivity {

    EditText questionTextView;
    EditText option1RadioButton;
    EditText option2RadioButton;
    EditText option3RadioButton;
    EditText correctoption;
    EditText marks;

    EditText option4RadioButton;
    RadioGroup optionRadioGroup;
    Button submit;
    String userid,coursesid;

    private dbhelper dbHelper;


    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addquestion);


        questionTextView = findViewById(R.id.questionTextView);
        option1RadioButton = findViewById(R.id.option1RadioButton);
        option2RadioButton = findViewById(R.id.option2RadioButton);
        option3RadioButton = findViewById(R.id.option3RadioButton);
        option4RadioButton = findViewById(R.id.option4RadioButton);
        correctoption = findViewById(R.id.correctoption);
        marks=findViewById(R.id.marks);
        optionRadioGroup = findViewById(R.id.optionRadioGroup);
        submit = findViewById(R.id.submit);

        dbHelper = new dbhelper(this);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String questionText = questionTextView.getText().toString();
                String option1 = option1RadioButton.getText().toString();
                String option2 = option2RadioButton.getText().toString();
                String option3 = option3RadioButton.getText().toString();
                String option4 = option4RadioButton.getText().toString();

                @SuppressLint("WrongViewCast")
               EditText correctOptionEditText = findViewById(R.id.correctoption);
                String correctoption = correctOptionEditText.getText().toString();
                EditText marksEditText = findViewById(R.id.marks);
                String marks = marksEditText.getText().toString();


                SharedPreferences sharedpreferences = getSharedPreferences("my_preferences", MODE_PRIVATE);
                userid = sharedpreferences.getString("userid", "");
                coursesid = sharedpreferences.getString("coursesid", "");



                long times = System.currentTimeMillis();
                SimpleDateFormat dates = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault());
                String formats = dates.format(new Date(times));
                long result = dbHelper.insertQuiz(questionText, option1, option2, option3, option4,correctoption,marks, userid,coursesid, formats);
                if (result != -1) {
                    Toast.makeText(addquestion.this, "Data added to the database", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(addquestion.this, "Error inserting data", Toast.LENGTH_LONG).show();
                }

            }
        });

//        public void onBackPressed() {
//            new AlertDialog.Builder(this)
//                    .setMessage("Do you want to go back?")
//                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            finish();
//                        }
//                    })
//                    .setNegativeButton("No", null)
//                    .show();
//        }


    }
}
