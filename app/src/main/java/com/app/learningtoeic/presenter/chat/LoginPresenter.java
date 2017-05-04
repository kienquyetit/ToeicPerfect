package com.app.learningtoeic.presenter.chat;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.app.learningtoeic.contract.chat.LoginContract;
import com.app.learningtoeic.mvp.fragment.FragmentPresenter;
import com.app.learningtoeic.ui.adapter.UsersChatAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by QUYET on 4/22/2017.
 */

public class LoginPresenter extends FragmentPresenter<LoginContract.IViewOps> implements LoginContract.IPresenterViewOps {

    private FirebaseAuth mAuth;
    private Activity mActivity;

    public LoginPresenter() {
        setAuthInstance();
    }

    public LoginPresenter(Activity activity) {
        setAuthInstance();
        this.mActivity = activity;
    }

    private void setAuthInstance() {
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void handleLogin(String userEmail, String userPassword) {
        getView().showAlertDialog("Log In...", false);

        mAuth.signInWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener(mActivity, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                getView().dismissAlertDialog();

                if (task.isSuccessful()) {
                    setUserOnline();
                    getView().onLoginSuccess();
                } else {
                    getView().showAlertDialog(task.getException().getMessage(), true);
                }
            }
        });
    }

    private void setUserOnline() {
        if (mAuth.getCurrentUser() != null) {
            String userId = mAuth.getCurrentUser().getUid();
            FirebaseDatabase.getInstance()
                    .getReference().
                    child("users").
                    child(userId).
                    child("connection").
                    setValue(UsersChatAdapter.ONLINE);
        }
    }
}
