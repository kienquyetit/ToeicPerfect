package com.app.learningtoeic;

import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.learningtoeic.base.BaseActivity;
import com.app.learningtoeic.base.BaseFragment;
import com.app.learningtoeic.ui.fragment.HomeFragment;
import com.app.learningtoeic.ui.fragment.chat.ChangePasswordFragment;
import com.app.learningtoeic.utils.FragmentHelper;

public class MainActivity extends BaseActivity
{

    View imvBack;
    TextView tvTitle;
    android.support.v7.widget.Toolbar MainToolbar;
    FrameLayout ViewItemToolbar;
    ImageView ivLike;
    FloatingActionButton fabListening, fabRecording, fabSliding;

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
        ivLike = (ImageView) findViewById(R.id.iv_favourite);
        imvBack = findViewById(R.id.imvBack);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        fabListening = (FloatingActionButton) findViewById(R.id.fab_listening);
        fabRecording = (FloatingActionButton) findViewById(R.id.fab_recording);
        fabSliding = (FloatingActionButton) findViewById(R.id.fab_sliding);
        imvBack.setOnClickListener(new View.OnClickListener() {
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
        switch (item.getItemId()){
            case R.id.nav_change_password:
                SwitchFragment(new ChangePasswordFragment(), true);
                break;
            case R.id.nav_log_out:
                onBackPressed();
                break;
            default:
                break;
        }
        return isMenuVisible;
    }

    public void SetScreenTitle(String title)
    {
        if(title == null)
        {
            return;
        }
        tvTitle.setText(title);
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

    public void setMenuVisible(boolean visible)
    {
        isMenuVisible = visible;
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
            imvBack.setVisibility(View.VISIBLE);
        } else {
            imvBack.setVisibility(View.GONE);
        }
    }

    public void setImgLikeVisible(boolean isVisible)
    {
        if(isVisible)
        {
            ivLike.setVisibility(View.VISIBLE);
        }
        else
        {
            ivLike.setVisibility(View.GONE);
        }
    }

    public void setListeningButtonVisible(boolean isVisible)
    {
        if(isVisible)
        {
            fabListening.setVisibility(View.VISIBLE);
        }
        else
        {
            fabListening.setVisibility(View.GONE);
        }
    }

    public void setRecordingButtonVisible(boolean isVisible)
    {
        if(isVisible)
        {
            fabRecording.setVisibility(View.VISIBLE);
        }
        else
        {
            fabRecording.setVisibility(View.GONE);
        }
    }

    public void setSlidingButtonVisible(boolean isVisible)
    {
        if(isVisible)
        {
            fabSliding.setVisibility(View.VISIBLE);
        }
        else
        {
            fabSliding.setVisibility(View.GONE);
        }
    }

    public void setFooterVisible(boolean isVisible)
    {
        if(isVisible)
        {
            findViewById(R.id.bottomToolbar).setVisibility(View.VISIBLE);
        }
        else
        {
            findViewById(R.id.bottomToolbar).setVisibility(View.GONE);
        }
    }

    public ImageView getIvLike() {
        return ivLike;
    }

    public FloatingActionButton getFabListening() {
        return fabListening;
    }

    public FloatingActionButton getFabRecording() {
        return fabRecording;
    }

    public FloatingActionButton getFabSliding() {
        return fabSliding;
    }

}
