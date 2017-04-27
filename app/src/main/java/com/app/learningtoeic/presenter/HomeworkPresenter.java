package com.app.learningtoeic.presenter;

import android.util.Log;

import com.app.learningtoeic.contract.HomeworkContract;
import com.app.learningtoeic.entity.Word;
import com.app.learningtoeic.mvp.fragment.FragmentPresenter;
import com.app.learningtoeic.utils.Config;
import com.app.learningtoeic.utils.Constants;
import com.app.learningtoeic.utils.SuffleHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.logging.Handler;

/**
 * Created by dell on 4/15/2017.
 */

public class HomeworkPresenter extends FragmentPresenter<HomeworkContract.IViewOps> implements HomeworkContract.IPresenterViewOps {

    boolean isVolcabularyQues = false;
    boolean isTranslateQues = false;
    boolean isExampleQues = false;
    boolean isExplanationQues = false;
    boolean isExplanationAns = false;
    boolean isTranslateAns = false;
    List<Word> listQuestion = new ArrayList<Word>();
    ArrayList<Integer> listIdAnswer = new ArrayList<>();
    ArrayList<String> listIndexQuestion = new ArrayList<>();
    List<Integer> listType = new ArrayList<>();
    Word question = new Word();
    int typeIndex = 1;
    String questionText = "";
    List<String> listAnswer ;
    String topicId = "";
    String userAnswer = "";
    String correctAnswer="";
    int correctAnswerIndex = 0;
    int trueCount = 0;
    int falseCount = 0;

    public HomeworkPresenter(String topicId) {
        this.topicId = topicId;
        for (int i = 1; i < 5; i++) {
            listType.add(i);
        }
        listQuestion = getListWord(topicId);
    }

