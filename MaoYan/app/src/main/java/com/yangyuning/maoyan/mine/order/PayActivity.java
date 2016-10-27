package com.yangyuning.maoyan.mine.order;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

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
    }

    @Override
    protected void initDatas() {
        //初始化标题栏
        initTitlebar();
        datas = new ArrayList<>();
        initListView();

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
}
