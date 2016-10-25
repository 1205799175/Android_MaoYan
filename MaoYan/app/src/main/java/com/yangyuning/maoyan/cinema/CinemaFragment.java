package com.yangyuning.maoyan.cinema;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.yangyuning.maoyan.R;
import com.yangyuning.maoyan.base.AbsBaseFragment;
import com.yangyuning.maoyan.cinema.map.MapActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/10/18.
 * 影院Fragment
 *
 * @author 杨宇宁 10.18
 */
public class CinemaFragment extends AbsBaseFragment {

    private TextView areaTv, titleTv;
    private ImageView areaIv, searchIv;
    private ListView listView;
    private CinemaAdapter cinemaAdapter;
    private TextView textView;
    private List<String> datas;

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
        listView = byView(R.id.cinema_list_view);
        textView = byView(R.id.map_tv);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(context,MapActivity.class);
                intent.putExtra("move","大连市沙河口区中山路673号福利庭生活广场3F");
                context.startActivity(intent);
            }
        });
    }

    @Override
    protected void initDatas() {
        datas = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            datas.add("电影院" + i);
        }
        cinemaAdapter = new CinemaAdapter(context);
        cinemaAdapter.setDatas(datas);
        listView.setAdapter(cinemaAdapter);
        initTitleBar();
    }

    private void initTitleBar() {
        areaTv.setText(R.string.dalian);
        titleTv.setText(R.string.cinema);
        searchIv.setImageResource(R.mipmap.title_bar_search);
        areaIv.setImageResource(R.mipmap.title_bar_erea);
    }
}
