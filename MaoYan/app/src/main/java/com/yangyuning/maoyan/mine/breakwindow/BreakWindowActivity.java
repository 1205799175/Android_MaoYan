package com.yangyuning.maoyan.mine.breakwindow;

import android.graphics.Paint;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.yangyuning.maoyan.R;
import com.yangyuning.maoyan.base.AbsBaseActivity;
import com.zys.brokenview.BrokenTouchListener;
import com.zys.brokenview.BrokenView;

/**
 * Created by dllo on 16/10/31.
 */
public class BreakWindowActivity extends AbsBaseActivity {
    private BrokenView mBrokenView;

    private RelativeLayout parentLayout;
    private ImageView imageView;
    private boolean hasAlpha;
    private BrokenTouchListener colorfulListener;
    private BrokenTouchListener whiteListener;
    private Paint whitePaint;

    @Override
    protected int setLayout() {
        return R.layout.activity_breakwindow;
    }

    @Override
    protected void initView() {
        parentLayout = (RelativeLayout) findViewById(R.id.demo_parent);
        imageView = (ImageView) findViewById(R.id.demo_image);
    }

    @Override
    protected void initDatas() {
        mBrokenView = BrokenView.add2Window(this);

        whitePaint = new Paint();
        whitePaint.setColor(0xffffffff);

        colorfulListener = new BrokenTouchListener.Builder(mBrokenView).
                build();
        whiteListener = new BrokenTouchListener.Builder(mBrokenView).
                setPaint(whitePaint).
                build();

        setOnTouchListener();
    }

    public void setOnTouchListener() {
        parentLayout.setOnTouchListener(colorfulListener);
        if (hasAlpha) {
            imageView.setOnTouchListener(whiteListener);
        } else {
            imageView.setOnTouchListener(colorfulListener);
        }
    }

}
