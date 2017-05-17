package com.app.learningtoeic.presenter.topic;

import android.os.AsyncTask;

import com.app.learningtoeic.contract.topic.TopicItemListContract;
import com.app.learningtoeic.entity.Word;
import com.app.learningtoeic.mvp.fragment.FragmentPresenter;
import com.app.learningtoeic.utils.Config;

import java.util.ArrayList;

/**
 * Created by QUYET on 5/16/2017.
 */

public class TopicItemListPresenter extends FragmentPresenter<TopicItemListContract.IViewOps> implements TopicItemListContract.IPresenterViewOps {

    public ArrayList<Word> wordsList;
    String topicId = "";

    @Override
    public void ExcuteTopicItemListTask(String topicId) {
        this.topicId = topicId;
        new TopicItemListPresenter.TopicItemListAsyncTask().execute(100000);
    }

    private class TopicItemListAsyncTask extends AsyncTask<Integer, Integer, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Integer... params) {
            wordsList = Config.wordDB.getListWordForTopic(topicId);
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            getView().InsertData(wordsList);
        }
    }
}
