package com.app.learningtoeic.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.learningtoeic.R;
import com.app.learningtoeic.entity.HighScore;

import org.w3c.dom.ls.LSException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 5/17/2017.
 */

public class HighScoreAdapter extends RecyclerView.Adapter {
    List<HighScore> highScoreList = new ArrayList<>();
    public Callback callback;

    public interface Callback {

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.high_score_item, parent, false);
        return new HighScoreViewHolder(viewItem, callback);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HighScoreViewHolder) {
            HighScoreViewHolder viewHolder = (HighScoreViewHolder) holder;
            viewHolder.BindView(highScoreList.get(position));
        }
    }

    public void InsertData(List<HighScore> highScores) {
        if (highScores.size() == 0) {
            return;
        }
        highScoreList.addAll(highScores);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return highScoreList.size();
    }

    public class HighScoreViewHolder extends RecyclerView.ViewHolder {
        Callback callback;
        TextView tvRank,tvName,tvNumOfQues,tvTime;
        public HighScoreViewHolder(View itemView, Callback callback) {
            super(itemView);
            this.callback = callback;
            tvRank = (TextView) itemView.findViewById(R.id.rank);
            tvName = (TextView) itemView.findViewById(R.id.name);
            tvNumOfQues = (TextView) itemView.findViewById(R.id.number_of_ques);
            tvTime = (TextView) itemView.findViewById(R.id.time);
        }

        public void BindView(HighScore item) {
            if(item != null)
            {
                if(item.getRank() == 0 )
                {
                    tvRank.setText("1st");
                }
                else if(item.getRank() == 1)
                {
                    tvRank.setText("2nd");
                }
                else if(item.getRank() == 2)
                {
                    tvRank.setText("3rd");
                }
                else
                {
                    tvRank.setText(item.getRank()+1 +"th");
                }
                tvName.setText(item.getName());
                tvNumOfQues.setText(item.getNumberQuestion()+"");
                tvTime.setText(item.getTime());
            }
        }
    }
}
