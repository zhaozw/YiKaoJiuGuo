package com.kongfuzi.student.ui.kao;

import java.util.ArrayList;
import java.util.List;

import android.R.integer;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kongfuzi.student.R;
import com.kongfuzi.student.ui.global.BaseActivity;
import com.kongfuzi.student.ui.message.MessageFragment;
import com.kongfuzi.student.ui.usercenter.UserCenterFragment;

/**
 * @author LBDL
 * @date 2014-11-17 12:52
 * @desc 首页面
 */
public class HomeActivity extends BaseActivity implements OnClickListener {

	private List<LinearLayout> tabLayoutList;
	private List<ImageView> tabImageList;
	private List<TextView> tabTextList;
	private List<Fragment> fragmentList;
	private boolean[] isTabAdded = {false, false, false};
	private int[] tabImageSelectedArray = { R.drawable.home_kao_selected, R.drawable.home_msg_selected,
			R.drawable.home_user_center_selected };
	private int[] tabImageUnSelectedArray = { R.drawable.home_kao_unselected, R.drawable.home_msg_unselected,
			R.drawable.home_user_center_unselected };
	private int curTab = 0;

	private FragmentManager fragmentManager;
	
	private static final String TAG = "HomeActivity";

	/**
	 * 回调接口 
	 * */
	public interface search {
		public void searchForResult();
		public void getIntent(Intent intent);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);
		// setBehindContentView(R.layout.menu_frame);

		// getSlidingMenu().setBehindWidth(300);

		initView();
		setTabSelection(0);
	}

	private void initView() {
		tabLayoutList = new ArrayList<LinearLayout>();
		tabLayoutList.add((LinearLayout) findViewById(R.id.kao_main_ll));
		tabLayoutList.add((LinearLayout) findViewById(R.id.msg_main_ll));
		tabLayoutList.add((LinearLayout) findViewById(R.id.user_center_main_ll));

		tabImageList = new ArrayList<ImageView>();
		tabImageList.add((ImageView) findViewById(R.id.kao_main_iv));
		tabImageList.add((ImageView) findViewById(R.id.msg_main_iv));
		tabImageList.add((ImageView) findViewById(R.id.user_center_main_iv));

		tabTextList = new ArrayList<TextView>();
		tabTextList.add((TextView) findViewById(R.id.kao_main_tv));
		tabTextList.add((TextView) findViewById(R.id.msg_main_tv));
		tabTextList.add((TextView) findViewById(R.id.user_center_main_tv));

		for (int i = 0; i < tabLayoutList.size(); i++) {
			tabLayoutList.get(i).setOnClickListener(this);
		}
		fragmentList = new ArrayList<Fragment>();
		fragmentList.add(KaoFragment.getInstance());
		fragmentList.add(MessageFragment.getInstance());
		fragmentList.add(UserCenterFragment.getInstance());
		fragmentManager = getSupportFragmentManager();
	}

	/**
	 * 根据传入的index参数来设置选中的tab页。
	 * 
	 * @param i
	 *            每个tab页对应的下标。0:报考 1:消息 2:我的
	 */
	private void setTabSelection(int index) {
		//重置底部tab button状态
		resetTabButtons();
		// 开启一个Fragment事务
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		hideFragments(transaction);
		tabImageList.get(index).setImageResource(tabImageSelectedArray[index]);
		tabTextList.get(index).setTextColor(getResources().getColor(R.color.green));
		if(!isTabAdded[index]){
			transaction.add(R.id.content_main_fl, fragmentList.get(index));
			isTabAdded[index] = true;
		}
		
		transaction.show(fragmentList.get(index));
		curTab = index;
		transaction.commit();
		fragmentManager.executePendingTransactions();
	}
	
	private void hideFragments(FragmentTransaction transaction){
		for(int i = 0; i < fragmentList.size(); i ++){
			transaction.hide(fragmentList.get(i));
		}
	}
	
	private void resetTabButtons(){
		for(int i = 0; i < tabLayoutList.size(); i ++){
			tabImageList.get(i).setImageResource(tabImageUnSelectedArray[i]);
			tabTextList.get(i).setTextColor(getResources().getColor(R.color.black));
		}
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		KaoFragment kaoFragment = (KaoFragment) fragmentList.get(0);
		if (curTab == 0 && event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
			kaoFragment.searchForResult();
			return true;
		}else{
			return super.dispatchKeyEvent(event);
		}
	}
	
	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		KaoFragment.getInstance().getIntent(intent);
		
	}

	@Override
	public void onClick(View v) {

		for (int i = 0; i < tabLayoutList.size(); i++) {
			if (tabLayoutList.get(i).getId() == v.getId()) {
				setTabSelection(i);
				break;
			}
		}
	}

}
