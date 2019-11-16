package com.worldtechpoints.bcsknowledge.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.worldtechpoints.bcsknowledge.MainActivity;
import com.worldtechpoints.bcsknowledge.R;

public class JobDetailShowActivity extends AppCompatActivity {

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId()==R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


    private ImageView jobImageView;
    private TextView jobTitleTV,jobDescriptionTV,websiteLinkTV;
    private String title,description,webLink,imageUrl;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_detail_show);

        Toolbar toolbar = findViewById(R.id.toolbarJobDetails_id);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("Job Update Details");

        jobDescriptionTV = findViewById(R.id.detailsJobDescription_id);
        jobTitleTV = findViewById(R.id.detailsJobTitle_id);
        websiteLinkTV = findViewById(R.id.detailsJobWebsiteLink_id);
        jobImageView = findViewById(R.id.detailsJobImage_id);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){

            title = bundle.getString("title");
            description = bundle.getString("description");
            webLink = bundle.getString("webLink");
             imageUrl = bundle.getString("imageUrl");

        }

        jobTitleTV.setText(title);
        jobDescriptionTV.setText(description);
        websiteLinkTV.setText(webLink);
        Picasso.get().load(imageUrl).placeholder(R.drawable.common_full_open_on_phone).into(jobImageView);



    }
}
