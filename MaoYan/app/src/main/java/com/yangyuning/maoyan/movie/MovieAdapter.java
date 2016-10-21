package com.yangyuning.maoyan.movie;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.yangyuning.maoyan.R;
import com.yangyuning.maoyan.base.AbsBaseAdapter;

/**
 * Created by dllo on 16/10/20.
 */
public class MovieAdapter extends AbsBaseAdapter<String, MovieAdapter.MovieViewHolder>{

    public MovieAdapter(Context context) {
        super(context);
    }

    @Override
    protected int setItemLayout() {
        return R.layout.item_movie_lv;
    }

    @Override
    protected MovieViewHolder onCreateViewHolder(View convertView) {
        return new MovieViewHolder(convertView);
    }

    @Override
    protected void onBindViewHolder(MovieViewHolder cinemaViewHolder, String itemData, int position) {
        cinemaViewHolder.textView.setText(itemData);

    }

    public class MovieViewHolder extends AbsBaseAdapter.BaseHolder {

        TextView textView;

        public MovieViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.movie_film_name);
        }
    }
}