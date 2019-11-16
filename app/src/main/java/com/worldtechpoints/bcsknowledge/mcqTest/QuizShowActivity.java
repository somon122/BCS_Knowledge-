package com.worldtechpoints.bcsknowledge.mcqTest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.worldtechpoints.bcsknowledge.MainActivity;
import com.worldtechpoints.bcsknowledge.Question.QuestionRecyclerView;
import com.worldtechpoints.bcsknowledge.Question.ShowQuestionActivity;
import com.worldtechpoints.bcsknowledge.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class QuizShowActivity extends AppCompatActivity {


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.home) {
           finish();
        }
        return super.onOptionsItemSelected(item);
    }


    private FirebaseFirestore mFirestore;
    private RadioGroup radioGroup;
    private RadioButton option1,option2,option3,option4;
    private RadioButton radioButton;

    private TextView questionTV;
    private Button submitButton;
    private Spinner spinner;
    private String category;
    private List<String> catagoryValue;
    private List<QuizSubmit> quizList;

    private int wrongAnswer;
    private int rightAnswer;

    private String answer;
    private int updateCount = 0;
    int size;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_show);

        Toolbar toolbar = findViewById(R.id.quizToolBar_id);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mFirestore = FirebaseFirestore.getInstance();
        quizList = new ArrayList<>();
        catagoryValue = new ArrayList<>();

        radioGroup = findViewById(R.id.quizOptionGroup);
        questionTV = findViewById(R.id.showQuizQuestion_id);
        submitButton = findViewById(R.id.quizShowSubmit_id);
        spinner = findViewById(R.id.quizShowSpinner_id);

        option1 = findViewById(R.id.quizOption1);
        option2 = findViewById(R.id.quizOption2);
        option3 = findViewById(R.id.quizOption3);
        option4 = findViewById(R.id.quizOption4);




        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            category = bundle.getString("name");
            setTitle(category);

            if (category.equals("Math")) {

                catagoryValue = new ArrayList<String>();
                catagoryValue.add("pules");
                catagoryValue.add("minus");
                catagoryValue.add("divide");
                catagoryValue.add("multi math");

            } else if (category.equals("English")) {

                catagoryValue = new ArrayList<String>();
                catagoryValue.add("Narration");
                catagoryValue.add("Tense");
                catagoryValue.add("Voice");
                catagoryValue.add("Vocabulary");

            }else if (category.equals("SpecialModelTest")) {

                modelTest();



            } else {

                Toast.makeText(this, "No fund data!", Toast.LENGTH_SHORT).show();
            }


        }


        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, catagoryValue);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String subCategory = spinner.getSelectedItem().toString();
                questionShowMethod(category, subCategory);
                updateCount = 0;
                rightAnswer = 0;
                wrongAnswer = 0;
                radioGroup.clearCheck();

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });




        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (radioGroup.getCheckedRadioButtonId() == -1){

                    radioGroup.clearCheck();
                    Toast.makeText(QuizShowActivity.this, "Please select any one option", Toast.LENGTH_SHORT).show();



                }else {
                    int radioId = radioGroup.getCheckedRadioButtonId();
                    radioButton = findViewById(radioId);
                    String status = radioButton.getText().toString();

                    if (status.equals(answer)){

                        rightAnswer = rightAnswer+1;
                        showQuizStatus("Your answer is Correct ","Congratulation !");
                        Toast.makeText(QuizShowActivity.this, radioButton.getText(), Toast.LENGTH_SHORT).show();

                    }else {

                        wrongAnswer = wrongAnswer+1;
                        showQuizStatus("Your answer is Wrong"," ooop! Carefully Answer!");

                    }
                }


            }
        });
    }

    private void modelTest() {

        catagoryValue = new ArrayList<String>();
        catagoryValue.add("Model Test No: 1");
        catagoryValue.add("Model Test No: 2");
        catagoryValue.add("Model Test No: 3");
        catagoryValue.add("Model Test No: 4");
        catagoryValue.add("Model Test No: 5");
        catagoryValue.add("Model Test No: 6");
        catagoryValue.add("Model Test No: 7");
        catagoryValue.add("Model Test No: 8");
        catagoryValue.add("Model Test No: 9");
        catagoryValue.add("Model Test No: 10");
        catagoryValue.add("Model Test No: 11");
        catagoryValue.add("Model Test No: 12");
        catagoryValue.add("Model Test No: 13");
        catagoryValue.add("Model Test No: 14");
        catagoryValue.add("Model Test No: 15");



    }


    private void questionShowMethod(String mainCategory, String subCategory) {

        CollectionReference collectionReference = mFirestore.collection("QuizPost")
                .document(mainCategory).collection(subCategory);

        collectionReference.orderBy("mTime", Query.Direction.DESCENDING)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()) {

                            quizList.clear();

                            for (DocumentSnapshot document : task.getResult()) {

                                QuizSubmit quizSubmit = new QuizSubmit(
                                        document.getString("mTime"),
                                        document.getString("mQuizQuestion"),
                                        document.getString("mQuizFirstOption"),
                                        document.getString("mQuizSecondOption"),
                                        document.getString("mQuizThirdOption"),
                                        document.getString("mQuizFourthOption"),
                                        document.getString("mQuizCorrectAns")
                                );

                                quizList.add(quizSubmit);

                            }
                            updateQuiz(0);
                            size = quizList.size();


                        } else {
                            Toast.makeText(QuizShowActivity.this, "Quiz Show is Field", Toast.LENGTH_SHORT).show();
                        }


                    }
                });



    }
    private void showQuizStatus(String rightWrongAns,String greetingStatus){


        final AlertDialog.Builder builder = new AlertDialog.Builder(QuizShowActivity.this);
        builder.setTitle("Your Question Status");
        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = layoutInflater.inflate(R.layout.quiz_view_model,null);

        TextView greeting = view.findViewById(R.id.greeting_id);
        TextView userStatus = view.findViewById(R.id.userStatus_id);
        greeting.setText(greetingStatus);
        userStatus.setText(rightWrongAns);
        if (rightWrongAns.equals("Your answer is Wrong")){
            userStatus.setBackgroundColor(getResources().getColor(R.color.Red));
        }else {
            userStatus.setBackgroundColor(getResources().getColor(R.color.green));
        }



        builder.setPositiveButton("Next", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                try {

                    int lastSize = size = size-1;
                    if (updateCount <= lastSize ){
                        updateCount = updateCount+1;
                        updateQuiz(updateCount);
                        dialogInterface.dismiss();
                        radioGroup.clearCheck();
                    }else {
                        dialogInterface.dismiss();
                        quizTestStatus(rightAnswer,wrongAnswer);
                        radioGroup.clearCheck();
                    }

                }catch (Exception e){
                    dialogInterface.dismiss();
                    quizTestStatus(rightAnswer,wrongAnswer);
                    radioGroup.clearCheck();

                }

            }
        });
        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.show();


    }

    private void quizTestStatus(int right , int wrong) {


        final AlertDialog.Builder builder = new AlertDialog.Builder(QuizShowActivity.this);

        builder.setTitle("Congratulation!")
                .setMessage("Your Quiz Test Status \n\nYour right answer: "+right+"\nYour wrong answer: "+wrong)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                        radioGroup.clearCheck();


                    }
                }).setNegativeButton("Go to home", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                startActivity(new Intent(QuizShowActivity.this,MainActivity.class));
                finish();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

    }




    private void updateQuiz(int num) {

        if (!quizList.isEmpty()){
            QuizSubmit quizSubmit = quizList.get(num);
            option1.setText(quizSubmit.getmQuizFirstOption());
            option2.setText(quizSubmit.getmQuizSecondOption());
            option3.setText(quizSubmit.getmQuizThirdOption());
            option4.setText(quizSubmit.getmQuizFourthOption());

            questionTV.setText(quizSubmit.getmQuizQuestion());
            answer = quizSubmit.getmQuizCorrectAns();

            option1.setVisibility(View.VISIBLE);
            option2.setVisibility(View.VISIBLE);
            option3.setVisibility(View.VISIBLE);
            option4.setVisibility(View.VISIBLE);
            submitButton.setVisibility(View.VISIBLE);


        }else {
            option1.setText("");
            option2.setText("");
            option3.setText("");
            option4.setText("");
            questionTV.setText("");

            option1.setVisibility(View.GONE);
            option2.setVisibility(View.GONE);
            option3.setVisibility(View.GONE);
            option4.setVisibility(View.GONE);
            submitButton.setVisibility(View.GONE);
            questionTV.setText("Question is coming soon....");

            Toast.makeText(this, "Data is not available", Toast.LENGTH_SHORT).show();
        }



    }
}