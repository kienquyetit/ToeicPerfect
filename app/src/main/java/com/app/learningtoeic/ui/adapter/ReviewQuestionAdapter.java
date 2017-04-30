package com.app.learningtoeic.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.learningtoeic.R;
import com.app.learningtoeic.entity.Question;
import com.app.learningtoeic.entity.Topic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 4/30/2017.
 */

public class ReviewQuestionAdapter extends RecyclerView.Adapter {
    List<Question> questionList = new ArrayList<>();
    public CallBack callBack;
    public interface CallBack{
        void GoToQuestion(Question question);
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_question_item, parent, false);
        NormalViewHolder vh = new NormalViewHolder(v,callBack);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof NormalViewHolder) {
            NormalViewHolder viewHolder = (NormalViewHolder) holder;
            if (questionList.size() != 0) {
                viewHolder.BindView(questionList.get(position));
            }
        }
    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }

    public void InsertData(Question Item) {
        questionList.add(Item);
        notifyDataSetChanged();
    }

    public class NormalViewHolder extends RecyclerView.ViewHolder {
        TextView questionIndexTv;
        int questionIndex = 0;
        public CallBack callBack;
        Question question;
        public NormalViewHolder(final View itemView, final CallBack callBack) {
            super(itemView);
            this.callBack = callBack;
            questionIndexTv = (TextView) itemView.findViewById(R.id.question_index_tv);
            questionIndexTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callBack.GoToQuestion(question);
                }
            });
        }

        public void BindView(Question item) {
            this.question = item;
            if(item.rightIndex != item.falseIndex)
            {
                questionIndexTv.setBackgroundResource(R.color.statusRed);
            }
            else
            {
                questionIndexTv.setBackgroundResource(android.R.color.holo_blue_bright);
            }
            questionIndex = item.questionIndex;
            questionIndexTv.setText(item.questionIndex + 1 + "");
        }
    }
}
