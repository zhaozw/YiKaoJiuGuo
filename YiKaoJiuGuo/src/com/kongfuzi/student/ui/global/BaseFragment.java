package com.kongfuzi.student.ui.global;

import com.kongfuzi.lib.volley.RequestQueue;
import com.kongfuzi.student.support.YiKaoApplication;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class BaseFragment extends Fragment {
	
	protected RequestQueue queue;
	
	private LoadingDialogFragment loadingDialogFragment;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return super.onCreateView(inflater, container, savedInstanceState);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		queue = YiKaoApplication.getQueueInstance();
		loadingDialogFragment = LoadingDialogFragment.getInstance();
	}
	
	protected void toast(String message){
		ToastDialogFragment fragment = ToastDialogFragment.newInstance(message);
		
		fragment.show(getFragmentManager(), "dialog");
	}
	
	protected void showLoadingDialog() {
		loadingDialogFragment.show(getFragmentManager(), "dialog");
	}
	
	protected void dismissLoadingDialog(){
		loadingDialogFragment.dismiss();
	}

}