    public void ImplementQuestion() {
       /* if (getView().GetQuestionCount() != 1) {
            if (userAnswer.equals(correctAnswer)) {
                // TODO: 4/24/2017
            } else {
                return;
            }
        }*/
        listAnswer = new ArrayList<>();
        if (getView().GetQuestionCount() > 32) {
            return;
        }
        for (int i = 0; i < getView().getAnsweredQuestionList().size(); i++) {
            for (int j = 0; j < listQuestion.size(); j++) {
                if (listQuestion.get(j).getId() == getView().getAnsweredQuestionList().get(i).getId()) {
                    listQuestion.remove(j);
                }
            }
        }
        //random question
        //List<Word> listAnsweredQuestion = getView().getAnsweredQuestionList();
        //listQuestion.removeAll(listAnsweredQuestion);

        SuffleHelper.shuffleWordArray(listQuestion);
        if (listQuestion.size() != 0) {
            getView().AddAnsweredQuestionId(listQuestion.get(0));
        }
        question = listQuestion.get(0);
        if (getView().GetQuestionCount() % 8 == 0) {
            if (getView().getAnsweredQuestionList().size() > 2) {
                getView().getAnsweredQuestionList().clear();
            }
            listQuestion = getListWord(topicId);
            //random answer type
            listType.removeAll(getView().GetType());
            SuffleHelper.shuffleIntArray(listType);
            getView().AddTypeId(listType.get(0));
        }
        if (getView().GetQuestionCount() < 8) {
            typeIndex = getView().GetType().get(0);
        } else if (getView().GetQuestionCount() < 16) {
            typeIndex = getView().GetType().get(1);
        } else if (getView().GetQuestionCount() < 24) {
            typeIndex = getView().GetType().get(2);
        } else {
            typeIndex = getView().GetType().get(3);
        }


        //Add answer
        switch (typeIndex) {
            case Constants.VOLCALBULARY:
                int answerIndex = getRandomIndex(1, 2);
                switch (answerIndex) {
                    case 1:
                        isExplanationAns = true;
                        break;
                    case 2:
                        isTranslateAns = true;
                        break;
                    default:
                        isExplanationAns = true;
                        break;
                }
                if (isExplanationAns) {
                    //shuffleArray(listIndexQuestion);
                    //listAnswer.add()
                    listIdAnswer = new ArrayList<>();
                    listIdAnswer = getListAnswer(question.getId());
                    for (int i = 0; i < listIdAnswer.size(); i++) {
                        listAnswer.add(getWord(listIdAnswer.get(i) + "").getExplanation());
                        Log.d("EXPLANATIONANS", listAnswer.get(i) + "");
                        isExplanationAns = false;
                    }
                } else {
                    listIdAnswer = getListAnswer(question.getId());
                    for (int i = 0; i < listIdAnswer.size(); i++) {
                        listAnswer.add(getWord(listIdAnswer.get(i) + "").getTranslate());
                        Log.d("TRANSLATEANS", listAnswer.get(i) + "");
                        isTranslateAns = false;
                    }
                }
                questionText = question.getVocabulary();
                Log.d("questionText", question.getVocabulary());
                isVolcabularyQues = true;
                break;
            case Constants.TRANSLATE:
                listIdAnswer = getListAnswer(question.getId());
                for (int i = 0; i < listIdAnswer.size(); i++) {
                    listAnswer.add(getWord(listIdAnswer.get(i) + "").getVocabulary());
                    Log.d("isTranslateQues", listAnswer.get(i) + "");
                }
                questionText = question.getTranslate();
                Log.d("translateques", questionText);
                break;
            case Constants.EXAMPLE:
                listIdAnswer = getListAnswer(question.getId());
                for (int i = 0; i < listIdAnswer.size(); i++) {
                    listAnswer.add(getWord(listIdAnswer.get(i) + "").getVocabulary());
                    Log.d("isExampleQues", listAnswer.get(i) + "");
                }
                questionText = question.getExample();
                Log.d("exampleQues", questionText);
                break;
            case Constants.EXPLANATION:
                listIdAnswer = getListAnswer(question.getId());
                for (int i = 0; i < listIdAnswer.size(); i++) {
                    listAnswer.add(getWord(listIdAnswer.get(i) + "").getVocabulary());
                    Log.d("isExplanationAns", listAnswer.get(i) + "");
                }
                questionText = question.getExplanation();
                Log.d("explanationQues", questionText);
                break;
        }
        String namePicture = question.getVocabulary().replace(' ', '_');
        getView().SetWordImgate(namePicture);
        getView().SetTvQuestionNum((getView().GetQuestionCount() + 1) + "");
        getView().SetTvQuestion(questionText);
        correctAnswer = listAnswer.get(0);
        SuffleHelper.suffleStringArray(listAnswer);
        getView().SetTvAnswer1(listAnswer.get(0));
        getView().SetTvAnswer2(listAnswer.get(1));
        getView().SetTvAnswer3(listAnswer.get(2));
        getView().SetTvAnswer4(listAnswer.get(3));
        correctAnswerIndex = listAnswer.indexOf(correctAnswer);
        //increase count question
        getView().IncreaseCountQuestion();
    }

