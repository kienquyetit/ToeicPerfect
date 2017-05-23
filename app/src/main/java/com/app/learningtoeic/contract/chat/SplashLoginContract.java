package com.app.learningtoeic.contract.chat;

import com.app.learningtoeic.mvp.fragment.IFragmentPresenterViewOps;
import com.app.learningtoeic.mvp.fragment.IFragmentViewOps;

/**
 * Created by QUYET on 5/19/2017.
 */

public class SplashLoginContract {
    public interface IViewOps extends IFragmentViewOps
    {
        void onLoginSuccess();
        void onLoginFailed();
    }
    public interface IPresenterViewOps extends IFragmentPresenterViewOps
    {
        void handleLogin(String userEmail, String userPassword);
    }
}
