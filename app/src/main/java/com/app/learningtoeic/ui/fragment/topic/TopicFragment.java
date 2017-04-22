package com.app.learningtoeic.ui.fragment.topic;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.app.learningtoeic.R;
import com.app.learningtoeic.contract.topic.TopicContract;
import com.app.learningtoeic.entity.Topic;
import com.app.learningtoeic.mvp.fragment.MVPFragment;
import com.app.learningtoeic.presenter.topic.TopicPresenter;
import com.app.learningtoeic.ui.adapter.TopicAdapter;

import java.util.ArrayList;

/**
 * Created by dell on 4/1/2017.
 */

public class TopicFragment extends MVPFragment<TopicContract.IPresenterViewOps> implements TopicContract.IViewOps,TopicAdapter.Callback {
    RecyclerView rcvTopic;
    TopicAdapter mAdapter;

    @Override
    protected void OnViewCreated() {
        mAdapter = new TopicAdapter();
        mAdapter.callback = this;
        rcvTopic.setAdapter(mAdapter);
        getPresenter().InitListTopic();
    }

    @Override
    protected void OnBindView() {
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 3);
        rcvTopic = (RecyclerView) FindViewById(R.id.rcv_topic);
        rcvTopic.setLayoutManager(layoutManager);
    }

    @Override
    public int GetLayoutId() {
        return R.layout.topic_fragment;
    }

    @Override
    protected String GetScreenTitle() {
        return getString(R.string.app_name);
    }

    @Override
    protected TopicContract.IPresenterViewOps OnRegisterPresenter() {
        return new TopicPresenter(getContext());
    }

    @Override
    public void InsertDataToAdapter(ArrayList<Topic> listItem) {
        mAdapter.InsertData(listItem);
    }

    @Override
    public void OnTopicItemClick(Topic topic) {
        SwitchFragment(new DetailTopicFragment(topic), true);
    }
}
