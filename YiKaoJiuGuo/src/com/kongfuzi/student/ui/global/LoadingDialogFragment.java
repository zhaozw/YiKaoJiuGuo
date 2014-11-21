package com.kongfuzi.student.ui.global;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

/**
 * @author LBDL
 * @desc 正在加载dialog
 *
 */
public class LoadingDialogFragment extends DialogFragment {
	
	private ProgressDialog progressDialog = null;
	private static LoadingDialogFragment dialog = null;

	public static LoadingDialogFragment getInstance() {

		if (dialog == null) {
			dialog = new LoadingDialogFragment();
		}
		
		Bundle bundle = new Bundle();
		dialog.setArguments(bundle);
		
		
		return dialog;
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		
//		progressDialog = ProgressDialog.show(getActivity(), "Loading...", "努力加载中...", true, false);
		progressDialog = new ProgressDialog(getActivity());
		progressDialog.setTitle("Loading...");
		progressDialog.setMessage("努力加载中...");
		progressDialog.setIndeterminate(true);
		progressDialog.setCancelable(false);
		
		return super.onCreateDialog(savedInstanceState);
	}
	
	public void show(){
		progressDialog.show();
	}
	
	public void cancel(){
		progressDialog.dismiss();
	}
}
