package com.app.learningtoeic.ui.fragment.chat;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.app.learningtoeic.R;
import com.app.learningtoeic.contract.chat.RegisterContract;
import com.app.learningtoeic.mvp.fragment.MVPFragment;
import com.app.learningtoeic.presenter.chat.RegisterPresenter;
import com.app.learningtoeic.utils.ChatHelper;

/**
 * Created by QUYET on 4/22/2017.
 */

public class RegisterFragment extends MVPFragment<RegisterContract.IPresenterViewOps> implements RegisterContract.IViewOps, View.OnClickListener {

    private EditText mDisplayNameRegister, mUserNameRegister, mUserPasswordRegister;
    private Button btnRegister, btnCancel;

    private AlertDialog dialog;

    public RegisterFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        hideActionBar();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private void hideActionBar() {
        GetMainAcitivity().getSupportActionBar().hide();
    }

    @Override
    protected void OnViewCreated() {
        btnRegister.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    @Override
    protected void OnBindView() {
        BindView();
    }

    private void BindView() {
        mDisplayNameRegister = (EditText) FindViewById(R.id.edit_text_display_name);
        mUserNameRegister = (EditText) FindViewById(R.id.edit_text_email_register);
        mUserPasswordRegister = (EditText) FindViewById(R.id.edit_text_password_register);
        btnRegister = (Button) FindViewById(R.id.btn_register_user);
        btnCancel = (Button) FindViewById(R.id.btn_cancel_register);
    }

    @Override
    public int GetLayoutId() {
        return R.layout.register_fragment;
    }

    @Override
    protected RegisterContract.IPresenterViewOps OnRegisterPresenter() {
        return new RegisterPresenter(GetMainAcitivity());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_register_user:
                onRegisterUser();
                break;
            case R.id.btn_cancel_register:
                GetMainAcitivity().onBackPressed();
                break;
            default:
                break;
        }
    }

    private void onRegisterUser() {
        if(getUserDisplayName().equals("") || getUserEmail().equals("") || getUserPassword().equals("")){
            showFieldsAreRequired();
            Log.d("ris", "1");
        }else if(isIncorrectEmail(getUserEmail()) || isIncorrectPassword(getUserPassword())) {
            showIncorrectEmailPassword();
            Log.d("ris", "2");
        }else {
            Log.d("ris", "3");
            getPresenter().handleSignUp(getUserDisplayName(), getUserEmail(), getUserPassword());
        }
    }

    private void showIncorrectEmailPassword() {
        showAlertDialog(getString(R.string.error_incorrect_email_pass), true);
    }

    private boolean isIncorrectEmail(String userEmail) {
        return !android.util.Patterns.EMAIL_ADDRESS.matcher(userEmail).matches();
    }

    private boolean isIncorrectPassword(String userPassword) {
        return !(userPassword.length() >= 6);
    }

    private void showFieldsAreRequired() {
        showAlertDialog(getString(R.string.error_fields_empty), true);
    }

    @Override
    public void onSignUpSuccess() {
        SwitchFragment(new ContactsFragment(), false);
    }


    public void showAlertDialog(String message, boolean isCancelable){
        dialog = ChatHelper.buildAlertDialog(getString(R.string.login_error_title),message,isCancelable,GetMainAcitivity());
        dialog.show();
    }

    public void dismissAlertDialog() {
        dialog.dismiss();
    }


    public String getUserDisplayName() {
        return mDisplayNameRegister.getText().toString().trim();
    }

    public String getUserEmail() {
        return mUserNameRegister.getText().toString().trim();
    }

    private String getUserPassword() {
        return mUserPasswordRegister.getText().toString().trim();
    }
}
