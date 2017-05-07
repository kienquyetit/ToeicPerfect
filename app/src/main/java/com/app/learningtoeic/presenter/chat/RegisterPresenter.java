package com.app.learningtoeic.presenter.chat;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.app.learningtoeic.contract.chat.RegisterContract;
import com.app.learningtoeic.entity.User;
import com.app.learningtoeic.mvp.fragment.FragmentPresenter;
import com.app.learningtoeic.ui.adapter.UsersChatAdapter;
import com.app.learningtoeic.utils.ChatHelper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

/**
 * Created by QUYET on 4/22/2017.
 */

public class RegisterPresenter extends FragmentPresenter<RegisterContract.IViewOps> implements RegisterContract.IPresenterViewOps  {

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    private Activity mActivity;

    private String  mUserDisplayName, mUserEmailRegister;

    public RegisterPresenter(){
        setAuthInstance();
    }

    public RegisterPresenter(Activity activity){
        this.mActivity = activity;
        setAuthInstance();
        setDatabaseInstance();
    }

    private void setDatabaseInstance() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    private void setAuthInstance() {
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void handleSignUp(String userDisplayName, String userEmailRegister, String userPasswordRegister) {
        getView().showAlertDialog("Registering...",true);
        this.mUserDisplayName = userDisplayName;
        this.mUserEmailRegister = userEmailRegister;
        mAuth.createUserWithEmailAndPassword(userEmailRegister, userPasswordRegister).addOnCompleteListener(mActivity, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                getView().dismissAlertDialog();
                if(task.isSuccessful()){
                    onAuthSuccess(task.getResult().getUser());
                }else {
                    getView().showAlertDialog(task.getException().getMessage(), true);
                }
            }
        });
    }

    private void onAuthSuccess(FirebaseUser user) {
        createNewUser(user.getUid());
        getView().onSignUpSuccess();
    }

    private void createNewUser(String userId){
        User user = buildNewUser();
        mDatabase.child("users").child(userId).setValue(user);
    }

    private User buildNewUser() {
        return new User(
                ChatHelper.generateRandomAvatarForUser(),
                UsersChatAdapter.ONLINE,
                mUserDisplayName,
                mUserEmailRegister,
                Calendar.getInstance().getTime().getTime()
        );
    }
}
