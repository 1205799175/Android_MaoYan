package com.yangyuning.maoyan.mine.order;

import android.app.Application;

import com.fuqianla.paysdk.FuQianLa;

/**
 * Created by siqi on 16/5/24.
 *
 * @author siqi
 */
public class DemoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FuQianLa.getInstance().init(getApplicationContext());
    }
}
