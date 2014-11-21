package com.kongfuzi.student.ui.kao;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.kongfuzi.lib.volley.RequestQueue;
import com.kongfuzi.lib.volley.Response.Listener;
import com.kongfuzi.student.R;
import com.kongfuzi.student.bean.Conditions;
import com.kongfuzi.student.bean.Major;
import com.kongfuzi.student.support.YiKaoApplication;
import com.kongfuzi.student.support.network.ArrayRequest;
import com.kongfuzi.student.support.utils.UrlConstants;
import com.kongfuzi.student.ui.adapter.LeftKaoAdapter;
import com.kongfuzi.student.ui.adapter.MajorListAdapter;
import com.kongfuzi.student.ui.global.BaseFragment;
import com.kongfuzi.student.ui.view.RightSlidingMenu;

/**
 * @author LBDL
 * @desc ±¨¿¼
 * 
 */
public class KaoFragment extends BaseFragment implements OnItemClickListener, HomeActivity.search {

	private RightSlidingMenu slidingMenu = null;

	private EditText search_et;
	private TextView conditions_tv;
	private ListView conditions_lv;
	private ListView content_lv;

	private static KaoFragment mInstance = null;
	private List<Conditions> leftList = new ArrayList<Conditions>();
	private List<Major> rightList = new ArrayList<Major>();
	
	private static final String TAG = "KaoFragment";

	public static KaoFragment getInstance() {
		if (mInstance == null) {
			mInstance = new KaoFragment();
			mInstance.setArguments(new Bundle());
		}
		return mInstance;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_kao, container, false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		search_et = (EditText) view.findViewById(R.id.search_kao_et);
		conditions_tv = (TextView) view.findViewById(R.id.conditions_kao_tv);
		conditions_lv = (ListView) view.findViewById(R.id.conditions_kao_lv);
		content_lv = (ListView) view.findViewById(R.id.content_kao_lv);
		
		content_lv.setOnItemClickListener(this);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		slidingMenu = new RightSlidingMenu(getActivity(), R.layout.sliding_menu_right_layout);

		// slidingMenu.setShadowWidthRes(R.dimen.shadow_width);
		slidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		slidingMenu.setFadeEnabled(true);
		slidingMenu.setFadeDegree(0.35f);
		slidingMenu.setMode(SlidingMenu.RIGHT);
		slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
		slidingMenu.setShadowDrawable(R.color.chocolate);
		slidingMenu.attachToActivity(getActivity(), SlidingMenu.SLIDING_CONTENT);

		conditions_tv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				slidingMenu.toggle();
			}
		});

		getLeftData();
		getRightData();

	}

	private void getLeftData() {

		Type type = new TypeToken<List<Conditions>>() {
		}.getType();
		showLoadingDialog();
		ArrayRequest<List<Conditions>> topRequest = new ArrayRequest<List<Conditions>>(UrlConstants.BATCH,
				new Listener<List<Conditions>>() {

					@Override
					public void onResponse(List<Conditions> response) {
						// dialog.dismiss();
						leftList.addAll(response);
					}
				}, type);

		ArrayRequest<List<Conditions>> bottomRequest = new ArrayRequest<List<Conditions>>(UrlConstants.PROVINCE,
				new Listener<List<Conditions>>() {

					@Override
					public void onResponse(List<Conditions> response) {
						if (response != null) {

							leftList.addAll(response);
							conditions_lv.setAdapter(new LeftKaoAdapter(getActivity(), leftList));
						}
					}
				}, type);

		queue.add(topRequest);
		queue.add(bottomRequest);
		queue.start();
	}

	private void getRightData() {

		Type type = new TypeToken<List<Major>>() {
		}.getType();

		ArrayRequest<List<Major>> request = new ArrayRequest<List<Major>>(UrlConstants.MAJOR_LIST,
				new Listener<List<Major>>() {

					@Override
					public void onResponse(List<Major> response) {
						dismissLoadingDialog();

						if (response != null) {
							rightList.addAll(response);
							content_lv.setAdapter(new MajorListAdapter(getActivity(), rightList));
						}
					}
				}, type);

		queue.add(request);
		queue.start();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		
		Object object = rightList.get(position);
		parent.getItemAtPosition(position);
		
		if (object != null && object instanceof Major) {
			
			Major major = (Major) object;
			Log.i(TAG, "major id = " + major.majorId);
			Intent intent = MajorDetailActivity.newIntent(major.majorId, major.collegeId);
			startActivity(intent);
		}
		
		
	}

	@Override
	public void searchForResult() {
		
		
	}

}
