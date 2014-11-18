package com.kongfuzi.student.ui.kao;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * @author LBDL
 * @desc ÎÒµÄ
 *
 */
public class UserCenterFragment extends Fragment {
	
	public static UserCenterFragment getInstance(){
		
		UserCenterFragment fragment = new UserCenterFragment();
		fragment.setArguments(new Bundle());
		
		return fragment;
	}

}
