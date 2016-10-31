package com.yangyuning.maoyan.mine.deblocking;

import android.widget.TextView;

import com.yangyuning.maoyan.R;
import com.yangyuning.maoyan.base.AbsBaseActivity;

/**
 * Created by dllo on 16/10/31.
 */
public class WalletAvtivity extends AbsBaseActivity{
    private TextView textView;

    @Override
    protected int setLayout() {
        return R.layout.activity_wallet;
    }

    @Override
    protected void initView() {
      textView = byView(R.id.wallet_tv);
    }

    @Override
    protected void initDatas() {

    }
}
