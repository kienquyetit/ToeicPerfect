package com.app.learningtoeic.contract;

import com.app.learningtoeic.entity.Word;
import com.app.learningtoeic.mvp.fragment.IFragmentPresenterViewOps;
import com.app.learningtoeic.mvp.fragment.IFragmentViewOps;

import java.util.ArrayList;

/**
 * Created by QUYET on 5/23/2017.
 */

public class FavouriteContract {
    public interface IViewOps extends IFragmentViewOps
    {
        void InsertData(ArrayList<Word> listItem);
    }
    public interface IPresenterViewOps extends IFragmentPresenterViewOps
    {
        void ExcuteDictionaryTask();
    }
}
