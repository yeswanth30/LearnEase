package com.Elearning;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.Elearning.Adapters.QuizAdapter;
import com.Elearning.Dbhelper.dbhelper;
import com.Elearning.models.InstructorModel;
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


public class quiz extends AppCompatActivity {


    RecyclerView recyclerView;
    Button createQuizButton;
    Button AddQuizButton;

    String userid,id;
    private AppBarConfiguration mAppBarConfiguration;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz);
        AddQuizButton = findViewById(R.id.AddQuizButton);





        AddQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent12 = new Intent(quiz.this, addquestion.class);

                startActivity(intent12);


            }

        });

        recyclerView = findViewById(R.id.recyclerView1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        SharedPreferences sharedpreferences = getSharedPreferences("my_preferences", MODE_PRIVATE);
        userid = sharedpreferences.getString("userid", "");
        id = sharedpreferences.getString("coursesid", "");
        dbhelper dbHelper = new dbhelper(this);
        List<QuizModel> quizModels = dbHelper.getQuizQuestions(userid,id);

        if (quizModels.size() > 0) {
            QuizAdapter QuizAdapters = new QuizAdapter(quizModels, this);
            recyclerView.setAdapter(QuizAdapters);
        } else {
            Toast.makeText(this, "There are no question in the database", Toast.LENGTH_SHORT).show();
        }


//        createQuizButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent2 = new Intent(quiz.this, addquestion.class);
//                startActivity(intent2);
//            }
//
//        });

    }
}