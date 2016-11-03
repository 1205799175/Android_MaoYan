package com.yangyuning.maoyan.mine.compass;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import com.yangyuning.maoyan.R;
import com.yangyuning.maoyan.base.AbsBaseActivity;
import com.yangyuning.maoyan.base.BaseTitleBar;
import com.yangyuning.maoyan.utils.GestureHelper;

/**
 * Created by dllo on 16/10/31.
 * 指南针
 *
 * @author 姜鑫
 */
public class CompassActivity extends AbsBaseActivity implements SensorEventListener {
    private ImageView znzImage;
    //记录指南针图片转过的角度
    private float currentDegree = 0f;
    //定义Sensor管理器
    private SensorManager manager;

    private GestureHelper gestureHelper;

    @Override
    protected int setLayout() {
        return R.layout.activity_compass;
    }

    @Override
    protected void initView() {
        znzImage = byView(R.id.compass_iv);
        //获取传感器管理服务
        manager = (SensorManager) getSystemService(SENSOR_SERVICE);
    }

    @Override
    protected void initDatas() {
        initTitleBar();
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

    private void initTitleBar() {
        new BaseTitleBar(this).setImageLsftRes(R.mipmap.title_bar_back).setTitle(getResources().getString(R.string.mine_compass)).setBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CompassActivity.this.finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        //为系统的传感器方向注册监听
        manager.registerListener(this, manager.getDefaultSensor(Sensor.TYPE_ORIENTATION), SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onPause() {
        //取消注册
        manager.unregisterListener(this);
        super.onPause();
    }

    @Override
    protected void onStop() {
        manager.unregisterListener(this);
        super.onStop();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        //获取触发event的传感器
        int sensorType = event.sensor.getType();
        switch (sensorType) {
            case Sensor.TYPE_ORIENTATION:
                //获取绕Z轴转过的角度
                float degree = event.values[0];
                //创建旋转动画(反向转过degree度)
                RotateAnimation ra = new RotateAnimation(currentDegree, -degree, Animation.RELATIVE_TO_SELF, 0.5f,
                        Animation.RELATIVE_TO_SELF, 0.5f);
                //设置动画持续的时间
                ra.setDuration(200);
                //运行动画
                znzImage.startAnimation(ra);
                currentDegree = -degree;
                break;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
