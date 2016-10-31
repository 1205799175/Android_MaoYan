package com.yangyuning.maoyan.mine.deblocking;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.yangyuning.maoyan.R;

/**
 * Created by dllo on 16/10/31.
 * 滑动解锁
 * @author 姜鑫
 */
public class LockPatternActivity extends AppCompatActivity implements LockPatternView.OnPatternChangeListener{

    private TextView mLockPatternHint;
    private LockPatternView mLockPatternView;
    private String A = "14789";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock_pattern);

        mLockPatternHint = (TextView) findViewById(R.id.lock_pattern_hint);
        mLockPatternView = (LockPatternView) findViewById(R.id.lock_pattern_view);
        mLockPatternView.setOnPatternChangeListener(this);

    }

    @Override
    public void onPatternChange(String patternPassword) {
        if (patternPassword == null) {
            mLockPatternHint.setText("至少5个点");
        } else {
//            mLockPatternHint.setText(patternPassword);
            if (patternPassword.equals(A)){
                Intent intent = new Intent(LockPatternActivity.this,WalletAvtivity.class);
                startActivity(intent);
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

