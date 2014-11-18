package com.kongfuzi.student.ui.view;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.kongfuzi.student.R;

public class RightSlidingMenu extends SlidingMenu implements OnClickListener{
	
	private Context context;
	
	private ListView list_lv;
	private Button clear_btn;
	private Button submit_btn;

	public RightSlidingMenu(Context context, int resId) {
		super(context);
		this.context = context;
		setMenu(resId);

		initView();
	}

	private void initView() {
		
		list_lv = (ListView) findViewById(R.id.list_sliding_menu_right_lv);
		clear_btn = (Button) findViewById(R.id.clear_sliding_menu_right_btn);
		submit_btn = (Button) findViewById(R.id.submit_sliding_menu_right_btn);
		
		clear_btn.setOnClickListener(this);
		submit_btn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		
		switch (v.getId()) {
		
		case R.id.clear_sliding_menu_right_btn:
			
			break;
			
		case R.id.submit_sliding_menu_right_btn:
			
			break;

		default:
			break;
		}
	}

}
