package com.app.learningtoeic.ui.fragment.test;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.app.learningtoeic.R;
import com.app.learningtoeic.base.BaseFragment;
import com.app.learningtoeic.contract.test.TestContract;
import com.app.learningtoeic.entity.Topic;
import com.app.learningtoeic.mvp.fragment.MVPFragment;
import com.app.learningtoeic.presenter.test.TestPresenter;
import com.app.learningtoeic.ui.adapter.TestAdapter;

import java.util.ArrayList;

/**
 * Created by dell on 4/1/2017.
 */

public class TestFragment extends MVPFragment<TestContract.IPresenterViewOps> implements TestContract.IViewOps {
    RecyclerView rcvTest;
    TestAdapter adapterTest;
    @Override
    protected void OnViewCreated() {
        adapterTest = new TestAdapter();
        rcvTest.setAdapter(adapterTest);
        getPresenter().InitListTopic();
    }

    @Override
    protected void OnBindView() {
        rcvTest = (RecyclerView) FindViewById(R.id.rcv_test);
        rcvTest.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public int GetLayoutId() {
        return R.layout.test_fragment;
    }

    @Override
    protected String GetScreenTitle() {
        return getString(R.string.app_name);
    }

    @Override
    protected TestContract.IPresenterViewOps OnRegisterPresenter() {
        return new TestPresenter();
    }

    @Override
    public void InsertDataToAdapter(ArrayList<Topic> listItem) {
        adapterTest.InsertData(listItem);
    }
}
