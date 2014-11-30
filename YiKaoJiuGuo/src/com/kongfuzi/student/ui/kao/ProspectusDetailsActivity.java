package com.kongfuzi.student.ui.kao;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.kongfuzi.student.R;
import com.kongfuzi.student.app.YiKaoApplication;
import com.kongfuzi.student.support.utils.BundleArgsConstants;
import com.kongfuzi.student.support.utils.UrlConstants;
import com.kongfuzi.student.ui.global.BaseActivity;

/**
 * @author Administrator
 *@goal 招生详情中的ListView点击后进入的WebView页面
 */
public class ProspectusDetailsActivity extends BaseActivity{
	private ImageView backButton;
	private TextView titleTextView;
	private WebView webView;
	private String detailID,detailTitle;
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_prospectusdetails);
		Intent intent=getIntent();
		detailID=intent.getStringExtra(BundleArgsConstants.PROSPECTUSDETAILS_ID);
		detailTitle=intent.getStringExtra(BundleArgsConstants.PROSPECTUSDETAILS_TITLE);
		initView();
		
	}
	/**
	 * @param major_id
	 *            ,college_id
	 * 
	 * */
	public static Intent newIntent(String detailID, String detailTitle) {
		Intent intent = new Intent(YiKaoApplication.getInstance(), ProspectusDetailsActivity.class);
		intent.putExtra(BundleArgsConstants.PROSPECTUSDETAILS_ID, detailID);
		intent.putExtra(BundleArgsConstants.PROSPECTUSDETAILS_TITLE, detailTitle);
		return intent;
	}

	
	/**
	 *初始化View 
	 */
	private void initView() {
		backButton=(ImageView) findViewById(R.id.prospectus_detail_back_iv);
		titleTextView=(TextView) findViewById(R.id.prospectus_detail_title_tv);
		webView=(WebView) findViewById(R.id.prospectus_detail_webview);
		backButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		titleTextView.setText(detailTitle);
		webView.loadUrl(UrlConstants.ENROLL_GUIDE_WEBVIEWURL+"&id="+detailID);
	}
	
}
