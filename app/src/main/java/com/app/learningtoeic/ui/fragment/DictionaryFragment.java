package com.app.learningtoeic.ui.fragment;

import android.app.ProgressDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.app.learningtoeic.R;
import com.app.learningtoeic.contract.DictionaryContract;
import com.app.learningtoeic.entity.Word;
import com.app.learningtoeic.mvp.fragment.MVPFragment;
import com.app.learningtoeic.presenter.DictionaryPresenter;
import com.app.learningtoeic.ui.adapter.DictionaryAdapter;

import java.util.ArrayList;

/**
 * Created by dell on 3/31/2017.
 */

public class DictionaryFragment extends MVPFragment<DictionaryContract.IPresenterViewOps> implements DictionaryContract.IViewOps,DictionaryAdapter.Callback
{
    ProgressDialog progressDialog;
    private RecyclerView recyclerView;
    public DictionaryAdapter adapter;

    public DictionaryFragment() {
        // Required empty public constructor
    }

    @Override
    protected void OnViewCreated() {
        adapter = new DictionaryAdapter();
        adapter.callback = this;
        recyclerView.setAdapter(adapter);
        getPresenter().ExcuteDictionaryTask();
    }

    @Override
    protected void OnBindView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView = (RecyclerView)FindViewById(R.id.recycle_view_vocabulary);
        recyclerView.setLayoutManager(linearLayoutManager);
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
    protected DictionaryContract.IPresenterViewOps OnRegisterPresenter() {
        return new DictionaryPresenter();
    }

    @Override
    public void InsertData(ArrayList<Word> listItem) {
        adapter.InsertData(listItem);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void ShowProgressDialog() {
        progressDialog = ProgressDialog.show(getContext(), "", "File loading ...",false, false);
    }

    @Override
    public void HideProgessDialog() {
        progressDialog.dismiss();
    }

    @Override
    public void OnClickDetailItem(Word word) {
        SwitchFragment(new DetailWordFragment(word, this),true);
    }
}
