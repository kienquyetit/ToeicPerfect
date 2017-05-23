package com.app.learningtoeic.presenter;

import android.os.AsyncTask;

import com.app.learningtoeic.contract.FavouriteContract;
import com.app.learningtoeic.entity.Word;
import com.app.learningtoeic.mvp.fragment.FragmentPresenter;
import com.app.learningtoeic.utils.Config;

import java.util.ArrayList;

/**
 * Created by QUYET on 5/23/2017.
 */

public class FavouritePresenter extends FragmentPresenter<FavouriteContract.IViewOps> implements FavouriteContract.IPresenterViewOps {

    public ArrayList<Word> wordsList;

    @Override
    public void ExcuteDictionaryTask() {
        new FavouritePresenter.FavouriteAsyncTask().execute(100000);
    }

    private class FavouriteAsyncTask extends AsyncTask<Integer, Integer, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Integer... params) {
            wordsList = Config.wordDB.getFavouriteList();
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