    @Override
    public void PostAnswer(int indexRadio) {
        if (indexRadio == correctAnswerIndex) {
            trueCount++;
            getView().SetTvTrueCount(trueCount + "");
        } else {
            falseCount++;
            getView().SetTvFalseCount(falseCount + "");
        }
        getView().SetColorAnswer(correctAnswerIndex);
        final android.os.Handler handler = new android.os.Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                getView().ResetQuestionAndAnswer();
                ImplementQuestion();
            }
        }, 500);
    }

    public void InitQuestion(String topicId) {

        listQuestion = getListWord(topicId);
        //Luu index question vao mang de random
        for (int i = 0; i < listQuestion.size(); i++) {
            listIndexQuestion.add(i + "");
        }
        //get random one word
        int questIndex = getRandomIndex(listQuestion.size(), 1);
        ArrayList<String> listChosenQuest = new ArrayList<>();
        listChosenQuest.add((questIndex - 1) + "");
        listIndexQuestion.removeAll(listChosenQuest);
        Word word = new Word();
        word = listQuestion.get(questIndex - 1);
        //get random question
        int questionIndex = getRandomIndex(4, 1);
        String questionText = "";
        switch (questionIndex) {
            case Constants.VOLCALBULARY:
                questionText = word.getVocabulary();
                isVolcabularyQues = true;
                break;
            case Constants.TRANSLATE:
                questionText = word.getTranslate();
                isTranslateQues = true;
                break;
            case Constants.EXAMPLE:
                questionText = word.getExample();
                isExampleQues = true;
                break;
            case Constants.EXPLANATION:
                questionText = word.getExplanation();
                isExplanationQues = true;
                break;
            default:
                questionText = word.getVocabulary();
                isVolcabularyQues = true;
                break;
        }
        String answerText1 = "";
        //get random answer of word
        ArrayList<String> listAnswer = new ArrayList<>();
        if (isVolcabularyQues) {
            int answerIndex = getRandomIndex(1, 2);
            switch (answerIndex) {
                case 1:
                    answerText1 = word.getExplanation();
                    listAnswer.add(answerText1);
                    isExplanationAns = true;
                    break;
                case 2:
                    answerText1 = word.getTranslate();
                    listAnswer.add(answerText1);
                    isTranslateAns = true;
                    break;
                default:
                    answerText1 = word.getExplanation();
                    listAnswer.add(answerText1);
                    isExplanationAns = true;
                    break;
            }
            if (isExplanationAns) {
                //shuffleArray(listIndexQuestion);
                //listAnswer.add()
                listIdAnswer = new ArrayList<>();
                listIdAnswer = getListAnswer(word.getId());
                for (int i = 0; i < listIdAnswer.size(); i++) {
                    Log.d("EXPLANATIONANS", listIdAnswer.get(i) + "");
                }
            } else {
                listIdAnswer = getListAnswer(word.getId());
                for (int i = 0; i < listIdAnswer.size(); i++) {
                    Log.d("TRANSLATEANS", listIdAnswer.get(i) + "");
                }
            }
        } else if (isTranslateQues) {
            listIdAnswer = getListAnswer(word.getId());
            for (int i = 0; i < listIdAnswer.size(); i++) {
                Log.d("isTranslateQues", listIdAnswer.get(i) + "");
            }
        } else if (isExampleQues) {
            listIdAnswer = getListAnswer(word.getId());
            for (int i = 0; i < listIdAnswer.size(); i++) {
                Log.d("isExampleQues", listIdAnswer.get(i) + "");
            }
        } else {
            listIdAnswer = getListAnswer(word.getId());
            for (int i = 0; i < listIdAnswer.size(); i++) {
                Log.d("isExplanationAns", listIdAnswer.get(i) + "");
            }
        }
    }

    public ArrayList<Integer> getListAnswer(int wordId) {
        ArrayList<Integer> listChosenAns = new ArrayList<>();
        listChosenAns.add(wordId);
        int ans2 = getRandomAnswer(listChosenAns);
        listChosenAns.add(ans2);
        int ans3 = getRandomAnswer(listChosenAns);
        listChosenAns.add(ans3);
        int ans4 = getRandomAnswer(listChosenAns);
        listChosenAns.add(ans4);
        return listChosenAns;
    }

    public int getRandomIndex(int startNumber, int endNumber) {
        return new Random().nextInt(endNumber) + startNumber;
    }

    public int getRandomAnswer(List<Integer> listItem) {
        for (int i = 0; i < Constants.NUMBER_OF_WORD; i++) {
            int newQuesIndex = new Random().nextInt(Constants.NUMBER_OF_WORD) + 1;
            if (listItem.size() == 3) {
                if (newQuesIndex != listItem.get(0) && newQuesIndex != listItem.get(1) && newQuesIndex != listItem.get(2)) {
                    return newQuesIndex;
                }
            } else if (listItem.size() == 2) {
                if (newQuesIndex != listItem.get(0) && newQuesIndex != listItem.get(1)) {
                    return newQuesIndex;
                }
            } else if (listItem.size() == 1) {
                if (newQuesIndex != listItem.get(0)) {
                    return newQuesIndex;
                }
            }
        }
        return 1;
    }

    public List<Word> getListWord(String topicId) {
        return Config.wordDB.getListWordForTopic(topicId);
    }

    public Word getWord(String wordId) {
        return Config.wordDB.getWord(wordId);
    }

}
