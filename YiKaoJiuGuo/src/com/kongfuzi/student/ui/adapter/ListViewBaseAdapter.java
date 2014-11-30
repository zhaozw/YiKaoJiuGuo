package com.kongfuzi.student.ui.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
/**
 * 
 * @author 洪闯
 *
 */
public abstract class ListViewBaseAdapter<T> extends BaseAdapter {

	public List<T> list;
	public Context context;
	public LayoutInflater layoutInflater;
	
	public ListViewBaseAdapter(List<T> list,Context context){
		super();
		this.context = context;
		this.list = list;
		layoutInflater = LayoutInflater.from(context);
	}
	
	@Override
	public int getCount() {
		return list!=null && !list.isEmpty() ? list.size() : 0;
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		return createView(position,convertView,parent);
	}

	public abstract View createView(int position, View convertView, ViewGroup parent);
	
}
