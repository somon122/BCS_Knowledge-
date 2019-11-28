package com.worldtechpoints.bcsknowledge.Question;

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
import com.worldtechpoints.bcsknowledge.Category_Content;
import com.worldtechpoints.bcsknowledge.MainActivity;
import com.worldtechpoints.bcsknowledge.R;
import com.worldtechpoints.bcsknowledge.mcqTest.QuizSubmitActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubmitQuestionActivity extends AppCompatActivity {


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() ==R.id.home)
        {
           finish();
        }
        return super.onOptionsItemSelected(item);
    }


    private Button uploadButton;
    private EditText questionET, ansET;
    private Spinner mainSpinner,subSpinner;
    private ProgressDialog dialog;

    private String subCategory;
    private String mainCategory;
    private List<String> subCategoryValue;
    private List<String>mainCatagoryValue;

    private Category_Content category_content;


    private FirebaseFirestore mFirestore;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_question);

        Toolbar toolbar = findViewById(R.id.questionSubmitToolbar_id);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        dialog =  new ProgressDialog(this);
        mFirestore = FirebaseFirestore.getInstance();
        uploadButton = findViewById(R.id.qAnsUpload_id);
        questionET = findViewById(R.id.uploadQuestion_id);
        ansET = findViewById(R.id.uploadAns_id);

        mainSpinner = findViewById(R.id.mainQuestionCategory_id);
        subSpinner = findViewById(R.id.subQuestionCategory_id);
        category_content = new Category_Content();

        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                submit();

            }
        });

        mainCatagoryValue = new ArrayList<String>();

        mainCatagoryValue.add(getString(R.string.bangla));
        mainCatagoryValue.add(getString(R.string.english));
        mainCatagoryValue.add(getString(R.string.math));
        mainCatagoryValue.add(getString(R.string.generalScience));
        mainCatagoryValue.add(getString(R.string.mantelSkill));
        mainCatagoryValue.add(getString(R.string.computer));
        mainCatagoryValue.add(getString(R.string.rules));
        mainCatagoryValue.add(getString(R.string.geographical));
        mainCatagoryValue.add(getString(R.string.bDKnowledge));
        mainCatagoryValue.add(getString(R.string.internationalKnowledge));
        mainCatagoryValue.add(getString(R.string.recentNews));


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



    }

    private void submit (){

        String question = questionET.getText().toString().trim();
        String ans = ansET.getText().toString().trim();

        if (question.isEmpty()){
            questionET.setError("Enter Question");

        }else if (ans.isEmpty()){
            ansET.setError("Enter Ans");
        }else {

            dialog.show();
            dialog.setMessage("Question is uploading...");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
            String currentDateAndTime = sdf.format(new Date());

            Map<String,Object> questionPost= new HashMap<>();
            questionPost.put("mTime",currentDateAndTime);
            questionPost.put("mQuestion",question);
            questionPost.put("mAns",ans);


            mFirestore.collection("QuestionPost")
                    .document(mainCategory).collection(subCategory).add(questionPost)
                    .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                @Override
                public void onComplete(@NonNull Task<DocumentReference> task) {

                    if (task.isSuccessful()){
                        dialog.dismiss();
                        questionET.setText("");
                        ansET.setText("");
                        Toast.makeText(SubmitQuestionActivity.this, "Test is successfully", Toast.LENGTH_SHORT).show();


                    }else {

                        Toast.makeText(SubmitQuestionActivity.this, "Test is Field", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }


                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(SubmitQuestionActivity.this, "Test is Field    two...", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            });

        }

    }

    private void subCategoryMethod() {

        if (mainCategory.equals(getString(R.string.math))) {

            subCategoryValue = category_content.math_Sub_Category();


        } else if (mainCategory.equals(getString(R.string.english))) {

            subCategoryValue = category_content.english_Sub_Category();

        }else if (mainCategory.equals(getString(R.string.geographical))) {

            subCategoryValue = category_content.geographical_Sub_Category();

        }else if (mainCategory.equals(getString(R.string.mantelSkill))) {

            subCategoryValue = category_content.mantalSkillSub_Category();

        }else if (mainCategory.equals(getString(R.string.bangla))) {

            subCategoryValue = category_content.bangla_Sub_Category();

        }else if (mainCategory.equals(getString(R.string.generalScience))) {

            subCategoryValue = category_content.g_science_Sub_Category();

        }else if (mainCategory.equals(getString(R.string.bDKnowledge))) {

            subCategoryValue = category_content.bdGK_Sub_Category();

        }else if (mainCategory.equals(getString(R.string.internationalKnowledge))) {

            subCategoryValue = category_content.i_GK_Sub_Category();

        }else if (mainCategory.equals(getString(R.string.rules))) {

            subCategoryValue = category_content.n_m_s_Sub_Category();

        }else if (mainCategory.equals(getString(R.string.computer))) {

            subCategoryValue = category_content.computer_Sub_Category();

        }else if (mainCategory.equals(getString(R.string.recentNews))) {

            subCategoryValue = category_content.recentNews_Sub_Category();

        }else {
            Toast.makeText(SubmitQuestionActivity.this, "Is not massing", Toast.LENGTH_SHORT).show();
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

}
