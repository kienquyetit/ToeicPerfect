package com.app.learningtoeic.contract.dictionary;

import com.app.learningtoeic.entity.Word;
import com.app.learningtoeic.mvp.fragment.IFragmentPresenterViewOps;
import com.app.learningtoeic.mvp.fragment.IFragmentViewOps;

import java.util.ArrayList;

/**
 * Created by dell on 4/7/2017.
 */

public class DictionaryContract {
    public interface IViewOps extends IFragmentViewOps
    {
        void InsertData(ArrayList<Word> listItem);
    }
    public interface IPresenterViewOps extends IFragmentPresenterViewOps
    {
        void ExcuteDictionaryTask(String topicId);
    }
}
