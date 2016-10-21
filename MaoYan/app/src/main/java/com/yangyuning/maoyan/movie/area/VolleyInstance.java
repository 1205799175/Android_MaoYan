package com.yangyuning.maoyan.movie.area;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.yangyuning.maoyan.app.MaoYanApp;

/**
 * Created by dllo on 16/9/10.
 * Volley使用单例类
 */
public class VolleyInstance {

    private static VolleyInstance instance;
    //定义请求队列
    private RequestQueue requestQueue;

    private VolleyInstance(){
        requestQueue = Volley.newRequestQueue(MaoYanApp.getContext());
    }

    public static VolleyInstance getInstance() {
        if (instance == null){
            synchronized (VolleyInstance.class){
                if (instance == null){
                    instance = new VolleyInstance();
                }
            }
        }
        return instance;
    }

    public void startResult(String url, final VolleyResult result){
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                result.success(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                result.failure();
            }
        });
        requestQueue.add(stringRequest);
    }


}
