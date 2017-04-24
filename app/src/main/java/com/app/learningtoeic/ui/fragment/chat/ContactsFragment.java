package com.app.learningtoeic.ui.fragment.chat;

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
    public void setCurrentUserInfo(String userUid, String email, long createdAt) {
        mUsersChatAdapter.setCurrentUserInfo(userUid, email, createdAt);
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
    public boolean IsBackButtonVisible() {
        return true;
    }

    @Override
    public boolean IsHeaderVisible() {
        return true;
    }

    @Override
    public void OnClickDetailItem(String currentUserId, String recipientId, String chatRef) {
        SwitchFragment(new ChatFragment(currentUserId, recipientId, chatRef), true);
    }
}
