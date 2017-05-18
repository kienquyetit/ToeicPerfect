package com.app.learningtoeic.contract.test;

import com.app.learningtoeic.entity.HighScore;
import com.app.learningtoeic.mvp.fragment.IFragmentPresenterViewOps;
import com.app.learningtoeic.mvp.fragment.IFragmentViewOps;

import java.util.List;


/**
 * Created by dell on 5/17/2017.
 */

public class HighScoreContract {
    public interface IViewOps extends IFragmentViewOps
    {
        void InsertData(List<HighScore> highScoreList);
    }
    public interface IPresenterViewOps extends IFragmentPresenterViewOps
    {
        void GetListHighScore();
    }
}
