package com.app.learningtoeic.ui.fragment.grammar;

import android.webkit.WebView;

import com.app.learningtoeic.R;
import com.app.learningtoeic.contract.grammar.DetailGrammarContract;
import com.app.learningtoeic.mvp.fragment.MVPFragment;
import com.app.learningtoeic.presenter.grammar.DetailGrammarPresenter;

/**
 * Created by QUYET on 4/21/2017.
 */

public class DetailGrammarFragment extends MVPFragment<DetailGrammarContract.IPresenterViewOps> implements DetailGrammarContract.IViewOps{

    private String mFileName;
    private String mTitleName;

    private WebView webView;

    public DetailGrammarFragment(String fileName, String title){
        this.mFileName = fileName;
        this.mTitleName = title;
    }

    @Override
    protected void OnViewCreated() {
        setUpWebView();
    }

    private void setUpWebView() {
        webView.loadUrl("file:///android_asset/grammar/"+mFileName+".htm");
    }

    @Override
    protected void OnBindView() {
        webView = (WebView) FindViewById(R.id.webView1);
    }

    @Override
    public int GetLayoutId() {
        return R.layout.detail_grammar_fragment;
    }

    @Override
    protected String GetScreenTitle() {
        return mTitleName;
    }

    @Override
    protected DetailGrammarContract.IPresenterViewOps OnRegisterPresenter() {
        return new DetailGrammarPresenter();
    }

    @Override
    public boolean IsBackButtonVisible() {
        return true;
    }

    @Override
    public boolean IsHeaderVisible() {
        return true;
    }
}
