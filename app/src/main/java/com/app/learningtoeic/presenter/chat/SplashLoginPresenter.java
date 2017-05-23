package com.app.learningtoeic.presenter.chat;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.app.learningtoeic.contract.chat.SplashLoginContract;
import com.app.learningtoeic.mvp.fragment.FragmentPresenter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by QUYET on 5/19/2017.
 */

public class SplashLoginPresenter extends FragmentPresenter<SplashLoginContract.IViewOps> implements SplashLoginContract.IPresenterViewOps {

    private FirebaseAuth mAuth;
    private Activity mActivity;

    public SplashLoginPresenter(Activity activity) {
        setAuthInstance();
        this.mActivity = activity;
    }

    private void setAuthInstance() {
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void handleLogin(String userEmail, String userPassword) {
        mAuth.signInWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener(mActivity, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    getView().onLoginSuccess();
                } else {
                    getView().onLoginFailed();
                }
            }
        });
    }
}
