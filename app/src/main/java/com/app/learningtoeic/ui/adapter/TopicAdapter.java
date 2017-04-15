package com.app.learningtoeic.ui.adapter;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.learningtoeic.R;
import com.app.learningtoeic.entity.Topic;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 4/10/2017.
 */

public class TopicAdapter extends RecyclerView.Adapter {
    public interface Callback
    {
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
        NormalViewHolder vh = new NormalViewHolder(v);
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
        public NormalViewHolder(View itemView) {
            super(itemView);
            imgTopic = (ImageView) itemView.findViewById(R.id.topic_img);
            tvTopicName = (TextView) itemView.findViewById(R.id.topic_name);
            tvTopicName.setSelected(true);
        }

        public void BindView(Topic item)
        {
            InputStream ims = null;
            try {
                ims = itemView.getContext().getAssets().open(item.topicImageName);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Drawable drawable = Drawable.createFromStream(ims, null);
            imgTopic.setImageResource(R.mipmap.ability);
            tvTopicName.setText( item.id+". "+item.name );
        }
    }
}
