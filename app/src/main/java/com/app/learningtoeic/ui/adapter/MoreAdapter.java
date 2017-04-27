package com.app.learningtoeic.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.learningtoeic.R;
import com.app.learningtoeic.entity.MoreItem;

import java.util.ArrayList;

/**
 * Created by QUYET on 4/21/2017.
 */

public class MoreAdapter extends RecyclerView.Adapter {

    ArrayList<MoreItem> mMoreList;

    public Callback callback;

    public interface Callback
    {
        void OnMoreItemClick(int position);
    }

    public MoreAdapter(){
        mMoreList = new ArrayList<>();
    }

    public void InsertData(ArrayList<MoreItem> items){
        if(items.size() == 0){
            return;
        }
        mMoreList = items;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.more_row, parent, false);
        return new MyViewHolder(view, callback);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof MyViewHolder){
            MyViewHolder myViewHolder = (MyViewHolder) holder;
            myViewHolder.BindView(mMoreList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mMoreList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tvTitleItemMore;
        ImageView ivItemMore;
        View moreRowLayout;
        Callback callback;

        public MyViewHolder(View itemView, final Callback callback) {
            super(itemView);
            this.callback = callback;
            tvTitleItemMore = (TextView) itemView.findViewById(R.id.tv_title_item_more);
            ivItemMore = (ImageView) itemView.findViewById(R.id.iv_item_more);
            moreRowLayout = itemView.findViewById(R.id.wrap_more_row);
            moreRowLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callback.OnMoreItemClick(getAdapterPosition());
                }
            });
        }

        public void BindView(MoreItem moreItem){
            tvTitleItemMore.setText(moreItem.getTitle());
            ivItemMore.setImageResource(moreItem.getImageId());
        }
    }
}
