package com.yangyuning.maoyan.app;

import android.app.Application;
import android.content.Context;

/**
 * Created by dllo on 16/10/18.
 * Application
 * @author 杨宇宁
 */
public class MaoYanApp extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }
}
