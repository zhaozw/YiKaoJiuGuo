package com.kongfuzi.student.ui.adapter;

import java.util.List;
import za.co.immedia.pinnedheaderlistview.SectionedBaseAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kongfuzi.student.R;
import com.kongfuzi.student.bean.Exam;
import com.kongfuzi.student.bean.Examination;

/**
 * @author LBDL
 * @desc 历年考题适配器
 *
 */
public class ExamQuesAdapter extends SectionedBaseAdapter{
	private Context context;
	private List<Examination>examinations;
	public ExamQuesAdapter(Context context,
			List<Examination> examinations) {
			this.context=context;
			this.examinations=examinations;
	}
	

	public class ViewHolder{
		public TextView body,second;
		
	}
	@Override
	public Object getItem(int section, int position) {
		return !examinations.isEmpty() && examinations != null ? examinations.get(position) : null;
	}


	@Override
	public long getItemId(int section, int position) {
		return 0;
	}


	@Override
	public int getSectionCount() {
		return examinations.size();
	}


	@Override
	public int getCountForSection(int section) {
		return examinations.get(section).exam.size();
	}


	@Override
	public View getItemView(int section, int position, View convertView, ViewGroup parent) {
		
		Exam exam = examinations.get(section).exam.get(position);
		
		ViewHolder viewHolder=null;
		if(convertView==null){
			viewHolder=new ViewHolder();
			convertView=LayoutInflater.from(context).inflate(R.layout.expandablelistview_item2, parent,false);
			viewHolder.second=(TextView) convertView.findViewById(R.id.textView_exLv_item2);
			viewHolder.body=(TextView) convertView.findViewById(R.id.textView_exLv_item3);
			convertView.setTag(viewHolder);
		}else {
			viewHolder=(ViewHolder) convertView.getTag();
		}
//		Log.i("E", "group:"+groupPosition + ", child:"+childPosition+ ", listSize："+examinations.size());
		viewHolder.second.setText(exam.second);
		viewHolder.body.setText(exam.body);
		return convertView;
		
	}


	@Override
	public View getSectionHeaderView(int section, View convertView, ViewGroup parent) {
		convertView = LayoutInflater.from(context).inflate(R.layout.item_my_volunteer_head, parent, false);
		TextView major_tv = (TextView) convertView.findViewById(R.id.major_item_my_volunteer_header_tv);
		
		major_tv.setText(examinations.get(section).title+"年");
		return convertView;
	}
}
