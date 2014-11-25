package com.kongfuzi.student.ui.kao.major;

import java.util.List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.google.gson.reflect.TypeToken;
import com.kongfuzi.lib.volley.Response.Listener;
import com.kongfuzi.student.R;
import com.kongfuzi.student.bean.Score;
import com.kongfuzi.student.support.network.ArrayRequest;
import com.kongfuzi.student.support.utils.BundleArgsConstants;
import com.kongfuzi.student.support.utils.UrlConstants;
import com.kongfuzi.student.ui.adapter.ScoreAdapter;
import com.kongfuzi.student.ui.global.BaseFragment;
/**
 * @author LBDL
 * @desc 录取分数(大学详情)
 *
 */
public class ScoreFragment extends BaseFragment {
	
	private ProgressBar progress_pb;
	private ListView list_lv;
	
//	private RequestQueue mQueue;
	
	/**
	 * @param id
	 * 专业id
	 * */
	public static ScoreFragment getInstance(int id){
		
		ScoreFragment fragment = new ScoreFragment();
		Bundle bundle = new Bundle();
		bundle.putInt(BundleArgsConstants.MAJOR_ID, id);
		fragment.setArguments(bundle);
		return fragment;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_score, container, false);
	}
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
		progress_pb = (ProgressBar) view.findViewById(R.id.progress_score_pb);
		list_lv = (ListView) view.findViewById(R.id.list_score_lv);
		
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		getData();
		
	}
	
	private void getData() {
		ArrayRequest<List<Score>> request = new ArrayRequest<List<Score>>(UrlConstants.MORE_SCORE +
				"&id=" + getArguments().getInt(BundleArgsConstants.MAJOR_ID),new Listener<List<Score>>() {

			@Override
			public void onResponse(List<Score> response) {
				progress_pb.setVisibility(View.GONE);
				if (!response.isEmpty() && response != null) {
					
					list_lv.setAdapter(new ScoreAdapter(getActivity(),response));
				}
				
			}
		},new TypeToken<List<Score>>(){}.getType());
		
		queue.add(request);
		queue.start();
	}

}
