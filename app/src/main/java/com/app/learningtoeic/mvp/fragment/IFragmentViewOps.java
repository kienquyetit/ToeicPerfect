package com.app.learningtoeic.mvp.fragment;

import android.content.Context;

import com.app.learningtoeic.mvp.IViewOps;

/**
 * Created by dell on 4/4/2017.
 */

public interface IFragmentViewOps extends IViewOps
{
    Context GetAppContext();

    Context GetActivityContext();
}
