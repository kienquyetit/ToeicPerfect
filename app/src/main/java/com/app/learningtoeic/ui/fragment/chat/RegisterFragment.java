package com.app.learningtoeic.ui.fragment.chat;

import com.app.learningtoeic.R;
import com.app.learningtoeic.contract.chat.RegisterContract;
import com.app.learningtoeic.mvp.fragment.MVPFragment;
import com.app.learningtoeic.presenter.chat.RegisterPresenter;

/**
 * Created by QUYET on 4/22/2017.
 */

public class RegisterFragment extends MVPFragment<RegisterContract.IPresenterViewOps> implements RegisterContract.IViewOps  {
    @Override
    protected void OnViewCreated() {

    }

    @Override
    protected void OnBindView() {

    }

    @Override
    public int GetLayoutId() {
        return R.layout.register_fragment;
    }

    @Override
    protected RegisterContract.IPresenterViewOps OnRegisterPresenter() {
        return new RegisterPresenter();
    }
}
