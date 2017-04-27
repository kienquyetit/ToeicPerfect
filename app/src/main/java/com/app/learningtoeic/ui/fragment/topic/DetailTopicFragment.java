package com.app.learningtoeic.ui.fragment.topic;

import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;

import com.app.learningtoeic.R;
import com.app.learningtoeic.contract.topic.DetailTopicContract;
import com.app.learningtoeic.entity.Topic;
import com.app.learningtoeic.mvp.fragment.MVPFragment;
import com.app.learningtoeic.presenter.topic.DetailTopicPresenter;
import com.app.learningtoeic.ui.adapter.HomePagerAdapter;
import com.app.learningtoeic.ui.fragment.dictionary.DictionaryFragment;

/**
 * Created by dell on 4/15/2017.
 */

public class DetailTopicFragment extends MVPFragment<DetailTopicContract.IPresenterViewOps> implements DetailTopicContract.IViewOps,ViewPager.OnPageChangeListener, TabLayout.OnTabSelectedListener {

    TabLayout mTabLayout;
    ViewPager mViewPager;
    Topic mTopic;

    public DetailTopicFragment(Topic topic)
    {
        this.mTopic = topic;
    }

    @Override
    protected DetailTopicContract.IPresenterViewOps OnRegisterPresenter() {
        return new DetailTopicPresenter();
    }

    @Override
    protected void OnViewCreated() {
        mViewPager.addOnPageChangeListener(this);
        setUpViewPager();
        mTabLayout.setupWithViewPager(mViewPager);
        initTab();
    }

    void initTab()
    {
        mTabLayout.getTabAt(0).select();
        mTabLayout.addOnTabSelectedListener(this);
        mTabLayout.setTabTextColors(
                ContextCompat.getColor(getContext(), R.color.black),
                ContextCompat.getColor(getContext(), R.color.white)
        );
    }

    private void setUpViewPager() {
        mViewPager.setOffscreenPageLimit(1);
        HomePagerAdapter homePagerAdapter = new HomePagerAdapter(getChildFragmentManager());
        homePagerAdapter.addFrag(new DictionaryFragment(topicId),"Self Study");
        homePagerAdapter.addFrag(new HomeworkFragment(topicId),"Homework");
        mViewPager.setAdapter(homePagerAdapter);
    }

    @Override
    protected void OnBindView() {
        mTabLayout = (TabLayout) FindViewById(R.id.tab_layout);
        mViewPager = (ViewPager) FindViewById(R.id.detail_topic_vp);
    }

    @Override
    public int GetLayoutId() {
        return R.layout.detail_topic_fragment;
    }

    @Override
    public boolean IsBackButtonVisible() {
        return true;
    }

    @Override
    protected String GetScreenTitle() {
        return mTopic.name;
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

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
