/**   
 * Copyright © 2014 All rights reserved.
 * 
 * @Title: ItemAdapter.java 
 * @Prject: PinYinSearch
 * @Package: com.example.pinyinsearch 
 * @Description: TODO
 * @author: raot  719055805@qq.com
 * @date: 2014年9月4日 下午2:06:35 
 * @version: V1.0   
 */
package com.yangyuning.maoyan.movie.area;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * @ClassName: ItemAdapter
 * @Description: TODO
 * @author: raot 719055805@qq.com
 * @date: 2014年9月4日 下午2:06:35
 */
public class ItemAdapter extends BaseAdapter {

	private ArrayList<Item> itemList;
	private Activity activity;

	public ItemAdapter(ArrayList<Item> itemList, Activity activity) {
		super();
		this.itemList = itemList;
		this.activity = activity;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return itemList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return itemList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		TextView textView;
		if (convertView == null) {
			textView = new TextView(activity);
			textView.setTextSize(20);
			textView.setGravity(Gravity.CENTER_VERTICAL);
			convertView = textView;
			convertView.setTag(textView);
		} else {
			textView = (TextView) convertView.getTag();
		}
		if (itemList.get(position).isTitle()) {
			textView.setBackgroundColor(Color.LTGRAY);
			textView.setPadding(0, 0, 0, 0);
		} else {
			textView.setBackgroundColor(Color.WHITE);
			textView.setPadding(20, 0, 0, 0);
		}
		textView.setText(itemList.get(position).getContent());
		return convertView;
	}

	@Override
	public boolean isEnabled(int position) {
		// TODO Auto-generated method stub
		if (itemList.get(position).isTitle()) {
			return false;
		}
		return super.isEnabled(position);
	}

}
