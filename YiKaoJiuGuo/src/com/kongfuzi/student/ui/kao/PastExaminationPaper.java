package com.kongfuzi.student.ui.kao;

import java.lang.reflect.Type;
import java.util.List;

import za.co.immedia.pinnedheaderlistview.PinnedHeaderListView;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.google.gson.reflect.TypeToken;
import com.kongfuzi.lib.volley.RequestQueue;
import com.kongfuzi.lib.volley.Response.Listener;
import com.kongfuzi.student.R;
import com.kongfuzi.student.app.YiKaoApplication;
import com.kongfuzi.student.bean.Examination;
import com.kongfuzi.student.support.network.ArrayRequest;
import com.kongfuzi.student.support.utils.BundleArgsConstants;
import com.kongfuzi.student.support.utils.UrlConstants;
import com.kongfuzi.student.ui.adapter.ExamQuesAdapter;
import com.kongfuzi.student.ui.global.BaseActivity;

/**
 * @author LBDL
 * @desc 历年考题
 * 
 */
public class PastExaminationPaper extends BaseActivity {

	private ImageView imageView, wushujuImageView;
	private PinnedHeaderListView listView;
	private String url = null;
	private List<Examination> examinations;
	private RequestQueue queue;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_exam_ques);
		queue = YiKaoApplication.getQueueInstance();
		initVIew();
		getArgument();
		loadData();
	}


	/**
	 * 得到参数
	 */
	private void getArgument() {
		Intent intent = getIntent();
		String id = intent.getStringExtra(BundleArgsConstants.EXAM_ID);
		url = UrlConstants.EXAMINATION_LIST + "&id=" + id;
	}

	/**
	 * @param id
	 *            大学id
	 * */
	public static Intent newIntent(String id) {
		Intent intent = new Intent(YiKaoApplication.getInstance(), PastExaminationPaper.class);
		intent.putExtra(BundleArgsConstants.EXAM_ID, id);
		return intent;
	}

	/**
	 * 网络加载数据
	 */
	private <T> void loadData() {
		Type type = new TypeToken<List<Examination>>() {
		}.getType();
		System.out.println("--------" + 1111);
		// ArrayRequest<List<Examination>>request=new
		// ArrayRequest<List<Examination>>(url, new
		// Listener<List<Examination>>() {
		// @Override
		// public void onResponse(List<Examination>list) {
		// System.out.println("-------"+list.get(0).exam.get(0).body);
		// examinations=list;
		// System.out.println("-------"+"走了吗");
		// ExpandableListViewAdapter adapter=new
		// ExpandableListViewAdapter(PastExaminationPaper.this,examinations);
		// listView.setAdapter(adapter);
		// }
		// }, type);
		ArrayRequest<List<Examination>> request = new ArrayRequest<List<Examination>>(url,
				new Listener<List<Examination>>() {

					@Override
					public void onResponse(List<Examination> response) {
						// TODO Auto-generated method stub
						Log.i("ArrayRequest", "get el response");
						examinations = response;
						if (response != null) {
							if (response.isEmpty()) {
								listView.setEmptyView(wushujuImageView);
							} else {

								ExamQuesAdapter adapter = new ExamQuesAdapter(PastExaminationPaper.this, response);
								listView.setAdapter(adapter);
							}
						}
					}
				}, type);
		queue.add(request);
		queue.start();
	}

	/**
	 * 初始化View
	 */
	private void initVIew() {
		listView = (PinnedHeaderListView) findViewById(R.id.list_exam_ques_phlv);
		wushujuImageView = (ImageView) findViewById(R.id.empty_kao_iv);
	}

}
