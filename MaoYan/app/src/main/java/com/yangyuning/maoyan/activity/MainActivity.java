package com.yangyuning.maoyan.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yangyuning.maoyan.R;
import com.yangyuning.maoyan.base.AbsBaseActivity;
import com.yangyuning.maoyan.base.BaseTitleBar;
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
        intiFragment();
        intiAdapter();
        intiTab();
        new BaseTitleBar(this).setTextLeft("大连").setTitle("热映");
    }

    private void intiTab() {
        for (int i = 0; i < mainTb.getTabCount(); i++) {
            mainTb.getTabAt(i).setCustomView(vpAdapter.getTabViewByPosition(i));
        }

        // 默认电影界面被点击
        mainTb.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition()!=0) {
                    mainTb.getTabAt(0).getCustomView().findViewById(R.id.item_tb_tv).setSelected(false);
                    mainTb.getTabAt(0).getCustomView().findViewById(R.id.item_tb_iv).setSelected(false);
                }
                mainVp.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void intiAdapter() {
        vpAdapter = new MainVpAdapter(getSupportFragmentManager(), this);
        vpAdapter.setFragments(fragments);
        mainVp.setAdapter(vpAdapter);
        mainTb.setupWithViewPager(mainVp);
    }

    private void intiFragment() {
        fragments = new ArrayList<>();
        fragments.add(MovieFragment.newInstance());
        fragments.add(CinemaFragment.newInstance());
        fragments.add(MineFragment.newInstance());
    }
}
