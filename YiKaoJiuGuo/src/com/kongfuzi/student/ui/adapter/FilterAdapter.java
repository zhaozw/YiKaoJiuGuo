package com.kongfuzi.student.ui.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import com.kongfuzi.student.R;
import com.kongfuzi.student.app.YiKaoApplication;
import com.kongfuzi.student.bean.Conditions;

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
	public List<Conditions> list = new ArrayList<Conditions>();

	public FilterAdapter(Context context) {
		mContext = context;
		
		nameArray = mContext.getResources().getStringArray(R.array.filter_condition);
		
		for (int i = 0; i < nameArray.length; i++) {
			
			Conditions conditions = new Conditions();
			conditions.ename = nameArray[i];
			
			list.add(conditions);
		}
		
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
			
			name_tv.setText(nameArray[position]);
			
			content_et.setOnEditorActionListener(new OnEditorActionListener() {
				
				@Override
				public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
					//输入结束
					if (actionId == EditorInfo.IME_ACTION_DONE) {
						//设置分数 设置  list item 的 id  ename = "估分"
						Conditions conditions = new Conditions();
						conditions.ename = nameArray[0];
						conditions.id = Integer.parseInt(v.getText().toString());
						YiKaoApplication.getConditionsList().set(0, conditions);
						
						
						return true;
					}else {
						return false;
					}
				}
			});
			
		}
		//最后一项
		else if (position == nameArray.length - 1) {
			convertView = inflater.inflate(R.layout.item_filter_condition_last, parent,false);
			
			CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.check_item_filter_condition_last_rb);
			
			checkBox.setText(nameArray[position]);
			
			checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					if (isChecked) {
						Conditions conditions = new Conditions();
						conditions.ename = nameArray[7];
						conditions.id = 1;
						YiKaoApplication.getConditionsList().set(7, conditions);
					}
				}
			});
			
		}else {
			
			convertView = inflater.inflate(R.layout.item_filter_condition, parent, false);
			
			TextView name_tv = (TextView) convertView.findViewById(R.id.name_item_filter_conditions_tv);
			TextView content_tv = (TextView) convertView.findViewById(R.id.content_item_filter_conditions_tv);
			
			name_tv.setText(nameArray[position]);
			content_tv.setText(list.get(position).ename);

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
