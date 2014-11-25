//package com.kongfuzi.student.ui.usercenter.exam;
//
//import com.kongfuzi.yikaojiuguo.R;
//import com.kongfuzi.yikaojiuguo.ui.BaseActivity;
//import com.kongfuzi.yikaojiuguo.ui.view.LoadingDialog;
//
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.net.Uri;
//import android.os.Bundle;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.webkit.WebSettings;
//import android.webkit.WebView;
//import android.webkit.WebViewClient;
//import android.widget.Button;
//
///**
// * @author LBDL
// * @desc 考试须知
// *
// */
//public class RegistrationNoticeActivity extends BaseActivity implements OnClickListener{
//	
//	private WebView content_wv;
//	private Button submit_btn;
//	
//	private LoadingDialog dialog;
//	
//
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_registration_notice);
//		
//		dialog = new LoadingDialog(this);
//		
//		content_wv = (WebView) findViewById(R.id.content_registration_notice_wv);
//		submit_btn = (Button) findViewById(R.id.submit_registration_notice_btn);
//		
//		
//		
//		submit_btn.setOnClickListener(this);
//		
//		WebSettings webSettings = content_wv.getSettings();
//		webSettings.setJavaScriptEnabled(true); //支持js
//		content_wv.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);//取消滚动条
//		
//		content_wv.setWebViewClient(new WebViewClient(){
//			
//			@Override
//			public void onPageStarted(WebView view, String url, Bitmap favicon) {
//				super.onPageStarted(view, url, favicon);
//				//dialog.show();
//			}
//			@Override
//			public void onPageFinished(WebView view, String url) {
//				super.onPageFinished(view, url);
//				dialog.cancel();
//			}
//		});
//		dialog.show();
//		content_wv.loadUrl("file:///android_asset/index.html");
//		
//		
//		
//	}
//
//	@Override
//	public void onClick(View v) {
//		
//		switch (v.getId()) {
//		case R.id.submit_registration_notice_btn:
////			startActivity(new Intent(this, FillRegistrationInfoActivity.class));
//			Uri uri = Uri.parse("http://www.kongfuzi.com/index.php/archives/index");
//			Intent intent = new Intent(Intent.ACTION_VIEW,uri);
//			startActivity(intent);
//			break;
//
//		default:
//			break;
//		}
//	}
//
//
//}
