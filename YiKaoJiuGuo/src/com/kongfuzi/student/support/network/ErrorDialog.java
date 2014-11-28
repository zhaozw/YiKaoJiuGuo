package com.kongfuzi.student.support.network;

import com.kongfuzi.student.support.utils.BundleArgsConstants;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

/**
 * @author LBDL
 * @desc ������ʾ
 *
 */
public class ErrorDialog extends DialogFragment {
	
	private static ErrorDialog dialog = null;
	
	
	public static ErrorDialog getInstance(String message) {

		if (dialog == null) {
			dialog = new ErrorDialog();
		}
		
		Bundle bundle = new Bundle();
		bundle.putString(BundleArgsConstants.ERROR_MESSAGE, message);
		try{
			dialog.setArguments(bundle);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		
		return dialog;
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		String errorMessageString = getArguments().getString(BundleArgsConstants.ERROR_MESSAGE);
		if(errorMessageString == null){
			errorMessageString = "�������Ӵ���,��������!";
		}
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
			.setTitle("������ʾ")
			.setMessage(errorMessageString)
			.setPositiveButton("ȷ��", null);
		
		return builder.create();
	}
	

}
