package com.app.learningtoeic.ui.fragment.chat;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.app.learningtoeic.R;
import com.app.learningtoeic.contract.chat.ChatContract;
import com.app.learningtoeic.entity.ChatMessage;
import com.app.learningtoeic.mvp.fragment.MVPFragment;
import com.app.learningtoeic.presenter.chat.ChatPresenter;
import com.app.learningtoeic.ui.adapter.MessageChatAdapter;

import java.util.ArrayList;

/**
 * Created by dell on 4/1/2017.
 */

public class ChatFragment extends MVPFragment<ChatContract.IPresenterViewOps> implements ChatContract.IViewOps, View.OnClickListener
{

    private RecyclerView mChatRecyclerView;
    private EditText mUserMessageChatText;
    private Button btnSendMessage;

    private String mCurrentUserId;
    private String mRecipientId;
    private String chatRef;


    private MessageChatAdapter messageChatAdapter;

    public ChatFragment(String currentUserId, String recipientId, String chatRef){
        this.mCurrentUserId = currentUserId;
        this.mRecipientId = recipientId;
        this.chatRef = chatRef;
    }

    @Override
    protected void OnViewCreated() {
        setDatabaseInstance();
        setChatRecyclerView();
        btnSendMessage.setOnClickListener(this);
    }

    private void setChatRecyclerView() {
        mChatRecyclerView.setLayoutManager(new LinearLayoutManager(GetMainAcitivity()));
        mChatRecyclerView.setHasFixedSize(true);
        messageChatAdapter = new MessageChatAdapter(new ArrayList<ChatMessage>());
        mChatRecyclerView.setAdapter(messageChatAdapter);
    }

    private void setDatabaseInstance() {
        getPresenter().createMessageChatDatabase(chatRef);
    }

    @Override
    protected void OnBindView() {
        BindView();
    }

    private void BindView() {
        mChatRecyclerView = (RecyclerView) FindViewById(R.id.recycler_view_chat);
        mUserMessageChatText = (EditText) FindViewById(R.id.edit_text_message);
        btnSendMessage = (Button) FindViewById(R.id.btn_send_message);
    }

    @Override
    public void onStart() {
        super.onStart();
        getPresenter().onMessageChatListener(mCurrentUserId);
    }

    @Override
    public void onStop() {
        super.onStop();
        getPresenter().removeEventListener();
        messageChatAdapter.cleanUp();
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_send_message:
                handleSendMessage();
                break;
            default:
                break;
        }
    }

    public void handleSendMessage(){
        String senderMessage = mUserMessageChatText.getText().toString().trim();
        if(!senderMessage.isEmpty()){
            ChatMessage newMessage = new ChatMessage(senderMessage, mCurrentUserId, mRecipientId);
            getPresenter().pushMessageChatDatabase(newMessage);
            mUserMessageChatText.setText("");
        }
    }

    @Override
    public void notifyMessageChatAdapter(ChatMessage newMessage) {
        messageChatAdapter.refillAdapter(newMessage);
        mChatRecyclerView.scrollToPosition(messageChatAdapter.getItemCount()-1);
    }

    @Override
    public boolean IsBackButtonVisible() {
        return true;
    }

    @Override
    public boolean IsHeaderVisible() {
        return true;
    }
}
