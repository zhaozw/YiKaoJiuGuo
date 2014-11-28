package com.kongfuzi.student.ui.global;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.kongfuzi.lib.volley.RequestQueue;
import com.kongfuzi.student.R;
import com.kongfuzi.student.app.YiKaoApplication;
import com.kongfuzi.student.support.utils.Util;
import com.kongfuzi.student.ui.view.LoadingDialog;
import com.nostra13.universalimageloader.core.ImageLoader;

public class BaseFragment extends Fragment {
	
	protected RequestQueue queue;
	protected ImageLoader imageLoader;
	
	private Dialog loadingDialog;
	
	protected ImageView empty_iv;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return super.onCreateView(inflater, container, savedInstanceState);
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		empty_iv = (ImageView) view.findViewById(R.id.empty_kao_iv);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		queue = YiKaoApplication.getQueueInstance();
		imageLoader = YiKaoApplication.getImageLoaderInstance();
		loadingDialog = LoadingDialog.getInstance(getActivity());
		
	}
	
	protected Boolean isLogin(){
		return Util.isLogin();
	}
	
	protected void toast(String message){
		ToastDialogFragment fragment = ToastDialogFragment.newInstance(message);
		
		fragment.show(getFragmentManager(), "dialog");
	}
	
	protected void showLoadingDialog() {
		loadingDialog.show();
	}
	
	protected void dismissLoadingDialog(){
		loadingDialog.dismiss();
	}

}
