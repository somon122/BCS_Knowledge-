package com.worldtechpoints.bcsknowledge.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.worldtechpoints.bcsknowledge.R;

import java.util.ArrayList;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class HomeFragment extends Fragment {


    private RecyclerView recyclerView;
    private List<JobUpdate> postList;
    private FirebaseFirestore mFireStore;
    private JobRecyclerView adapter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);


        postList = new ArrayList<>();
        recyclerView = root.findViewById(R.id.jobRecyclerViewId);
        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
        recyclerView.setHasFixedSize(true);

        mFireStore = FirebaseFirestore.getInstance();


        CollectionReference collectionReference = mFireStore.collection("JobUpdatePost");


        collectionReference.orderBy("mPostTime", Query.Direction.DESCENDING)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

                            postList.clear();

                            for (DocumentSnapshot document : task.getResult()) {

                                    JobUpdate jobUpdate = new JobUpdate(
                                            document.getString("mPostTime"),
                                            document.getString("mJobTitle"),
                                            document.getString("mJobBoardDescription"),
                                            document.getString("mJobShortDescription"),
                                            document.getString("mJobWebsiteLink"),
                                            document.getString("mJobInfoImageUrl"));

                                    postList.add(jobUpdate);
                            }

                            adapter = new JobRecyclerView(getContext(),postList);
                            recyclerView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();




                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });




        return root;
    }


}