package com.app.learningtoeic.ui.fragment.chat;

import com.app.learningtoeic.R;
import com.app.learningtoeic.contract.chat.ContactsContract;
import com.app.learningtoeic.mvp.fragment.MVPFragment;
import com.app.learningtoeic.presenter.chat.ContactsPresenter;

/**
 * Created by QUYET on 4/22/2017.
 */

public class ContactsFragment extends MVPFragment<ContactsContract.IPresenterViewOps> implements ContactsContract.IViewOps  {
    @Override
    protected void OnViewCreated() {

    }

    @Override
    protected void OnBindView() {

    }

    @Override
    public int GetLayoutId() {
        return R.layout.contacts_fragment;
    }

    @Override
    protected ContactsContract.IPresenterViewOps OnRegisterPresenter() {
        return new ContactsPresenter();
    }
}
