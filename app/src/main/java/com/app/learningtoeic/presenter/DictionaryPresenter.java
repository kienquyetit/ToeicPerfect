package com.app.learningtoeic.presenter;

import android.os.AsyncTask;

import com.app.learningtoeic.contract.DictionaryContract;
import com.app.learningtoeic.entity.Word;
import com.app.learningtoeic.mvp.fragment.FragmentPresenter;
import com.app.learningtoeic.utils.Config;

import java.util.ArrayList;

/**
 * Created by dell on 4/7/2017.
 */

public class DictionaryPresenter extends FragmentPresenter<DictionaryContract.IViewOps> implements DictionaryContract.IPresenterViewOps {

    public ArrayList<Word> wordsList;
    String topicId = "";

    @Override
    public void ExcuteDictionaryTask(String topicId) {
        this.topicId = topicId;
        new DictionaryPresenter.DictionaryAsyncTask().execute(100000);
    }

    private class DictionaryAsyncTask extends AsyncTask<Integer, Integer, Void> {

        @Override
        protected void onPreExecute() {
            getView().ShowProgressDialog();
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Integer... params) {

            if(topicId.length()!=0)
            {
                wordsList=Config.wordDB.getListWordForTopic(topicId);
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
            getView().HideProgessDialog();
            getView().InsertData(wordsList);
        }
    }
}
