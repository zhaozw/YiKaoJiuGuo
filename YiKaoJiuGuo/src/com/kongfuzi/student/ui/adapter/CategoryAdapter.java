package com.kongfuzi.student.ui.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;

import com.kongfuzi.student.R;
import com.kongfuzi.student.bean.Conditions;

/**
 * @author LBDL
 * @desc ·ÖÀà
 */
public class CategoryAdapter extends BaseAdapter {
	
	private Context context;
	private List<Conditions> list;
	
	
	public CategoryAdapter(Context context,List<Conditions> list){
		this.list = list;
		this.context = context;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.size() > 0 ? list.get(position) : null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		convertView = LayoutInflater.from(context).inflate(R.layout.item_category_list, parent, false);
		
		CheckBox category_cb = (CheckBox)convertView.findViewById(R.id.check_item_category_list_cb);
		
		category_cb.setText(list.get(position).ename);
		
		return convertView;
	}

	
}
