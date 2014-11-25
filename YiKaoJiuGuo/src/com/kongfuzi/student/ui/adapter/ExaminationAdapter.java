package com.kongfuzi.student.ui.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kongfuzi.student.R;
import com.kongfuzi.student.bean.Entity;

/**
 * @author LBDL
 * @desc 历年考题
 */
public class ExaminationAdapter extends BaseAdapter {

	private Context context;
	private String majorString;
	private ViewHolder holder;
	
	private List<Entity> list = new ArrayList<Entity>();
	
	public ExaminationAdapter(Context context,String majorString,List<Entity> list) {
		this.context = context;
		this.majorString = majorString;
		this.list = list;
	}

	class ViewHolder {
		TextView title_tv;
		TextView first_tv;
		TextView second_tv;
		TextView body_tv;
		LinearLayout content_ll;
	}
	
	@Override
	public int getCount() {
		Log.i("Examination", list.size() + "");
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		Entity entity = list.get(position);

		if (convertView == null) {
			
			holder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(R.layout.item_examination, parent, false);
			
			holder.title_tv = (TextView) convertView.findViewById(R.id.title_item_examination_tv);
			holder.first_tv = (TextView) convertView.findViewById(R.id.first_item_examination_tv);
			holder.second_tv = (TextView) convertView.findViewById(R.id.second_item_examination_tv);
			holder.body_tv = (TextView) convertView.findViewById(R.id.body_item_examination_tv);
			holder.content_ll = (LinearLayout) convertView.findViewById(R.id.content_item_examination_ll);

			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.content_ll.setVisibility(View.VISIBLE);
		holder.title_tv.setVisibility(View.VISIBLE);

		Log.i("Examination", entity.title + "isTitle = " + entity.isTitle);

		if (entity.isTitle) {
			holder.content_ll.setVisibility(View.GONE);
			holder.title_tv.setText(entity.title+"年考题--" + majorString);
		} else {
			holder.title_tv.setVisibility(View.GONE);
			holder.first_tv.setText(entity.first);
			holder.second_tv.setText(entity.second);
			holder.body_tv.setText(entity.body);
		}

		return convertView;
	}

}