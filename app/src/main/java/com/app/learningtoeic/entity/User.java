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
    private long createdAt;

    private String mRecipientId;

    public User() {
    }

    public User(String displayName, String email, String connection, int avatarId, long createdAt) {
        this.displayName = displayName;
        this.email = email;
        this.connection = connection;
        this.avatarId = avatarId;
        this.createdAt = createdAt;
    }


    public String createUniqueChatRef(String currentUserEmail) {
        return cleanEmailAddress(currentUserEmail) + "-" + cleanEmailAddress(getUserEmail());
    }

    public long getCreatedAt() {
        return createdAt;
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
