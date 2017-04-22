package com.app.learningtoeic.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.learningtoeic.R;
import com.app.learningtoeic.entity.Topic;

import java.util.ArrayList;

/**
 * Created by dell on 4/10/2017.
 */

public class TopicAdapter extends RecyclerView.Adapter {

    public Callback callback;

    public interface Callback
    {
        void OnTopicItemClick(Topic topic);
    }

    ArrayList<Topic> topicList;

    public void InsertData(ArrayList<Topic> listItem)
    {
        if(listItem.size()==0) {
            return;
        }
        topicList.addAll(listItem);
        notifyDataSetChanged();
    }

    public TopicAdapter()
    {
        topicList = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.topic_item, parent, false);
        NormalViewHolder vh = new NormalViewHolder(v,callback);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof NormalViewHolder)
        {
            NormalViewHolder viewHolder = (NormalViewHolder)holder;
            if (topicList.size() != 0)
            {
                viewHolder.BindView(topicList.get(position));
            }
        }
    }

    @Override
    public int getItemCount() {
        return topicList.size();
    }

    public class NormalViewHolder extends RecyclerView.ViewHolder {
        ImageView imgTopic;
        TextView tvTopicName;
        View topicItemLayout;
        Callback callback;
        Topic topicItem;

        public NormalViewHolder(View itemView, final Callback callback) {
            super(itemView);
            this.callback = callback;
            imgTopic = (ImageView) itemView.findViewById(R.id.topic_img);
            tvTopicName = (TextView) itemView.findViewById(R.id.topic_name);
            tvTopicName.setSelected(true);
            topicItemLayout = itemView.findViewById(R.id.wrap_topic_item);
            topicItemLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callback.OnTopicItemClick(topicItem);
                }
            });
        }

        public void BindView(Topic item)
        {
            topicItem = item;
            String mImageName = item.topicImageName;
            int resID = itemView.getResources().getIdentifier(mImageName ,"mipmap", itemView.getContext().getPackageName());
            imgTopic.setImageResource(resID);
            tvTopicName.setText( item.id+". "+item.name );
        }
    }
}
