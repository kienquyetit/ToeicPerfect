package com.app.learningtoeic.ui.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.app.learningtoeic.R;
import com.app.learningtoeic.contract.DetailWordOfTopicContract;
import com.app.learningtoeic.entity.Topic;
import com.app.learningtoeic.entity.Word;
import com.app.learningtoeic.mvp.fragment.MVPFragment;
import com.app.learningtoeic.presenter.DetailWordOfTopicPresenter;
import com.app.learningtoeic.ui.adapter.HomePagerAdapter;
import com.app.learningtoeic.utils.CustomViewPager;

import java.util.ArrayList;

/**
 * Created by QUYET on 4/17/2017.
 */

public class DetailWordOfTopicFragment extends MVPFragment<DetailWordOfTopicContract.IPresenterViewOps> implements DetailWordOfTopicContract.IViewOps,ViewPager.OnPageChangeListener, TabLayout.OnTabSelectedListener, DetailWordFragment.CallBack{

    CustomViewPager mViewPager;
    Topic mTopic;
    Integer mPosition;
    HomePagerAdapter homePagerAdapter;
    ArrayList<Word> listWord;

    private boolean isPagingEnabled = true;

    public DetailWordOfTopicFragment(Topic topic, int position)
    {
        this.mTopic = topic;
        this.mPosition = position;
    }

    @Override
    protected DetailWordOfTopicContract.IPresenterViewOps OnRegisterPresenter() {
        return new DetailWordOfTopicPresenter();
    }

    @Override
    protected void OnViewCreated() {
        mViewPager.addOnPageChangeListener(this);
        setUpViewPager();
    }

    private void setUpViewPager() {
        mViewPager.setOffscreenPageLimit(1);
        homePagerAdapter = new HomePagerAdapter(getChildFragmentManager());
        mViewPager.setAdapter(homePagerAdapter);
        getPresenter().ExcuteDictionaryTask(String.valueOf(mTopic.id));
    }

    @Override
    protected void OnBindView() {
        mViewPager = (CustomViewPager) FindViewById(R.id.detail_word_of_topic_vp);
    }

    @Override
    public int GetLayoutId() {
        return R.layout.detail_word_of_topic_fragment;
    }

    @Override
    public boolean IsBackButtonVisible() {
        return true;
    }

    @Override
    protected String GetScreenTitle() {
        return "";
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        this.mPosition = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void InsertData(ArrayList<Word> listItem) {
        this.listWord = listItem;
        for (int i = 0; i < listItem.size(); i++){
            homePagerAdapter.addFrag(new DetailWordFragment(listItem.get(i), mTopic, this), "");
        }
        homePagerAdapter.notifyDataSetChanged();
        mViewPager.setCurrentItem(mPosition, true);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void setSlidingViewPager() {
        mViewPager.setPagingEnabled(isPagingEnabled = !isPagingEnabled);
    }
}
