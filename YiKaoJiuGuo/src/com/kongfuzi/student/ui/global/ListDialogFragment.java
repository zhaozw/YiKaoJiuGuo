package com.kongfuzi.student.ui.global;

import java.util.List;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import com.kongfuzi.student.bean.Conditions;

/**
 * @author LBDL
 * @desc 三级分类列表
 *
 */
public class ListDialogFragment extends DialogFragment{
	
	private DialogItemClick dialogItemClick;
	private static List<Conditions> list;
	private static ListDialogFragment fragment = null;
	
	private ListDialogFragment(){}
	
	
	public interface DialogItemClick{
		public void dialogItemClickedListener(int position);
	}
	
	
	public static ListDialogFragment getInstance(List<Conditions> l){
		
		if (fragment == null) {
			fragment = new ListDialogFragment();
		}
		list = l;
		
		return fragment;
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		
		dialogItemClick = (DialogItemClick) getActivity();
		String[] nameArray = new String[list.size()];
		
		for (int i = 0; i < list.size(); i++) {
			nameArray[i] = list.get(i).ename;
		}
		
		Dialog dialog = new AlertDialog.Builder(getActivity())
		.setItems(nameArray, new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialogItemClick.dialogItemClickedListener(which);
			}
		}).create();
		
		return dialog;
	}

}
