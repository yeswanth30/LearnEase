package com.Elearning.Adapters;
import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Elearning.Dbhelper.dbhelper;
import com.Elearning.R;
import com.Elearning.models.QuestionModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class studentquizadapter extends RecyclerView.Adapter<studentquizadapter.ViewHolder> {
    private List<QuestionModel> quizList;
    private Context context;
    dbhelper dbhelpers;
    int flag=0;
    int totalmarks=0;

  
    public studentquizadapter(List<QuestionModel> quizList,Context context) {
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
        QuestionModel quiz = quizList.get(position);
        holder.questionTextView.setText(quiz.getQuestion());
        holder.option1RadioButton.setText(quiz.getOption1());
        holder.option2RadioButton.setText(quiz.getOption2());
        holder.option3RadioButton.setText(quiz.getOption3());
        holder.option4RadioButton.setText(quiz.getOption4());

        // Handle user selection and quiz logic here

        holder.optionRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                holder.radiobuttonclicked = group.findViewById(checkedId);
                    if(holder.radiobuttonclicked != null){
                        String option = holder.radiobuttonclicked.getText().toString();
                        long times = System.currentTimeMillis();
                        SimpleDateFormat dates = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault());
                        String formats = dates.format(new Date(times));
                        SharedPreferences sharedpreferences = context.getSharedPreferences("my_preferences", MODE_PRIVATE);
                       String userid = sharedpreferences.getString("userid", "");
                        dbhelpers.insertquizdata(quiz.getQuizdetailsid(),option,userid,formats);
//                      List<QuestionModel> data =  dbhelpers.checkoption(quiz.getQuizdetailsid());
//                        QuestionModel quizdata = data.get(position);
//                      if(option == quizdata.getCorrectoptionvalue()){
//                          totalmarks = totalmarks+quizdata.getMarks();
//
//                      }

                    }
            }


        });

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

        RadioButton radiobuttonclicked;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            questionTextView = itemView.findViewById(R.id.questionTextView);
            option1RadioButton = itemView.findViewById(R.id.option1RadioButton);
            option2RadioButton = itemView.findViewById(R.id.option2RadioButton);
            option3RadioButton = itemView.findViewById(R.id.option3RadioButton);
            option4RadioButton = itemView.findViewById(R.id.option4RadioButton);
            optionRadioGroup = itemView.findViewById(R.id.optionRadioGroup);
           dbhelpers = new dbhelper(context);

        }
    }
}
