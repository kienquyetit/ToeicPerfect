package com.app.learningtoeic.presenter.test;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.util.Log;

import com.app.learningtoeic.contract.test.ShowQuestionContract;
import com.app.learningtoeic.contract.test.TestContract;
import com.app.learningtoeic.entity.Question;
import com.app.learningtoeic.entity.Topic;
import com.app.learningtoeic.entity.Word;
import com.app.learningtoeic.mvp.fragment.FragmentPresenter;
import com.app.learningtoeic.utils.Config;
import com.app.learningtoeic.utils.Constants;
import com.app.learningtoeic.utils.SuffleHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by dell on 5/1/2017.
 */

public class ShowQuestionPresenter extends FragmentPresenter<ShowQuestionContract.IViewOps> implements ShowQuestionContract.IPresenterViewOps {

    boolean isVolcabularyQues = false;
    boolean isTranslateQues = false;
    boolean isExampleQues = false;
    boolean isExplanationQues = false;
    boolean isExplanationAns = false;
    boolean isTranslateAns = false;
    List<Topic> topicList = new ArrayList<>();
    List<Word> listQuestion = new ArrayList<>();
    ArrayList<Integer> listIdAnswer = new ArrayList<>();
    ArrayList<String> listIndexQuestion = new ArrayList<>();
    String questionText = "";
    List<String> listAnswer;
    Word question = new Word();
    List<Integer> listType = new ArrayList<>();
    String correctAnswer = "";
    int typeIndex = 1;
    int correctAnswerIndex = 0;
    int fullScore = 4;
    int numberOfQuestion = 0;
    List<Integer> listUserAnswered = new ArrayList<>();

    public ShowQuestionPresenter(List<Topic> listTopic) {
        topicList = listTopic;
        for (int i = 1; i < 5; i++) {
            listType.add(i);
        }
        for (int i = 0; i < topicList.size(); i++) {
            listQuestion.addAll(getListWord(topicList.get(i).id + ""));
        }
        numberOfQuestion = listQuestion.size();
    }

