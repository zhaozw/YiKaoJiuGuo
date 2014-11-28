package com.kongfuzi.student.ui.adapter;

import java.util.ArrayList;
import java.util.List;

import com.kongfuzi.student.R;
import com.kongfuzi.student.bean.KaoDian;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * @author LBDL
 * @desc 考点
 *
 */
public class KaoDianAdapter extends BaseAdapter {
	
	private Context context;
	private ViewHolder holder;
	private List<KaoDian> list = new ArrayList<KaoDian>();

	public KaoDianAdapter (Context context){
		this.context = context;
	}
	
	public void setList(List<KaoDian> list){
		this.list = list;
		notifyDataSetChanged();
	}
	
	class ViewHolder{
		TextView college_tv;
		TextView onlineTime_tv;
		TextView confirmTime_tv;
		TextView examTime_tv;
	}
	
	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.size() ==0 ? null : list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		KaoDian kaoDian = list.get(position);
		
		if (kaoDian == null) {
			return null;
		}
		
		if (convertView == null) {
			
			holder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(R.layout.item_kao_dian, parent, false);
			
			holder.college_tv = (TextView) convertView.findViewById(R.id.college_item_kao_dian_tv);
			holder.onlineTime_tv = (TextView) convertView.findViewById(R.id.online_date_item_kao_dian_tv);
			holder.confirmTime_tv = (TextView) convertView.findViewById(R.id.sub_date_item_kao_dian_tv);
			holder.examTime_tv = (TextView) convertView.findViewById(R.id.exam_date_item_kao_dian_tv);
			
			convertView.setTag(holder);
			
		}else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.college_tv.setText(kaoDian.address);
		holder.onlineTime_tv.setText( "网上报名时间: " + kaoDian.onlineTime);
		holder.confirmTime_tv.setText("现场时间: " + kaoDian.confirmTime);
		holder.examTime_tv.setText("考试时间: " + kaoDian.examTime);
		
		
		return convertView;
	}

}
