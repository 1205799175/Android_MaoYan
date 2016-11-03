package com.yangyuning.maoyan.app;

import android.app.Application;
import android.content.Context;

import com.fuqianla.paysdk.FuQianLa;

import cn.sharesdk.framework.ShareSDK;

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
        FuQianLa.getInstance().init(getApplicationContext());
        context = getApplicationContext();
        ShareSDK.initSDK(this);
    }

    public static Context getContext() {
        return context;
    }
}
