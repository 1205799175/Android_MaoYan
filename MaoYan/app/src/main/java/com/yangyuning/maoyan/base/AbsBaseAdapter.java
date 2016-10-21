package com.yangyuning.maoyan.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/10/18.
 * ListView 适配器基类
 * @author 杨宇宁
 */
public abstract class AbsBaseAdapter<D, VH extends AbsBaseAdapter.BaseHolder> extends BaseAdapter {
    protected Context context;
    protected List<D> datas;

    public AbsBaseAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<D> newList) {
        datas = newList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return datas ==  null ? 0 : datas.size();
    }

    @Override
    public D getItem(int position) {
        return datas == null ? null : datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        VH vh = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(setItemLayout(), parent, false);
            vh = onCreateViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (VH) convertView.getTag();
        }
        D itemData = getItem(position);
        onBindViewHolder(vh, itemData, position);
        return convertView;
    }

    protected abstract int setItemLayout();

    protected abstract VH onCreateViewHolder(View convertView);

    protected abstract void onBindViewHolder(VH vh, D itemData, int position);

    public static class BaseHolder{
        View itemView;
        public BaseHolder(View itemView){
            this.itemView = itemView;
        }
    }
}
