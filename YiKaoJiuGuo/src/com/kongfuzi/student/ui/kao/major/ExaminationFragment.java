package com.kongfuzi.student.ui.kao.major;

import java.util.ArrayList;
import java.util.List;

import com.kongfuzi.student.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;



/**
 * @author LBDL
 * @desc 历年考题(专业详情)
 * 
 */
public class ExaminationFragment extends Fragment {

	// 专业id
	private int id;
	//专业名称
	private String major;

	private ListView list_lv;
	private ProgressBar progress_pb;
//	private List<Entity> list = new ArrayList<Entity>();
//	private RequestQueue mQueue;

	class Entity {
		String title;
		String first;
		String second;
		String body;
		Boolean isTitle; // 是exam 还是title
	}

	public static ExaminationFragment getInstance(int id,String major) {
		
		ExaminationFragment fragment = new ExaminationFragment();
		Bundle bundle = new Bundle();
		bundle.putInt("id", id);
		bundle.putString("major", major);
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

//		mQueue = MainApplication.getRequestQueueInstance();

//		list_lv = (NoScrollListView) view.findViewById(R.id.list_item_examination_lv);
		progress_pb = (ProgressBar) view.findViewById(R.id.progress_item_examination_pb);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

//		getData();

	}

//	private void getData() {
//		NetworkRequest<List<Examination>> request = new NetworkRequest<List<Examination>>(Method.GET,
//				Constants.EXAMINATION + "&id=" + id, null, new Listener<List<Examination>>() {
//
//					@Override
//					public void onResponse(List<Examination> response) {
//						progress_pb.setVisibility(View.GONE);
//						Examination examination = null;
//
//						for (int i = 0; i < response.size(); i++) {
//							examination = response.get(i);
//							Entity entity = new Entity();
//							entity.title = examination.title;
//							entity.isTitle = true;
//							list.add(entity);
//							List<Exam> exam_list = examination.exam_list;
//							for (int j = 0; j < exam_list.size(); j++) {
//								Entity entity2 = new Entity();
//								Exam exam = exam_list.get(j);
//								entity2.first = exam.first;
//								entity2.second = exam.second;
//								entity2.body = exam.body;
//								entity2.isTitle = false;
//								list.add(entity2);
//							}
//						}
//						list_lv.setAdapter(new ListAdapter());
//						Util.setListViewHeightBasedOnChildren(list_lv);
//
//					}
//				}, new ErrorListener() {
//
//					@Override
//					public void onErrorResponse(VolleyError error) {
//						progress_pb.setVisibility(View.GONE);
//						try {
//							String message = (error.getMessage() == null) ? "网络访问失败,请您检查网络." : error.getMessage();
//							ToastDialog.showToast(getActivity(), message);
//						} catch (Exception e) {
//
//						}
//					}
//				}, new TypeToken<List<Examination>>() {
//				}.getType(), 1);
//
//		mQueue.add(request);
//		mQueue.start();
//	}

//	private class ListAdapter extends BaseAdapter {
//
//		private ViewHolder holder;
//
//		class ViewHolder {
//			TextView title_tv;
//			TextView first_tv;
//			TextView second_tv;
//			TextView body_tv;
//			LinearLayout content_ll;
//		}
//
//		@Override
//		public int getCount() {
//			Log.i("Examination", list.size() + "");
//			return list.size();
//		}
//
//		@Override
//		public Object getItem(int position) {
//			return null;
//		}
//
//		@Override
//		public long getItemId(int position) {
//			return 0;
//		}
//
//		@Override
//		public View getView(int position, View convertView, ViewGroup parent) {
//
//			Entity entity = list.get(position);
//
//			if (convertView == null) {
//				holder = new ViewHolder();
//				convertView = LayoutInflater.from(getActivity()).inflate(R.layout.item_examination, parent, false);
//				holder.title_tv = (TextView) convertView.findViewById(R.id.title_item_examination_tv);
//				holder.first_tv = (TextView) convertView.findViewById(R.id.first_item_examination_tv);
//				holder.second_tv = (TextView) convertView.findViewById(R.id.second_item_examination_tv);
//				holder.body_tv = (TextView) convertView.findViewById(R.id.body_item_examination_tv);
//				holder.content_ll = (LinearLayout) convertView.findViewById(R.id.content_item_examination_ll);
//
//				convertView.setTag(holder);
//
//			} else {
//				holder = (ViewHolder) convertView.getTag();
//			}
//
//			holder.content_ll.setVisibility(View.VISIBLE);
//			holder.title_tv.setVisibility(View.VISIBLE);
//
//			Log.i("Examination", entity.title + "isTitle = " + entity.isTitle);
//
//			if (entity.isTitle) {
//				holder.content_ll.setVisibility(View.GONE);
//				holder.title_tv.setText(entity.title+"年考题--" + major);
//			} else {
//				holder.title_tv.setVisibility(View.GONE);
//				holder.first_tv.setText(entity.first);
//				holder.second_tv.setText(entity.second);
//				holder.body_tv.setText(entity.body);
//			}
//
//			return convertView;
//		}
//
//	}

	

}
