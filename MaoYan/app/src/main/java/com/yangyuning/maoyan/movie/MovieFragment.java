package com.yangyuning.maoyan.movie;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yangyuning.maoyan.R;
import com.yangyuning.maoyan.base.AbsBaseFragment;
import com.yangyuning.maoyan.movie.qrcode.QRCodeActivity;
import com.yangyuning.maoyan.movie.zxing.activity.CaptureActivity;
import com.yangyuning.maoyan.views.RefreshListView;

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

    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case REFRESH_COMPLETE:
                    movieListview.setOnRefreshComplete();
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
        intiTitleBar();
        initListener();
        initdata();
    }

    private void initdata() {
        date=new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            date.add("寒寒");
        }
        movieAdapter=new MovieAdapter(context);
        movieListview.setAdapter(movieAdapter);
        movieAdapter.setDatas(date);
        movieListview.setOnRefreshListener(this);
    }

    private void intiTitleBar() {
        qRCode.setImageResource(R.mipmap.two_dimen_bar);
        areatTv.setText(R.string.dalian);
        titleTv.setText(R.string.hotShow);
    }

    private void initListener() {
        qRCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(context, CaptureActivity.class);
                startActivityForResult(intent3, PHOTO_PIC);
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
                    Intent intent = new Intent(context, QRCodeActivity.class);
                    intent.putExtra("result", result2);
                    context.startActivity(intent);
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void onRefresh() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    date.add(0, "万紫千红");
                    mHandler.sendEmptyMessage(REFRESH_COMPLETE);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
