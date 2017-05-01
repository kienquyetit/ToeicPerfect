package com.app.learningtoeic.presenter.test;

import com.app.learningtoeic.contract.HomeContract;
import com.app.learningtoeic.contract.test.TestContract;
import com.app.learningtoeic.entity.Topic;
import com.app.learningtoeic.mvp.fragment.FragmentPresenter;
import com.app.learningtoeic.utils.DbHelper;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by QUYET on 4/22/2017.
 */

public class TestPresenter extends FragmentPresenter<TestContract.IViewOps> implements TestContract.IPresenterViewOps {

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
