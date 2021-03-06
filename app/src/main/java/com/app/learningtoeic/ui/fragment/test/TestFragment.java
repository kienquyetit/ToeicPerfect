package com.app.learningtoeic.ui.fragment.test;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.app.learningtoeic.R;
import com.app.learningtoeic.base.BaseFragment;
import com.app.learningtoeic.contract.test.TestContract;
import com.app.learningtoeic.entity.Topic;
import com.app.learningtoeic.mvp.fragment.MVPFragment;
import com.app.learningtoeic.presenter.test.TestPresenter;
import com.app.learningtoeic.ui.adapter.TestAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 4/1/2017.
 */

public class TestFragment extends MVPFragment<TestContract.IPresenterViewOps> implements TestContract.IViewOps,TestAdapter.CallBack {
    RecyclerView rcvTest;
    TestAdapter adapterTest;
    TextView btnStart;
    TextView btnCheckAll;
    boolean isCheckAll = false;
    protected List<Topic> topicList = new ArrayList<>();
    @Override
    protected void OnViewCreated() {
        adapterTest = new TestAdapter();
        adapterTest.callBack = this;
        rcvTest.setAdapter(adapterTest);
        getPresenter().InitListTopic(false);
    }

    @Override
    protected void OnBindView() {
        rcvTest = (RecyclerView) FindViewById(R.id.rcv_test);
        rcvTest.setLayoutManager(new LinearLayoutManager(getContext()));
        btnStart = (TextView) FindViewById(R.id.start_btn);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(topicList.size()!=0) {
                    SwitchFragment(new ShowQuestionFragment(topicList), true);
                }
                else
                {
                    Toast.makeText(getContext(), "Please choose at least one topic !", Toast.LENGTH_LONG).show();
                }
            }
        });
        btnCheckAll = (TextView) FindViewById(R.id.checkall_btn);
        btnCheckAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isCheckAll) {
                    btnCheckAll.setText("Uncheck All");
                    isCheckAll = true;
                    topicList.clear();
                    topicList.addAll(getPresenter().InitListTopic(isCheckAll));
                }
                else
                {
                    btnCheckAll.setText("Check All");
                    isCheckAll = false;
                    topicList.clear();
                    getPresenter().InitListTopic(isCheckAll);
                }
            }
        });
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

    @Override
    public void HandlingCheckBox(Topic topic) {
        if(topicList.indexOf(topic) == -1)
        {
            topicList.add(topic);
        }
        else
        {
            topicList.remove(topic);
        }
    }
}
