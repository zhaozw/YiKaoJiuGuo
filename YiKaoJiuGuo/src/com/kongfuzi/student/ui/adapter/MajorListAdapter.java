package com.kongfuzi.student.ui.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kongfuzi.student.R;
import com.kongfuzi.student.app.YiKaoApplication;
import com.kongfuzi.student.bean.Major;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * @author LBDL
 * @desc 专业列表 (现在是大学列表)
 *
 */
public class MajorListAdapter extends BaseAdapter {

	private Context context;
	public List<Major> list = new ArrayList<Major>();

	private ImageLoader imageLoader;
	private ViewHolder holder;

	public MajorListAdapter(Context context) {
		this.context = context;

		imageLoader = YiKaoApplication.getImageLoaderInstance();
	}

	class ViewHolder {
		ImageView avatar_iv;
		TextView college_tv;
//		TextView major_tv;
		TextView batch_tv;
		TextView recruit_count_tv;
	}
	
	public void addFirstPageData(List<Major> college_list) {
		
		list.clear();
		
		if (college_list == null) {
			return;
		}
		list.addAll(college_list);
		notifyDataSetChanged();
	}

	public void addOtherPageData(List<Major> college_list) {
		
		if (college_list == null || college_list.isEmpty()) {
			return;
		}
		list.addAll(college_list);
		notifyDataSetChanged();
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty() ? true : false;
	}
	
	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Major getItem(int position) {
		return list.size() > 0 ? list.get(position) : null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		Major major = list.get(position);
		
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(R.layout.item_fragment_kao_right, parent, false);
			
			holder.avatar_iv = (ImageView) convertView.findViewById(R.id.avatar_item_kao_right_iv);
			holder.college_tv = (TextView) convertView.findViewById(R.id.college_item_fragment_kao_right_tv);
//			holder.major_tv = (TextView) convertView.findViewById(R.id.major_item_fragment_kao_right_tv);
			holder.batch_tv = (TextView) convertView.findViewById(R.id.batch_item_fragment_kao_right_tv);
			holder.recruit_count_tv = (TextView) convertView.findViewById(R.id.recruit_count_item_fragment_kao_right_tv);
			
			convertView.setTag(holder);
			
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		imageLoader.displayImage(major.avatar, holder.avatar_iv);
		holder.college_tv.setText(major.major);
//		holder.major_tv.setText("(" + major.major + ")");
		holder.batch_tv.setText(major.batch);
		holder.recruit_count_tv.setText("招生人数:" + major.recruit_count + "人");
		
		
		return convertView;
	}
}
