package com.kongfuzi.student.ui.global;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import com.kongfuzi.student.support.utils.BundleArgsConstants;

/**
 * @author LBDL
 * @desc 提示dialog
 *
 */
public class ToastDialogFragment extends DialogFragment {
	
	private static ToastDialogFragment toastDialogFragment;
	
	private ToastDialogFragment(){
		
	}
	/**
	 * @param message
	 * 提示信息
	 * */
	public static ToastDialogFragment newInstance(String message){
		
		if (toastDialogFragment == null) {
			
			toastDialogFragment = new ToastDialogFragment();
		}
		Bundle bundle = new Bundle();
		bundle.putString(BundleArgsConstants.MESSAGE, message);
		
		toastDialogFragment.setArguments(bundle);
		return toastDialogFragment;
	}
	
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		
		String message = getArguments().getString(BundleArgsConstants.MESSAGE);
		
		return new AlertDialog.Builder(getActivity())
				.setMessage(message)
				.setPositiveButton("确定", new OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
//						((ToastDialogFragment)getActivity()).doPositiveClick();
					}
				}).create();
	}

}
