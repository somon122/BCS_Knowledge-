package com.worldtechpoints.bcsknowledge.Question;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.worldtechpoints.bcsknowledge.R;

public class ShortQuestionFragment extends Fragment {

    Button englishButton,mathButton,recentNews,bangla,computer,rules,mantelSkill,bDKnowledge,interKnowedge,geographical,generalScience;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_short_question, container, false);

        englishButton = root.findViewById(R.id.questionEnglish_id);
        mathButton = root.findViewById(R.id.questionMath_id);
        bangla = root.findViewById(R.id.questionBangla_id);

        computer = root.findViewById(R.id.questionComputer_id);
        rules = root.findViewById(R.id.questionRules_id);
        mantelSkill = root.findViewById(R.id.questionMantelSkill_id);
        bDKnowledge = root.findViewById(R.id.questionBDKnowledge_id);
        interKnowedge = root.findViewById(R.id.questionI_Knowledge_id);
        geographical = root.findViewById(R.id.questionGeographical_id);
        generalScience = root.findViewById(R.id.questionGeneralScience_id);
        recentNews = root.findViewById(R.id.questionRecentNews_id);



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

        return root;
    }

    private void sent (String name){

        Intent intent = new Intent(getContext(),ShowQuestionActivity.class);
        intent.putExtra("name",name);
        startActivity(intent);

    }
}