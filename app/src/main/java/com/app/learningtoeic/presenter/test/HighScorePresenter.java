package com.app.learningtoeic.presenter.test;

import com.app.learningtoeic.contract.test.HighScoreContract;
import com.app.learningtoeic.contract.test.ShowQuestionContract;
import com.app.learningtoeic.entity.HighScore;
import com.app.learningtoeic.mvp.fragment.FragmentPresenter;
import com.app.learningtoeic.utils.Config;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by dell on 5/17/2017.
 */

public class HighScorePresenter extends FragmentPresenter<HighScoreContract.IViewOps> implements HighScoreContract.IPresenterViewOps {
    public void GetListHighScore() {
        List<HighScore> highScoreList = new ArrayList<>();
        highScoreList = getListHighScore();
        for (int i = 0; i < highScoreList.size(); i++)
        {
            highScoreList.get(i).setRank(i);
        }
        getView().InsertData(highScoreList);
    }

    List<HighScore> getListHighScore()
    {
        return Config.wordDB.GetListHighScore();
    }


}
