package com.app.learningtoeic.ui.fragment.chat;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.app.learningtoeic.R;
import com.app.learningtoeic.contract.chat.LoginContract;
import com.app.learningtoeic.mvp.fragment.MVPFragment;
import com.app.learningtoeic.presenter.chat.LoginPresenter;
import com.app.learningtoeic.utils.ChatHelper;

/**
 * Created by QUYET on 4/22/2017.
 */

public class LoginFragment extends MVPFragment<LoginContract.IPresenterViewOps> implements LoginContract.IViewOps, View.OnClickListener {

    private EditText mUserEmail, mUserPassWord;
    private Button btnLogin, btnRegister;

    private AlertDialog dialog;

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
        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
    }

    @Override
    protected void OnBindView() {
        BindView();
    }

    private void BindView() {
        mUserEmail = (EditText) FindViewById(R.id.edit_text_email_login);
        mUserPassWord = (EditText) FindViewById(R.id.edit_text_password_log_in);
        btnLogin = (Button) FindViewById(R.id.btn_login);
        btnRegister = (Button) FindViewById(R.id.btn_register);
    }

    @Override
    public int GetLayoutId() {
        return R.layout.login_fragment;
    }

    @Override
    protected LoginContract.IPresenterViewOps OnRegisterPresenter() {
        return new LoginPresenter(GetMainAcitivity());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                onLogInUser();
                break;
            case R.id.btn_register:
                goToRegisterFragment();
                break;
            default:
                break;
        }
    }

    private void onLogInUser() {
        if(getUserEmail().equals("") || getUserPassword().equals("")){
            showFieldsAreRequired();
        }else {
            getPresenter().handleLogin(getUserEmail(), getUserPassword());
        }
    }

    private void showFieldsAreRequired() {
        showAlertDialog(getString(R.string.error_incorrect_email_pass),true);
    }

    private void goToRegisterFragment() {
        SwitchFragment(new RegisterFragment(), true);
    }

    private String getUserEmail() {
        return mUserEmail.getText().toString().trim();
    }

    private String getUserPassword() {
        return mUserPassWord.getText().toString().trim();
    }

    @Override
    public void onLoginSuccess() {
        SwitchFragment(new OptionsFragment(), true);
    }

    @Override
    public void showAlertDialog(String message, boolean isCancelable) {
        dialog = ChatHelper.buildAlertDialog(getString(R.string.login_error_title), message,isCancelable, GetActivityContext());
        dialog.show();
    }

    @Override
    public void dismissAlertDialog() {
        dialog.dismiss();
    }
}
