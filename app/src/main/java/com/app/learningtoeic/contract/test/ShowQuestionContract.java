package com.app.learningtoeic.contract.test;

import com.app.learningtoeic.entity.Topic;
import com.app.learningtoeic.entity.Word;
import com.app.learningtoeic.mvp.fragment.IFragmentPresenterViewOps;
import com.app.learningtoeic.mvp.fragment.IFragmentViewOps;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 5/1/2017.
 */

public class ShowQuestionContract {
    public interface IViewOps extends IFragmentViewOps
    {
        int GetQuestionCount();
        void AddAnsweredQuestionId(Word answeredQuestion);
        void AddTypeId(int questionId);
        ArrayList<Integer> GetType();
        void IncreaseCountQuestion();
        List<Word> getAnsweredQuestionList();
        void SetWordImgate(String wordName);
        void SetTvQuestionNum(String questionNum);
        void SetTvQuestion(String question);
        void SetTvAnswer1(String answer);
        void SetTvAnswer2(String answer);
        void SetTvAnswer3(String answer);
        void SetTvAnswer4(String answer);
        void setFalseAnswer(int indexRadio);
        void setRightAnswer(int indexRadio);
        int GetAnsweredCount();
        void AddAnsweredCount();
        void ResetQuestion();
        void ShowScore();

    }
    public interface IPresenterViewOps extends IFragmentPresenterViewOps
    {
        void PostAnswer(int indexRadio);
        void ImplementQuestion();
    }
}
