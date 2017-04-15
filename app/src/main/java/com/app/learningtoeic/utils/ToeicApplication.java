package com.app.learningtoeic.utils;

import android.app.Application;

import java.io.IOException;

/**
 * Created by Quyet on 2/7/2017.
 */

public class ToeicApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            Config.wordDB = new DbHelper(getApplicationContext());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
