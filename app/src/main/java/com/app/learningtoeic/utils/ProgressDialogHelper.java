package com.app.learningtoeic.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v4.app.FragmentManager;

/**
 * Created by dell on 4/8/2017.
 */

public class ProgressDialogHelper {
    public static ProgressDialog dialog;
    static Context context;
    FragmentManager FragmentManager;
    public ProgressDialogHelper(Context context)
    {
        this.context = context;
    }
    public static void ShowProgressDialog(Context context)
    {
        if(dialog == null)
        {
            dialog= ProgressDialog.show(context, "", "File loading ...", false, false);
        }
    }
    public static void HideProgressDIalog()
    {
        dialog.dismiss();
    }
}
