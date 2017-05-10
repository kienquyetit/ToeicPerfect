package com.app.learningtoeic.ui.fragment.chat;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.app.learningtoeic.R;
import com.app.learningtoeic.contract.chat.ContactsContract;
import com.app.learningtoeic.entity.User;
import com.app.learningtoeic.mvp.fragment.MVPFragment;
import com.app.learningtoeic.presenter.chat.ContactsPresenter;
import com.app.learningtoeic.ui.adapter.UsersChatAdapter;
import com.app.learningtoeic.utils.Config;

import java.util.ArrayList;

/**
 * Created by QUYET on 4/22/2017.
 */

public class ContactsFragment extends MVPFragment<ContactsContract.IPresenterViewOps> implements ContactsContract.IViewOps, UsersChatAdapter.Callback  {

    private ProgressBar mProgressBarForUsers;
    private RecyclerView mUsersRecyclerView;

    private UsersChatAdapter mUsersChatAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        showActionBar();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private void showActionBar() {
        GetMainAcitivity().getSupportActionBar().show();
    }

    @Override
    protected void OnViewCreated() {
        setUserRecyclerView();
    }

    private void setUserRecyclerView() {
        mUsersChatAdapter = new UsersChatAdapter(GetMainAcitivity(), new ArrayList<User>(), this);
        mUsersRecyclerView.setLayoutManager(new LinearLayoutManager(GetMainAcitivity()));
        mUsersRecyclerView.setHasFixedSize(true);
        mUsersRecyclerView.setAdapter(mUsersChatAdapter);
    }

    @Override
    protected void OnBindView() {
        BindView();
    }

    private void BindView() {
        mProgressBarForUsers = (ProgressBar) FindViewById(R.id.progress_bar_users);
        mUsersRecyclerView = (RecyclerView) FindViewById(R.id.recycler_view_users);
    }

    @Override
    public void onStart() {
        super.onStart();
        showProgressBarForUsers();
        getPresenter().addAuthStateListener();
    }

    private void showProgressBarForUsers() {
        mProgressBarForUsers.setVisibility(View.VISIBLE);
    }

    @Override
    public void onStop() {
        super.onStop();
        mUsersChatAdapter.clear();
        getPresenter().clearCurrentUsers();
        getPresenter().removeListener();
    }

    @Override
    public int GetLayoutId() {
        return R.layout.contacts_fragment;
    }

    @Override
    protected ContactsContract.IPresenterViewOps OnRegisterPresenter() {
        return new ContactsPresenter();
    }

    @Override
    public void hideProgressBarForUsers() {
        if(mProgressBarForUsers.getVisibility()==View.VISIBLE) {
            mProgressBarForUsers.setVisibility(View.GONE);
        }
    }

    @Override
    public void setCurrentUserInfo(String userId, User currentUser) {
        saveSharedPreferencesOfUser(userId, currentUser);
        mUsersChatAdapter.setCurrentUserInfo(currentUser);
    }

    private void saveSharedPreferencesOfUser(String userId, User user) {
        SharedPreferences pref = GetMainAcitivity().getSharedPreferences(Config.KEY_USER_INFO, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(Config.KEY_USER_ID, userId);
        editor.putInt(Config.KEY_AVATAR_ID, user.getAvatarId());
        editor.putString(Config.KEY_CONNECTION, user.getConnection());
        editor.putString(Config.KEY_DISPLAY_NAME, user.getDisplayName());
        editor.putString(Config.KEY_EMAIL, user.getEmail());
        editor.putLong(Config.KEY_TIME_STAMP, user.getTimeStamp());
        editor.commit();
    }

    @Override
    public void refill(User users) {
        mUsersChatAdapter.refill(users);
    }

    @Override
    public void changeUser(int index, User user) {
        mUsersChatAdapter.changeUser(index, user);
    }

    @Override
    public void goToLogin() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        logout();
    }

    private void logout() {
        showProgressBarForUsers();
        getPresenter().setUserOffline();
    }

    @Override
    protected String GetScreenTitle() {
        return getString(R.string.title_chat);
    }

    @Override
    public boolean IsMenuVisible() {
        return true;
    }

    @Override
    public boolean IsHeaderVisible() {
        return true;
    }

    @Override
    public void OnClickDetailItem(String recipientUserId, String chatRef) {
        SwitchFragment(new ChatFragment(recipientUserId, chatRef), true);
    }
}
