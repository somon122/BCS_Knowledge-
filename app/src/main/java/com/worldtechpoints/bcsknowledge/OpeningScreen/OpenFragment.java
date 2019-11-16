package com.worldtechpoints.bcsknowledge.OpeningScreen;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.worldtechpoints.bcsknowledge.MainActivity;
import com.worldtechpoints.bcsknowledge.R;


public class OpenFragment extends Fragment {

    public OpenFragment() {
        // Required empty public constructor
    }

    private ProgressBar progressBar;
    private int progress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_open, container, false);



        progressBar = root.findViewById(R.id.progressBar);

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    doTheWork();
                    startApp();
                }
            });
            thread.start();


        return root;

    }

    private void startApp() {
        Intent intent = new Intent(getContext(),MainActivity.class);
        intent.putExtra("alert","alert");
        startActivity(intent);

    }

    private void doTheWork() {

        for (progress = 25; progress <= 100; progress = progress+25){
            try {
                Thread.sleep(1000);
                progressBar.setProgress(progress);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }




    }


}
