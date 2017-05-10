package com.app.learningtoeic.contract.topic;

import com.app.learningtoeic.entity.Question;
import com.app.learningtoeic.entity.Word;
import com.app.learningtoeic.mvp.fragment.IFragmentPresenterViewOps;
import com.app.learningtoeic.mvp.fragment.IFragmentViewOps;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 4/15/2017.
 */

public class HomeworkContract {
    public interface IViewOps extends IFragmentViewOps
    {
        void AddQuestionToCache(Question question);
        void AddAnsweredQuestionId(Word answeredQuestion);
        List<Word> getAnsweredQuestionList();
        ArrayList<Integer> GetType();
        void AddTypeId(int questionId);
        void IncreaseCountQuestion();
        int GetQuestionCount();
        void SetTvTrueCount(String trueCount);
        void SetTvFalseCount(String falseCount);
        void SetTvQuestionNum(String questionNum);
        void SetTvQuestion(String question);
        void SetTvAnswer1(String answer);
        void SetTvAnswer2(String answer);
        void SetTvAnswer3(String answer);
        void SetTvAnswer4(String answer);
        void SetWordImgate(String wordName);
        void SetColorAnswer(int indexRadio);
        void ResetQuestionAndAnswer();
        void showReviewAnswer();
        void disableQuestion();
        void SetCheckRadio(int indexRadio);
        void ClearDataToRenewHomework();
    }
    public interface IPresenterViewOps extends IFragmentPresenterViewOps
    {
        void ImplementQuestion();
        void PostAnswer(int indexRadio);
        void GoToReviewQuestion(Question question);
        String getTitle();
        void renewHomework();
        void startListening();
        void InitQuestion(String topicId);
    }
}
