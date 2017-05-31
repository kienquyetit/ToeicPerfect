package com.app.learningtoeic.ui.adapter;

import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.learningtoeic.R;
import com.app.learningtoeic.entity.Word;
import com.app.learningtoeic.utils.Config;

import java.util.ArrayList;

/**
 * Created by dell on 4/8/2017.
 */

public class DictionaryAdapter extends RecyclerView.Adapter {

    public final int HEADER_VIEW = 0;
    public final int NORMAL_VIEW = 1;

    public Callback callback;

    public interface Callback {
        void OnClickDetailItem(Word word);
        void OnSearchWord(String text);
    }

    @Override
    public int getItemViewType(int position) {
        if (IsPositionHeader(position)) {
            return HEADER_VIEW;
        }
        return NORMAL_VIEW;
    }

    boolean IsPositionHeader(int position) {
        return position == 0;
    }

    private ArrayList<Word> mWordsList;

    public DictionaryAdapter() {
        mWordsList = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        if (viewType == HEADER_VIEW) {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_dictionary_header, parent, false);
            return new HeaderViewHolder(v,callback);
        }
        if (viewType == NORMAL_VIEW) {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.vocabulary_row, parent, false);
            return new ViewHolderItem(v, callback);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolderItem) {
            ViewHolderItem viewHolder = (ViewHolderItem) holder;
            viewHolder.BindView(mWordsList.get(position-1));
        } else if (holder instanceof HeaderViewHolder) {
            HeaderViewHolder viewHolder = (HeaderViewHolder) holder;
            viewHolder.BindView();
        }
    }

    public void InsertData(ArrayList<Word> listItem) {
        this.mWordsList = listItem;
    }

    @Override
    public int getItemCount() {
        return mWordsList.size() + 1;
    }

    public class HeaderViewHolder extends RecyclerView.ViewHolder {
        EditText edtSearch;
        Callback callBack;
        String keyword = "";
        public HeaderViewHolder(final View itemView, final Callback callBack) {
            super(itemView);
            this.callBack= callBack;
            edtSearch = (EditText) itemView.findViewById(R.id.search_et);
            edtSearch.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if(edtSearch.getText().length()>=1) {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if (edtSearch.getText().length() >= 1 && edtSearch.getText().length() != keyword.length())
                                {
                                    callBack.OnSearchWord(edtSearch.getText().toString());
                                }
                                keyword = edtSearch.getText().toString();
                            }
                        }, 1500);
                    }
                    else
                    {
                        callBack.OnSearchWord("");
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }

        public void BindView()
        {
            edtSearch.requestFocus();
            edtSearch.requestFocusFromTouch();
        }
    }


    public class ViewHolderItem extends RecyclerView.ViewHolder {
        ImageView ivDescriptivePicture, ivFavourite, ivDetail;
        TextView tvVocabulary, tvVocalization, tvExplanation, tvTranslate;
        Word word;
        Callback callback;
        RelativeLayout wrapWord;

        public ViewHolderItem(final View itemView, final Callback callback) {
            super(itemView);
            this.callback = callback;
            wrapWord = (RelativeLayout)itemView.findViewById(R.id.wrap_word_layout);
            wrapWord.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Config.wordDB.ListeningWord(word,itemView.getContext());
                }
            });
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
                    callback.OnClickDetailItem(word);
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