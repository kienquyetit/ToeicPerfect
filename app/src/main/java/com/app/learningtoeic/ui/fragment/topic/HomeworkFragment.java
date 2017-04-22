package com.app.learningtoeic.ui.fragment.topic;

import com.app.learningtoeic.R;
import com.app.learningtoeic.contract.topic.HomeworkContract;
import com.app.learningtoeic.mvp.fragment.MVPFragment;
import com.app.learningtoeic.presenter.topic.HomeworkPresenter;

/**
 * Created by dell on 4/15/2017.
 */

public class HomeworkFragment extends MVPFragment<HomeworkContract.IPresenterViewOps> implements HomeworkContract.IViewOps {
    @Override
    protected HomeworkContract.IPresenterViewOps OnRegisterPresenter() {
        return new HomeworkPresenter();
    }

    @Override
    protected void OnViewCreated() {

    }

    @Override
    protected void OnBindView() {

    }

    @Override
    public int GetLayoutId() {
        return R.layout.homework_fragment;
    }

    @Override
    public boolean IsBackButtonVisible() {
        return true;
    }

    @Override
    protected String GetScreenTitle() {
        return "Contracts";
    }
}
