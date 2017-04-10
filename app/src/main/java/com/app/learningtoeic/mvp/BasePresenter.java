package com.app.learningtoeic.mvp;

/**
 * Created by dell on 4/4/2017.
 */

public abstract class BasePresenter<ViewTarget extends IViewOps> implements IPresenterModelOps,IPresenterViewOps
{
    private ViewTarget View;

    public abstract void OnCreate();
    public abstract void OnDestroy();
    public abstract void OnPause();
    public abstract void OnResume();

    protected ViewTarget getView()
    {
        return View;
    }

    @Override
    public void takeView(IViewOps viewTarget) {
        View = (ViewTarget) viewTarget;
    }
}
