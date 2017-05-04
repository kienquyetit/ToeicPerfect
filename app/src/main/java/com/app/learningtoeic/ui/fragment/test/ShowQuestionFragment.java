package com.app.learningtoeic.ui.fragment.test;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.util.Log;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.app.learningtoeic.R;
import com.app.learningtoeic.contract.test.ShowQuestionContract;
import com.app.learningtoeic.contract.test.TestContract;
import com.app.learningtoeic.entity.Topic;
import com.app.learningtoeic.entity.Word;
import com.app.learningtoeic.mvp.fragment.MVPFragment;
import com.app.learningtoeic.presenter.test.ShowQuestionPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 5/1/2017.
 */

public class ShowQuestionFragment extends MVPFragment<ShowQuestionContract.IPresenterViewOps> implements ShowQuestionContract.IViewOps, View.OnClickListener {
    TextView tvScore;
    List<Topic> topicList = new ArrayList<>();
    List<Word> listAnsweredQuestion = new ArrayList<>();
    ArrayList<Integer> listTypeAnswerId = new ArrayList<>();
    TextView tvQuestionNum, tvQuestion;
    ImageView imgRenewQuestion, imgSpeechAudio, imgWord;
    RadioButton tvAnswer1, tvAnswer2, tvAnswer3, tvAnswer4;
    RadioGroup wrapRadio;
    TextView btnSubmit;
    int countQuestion = 0;
    int answeredCount = 0;
    Chronometer timeCountUp;

    public ShowQuestionFragment(List<Topic> topicList) {
        this.topicList = topicList;
    }

    @Override
    protected ShowQuestionContract.IPresenterViewOps OnRegisterPresenter() {
        return new ShowQuestionPresenter(topicList);
    }

    @Override
    protected void OnViewCreated() {
        getPresenter().ImplementQuestion();
    }

    @Override
    protected void OnBindView() {
        timeCountUp = (Chronometer) FindViewById(R.id.time_count);
        timeCountUp.start();
        Log.d("avav", timeCountUp.getBase() + "");
        tvScore = (TextView) FindViewById(R.id.scrore_count);
        tvQuestionNum = (TextView) FindViewById(R.id.number_of_ques);
        tvQuestion = (TextView) FindViewById(R.id.question);
        imgRenewQuestion = (ImageView) FindViewById(R.id.renew_question);
        imgSpeechAudio = (ImageView) FindViewById(R.id.audio_question);
        imgWord = (ImageView) FindViewById(R.id.img_ques);
        tvScore = (TextView) FindViewById(R.id.scrore_count);
        tvScore = (TextView) FindViewById(R.id.scrore_count);
        tvScore = (TextView) FindViewById(R.id.scrore_count);
        tvScore = (TextView) FindViewById(R.id.scrore_count);
        tvAnswer1 = (RadioButton) FindViewById(R.id.radio_1);
        tvAnswer1.setOnClickListener(this);
        tvAnswer2 = (RadioButton) FindViewById(R.id.radio_2);
        tvAnswer2.setOnClickListener(this);
        tvAnswer3 = (RadioButton) FindViewById(R.id.radio_3);
        tvAnswer3.setOnClickListener(this);
        tvAnswer4 = (RadioButton) FindViewById(R.id.radio_4);
        tvAnswer4.setOnClickListener(this);
        wrapRadio = (RadioGroup) FindViewById(R.id.wrap_radio_btn);
        btnSubmit = (TextView) FindViewById(R.id.submit_tv);
        btnSubmit.setVisibility(View.GONE);
    }

