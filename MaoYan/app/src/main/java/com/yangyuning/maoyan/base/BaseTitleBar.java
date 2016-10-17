package com.yangyuning.maoyan.base;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yangyuning.maoyan.R;

/**
 * Created by dllo on 16/10/17.
 * 标题栏封装
 * @author 杨宇宁
 */
public class BaseTitleBar {
    ImageView title_bar_left;
    TextView title_bar_title;
    ImageView title_bar_share;
    ImageView title_bar_collect;
    public BaseTitleBar(AppCompatActivity context) {
        title_bar_title = (TextView) context.findViewById(R.id.title_bar_tv);
        title_bar_left = (ImageView) context.findViewById(R.id.title_bar_iv_left);
        title_bar_share = (ImageView) context.findViewById(R.id.title_bar_iv_share);
        title_bar_collect = (ImageView) context.findViewById(R.id.title_bar_iv_collect);
    }
    public BaseTitleBar setTitle(String str) {
        title_bar_title.setText(str);
        return this;
    }
    public BaseTitleBar setTextLeft(String str) {
        title_bar_title.setVisibility(View.VISIBLE);
        title_bar_title.setText(str);
        return this;
    }

    public BaseTitleBar setImageLeftRes(int res) {
        title_bar_share.setVisibility(View.VISIBLE);
        title_bar_share.setImageResource(res);
        return this;
    }
    public BaseTitleBar setImageRightRes(int res) {
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