    public void ImplementQuestion() {
        try {

            listAnswer = new ArrayList<>();
            if (getView().GetQuestionCount() >= (topicList.size() * 12)) {
                return;
            }
            for (int i = 0; i < getView().getAnsweredQuestionList().size(); i++) {
                for (int j = 0; j < listQuestion.size(); j++) {
                    if (listQuestion.get(j).getId() == getView().getAnsweredQuestionList().get(i).getId()) {
                        listQuestion.remove(j);
                    }
                }
            }
            SuffleHelper.shuffleWordArray(listQuestion);
            if (listQuestion.size() != 0) {
                getView().AddAnsweredQuestionId(listQuestion.get(0));
            }
            question = listQuestion.get(0);
            int userQuestionCount = getView().GetQuestionCount();
            if (userQuestionCount % (numberOfQuestion / 4) == 0) {
                if (getView().getAnsweredQuestionList().size() > 2) {
                    getView().getAnsweredQuestionList().clear();
                }
                listQuestion.clear();
                for (int i = 0; i < topicList.size(); i++) {
                    listQuestion.addAll(getListWord(topicList.get(i).id + ""));
                }
                //random answer type
                listType.removeAll(getView().GetType());
                SuffleHelper.shuffleIntArray(listType);
                getView().AddTypeId(listType.get(0));
            }
            if (userQuestionCount < (numberOfQuestion / 4)) {
                typeIndex = getView().GetType().get(0);
            } else if (userQuestionCount < ((numberOfQuestion / 4) * 2)) {
                typeIndex = getView().GetType().get(1);
            } else if (userQuestionCount < ((numberOfQuestion / 4) * 3)) {
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
                        listAnswer.add(getWord(listIdAnswer.get(i) + "").getExampleTranslate());
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
            getView().SetTvQuestionNum((userQuestionCount + 1) + "/" + (numberOfQuestion));
            getView().SetTvQuestion(questionText);
            correctAnswer = listAnswer.get(0);
            SuffleHelper.suffleStringArray(listAnswer);
            getView().SetTvAnswer1(listAnswer.get(0));
            getView().SetTvAnswer2(listAnswer.get(1));
            getView().SetTvAnswer3(listAnswer.get(2));
            getView().SetTvAnswer4(listAnswer.get(3));
            correctAnswerIndex = listAnswer.indexOf(correctAnswer);
        } catch (Exception e) {

        }

    }

    @Override
    public void renewQuestion() {
        getView().ClearDataToRenewQuestion();
        ImplementQuestion();
    }

    @Override
    public void listeningWord() {
        MediaPlayer player = new MediaPlayer();
        AssetFileDescriptor afd = null;
        try {
            afd = getView().GetActivityContext().getAssets().openFd("vocabulary/" + question.getVocabulary() + ".mp3");
            player.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            player.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        player.start();
    }

    @Override
    public void GoToReviewQuestion(Question question) {
        getView().ResetQuestion();
        getView().DisableRadio();
        getView().SetWordImgate(question.imageName.replace(' ', '_'));
        getView().SetTvQuestionNum((question.getQuestionIndex() + 1) + "/" + numberOfQuestion);
        getView().SetTvQuestion(question.getQuestion());
        getView().SetTvAnswer1(question.getAnswer().get(0));
        getView().SetTvAnswer2(question.getAnswer().get(1));
        getView().SetTvAnswer3(question.getAnswer().get(2));
        getView().SetTvAnswer4(question.getAnswer().get(3));
        getView().SetCheckRadio(question.getRightIndex());
        getView().SetColorAnswer(question.getRightIndex());
    }

    public void PostAnswer(int indexRadio) {
        if (getView().GetQuestionCount() >= numberOfQuestion) {
            getView().showReviewAnswer();
            return;
        }
        if (indexRadio != correctAnswerIndex) {
            fullScore--;
            getView().setFalseAnswer(indexRadio);
            listUserAnswered.add(indexRadio);
        } else {
            listUserAnswered.add(indexRadio);
            Question question = new Question(getView().GetQuestionCount(), questionText, listAnswer, correctAnswerIndex, listUserAnswered, this.question.getVocabulary());
            getView().AddQuestionToCache(question);
            getView().setRightAnswer(indexRadio);
            getView().ShowScore(fullScore);
            getView().DisableRadio();
            final android.os.Handler handler = new android.os.Handler();
            if (getView().GetQuestionCount() < numberOfQuestion - 1) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        fullScore = 4;
                        getView().ResetQuestion();
                        ImplementQuestion();
                    }
                }, 2000);
            } else {
                getView().DisableRadio();
                getView().showReviewAnswer();
                getView().ShowDialogSaveScore();
            }
            getView().IncreaseCountQuestion();
        }
    }

    public List<Word> getListWord(String topicId) {
        return Config.wordDB.getListWordForTopic(topicId);
    }

    public int getRandomIndex(int startNumber, int endNumber) {
        return new Random().nextInt(endNumber) + startNumber;
    }

    public ArrayList<Integer> getListAnswer(int wordId) {
        ArrayList<Integer> listChosenAns = new ArrayList<>();
        int ans1 = wordId;
        listChosenAns.add(ans1);
        int ans2 = getAnswerFromRandomAnswer(listChosenAns);
        listChosenAns.add(ans2);
        int ans3 = getAnswerFromRandomAnswer(listChosenAns);
        listChosenAns.add(ans3);
        int ans4 = getAnswerFromRandomAnswer(listChosenAns);
        listChosenAns.add(ans4);
        return listChosenAns;
    }

    public int getAnswerFromRandomAnswer(ArrayList<Integer> listChosenAns) {
        int ans = getRandomAnswer(listChosenAns);
        while (true) {
            if (ans == -1) {
                ans = getRandomAnswer(listChosenAns);
            } else {
                return ans;
            }
        }
    }

    public int getRandomAnswer(List<Integer> listItem) {
        for (int i = 0; i < numberOfQuestion; i++) {
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
        return -1;
    }

    public Word getWord(String wordId) {
        return Config.wordDB.getWord(wordId);
    }
}
