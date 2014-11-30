package com.kongfuzi.student.ui.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kongfuzi.student.R;
import com.kongfuzi.student.app.YiKaoApplication;
import com.kongfuzi.student.bean.Major;
import com.kongfuzi.student.support.utils.SQLiteOperate;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * @author Administrator
 *@goal 大学详情的Adapter
 */
public class CollegeDetailsListViewAdapter extends ListViewBaseAdapter<Major>{
	private ImageLoader imageLoader;
	private SQLiteOperate operate=new SQLiteOperate();
	
	
	public CollegeDetailsListViewAdapter(List<Major> list,
			Context context) {
		super(list, context);
		imageLoader=YiKaoApplication.getImageLoaderInstance();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public View createView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder=null;
		if(convertView==null){
			convertView=layoutInflater.inflate(R.layout.collegecategory_listview_item2, null);
			viewHolder=new ViewHolder();
			viewHolder.imageView=(ImageView) convertView.findViewById(R.id.imageView_college_category_listView_item);
			viewHolder.textViewType=(TextView) convertView.findViewById(R.id.type_college_category_listView_item);
			viewHolder.textViewNumber=(TextView) convertView.findViewById(R.id.number_college_category_listView_item);
			
			convertView.setTag(viewHolder);
			
			}else {
				viewHolder=(ViewHolder) convertView.getTag();
			}
//		if(list.get(position).litpic!=""){
		imageLoader.displayImage(list.get(position).avatar,viewHolder.imageView);
//		}else {
//			viewHolder.imageView.setVisibility(View.INVISIBLE);
//		}
		viewHolder.textViewType.setText(list.get(position).major);
		viewHolder.textViewNumber.setText("招生人数"+list.get(position).recruit_count+"");
		
		return convertView;
	}
	class ViewHolder{
		
		ImageView imageView;
		TextView textViewType,textViewNumber;
	}
}
