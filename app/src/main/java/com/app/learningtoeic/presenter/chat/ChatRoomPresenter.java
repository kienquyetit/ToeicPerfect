package com.app.learningtoeic.presenter.chat;

import com.app.learningtoeic.contract.chat.ChatRoomContract;
import com.app.learningtoeic.entity.ChatMessage;
import com.app.learningtoeic.mvp.fragment.FragmentPresenter;
import com.app.learningtoeic.ui.adapter.MessageChatAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by QUYET on 4/27/2017.
 */

public class ChatRoomPresenter extends FragmentPresenter<ChatRoomContract.IViewOps> implements ChatRoomContract.IPresenterViewOps {

    private DatabaseReference messageChatRoomDatabase;
    private ChildEventListener messageChatListener;

    @Override
    public void createMessageChatRoomDatabase(String chatRef) {
        messageChatRoomDatabase = FirebaseDatabase.getInstance().getReference(chatRef);
    }

    @Override
    public void onMessageChatRoomListener(final String currentUserId) {
        messageChatListener = messageChatRoomDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if(dataSnapshot.exists()){
                    ChatMessage chatMessage = dataSnapshot.getValue(ChatMessage.class);
                    if(chatMessage.getUserId().equals(currentUserId)){
                        chatMessage.setRecipientOrSenderStatus(MessageChatAdapter.SENDER);
                    }
                    else{
                        chatMessage.setRecipientOrSenderStatus(MessageChatAdapter.RECIPIENT);
                    }
                    getView().notifyMessageChatRoomAdapter(chatMessage);
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void pushMessageChatDatabase(ChatMessage newMessage) {
        messageChatRoomDatabase.push().setValue(newMessage);
    }

    @Override
    public void removeEventListener() {
        if(messageChatListener != null) {
            messageChatRoomDatabase.removeEventListener(messageChatListener);
        }
    }
}
