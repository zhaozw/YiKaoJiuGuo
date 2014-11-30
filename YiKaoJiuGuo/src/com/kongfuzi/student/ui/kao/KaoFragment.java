package com.kongfuzi.student.ui.kao;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import me.maxwin.view.IXListViewLoadMore;
import me.maxwin.view.IXListViewRefreshListener;
import me.maxwin.view.XListView;

import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.kongfuzi.lib.volley.Request.Method;
import com.kongfuzi.lib.volley.Response.ErrorListener;
import com.kongfuzi.lib.volley.Response.Listener;
import com.kongfuzi.lib.volley.VolleyError;
import com.kongfuzi.lib.volley.toolbox.JsonObjectRequest;
import com.kongfuzi.student.R;
import com.kongfuzi.student.app.YiKaoApplication;
import com.kongfuzi.student.bean.Conditions;
import com.kongfuzi.student.bean.KaoMajor;
import com.kongfuzi.student.bean.College;
import com.kongfuzi.student.support.network.ArrayRequest;
import com.kongfuzi.student.support.utils.BundleArgsConstants;
import com.kongfuzi.student.support.utils.UrlConstants;
import com.kongfuzi.student.ui.adapter.LeftKaoAdapter;
import com.kongfuzi.student.ui.adapter.CollegeListAdapter;
import com.kongfuzi.student.ui.global.BaseFragment;
import com.kongfuzi.student.ui.view.RightSlidingMenu;

/**
 * @author LBDL
 * @desc 报考
 * 
 */
