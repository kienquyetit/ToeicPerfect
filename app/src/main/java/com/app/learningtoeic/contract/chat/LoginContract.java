package com.app.learningtoeic.contract.chat;

import com.app.learningtoeic.mvp.fragment.IFragmentPresenterViewOps;
import com.app.learningtoeic.mvp.fragment.IFragmentViewOps;

/**
 * Created by QUYET on 4/22/2017.
 */

public class LoginContract {
    public interface IViewOps extends IFragmentViewOps
    {
        void onLoginSuccess();
        void showAlertDialog(String message, boolean isCancelable);
        void dismissAlertDialog();
    }
    public interface IPresenterViewOps extends IFragmentPresenterViewOps
    {
        void handleLogin(String userEmail, String userPassword);
    }
}
