package com.app.learningtoeic.ui.fragment.chat;

import com.app.learningtoeic.R;
import com.app.learningtoeic.contract.chat.LoginContract;
import com.app.learningtoeic.mvp.fragment.MVPFragment;
import com.app.learningtoeic.presenter.chat.LoginPresenter;

/**
 * Created by QUYET on 4/22/2017.
 */

public class LoginFragment extends MVPFragment<LoginContract.IPresenterViewOps> implements LoginContract.IViewOps {
    @Override
    protected void OnViewCreated() {

    }

    @Override
    protected void OnBindView() {

    }

    @Override
    public int GetLayoutId() {
        return R.layout.login_fragment;
    }

    @Override
    protected LoginContract.IPresenterViewOps OnRegisterPresenter() {
        return new LoginPresenter();
    }
}
