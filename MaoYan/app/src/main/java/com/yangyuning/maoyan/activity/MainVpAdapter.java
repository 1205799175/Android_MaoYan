package com.yangyuning.maoyan.activity;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yangyuning.maoyan.R;

import java.util.List;

/**
 * Created by dllo on 16/10/18.
 * ViewPager适配器
 * @author 杨宇宁
 */
public class MainVpAdapter extends FragmentPagerAdapter{

    private List<Fragment> fragments;
    private Context context;

    public void setFragments(List<Fragment> fragments) {
        this.fragments = fragments;
        notifyDataSetChanged();
    }

    public MainVpAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    public View getTabViewByPosition(int pos) {
        // 从xml文件注入一个Tab视图,设置为无父容器
        View view = LayoutInflater.from(context).inflate(R.layout.item_tablayout, null);
        ImageView img = (ImageView) view.findViewById(R.id.item_tb_iv);
        TextView tv = (TextView) view.findViewById(R.id.item_tb_tv);
        switch (pos) {
            case 0:
                tv.setText(R.string.movie);
                img.setImageResource(R.drawable.selector_radio_button_movie);
                // 默认拨号界面被点击
                img.setSelected(true);
                tv.setSelected(true);
                break;
            case 1:
                tv.setText(R.string.cinema);
                img.setImageResource(R.drawable.selector_radio_button_cinema);
                break;
            case 2:
                tv.setText(R.string.mine);
                img.setImageResource(R.drawable.selector_radio_button_mine);
                break;
            default:
                break;
        }
        return view;
    }

}
