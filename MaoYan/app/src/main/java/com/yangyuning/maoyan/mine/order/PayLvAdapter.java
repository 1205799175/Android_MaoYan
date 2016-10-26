package com.yangyuning.maoyan.mine.order;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yangyuning.maoyan.R;
import com.yangyuning.maoyan.base.AbsBaseAdapter;
import com.yangyuning.maoyan.mode.bean.PayBean;

/**
 * Created by dllo on 16/10/26.
 * 我的-待付款lv适配器
 *
 * @author 杨宇宁
 */
public class PayLvAdapter extends AbsBaseAdapter<PayBean, PayLvAdapter.PayViewHolder> {

    private int mRightWidth = 0;
    /**
     * 单击事件监听器
     */
    private IOnItemRightClickListener mListener;

    public PayLvAdapter(Context context, int rightWidth) {
        super(context);
        mRightWidth = rightWidth;
    }

    public void setmListener(IOnItemRightClickListener mListener) {
        this.mListener = mListener;
    }

    @Override
    protected int setItemLayout() {
        return R.layout.item_mine_pay_lv;
    }

    @Override
    protected PayViewHolder onCreateViewHolder(View convertView) {
        return new PayViewHolder(convertView);
    }

    @Override
    protected void onBindViewHolder(PayViewHolder payViewHolder, final PayBean itemData, final int position) {
        RadioGroup.LayoutParams lp1 = new RadioGroup.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        payViewHolder.itemLeft.setLayoutParams(lp1);
        RadioGroup.LayoutParams lp2 = new RadioGroup.LayoutParams(mRightWidth, RelativeLayout.LayoutParams.MATCH_PARENT);
        payViewHolder.itemRight.setLayoutParams(lp2);
        payViewHolder.titleTv.setText(itemData.getTitle());
        payViewHolder.privceTv.setText(itemData.getPrice() + context.getResources().getString(R.string.yuan));
        payViewHolder.itemRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onRightClick(v, position);
                }
            }
        });
        //点击付款
        payViewHolder.payIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NormalActivity.class);
                intent.putExtra(NormalActivity.KEY_PAY_GOODS, itemData.getTitle());
                intent.putExtra(NormalActivity.KEY_PAY_PRICE, itemData.getPrice());
                context.startActivity(intent);
            }
        });
    }

    class PayViewHolder extends AbsBaseAdapter.BaseHolder {
        TextView titleTv, privceTv;
        ImageView payIv;
        RelativeLayout itemLeft,itemRight;

        public PayViewHolder(View itemView) {
            super(itemView);
            titleTv = (TextView) itemView.findViewById(R.id.item_pay_title);
            privceTv = (TextView) itemView.findViewById(R.id.item_pay_price);
            payIv = (ImageView) itemView.findViewById(R.id.item_pay_pay_iv);
            itemLeft = (RelativeLayout) itemView.findViewById(R.id.item_left);
            itemRight = (RelativeLayout) itemView.findViewById(R.id.item_right);
        }
    }

    public interface IOnItemRightClickListener {
        void onRightClick(View v, int position);
    }
}
