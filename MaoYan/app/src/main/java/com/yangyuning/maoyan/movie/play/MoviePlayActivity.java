package com.yangyuning.maoyan.movie.play;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.tedcoder.wkvideoplayer.util.DensityUtil;
import com.android.tedcoder.wkvideoplayer.view.MediaController;
import com.android.tedcoder.wkvideoplayer.view.SuperVideoPlayer;
import com.yangyuning.maoyan.R;
import com.yangyuning.maoyan.base.BaseTitleBar;
import com.yangyuning.maoyan.utils.GestureHelper;

/**
 * Created by dllo on 16/10/26.
 * 电影播放界面
 * @author 姜鑫
 */
public class MoviePlayActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String MOVIE_URL = "url";
    public static final String MOVIE_NAME = "name";
    public static final String MOVIE_FORE = "fore";
    public static final String MOVIE_DIR = "dir";
    public static final String MOVIE_DESC = "desc";
    public static final String MOVIE_CAT = "cat";
    public static final String MOVIE_TIME = "time";
    private SuperVideoPlayer mSuperVideoPlayer;
    private View mPlayBtnView;
    private String url;
    private TextView nameTv, foreTv, dirTv, descTv, catTv, timeTv;
    private RelativeLayout view;
    private ConnectivityManager mConnectivity;
    private TelephonyManager mTelephony;
    private NetworkInfo info;

    private GestureHelper gestureHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_play);
        mSuperVideoPlayer = (SuperVideoPlayer) findViewById(R.id.video_player_item_1);
        mPlayBtnView = findViewById(R.id.play_btn);
        nameTv = (TextView) findViewById(R.id.move_name);
        foreTv = (TextView) findViewById(R.id.move_fore_show);
        dirTv = (TextView) findViewById(R.id.move_dir);
        descTv = (TextView) findViewById(R.id.move_desc);
        catTv = (TextView) findViewById(R.id.move_cat);
        timeTv = (TextView) findViewById(R.id.move_time);
        view = (RelativeLayout) findViewById(R.id.move_title);
        Intent intent = getIntent();
        url = intent.getStringExtra(MOVIE_URL);
        nameTv.setText(intent.getStringExtra(MOVIE_NAME));
        foreTv.setText(intent.getStringExtra(MOVIE_FORE));
        dirTv.setText(getResources().getString(R.string.play_dec) + intent.getStringExtra(MOVIE_DIR));
        descTv.setText(intent.getStringExtra(MOVIE_DESC));
        catTv.setText(getResources().getString(R.string.play_kinds) + intent.getStringExtra(MOVIE_CAT));
        timeTv.setText(getResources().getString(R.string.play_in_china) + intent.getStringExtra(MOVIE_TIME));
        //网络判断
        internet();
        mPlayBtnView.setOnClickListener(this);
        mSuperVideoPlayer.setVideoPlayCallback(mVideoPlayCallback);

        //初始化标题栏
        initTitleBar();
        //手势退出
        gestureBack();
    }

    private void gestureBack() {
        gestureHelper = new GestureHelper(MoviePlayActivity.this);
        gestureHelper.setListener(new GestureHelper.OnFlingListener() {
            @Override
            public void OnFlingLeft() {
                MoviePlayActivity.this.finish();
                // 退出动画
                overridePendingTransition(R.anim.translate_exit_in, R.anim.translate_exit_out);
            }

            @Override
            public void OnFlingRight() {

            }
        });
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureHelper.onTouchEvent(event);
    }

    private void initTitleBar() {
        new BaseTitleBar(this).setImageLsftRes(R.mipmap.title_bar_back).setBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MoviePlayActivity.this.finish();
            }
        });
    }

    //判断是否连接网络
    private void internet() {
        mConnectivity = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        mTelephony = (TelephonyManager) this.getSystemService(TELEPHONY_SERVICE);
        //检查网络连接
        info = mConnectivity.getActiveNetworkInfo();

        if (info == null || !mConnectivity.getBackgroundDataSetting()) {
            Toast.makeText(this, getResources().getString(R.string.have_no_net), Toast.LENGTH_SHORT).show();
        } else {
            int netType = info.getType();
            int netSubtype = info.getSubtype();

            if (netType == ConnectivityManager.TYPE_WIFI) {  //WIFI
                Toast.makeText(this, getResources().getString(R.string.wifi), Toast.LENGTH_SHORT).show();
            } else if (netType == ConnectivityManager.TYPE_MOBILE) {   //MOBILE
                Toast.makeText(this, getResources().getString(R.string.data), Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * 播放器的回调函数
     */
    private SuperVideoPlayer.VideoPlayCallbackImpl mVideoPlayCallback = new SuperVideoPlayer.VideoPlayCallbackImpl() {
        /**
         * 播放器关闭按钮回调
         */
        @Override
        public void onCloseVideo() {
            mSuperVideoPlayer.close();//关闭VideoView
            mPlayBtnView.setVisibility(View.VISIBLE);
            mSuperVideoPlayer.setVisibility(View.GONE);
            view.setVisibility(View.VISIBLE);
            resetPageToPortrait();
        }

        /**
         * 播放器横竖屏切换回调
         */
        @Override
        public void onSwitchPageType() {
            if (getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                mSuperVideoPlayer.setPageType(MediaController.PageType.SHRINK);
                view.setVisibility(View.VISIBLE);

            } else {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                mSuperVideoPlayer.setPageType(MediaController.PageType.EXPAND);
                view.setVisibility(View.GONE);
            }
        }

        /**
         * 播放完成回调
         */
        @Override
        public void onPlayFinish() {

        }
    };

    @Override
    public void onClick(View view) {
        mPlayBtnView.setVisibility(View.GONE);
        mSuperVideoPlayer.setVisibility(View.VISIBLE);
        mSuperVideoPlayer.setAutoHideController(false);
        Uri uri = Uri.parse(url);
        //参数1:播放网址
        mSuperVideoPlayer.loadAndPlay(uri, 0);
    }

    /***
     * 旋转屏幕之后回调
     *
     * @param newConfig newConfig
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (null == mSuperVideoPlayer) return;
        /***
         * 根据屏幕方向重新设置播放器的大小
         */
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getWindow().getDecorView().invalidate();
            float height = DensityUtil.getWidthInPx(this);
            float width = DensityUtil.getHeightInPx(this);
            mSuperVideoPlayer.getLayoutParams().height = (int) width;
            mSuperVideoPlayer.getLayoutParams().width = (int) height;
        } else if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            final WindowManager.LayoutParams attrs = getWindow().getAttributes();
            attrs.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getWindow().setAttributes(attrs);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            float width = DensityUtil.getWidthInPx(this);
            float height = DensityUtil.dip2px(this, 200.f);
            mSuperVideoPlayer.getLayoutParams().height = (int) height;
            mSuperVideoPlayer.getLayoutParams().width = (int) width;
        }
    }

    /***
     * 恢复屏幕至竖屏
     */
    private void resetPageToPortrait() {
        if (getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            mSuperVideoPlayer.setPageType(MediaController.PageType.SHRINK);
        }
    }

}
