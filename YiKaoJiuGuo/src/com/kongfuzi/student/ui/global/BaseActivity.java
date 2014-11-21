package com.kongfuzi.student.ui.global;

import com.kongfuzi.lib.volley.RequestQueue;
import com.kongfuzi.student.support.YiKaoApplication;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * @author LBDL
 *
 */
public class BaseActivity extends FragmentActivity {
	
	private ActionBar actionBar; 
	private ToastDialogFragment toastDialogFragment;
	
	protected RequestQueue queue;
	
	private LoadingDialogFragment loadingDialogFragment;
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		
		actionBar = getActionBar();
		queue = YiKaoApplication.getQueueInstance();
		loadingDialogFragment = LoadingDialogFragment.getInstance();
		
	}
	
	protected void setTitle(String title) {
		actionBar.setTitle(title);
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
		loadingDialogFragment.show(getSupportFragmentManager(), "dialog");
	}
	
	protected void dismissLoadingDialog(){
		loadingDialogFragment.dismiss();
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		queue.stop();
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		
		queue.cancelAll(this);
	}

}
