package com.app.learningtoeic.contract.chat;

import com.app.learningtoeic.entity.ChatMessage;
import com.app.learningtoeic.mvp.fragment.IFragmentPresenterViewOps;
import com.app.learningtoeic.mvp.fragment.IFragmentViewOps;

/**
 * Created by QUYET on 4/27/2017.
 */

public class ChatRoomContract {
    public interface IViewOps extends IFragmentViewOps
    {
        void notifyMessageChatRoomAdapter(ChatMessage newMessage);
    }
    public interface IPresenterViewOps extends IFragmentPresenterViewOps
    {
        void createMessageChatRoomDatabase(String chatRef);
        void onMessageChatRoomListener(String currentUserId);
        void pushMessageChatDatabase(ChatMessage newMessage);
        void removeEventListener();
    }
}
