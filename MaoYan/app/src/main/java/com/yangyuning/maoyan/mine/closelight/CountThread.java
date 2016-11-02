package com.yangyuning.maoyan.mine.closelight;

import android.os.Handler;
import android.os.Message;

/**
 * Created by dllo on 16/10/31.
 */
public class CountThread extends Thread {
    private Handler handler;
    public static final int COUNT = 0x111;
    int count = 0;

    public CountThread(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void run() {
        super.run();
        while (true){
            try {
                Thread.sleep(1000);
                Message message = Message.obtain();
                message.what = COUNT;
                message.obj = count;
                handler.sendMessage(message);
                count++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
