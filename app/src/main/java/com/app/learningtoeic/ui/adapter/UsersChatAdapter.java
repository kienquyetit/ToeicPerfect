package com.app.learningtoeic.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.learningtoeic.R;
import com.app.learningtoeic.entity.User;
import com.app.learningtoeic.utils.ChatHelper;

import java.util.List;

/**
 * Created by QUYET on 4/23/2017.
 */

public class UsersChatAdapter extends RecyclerView.Adapter<UsersChatAdapter.ViewHolderUsers> {

    public static final String ONLINE = "online";
    public static final String OFFLINE = "offline";
    private List<User> mUsers;
    private Context mContext;
    private String mCurrentUserEmail;
    private Long mCurrentUserCreatedAt;
    private String mCurrentUserId;

    public Callback callback;

    public interface Callback{
        void OnClickDetailItem(String currentUserId, String recipientId, String chatRef);
    }

    public UsersChatAdapter(Context context, List<User> fireChatUsers, UsersChatAdapter.Callback callback) {
        mUsers = fireChatUsers;
        mContext = context;
        this.callback = callback;
    }

    @Override
    public ViewHolderUsers onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolderUsers(mContext, LayoutInflater.from(parent.getContext()).inflate(R.layout.user_profile, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolderUsers holder, int position) {

        User fireChatUser = mUsers.get(position);

        // Set avatar
        int userAvatarId = ChatHelper.getDrawableAvatarId(fireChatUser.getAvatarId());
        Drawable avatarDrawable = ContextCompat.getDrawable(mContext, userAvatarId);
        holder.getUserAvatar().setImageDrawable(avatarDrawable);

        // Set display name
        holder.getUserDisplayName().setText(fireChatUser.getDisplayName());

        // Set presence status
        holder.getStatusConnection().setText(fireChatUser.getConnection());

        // Set presence text color
        if (fireChatUser.getConnection().equals(ONLINE)) {
            // Green color
            holder.getStatusConnection().setTextColor(Color.parseColor("#00FF00"));
        } else {
            // Red color
            holder.getStatusConnection().setTextColor(Color.parseColor("#FF0000"));
        }

    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public void refill(User users) {
        mUsers.add(users);
        notifyDataSetChanged();
    }

    public void changeUser(int index, User user) {
        mUsers.set(index, user);
        notifyDataSetChanged();
    }

    public void setCurrentUserInfo(String userUid, String email, long createdAt) {
        mCurrentUserId = userUid;
        mCurrentUserEmail = email;
        mCurrentUserCreatedAt = createdAt;
    }

    public void clear() {
        mUsers.clear();
    }


    /* ViewHolder for RecyclerView */
    public class ViewHolderUsers extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView mUserAvatar;
        private TextView mUserDisplayName;
        private TextView mStatusConnection;
        private Context mContextViewHolder;

        public ViewHolderUsers(Context context, View itemView) {
            super(itemView);
            mUserAvatar = (ImageView) itemView.findViewById(R.id.img_avatar);
            mUserDisplayName = (TextView) itemView.findViewById(R.id.text_view_display_name);
            mStatusConnection = (TextView) itemView.findViewById(R.id.text_view_connection_status);
            mContextViewHolder = context;

            itemView.setOnClickListener(this);
        }

        public ImageView getUserAvatar() {
            return mUserAvatar;
        }

        public TextView getUserDisplayName() {
            return mUserDisplayName;
        }

        public TextView getStatusConnection() {
            return mStatusConnection;
        }


        @Override
        public void onClick(View view) {

            User user = mUsers.get(getLayoutPosition());

            String chatRef = user.createUniqueChatRef(mCurrentUserCreatedAt, mCurrentUserEmail);

            callback.OnClickDetailItem(mCurrentUserId, user.getRecipientId(), chatRef);
        }
    }

}
