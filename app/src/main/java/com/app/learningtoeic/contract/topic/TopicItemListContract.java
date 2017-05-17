package com.app.learningtoeic.contract.topic;

import com.app.learningtoeic.entity.Word;
import com.app.learningtoeic.mvp.fragment.IFragmentPresenterViewOps;
import com.app.learningtoeic.mvp.fragment.IFragmentViewOps;

import java.util.ArrayList;

/**
 * Created by QUYET on 5/16/2017.
 */

public class TopicItemListContract {
    public interface IViewOps extends IFragmentViewOps
    {
        void InsertData(ArrayList<Word> listItem);
    }
    public interface IPresenterViewOps extends IFragmentPresenterViewOps
    {
        void ExcuteTopicItemListTask(String topicId);
    }
}