    @Override
    public int GetLayoutId() {
        return R.layout.test_question_fragment;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.radio_1:
                getPresenter().PostAnswer(0);
                break;
            case R.id.radio_2:
                getPresenter().PostAnswer(1);
                break;
            case R.id.radio_3:
                getPresenter().PostAnswer(2);
                break;
            case R.id.radio_4:
                getPresenter().PostAnswer(3);
                break;
        }
    }

    public int GetAnsweredCount() {
        return answeredCount;
    }

    public void AddAnsweredCount() {
        answeredCount++;
    }

    public void ResetQuestion() {
        answeredCount = 0;
        for (int i = 0; i < wrapRadio.getChildCount(); i++) {
            wrapRadio.getChildAt(i).setEnabled(true);
        }
        tvAnswer1.setTextColor(getResources().getColor(R.color.black));
        tvAnswer2.setTextColor(getResources().getColor(R.color.black));
        tvAnswer3.setTextColor(getResources().getColor(R.color.black));
        tvAnswer4.setTextColor(getResources().getColor(R.color.black));
        wrapRadio.clearCheck();
    }

    @Override
    public int GetQuestionCount() {
        return countQuestion;
    }

    @Override
    public void AddAnsweredQuestionId(Word answeredQuestion) {
        listAnsweredQuestion.add(answeredQuestion);
    }

    public List<Word> getAnsweredQuestionList() {
        return listAnsweredQuestion;
    }

    @Override
    public void AddTypeId(int questionId) {
        listTypeAnswerId.add(questionId);
    }

    public ArrayList<Integer> GetType() {
        return listTypeAnswerId;
    }

    @Override
    public void IncreaseCountQuestion() {
        countQuestion++;
    }

    public void SetWordImgate(String wordName) {
        imgWord.setImageResource(getContext().getResources().getIdentifier(wordName, "mipmap", getContext().getPackageName()));
    }

    public void SetTvQuestionNum(String questionNum) {
        tvQuestionNum.setText(questionNum);
    }

    public void SetTvQuestion(String question) {
        tvQuestion.setText(question);
    }

    public void SetTvAnswer1(String answer) {
        tvAnswer1.setText(answer);
    }

    public void SetTvAnswer2(String answer) {
        tvAnswer2.setText(answer);
    }

    public void SetTvAnswer3(String answer) {
        tvAnswer3.setText(answer);
    }

    public void SetTvAnswer4(String answer) {
        tvAnswer4.setText(answer);
    }

    @Override
    public void setFalseAnswer(int indexRadio) {
        switch (indexRadio) {
            case 0:
                tvAnswer1.setEnabled(false);
                break;
            case 1:
                tvAnswer2.setEnabled(false);
                break;
            case 2:
                tvAnswer3.setEnabled(false);
                break;
            case 3:
                tvAnswer4.setEnabled(false);
                break;
        }
    }

    @Override
    public void setRightAnswer(int indexRadio) {
        switch (indexRadio) {
            case 0:
                tvAnswer1.setTextColor(getResources().getColor(R.color.primary));
                tvAnswer1.setEnabled(false);
                break;
            case 1:
                tvAnswer2.setTextColor(getResources().getColor(R.color.primary));
                tvAnswer2.setEnabled(false);
                break;
            case 2:
                tvAnswer3.setTextColor(getResources().getColor(R.color.primary));
                tvAnswer3.setEnabled(false);
                break;
            case 3:
                tvAnswer4.setTextColor(getResources().getColor(R.color.primary));
                tvAnswer4.setEnabled(false);
                break;
        }
    }

    public void ShowScore() {
        // Prepare the View for the animation
        btnSubmit.setVisibility(View.VISIBLE);

// Start the animation
        btnSubmit.animate()
                .translationX(FindViewById(R.id.wrap_score_tv).getWidth()/2)
                .setDuration(1000)
                .alpha(1)
                .withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        btnSubmit.setVisibility(View.GONE);
                        btnSubmit.animate()
                                .translationX(0);
                    }
                });
        ////final android.os.Handler handler = new android.os.Handler();
        // handler.postDelayed(new Runnable() {
        //    @Override
        //  public void run() {

        // }
        // }, 2000);
    }
}
