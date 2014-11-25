package com.kongfuzi.student.ui.usercenter;

import java.util.List;

import me.maxwin.view.XListView;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.kongfuzi.lib.volley.Response.Listener;
import com.kongfuzi.student.R;
import com.kongfuzi.student.app.YiKaoApplication;
import com.kongfuzi.student.bean.Course;
import com.kongfuzi.student.support.network.ArrayRequest;
import com.kongfuzi.student.support.utils.UrlConstants;
import com.kongfuzi.student.ui.adapter.CourseAdapter;
import com.kongfuzi.student.ui.global.BaseActivity;
import com.umeng.analytics.MobclickAgent;

/**
 * @author LBDL
 * @desc 我的课程
 * 
 */
public class MyCourseActivity extends BaseActivity {

	private TextView count_tv;
	private XListView list_xlv;

	private Context context;
	private CourseAdapter adapter;
	// 是否正在加载
	private Boolean isLoading = false;
	public static final String TAG = "MyCourseActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_course);

		context = this;

		count_tv = (TextView) findViewById(R.id.count_my_course_tv);
		list_xlv = (XListView) findViewById(R.id.list_my_course_xlv);

		adapter = new CourseAdapter(context);
		list_xlv.setAdapter(adapter);

		if (isLoading == false) {
			isLoading = true;
			// 开始刷新
			list_xlv.startRefresh();
		} else {
			list_xlv.NotRefreshAtBegin();
		}

		isLoading = true;

		getData();

	}

	@Override
	protected void onResume() {
		super.onResume();
		MobclickAgent.onPageStart(TAG);
	}

	private void getData() {

		if (!isLogin()) {
			return;
		}

		ArrayRequest<List<Course>> request = new ArrayRequest<List<Course>>(UrlConstants.MY_COURSE + "&mid="
				+ YiKaoApplication.getStudentId(), new Listener<List<Course>>() {

			@Override
			public void onResponse(List<Course> response) {
				
				if (!response.isEmpty() && response != null) {
					count_tv.setText("我要考的" + response.size() + "门课程");
					adapter.setList(response);
				}
			}
		}, new TypeToken<List<Course>>() {
		}.getType());

		queue.add(request);
		queue.start();
	}

	@Override
	protected void onPause() {
		MobclickAgent.onPageEnd(TAG);
		super.onPause();
	}

}
