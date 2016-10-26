package com.yangyuning.maoyan.mine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.yangyuning.maoyan.R;
import com.yangyuning.maoyan.base.AbsBaseFragment;
import com.yangyuning.maoyan.mine.order.PayActivity;

/**
 * Created by dllo on 16/10/18.
 * 我的Fragment
 * @author 杨宇宁 10.18
 */
public class MineFragment extends AbsBaseFragment implements View.OnClickListener {

    private LinearLayout pay;

    public static MineFragment newInstance() {
        Bundle args = new Bundle();
        MineFragment fragment = new MineFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView() {
        pay = byView(R.id.order_pay);

        pay.setOnClickListener(this);
    }

    @Override
    protected void initDatas() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.order_pay:
                context.startActivity(new Intent(context, PayActivity.class));
                break;
        }
    }
}
