package com.Elearning;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.Elearning.Adapters.QuizAdapter;
import com.Elearning.Adapters.studentquizadapter;
import com.Elearning.Dbhelper.dbhelper;
import com.Elearning.models.InstructorModel;
import com.Elearning.models.QuestionModel;
import com.Elearning.models.QuizModel;
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


public class studentquiz extends AppCompatActivity {


    RecyclerView recyclerView;
    Button submitQuizButton;

    String userid;
    int id;
    private AppBarConfiguration mAppBarConfiguration;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.studentquiz);
        submitQuizButton = findViewById(R.id.submitQuizButton);
        dbhelper dbHelper = new dbhelper(this);


        int corseid = getIntent().getIntExtra("courseid", -1);

        Toast.makeText(studentquiz.this, "id" + corseid, Toast.LENGTH_LONG).show();


        recyclerView = findViewById(R.id.recyclerView2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);


        List<QuestionModel> QuestionModel = dbHelper.getstudentquestions(corseid);

        if (QuestionModel.size() > 0) {
            studentquizadapter studentquizadapters = new studentquizadapter(QuestionModel, studentquiz.this);
            recyclerView.setAdapter(studentquizadapters);
        } else {
            Toast.makeText(this, "There are no question in the database", Toast.LENGTH_SHORT).show();
        }

        submitQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedpreferences = getSharedPreferences("my_preferences", MODE_PRIVATE);
                userid = sharedpreferences.getString("userid", "");


                long times = System.currentTimeMillis();
                SimpleDateFormat dates = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault());
                String formats = dates.format(new Date(times));

                long result = dbHelper.insertsubmitquiz(userid, corseid, formats);

                if (result != -1) {
                    if (result == 0) {
                        Toast.makeText(studentquiz.this, "Quiz Submitted", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(studentquiz.this, "Quiz ", Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(studentquiz.this, "Quiz Submitted", Toast.LENGTH_LONG).show();
                }
                Intent intent = new Intent(studentquiz.this, student123.class);
                Bundle bundle = new Bundle();
                bundle.putInt("courseid", id);
                intent.putExtras(bundle);

                startActivity(intent);
            }
        });
    }
}