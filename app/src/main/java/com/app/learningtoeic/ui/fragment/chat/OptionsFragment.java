package com.app.learningtoeic.ui.fragment.chat;

import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;

import com.app.learningtoeic.R;
import com.app.learningtoeic.contract.chat.OptionsContract;
import com.app.learningtoeic.entity.Topic;
import com.app.learningtoeic.mvp.fragment.MVPFragment;
import com.app.learningtoeic.presenter.chat.OptionsPresenter;
import com.app.learningtoeic.ui.adapter.HomePagerAdapter;

/**
 * Created by QUYET on 5/4/2017.
 */

public class OptionsFragment extends MVPFragment<OptionsContract.IPresenterViewOps> implements OptionsContract.IViewOps,ViewPager.OnPageChangeListener, TabLayout.OnTabSelectedListener {

    TabLayout mTabLayout;
    ViewPager mViewPager;
    Topic mTopic;

    public OptionsFragment()
    {

    }

    @Override
    protected OptionsContract.IPresenterViewOps OnRegisterPresenter() {
        return new OptionsPresenter();
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
        homePagerAdapter.addFrag(new ContactsFragment(),"Contacts");
        homePagerAdapter.addFrag(new ChatRoomFragment(),"Chat Room");
        mViewPager.setAdapter(homePagerAdapter);
    }

    @Override
    protected void OnBindView() {
        mTabLayout = (TabLayout) FindViewById(R.id.options_tab_layout);
        mViewPager = (ViewPager) FindViewById(R.id.options_vp);
    }

    @Override
    public int GetLayoutId() {
        return R.layout.options_fragment;
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
