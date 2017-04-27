package com.app.learningtoeic.ui.fragment.grammar;

import android.app.ProgressDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.app.learningtoeic.R;
import com.app.learningtoeic.contract.grammar.GrammarContract;
import com.app.learningtoeic.mvp.fragment.MVPFragment;
import com.app.learningtoeic.presenter.grammar.GrammarPresenter;

import java.util.ArrayList;

/**
 * Created by dell on 4/8/2017.
 */

public class GrammarFragment extends MVPFragment<GrammarContract.IPresenterViewOps> implements GrammarContract.IViewOps {

    ListView lvGrammar;
    ProgressDialog progressDialog;
    ArrayList<String> mListTitle;

    public GrammarFragment(){
        mListTitle = new ArrayList();
    }

    @Override
    protected void OnViewCreated() {
        getPresenter().excuteGramarTask();
        lvGrammar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String title = mListTitle.get(position);
                SwitchFragment(new DetailGrammarFragment(String.valueOf(position+1), title), true);
            }
        });
    }

    @Override
    protected void OnBindView() {
        lvGrammar = (ListView)FindViewById(R.id.lvGrammar);
    }

    @Override
    public int GetLayoutId() {
        return R.layout.grammar_fragment;
    }

    @Override
    public void SetAdapter(ArrayAdapter adapter) {
        lvGrammar.setAdapter(adapter);
    }

    @Override
    public void ShowProgressDialog() {
        progressDialog = ProgressDialog.show(getContext(), "", "File loading ...", false, false);

    }

    @Override
    public void HideProgessDialog() {
        progressDialog.dismiss();

    }

    @Override
    public void GetTitle(ArrayList<String> listTitle) {
        mListTitle = listTitle;
    }

    @Override
    protected GrammarContract.IPresenterViewOps OnRegisterPresenter() {
        return new GrammarPresenter();
    }

    @Override
    protected String GetScreenTitle() {
        return "Grammar";
    }

    @Override
    public boolean IsBackButtonVisible() {
        return super.IsBackButtonVisible();
    }
}
