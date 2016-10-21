package com.yangyuning.maoyan.movie.area;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.yangyuning.maoyan.R;
import com.yangyuning.maoyan.mode.bean.AreaBean;

import java.util.List;

public class SortAdapter extends BaseAdapter implements SectionIndexer{
	private List<AreaBean.CtsBean> list = null;
	private Context mContext;
	
	public SortAdapter(Context mContext, List<AreaBean.CtsBean> list) {
		this.mContext = mContext;
		this.list = list;
	}
	
	/**
	 *当ListView数据放生变化时调用此方法更新数据
	 * @param list
	 */
	public void updateListView(List<AreaBean.CtsBean> list){
		this.list = list;
		notifyDataSetChanged();
	}

	public int getCount() {
		return this.list.size();
	}

	public Object getItem(int position) {
		return list.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(final int position, View view, ViewGroup arg2) {
		ViewHolder viewHolder = null;
		final AreaBean.CtsBean mContent = list.get(position);
		if (view == null) {
			viewHolder = new ViewHolder();
			view = LayoutInflater.from(mContext).inflate(R.layout.item_area_lv, null);
			viewHolder.tvTitle = (TextView) view.findViewById(R.id.title);	//地名
			viewHolder.tvLetter = (TextView) view.findViewById(R.id.catalog);	//A, B, C
			view.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) view.getTag();
		}
		
		int section = getSectionForPosition(position);

		if(position == getPositionForSection(section)){
			viewHolder.tvLetter.setVisibility(View.VISIBLE);
			viewHolder.tvLetter.setText(mContent.getPy());
		}else{
			viewHolder.tvLetter.setVisibility(View.GONE);
		}
		viewHolder.tvTitle.setText(this.list.get(position).getNm());
		return view;
	}
	


	final static class ViewHolder {
		TextView tvLetter;
		TextView tvTitle;
	}



	public int getSectionForPosition(int position) {
		return list.get(position).getPy().charAt(0);
	}


	public int getPositionForSection(int section) {
		for (int i = 0; i < getCount(); i++) {
			String sortStr = list.get(i).getPy();
			char firstChar = sortStr.toUpperCase().charAt(0);
			if (firstChar == section) {
				return i;
			}
		}
		return -1;
	}
	

	private String getAlpha(String str) {
		String  sortStr = str.trim().substring(0, 1).toUpperCase();
		if (sortStr.matches("[A-Z]")) {
			return sortStr;
		} else {
			return "#";
		}
	}

	@Override
	public Object[] getSections() {
		return null;
	}
}