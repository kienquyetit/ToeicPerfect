package com.app.learningtoeic.contract.chat;

import com.app.learningtoeic.entity.User;
import com.app.learningtoeic.mvp.fragment.IFragmentPresenterViewOps;
import com.app.learningtoeic.mvp.fragment.IFragmentViewOps;

/**
 * Created by QUYET on 4/22/2017.
 */

public class ContactsContract {
    public interface IViewOps extends IFragmentViewOps
    {
        void hideProgressBarForUsers();
        void setCurrentUserInfo(String userUid, String email);
        void refill(User users);
        void changeUser(int index, User user);
        void goToLogin();
    }
    public interface IPresenterViewOps extends IFragmentPresenterViewOps
    {
        void setUserOffline();
        void addAuthStateListener();
        void clearCurrentUsers();
        void removeListener();
    }
}
