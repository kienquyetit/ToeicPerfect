package com.app.learningtoeic.contract.grammar;

import android.widget.ArrayAdapter;

import com.app.learningtoeic.mvp.fragment.IFragmentPresenterViewOps;
import com.app.learningtoeic.mvp.fragment.IFragmentViewOps;

import java.util.ArrayList;

/**
 * Created by dell on 4/8/2017.
 */

public class GrammarContract {
    public interface IViewOps extends IFragmentViewOps
    {
        void SetAdapter(ArrayAdapter adapter);
        void ShowProgressDialog();
        void HideProgessDialog();
        void GetTitle(ArrayList<String> arrayList);
    }
    public interface IPresenterViewOps extends IFragmentPresenterViewOps
    {
        void excuteGramarTask();
    }
}
