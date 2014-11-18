package com.kongfuzi.student.ui.kao.college;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.ErrorListener;

import android.content.Intent;
import android.os.Bundle;
import android.provider.SyncStateContract.Constants;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.kongfuzi.student.R;

/**
 * @author LBDL
 * @desc ÕÐÉú¼òÕÂ(×¨ÒµÏêÇé)
 *
 */
public class RegulationsFragment extends Fragment {
	
	//´óÑ§id
	private int id;
	
	private ProgressBar progress_pb;
	private ListView list_lv;
	
//	private List<Prospectus> list = new ArrayList<Prospectus>();
	
	public static RegulationsFragment getInstance(int id){
		
		Bundle bundle = new Bundle();
		bundle.putInt("id", id);
		RegulationsFragment fragment = new RegulationsFragment();
		fragment.setArguments(bundle);
		return fragment;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_regulations, container, false);
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
		list_lv = (ListView) view.findViewById(R.id.list_prospectus_list_lv);
		progress_pb = (ProgressBar) view.findViewById(R.id.progress_prospectus_list_pb);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
//		mQueue = MainApplication.getRequestQueueInstance();
		
//		getData();
	}
	
//	private void getData() {
//		
//		NetworkRequest<List<Prospectus>> request = new NetworkRequest<List<Prospectus>>(Method.GET, Constants.PROSPECTUS_LIST + "&id=" + id, null, new Listener<List<Prospectus>>() {
//
//			@Override
//			public void onResponse(List<Prospectus> response) {
//				progress_pb.setVisibility(View.GONE);
//				if (response != null) {
//					list = response;
//					list_lv.setAdapter(new ListAdapter());
//				}
//			}
//		}, new ErrorListener() {
//
//			@Override
//			public void onErrorResponse(VolleyError error) {
//				progress_pb.setVisibility(View.GONE);
//				try {
//					String message = (error.getMessage() == null) ? "ÍøÂç·ÃÎÊÊ§°Ü,ÇëÄú¼ì²éÍøÂç." : error.getMessage();
//					ToastDialog.showToast(getActivity(), message);
//				} catch (Exception e) {
//				}
//			}
//		}, new TypeToken<List<Prospectus>>() {}.getType(), 1);
//
//		mQueue.add(request);
//		mQueue.start();
//	}
	
//	public class ListAdapter extends BaseAdapter {
//
//		private int index;
//
//		@Override
//		public int getCount() {
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
//			return position;
//		}
//
//		@Override
//		public View getView(int position, View convertView, ViewGroup parent) {
//			this.index = position;
//			View view = LayoutInflater.from(getActivity()).inflate(R.layout.item_question_list, parent, false);
//			TextView title_tv = (TextView) view.findViewById(R.id.major_item_question_tv);
//
//			title_tv.setText(list.get(position).title);
//
//			view.setOnClickListener(new OnClickListener() {
//
//				@Override
//				public void onClick(View v) {
//					Intent intent = new Intent(getActivity(), ProspectusDetailsActivity.class);
//					intent.putExtra("url", Constants.PROSPECTUS_DETAIL + "&id=" + list.get(index).id);
//					startActivity(intent);
//				}
//			});
//			return view;
//		}
//
//	}
		

}
