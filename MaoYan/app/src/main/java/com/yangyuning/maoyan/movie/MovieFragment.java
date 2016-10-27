package com.yangyuning.maoyan.movie;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.yangyuning.maoyan.R;
import com.yangyuning.maoyan.base.AbsBaseFragment;
import com.yangyuning.maoyan.mode.bean.MovieBean;
import com.yangyuning.maoyan.mode.bean.PayBean;
import com.yangyuning.maoyan.mode.db.LiteOrmInstance;
import com.yangyuning.maoyan.mode.net.OkHttpInstance;
import com.yangyuning.maoyan.movie.area.AreaActivity;
import com.yangyuning.maoyan.movie.area.VolleyInstance;
import com.yangyuning.maoyan.movie.area.VolleyResult;
import com.yangyuning.maoyan.movie.play.MoviePlayActivity;
import com.yangyuning.maoyan.movie.zxing.activity.CaptureActivity;
import com.yangyuning.maoyan.utils.CardUtils;
import com.yangyuning.maoyan.utils.MaoYanValue;
import com.yangyuning.maoyan.utils.ThreadInstance;
import com.yangyuning.maoyan.views.CardView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;

import java.util.List;

import okhttp3.Call;

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
    private CardView cardView;
    private List<MovieBean.DataBean.HotBean> datas;
    private ProgressBar progressBar;

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
        cardView = byView(R.id.movie_card);
        progressBar = byView(R.id.movie_progress_bar);
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
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getData(String area) {
        areatTv.setText(area);
    }

    private void getNetDatas() {
        ThreadInstance.getInstance().startThread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                OkHttpInstance.getAsyn(MaoYanValue.MOVIE, new OkHttpInstance.ResultCallback() {
                    @Override
                    public void onError(Call call, Exception e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(Object response) {
                        Gson gson = new Gson();
                        MovieBean movieBean = gson.fromJson(response.toString(), MovieBean.class);
                        datas = movieBean.getData().getHot();
                        progressBar.setVisibility(View.GONE);
                        initUi();
                    }
                });
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

    private void initUi() {
        cardView.setOnCardClickListener(this);
        //设置cardView的上下长度大小(根绝屏幕的物理尺寸)
        cardView.setItemSpace(CardUtils.convertDpToPixelInt(getContext(), 20));

        CardViewAdapter adapter = new CardViewAdapter(context);
        adapter.addAll(datas);
        cardView.setAdapter(adapter);
    }

    //cardView点击事件
    @Override
    public void onCardClick(View view, int position) {
          Intent intent = new Intent(context, MoviePlayActivity.class);
          intent.putExtra(MoviePlayActivity.MOVIE_URL,datas.get(position%datas.size()).getVideourl());
          intent.putExtra(MoviePlayActivity.MOVIE_NAME,datas.get(position%datas.size()).getNm());
          intent.putExtra(MoviePlayActivity.MOVIE_FORE,datas.get(position%datas.size()).getVideoName());
          intent.putExtra(MoviePlayActivity.MOVIE_DIR,datas.get(position%datas.size()).getDir());
          intent.putExtra(MoviePlayActivity.MOVIE_DESC,datas.get(position%datas.size()).getDesc());
          intent.putExtra(MoviePlayActivity.MOVIE_CAT,datas.get(position%datas.size()).getCat());
          intent.putExtra(MoviePlayActivity.MOVIE_TIME,datas.get(position%datas.size()).getShowTimeInfo());

          context.startActivity(intent);

        getActivity().overridePendingTransition(R.anim.movie_play_rotate, R.anim.movie_play_rotate);
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
            TextView nameTv, authorPointTv, proPointTv, detialTv, countTv, priceTv;
            ImageView buyIv;
            nameTv = (TextView) convertView.findViewById(R.id.movie_film_name);
            authorPointTv = (TextView) convertView.findViewById(R.id.movie_film_audience_point);
            proPointTv = (TextView) convertView.findViewById(R.id.movie_film_professional_point);
            detialTv = (TextView) convertView.findViewById(R.id.movie_film_detail);
            countTv = (TextView) convertView.findViewById(R.id.movie_film_count);
            buyIv = (ImageView) convertView.findViewById(R.id.movie_film_buy);
            priceTv = (TextView) convertView.findViewById(R.id.movie_film_price);
            final MovieBean.DataBean.HotBean text = getItem(position % datas.size());
            nameTv.setText(text.getNm());
            authorPointTv.setText(text.getMk() + "");
            proPointTv.setText(text.getProScore() + "");
            detialTv.setText(text.getScm());
            countTv.setText(text.getShowInfo());
            priceTv.setText(text.getVnum() + context.getResources().getString(R.string.yuan));
            //点击预售按钮
            buyIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LiteOrmInstance.getLiteOrmInstance().insertOne(new PayBean(text.getNm(), text.getVnum()));
                    Toast.makeText(context, context.getResources().getString(R.string.have_add_one) + text.getNm() + context.getResources().getString(R.string.to_payment), Toast.LENGTH_SHORT).show();
                }
            });
            return convertView;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
