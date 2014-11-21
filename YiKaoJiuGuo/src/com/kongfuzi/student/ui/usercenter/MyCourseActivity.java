//package com.kongfuzi.student.ui.usercenter;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import me.maxwin.view.IXListViewLoadMore;
//import me.maxwin.view.IXListViewRefreshListener;
//import me.maxwin.view.XListView;
//
//import android.annotation.SuppressLint;
//import android.content.Context;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import com.google.gson.reflect.TypeToken;
//import com.kongfuzi.lib.volley.Request.Method;
//import com.kongfuzi.lib.volley.RequestQueue;
//import com.kongfuzi.lib.volley.Response.ErrorListener;
//import com.kongfuzi.lib.volley.Response.Listener;
//import com.kongfuzi.lib.volley.VolleyError;
//import com.kongfuzi.student.R;
//import com.kongfuzi.student.app.MainApplication;
//import com.kongfuzi.student.bean.Course;
//import com.kongfuzi.student.bean.CourseMajor;
//import com.kongfuzi.student.support.network.NetworkRequest;
//import com.kongfuzi.student.ui.BaseActivity;
//import com.kongfuzi.student.ui.view.LoadingDialog;
//import com.kongfuzi.student.ui.view.ToastDialog;
//import com.kongfuzi.student.util.Constants;
//import com.kongfuzi.student.util.LoginUtil;
//import com.kongfuzi.student.util.Util;
//import com.umeng.analytics.MobclickAgent;
//
///**
// * @author LBDL
// * @desc 我的课程
// * 
// */
//public class MyCourseActivity extends BaseActivity implements IXListViewLoadMore, IXListViewRefreshListener {
//
//	private TextView count_tv;
//	private XListView list_xlv;
//	// 页码
//	private int page = 0;
//	// 是否正在加载
//	private Boolean isLoading = false;
//	private Context mContext;
//	private ListAdapter mListAdapter;
//	private RequestQueue mQueue;
//	private LoadingDialog dialog;
//	private List<Course> onePage_list = new ArrayList<Course>();
//	public static final String TAG = "MyCourseActivity";
//
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_my_course);
//
//		mContext = this;
//		mQueue = MainApplication.mQueue;
//		dialog = new LoadingDialog(mContext);
//
//		count_tv = (TextView) findViewById(R.id.count_my_course_tv);
//		list_xlv = (XListView) findViewById(R.id.list_my_course_xlv);
//
//		mListAdapter = new ListAdapter();
//		list_xlv.setAdapter(mListAdapter);
//
//		if (isLoading == false) {
//			isLoading = true;
//			list_xlv.setRefreshTime(Util.getCurrentDate());
//			// 加载数据库的数据
//			// mListAdapter.addFirstPageData(manager.query());
//			// 开始刷新
//			list_xlv.startRefresh();
//		} else {
//			list_xlv.NotRefreshAtBegin();
//		}
//
//		isLoading = true;
//
//		getData();
//
//	}
//
//	@Override
//	protected void onResume() {
//		super.onResume();
//		MobclickAgent.onPageStart(TAG);
//	}
//
//	private void getData() {
//		dialog.showDialog();
//		NetworkRequest<List<Course>> request = new NetworkRequest<List<Course>>(Method.GET, Constants.MY_COURSE
//				+ "&mid=" + LoginUtil.getStudentId() + "&p=" + page, null, new Listener<List<Course>>() {
//
//			@Override
//			public void onResponse(List<Course> response) {
//				dialog.cancelDialog();
//				onePage_list = response;
//				if (page == 0) {
//					list_xlv.stopRefresh(Util.getCurrentDate());
//				} else {
//					list_xlv.stopLoadMore();
//				}
//				initAdapter();
//			}
//		}, new ErrorListener() {
//
//			@Override
//			public void onErrorResponse(VolleyError error) {
//				dialog.cancel();
//				try {
//					String message = (error.getMessage() == null) ? "网络访问失败,请您检查网络." : error.getMessage();
//					ToastDialog.showToast(MyCourseActivity.this, message);
//				} catch (Exception e) {
//
//				}
//				// ToastDialog.showToast(mActivity, error.getMessage());
//			}
//		}, new TypeToken<List<Course>>() {
//		}.getType(), 1);
//
//		mQueue.add(request);
//		mQueue.start();
//	}
//
//	private void initAdapter() {
//
//		if (page == 0) {
//			mListAdapter.addFirstPageData(onePage_list);
//
//		} else {
//			mListAdapter.addOtherPageData(onePage_list);
//		}
//	}
//	
//
////	public class ListAdapter extends BaseAdapter {
////
////		private ViewHolder viewHolder;
////		private Course course;
////		private List<Course> list = new ArrayList<Course>();
////
////		class ViewHolder {
////			TextView position_tv;
////			TextView attr_tv;
////			TextView name_tv;
////			LinearLayout content_ll;
//////			TextView college0_tv;
//////			TextView major0_tv;
//////			TextView college1_tv;
//////			TextView major1_tv;
//////			TextView college2_tv;
//////			TextView major2_tv;
////			
////		}
////
////		@Override
////		public int getCount() {
////			return list.size();
////		}
////
////		@Override
////		public Object getItem(int position) {
////			return null;
////		}
////
////		@Override
////		public long getItemId(int position) {
////			return position;
////		}
////
////		public void addFirstPageData(List<Course> item_list) {
////			list.clear();
////			list.addAll(item_list);
////			notifyDataSetChanged();
////		}
////
////		public void addOtherPageData(List<Course> item_list) {
////			list.addAll(item_list);
////			notifyDataSetChanged();
////		}
////
////		@SuppressLint("NewApi")
////		@Override
////		public View getView(int position, View convertView, ViewGroup parent) {
////
////			if (list.isEmpty()) {
////				return convertView;
////			}
////			course = list.get(position);
////			if (convertView == null) {
////				viewHolder = new ViewHolder();
////
////				convertView = getLayoutInflater().inflate(R.layout.item_my_course_list, parent, false);
////				viewHolder.position_tv = (TextView) convertView.findViewById(R.id.position_item_my_course_tv);
////				viewHolder.attr_tv = (TextView) convertView.findViewById(R.id.attr_item_my_course_tv);
////				viewHolder.name_tv = (TextView) convertView.findViewById(R.id.name_item_my_course_tv);
////				viewHolder.content_ll = (LinearLayout) convertView.findViewById(R.id.content_item_my_course_list_ll);
//////				viewHolder.college0_tv = (TextView) convertView.findViewById(R.id.college0_item_my_course_tv);
//////				viewHolder.major0_tv = (TextView) convertView.findViewById(R.id.major0_item_my_course_tv);
//////				viewHolder.college1_tv = (TextView) convertView.findViewById(R.id.college1_item_my_course_tv);
//////				viewHolder.major1_tv = (TextView) convertView.findViewById(R.id.major1_item_my_course_tv);
//////				viewHolder.college2_tv = (TextView) convertView.findViewById(R.id.college2_item_my_course_tv);
//////				viewHolder.major2_tv = (TextView) convertView.findViewById(R.id.major2_item_my_course_tv);
////				
////				convertView.setTag(viewHolder);
////			}else {
////				viewHolder = (ViewHolder) convertView.getTag();
////			}
////			
////			viewHolder.position_tv.setText(String.valueOf(position + 1));
////			viewHolder.attr_tv.setText(course.attr);
////			viewHolder.name_tv.setText(course.name);
////			
////			List<CourseMajor> courseMajor_list = course.body;
////			Log.i(TAG, "size (" + courseMajor_list.size());
////			viewHolder.content_ll.removeAllViewsInLayout();
////			for (int i = 0; i < courseMajor_list.size(); i++) {
////				CourseMajor courseMajor = courseMajor_list.get(i);
////				TextView content_tv = new TextView(mContext);
////				content_tv.setTextColor(getResources().getColor(R.color.blue));
////				content_tv.setText(courseMajor.college_name + "   " + courseMajor.major_name);
////				viewHolder.content_ll.addView(content_tv);
////			}
////			
//////			if (courseMajor_list.size() > 3) {
//////				
//////				viewHolder.college0_tv.setText(courseMajor_list.get(0).college_name);
//////				viewHolder.major0_tv.setText(courseMajor_list.get(0).major_name);
//////				viewHolder.college1_tv.setText(courseMajor_list.get(1).college_name);
//////				viewHolder.major1_tv.setText(courseMajor_list.get(1).major_name);
//////				viewHolder.college2_tv.setText(courseMajor_list.get(2).college_name);
//////				viewHolder.major2_tv.setText(courseMajor_list.get(2).major_name);
//////				
//////			}
////			
////
////			return convertView;
////		}
////
////	}
//
//	@Override
//	protected void onPause() {
//		MobclickAgent.onPageEnd(TAG);
//		super.onPause();
//	}
//
//	@Override
//	public void onRefresh() {
//		page = 0;
//		getData();
//	}
//
//	@Override
//	public void onLoadMore() {
//		page++;
//		getData();
//		
//	}
//
//}
