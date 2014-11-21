package com.kongfuzi.student.ui.kao.filter;

import com.kongfuzi.student.R;
import com.kongfuzi.student.support.utils.BundleArgsConstants;
import com.kongfuzi.student.ui.global.PopupWindowActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.app.Activity;
import android.content.Intent;

/**
 * @author LBDL
 * @desc 二级分类
 *
 */
public class SecondCategoryActivity extends Activity {
	
	private ListView list_lv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first_category);
		
		setTitle(getIntent().getStringExtra(BundleArgsConstants.TITLE));
		
		list_lv = (ListView) findViewById(R.id.list_first_category_lv);
		
		list_lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
				
				Intent intent = new Intent(SecondCategoryActivity.this,PopupWindowActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
				startActivity(intent);
				
			}
		});
	
	}
	
	/**
	 * 第一次进入时走onCreate，不会走onNewIntent
	 * */
	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
	}


}
