package com.app.learningtoeic.ui.fragment;

import android.app.ProgressDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.app.learningtoeic.R;
import com.app.learningtoeic.base.BaseFragment;
import com.app.learningtoeic.contract.GrammarContract;
import com.app.learningtoeic.mvp.fragment.MVPFragment;
import com.app.learningtoeic.presenter.GrammarPresenter;

/**
 * Created by dell on 4/8/2017.
 */

public class GrammarFragment extends MVPFragment<GrammarContract.IPresenterViewOps> implements GrammarContract.IViewOps {
    ListView lvGrammar;
    ProgressDialog progressDialog;
    @Override
    protected void OnViewCreated() {
        getPresenter().excuteGramarTask();
        lvGrammar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(getContext(), DetailGrammarActivity.class);
//                intent.putExtra("Key", position + 1);
//                startActivity(intent);
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
    protected GrammarContract.IPresenterViewOps OnRegisterPresenter() {
        return new GrammarPresenter();
    }

    @Override
    protected String GetScreenTitle() {
        return "Grammar";
    }
}
