package com.app.learningtoeic.mvp;

/**
 * Created by dell on 4/4/2017.
 */

public interface IPresenterViewOps {
    void takeView(IViewOps viewTarget);
    void OnCreate();
    void OnPause();
    void OnResume();
    void OnDestroy();
}
