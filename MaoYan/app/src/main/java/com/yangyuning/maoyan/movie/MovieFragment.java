package com.yangyuning.maoyan.movie;

import android.os.Bundle;

import com.yangyuning.maoyan.R;
import com.yangyuning.maoyan.base.AbsBaseFragment;

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
