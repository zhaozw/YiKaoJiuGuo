package com.kongfuzi.student.ui.kao.college;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.kongfuzi.student.R;
/**
 * @author LBDL
 * @desc 招生计划(大学详情)
 *
 */
public class PlanFragment extends Fragment {
	
	private int id;
	
	private WebView plan_wv;
	private ProgressBar progress_pb;
	

	public static PlanFragment getInstance(int i){
		
		PlanFragment fragment = new PlanFragment();
		Bundle bundle = new Bundle();
		bundle.putInt("id", i);
		
		fragment.setArguments(bundle);
		return fragment;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_plan, container, false);
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
		progress_pb = (ProgressBar) view.findViewById(R.id.progress_plan_pb);
		plan_wv = (WebView) view.findViewById(R.id.content_plan_wv);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		plan_wv.getSettings().setJavaScriptEnabled(true);
		plan_wv.requestFocus();
//		plan_wv.loadUrl(Constants.PLAN+"&id="+id);
		plan_wv.setWebViewClient(new PlanWebViewClient());
		
	}
	
	private class PlanWebViewClient extends WebViewClient{
		
		
		@Override
		public void onPageStarted(WebView view, String url, Bitmap favicon) {
			super.onPageStarted(view, url, favicon);
		}
		
		@Override
		public void onPageFinished(WebView view, String url) {
			super.onPageFinished(view, url);
			progress_pb.setVisibility(View.GONE);
		}
	}

}
