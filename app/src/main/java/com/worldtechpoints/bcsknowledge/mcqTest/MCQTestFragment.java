package com.worldtechpoints.bcsknowledge.mcqTest;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.worldtechpoints.bcsknowledge.Question.ShowQuestionActivity;
import com.worldtechpoints.bcsknowledge.R;

public class MCQTestFragment extends Fragment {

    Button englishButton,mathButton,modelTestButton;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_mcq_test, container, false);

        englishButton = root.findViewById(R.id.quizEnglish_id);
        mathButton = root.findViewById(R.id.quizMath_id);
        modelTestButton = root.findViewById(R.id.modelTest_id);

       englishButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               sent("English");
           }
       });

        mathButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               sent("Math");
           }
       });
        modelTestButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               sent("SpecialModelTest");
           }
       });


        return root;
    }



    private void sent (String name){

        Intent intent = new Intent(getContext(), QuizShowActivity.class);
        intent.putExtra("name",name);
        startActivity(intent);

    }
}