package com.app.learningtoeic;

import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.learningtoeic.base.BaseActivity;
import com.app.learningtoeic.base.BaseFragment;
import com.app.learningtoeic.ui.fragment.ChatFragment;
import com.app.learningtoeic.ui.fragment.HomeFragment;
import com.app.learningtoeic.utils.FragmentHelper;

public class MainActivity extends BaseActivity
{

    View ImvBack;
    TextView TvTitle;
    android.support.v7.widget.Toolbar MainToolbar;
    FrameLayout ViewItemToolbar;
    ImageView imgLike;

    boolean isMenuVisible;
    @Override
    protected String GetScreenTitle() {
        return super.GetScreenTitle();
    }

    @Override
    protected void OnViewCreated() {
        ChangeStatusBar();
        MainToolbar = (Toolbar) findViewById(R.id.mainToolbar);
        ViewItemToolbar = (FrameLayout)findViewById(R.id.view_on_toolbar);
        imgLike = (ImageView) findViewById(R.id.ivFavourite);
        ImvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        setupActionBar();
        SwitchFragment(new HomeFragment(), false);
    }

    @Override
    protected void OnBindView() {
        ImvBack = findViewById(R.id.imvBack);
        TvTitle = (TextView) findViewById(R.id.tvTitle);
    }

    @Override
    protected int GetLayoutId() {
        return R.layout.activity_main;
    }
    public void ChangeStatusBar()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        }
    }

    void SetCustomViewOnToolBar(View view)
    {
        ViewItemToolbar.addView(view);
    }

    void setupActionBar()
    {
        setSupportActionBar(MainToolbar);
    }

    @Override
    public void SwitchFragment(Fragment fragment, boolean IsAddToBackStack) {
        super.SwitchFragment(fragment, IsAddToBackStack);
        MainToolbar.hideOverflowMenu();
    }

    public void RemoveItemMenu()
    {
        //ViewItemToolbar.RemoveAllViews();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return isMenuVisible;
    }

    public void SetScreenTitle(String title)
    {
        if(title == null)
        {
            return;
        }
        TvTitle.setText(title);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(isMenuVisible)
        {
            getMenuInflater().inflate(R.menu.activity_main_drawer, menu);
        }
        return isMenuVisible;
    }

    @Override
    public void onBackPressed() {
        BaseFragment currentFragment = (BaseFragment)getSupportFragmentManager().findFragmentById(R.id.root);
        if (currentFragment == null || !currentFragment.OnBackPress())
        {
            super.onBackPressed();
            FragmentHelper.RemoveLastFragment(getSupportFragmentManager());
        }
    }

    public void setMenuVisible(boolean visble)
    {
        isMenuVisible = visble;
        invalidateOptionsMenu();
    }

    public void setHeaderVisible(boolean isVisible)
    {
        if(isVisible)
        {
            MainToolbar.setVisibility(View.VISIBLE);
        }
        else
        {
            MainToolbar.setVisibility(View.GONE);
        }
    }

    public void setBackButtonVisible(boolean isVisible) {
        if (isVisible) {
            ImvBack.setVisibility(View.VISIBLE);
        } else {
            ImvBack.setVisibility(View.GONE);
        }
    }

    public void setImgLikeVisible(boolean isVisible)
    {
        if(isVisible)
        {
            imgLike.setVisibility(View.VISIBLE);
        }
        else
        {
            imgLike.setVisibility(View.GONE);
        }
    }
}
