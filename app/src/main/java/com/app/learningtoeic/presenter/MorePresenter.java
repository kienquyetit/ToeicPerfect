package com.app.learningtoeic.presenter;

import com.app.learningtoeic.R;
import com.app.learningtoeic.contract.MoreContract;
import com.app.learningtoeic.entity.MoreItem;
import com.app.learningtoeic.mvp.fragment.FragmentPresenter;

import java.util.ArrayList;

/**
 * Created by QUYET on 4/22/2017.
 */

public class MorePresenter extends FragmentPresenter<MoreContract.IViewOps> implements MoreContract.IPresenterViewOps {

    ArrayList<MoreItem> itemList;

    @Override
    public void InitListItem() {
        itemList = new ArrayList<>();
        createMoreList();
        getView().InsertDataToAdapter(itemList);
    }

    public void createMoreList(){
        itemList.add(new MoreItem(R.drawable.ic_grammar, "Grammar"));
        itemList.add(new MoreItem(R.drawable.ic_chat_menu, "Chat room"));
        itemList.add(new MoreItem(R.drawable.ic_high_score, "High score"));
        itemList.add(new MoreItem(R.drawable.ic_favourite_green, "Favourite"));
    }
}
