package com.app.learningtoeic.contract.chat;

import com.app.learningtoeic.entity.ChatMessage;
import com.app.learningtoeic.mvp.fragment.IFragmentPresenterViewOps;
import com.app.learningtoeic.mvp.fragment.IFragmentViewOps;

/**
 * Created by QUYET on 4/22/2017.
 */

public class ChatContract {
    public interface IViewOps extends IFragmentViewOps
    {
        void notifyMessageChatAdapter(ChatMessage newMessage);
    }
    public interface IPresenterViewOps extends IFragmentPresenterViewOps
    {
        void pushMessageChatDatabase(ChatMessage newMessage);
        void createMessageChatDatabase(String chatRef);
        void onMessageChatListener(String currentUserId);
        void removeEventListener();
    }
}
