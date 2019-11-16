package com.worldtechpoints.bcsknowledge.mcqTest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.worldtechpoints.bcsknowledge.MainActivity;
import com.worldtechpoints.bcsknowledge.Question.ShowQuestionActivity;
import com.worldtechpoints.bcsknowledge.Question.SubmitQuestionActivity;
import com.worldtechpoints.bcsknowledge.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuizSubmitActivity extends AppCompatActivity {


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() ==R.id.home)
        {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


    private Spinner mainSpinner,subSpinner;
    private EditText questionET,firstOptionET,secondOptionET,thirdOptionET,fourthOptionET,correctAnsET;
    private Button submitButton;

    private ProgressDialog dialog;
    private FirebaseFirestore mFirestore;

    private String subCategory;
    private String mainCategory;
    private List<String> subCategoryValue;
    private List<String>mainCatagoryValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_submit);

        Toolbar toolbar = findViewById(R.id.quizSubmitToolbar_id);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        dialog = new ProgressDialog(this);
        mFirestore = FirebaseFirestore.getInstance();

        mainSpinner = findViewById(R.id.quizMainCategorySpinner_id);
        subSpinner = findViewById(R.id.quizSubCategorySpinner_id);

        questionET = findViewById(R.id.quizQuestion_id);
        firstOptionET = findViewById(R.id.quizFirstOption_id);
        secondOptionET = findViewById(R.id.quizSecondOption_id);
        thirdOptionET = findViewById(R.id.quizThirdOption_id);
        fourthOptionET = findViewById(R.id.quizFourthOption_id);
        correctAnsET = findViewById(R.id.quizCorrectAnswer_id);

        submitButton = findViewById(R.id.quizSubmitButton_id);


        mainCatagoryValue = new ArrayList<String>();

        mainCatagoryValue.add("English");
        mainCatagoryValue.add("Math");
        mainCatagoryValue.add("Science");
        mainCatagoryValue.add("General Knowledge");


        ArrayAdapter<String> mainDataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mainCatagoryValue);
        mainDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mainSpinner.setAdapter(mainDataAdapter);

        mainSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                mainCategory = mainSpinner.getSelectedItem().toString();
                subCategoryMethod();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                quizSubmit();
            }

        });



    }

    private void subCategoryMethod() {

        if (mainCategory.equals("Math")) {

            subCategoryValue = new ArrayList<String>();
            subCategoryValue.add("pules");
            subCategoryValue.add("minus");
            subCategoryValue.add("divide");
            subCategoryValue.add("multi math");

            Toast.makeText(QuizSubmitActivity.this, "English is called", Toast.LENGTH_SHORT).show();

        } else if (mainCategory.equals("English")) {

            subCategoryValue = new ArrayList<String>();
            subCategoryValue.add("Tense");
            subCategoryValue.add("Narration");
            subCategoryValue.add("Voice");
            subCategoryValue.add("Vocabulary");
        }else {
            Toast.makeText(QuizSubmitActivity.this, "Is not massing", Toast.LENGTH_SHORT).show();
        }



        ArrayAdapter<String> subDataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, subCategoryValue);
        subDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        subSpinner.setAdapter(subDataAdapter);

        subSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                subCategory = subSpinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void quizSubmit() {


        String question = questionET.getText().toString().trim();
        String firstOption = firstOptionET.getText().toString().trim();
        String secondOption = secondOptionET.getText().toString().trim();
        String thirdOption = thirdOptionET.getText().toString().trim();
        String fourthOption = fourthOptionET.getText().toString().trim();
        String correctAns = correctAnsET.getText().toString().trim();

        if (question.isEmpty()){

            questionET.setError("Enter Question");

        }else if (firstOption.isEmpty()){
            fourthOptionET.setError("Enter first option");

        }else if (secondOption.isEmpty()){
            secondOptionET.setError("Enter second option");

        }else if (thirdOption.isEmpty()){
            thirdOptionET.setError("Enter third option");

        }else if (fourthOption.isEmpty()){
            fourthOptionET.setError("Enter fourth option");

        }else if (correctAns.isEmpty()){

            correctAnsET.setError("Enter correct answer");
        }
        else {


            dialog.show();
            dialog.setMessage("Question is uploading...");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
            String currentDateAndTime = sdf.format(new Date());

            Map<String,Object> quizPost= new HashMap<>();

            quizPost.put("mTime",currentDateAndTime);
            quizPost.put("mQuizQuestion",question);
            quizPost.put("mQuizFirstOption",firstOption);
            quizPost.put("mQuizSecondOption",secondOption);
            quizPost.put("mQuizThirdOption",thirdOption);
            quizPost.put("mQuizFourthOption",fourthOption);
            quizPost.put("mQuizCorrectAns",correctAns);

            mFirestore.collection("QuizPost").document(mainCategory).collection(subCategory).add(quizPost)
                    .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                @Override
                public void onComplete(@NonNull Task<DocumentReference> task) {

                    if (task.isSuccessful()){

                        questionET.setText("");
                        firstOptionET.setText("");
                        secondOptionET.setText("");
                        thirdOptionET.setText("");
                        fourthOptionET.setText("");
                        correctAnsET.setText("");
                        dialog.dismiss();
                        Toast.makeText(QuizSubmitActivity.this, "Quiz Upload is successfully", Toast.LENGTH_SHORT).show();


                    }else {

                        dialog.dismiss();
                        Toast.makeText(QuizSubmitActivity.this, "Upload is field", Toast.LENGTH_SHORT).show();

                    }


                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    dialog.dismiss();
                    Toast.makeText(QuizSubmitActivity.this, "Upload is field", Toast.LENGTH_SHORT).show();

                }
            });



        }



    }

}
