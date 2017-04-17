package com.app.learningtoeic.contract;

import com.app.learningtoeic.entity.Word;
import com.app.learningtoeic.mvp.fragment.IFragmentPresenterViewOps;
import com.app.learningtoeic.mvp.fragment.IFragmentViewOps;

/**
 * Created by dell on 4/8/2017.
 */

public class DetailWordContract {

    public interface IViewOps extends IFragmentViewOps
    {
        void ChangeLikeStatus();
    }

    public interface IPresenterViewOps extends IFragmentPresenterViewOps
    {
        void UpdateLikeStatus(Word word);
        void GetData();
    }
}