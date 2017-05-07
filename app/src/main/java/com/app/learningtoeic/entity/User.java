package com.app.learningtoeic.entity;

import com.google.firebase.database.Exclude;

/**
 * Created by QUYET on 4/23/2017.
 */

public class User {

    private String displayName;
    private String email;
    private String connection;
    private int avatarId;
    private long timeStamp;

    private String mRecipientId;

    public User() {
    }

    public User(int avatarId, String connection, String displayName, String email, long timeStamp){
        this.avatarId = avatarId;
        this.connection = connection;
        this.displayName = displayName;
        this.email = email;
        this.timeStamp = timeStamp;
    }

    public String createUniqueChatRef(long createdAtCurrentUser, String currentUserEmail) {
        String uniqueChatRef="";
        if(createdAtCurrentUser > getTimeStamp()){
            uniqueChatRef = cleanEmailAddress(currentUserEmail)+"-"+cleanEmailAddress(getUserEmail());
        }else {

            uniqueChatRef=cleanEmailAddress(getUserEmail())+"-"+cleanEmailAddress(currentUserEmail);
        }
        return uniqueChatRef;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    private String cleanEmailAddress(String email) {
        //replace dot with comma since firebase does not allow dot
        return email.replace(".", "-");
    }

    private String getUserEmail() {
        //Log.e("user email  ", userEmail);
        return email;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getEmail() {
        return email;
    }

    public String getConnection() {
        return connection;
    }

    public int getAvatarId() {
        return avatarId;
    }

    @Exclude
    public String getRecipientId() {
        return mRecipientId;
    }

    public void setRecipientId(String recipientId) {
        this.mRecipientId = recipientId;
    }
}
