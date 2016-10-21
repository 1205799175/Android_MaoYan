package com.yangyuning.maoyan.movie.area;

import com.yangyuning.maoyan.mode.bean.AreaBean;

import java.util.Comparator;

/**
 *	将A-Z排序
 */
public class PinyinComparator implements Comparator<AreaBean.CtsBean> {
	@Override
	public int compare(AreaBean.CtsBean o1, AreaBean.CtsBean o2) {
		if (o1.getPy().equals("@") || o2.getPy().equals("#")) {
			return -1;
		} else if (o1.getPy().equals("#") || o2.getPy().equals("@")) {
			return 1;
		} else {
			return o1.getPy().compareTo(o2.getPy());
		}
	}

}
