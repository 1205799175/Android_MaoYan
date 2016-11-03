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

import cn.sharesdk.onekeyshare.OnekeyShare;

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
    private Intent intent;

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
        intent = getIntent();
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
        new BaseTitleBar(this).setImageLsftRes(R.mipmap.title_bar_back).setImageShare(R.mipmap.title_share).setBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MoviePlayActivity.this.finish();
            }
        }).setLeftListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnekeyShare oks = new OnekeyShare();
                //关闭sso授权
                oks.disableSSOWhenAuthorize();

                // 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
                //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
                // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
                oks.setTitle(intent.getStringExtra(MOVIE_NAME));
                // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
                oks.setTitleUrl(intent.getStringExtra(MOVIE_NAME));
                // text是分享文本，所有平台都需要这个字段
                oks.setText(intent.getStringExtra(MOVIE_DESC));
                //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
                oks.setImageUrl("http://img7.doubanio.com/view/photo/photo/public/p865226824.jpg");
                // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
                //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
                // url仅在微信（包括好友和朋友圈）中使用
                oks.setUrl("http://sharesdk.cn");
                // comment是我对这条分享的评论，仅在人人网和QQ空间使用
                oks.setComment("我是测试评论文本");
                // site是分享此内容的网站名称，仅在QQ空间使用
                oks.setSite("ShareSDK");
                // siteUrl是分享此内容的网站地址，仅在QQ空间使用
                oks.setSiteUrl(url);

                // 启动分享GUI
                oks.show(MoviePlayActivity.this);

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
