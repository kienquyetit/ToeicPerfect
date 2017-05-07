package com.app.learningtoeic.entity;

import com.google.firebase.database.Exclude;

/**
 * Created by QUYET on 4/23/2017.
 */

public class ChatMessage {

    private String message;
    private String sender;
    private String recipient;
    private String timeStamp;
    private String senderName;
    private int urlAvatarImage;

    private int mRecipientOrSenderStatus;

    public ChatMessage() {
    }

    public ChatMessage(String message, String sender, String recipient,String senderName, int urlAvatarImage, String timeStamp) {
        this.message = message;
        this.recipient = recipient;
        this.sender = sender;
        this.senderName = senderName;
        this.urlAvatarImage = urlAvatarImage;
        this.timeStamp = timeStamp;
    }

    public ChatMessage(String message, String sender, String senderName, int urlAvatarImage, String timeStamp) {
        this.message = message;
        this.sender = sender;
        this.timeStamp = timeStamp;
        this.senderName = senderName;
        this.urlAvatarImage = urlAvatarImage;
    }

    public void setRecipientOrSenderStatus(int recipientOrSenderStatus) {
        this.mRecipientOrSenderStatus = recipientOrSenderStatus;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public String getRecipient(){
        return recipient;
    }

    public String getSender(){
        return sender;
    }

    public String getSenderName() {
        return senderName;
    }

    public int getUrlAvatarImage() {
        return urlAvatarImage;
    }

    @Exclude
    public int getRecipientOrSenderStatus() {
        return mRecipientOrSenderStatus;
    }
}
