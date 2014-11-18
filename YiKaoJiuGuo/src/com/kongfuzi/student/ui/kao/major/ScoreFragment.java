package com.kongfuzi.student.ui.kao.major;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.kongfuzi.student.R;
/**
 * @author LBDL
 * @desc 录取分数(大学详情)
 *
 */
public class ScoreFragment extends Fragment {
	
	//专业id
	private static int id;
	
	private ProgressBar progress_pb;
	private ListView list_lv;
	
//	private RequestQueue mQueue;
	
	
	public static ScoreFragment getInstance(int id){
		
		ScoreFragment fragment = new ScoreFragment();
		Bundle bundle = new Bundle();
		bundle.putInt("id", id);
		fragment.setArguments(bundle);
		return fragment;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.item_score, container, false);
	}
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
//		mQueue = MainApplication.getRequestQueueInstance();
		
		progress_pb = (ProgressBar) view.findViewById(R.id.progress_more_addmission_pb);
		list_lv = (ListView) view.findViewById(R.id.list_more_admission_lv);
		
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
//		getData();
		
	}
	
//	private void getData() {
//		NetworkRequest<List<Score>> request = new NetworkRequest<List<Score>>(Method.GET, Constants.MORE_SCORE +
//				"&id=" + id, null, new Listener<List<Score>>() {
//
//			@Override
//			public void onResponse(List<Score> response) {
//				progress_pb.setVisibility(View.GONE);
//				if (!response.isEmpty() && response != null) {
//					
//					list_lv.setAdapter(new GridAdapter(response));
//				}
//				
//			}
//		}, new ErrorListener() {
//
//			@Override
//			public void onErrorResponse(VolleyError error) {
//				progress_pb.setVisibility(View.GONE);
//			}
//		}, new TypeToken<List<Score>>(){}.getType(), 1);
//		
//		mQueue.add(request);
//		mQueue.start();
//	}
	
//	private class GridAdapter extends BaseAdapter{
//		
//		private List<Score> list = new ArrayList<Score>();
//		
//		public GridAdapter(List<Score> list){
//			this.list = list;
//		}
//
//		@Override
//		public int getCount() {
//			return list.size() + 1;
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
//			convertView = LayoutInflater.from(getActivity()).inflate(R.layout.item_more_admission, parent, false);
//			
//			TextView site_tv = (TextView) convertView.findViewById(R.id.site_item_more_admission_tv);
//			TextView category_tv = (TextView) convertView.findViewById(R.id.category_item_more_admission_tv);
//			TextView hscore_tv = (TextView) convertView.findViewById(R.id.hscore_item_more_admission_tv);
//			TextView lscore_tv = (TextView) convertView.findViewById(R.id.lscore_item_more_admission_tv);
//			
//			if (position == 0) {
//				site_tv.setText("地区");
//				category_tv.setText("类别");
//				hscore_tv.setText("最高分");
//				lscore_tv.setText("最底分");
//			}else {
//				
//				Score score = list.get(position - 1);
//				
//				site_tv.setText(score.province);
//				category_tv.setText(score.course);
//				hscore_tv.setText(score.hscore);
//				lscore_tv.setText(score.lscore);
//				
//			}
//			
//			
//			return convertView;
//		}
//		
//	}

}
