package com.yangyuning.maoyan.movie.qrcode;

import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.yangyuning.maoyan.R;
import com.yangyuning.maoyan.base.AbsBaseActivity;

/**
 * Created by dllo on 16/10/19.
 * 二维码 扫描相册中的二维码图片跳转到的webView
 * @author 杨宇宁
 */
public class QRCodeActivity extends AbsBaseActivity{
    private WebView webView;
    private TextView textView;
    @Override
    protected int setLayout() {
        return R.layout.activity_qr_code;
    }

    @Override
    protected void initView() {
        webView = byView(R.id.qr_code_webview);
        textView = byView(R.id.qr_code_tv);
    }

    @Override
    protected void initDatas() {
        Intent intent = getIntent();
        String result = intent.getStringExtra("result");
        if (!result.equals("") && result.startsWith("http")){
            ToWebView(result);
        } else {
            textView.setText(result);
        }
    }

    private void ToWebView(String result) {
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
                return false;
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
        //设置webView加载网页的属性 webSettings
        WebSettings set = webView.getSettings();
        // 让WebView能够执行javaScript
        set.setJavaScriptEnabled(true);
        // 让JavaScript可以自动打开windows
        set.setJavaScriptCanOpenWindowsAutomatically(true);
        // 设置缓存
        set.setAppCacheEnabled(true);
        // 设置缓存模式,一共有四种模式
        set.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        // 设置缓存路径
//        webSettings.setAppCachePath("");
        // 支持缩放(适配到当前屏幕)
        set.setSupportZoom(false);
        // 将图片调整到合适的大小
        set.setUseWideViewPort(true);
        // 支持内容重新布局,一共有四种方式
        // 默认的是NARROW_COLUMNS
        set.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        // 设置可以被显示的屏幕控制
        set.setDisplayZoomControls(true);
        // 设置默认字体大小
        set.setDefaultFontSize(12);
        webView.loadUrl(result);
    }

}
