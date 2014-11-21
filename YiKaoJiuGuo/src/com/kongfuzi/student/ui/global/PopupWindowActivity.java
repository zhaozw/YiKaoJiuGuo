package com.kongfuzi.student.ui.global;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.kongfuzi.student.R;
import com.kongfuzi.student.support.YiKaoApplication;
import com.kongfuzi.student.ui.kao.HomeActivity;

public class PopupWindowActivity extends Activity implements OnItemClickListener, OnClickListener {

	private ListView list_lv;
	private TextView title_tv;
	private Button cancel_btn;

	private PopupWindow mPopupWindow;

	public static final String TAG = "PopupWindowActivity";
	
	public static Intent newIntent(){
		Intent intent = new Intent(YiKaoApplication.getInstance(),PopupWindowActivity.class);
		
		return intent;
		
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTheme(R.style.Transparent);
		setContentView(R.layout.activity_popup_window);


		Handler popupHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				switch (msg.what) {
				case 0:
					mPopupWindow.showAtLocation((View) findViewById(R.id.activity_popup_window_rl), Gravity.BOTTOM
							| Gravity.CENTER_HORIZONTAL, 0, 0);
					mPopupWindow.update();
					break;
				}
			}

		};

		popupHandler.sendEmptyMessageDelayed(0, 100);

	}

	@Override
	protected void onResume() {
		super.onResume();
		initPopupWindow();
	}

	private void initPopupWindow() {

		View view = LayoutInflater.from(this).inflate(R.layout.popup_window_list, null);
		mPopupWindow = new PopupWindow(view, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, true);

		list_lv = (ListView) view.findViewById(R.id.list_popup_window_list_lv);
		cancel_btn = (Button) view.findViewById(R.id.cancel_popup_window_list_btn);

		list_lv.setOnItemClickListener(this);
		cancel_btn.setOnClickListener(this);

//		ListAdapter adapter = new ListAdapter(this, R.layout.item_popup_window);
//		list_lv.setAdapter(adapter);

		mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
		mPopupWindow.setOutsideTouchable(false);
		mPopupWindow.setFocusable(true);
		mPopupWindow.setAnimationStyle(R.style.PopupAnimation);

	}

//	class ListAdapter extends ArrayAdapter<Object> {
//
//		private int resourse;
//
//		public ListAdapter(Context context, int resource) {
//			super(context, resource);
//			this.resourse = resource;
//		}
//
//		@Override
//		public int getCount() {
//			return list.size();
//		}
//
//		@Override
//		public Conditions getItem(int position) {
//			return list.isEmpty() ? null : list.get(position);
//		}
//
//		@Override
//		public View getView(int position, View convertView, ViewGroup parent) {
//
//			Conditions conditions = getItem(position);
//
//			if (conditions == null) {
//				return convertView;
//			}
//
//			convertView = getLayoutInflater().inflate(resourse, parent, false);
//
//			TextView ename_tv = (TextView) convertView.findViewById(R.id.ename_item_popup_window_tv);
//			
//			ename_tv.setText(conditions.ename);
//
//			return convertView;
//		}
//
//	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.cancel_popup_window_list_btn:
			mPopupWindow.dismiss();
			finish();
			break;

		default:
			break;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long i) {
		
		Intent intent = new Intent(this,HomeActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		mPopupWindow.dismiss();
		this.finish();
	}
}
