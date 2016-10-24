package com.yangyuning.maoyan.movie.qrcode;

import android.graphics.Bitmap;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.yangyuning.maoyan.R;
import com.yangyuning.maoyan.base.AbsBaseActivity;
import com.yangyuning.maoyan.base.BaseTitleBar;
import com.yangyuning.maoyan.movie.zxing.encoding.EncodingHandler;
import com.yangyuning.maoyan.utils.GestureHelper;

/**
 * Created by dllo on 16/10/19.
 * 生成二维码的Activity
 * @author 杨宇宁
 */
public class CreateQRCodeActivity extends AbsBaseActivity{
    private EditText qrCodeEt;
    private ImageView qrCodeIv;
    private Button qrCodeBtn;
    private ImageView backIv;

    private GestureHelper gestureHelper;

    @Override
    protected int setLayout() {
        return R.layout.activity_create_qr_code;
    }

    @Override
    protected void initView() {
        qrCodeBtn = byView(R.id.create_qr_code_btn);
        qrCodeEt = byView(R.id.create_qr_code_et);
        qrCodeIv = byView(R.id.create_qr_code_iv);
        backIv = byView(R.id.title_bar_iv_left);
    }

    @Override
    protected void initDatas() {
        initListener();

        new BaseTitleBar(this).setImageLsftRes(R.mipmap.title_bar_back);

        //手势退出
        gestureHelper = new GestureHelper(this);
        gestureHelper.setListener(new GestureHelper.OnFlingListener() {
            @Override
            public void OnFlingLeft() {
                CreateQRCodeActivity.this.finish();
                // 退出动画
                overridePendingTransition(R.anim.translate_exit_in, R.anim.translate_exit_out);
            }

            @Override
            public void OnFlingRight() {

            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureHelper.onTouchEvent(event);
    }

    private void initListener() {
        qrCodeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取界面输入的内容
                String content = qrCodeEt.getText().toString();
                //判断内容是否为空
                if (null == content || "".equals(content)) {
                    Toast.makeText(CreateQRCodeActivity.this, R.string.please_input, Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    //生成二维码图片, 第一个参数是二维码的内容, 参数二十正方形图片的边长, 单位是像素
                    Bitmap qrcodeBitmap = EncodingHandler.createQRCode(content, 400);
                    qrCodeIv.setImageBitmap(qrcodeBitmap);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

        backIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateQRCodeActivity.this.finish();
            }
        });
    }
}
