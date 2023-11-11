package com.Elearning;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.Elearning.models.InstructorModel;

import java.util.List;

public class InstructorAdapter extends RecyclerView.Adapter<InstructorAdapter.ViewHolder> {
    List<InstructorModel> instructors;
    Context context;

    public InstructorAdapter(List<InstructorModel> instructors, Context context) {
        this.instructors = instructors;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.instructorlist, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull InstructorAdapter.ViewHolder holder, int position) {
        final InstructorModel instructor = instructors.get(position);
        Bitmap bitmapImage = BitmapFactory.decodeByteArray(instructor.getImageResource(), 0, instructor.getImageResource().length);
        holder.courseName.setText(instructor.getCourseName());
        holder.courseDes.setText(instructor.getCourseDes());
        holder.coursePrice.setText(instructor.getCoursePrice());
        holder.courseImage.setImageBitmap(bitmapImage);
         holder.instid = String.valueOf(instructor.getId());


        holder.quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedpreferences = context.getSharedPreferences("my_preferences", MODE_PRIVATE);
                SharedPreferences.Editor myedit = sharedpreferences.edit();
                myedit.putString("coursesid", String.valueOf(instructor.getId()));
                myedit.commit();
                Intent intent = new Intent(context, quiz.class);
//                Bundle bundle = new Bundle();
//                bundle.putInt("instructorId", instructor.getId());
//                intent.putExtras(bundle);

                context.startActivity(intent);

            }
        });
        holder.addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // You can implement an action when clicking the instructor's profile
                // For example, navigate to the instructor's profile page
//                Intent intent = new Intent(context, quiz.class);
//                Bundle bundle = new Bundle();
//                bundle.putInt("instructorId", instructor.getId());
//                intent.putExtras(bundle);
//                context.startActivity(intent);

            }
        });


    }
    @Override
    public int getItemCount() {
        return instructors.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView courseName,courseDes,coursePrice;
        ImageView courseImage;
        Button addToCartButton,quiz;
       public String instid;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            courseName = itemView.findViewById(R.id.courseName);
            courseImage = itemView.findViewById(R.id.courseImage);
            courseDes = itemView.findViewById(R.id.courseDes);
            coursePrice = itemView.findViewById(R.id.coursePrice);
            quiz=itemView.findViewById(R.id.quiz);
            addToCartButton = itemView.findViewById(R.id.addToCartButton);
        }
    }
}

