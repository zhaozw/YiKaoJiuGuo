package com.kongfuzi.student.ui.kao.filter;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.google.gson.reflect.TypeToken;
import com.kongfuzi.lib.volley.Response.Listener;
import com.kongfuzi.student.R;
import com.kongfuzi.student.app.YiKaoApplication;
import com.kongfuzi.student.bean.Conditions;
import com.kongfuzi.student.support.network.ArrayRequest;
import com.kongfuzi.student.support.utils.BundleArgsConstants;
import com.kongfuzi.student.ui.adapter.CategoryAdapter;
import com.kongfuzi.student.ui.global.BaseActivity;
import com.kongfuzi.student.ui.kao.HomeActivity;

/**
 * @author LBDL
 * @desc 一级分类
 * 
 */
public class FirstCategoryActivity extends BaseActivity {

	private ListView list_lv;
	
	private static final String TAG = "FirstCategoryActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first_category);


		getData();
		onItemListener();

	}

	public static Intent newIntent(String url, String title, int index) {
		Intent intent = new Intent(YiKaoApplication.getInstance(), FirstCategoryActivity.class);
		intent.putExtra(BundleArgsConstants.URL, url);
		intent.putExtra(BundleArgsConstants.TITLE, title);
		intent.putExtra(BundleArgsConstants.INDEX, index);

		return intent;
	}

	private void getData() {
		showLoadingDialog();
		ArrayRequest<List<Conditions>> request = new ArrayRequest<List<Conditions>>(getIntent().getStringExtra(
				BundleArgsConstants.URL), new Listener<List<Conditions>>() {

			@Override
			public void onResponse(List<Conditions> response) {
				dismissLoadingDialog();
				Conditions conditions = new Conditions();
				conditions.id = 0;
				conditions.ename = "全部";
				response.add(0, conditions);
				list_lv.setAdapter(new CategoryAdapter(FirstCategoryActivity.this, response));

			}
		}, new TypeToken<List<Conditions>>() {
		}.getType());

		queue.add(request);
		queue.start();
	}
	
	private void onItemListener(){
		
		list_lv = (ListView) findViewById(R.id.list_first_category_lv);
		//设置筛选条件分类title
		setTitle(getIntent().getStringExtra(BundleArgsConstants.TITLE));
		
		list_lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Object object = parent.getItemAtPosition(position);
				
				if (object != null && object instanceof Conditions) {
					
					Intent intent = new Intent();
					Conditions conditions = (Conditions) object;
					Log.i(TAG, "position = " + getIntent().getIntExtra(BundleArgsConstants.INDEX, 0));
					//选择的条件添加到list去
					YiKaoApplication.getConditionsList().set(getIntent().getIntExtra(BundleArgsConstants.INDEX, 0), conditions);
					
					if (BundleArgsConstants.MAJOR == getIntent().getIntExtra(BundleArgsConstants.INDEX, -1)) {
						//录取方式
						if (position == 0) {
							backHome(conditions);
						} else {
							intent.setClass(FirstCategoryActivity.this, SecondCategoryActivity.class);
							intent.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
							intent.putExtra(BundleArgsConstants.TITLE, conditions.ename);
							intent.putExtra(BundleArgsConstants.CATEGORY_ID, conditions.id);
							startActivity(intent);
						}
					}else {
						backHome(conditions);
					}
					
				}

			}
		});
	}
	
	private void backHome(Conditions conditions) {
		Intent intent = new Intent();
		Bundle bundle = new Bundle();
		bundle.putSerializable(BundleArgsConstants.CONDITIONS, conditions);
		
		intent.setClass(this, HomeActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		intent.putExtras(bundle);
		startActivity(intent);

	}

	/**
	 * 第一次进入时走onCreate，不会走onNewIntent
	 * */
	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		
		getData();
		onItemListener();
	}

}
