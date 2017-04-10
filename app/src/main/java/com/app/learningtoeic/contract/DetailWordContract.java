package com.app.learningtoeic.contract;

import com.app.learningtoeic.entity.Word;
import com.app.learningtoeic.mvp.fragment.IFragmentPresenterViewOps;
import com.app.learningtoeic.mvp.fragment.IFragmentViewOps;

import java.util.ArrayList;

/**
 * Created by dell on 4/8/2017.
 */

public class DetailWordContract {
    public interface IViewOps extends IFragmentViewOps
    {
    }
    public interface IPresenterViewOps extends IFragmentPresenterViewOps
    {
    }
}
