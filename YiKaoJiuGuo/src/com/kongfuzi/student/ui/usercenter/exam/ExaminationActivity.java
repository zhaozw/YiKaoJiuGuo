package com.kongfuzi.student.ui.usercenter.exam;

import java.util.ArrayList;
import java.util.List;

import me.maxwin.view.IXListViewRefreshListener;
import me.maxwin.view.XListView;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
import com.kongfuzi.student.ui.global.BaseActivity;

/**
 * @author LBDL
 * @desc 考试日程
 * 
 */
public class ExaminationActivity extends BaseActivity implements IXListViewRefreshListener, OnItemClickListener {

	private TextView count_tv;
	private XListView list_xlv;

	private Boolean isLoading = false;

	private Context mContext;
	private ExaminationScheduleAdapter adapter;

	private List<ExaminationSchedule> list = new ArrayList<ExaminationSchedule>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_examination);

		mContext = this;

		count_tv = (TextView) findViewById(R.id.count_examination_tv);
		list_xlv = (XListView) findViewById(R.id.list_examination_xlv);

		list_xlv.setPullRefreshEnable(this);
		list_xlv.setOnItemClickListener(this);

		adapter = new ExaminationScheduleAdapter(mContext);
		list_xlv.setAdapter(adapter);

		initPullToRefresh();
	}

	// 初始化下拉刷新
	private void initPullToRefresh() {
		// 如果没有正在加载
		if (isLoading == false) {
			isLoading = true;
			// 开始刷新
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
						if (response != null && !response.isEmpty()) {
							count_tv.setText("共有" + response.size() + "场考试");
							adapter.setList(response);
						}
					}
				}, new TypeToken<List<ExaminationSchedule>>() {}.getType());

		queue.add(request);
		queue.start();
	}

	@Override
	public void onRefresh() {
		getData();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View convertView, int position, long id) {

		startActivity(ExaminationDetailActivity.newIntent(list.get(position - 1).major_id,
				list.get(position - 1).schedules.id));
	}

}
