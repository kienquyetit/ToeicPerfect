package com.app.learningtoeic.presenter.grammar;

import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;

import com.app.learningtoeic.R;
import com.app.learningtoeic.contract.grammar.GrammarContract;
import com.app.learningtoeic.mvp.fragment.FragmentPresenter;

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
 * Created by dell on 4/8/2017.
 */

public class GrammarPresenter extends FragmentPresenter<GrammarContract.IViewOps> implements GrammarContract.IPresenterViewOps {

    ArrayList<String> arr = new ArrayList();
    Document doc = null;

    @Override
    public void excuteGramarTask() {
        new GrammarPresenter.GrammarAsyncTask().execute(100000);

    }

    public void getTitle() {
        AssetManager am = getView().GetActivityContext().getAssets();
        String htmlContentInStringFormat = null;
        for (int i = 1; i <= 50; i++) {
            try {
                InputStream is = am.open("grammar/" + i + ".htm", AssetManager.ACCESS_BUFFER);
                htmlContentInStringFormat = StreamToString(is);
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            doc = Jsoup.parse(htmlContentInStringFormat);
            Elements elements = doc.select("h3");
            arr.add(elements.get(0).text());
        }
    }

    private class GrammarAsyncTask extends AsyncTask<Integer, Integer, Void> {

        @Override
        protected void onPreExecute() {
            getView().ShowProgressDialog();
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Integer... params) {
            getTitle();
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            getView().GetTitle(arr);
            ArrayAdapter adapter = new ArrayAdapter(getView().GetActivityContext(), R.layout.grammar_item, arr);
            getView().SetAdapter(adapter);
            getView().HideProgessDialog();
        }
    }

    public static String StreamToString(InputStream is){
        if(is == null){
            return "";
        }
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1){
                writer.write(buffer, 0, n);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return writer.toString();
    }

}
