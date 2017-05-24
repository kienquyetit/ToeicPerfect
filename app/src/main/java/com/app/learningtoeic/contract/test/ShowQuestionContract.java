package com.app.learningtoeic.contract.test;

import com.app.learningtoeic.entity.Question;
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
    public interface IViewOps extends IFragmentViewOps {
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

        void ResetQuestion();

        void ShowScore(int score);

        void DisableRadio();

        void ClearDataToRenewQuestion();

        void AddQuestionToCache(Question item);

        void showReviewAnswer();

        void SetCheckRadio(int indexRadio);

        void SetColorAnswer(int indexRadio);

        void ShowDialogSaveScore();

        void SetTextHowQuestion(String text);

        void DisablePronounceWord(boolean isDisable);
    }

    public interface IPresenterViewOps extends IFragmentPresenterViewOps {
        void PostAnswer(int indexRadio);

        void ImplementQuestion();

        void renewQuestion();

        void listeningWord();

        void GoToReviewQuestion(Question question);
    }
}
