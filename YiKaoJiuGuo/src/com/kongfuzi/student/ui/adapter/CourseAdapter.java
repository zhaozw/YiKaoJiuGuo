package com.kongfuzi.student.ui.adapter;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kongfuzi.student.R;
import com.kongfuzi.student.bean.Course;
import com.kongfuzi.student.bean.CourseMajor;

public class CourseAdapter extends BaseAdapter {

	private Context context;
	
	private ViewHolder viewHolder;
	private Course course;
	private List<Course> list = new ArrayList<Course>();
	
	private static final String TAG = "CourseAdapter";
	
	public CourseAdapter(Context context){
		this.context = context;
	}
	
	public void setList(List<Course> list){
		this.list = list;
		notifyDataSetChanged();
	}

	class ViewHolder {
		TextView position_tv;
		TextView attr_tv;
		TextView name_tv;
		LinearLayout content_ll;
//		TextView college0_tv;
//		TextView major0_tv;
//		TextView college1_tv;
//		TextView major1_tv;
//		TextView college2_tv;
//		TextView major2_tv;
		
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public void addFirstPageData(List<Course> item_list) {
		list.clear();
		list.addAll(item_list);
		notifyDataSetChanged();
	}

	public void addOtherPageData(List<Course> item_list) {
		list.addAll(item_list);
		notifyDataSetChanged();
	}

	@SuppressLint("NewApi")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if (list.isEmpty()) {
			return convertView;
		}
		course = list.get(position);
		if (convertView == null) {
			viewHolder = new ViewHolder();

			convertView = LayoutInflater.from(context).inflate(R.layout.item_my_course_list, parent, false);
			viewHolder.position_tv = (TextView) convertView.findViewById(R.id.position_item_my_course_tv);
			viewHolder.attr_tv = (TextView) convertView.findViewById(R.id.attr_item_my_course_tv);
			viewHolder.name_tv = (TextView) convertView.findViewById(R.id.name_item_my_course_tv);
			viewHolder.content_ll = (LinearLayout) convertView.findViewById(R.id.content_item_my_course_list_ll);
//			viewHolder.college0_tv = (TextView) convertView.findViewById(R.id.college0_item_my_course_tv);
//			viewHolder.major0_tv = (TextView) convertView.findViewById(R.id.major0_item_my_course_tv);
//			viewHolder.college1_tv = (TextView) convertView.findViewById(R.id.college1_item_my_course_tv);
//			viewHolder.major1_tv = (TextView) convertView.findViewById(R.id.major1_item_my_course_tv);
//			viewHolder.college2_tv = (TextView) convertView.findViewById(R.id.college2_item_my_course_tv);
//			viewHolder.major2_tv = (TextView) convertView.findViewById(R.id.major2_item_my_course_tv);
			
			convertView.setTag(viewHolder);
		}else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		viewHolder.position_tv.setText(String.valueOf(position + 1));
		viewHolder.attr_tv.setText(course.attr);
		viewHolder.name_tv.setText(course.name);
		
		List<CourseMajor> courseMajor_list = course.body;
		Log.i(TAG, "size (" + courseMajor_list.size());
		viewHolder.content_ll.removeAllViewsInLayout();
		for (int i = 0; i < courseMajor_list.size(); i++) {
			CourseMajor courseMajor = courseMajor_list.get(i);
			TextView content_tv = new TextView(context);
			content_tv.setTextColor(context.getResources().getColor(R.color.green));
			content_tv.setText(courseMajor.college_name + "   " + courseMajor.major_name);
			viewHolder.content_ll.addView(content_tv);
		}
		

		return convertView;
	}

}
