package com.yangyuning.maoyan.cinema;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yangyuning.maoyan.R;
import com.yangyuning.maoyan.base.AbsBaseAdapter;
import com.yangyuning.maoyan.mode.bean.CinmaBean;

/**
 * Created by dllo on 16/10/20.
 * 影院适配器
 *
 * @author 姜鑫
 */
public class CinemaAdapter extends AbsBaseAdapter<CinmaBean, CinemaAdapter.CinemaViewHolder> {


    public CinemaAdapter(Context context) {
        super(context);
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
    protected void onBindViewHolder(CinemaViewHolder cinemaViewHolder, CinmaBean itemData, int position) {
        cinemaViewHolder.textView.setText(itemData.getName());
//        cinemaViewHolder.imageView.setImageResource(R.mipmap.ic_launcher);

    }

    class CinemaViewHolder extends AbsBaseAdapter.BaseHolder {

        TextView textView;
//        ImageView imageView;

        public CinemaViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.item_name);
//            imageView = (ImageView) itemView.findViewById(R.id.cinema_listener);
        }
    }

}
