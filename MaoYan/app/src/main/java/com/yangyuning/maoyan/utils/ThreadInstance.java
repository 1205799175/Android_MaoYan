package com.yangyuning.maoyan.utils;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by dllo on 16/10/20.
 * 线程池单例
 */
public class ThreadInstance {
    private static ThreadInstance instance;

    private ThreadPoolExecutor executor;

    private ThreadInstance(){
        int cupCount = Runtime.getRuntime().availableProcessors();
        executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(cupCount + 1);
    }

    public static ThreadInstance getInstance() {
        if (instance == null){
            synchronized (ThreadInstance.class){
                if (instance == null){
                    instance = new ThreadInstance();
                }
            }
        }
        return instance;
    }

    public void startThread(Runnable r){
        executor.execute(r);
    }

    public void stopThread(){
        executor.shutdown();
    }

    public void removeThread(Runnable r){
        executor.remove(r);
    }

    public boolean ifShutThread(){
        return executor.isShutdown();
    }

}
