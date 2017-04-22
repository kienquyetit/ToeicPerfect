package com.app.learningtoeic.ui.fragment.test;

import com.app.learningtoeic.R;
import com.app.learningtoeic.base.BaseFragment;

/**
 * Created by dell on 4/1/2017.
 */

public class TestFragment extends BaseFragment {
    @Override
    protected void OnViewCreated() {
    }

    @Override
    protected void OnBindView() {

    }

    @Override
    public int GetLayoutId() {
        return R.layout.dictionary_fragment;
    }

    @Override
    protected String GetScreenTitle() {
        return getString(R.string.app_name);
    }
}
