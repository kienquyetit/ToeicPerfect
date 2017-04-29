package com.app.learningtoeic.ui.fragment.topic;

import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.app.learningtoeic.R;
import com.app.learningtoeic.contract.HomeworkContract;
import com.app.learningtoeic.contract.topic.HomeworkContract;
import com.app.learningtoeic.entity.Word;
import com.app.learningtoeic.mvp.fragment.MVPFragment;
import com.app.learningtoeic.presenter.HomeworkPresenter;
import com.app.learningtoeic.presenter.topic.HomeworkPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 4/15/2017.
 */

public class HomeworkFragment extends MVPFragment<HomeworkContract.IPresenterViewOps> implements HomeworkContract.IViewOps, View.OnClickListener {
    int questionCount = 0;
    List<Word> listAnsweredQuestion = new ArrayList<>();
    ArrayList<Integer> listTypeAnswerId = new ArrayList<>();
    TextView tvTrueCount, tvFalseCount, tvQuestionNum, tvQuestion;
    ImageView imgRenewQuestion, imgSpeechAudio, imgWord;
    RadioButton tvAnswer1, tvAnswer2, tvAnswer3, tvAnswer4;
    RadioGroup wrapRadio;
    TextView submitTv;
    String topicId;
    int indexRadio = -1;
    public HomeworkFragment(String topicId) {
        this.topicId = topicId;
    }

    @Override
    protected HomeworkContract.IPresenterViewOps OnRegisterPresenter() {
        return new HomeworkPresenter(topicId);
    }

    @Override
    protected void OnViewCreated() {
        getPresenter().ImplementQuestion();
    }

    @Override
    protected void OnBindView() {
        wrapRadio = (RadioGroup) FindViewById(R.id.wrap_radio_btn);
        tvTrueCount = (TextView) FindViewById(R.id.true_count);
        tvFalseCount = (TextView) FindViewById(R.id.false_count);
        tvQuestionNum = (TextView) FindViewById(R.id.number_of_ques);
        tvQuestion = (TextView) FindViewById(R.id.question);
        imgRenewQuestion = (ImageView) FindViewById(R.id.renew_question);
        imgSpeechAudio = (ImageView) FindViewById(R.id.audio);
        tvAnswer1 = (RadioButton) FindViewById(R.id.radio_1);
        tvAnswer1.setOnClickListener(this);
        tvAnswer2 = (RadioButton) FindViewById(R.id.radio_2);
        tvAnswer2.setOnClickListener(this);
        tvAnswer3 = (RadioButton) FindViewById(R.id.radio_3);
        tvAnswer3.setOnClickListener(this);
        tvAnswer4 = (RadioButton) FindViewById(R.id.radio_4);
        tvAnswer4.setOnClickListener(this);
        submitTv = (TextView) FindViewById(R.id.submit_tv);
        submitTv.setAlpha(0.7f);
        imgWord = (ImageView) FindViewById(R.id.word_img);
        submitTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (indexRadio != -1) {
                    getPresenter().PostAnswer(indexRadio);
                }
            }
        });
    }

    public void SetWordImgate(String wordName) {
        imgWord.setImageResource(getContext().getResources().getIdentifier(wordName, "mipmap", getContext().getPackageName()));
    }

    public void SetTvTrueCount(String trueCount) {
        tvTrueCount.setText(trueCount);
    }

    public void SetTvFalseCount(String falseCount) {
        tvFalseCount.setText(falseCount);
    }

    public void SetTvQuestionNum(String questionNum) {
        tvQuestionNum.setText(questionNum + "/32");
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

    public void SetColorAnswer(int indexRadio) {
        switch (indexRadio) {
            case 0:
                tvAnswer1.setTextColor(getResources().getColor(R.color.primary));
                break;
            case 1:
                tvAnswer2.setTextColor(getResources().getColor(R.color.primary));
                break;
            case 2:
                tvAnswer3.setTextColor(getResources().getColor(R.color.primary));
                break;
            case 3:
                tvAnswer4.setTextColor(getResources().getColor(R.color.primary));
                break;
        }
    }

    public void ResetQuestionAndAnswer() {
        indexRadio = -1;
        submitTv.setAlpha(0.7f);
        tvAnswer1.setTextColor(getResources().getColor(R.color.black));
        tvAnswer2.setTextColor(getResources().getColor(R.color.black));
        tvAnswer3.setTextColor(getResources().getColor(R.color.black));
        tvAnswer4.setTextColor(getResources().getColor(R.color.black));
        wrapRadio.clearCheck();
    }

    @Override
    public int GetLayoutId() {
        return R.layout.homework_fragment;
    }

    @Override
    public boolean IsBackButtonVisible() {
        return true;
    }

    @Override
    protected String GetScreenTitle() {
        return "Contracts";
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
        questionCount++;
    }

    @Override
    public int GetQuestionCount() {
        return questionCount;
    }

    @Override
    public void onClick(View v) {
        submitTv.setAlpha(1);
        switch (v.getId()) {
            case R.id.radio_1:
                indexRadio = 0;
                break;
            case R.id.radio_2:
                indexRadio = 1;
                break;
            case R.id.radio_3:
                indexRadio = 2;
                break;
            case R.id.radio_4:
                indexRadio = 3;
                break;
        }
    }
}
