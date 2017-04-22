package com.app.learningtoeic.presenter;

import com.app.learningtoeic.R;
import com.app.learningtoeic.contract.MoreContact;
import com.app.learningtoeic.entity.MoreItem;
import com.app.learningtoeic.mvp.fragment.FragmentPresenter;

import java.util.ArrayList;

/**
 * Created by QUYET on 4/22/2017.
 */

public class MorePresenter extends FragmentPresenter<MoreContact.IViewOps> implements MoreContact.IPresenterViewOps {

    ArrayList<MoreItem> itemList;

    @Override
    public void InitListItem() {
        itemList = new ArrayList<>();
        createMoreList();
        getView().InsertDataToAdapter(itemList);
    }

    public void createMoreList(){
        itemList.add(new MoreItem(R.drawable.ic_menu_grammar, "Grammar"));
        itemList.add(new MoreItem(R.drawable.ic_menu_chat, "Chat Room"));
    }
}
