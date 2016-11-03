package com.yangyuning.maoyan.mine.closelight;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yangyuning.maoyan.R;
import com.yangyuning.maoyan.base.AbsBaseActivity;
import com.yangyuning.maoyan.base.BaseTitleBar;
import com.yangyuning.maoyan.mine.dialog.DialogOnClickListener;
import com.yangyuning.maoyan.mine.dialog.NormalAlertDialog;
import com.yangyuning.maoyan.utils.GestureHelper;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by dllo on 16/10/31.
 * 关灯游戏
 *
 * @杨宇宁
 */
public class CloseLightActivity extends AbsBaseActivity {

    private static final String KEY_LEVEL = "level";
    //定义存button的arraylist
    private ArrayList<Button> arrayList;
    private int count;
    private NormalAlertDialog dialog;
    private TextView levelTv, countTv;
    private Handler handler;
    private int level = 1;  //等级
    private boolean isCreating = true;
    private int stone;//不能动的点

    private GestureHelper gestureHelper;

    @Override
    protected int setLayout() {
        return R.layout.activity_close_light;
    }

    @Override
    protected void initView() {
        arrayList = new ArrayList<>();
        arrayList.add((Button) findViewById(R.id.button_1));
        arrayList.add((Button) findViewById(R.id.button_2));
        arrayList.add((Button) findViewById(R.id.button_3));
        arrayList.add((Button) findViewById(R.id.button_4));
        arrayList.add((Button) findViewById(R.id.button_5));
        arrayList.add((Button) findViewById(R.id.button_6));
        arrayList.add((Button) findViewById(R.id.button_7));
        arrayList.add((Button) findViewById(R.id.button_8));
        arrayList.add((Button) findViewById(R.id.button_9));
        arrayList.add((Button) findViewById(R.id.button_10));
        arrayList.add((Button) findViewById(R.id.button_11));
        arrayList.add((Button) findViewById(R.id.button_12));
        arrayList.add((Button) findViewById(R.id.button_13));
        arrayList.add((Button) findViewById(R.id.button_14));
        arrayList.add((Button) findViewById(R.id.button_15));
        arrayList.add((Button) findViewById(R.id.button_16));
        arrayList.add((Button) findViewById(R.id.button_17));
        arrayList.add((Button) findViewById(R.id.button_18));
        arrayList.add((Button) findViewById(R.id.button_19));
        arrayList.add((Button) findViewById(R.id.button_20));
        arrayList.add((Button) findViewById(R.id.button_21));
        arrayList.add((Button) findViewById(R.id.button_22));
        arrayList.add((Button) findViewById(R.id.button_23));
        arrayList.add((Button) findViewById(R.id.button_24));
        arrayList.add((Button) findViewById(R.id.button_25));
        levelTv = byView(R.id.close_light_level);
        countTv = byView(R.id.close_light_count);
    }

