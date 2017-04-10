package com.app.learningtoeic.presenter;

import android.app.ProgressDialog;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ArrayAdapter;

import com.app.learningtoeic.R;
import com.app.learningtoeic.contract.DictionaryContract;
import com.app.learningtoeic.entity.Word;
import com.app.learningtoeic.mvp.fragment.FragmentPresenter;
import com.app.learningtoeic.utils.Config;
import com.app.learningtoeic.utils.ProgressDialogHelper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;

/**
 * Created by dell on 4/7/2017.
 */

public class DictionaryPresenter extends FragmentPresenter<DictionaryContract.IViewOps> implements DictionaryContract.IPresenterViewOps {

    public ArrayList<Word> wordsList;


    @Override
    public void ExcuteDictionaryTask() {
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
            getView().HideProgessDialog();
            getView().InsertData(wordsList);
        }
    }
}
