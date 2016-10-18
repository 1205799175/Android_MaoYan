package com.yangyuning.maoyan.base;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.zhy.autolayout.AutoLayoutActivity;

/**
 * Created by dllo on 16/10/17.
 * Activity基类
 * @author 杨宇宁
 */
public abstract class AbsBaseActivity extends AutoLayoutActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置状态栏与duck栏颜色相同, 属于沉浸式状态栏
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        //Color.parseColor 将不是int类型的颜色转为int类型
        //为状态栏添加新的颜色
        window.setStatusBarColor(Color.parseColor("#ff3333"));

        //设置布局
        setContentView(setLayout());
        //初始化组件
        initView();
        //初始化数据
        initDatas();
    }

    /**
     * 设置布局文件
     * @return R.layout.xx
     */
    protected abstract int setLayout();

    /**
     * 初始化组件
     */
    protected abstract void initView();

    /**
     * 初始化数据
     */
    protected abstract void initDatas();

    /**
     * 简化FindViewById
     */
    protected  <T extends View> T byView(int resId){
        return (T) findViewById(resId);
    }

    /**
     * 不传值跳转
     */
    protected void goTo(Context from, Class<? extends AbsBaseActivity> to){
        startActivity(new Intent(from, to));
    }

    /**
     * 传值跳转
     */
    protected void goTo(Context from, Class<? extends AbsBaseActivity> to, Bundle extras){
        Intent intent = new Intent(from, to);
        intent.putExtras(extras);
        startActivity(intent);
    }
}
