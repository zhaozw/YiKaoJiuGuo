package com.kongfuzi.student.support.network;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

/**
 * @author LBDL
 * @desc 错误提示
 *
 */
public class ErrorDialog extends DialogFragment {
	
	private static ErrorDialog dialog = null;
	
	public static ErrorDialog getInstance() {

		if (dialog == null) {
			dialog = new ErrorDialog();
		}
		
		Bundle bundle = new Bundle();
		dialog.setArguments(bundle);
		
		return dialog;
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
			.setTitle("错误提示")
			.setMessage("网络请求失败,请检查您的网络或者下拉刷新重试!")
			.setPositiveButton("确定", null);
		
		return builder.create();
	}
	

}
