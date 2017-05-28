package com.app.learningtoeic.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.learningtoeic.R;
import com.app.learningtoeic.entity.Word;
import com.app.learningtoeic.utils.Config;

import java.util.ArrayList;

/**
 * Created by QUYET on 5/16/2017.
 */

public class TopicItemListAdapter extends RecyclerView.Adapter {

    public Callback callback;

    public interface Callback {
        void OnClickDetailItem(Word word, int adapterPosition);
    }

    private ArrayList<Word> mWordsList;

    public TopicItemListAdapter() {
        mWordsList = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.vocabulary_row, parent, false);
        return new ViewHolderItem(viewItem, callback);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolderItem) {
            ViewHolderItem viewHolder = (ViewHolderItem) holder;
            viewHolder.BindView(mWordsList.get(position));
        }
    }

    public void InsertData(ArrayList<Word> listItem) {
        this.mWordsList = listItem;
    }

    @Override
    public int getItemCount() {
        return mWordsList.size();
    }

    public class ViewHolderItem extends RecyclerView.ViewHolder {
        ImageView ivDescriptivePicture, ivFavourite, ivDetail;
        TextView tvVocabulary, tvVocalization, tvExplanation, tvTranslate;
        Word word;
        View wrapView;
        Callback callback;
        public ViewHolderItem(final View itemView, final Callback callback) {
            super(itemView);
            wrapView = itemView.findViewById(R.id.wrap_word_layout);
            wrapView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Config.wordDB.ListeningWord(word,itemView.getContext());
                }
            });
            this.callback = callback;
            ivDescriptivePicture = (ImageView) itemView.findViewById(R.id.ivDescriptivePicture);
            ivDetail = (ImageView) itemView.findViewById(R.id.iv_detail);
            ivFavourite = (ImageView) itemView.findViewById(R.id.iv_favourite);
            tvVocabulary = (TextView) itemView.findViewById(R.id.tv_vocabulary);
            tvVocalization = (TextView) itemView.findViewById(R.id.tv_vocalization);
            tvExplanation = (TextView) itemView.findViewById(R.id.tv_explanation);
            tvTranslate = (TextView) itemView.findViewById(R.id.tv_translate);

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
                    callback.OnClickDetailItem(word, getAdapterPosition());
                }
            });
        }

        public void BindView(Word word) {
            this.word = word;
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
