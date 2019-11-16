package com.worldtechpoints.bcsknowledge;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.worldtechpoints.bcsknowledge.Question.SubmitQuestionActivity;
import com.worldtechpoints.bcsknowledge.home.JobSubmitActivity;
import com.worldtechpoints.bcsknowledge.mcqTest.QuizSubmitActivity;

public class SubmitCategoryActivity extends AppCompatActivity {

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() ==R.id.home)
        {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private Button mJobSubmit,mQuestionSubmit,mQuizSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_catagory);

        Toolbar toolbar = findViewById(R.id.submitCategoryToolbar_id);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setTitle("Submit Category");

        mJobSubmit = findViewById(R.id.mJobUpdateSubmit_id);
        mQuestionSubmit = findViewById(R.id.mQuestionSubmit_id);
        mQuizSubmit = findViewById(R.id.mQuizSubmit_id);

        mJobSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SubmitCategoryActivity.this, JobSubmitActivity.class);
                startActivity(intent);

            }
        });

        mQuestionSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SubmitCategoryActivity.this, SubmitQuestionActivity.class);
                startActivity(intent);


            }
        });

        mQuizSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SubmitCategoryActivity.this, QuizSubmitActivity.class);
                startActivity(intent);

            }
        });




    }
}
