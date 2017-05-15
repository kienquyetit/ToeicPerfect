package com.app.learningtoeic.ui.fragment.chat;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.app.learningtoeic.R;
import com.app.learningtoeic.contract.chat.LoginContract;
import com.app.learningtoeic.mvp.fragment.MVPFragment;
import com.app.learningtoeic.presenter.chat.LoginPresenter;
import com.app.learningtoeic.utils.Config;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by QUYET on 4/22/2017.
 */

public class LoginFragment extends MVPFragment<LoginContract.IPresenterViewOps> implements LoginContract.IViewOps, View.OnClickListener {

    private EditText mUserEmail, mUserPassWord;
    private Button btnLogin, btnRegister;
    private CheckBox chkRememberPassword;
    private TextView tvForgotPass;

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
        if (isCheckRememberPassword()){
            onLogInUser(getUserEmailPref(), getUserPasswordPref());
        }
        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
        tvForgotPass.setOnClickListener(this);
    }

    private String getUserEmailPref(){
        return GetMainAcitivity().getSharedPreferences(Config.KEY_USER_INFO, Context.MODE_PRIVATE)
                .getString(Config.KEY_EMAIL, "");
    }

    private String getUserPasswordPref(){
        return GetMainAcitivity().getSharedPreferences(Config.KEY_USER_INFO, Context.MODE_PRIVATE)
                .getString(Config.KEY_PASSWORD, "");
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
        chkRememberPassword = (CheckBox) FindViewById(R.id.check_box_remember_pass);
        tvForgotPass = (TextView) FindViewById(R.id.text_view_forgot_pass);
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
                onLogInUser(getUserEmail(), getUserPassword());
                break;
            case R.id.btn_register:
                goToRegisterFragment();
                break;
            case R.id.text_view_forgot_pass:
                goToForgotPassword();
                break;
            default:
                break;
        }
    }

    private void goToForgotPassword() {
        AlertDialog.Builder builder = new AlertDialog.Builder(GetMainAcitivity());
        View view = GetMainAcitivity().getLayoutInflater().inflate(R.layout.dialog_forgot_pass_layout, null);
        final EditText userEmail = (EditText) view.findViewById(R.id.edit_text_user_email);
        Button btnSend = (Button) view.findViewById(R.id.btn_send_pass_to_email);
        Button btnCancel = (Button) view.findViewById(R.id.btn_cancel_forgot_pass);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().handleForgotPassword(userEmail.getText().toString());
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        builder.setView(view);
        dialog = builder.create();
        dialog.show();
    }

    private void onLogInUser(String userEmail, String userPassword) {
        if(userEmail.equals("") || userPassword.equals("")){
            showFieldsAreRequired();
        }else {
            getPresenter().handleLogin(userEmail, userPassword);
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

    public boolean isCheckRememberPassword() {
        return GetMainAcitivity().getSharedPreferences(Config.KEY_USER_INFO, Context.MODE_PRIVATE)
                .getBoolean(Config.KEY_CHECK_REMEMBER_PASS, false);
    }

    @Override
    public void onLoginSuccess(FirebaseUser user) {
        saveSharedPreferencesOfUser(user);
        if(chkRememberPassword.isChecked())
            SwitchFragment(new ChatRoomFragment(), false);
        else
            SwitchFragment(new ChatRoomFragment(), true);
    }

    private void saveSharedPreferencesOfUser(FirebaseUser user) {
        SharedPreferences pref = GetMainAcitivity().getSharedPreferences(Config.KEY_USER_INFO, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(Config.KEY_USER_ID, user.getUid());
        editor.putString(Config.KEY_PASSWORD, mUserPassWord.getText().toString());
        editor.putString(Config.KEY_DISPLAY_NAME, user.getDisplayName());
        editor.putString(Config.KEY_EMAIL, user.getEmail());
        if(chkRememberPassword.isChecked())
            editor.putBoolean(Config.KEY_CHECK_REMEMBER_PASS, true);
        else
            editor.putBoolean(Config.KEY_CHECK_REMEMBER_PASS, false);
        editor.commit();
    }

    @Override
    public void showAlertDialog(String message, boolean isCancelable) {
        dialog = Config.buildAlertDialog(getString(R.string.login_error_title), message,isCancelable, GetActivityContext());
        dialog.show();
    }

    @Override
    public void dismissAlertDialog() {
        dialog.dismiss();
    }

    @Override
    public boolean IsBackButtonVisible() {
        return true;
    }
}
