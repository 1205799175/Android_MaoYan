package com.yangyuning.maoyan.movie;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.yangyuning.maoyan.R;
import com.yangyuning.maoyan.app.MaoYanApp;
import com.yangyuning.maoyan.base.AbsBaseFragment;
import com.yangyuning.maoyan.base.BaseTitleBar;

/**
 * Created by dllo on 16/10/18.
 * 电影Fragment
 * @author 杨宇宁 10.18
 */
public class MovieFragment extends AbsBaseFragment {

    public static MovieFragment newInstance() {
        Bundle args = new Bundle();
        MovieFragment fragment = new MovieFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_movie;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initDatas() {
    }
}
