package com.kongfuzi.student.ui.kao;

import android.os.Bundle;
import android.support.v4.app.Fragment;

public class MessageFragment extends Fragment {
	
	public static MessageFragment getInstance(){
		
		MessageFragment fragment = new MessageFragment();
		fragment.setArguments(new Bundle());
		
		return fragment;
	}

}
