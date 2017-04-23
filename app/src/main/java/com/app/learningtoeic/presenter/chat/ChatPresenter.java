package com.app.learningtoeic.presenter.chat;

import com.app.learningtoeic.contract.chat.ChatContract;
import com.app.learningtoeic.entity.ChatMessage;
import com.app.learningtoeic.mvp.fragment.FragmentPresenter;
import com.app.learningtoeic.ui.adapter.MessageChatAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by QUYET on 4/22/2017.
 */

public class ChatPresenter extends FragmentPresenter<ChatContract.IViewOps> implements ChatContract.IPresenterViewOps {

    private DatabaseReference messageChatDatabase;
    private ChildEventListener messageChatListener;

    public ChatPresenter(){

    }

    @Override
    public void pushMessageChatDatabase(ChatMessage newMessage) {
        messageChatDatabase.push().setValue(newMessage);
    }

    @Override
    public void createMessageChatDatabase(String chatRef) {
        messageChatDatabase = FirebaseDatabase.getInstance().getReference().child(chatRef);
    }

    @Override
    public void onMessageChatListener(final String currentUserId) {
        messageChatListener = messageChatDatabase.limitToFirst(20).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildKey) {

                if(dataSnapshot.exists()){
                    ChatMessage newMessage = dataSnapshot.getValue(ChatMessage.class);
                    if(newMessage.getSender().equals(currentUserId)){
                        newMessage.setRecipientOrSenderStatus(MessageChatAdapter.SENDER);
                    }else{
                        newMessage.setRecipientOrSenderStatus(MessageChatAdapter.RECIPIENT);
                    }
                    getView().notifyMessageChatAdapter(newMessage);
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
    public void removeEventListener() {
        if(messageChatListener != null) {
            messageChatDatabase.removeEventListener(messageChatListener);
        }
    }
}
