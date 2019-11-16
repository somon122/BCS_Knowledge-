package com.worldtechpoints.bcsknowledge.Question;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.worldtechpoints.bcsknowledge.R;

import java.util.List;

public class QuestionRecyclerView extends RecyclerView.Adapter<QuestionRecyclerView.ViewHolder> {

    private Context context;
    private List<QuestionSubmit>questionList;
    private QuestionSubmit questionSubmit;

    public QuestionRecyclerView(Context context, List<QuestionSubmit> questionList) {
        this.context = context;
        this.questionList = questionList;

    }

    @NonNull
    @Override
    public QuestionRecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.question_view_model,parent,false);

        return new QuestionRecyclerView.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull QuestionRecyclerView.ViewHolder holder, final int position) {

        questionSubmit = questionList.get(position);

        holder.questionTV.setText(questionSubmit.getmQuestion());


       /* long millisecond = postData.getTimeDate().getTime();
        String dateTime = android.text.format.DateFormat.format("dd/MM/yyyy", new Date(millisecond)).toString();
        holder.timeStam.setText(dateTime);
        String userId = postData.getUserId();*/


        holder.showAnsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                questionSubmit = questionList.get(position);
                showAnswer(questionSubmit.getmQuestion(),questionSubmit.getmAns());

            }
        });


    }

    private void showAnswer(String question,String ans){


        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Your Answer");
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.ans_show_custom_layout,null);

        TextView okQuestion = view.findViewById(R.id.okQuestion_id);
        TextView okAns = view.findViewById(R.id.okAns_id);
        okQuestion.setText(question);
        okAns.setText("Answer : "+ans);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });



        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.show();


    }


    @Override
    public int getItemCount() {
        return questionList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView questionTV;
        private Button showAnsButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            questionTV = itemView.findViewById(R.id.showQuestion_id);
            showAnsButton = itemView.findViewById(R.id.showAnswer_id);


        }

    }

}