public class KaoFragment extends BaseFragment implements OnItemClickListener, HomeActivity.search, IXListViewLoadMore,
		IXListViewRefreshListener {

	public RightSlidingMenu slidingMenu = null;

	private EditText search_et;
	private TextView conditions_tv;
	private ListView conditions_lv;
	private XListView content_xlv;
	private TextView count_tv;
	private ImageView empty_iv;

	private int page = 0;
	private CollegeListAdapter adapter = null;
	private static KaoFragment mInstance = null;
	private List<Conditions> leftList = new ArrayList<Conditions>();

	private static final String TAG = "KaoFragment";
	private int curPosition = -1;
	private View curSelectedView = null;
	private LeftKaoAdapter leftConditionAdapter = null;

	public static KaoFragment getInstance() {
		if (mInstance == null) {
			mInstance = new KaoFragment();
		}
		return mInstance;
	}

	public interface ActivityResult {
		public void onActivityResulted(Intent intent);
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
		content_xlv = (XListView) view.findViewById(R.id.content_kao_xlv);
		count_tv = (TextView) view.findViewById(R.id.count_kao_tv);
		empty_iv = (ImageView) view.findViewById(R.id.empty_kao_iv);

		adapter = new CollegeListAdapter(getActivity());
		content_xlv.setAdapter(adapter);

		content_xlv.setOnItemClickListener(this);
		content_xlv.setPullLoadEnable(this);
		content_xlv.setPullRefreshEnable(this);
		leftConditionAdapter = new LeftKaoAdapter(getActivity(), leftList);
		conditions_lv.setOnItemClickListener(this);
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

		content_xlv.startRefresh();

	}

	private void getLeftData() {

		Type type = new TypeToken<List<Conditions>>() {
		}.getType();
		final ArrayRequest<List<Conditions>> topRequest = new ArrayRequest<List<Conditions>>(UrlConstants.BATCH,
				new Listener<List<Conditions>>() {

					@Override
					public void onResponse(List<Conditions> response) {

						if (response != null) {
							Conditions conditions = new Conditions();
							conditions.ename = "全部";
							response.add(0, conditions);
							leftList.addAll(response);
						}
					}
				}, type);

		ArrayRequest<List<Conditions>> bottomRequest = new ArrayRequest<List<Conditions>>(UrlConstants.PROVINCE,
				new Listener<List<Conditions>>() {

					@Override
					public void onResponse(List<Conditions> response) {
						if (response != null) {
							leftList.addAll(response);

							conditions_lv.setAdapter(leftConditionAdapter);
						}
					}
				}, type);

		queue.add(topRequest);
		queue.add(bottomRequest);
		queue.start();
	}

	public void getRightData(String urlString) {

		Log.i(TAG, "url = " + urlString);

		JsonObjectRequest request = new JsonObjectRequest(Method.GET, urlString, null, new Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				content_xlv.stopRefresh();
				content_xlv.stopLoadMore();

				if (response != null) {

					Gson gson = new Gson();
					KaoMajor kaoMajor = gson.fromJson(response.toString(), new TypeToken<KaoMajor>() {
					}.getType());
					count_tv.setText("共有" + kaoMajor.countString + "个搜索结果");

					if (kaoMajor.collegesList.isEmpty()) {
						//TODO 这个地方不刷新
						content_xlv.setEmptyView(empty_iv);
						adapter.list = kaoMajor.collegesList;
						adapter.notifyDataSetChanged();

					} else {
						initAdapter(kaoMajor.collegesList);
					}
				}
			}
		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				content_xlv.stopRefresh();
				content_xlv.stopLoadMore();

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
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Object object = parent.getItemAtPosition(position);
		
		if (parent == content_xlv) {
			//专业列表
			if (object != null && object instanceof College) {

				College college = (College) object;
				Log.i(TAG, "college id = " + college.collegeId);
				Intent intent = CollegeDetailActivity.newIntent(college.collegeId);
				startActivity(intent);
			}
		} else if (parent == conditions_lv) {
			//左边listview
			if (object != null && object instanceof Conditions) {
				if (curPosition == position)
					return;
				if (curSelectedView != null) {
					curSelectedView.setBackgroundColor(getResources().getColor(R.color.light_grey));
				}
				view.setBackgroundColor(getResources().getColor(R.color.white));
				int conditionId = ((Conditions) object).id;
				String requestUrl = null;
				if (position == 0) {
					// 全部
					requestUrl = UrlConstants.MAJOR_LIST;
				} else if (position < 5) {
					// 批次
					requestUrl = UrlConstants.MAJOR_LIST + "&batch=" + conditionId;
				} else {
					// 生源地
					requestUrl = UrlConstants.MAJOR_LIST + "&pid=" + conditionId;
				}
				getRightData(requestUrl);
				curPosition = position;
				curSelectedView = view;
				leftConditionAdapter.selectedPosition = position;
				leftConditionAdapter.notifyDataSetChanged();
			}
		}

	}

	@Override
	public void searchForResult() {
		String searchString = search_et.getText().toString();
		page = 0;
		try {
			getRightData(UrlConstants.getMajorListUrl(URLEncoder.encode(searchString, "UTF-8")));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onRefresh() {
		page = 0;

		getRightData(UrlConstants.getMajorListUrl("") + "&p=" + page);
	}

	@Override
	public void onLoadMore() {
		page++;
		getRightData(UrlConstants.getMajorListUrl("") + "&p=" + page);

	}
	
	

	/**
	 * 得到FirstCategoryActivity返回的intent
	 * */
	@Override
	public void getIntent(Intent intent) {

		Bundle bundle = intent.getExtras();
		int position = slidingMenu.position;
		// 0: 二级分类 1: 三级分类
		int index = bundle.getInt("index");
		Log.i(TAG, "bundle = " + bundle);
		Conditions conditions = (Conditions) bundle.getSerializable(BundleArgsConstants.CONDITIONS);
		if (bundle != null) {
			if (index == 0) {
				
				YiKaoApplication.getConditionsList().set(8, conditions);
			}else if (index == 1) {
				YiKaoApplication.getConditionsList().set(9, conditions);
				
			}


			slidingMenu.adapter.list.set(position, conditions);
			slidingMenu.adapter.notifyDataSetChanged();
		}
	}

}
