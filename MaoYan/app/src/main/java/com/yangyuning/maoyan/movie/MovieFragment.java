package com.yangyuning.maoyan.movie;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.yangyuning.maoyan.R;
import com.yangyuning.maoyan.base.AbsBaseFragment;
import com.yangyuning.maoyan.mode.bean.MovieBean;
import com.yangyuning.maoyan.movie.area.AreaActivity;
import com.yangyuning.maoyan.movie.area.VolleyInstance;
import com.yangyuning.maoyan.movie.area.VolleyResult;
import com.yangyuning.maoyan.movie.play.MoviePlayFragment;
import com.yangyuning.maoyan.movie.zxing.activity.CaptureActivity;
import com.yangyuning.maoyan.utils.CardUtils;
import com.yangyuning.maoyan.utils.MaoYanValue;
import com.yangyuning.maoyan.views.CardView;
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
public class MovieFragment extends AbsBaseFragment implements CardView.OnCardClickListener {

    public static final String KET_RESULT = "result";
    private TextView areatTv, titleTv;
    private ImageView qRCode; //二维码
    private static final int PHOTO_PIC = 1;
    private MoviePlayFragment frag;
    private CardView cardView;
    private List<MovieBean.DataBean.HotBean> datas;

    /**
     * 刷新视图
     */
//    private Handler mHandler = new Handler() {
//        public void handleMessage(android.os.Message msg) {
//            switch (msg.what) {
//                case REFRESH_COMPLETE:
//                    movieListview.setOnRefreshComplete();
////                    movieAdapter.setDatas(date);
//                    movieAdapter.notifyDataSetChanged();
//                    movieListview.setSelection(0);
//                    break;
//
//                default:
//                    break;
//            }
//        }
//    };
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
//        movieListview = byView(R.id.movie_lv);
        cardView = byView(R.id.movie_card);


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
        //获取网络数据
        getNetDatas();
//        movieListview.setOnRefreshListener(this);

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
                datas = movieBean.getData().getHot();
//                movieListview.setAdapter(movieAdapter);
//                movieAdapter.setDatas(datas);
                initUi();
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
                    String result2 = data.getExtras().getString(KET_RESULT);
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
//    @Override
//    public void onRefresh() {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(3000);
////                    date.add(0, "我家有一只凤尾蝶");
//                    mHandler.sendEmptyMessage(REFRESH_COMPLETE);
//                } catch (InterruptedException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//    }
    private void initUi() {
        cardView.setOnCardClickListener(this);
        //设置cardView的上下长度大小(根绝屏幕的物理尺寸)
        cardView.setItemSpace(CardUtils.convertDpToPixelInt(getContext(), 20));

        CardViewAdapter adapter = new CardViewAdapter(context);
        adapter.addAll(datas);
        cardView.setAdapter(adapter);
        frag = new MoviePlayFragment();
        FragmentManager manager = getChildFragmentManager();
        manager.beginTransaction().replace(R.id.movie_replace, frag).commit();
    }


    //cardView点击事件
    @Override
    public void onCardClick(View view, int position) {
        Bundle bundle = new Bundle();
        //传递数据
        bundle.putString("text", datas.get(position % datas.size()).getNm());
        frag.show(view, bundle);
    }

    //cardView的适配器
    public class CardViewAdapter extends CardAdapter<MovieBean.DataBean.HotBean> {

        public CardViewAdapter(Context context) {
            super(context);
        }

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        protected View getCardView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.item_movie_lv, parent, false);
            }
            TextView nameTv, authorPointTv, detialTv, countTv;
            nameTv = (TextView) convertView.findViewById(R.id.movie_film_name);
            authorPointTv = (TextView) convertView.findViewById(R.id.movie_film_audience_point);
            detialTv = (TextView) convertView.findViewById(R.id.movie_film_detail);
            countTv = (TextView) convertView.findViewById(R.id.movie_film_count);
            MovieBean.DataBean.HotBean text = getItem(position % datas.size());
            nameTv.setText(text.getNm());
            authorPointTv.setText(text.getMk() + "");
            detialTv.setText(text.getScm());
            countTv.setText(text.getShowInfo());
            return convertView;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
