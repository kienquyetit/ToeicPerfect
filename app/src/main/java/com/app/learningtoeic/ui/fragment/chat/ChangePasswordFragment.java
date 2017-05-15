package com.app.learningtoeic.ui.fragment.chat;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.app.learningtoeic.R;
import com.app.learningtoeic.contract.chat.ChangePasswordContract;
import com.app.learningtoeic.mvp.fragment.MVPFragment;
import com.app.learningtoeic.presenter.chat.ChangePasswordPresenter;
import com.app.learningtoeic.utils.Config;

/**
 * Created by QUYET on 5/7/2017.
 */

public class ChangePasswordFragment extends MVPFragment<ChangePasswordContract.IPresenterViewOps> implements ChangePasswordContract.IViewOps, View.OnClickListener {

    private EditText txtCurrentPassword, txtNewPassword, txtVerifyPassword;
    private Button btnChangePassword, btnCancel;

    private AlertDialog dialog;

    @Override
    protected void OnViewCreated() {
        btnChangePassword.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    @Override
    protected void OnBindView() {
        BindView();
    }

    private void BindView() {
        txtCurrentPassword = (EditText) FindViewById(R.id.edit_text_current_password);
        txtNewPassword = (EditText) FindViewById(R.id.edit_text_new_password);
        txtVerifyPassword = (EditText) FindViewById(R.id.edit_text_verify_password);
        btnChangePassword = (Button) FindViewById(R.id.btn_change_password);
        btnCancel = (Button) FindViewById(R.id.btn_cancel_change_password);
    }

    @Override
    public int GetLayoutId() {
        return R.layout.change_password_fragment;
    }

    @Override
    protected ChangePasswordContract.IPresenterViewOps OnRegisterPresenter() {
        return new ChangePasswordPresenter(GetMainAcitivity());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_change_password:
                onChangePassword();
                break;
            case R.id.btn_cancel_change_password:
                GetMainAcitivity().onBackPressed();
                break;
            default:
                break;
        }
    }

    private void onChangePassword() {
        if(getCurrentUserPassword().equals("") || getNewUserPassword().equals("") || getVerifyUserPassword().equals("")) {
            showAlertDialog(getString(R.string.error_pass_fields_empty), true);
        } else if(getCurrentUserPassword().length() < 6 || getNewUserPassword().length() < 6 || getVerifyUserPassword().length() < 6) {
            showAlertDialog(getString(R.string.error_incorrect_pass), true);
        } else if(!getCurrentUserPassword().equals(getCurrentPassword())){
            showAlertDialog(getString(R.string.error_incorrect_current_pass), true);
        } else if(!getNewUserPassword().equals(getVerifyUserPassword())) {
            showAlertDialog(getString(R.string.error_incorrect_verify_pass), true);
        } else{
            getPresenter().handleChangePassword(getCurrentUserPassword(), getNewUserPassword());
        }
    }

    public void showAlertDialog(String message, boolean isCancelable) {
        dialog = Config.buildAlertDialog(getString(R.string.login_error_title), message, isCancelable, GetMainAcitivity());
        dialog.show();
    }

    @Override
    public void onChangePasswordSuccess() {
        dialog.setMessage("Change password successfully!");
        txtCurrentPassword.setText("");
        txtNewPassword.setText("");
        txtVerifyPassword.setText("");
    }

    @Override
    public void onChangePasswordFailed() {
        showAlertDialog(getString(R.string.error_change_password_failed), true);
    }

    private String getCurrentUserPassword() {
        return txtCurrentPassword.getText().toString().trim();
    }

    private String getNewUserPassword() {
        return txtNewPassword.getText().toString().trim();
    }

    private String getVerifyUserPassword() {
        return txtVerifyPassword.getText().toString().trim();
    }

    private String getCurrentPassword() {
        SharedPreferences preferences = GetMainAcitivity().getSharedPreferences(Config.KEY_USER_INFO, Context.MODE_PRIVATE);
        return preferences.getString(Config.KEY_PASSWORD, "");
    }
}
