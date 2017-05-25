package com.app.learningtoeic.presenter.test;

import com.app.learningtoeic.contract.HomeContract;
import com.app.learningtoeic.contract.test.TestContract;
import com.app.learningtoeic.entity.Topic;
import com.app.learningtoeic.mvp.fragment.FragmentPresenter;
import com.app.learningtoeic.utils.DbHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by QUYET on 4/22/2017.
 */

public class TestPresenter extends FragmentPresenter<TestContract.IViewOps> implements TestContract.IPresenterViewOps {

    @Override
    public List<Topic> InitListTopic(boolean isCheckAll) {
        ArrayList<Topic> topicList = new ArrayList<>();
        try {
            topicList = new DbHelper(getView().GetActivityContext()).getListTopic();
            if(isCheckAll)
            {
                for (Topic topic : topicList
                     ) {
                    topic.isChecked= true;
                }
            }
            else
            {
                for (Topic topic : topicList
                        ) {
                    topic.isChecked= false;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        getView().InsertDataToAdapter(topicList);
        return topicList;
    }
}
