package com.app.learningtoeic.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 4/30/2017.
 */

public class Question {

    public Question(int questionIndex,String question,List<String> answer,int rightIndex,List<Integer> falseIndex,String imageName)
    {
        this.questionIndex = questionIndex;
        this.question = question;
        this.answer = answer;
        this.rightIndex = rightIndex;
        this.falseIndex = falseIndex;
        this.imageName = imageName;
    }

    public int questionIndex;
    public String question;
    public List<String> answer = new ArrayList<>();
    public int rightIndex;
    public List<Integer> falseIndex = new ArrayList<>();
    public String imageName;

    public int getQuestionIndex() {
        return questionIndex;
    }

    public void setQuestionIndex(int questionIndex) {
        this.questionIndex = questionIndex;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getAnswer() {
        return answer;
    }

    public void setAnswer(List<String> answer) {
        this.answer = answer;
    }

    public int getRightIndex() {
        return rightIndex;
    }

    public void setRightIndex(int rightIndex) {
        this.rightIndex = rightIndex;
    }

    public List<Integer> getFalseIndex() {
        return falseIndex;
    }

    public void setFalseIndex(List<Integer> falseIndex) {
        this.falseIndex = falseIndex;
    }
}
