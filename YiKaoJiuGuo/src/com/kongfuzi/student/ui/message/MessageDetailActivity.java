package com.kongfuzi.student.ui.message;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.kongfuzi.student.R;
import com.kongfuzi.student.app.YiKaoApplication;
import com.kongfuzi.student.support.utils.BundleArgsConstants;
import com.kongfuzi.student.ui.global.BaseActivity;
import com.umeng.analytics.MobclickAgent;

/**
 * @author LBDL
 * @desc 消息详情
 *
 */
public class MessageDetailActivity extends BaseActivity {
	


	private String url;
	private WebView web_wv;

	public static final String TAG = "ProspectusDetailsActivity";
	
	public static Intent newIntent(String title,String url) {
		Intent intent = new Intent(YiKaoApplication.getInstance(), MessageDetailActivity.class);
		intent.putExtra(BundleArgsConstants.URL, url);
		intent.putExtra(BundleArgsConstants.TITLE, title);
		
		return intent;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_message_detail);
		
		Intent intent = getIntent();
		
		setTitle(intent.getStringExtra(BundleArgsConstants.TITLE)); 	
		web_wv = (WebView) findViewById(R.id.web_message_detail_wv);

		url = getIntent().getStringExtra(BundleArgsConstants.URL);
		Log.i(TAG, "url = " + url);
		
		// 支持JavaScript
		web_wv.getSettings().setJavaScriptEnabled(true);
		
		web_wv.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);//取消滚动条
		web_wv.loadUrl(url);
		web_wv.setWebViewClient(new WebViewClient(){
			
			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				super.onPageStarted(view, url, favicon);
				showLoadingDialog();
			}
			@Override
			public void onPageFinished(WebView view, String url) {
				super.onPageFinished(view, url);
				dismissLoadingDialog();
			}
		});
		
	}
	

	@Override
	protected void onResume() {
		super.onResume();
		MobclickAgent.onPageStart(TAG);
	}

	@Override
	protected void onPause() {
		MobclickAgent.onPageEnd(TAG);
		super.onPause();
	}



}