    @Override
    protected void initDatas() {

        initTitleBar();
        Intent intent = getIntent();
        level = intent.getIntExtra(KEY_LEVEL, 1);
        //随机找出一个按钮设置为无点击事件, 颜色设置为黑色(增加游戏难度)
        stone = new Random().nextInt(24) + 1;
        arrayList.get(stone).setBackgroundColor(Color.BLACK);
        arrayList.get(stone).setEnabled(false);
        for (int i = 0; i < level; i++) {
            int num = new Random().nextInt(24) + 1;
            if (num == stone) {

            } else {
                buttonClick(arrayList.get(num));
            }
        }
        initTv();
        initNormalDialog();
        isCreating = false;

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

    public void buttonClick(View v) {
        //获取当前背景的颜色
        ColorDrawable colorDrawable = (ColorDrawable) v.getBackground();
        if (colorDrawable.getColor() == Color.parseColor("#99cc00")) {
            v.setBackgroundColor(Color.RED);
            count++;
        } else {
            v.setBackgroundColor(Color.parseColor("#99cc00"));
            count--;
        }
        int index = Integer.parseInt((String) v.getTag());

        //上
        if (index > 5) {
            Button upBtu = arrayList.get(index - 6);
            if (index - 6 != stone) {
                ColorDrawable upColorDrawable = (ColorDrawable) upBtu.getBackground();
                if (upColorDrawable.getColor() == Color.parseColor("#99cc00")) {
                    upBtu.setBackgroundColor(Color.RED);
                    count++;
                } else {
                    upBtu.setBackgroundColor(Color.parseColor("#99cc00"));
                    count--;
                }
            }
        }
        //下
        if (index < 21) {
            Button upBtu = arrayList.get(index + 4);
            if (index + 4 != stone) {
                ColorDrawable upColorDrawable = (ColorDrawable) upBtu.getBackground();
                if (upColorDrawable.getColor() == Color.parseColor("#99cc00")) {
                    upBtu.setBackgroundColor(Color.RED);
                    count++;
                } else {
                    upBtu.setBackgroundColor(Color.parseColor("#99cc00"));
                    count--;
                }
            }
        }
        //右
        if (index % 5 != 0) {
            Button upBtu = arrayList.get(index);
            if (index != stone) {
                ColorDrawable upColorDrawable = (ColorDrawable) upBtu.getBackground();
                if (upColorDrawable.getColor() == Color.parseColor("#99cc00")) {
                    upBtu.setBackgroundColor(Color.RED);
                    count++;
                } else {
                    upBtu.setBackgroundColor(Color.parseColor("#99cc00"));
                    count--;
                }
            }
        }
        //左
        if (index % 5 != 1) {
            Button upBtu = arrayList.get(index - 2);
            if (index - 2 != stone) {
                ColorDrawable upColorDrawable = (ColorDrawable) upBtu.getBackground();
                if (upColorDrawable.getColor() == Color.parseColor("#99cc00")) {
                    upBtu.setBackgroundColor(Color.RED);
                    count++;
                } else {
                    upBtu.setBackgroundColor(Color.parseColor("#99cc00"));
                    count--;
                }
            }
        }
        if (!isCreating && count == 0) {
            dialog.show();
        }
    }

    private void initTv() {
        levelTv.setText("等级:" + level);
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                if (msg.what == CountThread.COUNT) {
                    int count = (int) msg.obj;
                    countTv.setText("时间:" + count + "S");
                }
                return false;
            }
        });
        new CountThread(handler).start();
    }

    private void initTitleBar() {
        new BaseTitleBar(this).setImageLsftRes(R.mipmap.title_bar_back).setTitle(getResources().getString(R.string.close_light_game)).setBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CloseLightActivity.this.finish();
            }
        });
    }

    private void initNormalDialog() {
        int nextLevel = level + 1;
        dialog = new NormalAlertDialog.Builder(this)
                .setHeight(0.23f)
                .setWidth(0.65f)
                .setTitleVisible(true)
                .setTitleText("通过第" + level + "关")
                .setTitleTextColor(R.color.black_light)
                .setContentText("是否进入第" + nextLevel + "关?")
                .setContentTextColor(R.color.black_light)
                .setLeftButtonText("取消")
                .setLeftButtonTextColor(R.color.gray)
                .setRightButtonText("确定")
                .setRightButtonTextColor(R.color.black_light)
                .setOnclickListener(new DialogOnClickListener() {
                    @Override
                    public void clickLeftButton(View view) {
                        CloseLightActivity.this.finish();
                    }

                    @Override
                    public void clickRightButton(View view) {
                        dialog.dismiss();
                        Intent intent = new Intent(CloseLightActivity.this, CloseLightActivity.class);
                        intent.putExtra(KEY_LEVEL, level + 1);
                        startActivity(intent);
                        CloseLightActivity.this.finish();
                    }
                }).build();
    }

}
