package com.yangyuning.maoyan.utils;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * Created by dllo on 16/10/24.
 * 手势滑动退出帮助类
 * @author 杨宇宁
 */
public class GestureHelper implements GestureDetector.OnGestureListener{
    private GestureDetector gestureDetector;
    private OnFlingListener listener;

    public static abstract class OnFlingListener {
        public abstract void OnFlingLeft();
        public abstract void OnFlingRight();
    }

    public GestureHelper(Context context){
        gestureDetector = new GestureDetector(context, this);
    }

    public void setListener(OnFlingListener listener) {
        this.listener = listener;
    }

    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        if (e2.getX() - e1.getX() > 30 && Math.abs(velocityX) > 0 && (Math.abs(e2.getY() - e1.getY()) < 150)) {
            listener.OnFlingLeft();
        }
        else if (e1.getX() - e2.getX() > 30 && Math.abs(velocityX) > 0 && (Math.abs(e2.getY() - e1.getY())) < 150) {
            listener.OnFlingRight();
        }
        return true;
    }
}
