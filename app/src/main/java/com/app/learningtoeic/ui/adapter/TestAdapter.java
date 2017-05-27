package com.app.learningtoeic.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.learningtoeic.R;
import com.app.learningtoeic.entity.Topic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 5/1/2017.
 */

public class TestAdapter extends RecyclerView.Adapter {
    List<Topic> topicList = new ArrayList<>();
    public CallBack callBack;

    public interface CallBack {
        void HandlingCheckBox(Topic topic);
    }

    public void InsertData(ArrayList<Topic> listItem) {
        if (listItem.size() == 0) {
            return;
        }
        topicList.clear();
        topicList.addAll(listItem);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.test_item, parent, false);
        NormalViewHolder vh = new NormalViewHolder(v, callBack);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof NormalViewHolder) {
            NormalViewHolder viewHolder = (NormalViewHolder) holder;
            if (topicList.size() != 0) {
                viewHolder.BindView(topicList.get(position));
            }
        }
    }

    @Override
    public int getItemCount() {
        return topicList.size();
    }

    public class NormalViewHolder extends RecyclerView.ViewHolder {
        CallBack callBack;
        ImageView imgTopic;
        TextView tvTopicEng, tvTopicVie;
        CheckBox cbTopic;
        Topic topic;

        public NormalViewHolder(View itemView, final CallBack callBack) {
            super(itemView);
            this.callBack = callBack;
            imgTopic = (ImageView) itemView.findViewById(R.id.topic_img);
            tvTopicEng = (TextView) itemView.findViewById(R.id.description_eng);
            tvTopicVie = (TextView) itemView.findViewById(R.id.description_vie);
            cbTopic = (CheckBox) itemView.findViewById(R.id.topic_cb);
            cbTopic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    topic.isChecked = topic.isChecked?false:true;
                    callBack.HandlingCheckBox(topic);
                }
            });
        }

        public void BindView(Topic item) {
            topic = item;
            cbTopic.setChecked(item.isChecked);
            String mImageName = item.topicImageName;
            int resID = itemView.getResources().getIdentifier(mImageName, "mipmap", itemView.getContext().getPackageName());
            imgTopic.setImageResource(resID);
            tvTopicEng.setText(item.name);
            if(item.translateVie.length() > 22)
            {
                tvTopicVie.setText(item.translateVie.substring(0,21) +"...");
            }
            else
            {
                tvTopicVie.setText(item.translateVie);
            }

        }
    }
}
