package com.yangyuning.maoyan.cinema;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.yangyuning.maoyan.R;
import com.yangyuning.maoyan.base.AbsBaseAdapter;
import com.yangyuning.maoyan.mode.bean.CinemaBean;

/**
 * Created by dllo on 16/10/20.
 * 影院适配器
 *
 * @author 姜鑫
 */
public class CinemaAdapter extends AbsBaseAdapter<CinemaBean, CinemaAdapter.CinemaViewHolder> {

    public CinemaAdapter(Context context) {
        super(context);
    }

    private IStarChangeListener starChangeListener;

    public void setStarChangeListener(IStarChangeListener starChangeListener) {
        this.starChangeListener = starChangeListener;
    }

    @Override
    protected int setItemLayout() {
        return R.layout.item_cinema;
    }

    @Override
    protected CinemaViewHolder onCreateViewHolder(View convertView) {
        return new CinemaViewHolder(convertView);
    }

    @Override
    protected void onBindViewHolder(CinemaViewHolder cinemaViewHolder, CinemaBean itemData, int position) {
        cinemaViewHolder.nameTv.setText(itemData.getName());
        cinemaViewHolder.privceTv.setText(itemData.getPrice());
        cinemaViewHolder.addressTv.setText(itemData.getAddr());
        if (position > 5){
            starChangeListener.onShow();
        } else {
            starChangeListener.onDismiss();
        }
    }

    class CinemaViewHolder extends AbsBaseAdapter.BaseHolder {

        TextView nameTv, privceTv, addressTv;
        public CinemaViewHolder(View itemView) {
            super(itemView);
            nameTv = (TextView) itemView.findViewById(R.id.item_name);
            privceTv = (TextView) itemView.findViewById(R.id.item_price);
            addressTv = (TextView) itemView.findViewById(R.id.item_address);
        }
    }

}
