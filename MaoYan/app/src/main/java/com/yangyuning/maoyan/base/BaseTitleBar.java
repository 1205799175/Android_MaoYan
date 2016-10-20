package com.yangyuning.maoyan.base;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yangyuning.maoyan.R;
import com.yangyuning.maoyan.app.MaoYanApp;

/**
 * Created by dllo on 16/10/17.
 * 标题栏封装
 * @author 杨宇宁
 */
public class BaseTitleBar {
    RelativeLayout titleBar;
    TextView title_bar_left_tv;
    ImageView title_bar_left;
    TextView title_bar_title;
    ImageView title_bar_share;
    ImageView title_bar_collect;
    public BaseTitleBar(AppCompatActivity context) {
        titleBar = (RelativeLayout) context.findViewById(R.id.title_bar);
        title_bar_left_tv = (TextView) context.findViewById(R.id.title_bar_tv_left);   //左边文字
        title_bar_left = (ImageView) context.findViewById(R.id.title_bar_iv_left);     //左边图片
        title_bar_title = (TextView) context.findViewById(R.id.title_bar_tv);      //中间文字
        title_bar_share = (ImageView) context.findViewById(R.id.title_bar_iv_share);   //右边分享
        title_bar_collect = (ImageView) context.findViewById(R.id.title_bar_iv_collect);   //右边收藏
    }

    public BaseTitleBar isShowTitleBar(boolean isShow){
        if (isShow == true){
            titleBar.setVisibility(View.GONE);
        }
        return this;
    }

    /**
     * 左边文字
     */
    public BaseTitleBar setTextLeft(String str){
        title_bar_left_tv.setText(str);
        return this;
    }

    /**
     * 左边图片
     */
    public BaseTitleBar setImageLsftRes(int res){
        title_bar_left.setVisibility(View.VISIBLE);
        title_bar_left.setImageResource(res);
        title_bar_left_tv.setVisibility(View.GONE);
        return this;
    }

    /**
     * 中间文字
     */
    public BaseTitleBar setTitle(String str) {
        title_bar_title.setText(str);
        return this;
    }
    /**
     * 右边分享
     */
    public BaseTitleBar setImageShare(int res) {
        title_bar_share.setVisibility(View.VISIBLE);
        title_bar_share.setImageResource(res);
        return this;
    }

    /**
     * 右边收藏
     */
    public BaseTitleBar setImageCollect(int res) {
        title_bar_collect.setVisibility(View.VISIBLE);
        title_bar_collect.setImageResource(res);
        return this;
    }

    public BaseTitleBar setLeftListener(View.OnClickListener listener) {
        if(title_bar_share.getVisibility() == View.VISIBLE) {
            title_bar_share.setOnClickListener(listener);
        }
        if(title_bar_share.getVisibility() == View.VISIBLE) {
            title_bar_share.setOnClickListener(listener);
        }
        return this;
    }
    public BaseTitleBar setRightListener(View.OnClickListener listener) {
        if(title_bar_collect.getVisibility() == View.VISIBLE) {
            title_bar_collect.setOnClickListener(listener);
        }
        if(title_bar_collect.getVisibility() == View.VISIBLE) {
            title_bar_collect.setOnClickListener(listener);
        }
        return this;
    }

}
