package com.worldtechpoints.bcsknowledge.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;
import com.worldtechpoints.bcsknowledge.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import id.zelory.compressor.Compressor;

public class JobSubmitActivity extends AppCompatActivity {

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() ==R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }



    private EditText jobSubmitTitleET,jobShortDescriptionET,jobBroadDescriptionET,jobWebsiteLinkET;
    private Button imageSelectButton,jobSubmitButton;
    private ImageView imageView;

    private Uri imageUri = null;
    private Bitmap compressedImageFile;

    private FirebaseFirestore mFirestone;
    private StorageReference mStoreRef;

    private ProgressDialog dialog;

    private String jobTile;
    private  String jobShortDescription;
    private String jobBoardDescription;
    private String jobWebsiteLink;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_submit);

        Toolbar toolbar = findViewById(R.id.jobSubmitToolbar_id);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setTitle("Job Submit Panel");

        dialog = new ProgressDialog(this);
        mFirestone = FirebaseFirestore.getInstance();

        mStoreRef = FirebaseStorage.getInstance().getReference().child("JobPostImage");


        jobBroadDescriptionET = findViewById(R.id.jobBroadDescription_id);
        jobShortDescriptionET = findViewById(R.id.jobShortDescription_id);
        jobSubmitTitleET = findViewById(R.id.jobSubmitTitle_id);
        jobWebsiteLinkET = findViewById(R.id.jobWebsiteLink_id);

        imageSelectButton = findViewById(R.id.imageSelectButton_id);
        jobSubmitButton = findViewById(R.id.jobSubmitButton_id);

        imageView = findViewById(R.id.jobSubmitImage_id);


        imageSelectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CropImage.activity()
                        .setMinCropResultSize(720,720)
                        .setAspectRatio(1,1)
                        .setAutoZoomEnabled(true)
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .start(JobSubmitActivity.this);


            }
        });

        jobSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jobSubmitDetails();
            }
        });



    }

    private void jobSubmitDetails() {


       jobTile = jobSubmitTitleET.getText().toString().trim();
       jobShortDescription = jobSubmitTitleET.getText().toString().trim();
       jobBoardDescription = jobSubmitTitleET.getText().toString().trim();
        jobWebsiteLink = jobWebsiteLinkET.getText().toString().trim();

        if (TextUtils.isEmpty(jobTile)){

            jobSubmitTitleET.setError("Please Enter Job Title.");

        }else if (TextUtils.isEmpty(jobShortDescription)){
            jobShortDescriptionET.setError("Please Enter Short Description");

        }else if (TextUtils.isEmpty(jobBoardDescription)){

            jobBroadDescriptionET.setError("Please Enter Board Description");

        }else if (TextUtils.isEmpty(jobWebsiteLink)){

            jobWebsiteLinkET.setError("Please Enter Website Link");

        }else {

            if (imageUri != null){

                dialog.setMessage("Job is Uploading....");
                dialog.show();

                File newImageFile = new File(imageUri.getPath());


                try {
                    compressedImageFile = new Compressor(this)
                            .setMaxWidth(720)
                            .setMaxHeight(570)
                            .setQuality(75)
                            .compressToBitmap(newImageFile);

                } catch (IOException e) {
                    e.printStackTrace();
                }


                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                compressedImageFile.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] newImageData = baos.toByteArray();

                final StorageReference imageName = mStoreRef.child(imageUri.getLastPathSegment()).child(".jpg");


                imageName.putBytes(newImageData)
                        .addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {

                        if (task.isSuccessful()){

                            imageName.getDownloadUrl()
                                    .addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {

                                           uploadJobInformation(String.valueOf(uri));

                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception exception) {

                                    dialog.dismiss();
                                    Toast.makeText(JobSubmitActivity.this, "Hopppposss", Toast.LENGTH_SHORT).show();
                                    // Handle any errors
                                }
                            });





                        }else {

                            dialog.dismiss();
                            Toast.makeText(JobSubmitActivity.this, "Upload is Field", Toast.LENGTH_SHORT).show();
                        }



                    }
                });





            }else {

                Toast.makeText(this, "Please select or set Image", Toast.LENGTH_SHORT).show();
            }


        }



    }

    private void uploadJobInformation(String uri){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
        String currentDateAndTime = sdf.format(new Date());

        Map<String,Object> newPost= new HashMap<>();
        newPost.put("mPostTime",currentDateAndTime);
        newPost.put("mJobTitle",jobTile);
        newPost.put("mJobBoardDescription",jobBoardDescription);
        newPost.put("mJobShortDescription",jobShortDescription);
        newPost.put("mJobWebsiteLink",jobWebsiteLink);
        newPost.put("mJobInfoImageUrl",uri);

        mFirestone.collection("JobUpdatePost").add(newPost)
                .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {

                        if (task.isSuccessful()){

                            jobSubmitTitleET.setText("");
                            jobShortDescriptionET.setText("");
                            jobBroadDescriptionET.setText("");
                            jobWebsiteLinkET.setText("");
                            imageView.setBackground(null);

                            Toast.makeText(JobSubmitActivity.this, "Job Upload Successfully", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }else {

                            Toast.makeText(JobSubmitActivity.this, "", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }



                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                dialog.dismiss();
            }
        });


    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                imageUri = result.getUri();
                imageView.setImageURI(imageUri);
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
                Toast.makeText(this, "Problem "+error, Toast.LENGTH_SHORT).show();
            }
        }
    }



}
