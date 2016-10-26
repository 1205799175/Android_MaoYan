package com.yangyuning.maoyan.mine.order;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fuqianla.paysdk.FuQianLa;
import com.fuqianla.paysdk.FuQianLaPay;
import com.fuqianla.paysdk.bean.FuQianLaResult;
import com.yangyuning.maoyan.R;

/**
 * Created by siqi on 16/7/25.
 *
 * @author siqi
 * 订页面
 */
public class CustomRSAPayActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etCommodity, etDetails, etAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal);

        etCommodity = (EditText) findViewById(R.id.et_commodity);
        etDetails = (EditText) findViewById(R.id.et_details);
        etAmount = (EditText) findViewById(R.id.et_amount);
        Button btnPay = (Button) findViewById(R.id.btn_pay);
        btnPay.setOnClickListener(this);

        etCommodity.setText("正常模式-商品名称");
        etDetails.setText("正常模式-支付详情");
        etAmount.setText("0.01");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_pay:

                String subject = etCommodity.getText().toString();
                String amount = etAmount.getText().toString();
                String body = etDetails.getText().toString();

                if (TextUtils.isEmpty(subject)
                        || TextUtils.isEmpty(amount))
                    return;

                //支付核心代码
                FuQianLaPay pay = new FuQianLaPay.Builder(this)
                        .partner(Merchant.MERCHANT_NO)//商户号
                        .alipay(true)
                        .wxpay(true)
                        .baidupay(true)
                        .unionpay(true)
                        .jdpay(true)
                        .orderID(OrderUtils.getOutTradeNo())//订单号
                        .amount(Double.parseDouble(amount))//金额
                        .subject(subject)//商品名称
                        .body(body)
                        .notifyUrl(Merchant.MERCHANT_NOTIFY_URL)
                        .rsaPrivatePKCS8(Merchant.RSA_PRIVATE_KEY)
                        .build();
                pay.startPay();
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //返回结果
        if (requestCode == FuQianLa.REQUESTCODE
                && resultCode == FuQianLa.RESULTCODE
                && data != null) {
            FuQianLaResult result = data.getParcelableExtra(FuQianLa.PAYRESULT_KEY);
            Toast.makeText(getApplicationContext(), result.toString(), Toast.LENGTH_SHORT).show();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}
