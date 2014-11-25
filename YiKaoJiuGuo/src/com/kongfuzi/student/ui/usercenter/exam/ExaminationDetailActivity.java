package com.kongfuzi.student.ui.usercenter.exam;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.kongfuzi.lib.volley.Response.Listener;
import com.kongfuzi.student.R;
import com.kongfuzi.student.app.YiKaoApplication;
import com.kongfuzi.student.bean.ExamSite;
import com.kongfuzi.student.bean.ExaminationSchedule;
import com.kongfuzi.student.support.network.ObjectRequest;
import com.kongfuzi.student.support.utils.BundleArgsConstants;
import com.kongfuzi.student.support.utils.UrlConstants;
import com.kongfuzi.student.ui.global.BaseActivity;
import com.kongfuzi.student.ui.kao.major.ExaminationFragment;

/**
 * @author LBDL
 * @desc 考试日程详情
 * 
 */
public class ExaminationDetailActivity extends BaseActivity implements OnClickListener {

	private TextView school_tv;
	private TextView major_tv;
	private TextView online_tv;
	private TextView site_tv;
	private TextView confirm_tv;
	private TextView date_tv;
	private TextView range_tv;

	private int major_id;
	private String major_ename;
	private int schedule_id;

	private Context mContext;
	private List<ExamSite> site_list = new ArrayList<ExamSite>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_examination_detail);

		mContext = this;

		Intent intent = getIntent();
		major_id = intent.getIntExtra("major_id", 0);
		schedule_id = intent.getIntExtra("schedule_id", 0);

		initView();
		getData();
	}

	/**
	 * @param major_id
	 *            专业id
	 * @param schedule_id
	 *            日程id
	 * */
	public static Intent newIntent(int major_id, int schedule_id) {
		Intent intent = new Intent(YiKaoApplication.getInstance(), ExaminationDetailActivity.class);
		intent.putExtra(BundleArgsConstants.MAJOR_ID, major_id);
		intent.putExtra(BundleArgsConstants.SCHEDULE_ID, schedule_id);
		return intent;

	}

	private void initView() {

		school_tv = (TextView) findViewById(R.id.school_exam_detail_tv);
		major_tv = (TextView) findViewById(R.id.major_exam_detail_tv);
		online_tv = (TextView) findViewById(R.id.online_exam_detail_tv);
		site_tv = (TextView) findViewById(R.id.site_exam_detail_tv);
		confirm_tv = (TextView) findViewById(R.id.confirm_exam_detail_tv);
		date_tv = (TextView) findViewById(R.id.date_exam_detail_tv);
		range_tv = (TextView) findViewById(R.id.range_exam_detail_tv);

	}

	private void getData() {

		if (!isLogin()) {
			return;
		}

		Intent intent = getIntent();
		int majorId = intent.getIntExtra(BundleArgsConstants.MAJOR_ID, 0);
		int scheduleId = intent.getIntExtra(BundleArgsConstants.SCHEDULE_ID, 0);

		showLoadingDialog();
		ObjectRequest<ExaminationSchedule> request = new ObjectRequest<ExaminationSchedule>(
				UrlConstants.SCHEDULE_DETAIL + "&mid=" + YiKaoApplication.getStudentId() + "&id=" + majorId + "&tid="
						+ scheduleId, new Listener<ExaminationSchedule>() {

					@Override
					public void onResponse(ExaminationSchedule response) {
						dismissLoadingDialog();
						if (response != null) {
							major_ename = response.major;
							school_tv.setText(response.school);
							major_tv.setText(response.major);
							online_tv.setText(response.online);
							site_tv.setText(response.site);
							confirm_tv.setText(response.confirm);
							date_tv.setText(response.date);
							range_tv.setText(response.range);
							site_list = response.examSites;

							site_tv.setOnClickListener(ExaminationDetailActivity.this);
						}
					}
				}, new TypeToken<ExaminationSchedule>() {
				}.getType());

		queue.add(request);
		queue.start();
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		// 考点
		case R.id.site_exam_detail_tv:
			// siteDialog();
			break;

		default:
			break;
		}
	}

}
