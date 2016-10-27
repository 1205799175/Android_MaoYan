package com.yangyuning.maoyan.activity;

import android.content.Intent;
import android.os.CountDownTimer;
import android.view.WindowManager;

import com.yangyuning.maoyan.R;
import com.yangyuning.maoyan.base.AbsBaseActivity;

/**
 * Created by dllo on 16/10/27.
 * 欢迎页
 * @author 姜鑫
 */
public class WelcomeActivity extends AbsBaseActivity {
    @Override
    protected int setLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initView() {

        //隐藏标题栏
//        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        //隐藏状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    protected void initDatas() {
        CountDownTimer timer = new CountDownTimer(3000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        };

        timer.start();
    }


}
