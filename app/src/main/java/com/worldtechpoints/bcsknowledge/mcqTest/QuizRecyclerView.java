package com.worldtechpoints.bcsknowledge.mcqTest;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class QuizRecyclerView extends RecyclerView.Adapter<QuizRecyclerView.ViewHolder> {


    private Context context;
    private List<QuizSubmit>quizList;

    public QuizRecyclerView(Context context, List<QuizSubmit> quizList) {
        this.context = context;
        this.quizList = quizList;
    }

    @NonNull
    @Override
    public QuizRecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull QuizRecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return quizList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        public ViewHolder(@NonNull View itemView) {
            super(itemView);


        }
    }
}
