package com.Elearning.Adapters;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Elearning.R;
import com.Elearning.models.InstructorModel;
import com.Elearning.models.QuizModel;

import java.util.List;


public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.ViewHolder> {
    private List<QuizModel> quizList;
    private Context context;

    public QuizAdapter(List<QuizModel> quizList, Context context) {
        this.quizList = quizList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.quizlist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        QuizModel quiz = quizList.get(position);
        holder.questionTextView.setText(quiz.getQuestion());
        holder.option1RadioButton.setText(quiz.getOption1());
        holder.option2RadioButton.setText(quiz.getOption2());
        holder.option3RadioButton.setText(quiz.getOption3());
        holder.option4RadioButton.setText(quiz.getOption4());

        // Handle user selection and quiz logic here
    }

    @Override
    public int getItemCount() {
        return quizList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView questionTextView;
        RadioButton option1RadioButton;
        RadioButton option2RadioButton;
        RadioButton option3RadioButton;
        RadioButton option4RadioButton;
        RadioGroup optionRadioGroup;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            questionTextView = itemView.findViewById(R.id.questionTextView);
            option1RadioButton = itemView.findViewById(R.id.option1RadioButton);
            option2RadioButton = itemView.findViewById(R.id.option2RadioButton);
            option3RadioButton = itemView.findViewById(R.id.option3RadioButton);
            option4RadioButton = itemView.findViewById(R.id.option4RadioButton);
            optionRadioGroup = itemView.findViewById(R.id.optionRadioGroup);

        }
    }
}