package com.app.learningtoeic.ui.fragment;

import android.view.View;

import com.app.learningtoeic.R;
import com.app.learningtoeic.base.BaseFragment;

/**
 * Created by dell on 4/7/2017.
 */

public class MoreFragment extends BaseFragment {

    View wrapGrammarLayout;
    @Override
    protected void OnViewCreated() {

    }

    @Override
    protected void OnBindView() {
        wrapGrammarLayout = FindViewById(R.id.wrap_grammar);
        wrapGrammarLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SwitchFragment(new GrammarFragment(),true);
            }
        });
    }

    @Override
    public int GetLayoutId() {
        return R.layout.more_fragment;
    }

    @Override
    protected String GetScreenTitle() {
        return "ToeicPerfect";
    }
}
