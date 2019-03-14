package com.example.forev.vetapp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.button.MaterialButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.forev.vetapp.Models.AnswerModel;
import com.example.forev.vetapp.Models.DeleteAnswerModel;
import com.example.forev.vetapp.R;
import com.example.forev.vetapp.RestApi.ManagerAll;
import com.example.forev.vetapp.Utils.Warning;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AnswersAdapter extends RecyclerView.Adapter<AnswersAdapter.ViewHolder>{

    List<AnswerModel> list;
    Context context;

    public AnswersAdapter(List<AnswerModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.alert_layout_answer,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {

        viewHolder.answer_question.setText("Question: "+ list.get(position).getQuestion().toString());
        viewHolder.answer_answer.setText("Answer: "+ list.get(position).getAnswer().toString());

        viewHolder.button_answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete(list.get(position).getAnswerid().toString(),list.get(position).getQuestionid().toString(),position);
            }
        });
    }

    public void deleteAndUpdateList(int position)
    {
        list.remove(position);
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }

    public void delete(String answerid,String questionid,final int pos)
    {
        Call<DeleteAnswerModel> req = ManagerAll.getInstance().deleteAnswer(answerid,questionid);
        req.enqueue(new Callback<DeleteAnswerModel>() {
            @Override
            public void onResponse(Call<DeleteAnswerModel> call, Response<DeleteAnswerModel> response) {
                if (response.body().isTf())
                {
                    if(response.isSuccessful())
                    {
                        Toast.makeText(context,response.body().getText(),Toast.LENGTH_LONG).show();
                        deleteAndUpdateList(pos);
                    }
                }
                else
                {
                    Toast.makeText(context,response.body().getText(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<DeleteAnswerModel> call, Throwable t) {
                Toast.makeText(context,Warning.internetProblemText,Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView answer_question, answer_answer;
        MaterialButton button_answer;

        public ViewHolder(View itemView) {
            super(itemView);

            answer_question = (TextView)itemView.findViewById(R.id.answer_question);
            answer_answer = (TextView)itemView.findViewById(R.id.answer_answer);
            button_answer = (MaterialButton)itemView.findViewById(R.id.button_answer);
        }
    }
}
