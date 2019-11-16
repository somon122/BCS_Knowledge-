package com.worldtechpoints.bcsknowledge.Question;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.worldtechpoints.bcsknowledge.MainActivity;
import com.worldtechpoints.bcsknowledge.R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ShowQuestionActivity extends AppCompatActivity {

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() ==R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private String catagory;
    private Spinner spinner;
    private List<String> catagoryValu;
    private RecyclerView recyclerView;

    private List<QuestionSubmit> questionList;
    private FirebaseFirestore mFireStore;
    private QuestionRecyclerView adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_question);

        Toolbar toolbar = findViewById(R.id.questionToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        spinner = findViewById(R.id.questionSpinner_id);

        recyclerView =findViewById(R.id.questionRecyclerView_id);
        questionList = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        mFireStore = FirebaseFirestore.getInstance();



        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
        {
            catagory = bundle.getString("name");
            setTitle(catagory);

            if (catagory.equals("Math")){

                catagoryValu = new ArrayList<String>();
                catagoryValu.add("pules");
                catagoryValu.add("minus");
                catagoryValu.add("divide");
                catagoryValu.add("multi math");


            }else if (catagory.equals("English")){

                catagoryValu = new ArrayList<String>();
                catagoryValu.add("Tense");
                catagoryValu.add("Narration");
                catagoryValu.add("Voice");
                catagoryValu.add("Vocabulary");

            }else {

                Toast.makeText(this, "No fund data!", Toast.LENGTH_SHORT).show();
            }


        }



        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, catagoryValu);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(dataAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String subCategory = spinner.getSelectedItem().toString();
                questionShowMethod(catagory,subCategory);

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    private void questionShowMethod(String mainCategory, String subCategory) {

        CollectionReference collectionReference = mFireStore.collection("QuestionPost")
                .document(mainCategory).collection(subCategory);


        collectionReference.orderBy("mTime", Query.Direction.DESCENDING)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

               if (task.isSuccessful()) {

                   questionList.clear();

                   for (DocumentSnapshot document : task.getResult()) {

                       QuestionSubmit questionSubmit = new QuestionSubmit(
                               document.getString("mTime"),
                               document.getString("mQuestion"),
                               document.getString("mAns")
                       );

                       questionList.add(questionSubmit);
                   }
                   updateListUsers(questionList);

               }else {

                   Toast.makeText(ShowQuestionActivity.this, "Question Show is Field", Toast.LENGTH_SHORT).show();
               }


            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.question_search_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.searchBar_id);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

               searchQuestion(query);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                searchQuestion(newText);

                return false;
            }
        });


        return true;
    }

    private void searchQuestion(String recherche) {
        if (recherche.length() > 0)
            recherche = recherche.substring(0, 1).toUpperCase() + recherche.substring(1).toLowerCase();

        List<QuestionSubmit> results = new ArrayList<>();
        for(QuestionSubmit questionSubmit : questionList){
            if(questionSubmit.getmQuestion() != null && questionSubmit.getmQuestion().contains(recherche)){
                results.add(questionSubmit);
            }
        }
        updateListUsers(results);
    }
    private void updateListUsers(List<QuestionSubmit> listQuestion) {

        // Sort the list by date

        Collections.sort(listQuestion, new Comparator<QuestionSubmit>() {
            @Override
            public int compare(QuestionSubmit o1, QuestionSubmit o2) {
                int res = 1;
                if (o1.getmQuestion() == (o2.getmQuestion())) {
                    res = -1;
                }
                return res;
            }
        });

        adapter = new QuestionRecyclerView(ShowQuestionActivity.this, listQuestion);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


}
