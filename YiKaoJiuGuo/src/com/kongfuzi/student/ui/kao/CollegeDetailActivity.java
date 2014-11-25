package com.kongfuzi.student.ui.kao;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.kongfuzi.student.R;
import com.kongfuzi.student.app.YiKaoApplication;
import com.kongfuzi.student.support.utils.BundleArgsConstants;
import com.kongfuzi.student.ui.kao.college.PlanFragment;
import com.kongfuzi.student.ui.kao.college.RegulationsFragment;
import com.kongfuzi.student.ui.kao.college.RulesFragment;

/**
 * @author LBDL
 * @desc ��ѧ����
 * 
 */
public class CollegeDetailActivity extends FragmentActivity implements OnClickListener {

	private TextView imgtotal_tv;
	private ImageView avatar_iv;
	private TextView property_tv;
	private TextView site_tv;
	private TextView province_tv;
	private TextView regulation_tv;
	private TextView plan_tv;
	private TextView rule_tv;

	private FragmentManager fragmentManager;
	private List<TextView> tabList = new ArrayList<TextView>();
	private List<Fragment> fragmentsList = new ArrayList<Fragment>();
	
	public static Intent newIntent(int id) {
		
		Intent intent = new Intent(YiKaoApplication.getInstance(), CollegeDetailActivity.class);
		intent.putExtra(BundleArgsConstants.COLLEGE_ID, id);
		
		return intent;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_college_detail);

		initView();
		setTabSelection(0);
	}

	private void initView() {
		avatar_iv = (ImageView) findViewById(R.id.image_college_details_iv);
		imgtotal_tv = (TextView) findViewById(R.id.imgtotal_college_details_tv);
		property_tv = (TextView) findViewById(R.id.property_college_details_tv);
		site_tv = (TextView) findViewById(R.id.site_college_details_tv);
		province_tv = (TextView) findViewById(R.id.province_college_details_tv);
		
		regulation_tv = (TextView) findViewById(R.id.regulation_college_detail_tv);
		plan_tv = (TextView) findViewById(R.id.plan_college_detail_tv);
		rule_tv = (TextView) findViewById(R.id.rule_college_detail_tv);

		tabList.add((TextView) findViewById(R.id.regulation_college_detail_tv));
		tabList.add((TextView) findViewById(R.id.plan_college_detail_tv));
		tabList.add((TextView) findViewById(R.id.rule_college_detail_tv));

		fragmentManager = getSupportFragmentManager();
		//��ѧid
		int collegeId = getIntent().getIntExtra(BundleArgsConstants.CATEGORY_ID, 0);
		
		fragmentsList.add(RegulationsFragment.getInstance(collegeId));
		fragmentsList.add(RulesFragment.getInstance(collegeId));
		fragmentsList.add(PlanFragment.getInstance(collegeId));

		avatar_iv.setOnClickListener(this);

		for (int i = 0; i < tabList.size(); i++) {
			tabList.get(i).setOnClickListener(this);
		}
		regulation_tv.setOnClickListener(this);
		plan_tv.setOnClickListener(this);
		rule_tv.setOnClickListener(this);
	}

	/**
	 * ���ݴ����index����������ѡ�е�tabҳ��
	 * 
	 * @param i
	 *            0:�������� 1:�����ƻ� 2: ¼ȡԭ��
	 * 
	 * */
	private void setTabSelection(int i) {

		// ����һ��Fragment����
		FragmentTransaction transaction = fragmentManager.beginTransaction();

		for (int j = 0; j < fragmentsList.size(); j++) {

			Resources resources = getResources();
			TextView textView = tabList.get(j);

			if (i == j) {
				// ѡ�е�tab �ı���������ɫ �ͱ���ɫ
				textView.setBackgroundColor(resources.getColor(R.color.green));
				textView.setTextColor(resources.getColor(R.color.white));

				// ��ʾ��fragment
				transaction.show(fragmentsList.get(j));

			} else {
				// δѡ�е�tab �ָ���Ĭ��
				textView.setBackgroundColor(resources.getColor(R.color.light_grey));
				textView.setTextColor(resources.getColor(R.color.black));

				// ����δѡ�е�fragment
				transaction.hide(fragmentsList.get(j));
			}
		}

		transaction.commit();

	}

	@Override
	public void onClick(View v) {

		for (int i = 0; i < tabList.size(); i++) {

			if (v.getId() == tabList.get(i).getId()) {
				setTabSelection(i);
			}
		}

	}
}
