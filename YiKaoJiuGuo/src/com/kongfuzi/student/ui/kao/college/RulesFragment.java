package com.kongfuzi.student.ui.kao.college;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kongfuzi.student.R;
/**
 * @author LBDL
 * @desc 录取规则(大学详情)
 *
 */
public class RulesFragment extends Fragment {
	
	private int id;
	
	private TextView rule_tv;
	
//	private RequestQueue mQueue;
	
	public static RulesFragment getInstance(int i){
		
		RulesFragment fragment = new RulesFragment();
		Bundle bundle = new Bundle();
		bundle.putInt("id", i);
		fragment.setArguments(bundle);
		
		return fragment;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_admission_rules, container, false);
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
//		mQueue = MainApplication.getRequestQueueInstance();
		
		rule_tv = (TextView) view.findViewById(R.id.content_admisstion_rules_tv);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
//		JsonObjectRequest request = new JsonObjectRequest(Method.GET, Constants.RULES+"&id="+id, null, new Listener<JSONObject>() {
//
//			@Override
//			public void onResponse(JSONObject response) {
//				if (response.optBoolean("success")) {
//					rule_tv.setText(response.optString("rules"));
//				}
//			}
//		}, new ErrorListener() {
//
//			@Override
//			public void onErrorResponse(VolleyError error) {
//				
//			}
//		} );
		
//		mQueue.add(request);
//		mQueue.start();

		
	}

}
