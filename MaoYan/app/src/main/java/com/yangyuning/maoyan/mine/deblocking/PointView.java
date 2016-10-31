package com.yangyuning.maoyan.mine.deblocking;

import android.graphics.Point;

/**
 * Created by Jackie on 2015/12/25.
 * 自定义点对象
 * @author 姜鑫
 */
public class PointView extends Point {
    //用于转化密码的下标
    public int index;

    public PointView(int x, int y) {
        super(x, y);
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
