package com.app.learningtoeic.presenter.chat;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.app.learningtoeic.contract.chat.RegisterContract;
import com.app.learningtoeic.mvp.fragment.FragmentPresenter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

/**
 * Created by QUYET on 4/22/2017.
 */

public class RegisterPresenter extends FragmentPresenter<RegisterContract.IViewOps> implements RegisterContract.IPresenterViewOps  {

    private FirebaseAuth mAuth;

    private Activity mActivity;

    public RegisterPresenter(Activity activity){
        setAuthInstance();
        this.mActivity = activity;
    }

    private void setAuthInstance() {
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void handleSignUp(final String userDisplayName, String userEmailRegister, String userPasswordRegister) {
        getView().showAlertDialog("Registering...",true);
        mAuth.createUserWithEmailAndPassword(userEmailRegister, userPasswordRegister).addOnCompleteListener(mActivity, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                getView().dismissAlertDialog();
                if(task.isSuccessful()){
                    onAuthSuccess(task.getResult().getUser(), userDisplayName);
                }else {
                    getView().showAlertDialog(task.getException().getMessage(), true);
                }
            }
        });
    }

    private void onAuthSuccess(FirebaseUser user, String userDisplayName){
        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(userDisplayName)
                .build();
        user.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            getView().onSignUpSuccess();
                        }
                        else {
                            getView().onSignUpFailed();
                        }
                    }
                });
    }
}
