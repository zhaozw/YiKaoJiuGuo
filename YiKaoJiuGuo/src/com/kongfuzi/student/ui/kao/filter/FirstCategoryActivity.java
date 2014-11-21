package com.kongfuzi.student.ui.kao.filter;

import java.util.List;

import com.google.gson.reflect.TypeToken;
import com.kongfuzi.lib.volley.RequestQueue;
import com.kongfuzi.lib.volley.Response.Listener;
import com.kongfuzi.student.R;
import com.kongfuzi.student.bean.Conditions;
import com.kongfuzi.student.bean.Filter;
import com.kongfuzi.student.support.YiKaoApplication;
import com.kongfuzi.student.support.network.ArrayRequest;
import com.kongfuzi.student.support.utils.BundleArgsConstants;
import com.kongfuzi.student.ui.adapter.CategoryAdapter;
import com.kongfuzi.student.ui.global.BaseActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

/**
 * @author LBDL
 * @desc 一级分类
 * 
 */
public class FirstCategoryActivity extends BaseActivity {

	private ListView list_lv;

	private RequestQueue queue;

	// private LoadingDialog dialog = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first_category);

		// dialog = LoadingDialog.getInstance();
		queue = YiKaoApplication.getQueueInstance();

		list_lv = (ListView) findViewById(R.id.list_first_category_lv);

		setTitle(getIntent().getStringExtra(BundleArgsConstants.TITLE));

		getData();

		list_lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Log.i("FirstCategory", "fasdfadsf");
				//TODO  object == null
				Object object = parent.getItemAtPosition(position);

				if (position == getIntent().getIntExtra(BundleArgsConstants.INDEX, -1) && object != null
						&& object instanceof Conditions) {
					
					Conditions conditions = (Conditions) object;

					Intent intent = new Intent(FirstCategoryActivity.this, SecondCategoryActivity.class);
					intent.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
					intent.putExtra(BundleArgsConstants.TITLE, conditions.ename);
					startActivity(intent);
				}

			}
		});

	}

	public static Intent newIntent(String url, String title, int index) {
		Intent intent = new Intent(YiKaoApplication.getInstance(), FirstCategoryActivity.class);
		intent.putExtra(BundleArgsConstants.URL, url);
		intent.putExtra(BundleArgsConstants.TITLE, title);
		intent.putExtra(BundleArgsConstants.INDEX, index);

		return intent;
	}

	private void getData() {
		// dialog.show();
		ArrayRequest<List<Conditions>> request = new ArrayRequest<List<Conditions>>(getIntent().getStringExtra(
				BundleArgsConstants.URL), new Listener<List<Conditions>>() {

			@Override
			public void onResponse(List<Conditions> response) {
				// dialog.dismiss();
				list_lv.setAdapter(new CategoryAdapter(FirstCategoryActivity.this, response));

			}
		}, new TypeToken<List<Conditions>>() {
		}.getType());

		queue.add(request);
		queue.start();
	}

	/**
	 * 第一次进入时走onCreate，不会走onNewIntent
	 * */
	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
	}

}
