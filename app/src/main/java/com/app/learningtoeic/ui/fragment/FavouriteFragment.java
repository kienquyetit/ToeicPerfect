package com.app.learningtoeic.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.app.learningtoeic.R;
import com.app.learningtoeic.contract.FavouriteContract;
import com.app.learningtoeic.entity.Word;
import com.app.learningtoeic.mvp.fragment.MVPFragment;
import com.app.learningtoeic.presenter.FavouritePresenter;
import com.app.learningtoeic.ui.adapter.DictionaryAdapter;
import com.app.learningtoeic.ui.adapter.TopicItemListAdapter;
import com.app.learningtoeic.ui.fragment.dictionary.DetailWordFragment;
import com.app.learningtoeic.utils.Config;

import java.util.ArrayList;

/**
 * Created by QUYET on 5/23/2017.
 */

public class FavouriteFragment extends MVPFragment<FavouriteContract.IPresenterViewOps> implements FavouriteContract.IViewOps, TopicItemListAdapter.Callback {

    private RecyclerView recyclerView;
    public TopicItemListAdapter adapter;

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
        getPresenter().ExcuteDictionaryTask();
    }

    @Override
    public int GetLayoutId() {
        return R.layout.favourite_fragment;
    }

    @Override
    protected FavouriteContract.IPresenterViewOps OnRegisterPresenter() {
        return new FavouritePresenter();
    }

    @Override
    public void InsertData(ArrayList<Word> listItem) {
        adapter.InsertData(listItem);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected String GetScreenTitle() {
        return "Favourite";
    }

    @Override
    public void OnClickDetailItem(Word word, int adapterPosition) {
        SwitchFragment(new DetailWordFragment(word), true);
    }
}
