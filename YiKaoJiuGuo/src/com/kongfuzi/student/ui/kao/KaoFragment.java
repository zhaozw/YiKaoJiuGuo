package com.kongfuzi.student.ui.kao;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import me.maxwin.view.IXListViewLoadMore;
import me.maxwin.view.IXListViewRefreshListener;
import me.maxwin.view.XListView;

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
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.kongfuzi.lib.volley.Response.Listener;
import com.kongfuzi.student.R;
import com.kongfuzi.student.app.YiKaoApplication;
import com.kongfuzi.student.bean.Conditions;
import com.kongfuzi.student.bean.Major;
import com.kongfuzi.student.support.network.ArrayRequest;
import com.kongfuzi.student.support.utils.BundleArgsConstants;
import com.kongfuzi.student.support.utils.UrlConstants;
import com.kongfuzi.student.ui.adapter.LeftKaoAdapter;
import com.kongfuzi.student.ui.adapter.MajorListAdapter;
import com.kongfuzi.student.ui.global.BaseFragment;
import com.kongfuzi.student.ui.view.RightSlidingMenu;

/**
 * @author LBDL
 * @desc 报考
 * 
 */
public class KaoFragment extends BaseFragment implements OnItemClickListener, HomeActivity.search, IXListViewLoadMore,
		IXListViewRefreshListener {

	private RightSlidingMenu slidingMenu = null;

	private EditText search_et;
	private TextView conditions_tv;
	private ListView conditions_lv;
	private XListView content_xlv;

	private int page = 0;
	private MajorListAdapter adapter = null;
	private String urlString = null;
	private static KaoFragment mInstance = null;
	private List<Conditions> leftList = new ArrayList<Conditions>();

	private static final String TAG = "KaoFragment";

	public static KaoFragment getInstance() {
		if (mInstance == null) {
			mInstance = new KaoFragment();
			mInstance.setArguments(new Bundle());
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

		adapter = new MajorListAdapter(getActivity());
		content_xlv.setAdapter(adapter);

		content_xlv.setOnItemClickListener(this);
		content_xlv.setPullLoadEnable(this);
		content_xlv.setPullRefreshEnable(this);
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

		urlString = UrlConstants.MAJOR_LIST + "&p=" + page;
		content_xlv.startRefresh();

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

	public void getRightData() {

		Type type = new TypeToken<List<Major>>() {}.getType();
		List<Conditions> filterList = YiKaoApplication.getConditionsList();
		urlString = urlString + "&score=" + filterList.get(0).id + "&category=" + filterList.get(1).id + "&cid="
				+ filterList.get(8).id + "&three=" + filterList.get(9).id + "&methods=" + filterList.get(2).id
				+ "&batch=" + filterList.get(3).id + "&city=" + filterList.get(4).id + "&course=" + filterList.get(5)
				+ "&pid=" + filterList.get(6) + "&other=" + filterList.get(7);

		ArrayRequest<List<Major>> request = new ArrayRequest<List<Major>>(urlString, new Listener<List<Major>>() {

			@Override
			public void onResponse(List<Major> response) {
				dismissLoadingDialog();
				if (page == 0) {
					content_xlv.stopRefresh();
				} else {
					content_xlv.stopLoadMore();
				}

				if (response != null) {
					initAdapter(response);
				}
			}
		}, type);

		queue.add(request);
		queue.start();
	}

	private void initAdapter(List<Major> list) {

		if (page == 0) {
			adapter.addFirstPageData(list);
		} else {
			adapter.addOtherPageData(list);
		}

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

		Object object = parent.getItemAtPosition(position);

		if (object != null && object instanceof Major) {

			Major major = (Major) object;
			Log.i(TAG, "major id = " + major.majorId);
			Intent intent = MajorDetailActivity.newIntent(major.majorId, major.collegeId);
			startActivity(intent);
		}

	}

	@Override
	public void searchForResult() {
		String searchString = search_et.getText().toString();
		page = 0;
		// try {
		// urlString = UrlConstants.MAJOR_LIST + "&title=" +
		// URLEncoder.encode(searchString, "UTF-8");
		// getRightData();
		// } catch (UnsupportedEncodingException e) {
		// e.printStackTrace();
		// }
		urlString = UrlConstants.MAJOR_LIST + "&title=" + searchString;
		Log.i(TAG, "url = " + urlString);
	}

	@Override
	public void onRefresh() {
		page = 0;
		getRightData();
	}

	@Override
	public void onLoadMore() {
		page++;
		getRightData();

	}

	/**
	 * 得到FirstCategoryActivity返回的intent
	 * */
	@Override
	public void getIntent(Intent intent) {

		Bundle bundle = intent.getExtras();
		int position = slidingMenu.position;
		Log.i(TAG, "bundle = " + bundle);
		Log.i(TAG, "position = " + position);
		// TODO null
		Conditions conditions = (Conditions) bundle.getSerializable(BundleArgsConstants.CONDITIONS);
		YiKaoApplication.getConditionsList().set(position, conditions);

		slidingMenu.adapter.list.set(position, conditions);
		slidingMenu.adapter.notifyDataSetChanged();
	}

}
