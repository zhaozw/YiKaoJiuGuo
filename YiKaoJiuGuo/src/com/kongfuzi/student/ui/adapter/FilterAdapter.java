package com.kongfuzi.student.ui.adapter;

import com.kongfuzi.student.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

/**
 * @author LBDL
 * @desc 筛选条件适配器
 *
 */
public class FilterAdapter extends BaseAdapter {
	
	private Context mContext;
	//分数
	public int score;
	
	public String[] nameArray = null;
//	public List<String> list = new ArrayList<String>();

	public FilterAdapter(Context context) {
		mContext = context;
		
		nameArray = mContext.getResources().getStringArray(R.array.filter_condition);
		
//		for (int i = 0; i < nameArray.length; i++) {
//			
//			list.add(nameArray[i]);
//		}
		
	}
	
	@Override
	public int getCount() {
		return nameArray.length;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		LayoutInflater inflater = LayoutInflater.from(mContext);
		
		//估分 
		if (position == 0) {
			
			convertView = inflater.inflate(R.layout.item_filter_condition_first, parent, false);
			
			TextView name_tv = (TextView) convertView.findViewById(R.id.name_item_filter_conditions_first_tv);
			EditText content_et = (EditText) convertView.findViewById(R.id.content_item_filter_conditions_first_et);
			
		}
		//最后一项
		else if (position == nameArray.length - 1) {
			convertView = inflater.inflate(R.layout.item_filter_condition_last, parent,false);
			
			CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.check_item_filter_condition_last_rb);
			
			checkBox.setText(nameArray[position]);
			
		}else {
			
			convertView = inflater.inflate(R.layout.item_filter_condition, parent, false);
			
			TextView name_tv = (TextView) convertView.findViewById(R.id.name_item_filter_conditions_tv);
			TextView content_tv = (TextView) convertView.findViewById(R.id.content_item_filter_conditions_tv);
			
			content_tv.setVisibility(View.GONE);
			name_tv.setTextColor(mContext.getResources().getColor(R.color.white));
			name_tv.setText(nameArray[position]);

		}
		
		
		
		return convertView;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

}
