package com.kongfuzi.student.ui.usercenter.exam;

import java.util.List;

import me.maxwin.view.IXListViewRefreshListener;
import me.maxwin.view.XListView;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.kongfuzi.lib.volley.Response.Listener;
import com.kongfuzi.student.R;
import com.kongfuzi.student.app.YiKaoApplication;
import com.kongfuzi.student.bean.ExaminationSchedule;
import com.kongfuzi.student.support.network.ArrayRequest;
import com.kongfuzi.student.support.utils.UrlConstants;
import com.kongfuzi.student.ui.adapter.ExaminationScheduleAdapter;
import com.kongfuzi.student.ui.adapter.MyVolunteerAdapter;
import com.kongfuzi.student.ui.global.BaseActivity;
import com.kongfuzi.student.ui.usercenter.MyVolunteerActivity;

/**
 * @author LBDL
 * @desc �����ճ�
 * 
 */
public class ExaminationActivity extends BaseActivity implements IXListViewRefreshListener, OnItemClickListener {

	private TextView count_tv;
	private XListView list_xlv;
	private ImageView empty_iv;

	private Boolean isLoading = false;

	private Context mContext;
	private ExaminationScheduleAdapter adapter;

	private List<ExaminationSchedule> list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_examination);

		mContext = this;

		count_tv = (TextView) findViewById(R.id.count_examination_tv);
		list_xlv = (XListView) findViewById(R.id.list_examination_xlv);
		empty_iv = (ImageView) findViewById(R.id.empty_kao_iv);

		list_xlv.setPullRefreshEnable(this);
		list_xlv.setOnItemClickListener(this);

		adapter = new ExaminationScheduleAdapter(mContext);
		list_xlv.setAdapter(adapter);

		initPullToRefresh();
	}

	// ��ʼ������ˢ��
	private void initPullToRefresh() {
		// ���û�����ڼ���
		if (isLoading == false) {
			isLoading = true;
			// ��ʼˢ��
			list_xlv.startRefresh();
		} else {
			list_xlv.NotRefreshAtBegin();
		}
		isLoading = false;
	}

	private void getData() {

		if (!isLogin()) {
			return;
		}

		ArrayRequest<List<ExaminationSchedule>> request = new ArrayRequest<List<ExaminationSchedule>>(
				UrlConstants.EXAMINATION_SCHEDULE + "&mid=" + YiKaoApplication.getStudentId(),
				new Listener<List<ExaminationSchedule>>() {

					@Override
					public void onResponse(List<ExaminationSchedule> response) {
						list_xlv.stopRefresh();
						if (response != null) {
							count_tv.setText("����" + response.size() + "������");
							list = response;
							if (response.isEmpty()) {
								list_xlv.setEmptyView(empty_iv);
							} else {
								adapter.setList(response);

							}
						}
					}
				}, new TypeToken<List<ExaminationSchedule>>() {
				}.getType());

		queue.add(request);
		queue.start();
	}

	@Override
	public void onRefresh() {
		getData();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View convertView, int position, long id) {

		// startActivity(ExaminationDetailActivity.newIntent(list.get(position -
		// 1).major_id,
		// list.get(position - 1).schedules.id));
		if (!list.isEmpty()) {

			int majorId = list.get(position - 1).major_id;
			String collegeString = list.get(position - 1).school;
			int scheduleId = list.get(position - 1).schedules.id;
			Intent intent = ExaminationDetailActivity.newIntent(collegeString, majorId, scheduleId);
			startActivity(intent);
		}
	}

}
