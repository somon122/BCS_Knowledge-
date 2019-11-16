package com.worldtechpoints.bcsknowledge.home;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.worldtechpoints.bcsknowledge.R;

import java.util.List;

public class JobRecyclerView extends RecyclerView.Adapter<JobRecyclerView.ViewHolder> {

    private Context context;
    private List<JobUpdate>jobUpdateList;

    private JobUpdate jobUpdate;



    public JobRecyclerView(Context context, List<JobUpdate> jobUpdateList) {
        this.context = context;
        this.jobUpdateList = jobUpdateList;

    }

    @NonNull
    @Override
    public JobRecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.job_update_model,parent,false);

        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull JobRecyclerView.ViewHolder holder, final int position) {


        jobUpdate = jobUpdateList.get(position);
        holder.jobDescription.setText(jobUpdate.mJobShortDescription);
        holder.jobTitle.setText(jobUpdate.mJobTitle);
        Picasso.get().load(jobUpdate.getmJobInfoImageUrl()).placeholder(R.drawable.ic_launcher_background).into(holder.jobInfoImage);

        holder.readMoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                jobUpdate = jobUpdateList.get(position);
                Intent intent = new Intent(context,JobDetailShowActivity.class);
                intent.putExtra("title",jobUpdate.mJobTitle);
                intent.putExtra("description",jobUpdate.mJobBoardDescription);
                intent.putExtra("webLink",jobUpdate.mJobWebsiteLink);
                intent.putExtra("imageUrl",jobUpdate.mJobInfoImageUrl);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {

        return jobUpdateList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView jobTitle,jobDescription;
        private ImageView jobInfoImage;
        private Button readMoreButton;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            jobTitle = itemView.findViewById(R.id.jobTitle_id);
            jobDescription = itemView.findViewById(R.id.jobDescription_id);
            jobInfoImage = itemView.findViewById(R.id.jobDetailsPic_id);
            readMoreButton = itemView.findViewById(R.id.jobDetailButton_id);


        }
    }
}

