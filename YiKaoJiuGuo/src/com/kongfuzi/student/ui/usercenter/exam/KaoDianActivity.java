package com.kongfuzi.student.ui.usercenter.exam;

import java.util.List;

import com.google.gson.reflect.TypeToken;
import com.kongfuzi.lib.volley.Response.Listener;
import com.kongfuzi.student.R;
import com.kongfuzi.student.R.id;
import com.kongfuzi.student.R.layout;
import com.kongfuzi.student.app.YiKaoApplication;
import com.kongfuzi.student.bean.KaoDian;
import com.kongfuzi.student.support.network.ArrayRequest;
import com.kongfuzi.student.support.utils.BundleArgsConstants;
import com.kongfuzi.student.support.utils.UrlConstants;
import com.kongfuzi.student.ui.adapter.KaoDianAdapter;
import com.kongfuzi.student.ui.global.BaseActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

public class KaoDianActivity extends BaseActivity {
	
	private ListView list_lv;
	
	private KaoDianAdapter adapter;
	
	
	/**
	 * @param רҵid 
	 * 
	 * */
	public static Intent newIntent(int majorId){
		
		Intent intent = new Intent(YiKaoApplication.getInstance(), KaoDianActivity.class);
		intent.putExtra(BundleArgsConstants.MAJOR_ID, majorId);
		
		return intent;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_kao_dian);
		
		list_lv = (ListView) findViewById(R.id.list_kao_dian_lv);
		
		adapter = new KaoDianAdapter(this);
		
		list_lv.setAdapter(adapter);
		
		getData();
	}

	private void getData() {
		
		showLoadingDialog();
		ArrayRequest<List<KaoDian>> request = new ArrayRequest<List<KaoDian>>(UrlConstants.SCHEDULE_ADDRESS + getIntent().getIntExtra(BundleArgsConstants.MAJOR_ID, 0), new Listener<List<KaoDian>>() {

			@Override
			public void onResponse(List<KaoDian> response) {
				dismissLoadingDialog();
				if (response != null && !response.isEmpty()) {
					adapter.setList(response);
				}
			}
		}, new TypeToken<List<KaoDian>>(){}.getType());
		
		queue.add(request);
		queue.start();
	}
}
