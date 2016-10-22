package com.yangyuning.maoyan.movie;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.yangyuning.maoyan.R;
import com.yangyuning.maoyan.base.AbsBaseFragment;
import com.yangyuning.maoyan.mode.bean.MovieBean;
import com.yangyuning.maoyan.movie.area.AreaActivity;
import com.yangyuning.maoyan.movie.area.VolleyInstance;
import com.yangyuning.maoyan.movie.area.VolleyResult;
import com.yangyuning.maoyan.movie.zxing.activity.CaptureActivity;
import com.yangyuning.maoyan.utils.MaoYanValue;
import com.yangyuning.maoyan.views.RefreshListView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/10/18.
 * 电影Fragment
 *
 * @author 杨宇宁 10.18
 */
public class MovieFragment extends AbsBaseFragment implements RefreshListView.OnRefreshListener {

    private TextView areatTv, titleTv;
    private ImageView qRCode; //二维码
    private static final int PHOTO_PIC = 1;
    private RefreshListView movieListview;
    private MovieAdapter movieAdapter;
    private List<String> date;
    private final static int REFRESH_COMPLETE = 0;

    /**
     * 刷新视图
     */
    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case REFRESH_COMPLETE:
                    movieListview.setOnRefreshComplete();
//                    movieAdapter.setDatas(date);
                    movieAdapter.notifyDataSetChanged();
                    movieListview.setSelection(0);
                    break;

                default:
                    break;
            }
        }
    };

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
        areatTv = byView(R.id.title_bar_tv_left);
        titleTv = byView(R.id.title_bar_tv);
        qRCode = byView(R.id.title_bar_iv_share);
        movieListview = byView(R.id.movie_lv);

    }

    @Override
    protected void initDatas() {
        //注册
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        //标题栏
        intiTitleBar();
        //点击事件
        initListener();
        //初始化适配器
        movieAdapter = new MovieAdapter(context);
        //获取网络数据
        getNetDatas();
        movieListview.setOnRefreshListener(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getData(String area) {
        areatTv.setText(area);
    }

    private void getNetDatas() {
        VolleyInstance.getInstance().startResult(MaoYanValue.MOVIE, new VolleyResult() {
            @Override
            public void success(String resultStr) {
                Gson gson = new Gson();
                MovieBean movieBean = gson.fromJson(resultStr, MovieBean.class);
                List<MovieBean.DataBean.HotBean> datas = movieBean.getData().getHot();
                movieListview.setAdapter(movieAdapter);
                movieAdapter.setDatas(datas);
            }

            @Override
            public void failure() {

            }
        });
    }

    private void intiTitleBar() {
        qRCode.setImageResource(R.mipmap.two_dimen_bar);
        areatTv.setText(R.string.dalian);
        titleTv.setText(R.string.hotShow);
    }

    private void initListener() {
        //点击二维码
        qRCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(context, CaptureActivity.class);
                startActivityForResult(intent3, PHOTO_PIC);
            }
        });
        //点击选择地区
        areatTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, AreaActivity.class));
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1) {
            switch (requestCode) {
                case PHOTO_PIC:
                    String result2 = data.getExtras().getString("result");
                    Uri uri = Uri.parse(result2);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    context.startActivity(intent);

                    break;
                default:
                    break;
            }
        }
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
//                    date.add(0, "我家有一只凤尾蝶");
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
