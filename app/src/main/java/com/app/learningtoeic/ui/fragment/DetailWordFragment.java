package com.app.learningtoeic.ui.fragment;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.learningtoeic.R;
import com.app.learningtoeic.contract.DetailWordContract;
import com.app.learningtoeic.contract.DetailWordOfTopicContract.CallBack;
import com.app.learningtoeic.entity.Topic;
import com.app.learningtoeic.entity.Word;
import com.app.learningtoeic.mvp.fragment.MVPFragment;
import com.app.learningtoeic.presenter.DetailWordPresenter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

import static android.app.Activity.RESULT_OK;

/**
 * Created by dell on 4/8/2017.
 */

public class DetailWordFragment extends MVPFragment<DetailWordContract.IPresenterViewOps> implements DetailWordContract.IViewOps, View.OnClickListener{

    private Word mWord;

    private ImageView ivDescriptivePicture;
    private TextView tvVocabulary, tvVocalization, tvExplanation, tvTranslate, tvExample, tvExampleTranslate;

    private Topic mTopic;

    private final int REQ_CODE_SPEECH_OUTPUT = 113;

    private CallBack callback;

    public DetailWordFragment(Word word) {
        this.mWord = word;
    }

    public DetailWordFragment(Word word, Topic topic, CallBack callback) {
        this.mWord = word;
        this.mTopic = topic;
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

    private void FetchData() {
        String namePicture = mWord.getVocabulary().replace(' ', '_');
        ivDescriptivePicture.setImageResource(getContext().getResources().getIdentifier(namePicture, "mipmap", getContext().getPackageName()));
        tvVocabulary.setText(mWord.getVocabulary());
        tvVocalization.setText(mWord.getVocalization());
        tvExplanation.setText(mWord.getExplanation());
        tvTranslate.setText(mWord.getTranslate());
        tvExample.setText(mWord.getExample());
        tvExampleTranslate.setText(mWord.getExampleTranslate());

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

    private void BindView() {
        ivDescriptivePicture = (ImageView) FindViewById(R.id.iv_descriptivePicture);
        tvVocabulary = (TextView) FindViewById(R.id.tv_vocabulary);
        tvVocalization = (TextView) FindViewById(R.id.tv_vocalization);
        tvExplanation = (TextView) FindViewById(R.id.tv_explanation);
        tvTranslate = (TextView) FindViewById(R.id.tv_translate);
        tvExample = (TextView) FindViewById(R.id.tv_example);
        tvExampleTranslate = (TextView) FindViewById(R.id.tv_exampleTranslate);
    }

    @Override
    public void onResume() {
        super.onResume();
        GetLikeStatus(mWord);
    }

    public void GetLikeStatus(Word word){
        if (word.getFavourite() == 1) {
            GetMainAcitivity().getIvLike().setImageResource(R.drawable.ic_favorite_red);
        } else {
            GetMainAcitivity().getIvLike().setImageResource(R.drawable.ic_favorite_white);
        }
    }

    @Override
    public int GetLayoutId() {
        return R.layout.detail_word_fragment;
    }

    @Override
    protected String GetScreenTitle() {
        if (mTopic != null) {
            return mTopic.name;
        } else {
            return mWord.getVocabulary();
        }
    }

    @Override
    public boolean IsImgLikeVisible() {
        return true;
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
        if(mTopic != null){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_favourite:
                setLikeStatus(mWord);
                break;
            case R.id.fab_listening:
                startListening();
                break;
            case R.id.fab_recording:
                startRecording();
                break;
            case R.id.fab_sliding:
                callback.setSlidingViewPager();
                break;
            default:
                break;
        }
    }

    private void startRecording() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hi, Speak now ...");

        try{
            startActivityForResult(intent, REQ_CODE_SPEECH_OUTPUT);
        } catch (ActivityNotFoundException ex){

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case REQ_CODE_SPEECH_OUTPUT:
                if(resultCode == RESULT_OK && data != null){
                    ArrayList<String> voiceText = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                }
                break;
        }
    }

    private void startListening() {
        MediaPlayer player = new MediaPlayer();
        AssetFileDescriptor afd = null;
        try {
            afd = GetMainAcitivity().getAssets().openFd("vocabulary/" + mWord.getVocabulary() + ".mp3");
            player.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            player.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        player.start();
    }

    public void setLikeStatus(Word word) {
        if (word.getFavourite() == 1) {
            word.setFavourite(0);
            getPresenter().UpdateLikeStatus(word);
        } else {
            word.setFavourite(1);
            getPresenter().UpdateLikeStatus(word);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean IsFooterVisible() {
        return true;
    }

    @Override
    public void ChangeLikeStatus() {
        GetLikeStatus(mWord);
    }
}