package com.app.learningtoeic.presenter.dictionary;

import com.app.learningtoeic.contract.dictionary.DetailWordContract;
import com.app.learningtoeic.entity.Word;
import com.app.learningtoeic.mvp.fragment.FragmentPresenter;
import com.app.learningtoeic.utils.Config;

/**
 * Created by dell on 4/8/2017.
 */

public class DetailWordPresenter extends FragmentPresenter<DetailWordContract.IViewOps> implements DetailWordContract.IPresenterViewOps
{

    @Override
    public void UpdateLikeStatus(Word word) {
        Config.wordDB.update(word);
        getView().ChangeLikeStatus();
    }

}
