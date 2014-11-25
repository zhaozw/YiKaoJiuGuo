package com.kongfuzi.student.ui.view;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.kongfuzi.student.R;
import com.kongfuzi.student.app.YiKaoApplication;
import com.kongfuzi.student.support.utils.BundleArgsConstants;
import com.kongfuzi.student.support.utils.UrlConstants;
import com.kongfuzi.student.ui.adapter.FilterAdapter;
import com.kongfuzi.student.ui.kao.KaoFragment;
import com.kongfuzi.student.ui.kao.filter.FirstCategoryActivity;

/**
 * @author LBDL
 * @desc 筛选条件
 *
 */
public class RightSlidingMenu extends SlidingMenu implements OnClickListener,OnItemClickListener{
	
	private Activity activity;
//	private CallBacks callBacks;
	
	private ListView list_lv;
	private Button clear_btn;
	private Button submit_btn;
	
	public int position;
	
	public FilterAdapter adapter;
	
	private static final String TAG = "RightSlidingMenu";
	

	public RightSlidingMenu(Activity activity, int resId) {
		super(activity);
		this.activity = activity;
		setMenu(resId);

		initView();
	}
	
//	public interface CallBacks{
//		
//		public void onRightSlidingMenuItemClicked(int position);
//		public void onRightSlidingMenuOnClicked(int id);
//	}

	private void initView() {
		
		
		list_lv = (ListView) findViewById(R.id.list_sliding_menu_right_lv);
		clear_btn = (Button) findViewById(R.id.clear_sliding_menu_right_btn);
		submit_btn = (Button) findViewById(R.id.submit_sliding_menu_right_btn);
		
		clear_btn.setOnClickListener(this);
		submit_btn.setOnClickListener(this);
		list_lv.setOnItemClickListener(this);
		
	    adapter = new FilterAdapter(activity);
		list_lv.setAdapter(adapter);
	}

	@Override
	public void onClick(View v) {
//		callBacks.onRightSlidingMenuOnClicked(v.getId());
		
		switch (v.getId()) {
		//清除
		case R.id.clear_sliding_menu_right_btn:
			
			break;
		//确定
		case R.id.submit_sliding_menu_right_btn:
			Log.i(TAG, "score = " + YiKaoApplication.getConditionsList().get(0).id);
			Log.i(TAG, "other = " + YiKaoApplication.getConditionsList().get(7).id);
			showContent();
			KaoFragment.getInstance().getRightData();
			break;

		default:
			break;
		}
	}
	
//	@Override
//	public void attachToActivity(Activity activity, int slideStyle) {
//		super.attachToActivity(activity, slideStyle);
//
//		if (!(activity instanceof CallBacks)) {
//			throw new IllegalStateException(
//					"the Activity which NavigationSlidingMenu belongs to must implement the Callbacks interface");
//		}
//		callBacks = (CallBacks) activity;
//	}
	
	/**
	 * @param position
	 * 0:估分   1:专业分类 2:录取方式 3:本科批次  4:大学所在地5:文理分类   6:生源地  7:省外按联考录取大学
	 */

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int p, long id) {
//		callBacks.onRightSlidingMenuItemClicked(position);
		
		position = p;
		
		String url = null;
		Intent intent = null;
		
		switch (position) {
		//专业分类
		case 1:
			url = UrlConstants.FIRST_MAJOR_CATEGORY;
			intent = FirstCategoryActivity.newIntent(url, "专业分类",BundleArgsConstants.MAJOR);
			break;
		//录取方式
		case 2:
			url = UrlConstants.MATRICULATE_MODE;
			intent = FirstCategoryActivity.newIntent(url, "录取方式",BundleArgsConstants.MODE);
			break;
		//本科批次
		case 3:
			url = UrlConstants.BATCH;
			
			intent = FirstCategoryActivity.newIntent(url, "本科批次",BundleArgsConstants.BATCH);
			break;
		//大学所在地
		case 4:
			url = UrlConstants.PROVINCE;
			intent = FirstCategoryActivity.newIntent(url, "大学所在地",BundleArgsConstants.COLLEGE_SITE);
			
			break;
		//文理分类
		case 5:
			url = UrlConstants.PROPERTY;
			intent = FirstCategoryActivity.newIntent(url, "文理分类",BundleArgsConstants.PROPERTY);
			
			break;
		//生源地
		case 6:
			url = UrlConstants.PROVINCE;
			intent = FirstCategoryActivity.newIntent(url, "生源地",BundleArgsConstants.ORIGIN_SITE);
			
			break;

		default:
			break;
		}
		
		if (url != null && intent != null) {
			activity.startActivity(intent);
		}
	}

}
