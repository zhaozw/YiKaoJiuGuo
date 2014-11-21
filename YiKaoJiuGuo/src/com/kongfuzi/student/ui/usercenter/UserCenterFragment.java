package com.kongfuzi.student.ui.usercenter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kongfuzi.student.R;

/**
 * @author LBDL
 * @desc ÎÒµÄ
 *
 */
public class UserCenterFragment extends Fragment implements OnClickListener{
	
	private ImageView avatar_iv;
	private TextView nick_tv;
	private ImageView setting_iv;
	
	private LinearLayout course_ll;
	private LinearLayout volunteer_ll;
	private LinearLayout schedule_ll;
	
	public static UserCenterFragment getInstance(){
		
		UserCenterFragment fragment = new UserCenterFragment();
		fragment.setArguments(new Bundle());
		
		return fragment;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_user_center, container, false);
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
		avatar_iv = (ImageView) view.findViewById(R.id.avatar_user_center_iv);
		nick_tv = (TextView) view.findViewById(R.id.nick_user_center_tv);
		setting_iv = (ImageView) view.findViewById(R.id.setting_user_center_iv);
		course_ll = (LinearLayout) view.findViewById(R.id.course_user_center_ll);
		volunteer_ll = (LinearLayout) view.findViewById(R.id.volunteer_user_center_ll);
		schedule_ll = (LinearLayout) view.findViewById(R.id.schedule_user_center_ll);
		
		nick_tv.setOnClickListener(this);
		setting_iv.setOnClickListener(this);
		course_ll.setOnClickListener(this);
		volunteer_ll.setOnClickListener(this);
		schedule_ll.setOnClickListener(this);
	}
	
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onClick(View v) {
		
		switch (v.getId()) {
		
		case R.id.nick_user_center_tv:
						
			break;
			
		case R.id.setting_user_center_iv:
			
			break;
			
		case R.id.course_user_center_ll:
			
			break;
			
		case R.id.volunteer_user_center_ll:
			
			break;
			
		case R.id.schedule_user_center_ll:
			
			break;

		default:
			break;
		}
		
	}

}
