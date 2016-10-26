package com.yangyuning.maoyan.mine.order;

import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.yangyuning.maoyan.R;
import com.yangyuning.maoyan.base.AbsBaseActivity;
import com.yangyuning.maoyan.base.BaseTitleBar;
import com.yangyuning.maoyan.mode.bean.PayBean;
import com.yangyuning.maoyan.mode.db.LiteOrmInstance;
import com.yangyuning.maoyan.utils.GestureHelper;
import com.yangyuning.maoyan.views.SwipeListView;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by dllo on 16/10/26.
 * 我的界面-我的订单-待付款
 */
public class PayActivity extends AbsBaseActivity {

    private PayLvAdapter payAdapter;
    private List<PayBean> datas;
    private SwipeListView listView;
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
        gestuerBack();
    }

    private void gestuerBack() {
        gestureHelper = new GestureHelper(this);
        gestureHelper.setListener(new GestureHelper.OnFlingListener() {
            @Override
            public void OnFlingLeft() {
                Toast.makeText(PayActivity.this, "a", Toast.LENGTH_SHORT).show();
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
        payAdapter = new PayLvAdapter(this, listView.getRightViewWidth());
        //获取数据库的数据
        datas = LiteOrmInstance.getLiteOrmInstance().queryAll();
        //给适配器设置数据
        payAdapter.setDatas(datas);
        listView.setAdapter(payAdapter);
        //侧滑删除
        payAdapter.setmListener(new PayLvAdapter.IOnItemRightClickListener() {
            @Override
            public void onRightClick(View v, int position) {
                datas = LiteOrmInstance.getLiteOrmInstance().queryAll();
                if (datas != null) {
                    LiteOrmInstance.getLiteOrmInstance().deleteByTitle(datas.get(position).getTitle());
                    payAdapter.setDatas(LiteOrmInstance.getLiteOrmInstance().queryAll());
                }
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
}
