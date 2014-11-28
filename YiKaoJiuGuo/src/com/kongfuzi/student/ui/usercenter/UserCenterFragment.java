package com.kongfuzi.student.ui.usercenter;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.kongfuzi.lib.volley.Response.Listener;
import com.kongfuzi.student.R;
import com.kongfuzi.student.app.YiKaoApplication;
import com.kongfuzi.student.bean.User;
import com.kongfuzi.student.support.network.ObjectRequest;
import com.kongfuzi.student.support.utils.UrlConstants;
import com.kongfuzi.student.ui.global.BaseFragment;
import com.kongfuzi.student.ui.setting.SettingActivity;
import com.kongfuzi.student.ui.usercenter.exam.ExaminationActivity;

/**
 * @author LBDL
 * @desc 我的
 * 
 */
public class UserCenterFragment extends BaseFragment implements OnClickListener {

	private ImageView avatar_iv;
	private TextView nick_tv;
	private ImageView setting_iv;

	private TextView course_tv;
	private TextView volunteer_tv;
	private TextView schedule_tv;
	private TextView colletion_tv;

	private LinearLayout course_ll;
	private LinearLayout volunteer_ll;
	private LinearLayout schedule_ll;
	private LinearLayout collection_ll;

	public static UserCenterFragment getInstance() {

		UserCenterFragment fragment = new UserCenterFragment();
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

		course_tv = (TextView) view.findViewById(R.id.course_user_center_tv);
		volunteer_tv = (TextView) view.findViewById(R.id.volunteer_user_center_tv);
		schedule_tv = (TextView) view.findViewById(R.id.schedule_user_center_tv);
		colletion_tv = (TextView) view.findViewById(R.id.collection_user_center_tv);

		course_ll = (LinearLayout) view.findViewById(R.id.course_user_center_ll);
		volunteer_ll = (LinearLayout) view.findViewById(R.id.volunteer_user_center_ll);
		schedule_ll = (LinearLayout) view.findViewById(R.id.schedule_user_center_ll);
		collection_ll = (LinearLayout) view.findViewById(R.id.collection_user_center_ll);

		nick_tv.setOnClickListener(this);
		setting_iv.setOnClickListener(this);
		course_ll.setOnClickListener(this);
		volunteer_ll.setOnClickListener(this);
		schedule_ll.setOnClickListener(this);
		collection_ll.setOnClickListener(this);

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		if (isLogin()) {
			// 已经登录
			getUserInfo();
		}
	}

	private void getUserInfo() {

		showLoadingDialog();
		ObjectRequest<User> request = new ObjectRequest<User>(UrlConstants.MY_INFO + "&mid="
				+ YiKaoApplication.getStudentId(), new Listener<User>() {

			@Override
			public void onResponse(User user) {
				dismissLoadingDialog();
				if (user != null) {
					Log.i("UserCenterFragment", "userName = " + YiKaoApplication.getPhone());
					imageLoader.displayImage(user.avatar.pic, avatar_iv);
					nick_tv.setText(YiKaoApplication.getPhone());
					course_tv.setText(user.courseNum + "门课程");
					volunteer_tv.setText(user.volunteerNum + "所大学");
					schedule_tv.setText(user.scheduleNum + "场考试");
					colletion_tv.setText(user.collectionNum + "个收藏");
				}
			}
		}, new TypeToken<User>() {
		}.getType());

		queue.add(request);
		queue.start();
	}

	@Override
	public void onClick(View v) {

		Intent intent = new Intent();
		Class<?> cls = null;

		switch (v.getId()) {

		case R.id.nick_user_center_tv:

			if (!isLogin()) {
				getActivity().startActivity(LoginActivity.newIntent());
			}

			break;

		case R.id.setting_user_center_iv:
			cls = SettingActivity.class;
			break;

		case R.id.course_user_center_ll:
			cls = MyCourseActivity.class;
			break;

		case R.id.volunteer_user_center_ll:
			 cls =MyVolunteerActivity.class;
			break;

		case R.id.schedule_user_center_ll:
			cls = ExaminationActivity.class;
			break;
		case R.id.collection_user_center_ll:
			cls = MyCollectionActivity.class;
			break;

		default:
			break;
		}

		if (cls != null) {
			intent.setClass(getActivity(), cls);
			getActivity().startActivity(intent);
		}

	}

}
