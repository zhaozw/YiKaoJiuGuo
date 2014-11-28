package com.kongfuzi.student.ui.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.kongfuzi.student.R;
import com.kongfuzi.student.bean.Message;

/**
 * @author LBDL
 * @desc œ˚œ¢  ≈‰∆˜
 */
public class MessageAdapter extends BaseAdapter {
	
	private Context context;
	private List<Message> list ;
	
	public MessageAdapter(Context context) {
		this.context = context;
	}
	
	public void setList(List<Message> list) {
		this.list = list;
		notifyDataSetChanged();
	}
	
	@Override
	public int getCount() {
		return list == null ? 0 : list.size();
	}

	@Override
	public Object getItem(int position) {
		return list == null ? null : list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		convertView = LayoutInflater.from(context).inflate(R.layout.item_fragment_message, parent, false);
		
		TextView text_tv = (TextView) convertView.findViewById(R.id.text_item_message_tv);
		
		text_tv.setText(list.get(position).title);
		
		return convertView;
	}

}
