package com.app.learningtoeic.contract;

import com.app.learningtoeic.entity.MoreItem;
import com.app.learningtoeic.mvp.fragment.IFragmentPresenterViewOps;
import com.app.learningtoeic.mvp.fragment.IFragmentViewOps;

import java.util.ArrayList;

/**
 * Created by QUYET on 4/22/2017.
 */

public class MoreContact {
    public interface IViewOps extends IFragmentViewOps
    {
        void InsertDataToAdapter(ArrayList<MoreItem> listItem);
    }
    public interface IPresenterViewOps extends IFragmentPresenterViewOps
    {
        void InitListItem();
    }
}
