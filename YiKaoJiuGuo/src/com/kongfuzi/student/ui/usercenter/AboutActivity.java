//package com.kongfuzi.student.ui.usercenter;
//
//import android.os.Bundle;
//
//import com.kongfuzi.student.R;
//import com.kongfuzi.student.ui.BaseActivity;
//import com.umeng.analytics.MobclickAgent;
//
///**
// * @author LBDL
// * @desc 关于我们
// *
// */
//public class AboutActivity extends BaseActivity {
//	
//	public static final String TAG = "AboutActivity";
//	
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_about);
//	}
//	
//	@Override
//	protected void onResume() {
//		super.onResume();
//		MobclickAgent.onPageStart(TAG);
//	}
//	
//	@Override
//	protected void onPause() {
//		MobclickAgent.onPageEnd(TAG);
//		super.onPause();
//	}
//
//}
