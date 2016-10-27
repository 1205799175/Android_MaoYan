package com.yangyuning.maoyan.mine.order;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.jude.swipbackhelper.SwipeBackHelper;
import com.yangyuning.maoyan.R;
import com.yangyuning.maoyan.base.AbsBaseActivity;
import com.yangyuning.maoyan.base.BaseTitleBar;
import com.yangyuning.maoyan.mine.order.swipe.SwipeMenu;
import com.yangyuning.maoyan.mine.order.swipe.SwipeMenuCreator;
import com.yangyuning.maoyan.mine.order.swipe.SwipeMenuItem;
import com.yangyuning.maoyan.mine.order.swipe.SwipeMenuListView;
import com.yangyuning.maoyan.mode.bean.PayBean;
import com.yangyuning.maoyan.mode.db.LiteOrmInstance;
import com.yangyuning.maoyan.utils.GestureHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/10/26.
 * 我的界面-我的订单-待付款
 */
public class PayActivity extends AbsBaseActivity {

    private PayLvAdapter payAdapter;
    private List<PayBean> datas;
    private SwipeMenuListView listView;
    private GestureHelper gestureHelper;

    @Override
    protected int setLayout() {
        return R.layout.activity_pay;
    }

    @Override
    protected void initView() {
        listView = byView(R.id.mine_order_pay);
        //解决gesture和ListView的滑动冲突
        swipeBack();
    }

    @Override
    protected void initDatas() {
        //初始化标题栏
        initTitlebar();
        datas = new ArrayList<>();
        initListView();
        //手势退出
        gestureBack();
    }

    private void swipeBack() {
        SwipeBackHelper.onCreate(this); // 手势相关
        SwipeBackHelper.getCurrentPage(this)//获取当前页面
                .setSwipeBackEnable(true)//设置是否可滑动
                .setSwipeEdge(200)//可滑动的范围。px。200表示为左边200px的屏幕
                .setSwipeEdgePercent(0.2f)//可滑动的范围。百分比。0.2表示为左边20%的屏幕
                .setSwipeSensitivity(0.5f)//对横向滑动手势的敏感程度。0为迟钝 1为敏感
                .setClosePercent(0.8f)//触发关闭Activity百分比
                .setSwipeRelateEnable(true)//是否与下一级activity联动(微信效果)。默认关
                .setSwipeRelateOffset(500)//activity联动时的偏移量。默认500px。
                .setDisallowInterceptTouchEvent(false);//不抢占事件，默认关（事件将先由子View处理再由滑动关闭处理）
    }

    private void gestureBack() {
        //手势滑动退出
        gestureHelper = new GestureHelper(this);
        gestureHelper.setListener(new GestureHelper.OnFlingListener() {
            @Override
            public void OnFlingLeft() {
                PayActivity.this.finish();
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

    private void initListView() {
        payAdapter = new PayLvAdapter(this);
        //获取数据库的数据
        datas = LiteOrmInstance.getLiteOrmInstance().queryAll();
        //给适配器设置数据
        payAdapter.setDatas(datas);
        listView.setAdapter(payAdapter);

        // step 1. create a MenuCreator
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9, 0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(dp2px(90));
                // set a icon
                deleteItem.setIcon(R.mipmap.delete);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };

        // set creator
        listView.setMenuCreator(creator);

        //侧滑删除
        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                datas = LiteOrmInstance.getLiteOrmInstance().queryAll();
                LiteOrmInstance.getLiteOrmInstance().deleteByTitle(datas.get(position).getTitle());
                payAdapter.setDatas(LiteOrmInstance.getLiteOrmInstance().queryAll());
                return false;
            }
        });

    }

    private void initTitlebar() {
        new BaseTitleBar(this).setImageLsftRes(R.mipmap.title_bar_back).setBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PayActivity.this.finish();
            }
        });
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        SwipeBackHelper.onPostCreate(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SwipeBackHelper.onDestroy(this);
    }
}
