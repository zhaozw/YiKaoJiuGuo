package com.kongfuzi.student.ui.kao.major;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kongfuzi.student.R;
/**
 * @author LBDL
 * @desc 招生详情(专业详情)
 *
 */

public class RecruitFragment extends Fragment {
	
	//专业id
	private int id;
	
	private TextView count_tv;
	private TextView major_tv;
	private TextView province_tv;
	private TextView mode_tv;
	
	/**
	 * @param i  专业id
	 */
	public static RecruitFragment getInstance(int i){
		
		RecruitFragment fragment = new RecruitFragment();
		Bundle bundle = new Bundle();
		bundle.putInt("id", i);
		fragment.setArguments(bundle);
		
		return fragment;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_recruit_major_detail, container, false);
	}
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
		count_tv = (TextView) view.findViewById(R.id.count_recruit_major_detail_tv);
		major_tv = (TextView) view.findViewById(R.id.major_recruit_major_detail_tv);
		province_tv = (TextView) view.findViewById(R.id.province_recruit_major_detail_tv);
		mode_tv = (TextView) view.findViewById(R.id.mode_recruit_major_detail_tv);
		
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
//		getData();
	}

//	private void getData() {
//		JsonObjectRequest request = new JsonObjectRequest(Method.GET, Constants.RECRUIT_DETAIL + "&id=" + id, null, new Listener<JSONObject>() {
//
//			@Override
//			public void onResponse(JSONObject response) {
//				
//				JSONObject jsonObject = response.optJSONObject("data");
//				
//				if (response != null && response.optBoolean("success")) {
//					count_tv.setText(jsonObject.optInt("people") + "人");
//					major_tv.setText(jsonObject.optString("subject"));
//					province_tv.setText(jsonObject.optString("province"));
//					mode_tv.setText(jsonObject.optString("body"));
//				}
//			}
//		}, null);
//		
//		RequestQueue queue = MainApplication.getRequestQueueInstance();
//		queue.add(request);
//		queue.start();
//	}

}
