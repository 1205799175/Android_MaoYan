package com.yangyuning.maoyan.cinema;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.yangyuning.maoyan.R;
import com.yangyuning.maoyan.activity.MainActivity;
import com.yangyuning.maoyan.base.AbsBaseFragment;
import com.yangyuning.maoyan.base.BaseTitleBar;
import com.yangyuning.maoyan.cinema.map.MapActivity;
import com.yangyuning.maoyan.mode.bean.CinmaBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/10/18.
 * 影院Fragment
 *
 * @author 杨宇宁 10.18
 */
public class CinemaFragment extends AbsBaseFragment {

    private TextView areaTv, titleTv;
    private ImageView areaIv, searchIv;
    private ListView listView;
    private CinemaAdapter cinemaAdapter;
    private List<CinmaBean> datas;
    private TextView textView;



    private String url = "http://api.meituan.com/mmcs/cinema/v1/select/cinemas.json?" +
            "cityId=65&channelId=1&clientType=android&offset=0&limit=10&userid=-1&__vhost=api.maoyan.com&utm_campaign=AmovieBmovieCD-1&movieBundleVersion=7401&utm_source=360tjy-dy&utm_medium=android&utm_term=7.4.0&utm_content=000000000000000&ci=65&net=255&dModel=Google%20Nexus%205%20-%205.1.0%20-%20API%2022%20-%201080x1920" +
            "&uuid=2C2C0ECD557F366849954BEF88D0017A03C78C77F2FA655C70E142ED41438C39&lat=0.0&lng=0.0&refer=%2FWelcome&__skck=6a375bce8c66a0dc293860dfa83833ef&__skts=1477104759676&__skua=32bcf146c756ecefe7535b95816908e3&__skno=3d62a81a-0839-4b3b-ab33-3ea64ee2f1be&__skcy=MI6gwa8jXEQkbmne9Px4aSKRVZs%3D";

    public static CinemaFragment newInstance() {
        Bundle args = new Bundle();
        CinemaFragment fragment = new CinemaFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_cinema;
    }

    @Override
    protected void initView() {
        areaTv = byView(R.id.title_bar_tv_left);
        titleTv = byView(R.id.title_bar_tv);
        areaIv = byView(R.id.title_bar_iv_share);
        searchIv = byView(R.id.title_bar_iv_collect);
        listView = byView(R.id.cinema_list_view);
        textView = byView(R.id.map_tv);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MapActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    protected void initDatas() {
        initTitleBar();

        cinemaAdapter = new CinemaAdapter(context);
        datas = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            datas.add(new CinmaBean("中影明生国际影城(华太财富广场店)" + i));
        }
        cinemaAdapter.setDatas(datas);
        listView.setAdapter(cinemaAdapter);
    }

    private void initTitleBar() {
        areaTv.setText(R.string.dalian);
        titleTv.setText(R.string.cinema);
        searchIv.setImageResource(R.mipmap.title_bar_search);
        areaIv.setImageResource(R.mipmap.title_bar_erea);
    }
}
