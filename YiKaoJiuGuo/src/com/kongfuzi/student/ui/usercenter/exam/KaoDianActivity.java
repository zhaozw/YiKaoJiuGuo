package com.kongfuzi.student.ui.usercenter.exam;

import java.util.List;

import com.google.gson.reflect.TypeToken;
import com.kongfuzi.lib.volley.Response.Listener;
import com.kongfuzi.student.R;
import com.kongfuzi.student.app.YiKaoApplication;
import com.kongfuzi.student.bean.KaoDian;
import com.kongfuzi.student.support.network.ArrayRequest;
import com.kongfuzi.student.support.utils.BundleArgsConstants;
import com.kongfuzi.student.support.utils.UrlConstants;
import com.kongfuzi.student.ui.adapter.KaoDianAdapter;
import com.kongfuzi.student.ui.global.BaseActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class KaoDianActivity extends BaseActivity implements OnItemClickListener{

	private ListView list_lv;

	private KaoDianAdapter adapter;

	/**
	 * @param רҵid
	 * 
	 * */
	public static Intent newIntent(int majorId) {

		Intent intent = new Intent(YiKaoApplication.getInstance(), KaoDianActivity.class);
		intent.putExtra(BundleArgsConstants.MAJOR_ID, majorId);

		return intent;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_kao_dian);

		list_lv = (ListView) findViewById(R.id.list_kao_dian_lv);

		list_lv.setOnItemClickListener(this);
		
		adapter = new KaoDianAdapter(this);
		list_lv.setAdapter(adapter);

		getData();
	}

	private void getData() {

		showLoadingDialog();
		ArrayRequest<List<KaoDian>> request = new ArrayRequest<List<KaoDian>>(UrlConstants.SCHEDULE_ADDRESS + "&id="
				+ getIntent().getIntExtra(BundleArgsConstants.MAJOR_ID, 0), new Listener<List<KaoDian>>() {

			@Override
			public void onResponse(List<KaoDian> response) {
				dismissLoadingDialog();
				if (response != null && !response.isEmpty()) {
					adapter.setList(response);
				}
			}
		}, new TypeToken<List<KaoDian>>() {
		}.getType());

		queue.add(request);
		queue.start();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		
		Object object = parent.getItemAtPosition(position);
		
		if (object != null && object instanceof KaoDian) {
			
			Intent intent = new Intent(this, getIntent().getClass());
			Bundle bundle = new Bundle();
			KaoDian kaoDian = (KaoDian) object;
			
			bundle.putString(BundleArgsConstants.KAODIAN_ID, kaoDian.id);
			bundle.putString(BundleArgsConstants.KAODIAN_NAME, kaoDian.address);
			
			intent.putExtras(bundle);
			setResult(Activity.RESULT_OK, intent);
			finish();
		}
	}
}
