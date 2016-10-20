package com.yangyuning.maoyan.movie;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yangyuning.maoyan.R;
import com.yangyuning.maoyan.base.AbsBaseFragment;
import com.yangyuning.maoyan.movie.area.AreaActivity;
import com.yangyuning.maoyan.movie.qrcode.QRCodeActivity;
import com.yangyuning.maoyan.movie.zxing.activity.CaptureActivity;

/**
 * Created by dllo on 16/10/18.
 * 电影Fragment
 * @author 杨宇宁 10.18
 */
public class MovieFragment extends AbsBaseFragment {

    private TextView areatTv, titleTv;
    private ImageView qRCode; //二维码
    private static final int PHOTO_PIC = 1;

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
    }

    @Override
    protected void initDatas() {
        intiTitleBar();
        initListener();
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
        if(resultCode == -1){
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
}
