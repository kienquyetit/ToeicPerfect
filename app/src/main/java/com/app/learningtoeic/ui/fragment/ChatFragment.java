package com.app.learningtoeic.ui.fragment;

import com.app.learningtoeic.R;
import com.app.learningtoeic.base.BaseFragment;
import com.app.learningtoeic.contract.HomeContract;
import com.app.learningtoeic.mvp.fragment.MVPFragment;
import com.app.learningtoeic.presenter.HomePresenter;
import com.squareup.picasso.Downloader;

/**
 * Created by dell on 4/1/2017.
 */

public class ChatFragment extends MVPFragment<HomeContract.IPresenterViewOps> implements HomeContract.IViewOps
{
    @Override
    protected void OnViewCreated() {

    }

    @Override
    protected void OnBindView() {

    }

    @Override
    public int GetLayoutId() {
        return R.layout.dictionary_fragment;
    }

    @Override
    protected HomeContract.IPresenterViewOps OnRegisterPresenter() {
        return new HomePresenter();
    }

    @Override
    protected String GetScreenTitle() {
        return getString(R.string.app_name);
    }

}
