package com.Elearning;


import static android.content.Context.MODE_PRIVATE;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.Elearning.Dbhelper.dbhelper;


import com.Elearning.models.CourseModel;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder> {
    List<CourseModel> courses;
    Context context;
    dbhelper db;
    String userid;

    public CourseAdapter(List<CourseModel> courses, Context context) {
        this.courses = courses;
        this.context = context;
        db = new dbhelper(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.courseslist, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CourseAdapter.ViewHolder holder, int position) {
        final CourseModel course = courses.get(position);
        Bitmap bitmapImage = BitmapFactory.decodeByteArray(course.getImageResource(), 0, course.getImageResource().length);
        holder.courseName.setText(course.getCourseName());
        holder.courseImage.setImageBitmap(bitmapImage);

        holder.addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, coursedetails.class);
                Bundle bundle = new Bundle();
                bundle.putInt("courseid",course.getId());
                intent.putExtras(bundle);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView courseName;
        ImageView courseImage;
        Button addToCartButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            courseName = itemView.findViewById(R.id.courseName);
            courseImage = itemView.findViewById(R.id.courseImage);

            addToCartButton = itemView.findViewById(R.id.addToCartButton);
        }
    }
}
