package com.app.learningtoeic.presenter.dictionary;

import android.os.AsyncTask;

import com.app.learningtoeic.contract.dictionary.DictionaryContract;
import com.app.learningtoeic.entity.Word;
import com.app.learningtoeic.mvp.fragment.FragmentPresenter;
import com.app.learningtoeic.utils.Config;

import java.util.ArrayList;

/**
 * Created by dell on 4/7/2017.
 */

public class DictionaryPresenter extends FragmentPresenter<DictionaryContract.IViewOps> implements DictionaryContract.IPresenterViewOps {

    public ArrayList<Word> wordsList;
    public String newText;

    @Override
    public void ExcuteDictionaryTask() {
        new DictionaryPresenter.DictionaryAsyncTask().execute(100000);
    }

    @Override
    public void ReadRecordsSearch(String newText) {
        this.newText = newText;
        new DictionaryPresenter.DictionarySearchAsyncTask().execute(100000);
    }

    private class DictionaryAsyncTask extends AsyncTask<Integer, Integer, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Integer... params) {
            wordsList = Config.wordDB.getListWord();
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

    private class DictionarySearchAsyncTask extends AsyncTask<Integer, Integer, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Integer... params) {
            wordsList = Config.wordDB.readRecordsSearch(newText);
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
