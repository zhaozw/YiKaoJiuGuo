package com.kongfuzi.student.ui.kao;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

import com.kongfuzi.student.R;
import com.kongfuzi.student.app.YiKaoApplication;
import com.kongfuzi.student.support.utils.BundleArgsConstants;
import com.kongfuzi.student.support.utils.UrlConstants;
import com.kongfuzi.student.ui.global.BaseActivity;

/**
 * @author LBDL
 * @desc 招生计划
 *
 */
public class EnrollPlay extends BaseActivity{

	private WebView playWebView;
	private String college; 
	private String id;
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_enrollplay);
		
		InitView();
	}
	/**
	 * @param id  大学id
	 * */
	public static Intent newIntent(String id,String college) {
		Intent intent = new Intent(YiKaoApplication.getInstance(), EnrollPlay.class);
		intent.putExtra(BundleArgsConstants.ENROLL_PLAY_ID, id);
		intent.putExtra(BundleArgsConstants.ENROLL_PLAY_COLLEGE, college);
		return intent;
	}

	/**
	 *初始化View 
	 */
	private void InitView() {
		Intent intent=getIntent();
		id=intent.getStringExtra(BundleArgsConstants.ENROLL_PLAY_ID);
		college=intent.getStringExtra(BundleArgsConstants.ENROLL_PLAY_COLLEGE);
		
		playWebView=(WebView) findViewById(R.id.play_wv);
		
		setTitle(college);
		playWebView.loadUrl(UrlConstants.ENROLL_PLAY+id);
	}
}
