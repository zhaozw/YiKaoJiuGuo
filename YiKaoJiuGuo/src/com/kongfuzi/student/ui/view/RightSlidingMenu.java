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
 * @desc ɸѡ����
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
		//���
		case R.id.clear_sliding_menu_right_btn:
			
			break;
		//ȷ��
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
	 * 0:����   1:רҵ���� 2:¼ȡ��ʽ 3:��������  4:��ѧ���ڵ�5:�������   6:��Դ��  7:ʡ�ⰴ����¼ȡ��ѧ
	 */

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int p, long id) {
//		callBacks.onRightSlidingMenuItemClicked(position);
		
		position = p;
		
		String url = null;
		Intent intent = null;
		
		switch (position) {
		//רҵ����
		case 1:
			url = UrlConstants.FIRST_MAJOR_CATEGORY;
			intent = FirstCategoryActivity.newIntent(url, "רҵ����",BundleArgsConstants.MAJOR);
			break;
		//¼ȡ��ʽ
		case 2:
			url = UrlConstants.MATRICULATE_MODE;
			intent = FirstCategoryActivity.newIntent(url, "¼ȡ��ʽ",BundleArgsConstants.MODE);
			break;
		//��������
		case 3:
			url = UrlConstants.BATCH;
			
			intent = FirstCategoryActivity.newIntent(url, "��������",BundleArgsConstants.BATCH);
			break;
		//��ѧ���ڵ�
		case 4:
			url = UrlConstants.PROVINCE;
			intent = FirstCategoryActivity.newIntent(url, "��ѧ���ڵ�",BundleArgsConstants.COLLEGE_SITE);
			
			break;
		//�������
		case 5:
			url = UrlConstants.PROPERTY;
			intent = FirstCategoryActivity.newIntent(url, "�������",BundleArgsConstants.PROPERTY);
			
			break;
		//��Դ��
		case 6:
			url = UrlConstants.PROVINCE;
			intent = FirstCategoryActivity.newIntent(url, "��Դ��",BundleArgsConstants.ORIGIN_SITE);
			
			break;

		default:
			break;
		}
		
		if (url != null && intent != null) {
			activity.startActivity(intent);
		}
	}

}
