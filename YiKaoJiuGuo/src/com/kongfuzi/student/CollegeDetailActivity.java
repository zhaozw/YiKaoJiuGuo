package com.kongfuzi.student;

import com.kongfuzi.student.ui.kao.college.PlanFragment;
import com.kongfuzi.student.ui.kao.college.RegulationsFragment;
import com.kongfuzi.student.ui.kao.college.RulesFragment;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author LBDL
 * @desc ��ѧ����
 *
 */
public class CollegeDetailActivity extends FragmentActivity implements OnClickListener{
	
	private TextView imgtotal_tv;
	private ImageView avatar_iv;
	private TextView property_tv;
	private TextView site_tv;
	private TextView province_tv;
	private TextView regulation_tv;
	private TextView plan_tv;
	private TextView rule_tv;
	
	private RegulationsFragment regulationsFragment = null;
	private PlanFragment planFragment = null;
	private RulesFragment rulesFragment = null;

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

		avatar_iv.setOnClickListener(this);
		regulation_tv.setOnClickListener(this);
		plan_tv.setOnClickListener(this);
		rule_tv.setOnClickListener(this);
	}
	
	/**
	 * �����е�Fragment����Ϊ����״̬��
	 * 
	 * @param transaction
	 *  ���ڶ�Fragmentִ�в���������
	 */
	
	private void hideFragments(FragmentTransaction transaction) {
		
		if (regulationsFragment != null) {
			transaction.hide(regulationsFragment);
		}
		if (rulesFragment != null) {
			transaction.hide(rulesFragment);
		}
		if (planFragment != null) {
			transaction.hide(planFragment);
		}
	}
	
	/**
	 * ���ݴ����index����������ѡ�е�tabҳ��
	 * @param i 
	 * 0:�������� 1:�����ƻ� 2: ¼ȡԭ��
	 * 
	 * */
	private void setTabSelection(int i){
		

		
		// ÿ��ѡ��֮ǰ��������ϴε�ѡ��״̬
		clearSelection();
		FragmentManager fragmentManager = getSupportFragmentManager();
		// ����һ��Fragment����
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		// �����ص����е�Fragment���Է�ֹ�ж��Fragment��ʾ�ڽ����ϵ����
		hideFragments(transaction);
		
		switch (i) {
		//��������
		case 0:
			
			regulation_tv.setTextColor(getResources().getColor(R.color.white));
			regulation_tv.setBackgroundColor(getResources().getColor(R.color.green));
			
			if (regulationsFragment == null) {
				// ���MessageFragmentΪ�գ��򴴽�һ������ӵ�������
//				regulationsFragment = RegulationsFragment.getInstance();
				transaction.add(R.id.content_main_fl,regulationsFragment);
				
			} else {
				transaction.show(regulationsFragment);
			}
			break;
			//�����ƻ�
		case 1:
			
			plan_tv.setTextColor(getResources().getColor(R.color.white));
			plan_tv.setBackgroundColor(getResources().getColor(R.color.green));
			
			if (planFragment == null) {
//				planFragment = PlanFragment.getInstance();
				transaction.add(R.id.content_main_fl, planFragment);
				
			} else {
				
				transaction.show(planFragment);
			}
			break;
		//¼ȡԭ��
		case 2:
			
			rule_tv.setBackgroundColor(getResources().getColor(R.color.green));
			rule_tv.setTextColor(getResources().getColor(R.color.white));
			
			if (rulesFragment == null) {
				// ���NewsFragmentΪ�գ��򴴽�һ������ӵ�������
//				rulesFragment = RulesFragment.getInstance();
				transaction.add(R.id.content_main_fl, rulesFragment);
				
			} else {
				transaction.show(rulesFragment);
			}
			break;
		}
		transaction.commit();
	
	}
	
	/**
	 * ��������е�ѡ��״̬��
	 */
	private void clearSelection() {
		
		Resources resources = getResources();
		
		regulation_tv.setTextColor(resources.getColor(R.color.black));
		regulation_tv.setBackgroundColor(resources.getColor(R.color.light_grey));
		plan_tv.setTextColor(resources.getColor(R.color.black));
		plan_tv.setBackgroundColor(resources.getColor(R.color.light_grey));
		rule_tv.setTextColor(resources.getColor(R.color.grey));
		rule_tv.setBackgroundColor(resources.getColor(R.color.light_grey));
	}

	@Override
	public void onClick(View v) {
		
		switch (v.getId()) {
		//��������
		case R.id.regulation_college_detail_tv:
			setTabSelection(0);
			break;
		//�����ƻ�
		case R.id.plan_college_detail_tv:
			
			setTabSelection(1);
			break;
		//¼ȡԭ��
		case R.id.rule_college_detail_tv:
			
			setTabSelection(2);
			break;

		default:
			break;
		}
		
	}
}
