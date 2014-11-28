package com.kongfuzi.student.ui.adapter;

import java.util.List;

import android.R.integer;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.kongfuzi.student.R;
import com.kongfuzi.student.bean.Conditions;
import com.kongfuzi.student.support.utils.UrlConstants;

public class LeftKaoAdapter extends BaseAdapter{
	
	private Context context;
	private List<Conditions> list;
	public int selectedPosition = -1;
	private View curSelectedView = null;
	
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
	
	class MyOnClickListener implements OnClickListener{
		private View clickView;
		private int curPosition;
		public MyOnClickListener(View view, int position){
			clickView = view;
			curPosition = position;
		}
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(selectedPosition != curPosition){
				clickView.setBackgroundColor(context.getResources().getColor(R.color.white));
				curSelectedView.setBackgroundColor(context.getResources().getColor(R.color.light_grey));
			}
			selectedPosition = curPosition;
			curSelectedView = clickView;
			notifyDataSetChanged();
		}
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		convertView = LayoutInflater.from(context).inflate(R.layout.item_fragment_kao_left, parent, false);
		
		TextView text_tv = (TextView)convertView.findViewById(R.id.text_item_kao_left_tv);
		
		text_tv.setText(list.get(position).ename);
		if(position == selectedPosition){
			convertView.setBackgroundColor(context.getResources().getColor(R.color.white));
		}else{
			convertView.setBackgroundColor(context.getResources().getColor(R.color.light_grey));
		}
		
		//.convertView.setOnClickListener(new MyOnClickListener(convertView, position));
		
		return convertView;
	}

}
