package com.app.learningtoeic.ui.fragment.chat;

import android.content.Context;

import com.app.learningtoeic.R;
import com.app.learningtoeic.contract.chat.SplashContract;
import com.app.learningtoeic.mvp.fragment.MVPFragment;
import com.app.learningtoeic.presenter.chat.SplashPresenter;
import com.app.learningtoeic.utils.Config;

/**
 * Created by QUYET on 5/19/2017.
 */

public class SplashFragment extends MVPFragment<SplashContract.IPresenterViewOps> implements SplashContract.IViewOps {

    @Override
    protected void OnViewCreated() {
        if (isCheckRememberPassword()){
            getPresenter().handleLogin(getUserEmailPref(), getUserPasswordPref());
        }
        else {
            SwitchFragment(new LoginFragment(), false);
        }
    }

    @Override
    protected void OnBindView() {

    }

    public boolean isCheckRememberPassword() {
        return GetMainAcitivity().getSharedPreferences(Config.KEY_USER_INFO, Context.MODE_PRIVATE)
                .getBoolean(Config.KEY_CHECK_REMEMBER_PASS, false);
    }

    private String getUserEmailPref(){
        return GetMainAcitivity().getSharedPreferences(Config.KEY_USER_INFO, Context.MODE_PRIVATE)
                .getString(Config.KEY_EMAIL, "");
    }

    private String getUserPasswordPref(){
        return GetMainAcitivity().getSharedPreferences(Config.KEY_USER_INFO, Context.MODE_PRIVATE)
                .getString(Config.KEY_PASSWORD, "");
    }

    @Override
    public int GetLayoutId() {
        return R.layout.splash_fragment;
    }

    @Override
    protected SplashContract.IPresenterViewOps OnRegisterPresenter() {
        return new SplashPresenter(GetMainAcitivity());
    }

    @Override
    public void onLoginSuccess() {
        SwitchFragment(new ChatRoomFragment(), false);
    }

    @Override
    public void onLoginFailed() {
        SwitchFragment(new LoginFragment(), false);
    }
}
