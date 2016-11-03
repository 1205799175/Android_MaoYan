package com.yangyuning.maoyan.mine.breakwindow;

import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.yangyuning.maoyan.R;
import com.yangyuning.maoyan.base.AbsBaseActivity;
import com.yangyuning.maoyan.base.BaseTitleBar;
import com.yangyuning.maoyan.utils.GestureHelper;
import com.zys.brokenview.BrokenTouchListener;
import com.zys.brokenview.BrokenView;

/**
 * Created by dllo on 16/10/31.
 *
 * @韩朝
 */
public class BreakWindowActivity extends AbsBaseActivity implements View.OnClickListener {
    private BrokenView mBrokenView;

    private RelativeLayout parentLayout;
    private ImageView imageView;
    private boolean hasAlpha;
    private BrokenTouchListener colorfulListener;
    private BrokenTouchListener whiteListener;
    private Paint whitePaint;
    private LinearLayout back;

    private GestureHelper gestureHelper;

    @Override
    protected int setLayout() {
        return R.layout.activity_breakwindow;
    }

    @Override
    protected void initView() {
        parentLayout = (RelativeLayout) findViewById(R.id.demo_parent);
        imageView = (ImageView) findViewById(R.id.demo_image);
        back = (LinearLayout) findViewById(R.id.breakwindow_back);
    }

    @Override
    protected void initDatas() {
        new BaseTitleBar(this).setImageLsftRes(R.mipmap.title_bar_back).setTitle(getResources().
                getString(R.string.breakwindow)).setBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BreakWindowActivity.this.finish();
            }
        });
        mBrokenView = BrokenView.add2Window(this);

        whitePaint = new Paint();
        whitePaint.setColor(0xffffffff);

        colorfulListener = new BrokenTouchListener.Builder(mBrokenView).
                build();
        whiteListener = new BrokenTouchListener.Builder(mBrokenView).
                setPaint(whitePaint).
                build();

        setOnTouchListener();
        back.setOnClickListener(this);

        initGestureBack();
    }

    private void initGestureBack() {
        gestureHelper = new GestureHelper(this);
        gestureHelper.setListener(new GestureHelper.OnFlingListener() {
            @Override
            public void OnFlingLeft() {
                finish();
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


    public void setOnTouchListener() {
        parentLayout.setOnTouchListener(colorfulListener);
        if (hasAlpha) {
            imageView.setOnTouchListener(whiteListener);
        } else {
            imageView.setOnTouchListener(colorfulListener);
        }
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
