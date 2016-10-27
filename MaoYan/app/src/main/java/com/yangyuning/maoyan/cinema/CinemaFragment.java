package com.yangyuning.maoyan.cinema;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.yangyuning.maoyan.R;
import com.yangyuning.maoyan.base.AbsBaseFragment;
import com.yangyuning.maoyan.cinema.map.MapActivity;
import com.yangyuning.maoyan.mode.bean.CinemaBean;
import com.yangyuning.maoyan.movie.area.AreaActivity;
import com.yangyuning.maoyan.utils.ThreadInstance;
import com.yangyuning.maoyan.views.RefreshListView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;

/**
 * Created by dllo on 16/10/18.
 * 影院Fragment
 *
 * @author 杨宇宁 10.18
 */
public class CinemaFragment extends AbsBaseFragment implements RefreshListView.OnRefreshListener {

    private TextView areaTv, titleTv;
    private ImageView areaIv, searchIv;
    private RefreshListView listView;
    private CinemaAdapter cinemaAdapter;

    private final static int REFRESH_COMPLETE = 0;
    private List<CinemaBean> datas;

    /**
     * 刷新视图
     */
    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case REFRESH_COMPLETE:
                    listView.setOnRefreshComplete();
                    cinemaAdapter.setDatas(datas);
                    cinemaAdapter.notifyDataSetChanged();
                    listView.setSelection(0);
                    break;
                default:
                    break;
            }
        }
    };


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
    }

    @Override
    protected void initDatas() {
        //注册
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }

        datas = new ArrayList<>();
        //从excel获取数据
        getExcelData();
        cinemaAdapter = new CinemaAdapter(context);
        cinemaAdapter.setDatas(datas);
        listView.setAdapter(cinemaAdapter);
        listView.setOnRefreshListener(this);
        initTitleBar();

        initListener();
    }

    private void initListener() {
        //点击选择地区
        areaTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, AreaActivity.class));
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(context, MapActivity.class);
                intent.putExtra(MapActivity.KEY_ADDTESS, datas.get(position - 1).getAddr());
                context.startActivity(intent);
            }
        });
    }

    private void getExcelData() {
        try {
            InputStream is = context.getAssets().open("cinema.xls");
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getData(String area) {
        areaTv.setText(area);
    }


    private void initTitleBar() {
        areaTv.setText(R.string.dalian);
        titleTv.setText(R.string.cinema);
        searchIv.setImageResource(R.mipmap.title_bar_search);
        areaIv.setImageResource(R.mipmap.title_bar_erea);
    }

    /**
     * 刷新添加数据
     */
    @Override
    public void onRefresh() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
//                    datas.add(0, "我家有一只凤尾蝶");
                    mHandler.sendEmptyMessage(REFRESH_COMPLETE);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
