package com.app.learningtoeic.presenter;

import android.os.AsyncTask;

import com.app.learningtoeic.contract.DetailWordOfTopicContract;
import com.app.learningtoeic.entity.Word;
import com.app.learningtoeic.mvp.fragment.FragmentPresenter;
import com.app.learningtoeic.utils.Config;

import java.util.ArrayList;

/**
 * Created by QUYET on 4/17/2017.
 */

public class DetailWordOfTopicPresenter extends FragmentPresenter<DetailWordOfTopicContract.IViewOps> implements DetailWordOfTopicContract.IPresenterViewOps {

    public ArrayList<Word> wordsList;
    String topicId = "";

    @Override
    public void ExcuteDictionaryTask(String topicId) {
        this.topicId = topicId;
        new DetailWordOfTopicPresenter.DetailWordOfTopicAsyncTask().execute(100000);
    }

    private class DetailWordOfTopicAsyncTask extends AsyncTask<Integer, Integer, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Integer... params) {
            if(topicId.length()!=0)
            {
                wordsList = Config.wordDB.getListWordForTopic(topicId);
            }
            else {
                wordsList = Config.wordDB.getListWord();
            }
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
