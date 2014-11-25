package com.kongfuzi.student.ui.kao.major;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.google.gson.reflect.TypeToken;
import com.kongfuzi.lib.volley.Response.Listener;
import com.kongfuzi.student.R;
import com.kongfuzi.student.bean.Entity;
import com.kongfuzi.student.bean.Exam;
import com.kongfuzi.student.bean.Examination;
import com.kongfuzi.student.support.network.ArrayRequest;
import com.kongfuzi.student.support.utils.BundleArgsConstants;
import com.kongfuzi.student.support.utils.UrlConstants;
import com.kongfuzi.student.support.utils.Util;
import com.kongfuzi.student.ui.adapter.ExaminationAdapter;
import com.kongfuzi.student.ui.global.BaseFragment;
import com.kongfuzi.student.ui.view.NoScrollListView;

/**
 * @author LBDL
 * @desc 历年考题(专业详情)
 * 
 */
public class ExaminationFragment extends BaseFragment {

	// 专业名称
	private String major;

	private NoScrollListView list_lv;
	private ProgressBar progress_pb;

	private List<Entity> list = new ArrayList<Entity>();

	/**
	 * @param id
	 *            专业id
	 * 
	 * **/
	public static ExaminationFragment getInstance(int id, String major) {

		ExaminationFragment fragment = new ExaminationFragment();
		Bundle bundle = new Bundle();
		bundle.putInt(BundleArgsConstants.MAJOR_ID, id);
		bundle.putString(BundleArgsConstants.MAJOR_NAME, major);
		fragment.setArguments(bundle);

		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_examination, container, false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		list_lv = (NoScrollListView) view.findViewById(R.id.list_item_examination_lv);
		progress_pb = (ProgressBar) view.findViewById(R.id.progress_item_examination_pb);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		getData();

	}

	private void getData() {

		int majorId = getArguments().getInt(BundleArgsConstants.MAJOR_ID);

		ArrayRequest<List<Examination>> request = new ArrayRequest<List<Examination>>(UrlConstants.EXAMINATION_LIST
				+ "&id=" + majorId, new Listener<List<Examination>>() {

			@Override
			public void onResponse(List<Examination> response) {
				progress_pb.setVisibility(View.GONE);
				Examination examination = null;

				for (int i = 0; i < response.size(); i++) {
					examination = response.get(i);
					Entity entity = new Entity();
					entity.title = examination.title;
					entity.isTitle = true;
					list.add(entity);
					List<Exam> exam_list = examination.exam_list;
					for (int j = 0; j < exam_list.size(); j++) {
						Entity entity2 = new Entity();
						Exam exam = exam_list.get(j);
						entity2.first = exam.first;
						entity2.second = exam.second;
						entity2.body = exam.body;
						entity2.isTitle = false;
						list.add(entity2);
					}
				}
				list_lv.setAdapter(new ExaminationAdapter(getActivity(), getArguments().getString(
						BundleArgsConstants.MAJOR_NAME),list));
				Util.setListViewHeightBasedOnChildren(list_lv);
			}
		}, new TypeToken<List<Examination>>() {
		}.getType());

		queue.add(request);
		queue.start();
	}

}
