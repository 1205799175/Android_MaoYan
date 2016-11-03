package com.yangyuning.maoyan.mine.refesh;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.jude.swipbackhelper.SwipeBackHelper;
import com.yangyuning.maoyan.R;
import com.yangyuning.maoyan.base.AbsBaseActivity;
import com.yangyuning.maoyan.base.BaseTitleBar;
import com.yangyuning.maoyan.cinema.CinemaAdapter;
import com.yangyuning.maoyan.cinema.IStarChangeListener;
import com.yangyuning.maoyan.cinema.map.MapActivity;
import com.yangyuning.maoyan.mode.bean.CinemaBean;
import com.yangyuning.maoyan.utils.GestureHelper;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;

/**
 * Created by dllo on 16/10/28.
 * 刷新 雨滴效果
 * @author 杨宇宁
 */
public class RefeshActivity extends AbsBaseActivity implements WaveSwipeRefreshLayout.OnRefreshListener {

    private ListView mListview;

    private List<CinemaBean> datas;
    private CinemaAdapter cinemaAdapter;
    private WaveSwipeRefreshLayout mWaveSwipeRefreshLayout;

    @Override
    protected int setLayout() {
        return R.layout.activity_refesh;
    }

    @Override
    protected void initView() {
        mWaveSwipeRefreshLayout = byView(R.id.main_swipe);
        mListview = byView(R.id.main_list);
        mWaveSwipeRefreshLayout.setColorSchemeColors(Color.WHITE, Color.WHITE);
        mWaveSwipeRefreshLayout.setOnRefreshListener(this);
        mWaveSwipeRefreshLayout.setWaveColor(Color.argb(100,255,0,0));
    }

    @Override
    protected void initDatas() {
        setSampleData();
        initListener();
        initTitle();
    }

    private void initTitle() {
        new BaseTitleBar(this).setImageLsftRes(R.mipmap.title_bar_back).setTitle(getResources().getString(R.string.refesh_water)).setBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RefeshActivity.this.finish();
            }
        });
    }

    private void initListener() {
        mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(RefeshActivity.this, MapActivity.class);
                intent.putExtra(MapActivity.KEY_ADDTESS, datas.get(position - 1).getAddr());
                startActivity(intent);
            }
        });

        cinemaAdapter.setStarChangeListener(new IStarChangeListener() {
            @Override
            public void onShow() {
            }

            @Override
            public void onDismiss() {
            }
        });
    }

    private void setSampleData() {
        datas = new ArrayList<>();
        //从excel获取数据
        getExcelData();
        cinemaAdapter = new CinemaAdapter(this);
        cinemaAdapter.setDatas(datas);
        mListview.setAdapter(cinemaAdapter);
    }

    private void getExcelData() {
        try {
            InputStream is = getAssets().open("cinema.xls");
            Workbook book = Workbook.getWorkbook(is);
            book.getNumberOfSheets();
            // 获得第一个工作表对象
            Sheet sheet = book.getSheet(0);
            int Rows = sheet.getRows();
            for (int i = 1; i < Rows; ++i) {
                String addr = (sheet.getCell(0, i)).getContents();
                String name = (sheet.getCell(1, i)).getContents();
                String sellPrice = (sheet.getCell(2, i)).getContents();
                datas.add(new CinemaBean(addr, name, sellPrice));
            }
            book.close();
        } catch (Exception e) {
        }
    }

    private void refresh(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mWaveSwipeRefreshLayout.setRefreshing(false);
            }
        }, 3000);
    }

    @Override
    protected void onResume() {
        //mWaveSwipeRefreshLayout.setRefreshing(true);
        refresh();
        super.onResume();
    }

    @Override
    public void onRefresh() {
        refresh();
    }

}
