package com.yangyuning.maoyan.mine.order;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

/**
 * Created by siqi on 16/3/30.
 *
 * @author siqi
 */
public class OrderUtils {

    /**
     * 生成商户订单号，该值在商户端应保持唯一（String(8,20) 仅支持数字或字母）
     */
    public static String getOutTradeNo() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss",
                Locale.getDefault());
        Date date = new Date();
        String key = format.format(date);
        Random r = new Random();
        key = key + r.nextInt(100000);
        return key;
    }

}
