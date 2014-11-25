package com.kongfuzi.student.ui.message;

import me.maxwin.view.XListView;

import com.kongfuzi.student.R;
import com.kongfuzi.student.ui.global.BaseFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author LBDL
 * @desc 消息列表
 *
 */
public class MessageFragment extends BaseFragment {
	
	private XListView msg_xlv;
	
	public static MessageFragment getInstance(){
		
		MessageFragment fragment = new MessageFragment();
		fragment.setArguments(new Bundle());
		
		return fragment;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_message, container, false);
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
		msg_xlv = (XListView) view.findViewById(R.id.msg_message_xlv);
		
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		
	}
	
	private void getData() {

	}

}
