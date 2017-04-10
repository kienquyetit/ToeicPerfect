package com.app.learningtoeic.mvp.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.learningtoeic.base.BaseFragment;

/**
 * Created by dell on 4/4/2017.
 */

public abstract class MVPFragment<P extends IFragmentPresenterViewOps> extends BaseFragment implements IFragmentViewOps {


    public P getPresenter() {
        return Presenter;
    }

    P Presenter;

    protected abstract P OnRegisterPresenter();

    @Override
    public Context GetAppContext() {
        return getActivity().getApplicationContext();
    }

    @Override
    public Context GetActivityContext() {
        return getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            initPresenter();
            View view = super.onCreateView(inflater, container, savedInstanceState);
            Presenter.OnCreate();
            return view;
        } else {
            return rootView;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Presenter.OnResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Presenter.OnDestroy();
    }

    private void initPresenter() {
        //Presenter = (P)PresenterFactory.getInstance().createPresenter(P_ID, this);
        Presenter = OnRegisterPresenter();
        Presenter.takeView(this);

    }
}