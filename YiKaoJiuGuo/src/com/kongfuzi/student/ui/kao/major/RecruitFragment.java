package com.kongfuzi.student.ui.kao.major;

import java.lang.reflect.Type;
import java.util.List;

import org.json.JSONObject;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.kongfuzi.lib.volley.Request.Method;
import com.kongfuzi.lib.volley.Response.Listener;
import com.kongfuzi.lib.volley.toolbox.JsonObjectRequest;
import com.kongfuzi.student.R;
import com.kongfuzi.student.bean.EnrollDetail;
import com.kongfuzi.student.bean.Prospectus;
import com.kongfuzi.student.support.network.ObjectRequest;
import com.kongfuzi.student.support.utils.BundleArgsConstants;
import com.kongfuzi.student.support.utils.UrlConstants;
import com.kongfuzi.student.ui.global.BaseFragment;

/**
 * @author LBDL
 * @desc 招生详情(专业详情)
 * 
 */

public class RecruitFragment extends BaseFragment {

	private static final String TAG = "RecruitFragment";
	private TextView count_tv;
	private TextView major_tv;
	private TextView province_tv;
	private TextView mode_tv;
	private ProgressBar progress_pb;
	/**
	 * @param i
	 *            专业id
	 */
	public static RecruitFragment getInstance(String id) {

		RecruitFragment fragment = new RecruitFragment();
		Bundle bundle = new Bundle();
		bundle.putString(BundleArgsConstants.MAJOR_ID, id);
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
		progress_pb = (ProgressBar) view.findViewById(R.id.progress_recruit_major_detail_pb);

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		getData();
	}

	private void getData() {
//		JsonObjectRequest request = new JsonObjectRequest(Method.GET, UrlConstants.RECRUIT_DETAIL + "&id="
//				+ getArguments().getInt(BundleArgsConstants.MAJOR_ID), null, new Listener<JSONObject>() {
//
//			@Override
//			public void onResponse(JSONObject response) {
//				progress_pb.setVisibility(View.GONE);
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
		Type type=new TypeToken<EnrollDetail>(){}.getType();
		Log.i(TAG, getArguments().getString(BundleArgsConstants.MAJOR_ID));
		ObjectRequest<EnrollDetail>request=new ObjectRequest<>(UrlConstants.RECRUIT_DETAIL+"&id="+getArguments().getString(BundleArgsConstants.MAJOR_ID), new Listener<EnrollDetail>() {
			
			@Override
			public void onResponse(EnrollDetail response) {
				// TODO Auto-generated method stub
				progress_pb.setVisibility(View.GONE);
				
				if(response!=null){
					count_tv.setText(""+response.people+"人");
					major_tv.setText(""+response.subject);
					province_tv.setText(""+response.province);
					mode_tv.setText(""+response.body);
				}
			}
		}, type);
		queue.add(request);
		queue.start();
	}

}
