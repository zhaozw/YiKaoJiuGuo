package com.kongfuzi.student.ui.adapter;

import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.kongfuzi.student.R;
import com.kongfuzi.student.bean.ExaminationSchedule;

/**
 * @author LBDL
 * @desc �����ճ�
 */
public class ExaminationAdapter{
//
//	private ViewHolder holder;
//	private ExaminationSchedule eSchedule;
//	private List<ExaminationSchedule> list = new ArrayList<ExaminationSchedule>();
//
//	class ViewHolder {
//		TextView position_tv;
//		TextView major_tv;
//		TextView school_tv;
//		TextView site_tv;
//		TextView date_tv;
//	}
//	
//	public void setList(List<ExaminationSchedule> list){
//		this.list = list;
//		notifyDataSetChanged();
//	}
//
//	@Override
//	public int getCount() {
//		return list.size();
//	}
//
//	@Override
//	public Object getItem(int position) {
//		return null;
//	}
//
//	@Override
//	public long getItemId(int position) {
//		return 0;
//	}
//
//	@Override
//	public View getView(int position, View convertView, ViewGroup parent) {
//
//		eSchedule = list.get(position);
//
//		if (convertView == null) {
//
//			holder = new ViewHolder();
//			convertView = getLayoutInflater().inflate(R.layout.item_examination_schedule, parent, false);
//
//			holder.position_tv = (TextView) convertView.findViewById(R.id.position_item_examination_schedule_tv);
//			holder.major_tv = (TextView) convertView.findViewById(R.id.major_item_examination_schedule_tv);
//			holder.school_tv = (TextView) convertView.findViewById(R.id.school_item_examination_schedule_tv);
//			holder.site_tv = (TextView) convertView.findViewById(R.id.site_item_examination_schedule_tv);
//			holder.date_tv = (TextView) convertView.findViewById(R.id.date_item_examination_schedule_tv);
//
//			convertView.setTag(holder);
//		} else {
//			holder = (ViewHolder) convertView.getTag();
//		}
//
//		if (position <= 2) {
//			holder.position_tv.setBackgroundDrawable(getResources().getDrawable(R.drawable.examinationfirst_bg));
//		} else {
//			holder.position_tv.setBackgroundDrawable(getResources().getDrawable(R.drawable.examinationsecond_bg));
//
//		}
//
//		holder.position_tv.setText(position + "");
//		holder.major_tv.setText(eSchedule.major);
//		holder.school_tv.setText("(" + eSchedule.school + ")");
//		Schedule schedule = eSchedule.schedules;
//		holder.site_tv.setText(schedule.site);
//		holder.date_tv.setText(schedule.date);
//
//		return convertView;
//	}
//
}
