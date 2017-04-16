package com.app.learningtoeic.contract;

import com.app.learningtoeic.mvp.fragment.IFragmentPresenterViewOps;
import com.app.learningtoeic.mvp.fragment.IFragmentViewOps;

/**
 * Created by dell on 4/15/2017.
 */

public class HomeworkContract {
    public interface IViewOps extends IFragmentViewOps
    {
    }
    public interface IPresenterViewOps extends IFragmentPresenterViewOps
    {
    }
}
