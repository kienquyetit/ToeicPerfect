package com.app.learningtoeic.ui.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.learningtoeic.R;
import com.app.learningtoeic.entity.ChatMessage;
import com.app.learningtoeic.utils.ChatHelper;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by QUYET on 4/23/2017.
 */

public class MessageChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ChatMessage> mChatList;
    private Context mContext;

    public static final int SENDER = 0;
    public static final int RECIPIENT = 1;

    public MessageChatAdapter(Context context, List<ChatMessage> listOfFireChats){
        this.mContext = context;
        this.mChatList = listOfFireChats;
    }

    @Override
    public int getItemViewType(int position) {
        if (mChatList.get(position).getRecipientOrSenderStatus() == SENDER) {
            return SENDER;
        } else {
            return RECIPIENT;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        switch (viewType) {
            case SENDER:
                View viewSender = inflater.inflate(R.layout.layout_sender_message, viewGroup, false);
                viewHolder = new ViewHolderSender(viewSender);
                break;
            case RECIPIENT:
                View viewRecipient = inflater.inflate(R.layout.layout_recipient_message, viewGroup, false);
                viewHolder = new ViewHolderRecipient(viewRecipient);
                break;
            default:
                View viewSenderDefault = inflater.inflate(R.layout.layout_sender_message, viewGroup, false);
                viewHolder = new ViewHolderSender(viewSenderDefault);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        switch (viewHolder.getItemViewType()) {
            case SENDER:
                ViewHolderSender viewHolderSender = (ViewHolderSender) viewHolder;
                configureSenderView(viewHolderSender, position);
                break;
            case RECIPIENT:
                ViewHolderRecipient viewHolderRecipient = (ViewHolderRecipient) viewHolder;
                configureRecipientView(viewHolderRecipient, position);
                break;
        }
    }

    private void configureSenderView(ViewHolderSender viewHolderSender, int position) {
        ChatMessage senderFireMessage = mChatList.get(position);
        viewHolderSender.getSenderMessageTextView().setText(senderFireMessage.getMessage());
        viewHolderSender.getmSenderTimeStampTextView().setText(convertTimeStamp(senderFireMessage.getTimeStamp()));
        viewHolderSender.getSenderAvatarCircleImageView().setImageDrawable(getAvatarDrawable(senderFireMessage.getUrlAvatarImage()));
    }

    private void configureRecipientView(ViewHolderRecipient viewHolderRecipient, int position) {
        ChatMessage recipientFireMessage = mChatList.get(position);
        viewHolderRecipient.getRecipientMessageTextView().setText(recipientFireMessage.getMessage());
        viewHolderRecipient.getmRecipientTimeStampTextView().setText(convertTimeStamp(recipientFireMessage.getTimeStamp()));
        viewHolderRecipient.getRecipientAvatarCircleImageView().setImageDrawable(getAvatarDrawable(recipientFireMessage.getUrlAvatarImage()));
    }

    private CharSequence convertTimeStamp(String timeMillis){
        return DateUtils.getRelativeTimeSpanString(Long.parseLong(timeMillis),System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS);
    }

    private Drawable getAvatarDrawable(int avatarId){
        int userAvatarId = ChatHelper.getDrawableAvatarId(avatarId);
        Drawable avatarDrawable = ContextCompat.getDrawable(mContext, userAvatarId);
        return avatarDrawable;
    }

    @Override
    public int getItemCount() {
        return mChatList.size();
    }


    public void refillAdapter(ChatMessage newFireChatMessage) {

        /*add new message chat to list*/
        mChatList.add(newFireChatMessage);

        /*refresh view*/
        notifyItemInserted(getItemCount() - 1);
    }

    public void cleanUp() {
        mChatList.clear();
    }

    /*==============ViewHolder===========*/

    /*ViewHolder for Sender*/

    public class ViewHolderSender extends RecyclerView.ViewHolder {

        private TextView mSenderMessageTextView, mSenderTimeStampTextView;
        private CircleImageView mSenderAvatarCircleImageView;

        public ViewHolderSender(View itemView) {
            super(itemView);
            mSenderMessageTextView = (TextView) itemView.findViewById(R.id.text_view_sender_message);
            mSenderTimeStampTextView = (TextView) itemView.findViewById(R.id.tv_time_stamp_sender_message);
            mSenderAvatarCircleImageView = (CircleImageView) itemView.findViewById(R.id.iv_avatar_sender_message);
        }

        public TextView getSenderMessageTextView() {
            return mSenderMessageTextView;
        }

        public TextView getmSenderTimeStampTextView() {
            return mSenderTimeStampTextView;
        }

        public CircleImageView getSenderAvatarCircleImageView() {
            return mSenderAvatarCircleImageView;
        }
    }

    /*ViewHolder for Recipient*/
    public class ViewHolderRecipient extends RecyclerView.ViewHolder {

        private TextView mRecipientMessageTextView, mRecipientTimeStampTextView;
        private CircleImageView mRecipientAvatarCircleImageView;

        public ViewHolderRecipient(View itemView) {
            super(itemView);
            mRecipientMessageTextView = (TextView) itemView.findViewById(R.id.text_view_recipient_message);
            mRecipientTimeStampTextView = (TextView) itemView.findViewById(R.id.tv_time_stamp_recipient_message);
            mRecipientAvatarCircleImageView = (CircleImageView) itemView.findViewById(R.id.iv_avatar_recipient_message);
        }

        public TextView getRecipientMessageTextView() {
            return mRecipientMessageTextView;
        }

        public TextView getmRecipientTimeStampTextView() {
            return mRecipientTimeStampTextView;
        }

        public CircleImageView getRecipientAvatarCircleImageView() {
            return mRecipientAvatarCircleImageView;
        }
    }
}