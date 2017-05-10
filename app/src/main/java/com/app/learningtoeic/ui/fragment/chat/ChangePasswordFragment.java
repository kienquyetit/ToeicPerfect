package com.app.learningtoeic.ui.fragment.chat;

import com.app.learningtoeic.R;
import com.app.learningtoeic.contract.chat.ChangePasswordContract;
import com.app.learningtoeic.mvp.fragment.MVPFragment;
import com.app.learningtoeic.presenter.chat.ChangePasswordPresenter;

/**
 * Created by QUYET on 5/7/2017.
 */

public class ChangePasswordFragment extends MVPFragment<ChangePasswordContract.IPresenterViewOps> implements ChangePasswordContract.IViewOps {

    @Override
    protected void OnViewCreated() {

    }

    @Override
    protected void OnBindView() {

    }

    @Override
    public int GetLayoutId() {
        return R.layout.change_password_fragment;
    }

    @Override
    protected ChangePasswordContract.IPresenterViewOps OnRegisterPresenter() {
        return new ChangePasswordPresenter();
    }

}
