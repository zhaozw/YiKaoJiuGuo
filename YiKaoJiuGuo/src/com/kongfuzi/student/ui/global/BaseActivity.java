package com.kongfuzi.student.ui.global;

import android.app.ActionBar;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.kongfuzi.lib.volley.RequestQueue;
import com.kongfuzi.student.app.YiKaoApplication;
import com.kongfuzi.student.support.utils.Util;
import com.kongfuzi.student.ui.view.LoadingDialog;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * @author LBDL
 *
 */
public class BaseActivity extends FragmentActivity {
	
	private ActionBar actionBar; 
	private ToastDialogFragment toastDialogFragment;
	
	protected RequestQueue queue;
	protected ImageLoader imageLoader;
	
	private LoadingDialog loadingDialog;
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		
		actionBar = getActionBar();
		queue = YiKaoApplication.getQueueInstance();
		imageLoader = YiKaoApplication.getImageLoaderInstance();
		loadingDialog = LoadingDialog.getInstance(this);
		
	}
	
	protected void setTitle(String title) {
		actionBar.setTitle(title);
	}
	
	protected Boolean isLogin() {
		return Util.isLogin();
	}
	
	/**
	 * Ã· ædialogFragment
	 * @param message
	 * 
	 * */
	protected void toast(String message){
		toastDialogFragment = ToastDialogFragment.newInstance(message);
		toastDialogFragment.show(getSupportFragmentManager(), "dialog");
	}
	
	protected void showLoadingDialog() {
		loadingDialog.show();
	}
	
	protected void dismissLoadingDialog(){
		loadingDialog.dismiss();
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		queue.stop();
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		
		if (loadingDialog.isShowing()) {
			queue.cancelAll(this);
		}
	}

}
