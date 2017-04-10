package com.app.learningtoeic.ui.fragment;

import android.os.Bundle;

import com.app.learningtoeic.R;
import com.app.learningtoeic.contract.DetailWordContract;
import com.app.learningtoeic.contract.HomeContract;
import com.app.learningtoeic.entity.Word;
import com.app.learningtoeic.mvp.fragment.MVPFragment;
import com.app.learningtoeic.presenter.DetailWordPresenter;
import com.app.learningtoeic.utils.Config;

import java.util.ArrayList;

/**
 * Created by dell on 4/8/2017.
 */

public class DetailWordFragment extends MVPFragment<DetailWordContract.IPresenterViewOps> implements DetailWordContract.IViewOps {

    ArrayList<Word> mWordList = new ArrayList<>();
    int wordId;
    public  DetailWordFragment(ArrayList<Word> wordList,int wordId)
    {
        this.mWordList = wordList;
        this.wordId = wordId;
    }

    @Override
    protected DetailWordContract.IPresenterViewOps OnRegisterPresenter() {
        return new DetailWordPresenter();
    }

    @Override
    protected void OnViewCreated() {
    }

    @Override
    protected void OnBindView() {

    }

    @Override
    public int GetLayoutId() {
        return R.layout.detail_word_fragment;
    }

    @Override
    protected String GetScreenTitle() {
        return "Detail Word";
    }

    @Override
    public boolean IsImgLikeVisible() {
        return  true;
    }
}
