package com.app.learningtoeic.contract;

import com.app.learningtoeic.entity.Topic;
import com.app.learningtoeic.entity.Word;
import com.app.learningtoeic.mvp.fragment.IFragmentPresenterViewOps;
import com.app.learningtoeic.mvp.fragment.IFragmentViewOps;

import java.util.ArrayList;

/**
 * Created by dell on 4/10/2017.
 */

public class TopicContract {
    public interface IViewOps extends IFragmentViewOps
    {
        void InsertDataToAdapter(ArrayList<Topic> listItem);
    }
    public interface IPresenterViewOps extends IFragmentPresenterViewOps
    {
        void InitListTopic();
    }
}
