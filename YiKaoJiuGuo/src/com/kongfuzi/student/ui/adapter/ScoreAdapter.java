package com.kongfuzi.student.ui.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.kongfuzi.student.R;
import com.kongfuzi.student.bean.Score;

/**
 * @author LBDL
 * @desc 分数适配器
 *
 */
public class ScoreAdapter extends BaseAdapter {
	
	private Context context;
	private List<Score> list = new ArrayList<Score>();
	
	public ScoreAdapter (Context context,List<Score> list){
		this.context = context;
		this.list = list;
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
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		convertView = LayoutInflater.from(context).inflate(R.layout.item_more_admission, parent, false);
		
		TextView site_tv = (TextView) convertView.findViewById(R.id.site_item_more_admission_tv);
		TextView category_tv = (TextView) convertView.findViewById(R.id.category_item_more_admission_tv);
		TextView hscore_tv = (TextView) convertView.findViewById(R.id.hscore_item_more_admission_tv);
		TextView lscore_tv = (TextView) convertView.findViewById(R.id.lscore_item_more_admission_tv);
		
		if (position == 0) {
			site_tv.setText("地区");
			category_tv.setText("类别");
			hscore_tv.setText("最高分");
			lscore_tv.setText("最底分");
		}else {
			
			Score score = list.get(position - 1);
			
			site_tv.setText(score.province);
			category_tv.setText(score.course);
			hscore_tv.setText(score.hscore);
			lscore_tv.setText(score.lscore);
			
		}
		
		return convertView;
	}

}
