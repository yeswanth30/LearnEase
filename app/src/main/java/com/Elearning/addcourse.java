package com.Elearning;

import android.annotation.SuppressLint;
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
import android.widget.TextView;
import android.widget.Toast;
import android.window.OnBackInvokedDispatcher;


import com.Elearning.Dbhelper.dbhelper;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
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


public class  addcourse extends AppCompatActivity {

    EditText first,first1,first2;
    Button submit;
    ImageView imageview1;
    String userid;
    Button SelectImage;
    private   Uri selectedImage;
    String imagepath,StringName;

    private final int GALLERY_REQ_CODE = 1000;
    private Bitmap imageTostore;
    private dbhelper dbHelper;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addcourse);

        first = findViewById(R.id.first);
        first1 = findViewById(R.id.first1);
        first2 = findViewById(R.id.first2);


        imageview1 = findViewById(R.id.imageview1);
        SelectImage = findViewById(R.id.SelectImage);
        submit = findViewById(R.id.submit);

        dbHelper = new dbhelper(this);

        SelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the image gallery
                Intent iGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(iGallery, GALLERY_REQ_CODE);

            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String courseName = first.getText().toString();
                String courseDescription = first1.getText().toString();
                String coursePrice = first2.getText().toString();
                SharedPreferences sharedpreferences = getSharedPreferences("my_preferences", MODE_PRIVATE);
                userid = sharedpreferences.getString("userid","");
                long times = System.currentTimeMillis();
                SimpleDateFormat dates = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault());
                String formats = dates.format(new Date(times));

                long result = dbHelper.insertCourse(courseName,imageTostore, courseDescription,coursePrice,userid , formats);
                if (result != -1) {
                    Toast.makeText(addcourse.this, "Data added to the database", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(addcourse.this, "Error inserting data", Toast.LENGTH_LONG).show();
                }

            }
        });


        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                Toast.makeText(addcourse.this, "Back button pressed ", Toast.LENGTH_SHORT).show();
                finish();
            }
        };
        getOnBackPressedDispatcher().addCallback(addcourse.this, callback);

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == GALLERY_REQ_CODE) {
            selectedImage = data.getData();
            imageview1.setImageURI(selectedImage);
            try {
                imageTostore = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }



 }