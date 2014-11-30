package com.kongfuzi.student.ui.kao;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.kongfuzi.lib.volley.RequestQueue;
import com.kongfuzi.lib.volley.Response.Listener;
import com.kongfuzi.student.R;
import com.kongfuzi.student.app.YiKaoApplication;
import com.kongfuzi.student.bean.Prospectus;
import com.kongfuzi.student.support.network.ArrayRequest;
import com.kongfuzi.student.support.utils.BundleArgsConstants;
import com.kongfuzi.student.support.utils.UrlConstants;
import com.kongfuzi.student.ui.global.BaseActivity;

/**
 * @author Administrator
 * @goal 招生简章
 */
public class EnrollGuideActivity extends BaseActivity implements OnClickListener {
	
	private ListView listView;
	private ImageView empty_iv;
	
	
	private List<Prospectus> list = new ArrayList<>();
	private ListAdapter adapter;

	/**
	 * @param id
	 *            大学id
	 * */
	public static Intent newIntent(String id) {
		Intent intent = new Intent(YiKaoApplication.getInstance(), EnrollGuideActivity.class);
		intent.putExtra(BundleArgsConstants.COLLEGE_ID, id);
		return intent;
	}

	@Override
	protected void onCreate(Bundle arg0) {

		super.onCreate(arg0);
		setContentView(R.layout.activity_enrollguide);

		queue = YiKaoApplication.getQueueInstance();

		initView();
		getData();
	}

	/**
	 * 初始化View
	 */
	private void initView() {

		listView = (ListView) findViewById(R.id.enrollGuide_listView);
		empty_iv = (ImageView) findViewById(R.id.empty_kao_iv);

	}

	@Override
	public void onClick(View v) {

	}

	private void getData() {
		Type type = new TypeToken<List<Prospectus>>() {
		}.getType();
		showLoadingDialog();
		ArrayRequest<List<Prospectus>> request = new ArrayRequest<>(UrlConstants.RECRUIT_DETAIL + "&id="
				+ getIntent().getStringExtra(BundleArgsConstants.COLLEGE_ID), new Listener<List<Prospectus>>() {

			@Override
			public void onResponse(List<Prospectus> response) {
				dismissLoadingDialog();
				if (response != null) {
					list = response;
					if (response.isEmpty()) {
						listView.setEmptyView(empty_iv);
					}else {
						adapter = new ListAdapter();
						listView.setAdapter(adapter);
					}
				} 
			}
		}, type);

		queue.add(request);
		queue.start();
	}

	public class ListAdapter extends BaseAdapter {

		private int index;

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			this.index = position;
			View view = LayoutInflater.from(YiKaoApplication.getInstance()).inflate(R.layout.item_question_list,
					parent, false);
			TextView title_tv = (TextView) view.findViewById(R.id.major_item_question_tv);

			title_tv.setText(list.get(position).title);

			view.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// Intent intent = new
					// Intent(YiKaoApplication.getInstance(),ProspectusDetailsActivity.class);
					Intent intent = ProspectusDetailsActivity.newIntent(list.get(position).id, list.get(position).title);
					// intent.putExtra("url",
					// UrlConstants.ENROLL_GUIDE_WEBVIEWURL + "&id=" +
					// list.get(index).id);
					startActivity(intent);
				}
			});
			return view;
		}

	}

}
