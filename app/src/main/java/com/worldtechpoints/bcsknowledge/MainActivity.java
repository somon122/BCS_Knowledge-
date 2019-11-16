package com.worldtechpoints.bcsknowledge;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import android.view.MenuItem;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.google.android.material.navigation.NavigationView;
import com.worldtechpoints.bcsknowledge.Question.ShortQuestionFragment;
import com.worldtechpoints.bcsknowledge.home.HomeFragment;
import com.worldtechpoints.bcsknowledge.mcqTest.MCQTestFragment;
import com.worldtechpoints.bcsknowledge.mcqTest.QuizShowActivity;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNav;

    DrawerLayout drawer;
    NavigationView navigationView;
    private HomeFragment homeFragment;
    private MCQTestFragment mcqTestFragment;
    private ShortQuestionFragment shortQuestionFragment;

    private int question = 0;
    private ControlClass controlClass;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bottomNav = findViewById(R.id.bottomNavigationView);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        homeFragment = new HomeFragment();
        mcqTestFragment = new MCQTestFragment();
        shortQuestionFragment = new ShortQuestionFragment();

        controlClass = new ControlClass(this);
        question = controlClass.getScore();

        Bundle bundle = getIntent().getExtras();

        if (bundle != null){

           quizTestStatus(50,30);

        }

            if (question == 1){

                maintainFragment(shortQuestionFragment);
                bottomNav.setSelectedItemId(R.id.question_id);
                navigationView.setCheckedItem(R.id.nav_shortQuestion);
                setTitle("Question");


        }else if (question == 2){

                maintainFragment(mcqTestFragment);
                bottomNav.setSelectedItemId(R.id.quiz_id);
                navigationView.setCheckedItem(R.id.nav_mcqTest);
                setTitle("MCQ Quiz");


            }else {

            if (savedInstanceState==null) {
                maintainFragment(homeFragment);
                navigationView.setCheckedItem(R.id.nav_home);
                setTitle("Job Search");
            }
        }


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        int id = menuItem.getItemId();

        if (id == R.id.nav_home){
           maintainFragment(homeFragment);
           bottomNav.setSelectedItemId(R.id.home_id);
            navigationView.setCheckedItem(R.id.nav_home);
            setTitle("Job Search");
            controlClass.Delete();
        }
        if (id == R.id.nav_shortQuestion){

           maintainFragment(shortQuestionFragment);
           bottomNav.setSelectedItemId(R.id.question_id);
            navigationView.setCheckedItem(R.id.nav_shortQuestion);
            setTitle("Question");
            controlClass.setStoreScore(1);
        }
        if (id == R.id.nav_mcqTest){
           maintainFragment(mcqTestFragment);
           bottomNav.setSelectedItemId(R.id.quiz_id);
            navigationView.setCheckedItem(R.id.nav_mcqTest);
            setTitle("MCQ Quiz");
            controlClass.setStoreScore(2);

        }
        if (id == R.id.nav_yourScore){

            Toast.makeText(MainActivity.this, "Coming soon....", Toast.LENGTH_SHORT).show();

        }
        if (id == R.id.nev_adminPanel_id){

          startActivity(new Intent(MainActivity.this,SubmitCategoryActivity.class));

        }
        if (id == R.id.nav_share){
            shareApp();

        }
        if (id == R.id.nav_rate_us){

            Toast.makeText(MainActivity.this, "Coming soon....", Toast.LENGTH_SHORT).show();

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);


        return false;

            }
        });



        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.home_id:
                        maintainFragment(homeFragment);
                        controlClass.Delete();

                        return true;

                    case R.id.question_id:
                        maintainFragment(shortQuestionFragment);
                        controlClass.setStoreScore(1);

                        return true;


                    case R.id.quiz_id:

                     maintainFragment(mcqTestFragment);
                        controlClass.setStoreScore(2);
                        return true;

                    default:
                        return false;
                }

            }
        });

    }

    private void quizTestStatus(int right , int wrong) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        builder.setTitle("Congratulation!")
                .setMessage("Your Quiz Test Status \n\nYour right answer: "+right+"\nYour wrong answer: "+wrong)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();

                    }
                }).setNegativeButton("Go to home", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                startActivity(new Intent(MainActivity.this,MainActivity.class));
                finish();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

    }





    private void shareApp() {

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        String shareBody = "App link : https://youtu.be/eGn-2tGoG6s";
        String shareSub = "Make Money by Android App";
        intent.putExtra(Intent.EXTRA_SUBJECT,shareSub);
        intent.putExtra(Intent.EXTRA_TEXT,shareBody);
        startActivity(Intent.createChooser(intent,"Earning App"));

    }

    private void maintainFragment(Fragment fragment){

        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.nev_hostFragment,fragment).commit();

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_logOut){

            Toast.makeText(this, "Log_out is called", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

}
