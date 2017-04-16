package com.app.learningtoeic.ui.fragment;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.learningtoeic.R;
import com.app.learningtoeic.contract.DetailWordContract;
import com.app.learningtoeic.contract.DictionaryContract;
import com.app.learningtoeic.entity.Word;
import com.app.learningtoeic.mvp.fragment.MVPFragment;
import com.app.learningtoeic.presenter.DetailWordPresenter;
import com.app.learningtoeic.utils.Config;

import java.io.IOException;

/**
 * Created by dell on 4/8/2017.
 */

public class DetailWordFragment extends MVPFragment<DetailWordContract.IPresenterViewOps> implements DetailWordContract.IViewOps, View.OnClickListener
{

    private Word mWord;

    private ImageView ivDescriptivePicture;
    private TextView tvVocabulary, tvVocalization, tvExplanation, tvTranslate, tvExample, tvExampleTranslate;

    private DictionaryContract.IViewOps callback;

    public DetailWordFragment(Word word, DictionaryContract.IViewOps callback)
    {
        this.mWord = word;
        this.callback = callback;
    }

    @Override
    protected DetailWordContract.IPresenterViewOps OnRegisterPresenter() {
        return new DetailWordPresenter();
    }

    @Override
    protected void OnViewCreated() {
        FetchData();
    }

    private void FetchData(){
        String namePicture = mWord.getVocabulary().replace(' ', '_');
        ivDescriptivePicture.setImageResource(getContext().getResources().getIdentifier(namePicture, "mipmap", getContext().getPackageName()));
        tvVocabulary.setText(mWord.getVocabulary());
        tvVocalization.setText(mWord.getVocalization());
        tvExplanation.setText(mWord.getExplanation());
        tvTranslate.setText(mWord.getTranslate());
        tvExample.setText(mWord.getExample());
        tvExampleTranslate.setText(mWord.getExampleTranslate());
        GetMainAcitivity().getTvTitle().setText(mWord.getVocabulary());
        if(mWord.getFavourite() == 1){
            GetMainAcitivity().getIvLike().setImageResource(R.drawable.ic_favorite_red);
        }
        else{
            GetMainAcitivity().getIvLike().setImageResource(R.drawable.ic_favorite_white);
        }

        // --------------------------
        GetMainAcitivity().getIvLike().setOnClickListener(this);
        GetMainAcitivity().getFabListening().setOnClickListener(this);
        GetMainAcitivity().getFabRecording().setOnClickListener(this);
        GetMainAcitivity().getFabSliding().setOnClickListener(this);
    }

    @Override
    protected void OnBindView() {
        BindView();
    }

    private void BindView(){
        ivDescriptivePicture = (ImageView) FindViewById(R.id.iv_descriptivePicture);
        tvVocabulary = (TextView) FindViewById(R.id.tv_vocabulary);
        tvVocalization = (TextView) FindViewById(R.id.tv_vocalization);
        tvExplanation = (TextView) FindViewById(R.id.tv_explanation);
        tvTranslate = (TextView) FindViewById(R.id.tv_translate);
        tvExample = (TextView) FindViewById(R.id.tv_example);
        tvExampleTranslate = (TextView) FindViewById(R.id.tv_exampleTranslate);
    }

    @Override
    public int GetLayoutId() {
        return R.layout.detail_word_fragment;
    }

    @Override
    protected String GetScreenTitle() {
        return "Detail Word";
    }

    @Override
    public boolean IsImgLikeVisible() {
        return  true;
    }

    @Override
    public boolean IsBackButtonVisible() {
        return true;
    }

    @Override
    public boolean IsListeningButtonVisible() {
        return true;
    }

    @Override
    public boolean IsRecordingButtonVisible() {
        return true;
    }

    @Override
    public boolean IsSlidingButtonVisible() {
        return true;
    }

    @Override
    public void ChangeLikeStatus() {
        if(mWord.getFavourite() == 1){
            GetMainAcitivity().getIvLike().setImageResource(R.drawable.ic_favorite_red);
        }
        else{
            GetMainAcitivity().getIvLike().setImageResource(R.drawable.ic_favorite_white);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvTitle:
                break;
            case R.id.iv_favourite:
                if(mWord.getFavourite() == 1){
                    mWord.setFavourite(0);
                    getPresenter().UpdateLikeStatus(mWord);
                }
                else{
                    mWord.setFavourite(1);
                    getPresenter().UpdateLikeStatus(mWord);
                }
                break;
            case R.id.fab_listening:
                MediaPlayer player = new MediaPlayer();
                AssetFileDescriptor afd = null;
                try {
                    afd = GetMainAcitivity().getAssets().openFd("vocabulary/"+mWord.getVocabulary()+".mp3");
                    player.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                    player.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                player.start();
                break;
            case R.id.fab_recording:
                break;
            case R.id.fab_sliding:
                break;
            default:
                break;
        }
    }

    @Override
    public void onDestroy() {
        callback.InsertData(Config.wordDB.getListWord());
        super.onDestroy();
    }
}