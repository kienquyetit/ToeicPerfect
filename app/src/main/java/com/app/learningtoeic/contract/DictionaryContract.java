package com.app.learningtoeic.contract;

import android.widget.ArrayAdapter;

import com.app.learningtoeic.entity.Word;
import com.app.learningtoeic.mvp.fragment.IFragmentPresenterViewOps;
import com.app.learningtoeic.mvp.fragment.IFragmentViewOps;

import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 4/7/2017.
 */

public class DictionaryContract {
    public interface IViewOps extends IFragmentViewOps
    {
        void InsertData(ArrayList<Word> listItem);
        void ShowProgressDialog();
        void HideProgessDialog();
    }
    public interface IPresenterViewOps extends IFragmentPresenterViewOps
    {
        void ExcuteDictionaryTask(String topicId);
    }
}
