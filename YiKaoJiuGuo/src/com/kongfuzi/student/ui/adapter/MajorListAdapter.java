package com.kongfuzi.student.ui.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kongfuzi.student.R;
import com.kongfuzi.student.bean.Major;
import com.kongfuzi.student.support.YiKaoApplication;
import com.nostra13.universalimageloader.core.ImageLoader;

public class MajorListAdapter extends BaseAdapter {

	private Context context;
	private List<Major> list;

	private ImageLoader imageLoader;
	private ViewHolder holder;

	public MajorListAdapter(Context context, List<Major> list) {
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
			holder.major_tv = (TextView) convertView.findViewById(R.id.major_item_fragment_kao_right_tv);
			holder.batch_tv = (TextView) convertView.findViewById(R.id.batch_item_fragment_kao_right_tv);
			holder.recruit_count_tv = (TextView) convertView.findViewById(R.id.recruit_count_item_fragment_kao_right_tv);
			
			convertView.setTag(holder);
			
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		imageLoader.displayImage(major.avatar, holder.avatar_iv);
		holder.college_tv.setText(major.college);
		holder.major_tv.setText("(" + major.major + ")");
		holder.batch_tv.setText(major.batch);
		holder.recruit_count_tv.setText("��������:" + major.recruit_count);
		
		
		return convertView;
	}
}
