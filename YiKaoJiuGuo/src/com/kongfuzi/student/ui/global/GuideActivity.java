package com.kongfuzi.student.ui.global;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.ListView;

import com.google.gson.reflect.TypeToken;
import com.kongfuzi.lib.volley.Request.Method;
import com.kongfuzi.lib.volley.RequestQueue;
import com.kongfuzi.lib.volley.Response.ErrorListener;
import com.kongfuzi.lib.volley.Response.Listener;
import com.kongfuzi.lib.volley.VolleyError;
import com.kongfuzi.student.R;
import com.kongfuzi.student.app.YiKaoApplication;
import com.kongfuzi.student.bean.Conditions;
import com.kongfuzi.student.support.network.ArrayRequest;
import com.kongfuzi.student.support.utils.UrlConstants;
import com.kongfuzi.student.ui.usercenter.LoginActivity;

/**
 * @author LBDL
 * @desc 引导页
 * 
 */
public class GuideActivity extends BaseActivity {

	private ViewPager guide_vp;
	private ListView list3_lv;
	private ListView list4_lv;
	private Button start_btn;
	private List<View> list = new ArrayList<View>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_guide);

		guide_vp = (ViewPager) findViewById(R.id.pager_guide_vp);

		ImageView page1 = new ImageView(this);
		ImageView page2 = new ImageView(this);
		page1.setScaleType(ScaleType.FIT_XY);
		page2.setScaleType(ScaleType.FIT_XY);
		page1.setImageResource(R.drawable.navigation_first);
		page2.setImageResource(R.drawable.navigation_second);
		View page3 = getLayoutInflater().inflate(R.layout.navigation_third, null);
		View page4 = getLayoutInflater().inflate(R.layout.navigation_fourth, null);

		list3_lv = (ListView) page3.findViewById(R.id.list_navigation_third_lv);
		list4_lv = (ListView) page4.findViewById(R.id.list_navigation_fourth_lv);

		list.add(page1);
		list.add(page2);
		list.add(page3);
		list.add(page4);

		getData(UrlConstants.PROVINCE);
		getData(UrlConstants.HOME_CATEGORY);

		guide_vp.setAdapter(new MyPagerAdapter());

	}

	private void getData(final String url) {

		ArrayRequest<ArrayList<Conditions>> request = new ArrayRequest<ArrayList<Conditions>>(url,
				new Listener<ArrayList<Conditions>>() {

					@Override
					public void onResponse(ArrayList<Conditions> response) {

						if (response != null) {
							if (url.equals(UrlConstants.PROVINCE)) {
								list3_lv.setAdapter(new ListAdapter(response, 0));

							} else {
								list4_lv.setAdapter(new ListAdapter(response, 1));
							}
						}
					}
				}, new TypeToken<ArrayList<Conditions>>() {
				}.getType());

		queue.add(request);
		queue.start();
	}

	private class ListAdapter extends BaseAdapter {

		// 0:生源地 1:专业
		private int index;
		private int id;
		private ArrayList<Conditions> category_list = new ArrayList<Conditions>();

		public ListAdapter(ArrayList<Conditions> category_list, int index) {
			this.category_list = category_list;
			this.index = index;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return category_list.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			convertView = getLayoutInflater().inflate(R.layout.item_navigation, parent, false);
			Button item_btn = (Button) convertView.findViewById(R.id.category_item_navigation_btn);
			item_btn.setText(category_list.get(position).ename);
			id = category_list.get(position).id;
			item_btn.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					switch (index) {
					case 0:
						YiKaoApplication.putOriginZone(id);
						guide_vp.setCurrentItem(guide_vp.getCurrentItem() + 1);
						break;
					case 1:
						YiKaoApplication.putMajor(id);
						startActivity(new Intent(GuideActivity.this, LoginActivity.class));
						GuideActivity.this.finish();
						break;

					default:
						break;
					}
				}
			});
			return convertView;
		}

	}

	private class MyPagerAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			View child = list.get(position);
			ViewParent parent = child.getParent();
			if (parent != null) {
				((ViewGroup) parent).removeView(container);
			}
			container.addView(child);
			return child;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}

		@Override
		public boolean isViewFromObject(View view, Object obj) {
			return view == obj;
		}

	}

}
