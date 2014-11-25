package com.kongfuzi.student.ui.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.kongfuzi.student.R;
import com.kongfuzi.student.bean.Conditions;

public class LeftKaoAdapter extends BaseAdapter{
	
	private Context context;
	private List<Conditions> list;
	
	public LeftKaoAdapter(Context context,List<Conditions> list) {
		this.context = context;
		this.list = list;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		
		return list.size() >0 ?list.get(arg0):null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		convertView = LayoutInflater.from(context).inflate(R.layout.item_fragment_kao_left, parent, false);
		
		TextView text_tv = (TextView)convertView.findViewById(R.id.text_item_kao_left_tv);
		
		text_tv.setText(list.get(position).ename);
		
		return convertView;
	}

}
