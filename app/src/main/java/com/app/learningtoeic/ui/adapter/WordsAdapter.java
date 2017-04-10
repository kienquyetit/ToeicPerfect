package com.app.learningtoeic.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.learningtoeic.R;
import com.app.learningtoeic.contract.DetailWordContract;
import com.app.learningtoeic.entity.Word;
import com.app.learningtoeic.ui.fragment.DetailWordFragment;
import com.app.learningtoeic.utils.Config;

import java.util.ArrayList;

/**
 * Created by dell on 4/8/2017.
 */

public class WordsAdapter extends RecyclerView.Adapter {

    public Callback callback;

    public interface Callback{
        void OnClickDetailItem(ArrayList<Word> wordList,int wordId);
    }
    private ArrayList<Word> mWordsList;

    public WordsAdapter() {
        mWordsList = new ArrayList<>();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vocabulary_row, parent, false);
        return new MyViewHolder(view,callback);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof MyViewHolder)
        {
            MyViewHolder viewHolder = (MyViewHolder) holder;
            viewHolder.BindView(mWordsList.get(position));
        }
    }

    public void InsertData(ArrayList<Word> listItem)
    {
        mWordsList.addAll(listItem);
    }

    @Override
    public int getItemCount() {
        return mWordsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView ivDescriptivePicture, ivFavourite, ivDetail;
        TextView tvVocabulary, tvVocalization, tvExplanation, tvTranslate;
        Word word;
        Callback callback;
        public MyViewHolder(final View itemView, final Callback callback) {
            super(itemView);
            this.callback = callback;
            ivDescriptivePicture = (ImageView) itemView.findViewById(R.id.ivDescriptivePicture);
            ivDetail = (ImageView) itemView.findViewById(R.id.ivDetail);
            ivFavourite = (ImageView) itemView.findViewById(R.id.ivFavourite);
            tvVocabulary = (TextView) itemView.findViewById(R.id.tvVocabulary);
            tvVocalization = (TextView) itemView.findViewById(R.id.tvVocalization);
            tvExplanation = (TextView) itemView.findViewById(R.id.tvExplanation);
            tvTranslate = (TextView) itemView.findViewById(R.id.tvTranslate);

            ivFavourite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (word.getFavourite() == 0) {
                        word.setFavourite(1);
                        Config.wordDB.update(word);
                        ivFavourite.setImageResource(R.drawable.ic_favorite_red);
                    } else {
                        word.setFavourite(0);
                        Config.wordDB.update(word);
                        ivFavourite.setImageResource(R.drawable.ic_favorite_grey);
                    }
                }
            });
            ivDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callback.OnClickDetailItem(mWordsList,word.getId());
                    /*DetailWordFragment fragment = new DetailWordFragment();
                    Bundle bundle = new Bundle();
                    bundle.putParcelableArrayList(Config.KEY_VOCABULARY_DATA,mWordsList);
                    bundle.putInt(Config.KEY_VOCABULARY_ID,word.getId());
                    fragment.setArguments(bundle);*/
                }
            });
        }

        public void BindView(Word word)
        {
            this.word =word;
            String namePicture = word.getVocabulary().replace(' ', '_');
            ivDescriptivePicture.setImageResource(itemView.getContext().getResources().getIdentifier(namePicture, "mipmap", itemView.getContext().getPackageName()));


            tvVocabulary.setText(word.getVocabulary().toString());
            tvVocalization.setText(word.getVocalization().toString());
            tvExplanation.setText(word.getExplanation().toString() + " ");
            tvTranslate.setText(word.getTranslate().toString());

            if (word.getFavourite() == 0) {
                ivFavourite.setImageResource(R.drawable.ic_favorite_grey);
            } else if (word.getFavourite() == 1) {
                ivFavourite.setImageResource(R.drawable.ic_favorite_red);
            }


            ivDetail.setImageResource(R.drawable.ic_keyboard_arrow_right_grey600_48dp);

        }
    }
}
