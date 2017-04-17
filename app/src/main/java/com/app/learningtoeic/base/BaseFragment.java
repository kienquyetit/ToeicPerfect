package com.app.learningtoeic.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.learningtoeic.MainActivity;
import com.app.learningtoeic.R;
import com.app.learningtoeic.utils.FragmentHelper;

/**
 * Created by dell on 4/4/2017.
 */

public abstract class BaseFragment extends Fragment {
    public View rootView;

    protected abstract void OnViewCreated();

    protected abstract void OnBindView();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(GetLayoutId(), container, false);
            OnBindView();
            OnViewCreated();
        }
        return rootView;
    }

    public abstract int GetLayoutId();

    public View FindViewById(int resId) {
        return rootView.findViewById(resId);
    }

    public void SwitchFragment(Fragment fragment, boolean IsAddToBackStack) {
        GetMainAcitivity().SwitchFragment(fragment, IsAddToBackStack);
        GetMainAcitivity().RemoveItemMenu();
    }

    public void ReloadFragment() {
        FragmentHelper.ReloadFragment(getFragmentManager(), this);
    }

    public void FinishFragment() {
        FragmentHelper.PopBackStack(GetMainAcitivity().getSupportFragmentManager());
        GetMainAcitivity().RemoveItemMenu();
    }

    public Fragment GetCurrentFragment() {
        return GetMainAcitivity().getSupportFragmentManager().findFragmentById(R.id.root);
    }

    protected MainActivity GetMainAcitivity() {
        if (getActivity() instanceof MainActivity) {
            return ((MainActivity) getActivity());
        }
        return null;
    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            GetMainAcitivity().SetScreenTitle(GetScreenTitle());
            GetMainAcitivity().setHeaderVisible(IsHeaderVisible());
            GetMainAcitivity().setBackButtonVisible(IsBackButtonVisible());
            GetMainAcitivity().setMenuVisible(IsMenuVisible());
            GetMainAcitivity().setImgLikeVisible(IsImgLikeVisible());
            GetMainAcitivity().setListeningButtonVisible(IsListeningButtonVisible());
            GetMainAcitivity().setRecordingButtonVisible(IsRecordingButtonVisible());
            GetMainAcitivity().setSlidingButtonVisible(IsSlidingButtonVisible());
            GetMainAcitivity().setFooterVisible(IsFooterVisible());
        }catch (Exception e)
        {

        }
    }

    public boolean IsFooterVisible() {
        return false;
    }

    public boolean IsMenuVisible() {
        return false;
    }

    public boolean IsBackButtonVisible() {
        return false;
    }

    public boolean IsHeaderVisible() {
        return true;
    }

    public boolean IsImgLikeVisible(){return false;}

    public boolean IsListeningButtonVisible() {
        return false;
    }

    public boolean IsRecordingButtonVisible() {
        return false;
    }

    public boolean IsSlidingButtonVisible() {
        return false;
    }

    public boolean GetMenuVisible() {
        return false;
    }

    protected String GetScreenTitle() {
        return "";
    }

    public boolean OnBackPress() {
        return false;
    }

}
