package com.app.learningtoeic.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.learningtoeic.R;
import com.app.learningtoeic.entity.HighScore;

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
        TextView tvLevel, tvName, tvScore, tvTotal, tvCompleteTime;
        public HighScoreViewHolder(View itemView, Callback callback) {
            super(itemView);
            this.callback = callback;
            tvLevel = (TextView) itemView.findViewById(R.id.tv_level);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            tvScore = (TextView) itemView.findViewById(R.id.tv_score);
            tvTotal = (TextView) itemView.findViewById(R.id.tv_total);
            tvCompleteTime = (TextView) itemView.findViewById(R.id.tv_time);
        }

        public void BindView(HighScore item) {
            if(item != null)
            {
                if(item.getRank() == 0 )
                {
                    tvLevel.setText("1st");
                }
                else if(item.getRank() == 1)
                {
                    tvLevel.setText("2nd");
                }
                else if(item.getRank() == 2)
                {
                    tvLevel.setText("3rd");
                }
                else
                {
                    tvLevel.setText(item.getRank()+1);
                }
                tvName.setText(item.getName());
                tvScore.setText(item.getScore()+"");
                tvTotal.setText(item.getNumberQuestion()+"");
                tvCompleteTime.setText(item.getTime());
            }
        }
    }
}
