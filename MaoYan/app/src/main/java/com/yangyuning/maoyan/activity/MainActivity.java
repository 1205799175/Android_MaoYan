package com.yangyuning.maoyan.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yangyuning.maoyan.R;
import com.yangyuning.maoyan.base.AbsBaseActivity;
import com.yangyuning.maoyan.cinema.CinemaFragment;
import com.yangyuning.maoyan.mine.MineFragment;
import com.yangyuning.maoyan.movie.MovieFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AbsBaseActivity {
    private ViewPager mainVp;
    private TabLayout mainTb;
    private MainVpAdapter vpAdapter;
    private List<Fragment> fragments;

    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        mainTb = byView(R.id.main_tb);
        mainVp = byView(R.id.main_vp);
    }

    @Override
    protected void initDatas() {
        fragments = new ArrayList<>();
        vpAdapter = new MainVpAdapter(getSupportFragmentManager());
        fragments.add(MovieFragment.newInstance());
        fragments.add(CinemaFragment.newInstance());
        fragments.add(MineFragment.newInstance());
        vpAdapter.setFragments(fragments);
        mainVp.setAdapter(vpAdapter);
        mainTb.setupWithViewPager(mainVp);

        mainTb.getTabAt(0).setText(R.string.movie);
        mainTb.getTabAt(0).setIcon(R.drawable.selector_radio_button_movie);
        mainTb.getTabAt(1).setText(R.string.cinema);
        mainTb.getTabAt(1).setIcon(R.drawable.selector_radio_button_cinema);
        mainTb.getTabAt(2).setText(R.string.mine);
        mainTb.getTabAt(2).setIcon(R.drawable.selector_radio_button_mine);
    }
}
