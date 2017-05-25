package com.app.learningtoeic.contract.test;

import com.app.learningtoeic.entity.Topic;
import com.app.learningtoeic.mvp.fragment.IFragmentPresenterViewOps;
import com.app.learningtoeic.mvp.fragment.IFragmentViewOps;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by QUYET on 4/22/2017.
 */

public class TestContract {
    public interface IViewOps extends IFragmentViewOps
    {
        void InsertDataToAdapter(ArrayList<Topic> listItem);
    }
    public interface IPresenterViewOps extends IFragmentPresenterViewOps
    {
        List<Topic> InitListTopic(boolean isCheckAll);
    }
}
