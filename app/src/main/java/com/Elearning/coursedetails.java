package com.Elearning;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.Elearning.Dbhelper.dbhelper;
import com.Elearning.models.CourseModel;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.activity.OnBackPressedCallback;
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


public class  coursedetails extends AppCompatActivity {

    TextView textView1, textView2, textView3;
    Button buy;
    EditText status;
    ImageView imageView;
    Integer id;
    String userid,coursesid;
    private dbhelper dbHelper;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coursedetails);
        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        imageView = findViewById(R.id.imageView);
        status=findViewById(R.id.status);
        buy = findViewById(R.id.buy);
        dbHelper = new dbhelper(this);


        Intent intent = getIntent();
        if (intent != null) {
            id = intent.getIntExtra("courseid", 0);
        }

        int position = 0;
        List<CourseModel> course = dbHelper.getcorusedetail(id);
        CourseModel courses = course.get(position);
        Bitmap bitmapImage = BitmapFactory.decodeByteArray(courses.getImageResource(), 0, courses.getImageResource().length);

        String name = courses.getCourseName().toString();
        textView1.setText(name);
        String coursedetails = courses.getCoursedes().toString();
        textView2.setText(coursedetails);
        Integer price = courses.getPrice();
        textView3.setText(String.valueOf(price));
        imageView.setImageBitmap(bitmapImage);


        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String status = "1";
                SharedPreferences sharedpreferences = getSharedPreferences("my_preferences", MODE_PRIVATE);
                userid = sharedpreferences.getString("userid", "");
                // coursesid = sharedpreferences.getString("coursesid", "");

                long times = System.currentTimeMillis();
                SimpleDateFormat dates = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault());
                String formats = dates.format(new Date(times));

                long result = dbHelper.insterenrol(userid, id, status, formats);
                if (result != -1) {
                    if (result == 0) {
                        Toast.makeText(coursedetails.this, "Already Purchased", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(coursedetails.this, "Purchased Successfully", Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(coursedetails.this, "Already Purchased", Toast.LENGTH_LONG).show();
                }

                Intent intent = new Intent(coursedetails.this, studentquiz.class);
                Bundle bundle = new Bundle();
                bundle.putInt("courseid", id);
                intent.putExtras(bundle);

                startActivity(intent);

                OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
                    @Override
                    public void handleOnBackPressed() {
                        Toast.makeText(coursedetails.this, "Back button pressed ", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                };
                getOnBackPressedDispatcher().addCallback(coursedetails.this, callback);
            }
        });
    }
}

