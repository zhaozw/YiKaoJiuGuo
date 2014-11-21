package com.kongfuzi.student.support.network;

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
			.setTitle("������ʾ")
			.setMessage("��������ʧ��,�������������������ˢ������!")
			.setPositiveButton("ȷ��", null);
		
		return builder.create();
	}
	

}
