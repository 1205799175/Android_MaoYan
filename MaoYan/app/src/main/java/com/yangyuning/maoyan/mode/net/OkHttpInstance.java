package com.yangyuning.maoyan.mode.net;

import android.os.Handler;
import android.os.Looper;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by dllo on 16/10/26.
 * OkHttp
 * @杨宇宁
 */
public class OkHttpInstance {

    private static OkHttpInstance instance;
    private OkHttpClient okHttpClient;
    private Handler handler;

    private OkHttpInstance(){
        okHttpClient = new OkHttpClient();
        handler = new Handler(Looper.getMainLooper());
    }

    public static OkHttpInstance getInstance(){
        if (instance == null){
            synchronized (OkHttpInstance.class){
                if (instance == null){
                    instance = new OkHttpInstance();
                }
            }
        }
        return instance;
    }
    /**
     * 同步get请求
     */
    private Response mGet(String url) throws IOException{
        final Request request = new Request.Builder().url(url).build();
        Call call = okHttpClient.newCall(request);
        Response execute = call.execute();
        return execute;
    }
    private String mGetAsString(String url) throws IOException{
        Response execute = mGet(url);
        return execute.body().string();
    }
    /**
     * 异步的get请求
     */
    private void mGetAsyn(String url,final ResultCallback callback){
        final Request request = new Request.Builder().url(url).build();
        deliveryResult(callback,request);
    }

    /**
     * 同步的post请求
     */
    private Response mPost(String url,Param... params) throws IOException{
        Request request = buildPostRequest(url, params);
        Response response = okHttpClient.newCall(request).execute();
        return response;
    }

    /**
     * 同步的post请求
     * @param params post的参数
     */
    private String mPostAsString(String url,Param... params) throws IOException{
        Response response = mPost(url,params);
        return response.body().string();
    }

    /**
     * 异步的post请求
     */
    private void mPostAsyn(String url,final ResultCallback callback,Param... params){
        Request request = buildPostRequest(url,params);
        deliveryResult(callback,request);
    }
    /**
     * 异步的post请求
     */
    private void mPostAsyn(String url,final ResultCallback callback,Map<String, String> params){
        Param[] paramsArr= map2Params(params);
        Request request = buildPostRequest(url,paramsArr);
        deliveryResult(callback,request);
    }

    /*****************对外提供的方法******************/
    public static Response getAsyn(String url) throws IOException {
        return getInstance().mGet(url);
    }
    public static String getAsString(String url) throws IOException {
        return getInstance().mGetAsString(url);
    }

    public static void getAsyn(String url, ResultCallback callback) {
        getInstance().mGetAsyn(url, callback);
    }

    public static Response post(String url, Param... params) throws IOException {
        return getInstance().mPost(url, params);
    }

    public static String postAsString(String url, Param... params) throws IOException {
        return getInstance().mPostAsString(url, params);
    }

    public static void postAsyn(String url, final ResultCallback callback, Param... params) {
        getInstance().mPostAsyn(url, callback, params);
    }


    public static void postAsyn(String url, final ResultCallback callback, Map<String, String> params) {
        getInstance().mPostAsyn(url, callback, params);
    }

    /**********************************************/

    private Param[] validateParam(Param[] params)
    {
        if (params == null)
            return new Param[0];
        else return params;
    }

    private Param[] map2Params(Map<String, String> params)
    {
        if (params == null) return new Param[0];
        int size = params.size();
        Param[] res = new Param[size];
        Set<Map.Entry<String, String>> entries = params.entrySet();
        int i = 0;
        for (Map.Entry<String, String> entry : entries)
        {
            res[i++] = new Param(entry.getKey(), entry.getValue());
        }
        return res;
    }

    private Request buildPostRequest(String url, Param[] params) {
        if (params == null){
            params = new Param[0];
        }
        FormBody.Builder builder = new FormBody.Builder();
        for (Param param : params){
            builder.add(param.key,param.value);
        }
        RequestBody requestBody = builder.build();
        return new Request.Builder().url(url).post(requestBody).build();
    }

    private void deliveryResult(final ResultCallback callback, Request request) {
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                sendFailedStringCallback(call,e,callback);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                    sendSuccessResultCallback(string, callback);
            }
        });
    }

    private void sendSuccessResultCallback(final Object object, final ResultCallback callback) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (callback != null){
                    callback.onResponse(object);
                }
            }
        });
    }

    private void sendFailedStringCallback(final Call call, final IOException e, final ResultCallback callback) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (callback != null){
                    callback.onError(call,e);
                }
            }
        });
    }


    public static abstract class ResultCallback<T>{
        public abstract void onError(Call call, Exception e);

        public abstract void onResponse(T response);
    }
    public static class Param {
        public Param() {}
        public Param(String key, String value) {
            this.key = key;
            this.value = value;
        }
        String key;
        String value;
    }

}
