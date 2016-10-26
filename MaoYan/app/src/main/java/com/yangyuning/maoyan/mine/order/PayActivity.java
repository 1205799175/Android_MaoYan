package com.yangyuning.maoyan.mine.order;

import android.view.View;
import android.widget.ListView;

import com.yangyuning.maoyan.R;
import com.yangyuning.maoyan.base.AbsBaseActivity;
import com.yangyuning.maoyan.base.BaseTitleBar;
import com.yangyuning.maoyan.mode.bean.PayBean;
import com.yangyuning.maoyan.mode.db.LiteOrmInstance;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by dllo on 16/10/26.
 * 我的界面-我的订单-待付款
 */
public class PayActivity extends AbsBaseActivity {

    private PayLvAdapter payAdapter;
    private List<PayBean> datas;
    private ListView listView;

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
        payAdapter = new PayLvAdapter(this);
        datas = LiteOrmInstance.getLiteOrmInstance().queryAll();
        payAdapter.setDatas(datas);
        listView.setAdapter(payAdapter);
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
