package com.kongfuzi.student.ui.view;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.kongfuzi.student.R;
import com.kongfuzi.student.support.utils.BundleArgsConstants;
import com.kongfuzi.student.support.utils.UrlConstants;
import com.kongfuzi.student.ui.adapter.FilterAdapter;
import com.kongfuzi.student.ui.kao.filter.FirstCategoryActivity;

/**
 * @author LBDL
 * @desc ɸѡ����
 *
 */
public class RightSlidingMenu extends SlidingMenu implements OnClickListener,OnItemClickListener{
	
	private Context context;
	
	private ListView list_lv;
	private Button clear_btn;
	private Button submit_btn;

	public RightSlidingMenu(Context context, int resId) {
		super(context);
		this.context = context;
		setMenu(resId);

		initView();
	}

	private void initView() {
		
		list_lv = (ListView) findViewById(R.id.list_sliding_menu_right_lv);
		clear_btn = (Button) findViewById(R.id.clear_sliding_menu_right_btn);
		submit_btn = (Button) findViewById(R.id.submit_sliding_menu_right_btn);
		
		clear_btn.setOnClickListener(this);
		submit_btn.setOnClickListener(this);
		list_lv.setOnItemClickListener(this);
		
		FilterAdapter adapter = new FilterAdapter(context);
		list_lv.setAdapter(adapter);
	}

	@Override
	public void onClick(View v) {
		
		switch (v.getId()) {
		
		case R.id.clear_sliding_menu_right_btn:
			
			break;
			
		case R.id.submit_sliding_menu_right_btn:
			
			break;

		default:
			break;
		}
	}
	
	/**
	 * @param position
	 * 0:����   1:רҵ���� 2:¼ȡ��ʽ 3:��������  4:��ѧ���ڵ�5:�������   6:��Դ��  7:ʡ�ⰴ����¼ȡ��ѧ
	 */

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		
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
			context.startActivity(intent);
		}
		
	}

}
