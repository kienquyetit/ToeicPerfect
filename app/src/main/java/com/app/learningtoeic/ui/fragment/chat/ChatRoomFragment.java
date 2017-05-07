package com.app.learningtoeic.ui.fragment.chat;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.app.learningtoeic.R;
import com.app.learningtoeic.contract.chat.ChatRoomContract;
import com.app.learningtoeic.entity.ChatMessage;
import com.app.learningtoeic.entity.User;
import com.app.learningtoeic.mvp.fragment.MVPFragment;
import com.app.learningtoeic.presenter.chat.ChatRoomPresenter;
import com.app.learningtoeic.ui.adapter.MessageChatAdapter;
import com.app.learningtoeic.utils.Config;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by QUYET on 4/27/2017.
 */

public class ChatRoomFragment extends MVPFragment<ChatRoomContract.IPresenterViewOps> implements ChatRoomContract.IViewOps, View.OnClickListener {

    private RecyclerView mChatRoomRecyclerView;
    private EditText mUserMessageChatText;
    private Button btnSendMessage;

    private MessageChatAdapter messageChatAdapter;

    @Override
    protected void OnViewCreated() {
        setDatabaseInstance();
        setChatRoomRecyclerView();
        btnSendMessage.setOnClickListener(this);
    }

    private void setDatabaseInstance() {
        getPresenter().createMessageChatRoomDatabase(Config.KEY_ROOM_ONE);
    }

    private void setChatRoomRecyclerView() {
        mChatRoomRecyclerView.setLayoutManager(new LinearLayoutManager(GetMainAcitivity()));
        mChatRoomRecyclerView.setHasFixedSize(true);
        messageChatAdapter = new MessageChatAdapter(GetActivityContext(), new ArrayList<ChatMessage>());
        mChatRoomRecyclerView.setAdapter(messageChatAdapter);
    }

    @Override
    protected void OnBindView() {
        BindView();
    }

    public void BindView(){
        mChatRoomRecyclerView = (RecyclerView) FindViewById(R.id.recycler_view_chat_room);
        mUserMessageChatText = (EditText) FindViewById(R.id.edit_text_message_chat_room);
        btnSendMessage = (Button) FindViewById(R.id.btn_send_message_chat_room);
    }

    @Override
    public void onStart() {
        super.onStart();
        getPresenter().onMessageChatRoomListener(getCurrentUserId());
    }

    private String getCurrentUserId() {
        SharedPreferences pref = GetMainAcitivity().getSharedPreferences(Config.KEY_USER_INFO, Context.MODE_PRIVATE);
        return pref.getString(Config.KEY_USER_ID, "");
    }

    private User getCurrentUserInfo() {
        SharedPreferences sharedPreferences = GetMainAcitivity().getSharedPreferences(Config.KEY_USER_INFO, Context.MODE_PRIVATE);
        int avatarId = sharedPreferences.getInt(Config.KEY_AVATAR_ID, 0);
        String connection = sharedPreferences.getString(Config.KEY_CONNECTION, "");
        String displayName = sharedPreferences.getString(Config.KEY_DISPLAY_NAME, "");
        String email = sharedPreferences.getString(Config.KEY_EMAIL, "");
        long timeStamp = sharedPreferences.getLong(Config.KEY_TIME_STAMP, 0);
        return new User(avatarId, connection, displayName, email, timeStamp);
    }

    @Override
    public void onStop() {
        super.onStop();
        getPresenter().removeEventListener();
        messageChatAdapter.cleanUp();
    }

    @Override
    public int GetLayoutId() {
        return R.layout.chat_room_fragment;
    }

    @Override
    protected ChatRoomContract.IPresenterViewOps OnRegisterPresenter() {
        return new ChatRoomPresenter();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_send_message_chat_room:
                handleSendMessage();
                break;
            default:
                break;
        }
    }

    public void handleSendMessage(){
        String senderMessage = mUserMessageChatText.getText().toString().trim();
        if(!senderMessage.isEmpty()){
            ChatMessage newMessage = new ChatMessage(senderMessage, getCurrentUserId(), getCurrentUserInfo().getDisplayName(),
                    getCurrentUserInfo().getAvatarId(),Calendar.getInstance().getTime().getTime() + "");
            getPresenter().pushMessageChatDatabase(newMessage);
            mUserMessageChatText.setText("");
        }
    }

    @Override
    public void notifyMessageChatRoomAdapter(ChatMessage newMessage) {
        messageChatAdapter.refillAdapter(newMessage);
        mChatRoomRecyclerView.scrollToPosition(messageChatAdapter.getItemCount()-1);
    }

    @Override
    public boolean IsBackButtonVisible() {
        return true;
    }
}
