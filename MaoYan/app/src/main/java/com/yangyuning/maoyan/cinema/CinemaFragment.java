package com.yangyuning.maoyan.cinema;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.yangyuning.maoyan.R;
import com.yangyuning.maoyan.activity.MainActivity;
import com.yangyuning.maoyan.base.AbsBaseFragment;
import com.yangyuning.maoyan.base.BaseTitleBar;

/**
 * Created by dllo on 16/10/18.
 * 影院Fragment
 * @author 杨宇宁 10.18
 */
public class CinemaFragment extends AbsBaseFragment {

    private TextView areaTv, titleTv;
    private ImageView areaIv, searchIv;
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
        areaTv = byView(R.id.title_bar_tv_left);
        titleTv = byView(R.id.title_bar_tv);
        areaIv = byView(R.id.title_bar_iv_share);
        searchIv = byView(R.id.title_bar_iv_collect);
    }

    @Override
    protected void initDatas() {
        initTitleBar();
    }

    private void initTitleBar() {
        areaTv.setText(R.string.dalian);
        titleTv.setText(R.string.cinema);
        searchIv.setImageResource(R.mipmap.title_bar_search);
        areaIv.setImageResource(R.mipmap.title_bar_erea);
    }
}
