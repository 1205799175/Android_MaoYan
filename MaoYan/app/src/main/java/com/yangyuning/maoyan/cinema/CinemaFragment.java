package com.yangyuning.maoyan.cinema;

import android.os.Bundle;

import com.yangyuning.maoyan.R;
import com.yangyuning.maoyan.base.AbsBaseFragment;

/**
 * Created by dllo on 16/10/18.
 * 影院Fragment
 * @author 杨宇宁 10.18
 */
public class CinemaFragment extends AbsBaseFragment {

    public static CinemaFragment newInstance() {
        Bundle args = new Bundle();
        CinemaFragment fragment = new CinemaFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_cinema;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initDatas() {

    }
}
