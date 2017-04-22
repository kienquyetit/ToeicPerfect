package com.app.learningtoeic.ui.fragment.chat;

import com.app.learningtoeic.R;
import com.app.learningtoeic.contract.chat.ChatContract;
import com.app.learningtoeic.mvp.fragment.MVPFragment;
import com.app.learningtoeic.presenter.chat.ChatPresenter;

/**
 * Created by dell on 4/1/2017.
 */

public class ChatFragment extends MVPFragment<ChatContract.IPresenterViewOps> implements ChatContract.IViewOps
{
    @Override
    protected void OnViewCreated() {

    }

    @Override
    protected void OnBindView() {

    }

    @Override
    public int GetLayoutId() {
        return R.layout.chat_fragment;
    }

    @Override
    protected ChatContract.IPresenterViewOps OnRegisterPresenter() {
        return new ChatPresenter();
    }

    @Override
    protected String GetScreenTitle() {
        return "Chat Room";
    }

}
