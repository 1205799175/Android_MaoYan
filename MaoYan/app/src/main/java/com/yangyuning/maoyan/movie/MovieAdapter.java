package com.yangyuning.maoyan.movie;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yangyuning.maoyan.R;
import com.yangyuning.maoyan.base.AbsBaseAdapter;
import com.yangyuning.maoyan.mode.bean.MovieBean;

/**
 * Created by dllo on 16/10/20.
 * movieFragment LV适配器
 */
public class MovieAdapter extends AbsBaseAdapter<MovieBean.DataBean.HotBean, MovieAdapter.MovieViewHolder>{

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
    protected void onBindViewHolder(MovieViewHolder movieViewHolder, MovieBean.DataBean.HotBean itemData, int position) {
        if (itemData.getVer().substring(0, 2).equals("2D")){
            movieViewHolder.filmState.setImageResource(R.mipmap.movie_item_two_d);
        }
        movieViewHolder.filmName.setText(itemData.getNm());
        movieViewHolder.filmAudPoint.setText(itemData.getMk() + "");
        movieViewHolder.filmPrePoint.setText(itemData.getProScore() + "");
        movieViewHolder.filmDetail.setText(itemData.getScm());
        movieViewHolder.filmCount.setText(itemData.getShowInfo());
//        Picasso.with(context).load(itemData.getImg()).into(movieViewHolder.filmIcon);
    }

    public class MovieViewHolder extends AbsBaseAdapter.BaseHolder {

        TextView filmName, filmAudPoint, filmPrePoint, filmDetail, filmCount, filmAud, filmPro;
        View filmView;
        ImageView filmIcon, filmState;

        public MovieViewHolder(View itemView) {
            super(itemView);
            filmName = (TextView) itemView.findViewById(R.id.movie_film_name);
            filmAudPoint = (TextView) itemView.findViewById(R.id.movie_film_audience_point);
            filmPrePoint = (TextView) itemView.findViewById(R.id.movie_film_professional_point);
            filmDetail = (TextView) itemView.findViewById(R.id.movie_film_detail);
            filmCount = (TextView) itemView.findViewById(R.id.movie_film_count);
            filmIcon = (ImageView) itemView.findViewById(R.id.movie_film_icon);
            filmAud = (TextView) itemView.findViewById(R.id.movie_film_audience);
            filmPro = (TextView) itemView.findViewById(R.id.movie_film_professional);
            filmView = (View) itemView.findViewById(R.id.movie_film_view);
            filmState = (ImageView) itemView.findViewById(R.id.movie_film_state);
        }
    }
}