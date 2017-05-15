package com.app.learningtoeic.presenter.chat;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.app.learningtoeic.contract.chat.ChangePasswordContract;
import com.app.learningtoeic.mvp.fragment.FragmentPresenter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by QUYET on 5/8/2017.
 */

public class ChangePasswordPresenter extends FragmentPresenter<ChangePasswordContract.IViewOps> implements ChangePasswordContract.IPresenterViewOps {

    private Activity mActivity;

    public ChangePasswordPresenter(Activity activity){
        this.mActivity = activity;
    }

    @Override
    public void handleChangePassword(String currentPassword, String newPassword) {
        getView().showAlertDialog("Registering...", true);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        user.updatePassword(newPassword).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    getView().onChangePasswordSuccess();
                }
                else{
                    getView().onChangePasswordFailed();
                }
            }
        });
    }
}
