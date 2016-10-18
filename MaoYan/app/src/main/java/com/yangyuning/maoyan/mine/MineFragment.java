package com.yangyuning.maoyan.mine;

import android.os.Bundle;

import com.yangyuning.maoyan.R;
import com.yangyuning.maoyan.base.AbsBaseFragment;

/**
 * Created by dllo on 16/10/18.
 * 我的Fragment
 * @author 杨宇宁 10.18
 */
public class MineFragment extends AbsBaseFragment {

    public static MineFragment newInstance() {
        Bundle args = new Bundle();
        MineFragment fragment = new MineFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initDatas() {

    }
}
