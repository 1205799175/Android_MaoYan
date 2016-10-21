package com.yangyuning.maoyan.cinema.map;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ToggleButton;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.yangyuning.maoyan.R;
/**
 * Created by dllo on 16/10/20.
 * 地图
 * @author 姜鑫
 */
public class MapActivity extends AppCompatActivity {
    private MapView mapView;
    private AMap aMap;
    private ToggleButton button;
    private boolean isCheck = false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        mapView = (MapView) findViewById(R.id.map);
        button = (ToggleButton) findViewById(R.id.toggle);
        mapView.onCreate(savedInstanceState);
        init();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isCheck){
                    aMap.setMapType(AMap.MAP_TYPE_NORMAL);
                }else {
                    aMap.setMapType(AMap.MAP_TYPE_NAVI);
                }
            }
        });
    }

    private void init() {
        if (aMap == null){
            aMap = mapView.getMap();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }
}
