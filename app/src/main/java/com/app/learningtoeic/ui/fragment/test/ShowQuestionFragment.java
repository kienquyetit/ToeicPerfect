package com.app.learningtoeic.ui.fragment.test;

import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.app.learningtoeic.R;
import com.app.learningtoeic.contract.test.ShowQuestionContract;
import com.app.learningtoeic.entity.Question;
import com.app.learningtoeic.entity.Topic;
import com.app.learningtoeic.entity.Word;
import com.app.learningtoeic.mvp.fragment.MVPFragment;
import com.app.learningtoeic.presenter.test.ShowQuestionPresenter;
import com.app.learningtoeic.ui.adapter.ReviewQuestionAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 5/1/2017.
 */

public class ShowQuestionFragment extends MVPFragment<ShowQuestionContract.IPresenterViewOps> implements ShowQuestionContract.IViewOps, View.OnClickListener, ReviewQuestionAdapter.CallBack {
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
    int userScore = 0;
    Chronometer timeCountUp;
    ReviewQuestionAdapter reviewQuestionAdapter;
    private BottomSheetBehavior mBottomSheetBehavior;
    View bottomSheet;
    RecyclerView rcvReview;

    public ShowQuestionFragment(List<Topic> topicList) {
        this.topicList = topicList;
    }

    View reviewBtn;

    @Override
    protected ShowQuestionContract.IPresenterViewOps OnRegisterPresenter() {
        return new ShowQuestionPresenter(topicList);
    }

    @Override
    protected void OnViewCreated() {
        setupUI(FindViewById(R.id.homework_layout));
        getPresenter().ImplementQuestion();
        reviewQuestionAdapter = new ReviewQuestionAdapter();
        reviewQuestionAdapter.callBack = this;
        rcvReview.setAdapter(reviewQuestionAdapter);
    }

    public void AddQuestionToCache(Question item) {
        reviewQuestionAdapter.InsertData(item);
    }

    @Override
    public void showReviewAnswer() {
        reviewBtn.setVisibility(View.VISIBLE);
    }

    @Override
    protected void OnBindView() {
        rcvReview = (RecyclerView) FindViewById(R.id.homework_rcv);
        rcvReview.setLayoutManager(new GridLayoutManager(getContext(), 8));
        bottomSheet = FindViewById(R.id.bottom_sheet1);
        mBottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        mBottomSheetBehavior.setPeekHeight(0);
        reviewBtn = FindViewById(R.id.review_tv);
        reviewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBottomSheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                    mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                } else {
                    mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }
        });
        timeCountUp = (Chronometer) FindViewById(R.id.time_count);
        timeCountUp.start();
        Log.d("avav", timeCountUp.getFormat() + "");
        tvScore = (TextView) FindViewById(R.id.scrore_count);
        tvQuestionNum = (TextView) FindViewById(R.id.number_of_ques);
        tvQuestion = (TextView) FindViewById(R.id.question);
        imgRenewQuestion = (ImageView) FindViewById(R.id.renew_question);
        imgRenewQuestion.setOnClickListener(this);
        imgSpeechAudio = (ImageView) FindViewById(R.id.audio_question);
        imgSpeechAudio.setOnClickListener(this);
        imgWord = (ImageView) FindViewById(R.id.img_ques);
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

    public void setupUI(View view) {
        // Set up touch listener for non-text box views to hide keyboard.
        if (!(view == bottomSheet)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    return false;
                }
            });
        }
        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupUI(innerView);
            }
        }
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
            case R.id.renew_question:
                Animation animation = AnimationUtils.loadAnimation(GetMainAcitivity(), R.anim.rotate_animation);
                imgRenewQuestion.startAnimation(animation);
                getPresenter().renewQuestion();
                break;
            case R.id.audio_question:
                getPresenter().listeningWord();
                break;
        }
    }

    public void ResetQuestion() {
        for (int i = 0; i < wrapRadio.getChildCount(); i++) {
            wrapRadio.getChildAt(i).setEnabled(true);
        }
        tvAnswer1.setTextColor(getResources().getColor(R.color.black));
        tvAnswer2.setTextColor(getResources().getColor(R.color.black));
        tvAnswer3.setTextColor(getResources().getColor(R.color.black));
        tvAnswer4.setTextColor(getResources().getColor(R.color.black));
        wrapRadio.clearCheck();
    }

    public void DisableRadio() {
        for (int i = 0; i < wrapRadio.getChildCount(); i++) {
            wrapRadio.getChildAt(i).setEnabled(false);
        }
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

    public void ClearDataToRenewQuestion() {
        countQuestion = 0;
        reviewBtn.setVisibility(View.GONE);
        reviewQuestionAdapter.ClearData();
        listAnsweredQuestion.clear();
        listTypeAnswerId.clear();
        ResetQuestion();
    }

    @Override
    public void AddTypeId(int typeId) {
        listTypeAnswerId.add(typeId);
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
                tvAnswer1.setTextColor(getResources().getColor(R.color.primaryDark));
                tvAnswer1.setEnabled(false);
                break;
            case 1:
                tvAnswer2.setTextColor(getResources().getColor(R.color.primaryDark));
                tvAnswer2.setEnabled(false);
                break;
            case 2:
                tvAnswer3.setTextColor(getResources().getColor(R.color.primaryDark));
                tvAnswer3.setEnabled(false);
                break;
            case 3:
                tvAnswer4.setTextColor(getResources().getColor(R.color.primaryDark));
                tvAnswer4.setEnabled(false);
                break;
        }
    }

    public void ShowScore(int score) {
        btnSubmit.setText("+" + score + "");
        userScore += score;
        tvScore.setText(userScore + "");
        btnSubmit.setVisibility(View.VISIBLE);
        btnSubmit.animate()
                .translationX(FindViewById(R.id.wrap_score_tv).getWidth() / 2)
                .setDuration(2000)
                .alpha(1)
                .withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        btnSubmit.setVisibility(View.GONE);
                        btnSubmit.animate()
                                .translationX(0);
                    }
                });
    }

    public void SetCheckRadio(int indexRadio) {
        switch (indexRadio) {
            case 0:
                tvAnswer1.setChecked(true);
                break;
            case 1:
                tvAnswer2.setChecked(true);
                break;
            case 2:
                tvAnswer3.setChecked(true);
                break;
            case 3:
                tvAnswer4.setChecked(true);
                break;
        }
    }

    public void SetColorAnswer(int indexRadio) {
        switch (indexRadio) {
            case 0:
                tvAnswer1.setTextColor(getResources().getColor(R.color.primaryDark));
                break;
            case 1:
                tvAnswer2.setTextColor(getResources().getColor(R.color.primaryDark));
                break;
            case 2:
                tvAnswer3.setTextColor(getResources().getColor(R.color.primaryDark));
                break;
            case 3:
                tvAnswer4.setTextColor(getResources().getColor(R.color.primaryDark));
                break;
        }
    }

    @Override
    public void ShowDialogSaveScore() {
        timeCountUp.stop();
        SaveScoreDialogFragment dialog = new SaveScoreDialogFragment(userScore+"",timeCountUp.getText().toString(),countQuestion+1);
        dialog.show(getFragmentManager(),ShowQuestionFragment.class.getName());
    }

    @Override
    public boolean IsBackButtonVisible() {
        return true;
    }

    @Override
    protected String GetScreenTitle() {
        return "Test";
    }

    @Override
    public void GoToQuestion(Question question) {
        getPresenter().GoToReviewQuestion(question);
    }

    public CallBack Callback;
    public interface CallBack
    {
         void GoToHighScoreFragment();
    }
}
