package com.kongfuzi.student.ui.usercenter;

import java.util.List;

import org.json.JSONObject;

import me.maxwin.view.IXListViewRefreshListener;
import me.maxwin.view.XListView;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kongfuzi.lib.volley.VolleyError;
import com.kongfuzi.lib.volley.Request.Method;
import com.kongfuzi.lib.volley.Response.ErrorListener;
import com.kongfuzi.lib.volley.Response.Listener;
import com.kongfuzi.lib.volley.toolbox.JsonObjectRequest;
import com.kongfuzi.student.R;
import com.kongfuzi.student.app.YiKaoApplication;
import com.kongfuzi.student.bean.KaoMajor;
import com.kongfuzi.student.bean.College;
import com.kongfuzi.student.support.utils.UrlConstants;
import com.kongfuzi.student.ui.adapter.CollegeListAdapter;
import com.kongfuzi.student.ui.global.BaseActivity;

/**
 * @author LBDL
 * @desc 我的收藏
 * 
 */
public class MyCollectionActivity extends BaseActivity implements IXListViewRefreshListener {

	private TextView count_tv;
	private XListView list_xlv;
	private ImageView empty_iv;

	private int page = 0;

	private CollegeListAdapter adapter = null;

	public static final String TAG = "MyCollectionActivity";

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_my_collection);

		count_tv = (TextView) findViewById(R.id.count_my_collection_tv);
		list_xlv = (XListView) findViewById(R.id.list_my_collection_xlv);
		empty_iv = (ImageView) findViewById(R.id.empty_kao_iv);

		adapter = new CollegeListAdapter(this);
		list_xlv.setAdapter(adapter);

		list_xlv.setPullRefreshEnable(this);
		list_xlv.startRefresh();
	}

	public void getRightData() {

		JsonObjectRequest request = new JsonObjectRequest(Method.GET, UrlConstants.MY_COLLECTION + "&mid="
				+ YiKaoApplication.getStudentId(), null, new Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				list_xlv.stopRefresh();
				list_xlv.stopLoadMore();

				if (response != null) {

					Gson gson = new Gson();
					KaoMajor kaoMajor = gson.fromJson(response.toString(), new TypeToken<KaoMajor>() {
					}.getType());
					count_tv.setText("共有" + kaoMajor.collegesList.size() + "个搜索结果");

					if (kaoMajor.collegesList.isEmpty()) {

						list_xlv.setEmptyView(empty_iv);

					} else {
						initAdapter(kaoMajor.collegesList);
					}
				}
			}
		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				list_xlv.stopRefresh();

				try {
					String message = (error.getMessage() == null) ? "网络访问失败,请您检查网络." : error.getMessage();
					toast(message);
				} catch (Exception e) {

				}
			}
		});

		queue.add(request);
		queue.start();
	}

	private void initAdapter(List<College> list) {

		if (page == 0) {
			adapter.addFirstPageData(list);
		} else {
			adapter.addOtherPageData(list);
		}

	}

	@Override
	public void onRefresh() {
		getRightData();
	}
}
