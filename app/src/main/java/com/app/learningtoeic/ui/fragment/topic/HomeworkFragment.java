package com.app.learningtoeic.ui.fragment.topic;

import android.os.Handler;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.app.learningtoeic.R;
import com.app.learningtoeic.contract.topic.HomeworkContract;
import com.app.learningtoeic.entity.Question;
import com.app.learningtoeic.entity.Word;
import com.app.learningtoeic.mvp.fragment.MVPFragment;
import com.app.learningtoeic.presenter.topic.HomeworkPresenter;
import com.app.learningtoeic.ui.adapter.ReviewQuestionAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 4/15/2017.
 */

public class HomeworkFragment extends MVPFragment<HomeworkContract.IPresenterViewOps> implements HomeworkContract.IViewOps, View.OnClickListener, ReviewQuestionAdapter.CallBack {
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
    private BottomSheetBehavior mBottomSheetBehavior;
    View bottomSheet;
    RecyclerView rcvReview;
    ReviewQuestionAdapter reviewQuestionAdapter;

    public HomeworkFragment(String topicId) {
        this.topicId = topicId;
    }

    @Override
    protected HomeworkContract.IPresenterViewOps OnRegisterPresenter() {
        return new HomeworkPresenter(topicId);
    }

    @Override
    protected void OnViewCreated() {
        setupUI(FindViewById(R.id.homework_layout));
        getPresenter().ImplementQuestion();
        reviewQuestionAdapter = new ReviewQuestionAdapter();
        reviewQuestionAdapter.callBack = this;
        rcvReview.setAdapter(reviewQuestionAdapter);
    }

    @Override
    protected void OnBindView() {
        rcvReview = (RecyclerView) FindViewById(R.id.homework_rcv);
        rcvReview.setLayoutManager(new GridLayoutManager(getContext(), 8));
        bottomSheet = FindViewById(R.id.bottom_sheet1);
        mBottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        mBottomSheetBehavior.setPeekHeight(0);
        wrapRadio = (RadioGroup) FindViewById(R.id.wrap_radio_btn);
        tvTrueCount = (TextView) FindViewById(R.id.true_count);
        tvFalseCount = (TextView) FindViewById(R.id.false_count);
        tvQuestionNum = (TextView) FindViewById(R.id.number_of_ques);
        tvQuestion = (TextView) FindViewById(R.id.question);
        imgRenewQuestion = (ImageView) FindViewById(R.id.renew_question);
        imgRenewQuestion.setOnClickListener(this);
        imgSpeechAudio = (ImageView) FindViewById(R.id.audio);
        imgSpeechAudio.setOnClickListener(this);
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
                    indexRadio = -1;
                    submitTv.setEnabled(false);
                    if (questionCount >= 32) {
                        submitTv.setEnabled(true);
                    }
                }
                if (questionCount >= 32) {
                    if (mBottomSheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    } else {
                        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    }
                }
            }
        });
    }

    public void disableQuestion() {
        for (int i = 0; i < wrapRadio.getChildCount(); i++) {
            wrapRadio.getChildAt(i).setEnabled(false);
        }
    }

    public void ClearDataToRenewHomework() {
        questionCount = 0;
        reviewQuestionAdapter.ClearData();
        listAnsweredQuestion.clear();
        listTypeAnswerId.clear();
        ResetQuestionAndAnswer();
    }

    public void showReviewAnswer() {
        submitTv.setText("Review");
    }

    public void AddQuestionToCache(Question item) {
        reviewQuestionAdapter.InsertData(item);
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

    public void ResetQuestionAndAnswer() {
        submitTv.setEnabled(true);
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
        return getPresenter().getTitle();
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
        switch (v.getId()) {
            case R.id.radio_1:
                submitTv.setAlpha(1);
                indexRadio = 0;
                break;
            case R.id.radio_2:
                submitTv.setAlpha(1);
                indexRadio = 1;
                break;
            case R.id.radio_3:
                submitTv.setAlpha(1);
                indexRadio = 2;
                break;
            case R.id.radio_4:
                submitTv.setAlpha(1);
                indexRadio = 3;
                break;
            case R.id.renew_question:
                getPresenter().renewHomework();
                break;
            case R.id.audio:
                getPresenter().startListening();
                break;
        }
    }

    @Override
    public void GoToQuestion(Question question) {
        getPresenter().GoToReviewQuestion(question);
    }
}
