package com.kongfuzi.student.ui.kao.major;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kongfuzi.student.R;
/**
 * @author LBDL
 * @desc 专业介绍(专业详情)
 *
 */
public class IntroductionFragment extends Fragment {
	
	private static String content;
	
	private TextView content_tv;
	
	public static IntroductionFragment getInstance(String body){
		content = body;
		return new IntroductionFragment();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_all_production, container, false);
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
		content_tv = (TextView) view.findViewById(R.id.text_all_production_tv);
		
		if (TextUtils.isEmpty(content)) {
			content_tv.setText("暂无数据");
		} else {
			content_tv.setText(content);
		}
		
	}

}
