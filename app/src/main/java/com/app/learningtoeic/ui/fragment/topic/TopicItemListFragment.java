package com.app.learningtoeic.ui.fragment.topic;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.app.learningtoeic.R;
import com.app.learningtoeic.contract.topic.TopicItemListContract;
import com.app.learningtoeic.entity.Topic;
import com.app.learningtoeic.entity.Word;
import com.app.learningtoeic.mvp.fragment.MVPFragment;
import com.app.learningtoeic.presenter.topic.TopicItemListPresenter;
import com.app.learningtoeic.ui.adapter.TopicItemListAdapter;
import com.app.learningtoeic.utils.Config;

import java.util.ArrayList;

/**
 * Created by QUYET on 5/16/2017.
 */

public class TopicItemListFragment  extends MVPFragment<TopicItemListContract.IPresenterViewOps> implements TopicItemListContract.IViewOps, TopicItemListAdapter.Callback {

    private RecyclerView recyclerView;
    public TopicItemListAdapter adapter;
    Topic mTopic;

    public TopicItemListFragment(Topic topic) {
        this.mTopic = topic;
    }

    @Override
    protected void OnViewCreated() {
        adapter = new TopicItemListAdapter();
        adapter.callback = this;
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void OnBindView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView = (RecyclerView) FindViewById(R.id.recycle_view_vocabulary);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void onResume() {
        super.onResume();
        getPresenter().ExcuteTopicItemListTask(String.valueOf(mTopic.id));
    }

    @Override
    public int GetLayoutId() {
        return R.layout.dictionary_fragment;
    }

    @Override
    protected String GetScreenTitle() {
        return getString(R.string.app_name);
    }

    @Override
    protected TopicItemListContract.IPresenterViewOps OnRegisterPresenter() {
        return new TopicItemListPresenter();
    }

    @Override
    public void InsertData(ArrayList<Word> listItem) {
        adapter.InsertData(listItem);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void OnClickDetailItem(Word word, int adapterPosition) {
        SwitchFragment(new DetailWordOfTopicFragment(mTopic, adapterPosition), true);
    }
}
