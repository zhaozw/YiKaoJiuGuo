package com.kongfuzi.student.ui.kao;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.kongfuzi.student.R;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * @author LBDL
 * @date 2014-11-17 12:52
 * @desc ��ҳ��
 */
public class HomeActivity extends SlidingFragmentActivity implements OnClickListener{

	private LinearLayout kao_ll;
	private LinearLayout msg_ll;
	private LinearLayout user_center_ll;

	private ImageView kao_iv;
	private ImageView msg_iv;
	private ImageView user_center_iv;

	private TextView kao_tv;
	private TextView msg_tv;
	private TextView user_center_tv;
	
	private KaoFragment kaoFragment = null;
	private MessageFragment messageFragment = null;
	private UserCenterFragment userCenterFragment = null;

	private FragmentManager fragmentManager;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setBehindContentView(R.layout.menu_frame);

		initView();

		fragmentManager = getSupportFragmentManager();
		
		setTabSelection(0);
	}
	

	private void initView() {

		kao_ll = (LinearLayout) findViewById(R.id.kao_main_ll);
		msg_ll = (LinearLayout) findViewById(R.id.msg_main_ll);
		user_center_ll = (LinearLayout) findViewById(R.id.user_center_main_ll);

		kao_iv = (ImageView) findViewById(R.id.kao_main_iv);
		msg_iv = (ImageView) findViewById(R.id.msg_main_iv);
		user_center_iv = (ImageView) findViewById(R.id.user_center_main_iv);

		kao_tv = (TextView) findViewById(R.id.kao_main_tv);
		msg_tv = (TextView) findViewById(R.id.msg_main_tv);
		user_center_tv = (TextView) findViewById(R.id.user_center_main_tv);
		
		kao_ll.setOnClickListener(this);
		msg_ll.setOnClickListener(this);
		user_center_ll.setOnClickListener(this);
	}
	
	/**
	 * ���ݴ����index����������ѡ�е�tabҳ��
	 * 
	 * @param i
	 * ÿ��tabҳ��Ӧ���±ꡣ0:���� 1:��Ϣ 2:�ҵ�
	 */
	private void setTabSelection(int i) {
		
		// ÿ��ѡ��֮ǰ��������ϴε�ѡ��״̬
		clearSelection();
		// ����һ��Fragment����
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		// �����ص����е�Fragment���Է�ֹ�ж��Fragment��ʾ�ڽ����ϵ����
		hideFragments(transaction);
		switch (i) {
		case 0:
			// ������˱���tabʱ���ı�ؼ���ͼƬ��������ɫ
			kao_iv.setImageResource(R.drawable.home_kao_selected);
			kao_tv.setTextColor(getResources().getColor(R.color.green));
			if (kaoFragment == null) {
				// ���MessageFragmentΪ�գ��򴴽�һ������ӵ�������
				kaoFragment = KaoFragment.getInstance();
				transaction.add(R.id.content_main_fl,kaoFragment);
				
			} else {
				transaction.show(kaoFragment);
			}
			break;
		case 1:
			
			msg_iv.setImageResource(R.drawable.home_msg_selected);
			msg_tv.setTextColor(getResources().getColor(R.color.green));
			if (messageFragment == null) {
				messageFragment = MessageFragment.getInstance();
				transaction.add(R.id.content_main_fl, messageFragment);
				
			} else {
				
				transaction.show(messageFragment);
			}
			break;
		case 2:
			user_center_iv.setImageResource(R.drawable.home_user_center_selected);
			user_center_tv.setTextColor(getResources().getColor(R.color.green));
			if (userCenterFragment == null) {
				// ���NewsFragmentΪ�գ��򴴽�һ������ӵ�������
				userCenterFragment = UserCenterFragment.getInstance();
				transaction.add(R.id.content_main_fl, userCenterFragment);
				
			} else {
				transaction.show(userCenterFragment);
			}
			break;
		}
		transaction.commit();
	}
	
	/**
	 * ��������е�ѡ��״̬��
	 */
	private void clearSelection() {
		kao_iv.setImageResource(R.drawable.home_kao_unselected);
		kao_tv.setTextColor(getResources().getColor(R.color.grey));
		msg_iv.setImageResource(R.drawable.home_msg_unselected);
		msg_tv.setTextColor(getResources().getColor(R.color.grey));
		user_center_iv.setImageResource(R.drawable.home_user_center_unselected);
		user_center_tv.setTextColor(getResources().getColor(R.color.grey));
	}

	/**
	 * �����е�Fragment����Ϊ����״̬��
	 * 
	 * @param transaction
	 *            ���ڶ�Fragmentִ�в���������
	 */
	private void hideFragments(FragmentTransaction transaction) {
		
		if (kaoFragment != null) {
			transaction.hide(kaoFragment);
		}
		if (messageFragment != null) {
			transaction.hide(messageFragment);
		}
		if (userCenterFragment != null) {
			transaction.hide(userCenterFragment);
		}
	}

	
	@Override
	public void onClick(View v) {
		
		switch (v.getId()) {
		
		//����
		case R.id.kao_main_ll:
			setTabSelection(0);
			break;
		//��Ϣ
		case R.id.msg_main_ll:
			
			setTabSelection(1);
			break;
		//�ҵ�
		case R.id.user_center_main_ll:
			
			setTabSelection(2);
			break;

		default:
			break;
		}
	}

}
