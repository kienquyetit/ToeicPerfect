package com.app.learningtoeic.presenter;

import android.content.Context;

import com.app.learningtoeic.R;
import com.app.learningtoeic.contract.TopicContract;
import com.app.learningtoeic.entity.Topic;
import com.app.learningtoeic.mvp.fragment.FragmentPresenter;

import java.util.ArrayList;

/**
 * Created by dell on 4/10/2017.
 */

public class TopicPresenter extends FragmentPresenter<TopicContract.IViewOps> implements TopicContract.IPresenterViewOps {
     private static final int TOPIC_NUM  = 50;
    Context context;
    public TopicPresenter(Context context){
        this.context = context;
    }
    @Override
    public void InitListTopic() {
        ArrayList<Topic> topicList = new ArrayList<>();
        for (int i =0;i<TOPIC_NUM;i++)
        {
            Topic topic = new Topic();
            topic.id = i + 1;
            topic.name = context.getResources().getStringArray(R.array.topic_name)[i];
            topic.topicImageName = "ability.jpg";
            topicList.add(topic);
        }
        getView().InsertDataToAdapter(topicList);
    }
}
