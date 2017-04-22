package com.app.learningtoeic.presenter;

import android.content.Context;

import com.app.learningtoeic.contract.TopicContract;
import com.app.learningtoeic.entity.Topic;
import com.app.learningtoeic.mvp.fragment.FragmentPresenter;
import com.app.learningtoeic.utils.DbHelper;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by dell on 4/10/2017.
 */

public class TopicPresenter extends FragmentPresenter<TopicContract.IViewOps> implements TopicContract.IPresenterViewOps {

    Context context;

    public TopicPresenter(Context context){
        this.context = context;
    }
    @Override
    public void InitListTopic() {
        ArrayList<Topic> topicList = new ArrayList<>();
        try {
            topicList = new DbHelper(getView().GetActivityContext()).getListTopic();
        } catch (IOException e) {
            e.printStackTrace();
        }
        getView().InsertDataToAdapter(topicList);
    }
}
