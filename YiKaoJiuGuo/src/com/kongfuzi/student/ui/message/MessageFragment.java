package com.kongfuzi.student.ui.message;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * @author LBDL
 * @desc ��Ϣ�б�
 *
 */
public class MessageFragment extends Fragment {
	
	public static MessageFragment getInstance(){
		
		MessageFragment fragment = new MessageFragment();
		fragment.setArguments(new Bundle());
		
		return fragment;
	}

}
