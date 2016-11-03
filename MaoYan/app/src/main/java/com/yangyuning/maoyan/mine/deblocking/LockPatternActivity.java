package com.yangyuning.maoyan.mine.deblocking;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.yangyuning.maoyan.R;
import com.yangyuning.maoyan.base.BaseTitleBar;
import com.yangyuning.maoyan.utils.GestureHelper;

/**
 * Created by dllo on 16/10/31.
 * 滑动解锁
 * @author 姜鑫
 */
public class LockPatternActivity extends AppCompatActivity implements LockPatternView.OnPatternChangeListener{

    private TextView mLockPatternHint;
    private LockPatternView mLockPatternView;
    private String password = "14789";

    private GestureHelper gestureHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock_pattern);

        mLockPatternHint = (TextView) findViewById(R.id.lock_pattern_hint);
        mLockPatternView = (LockPatternView) findViewById(R.id.lock_pattern_view);
        mLockPatternView.setOnPatternChangeListener(this);
        initTitleBar();
    }

    private void initTitleBar() {
        initTitlebar();
        initGestureback();
    }

    private void initGestureback() {
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

    private void initTitlebar() {
        new BaseTitleBar(this).setImageLsftRes(R.mipmap.title_bar_back).setTitle(getResources().getString(R.string.deblocking_gesture)).setBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LockPatternActivity.this.finish();
            }
        });
    }

    @Override
    public void onPatternChange(String patternPassword) {
        if (patternPassword == null) {
            mLockPatternHint.setText("至少5个点");
        } else {
//            mLockPatternHint.setText(patternPassword);
            if (patternPassword.equals(password)){
                Intent intent = new Intent(LockPatternActivity.this,WalletAvtivity.class);
                startActivity(intent);
                LockPatternActivity.this.finish();
            } else {
                Toast.makeText(this, "密码错误,请重新输入!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onPatternStarted(boolean isStarted) {
        if (isStarted) {
            mLockPatternHint.setText("请绘制图案");
        }
    }
}

