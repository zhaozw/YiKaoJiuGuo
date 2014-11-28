package com.kongfuzi.student.ui.usercenter.exam;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.kongfuzi.lib.volley.Response.Listener;
import com.kongfuzi.student.R;
import com.kongfuzi.student.app.YiKaoApplication;
import com.kongfuzi.student.bean.ExaminationSchedule;
import com.kongfuzi.student.support.network.ObjectRequest;
import com.kongfuzi.student.support.utils.BundleArgsConstants;
import com.kongfuzi.student.support.utils.UrlConstants;
import com.kongfuzi.student.ui.global.BaseActivity;


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

	private int majorId;
	private String majorName;
	private int scheduleId;

	private Context mContext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_examination_detail);

		mContext = this;

		Intent intent = getIntent();
		majorId = intent.getIntExtra(BundleArgsConstants.MAJOR_ID, 0);
		majorName = intent.getStringExtra(BundleArgsConstants.MAJOR_NAME);
		scheduleId = intent.getIntExtra(BundleArgsConstants.SCHEDULE_ID, 0);

		setTitle(majorName);

		initView();
		getData();
	}

	/**
	 * @param collegeString
	 *            大学名称
	 * @param major_id
	 *            专业id
	 * @param schedule_id
	 *            日程id
	 * */
	public static Intent newIntent(String collegeString, int major_id, int schedule_id) {
		Intent intent = new Intent(YiKaoApplication.getInstance(), ExaminationDetailActivity.class);
		intent.putExtra(BundleArgsConstants.MAJOR_NAME, collegeString);
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

		site_tv.setOnClickListener(this);

	}

	private void getData() {

		if (!isLogin()) {
			return;
		}

		showLoadingDialog();
		ObjectRequest<ExaminationSchedule> request = new ObjectRequest<ExaminationSchedule>(
				UrlConstants.SCHEDULE_DETAIL + "&mid=" + YiKaoApplication.getStudentId() + "&id=" + majorId + "&tid="
						+ scheduleId, new Listener<ExaminationSchedule>() {

					@Override
					public void onResponse(ExaminationSchedule response) {
						dismissLoadingDialog();
						if (response != null) {
							majorName = response.major;
							school_tv.setText(response.school);
							major_tv.setText(response.major);
							online_tv.setText(response.online);
							site_tv.setText(response.site);
							confirm_tv.setText(response.confirm);
							date_tv.setText(response.date);
							range_tv.setText(response.range);

						}
					}
				}, new TypeToken<ExaminationSchedule>() {
				}.getType());

		queue.add(request);
		queue.start();
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		if (resultCode == Activity.RESULT_OK) {
			Bundle bundle = data.getExtras();
			site_tv.setText(bundle.getString(BundleArgsConstants.KAODIAN_NAME));
			//考点id
			scheduleId = Integer.parseInt(bundle.getString(BundleArgsConstants.KAODIAN_ID));
			
			getData();
		}
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		// 考点
		case R.id.site_exam_detail_tv:
			Intent intent = KaoDianActivity.newIntent(majorId);
			startActivityForResult(intent,0);
			break;

		default:
			break;
		}
	}

}
