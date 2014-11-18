package com.kongfuzi.student.ui.kao;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.kongfuzi.student.R;
import com.kongfuzi.student.ui.view.RightSlidingMenu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class KaoFragment extends Fragment implements OnItemClickListener{
	
	private RightSlidingMenu slidingMenu = null;
	
	private ListView conditions_lv;
	private ListView content_lv;
	
	public static KaoFragment getInstance(){
		
		KaoFragment fragment = new KaoFragment();
		fragment.setArguments(new Bundle());
		
		return fragment;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_kao, container, false );
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
		conditions_lv = (ListView) view.findViewById(R.id.conditions_kao_lv);
		content_lv = (ListView) view.findViewById(R.id.content_kao_lv);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
	    slidingMenu = new RightSlidingMenu(getActivity(), R.layout.sliding_menu_right_layout);
	    
		slidingMenu.setShadowWidthRes(R.dimen.shadow_width);
		slidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		slidingMenu.setFadeDegree(0.35f);
		slidingMenu.setMode(SlidingMenu.RIGHT);
		slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
		slidingMenu.setShadowDrawable(R.color.chocolate);
		slidingMenu.attachToActivity(getActivity(), SlidingMenu.SLIDING_WINDOW);
		
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		
	}

}
