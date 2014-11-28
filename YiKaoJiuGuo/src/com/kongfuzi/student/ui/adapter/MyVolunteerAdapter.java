package com.kongfuzi.student.ui.adapter;

import java.util.List;

import com.kongfuzi.student.R;
import com.kongfuzi.student.app.YiKaoApplication;
import com.kongfuzi.student.bean.Major;
import com.kongfuzi.student.bean.Volunteer;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import za.co.immedia.pinnedheaderlistview.SectionedBaseAdapter;

public class MyVolunteerAdapter extends SectionedBaseAdapter {

	private Context context;
	private ViewHolder holder;
	private ImageLoader imageLoader;

	private List<Volunteer> list;

	public MyVolunteerAdapter(Context context, List<Volunteer> list) {
		this.context = context;
		this.list = list;

		imageLoader = YiKaoApplication.getImageLoaderInstance();
	}

	class ViewHolder {
		ImageView avatar_iv;
		TextView college_tv;
		TextView major_tv;
		TextView batch_tv;
		TextView recruit_count_tv;
	}

	@Override
	public Object getItem(int section, int position) {
		return !list.isEmpty() && list != null ? list.get(position) : null;
	}

	@Override
	public long getItemId(int section, int position) {
		return 0;
	}

	@Override
	public int getSectionCount() {
		return list.size();
	}

	@Override
	public int getCountForSection(int section) {
		return list.get(section).majorList.size();
	}

	@Override
	public View getItemView(int section, int position, View convertView, ViewGroup parent) {

		Major major = list.get(section).majorList.get(position);
		if (major == null) {
			return convertView;
		}

		if (convertView == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(R.layout.item_my_volunteer, parent, false);

			holder.avatar_iv = (ImageView) convertView.findViewById(R.id.avatar_item_my_volunteer_iv);
			holder.college_tv = (TextView) convertView.findViewById(R.id.college_item_my_volunteer_tv);
			holder.major_tv = (TextView) convertView.findViewById(R.id.major_item_my_volunteer_tv);
			holder.batch_tv = (TextView) convertView.findViewById(R.id.batch_item_my_volunteer_tv);
			holder.recruit_count_tv = (TextView) convertView.findViewById(R.id.recruit_count_item_my_volunteer_tv);

			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		imageLoader.displayImage(major.avatar, holder.avatar_iv);
		holder.college_tv.setText(major.college);
		holder.major_tv.setText(major.major);
		holder.batch_tv.setText(major.batch);
		holder.recruit_count_tv.setText("ÕÐÉúÈËÊý: " + major.recruit_count);

		return convertView;
	}

	@Override
	public View getSectionHeaderView(int section, View convertView, ViewGroup parent) {
		
		convertView = LayoutInflater.from(context).inflate(R.layout.item_my_volunteer_head, parent, false);
		TextView major_tv = (TextView) convertView.findViewById(R.id.major_item_my_volunteer_header_tv);
		
		major_tv.setText(list.get(section).majorName);
		
		
		
		return convertView;
	}
}
