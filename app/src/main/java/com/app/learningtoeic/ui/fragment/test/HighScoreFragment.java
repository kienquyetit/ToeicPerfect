package com.app.learningtoeic.ui.fragment.test;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.app.learningtoeic.R;
import com.app.learningtoeic.contract.test.HighScoreContract;
import com.app.learningtoeic.contract.test.ShowQuestionContract;
import com.app.learningtoeic.entity.HighScore;
import com.app.learningtoeic.mvp.fragment.MVPFragment;
import com.app.learningtoeic.presenter.test.HighScorePresenter;
import com.app.learningtoeic.ui.adapter.HighScoreAdapter;

import java.util.List;

/**
 * Created by dell on 5/17/2017.
 */

public class HighScoreFragment extends MVPFragment<HighScoreContract.IPresenterViewOps> implements HighScoreContract.IViewOps {
    RecyclerView rcvHighScore;
    HighScoreAdapter mAdapter;

    @Override
    protected HighScoreContract.IPresenterViewOps OnRegisterPresenter() {
        return new HighScorePresenter();
    }

    @Override
    protected void OnViewCreated() {
        mAdapter = new HighScoreAdapter();
        rcvHighScore.setAdapter(mAdapter);
        getPresenter().GetListHighScore();
    }

    @Override
    protected void OnBindView() {
        rcvHighScore = (RecyclerView) FindViewById(R.id.rcv_highscore);
        rcvHighScore.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public int GetLayoutId() {
        return R.layout.high_score_fragment;
    }

    public void InsertData(List<HighScore> highScoreList) {
        mAdapter.InsertData(highScoreList);
    }

    @Override
    public boolean IsBackButtonVisible() {
        return true;
    }

    @Override
    protected String GetScreenTitle() {
        return "High Score";
    }
}
