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

    Button englishButton,mathButton,modelTestButton,bangla,computer,recentNews,
            rules,mantelSkill,bDKnowledge,interKnowedge,geographical,generalScience;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_mcq_test, container, false);

        englishButton = root.findViewById(R.id.quizEnglish_id);
        mathButton = root.findViewById(R.id.quizMath_id);
        bangla = root.findViewById(R.id.quizBangla_id);

        computer = root.findViewById(R.id.quizComputer_id);
        rules = root.findViewById(R.id.quizRules_id);
        mantelSkill = root.findViewById(R.id.quizMantelSkill_id);
        bDKnowledge = root.findViewById(R.id.quizBDKnowledge_id);
        interKnowedge = root.findViewById(R.id.quizI_Knowledge_id);
        geographical = root.findViewById(R.id.quizGeographical_id);
        generalScience = root.findViewById(R.id.quizGeneralScience_id);
        recentNews = root.findViewById(R.id.quizRecentNews_id);
        modelTestButton = root.findViewById(R.id.modelTest_id);



       englishButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               sent(getString(R.string.english));
           }
       });
        bangla.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               sent(getString(R.string.bangla));
           }
       });
        computer.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               sent(getString(R.string.computer));
           }
       });
        rules.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               sent(getString(R.string.rules));
           }
       });
        mantelSkill.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               sent(getString(R.string.mantelSkill));
           }
       });
        bDKnowledge.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               sent(getString(R.string.bDKnowledge));
           }
       });
        interKnowedge.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               sent(getString(R.string.internationalKnowledge));
           }
       });
        geographical.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               sent(getString(R.string.geographical));
           }
       });
        generalScience.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               sent(getString(R.string.generalScience));
           }
       });

        mathButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               sent(getString(R.string.math));
           }
       });
        recentNews.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               sent(getString(R.string.recentNews));
           }
       });
        modelTestButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               sent(getString(R.string.modelTest));
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