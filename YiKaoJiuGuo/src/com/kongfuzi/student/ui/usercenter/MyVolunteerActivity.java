package com.kongfuzi.student.ui.usercenter;

import java.util.List;

import za.co.immedia.pinnedheaderlistview.PinnedHeaderListView;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.kongfuzi.lib.volley.Response.Listener;
import com.kongfuzi.student.R;
import com.kongfuzi.student.app.YiKaoApplication;
import com.kongfuzi.student.bean.Volunteer;
import com.kongfuzi.student.support.network.ArrayRequest;
import com.kongfuzi.student.support.utils.UrlConstants;
import com.kongfuzi.student.ui.adapter.MyVolunteerAdapter;
import com.kongfuzi.student.ui.global.BaseActivity;

/**
 * @author LBDL
 * @desc 我的志愿
 * 
 */
public class MyVolunteerActivity extends BaseActivity {

	private TextView count_tv;
	private ImageView empty_iv;
	private PinnedHeaderListView list_phlv;
	
	private static final String TAG = "MyVolunteerActivity";

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_my_volunteer);
		
		count_tv = (TextView) findViewById(R.id.count_my_volunteer_tv);
		list_phlv = (PinnedHeaderListView) findViewById(R.id.list_my_volunteer_phlv);
		empty_iv = (ImageView) findViewById(R.id.empty_kao_iv);

		getData();
	}

	private void getData() {

		if (!isLogin()) {
			return;
		}

		showLoadingDialog();
		ArrayRequest<List<Volunteer>> request = new ArrayRequest<List<Volunteer>>(UrlConstants.MY_VOLUNTEER + "&mid="
				+ YiKaoApplication.getStudentId(), new Listener<List<Volunteer>>() {

			@Override
			public void onResponse(List<Volunteer> response) {
				dismissLoadingDialog();
				
				if (response != null) {
					count_tv.setText("共报考" + response.size() + "个大学");
					if (response.isEmpty()) {
						list_phlv.setEmptyView(empty_iv);
					}else {
						MyVolunteerAdapter adapter = new MyVolunteerAdapter(MyVolunteerActivity.this, response);
						list_phlv.setAdapter(adapter);
						
					}
				}
			}
		}, new TypeToken<List<Volunteer>>() {
		}.getType());

		queue.add(request);
		queue.start();
	}

}
