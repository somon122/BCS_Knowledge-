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

    private Button english,math;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_short_question, container, false);

        english = root.findViewById(R.id.english_id);
        math = root.findViewById(R.id.math_id);

        english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sent("English");

            }
        });


        math.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sent("Math");

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