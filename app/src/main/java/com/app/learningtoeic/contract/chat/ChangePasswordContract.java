package com.app.learningtoeic.contract.chat;

import com.app.learningtoeic.mvp.fragment.IFragmentPresenterViewOps;
import com.app.learningtoeic.mvp.fragment.IFragmentViewOps;

/**
 * Created by QUYET on 5/8/2017.
 */

public class ChangePasswordContract {
    public interface IViewOps extends IFragmentViewOps
    {
        void showAlertDialog(String message, boolean isCancelable);
        void onChangePasswordSuccess();
        void onChangePasswordFailed();

    }
    public interface IPresenterViewOps extends IFragmentPresenterViewOps
    {
        void handleChangePassword(String currentPassword, String newPassword);
    }
}
