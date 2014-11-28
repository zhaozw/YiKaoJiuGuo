package com.kongfuzi.student.ui.kao.filter;

import java.util.List;

import android.R.integer;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.google.gson.reflect.TypeToken;
import com.kongfuzi.lib.volley.Response.Listener;
import com.kongfuzi.student.R;
import com.kongfuzi.student.app.YiKaoApplication;
import com.kongfuzi.student.bean.Conditions;
import com.kongfuzi.student.support.network.ArrayRequest;
import com.kongfuzi.student.support.utils.BundleArgsConstants;
import com.kongfuzi.student.support.utils.UrlConstants;
import com.kongfuzi.student.ui.adapter.CategoryAdapter;
import com.kongfuzi.student.ui.global.BaseActivity;
import com.kongfuzi.student.ui.global.ListDialogFragment;
import com.kongfuzi.student.ui.global.ListDialogFragment.DialogItemClick;
import com.kongfuzi.student.ui.kao.HomeActivity;

/**
 * @author LBDL
 * @desc 二级分类
 * 
 */
public class SecondCategoryActivity extends BaseActivity implements DialogItemClick {

	private int MajorId;
	private ListView list_lv;
	
	//一级分类ename
	private String titleString;
	//二级分类ename
	private String secondTitleString;

	// 二级分类
//	private Conditions conditions = new Conditions();
//	private List<Conditions> list = new ArrayList<Conditions>();

	// private List<Conditions> thirdList = new ArrayList<Conditions>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first_category);
		
		getIntentData();
		//二级分类
		getData(0, UrlConstants.SECOND_MAJOR_CATEGORY + "&id=" + MajorId);
		onItemClickListener();

	}

	private void getIntentData() {
		Intent intent = getIntent();
		titleString = intent.getStringExtra(BundleArgsConstants.TITLE);
		MajorId = intent.getIntExtra(BundleArgsConstants.CATEGORY_ID, 0);
		setTitle(titleString);
	}

	private void getData(final int index, String urlString) {
		showLoadingDialog();
		ArrayRequest<List<Conditions>> request = new ArrayRequest<List<Conditions>>(urlString,
				new Listener<List<Conditions>>() {

					@Override
					public void onResponse(List<Conditions> response) {
						
						dismissLoadingDialog();
						
						Conditions conditions = new Conditions();
						conditions.id = 0;
						conditions.ename = "全部";
						response.add(0, conditions);
						
						if (index == 0) {
							//二级分类
							list_lv.setAdapter(new CategoryAdapter(SecondCategoryActivity.this, response));

						} else if (index == 1) {
							//三级分类
							ListDialogFragment fragment = ListDialogFragment.getInstance(response);
							fragment.show(getSupportFragmentManager(), "dialog");
						}

					}
				}, new TypeToken<List<Conditions>>() {
				}.getType());

		queue.add(request);
		queue.start();
	}

	/**
	 * 二级分类的item
	 * */
	private void onItemClickListener() {

		list_lv = (ListView) findViewById(R.id.list_first_category_lv);

		list_lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

				Object object = parent.getItemAtPosition(position);
				
				if (object !=null && object instanceof Conditions) {
					Conditions conditions = (Conditions) object;
					secondTitleString = conditions.ename;
					// 二级分类Conditions set 在 list
					YiKaoApplication.getConditionsList().set(8, conditions);
					
					if (position == 0) {
						//全部
						conditions.ename = titleString;
						backHome(0,conditions);
						
					} else {
						getData(1,UrlConstants.THIRD_MAJOR_CATEGORY + "&id=" + conditions.id);
					}
					
				}


				// Intent intent = new Intent(SecondCategoryActivity.this,
				// PopupWindowActivity.class);
				// intent.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
				// startActivity(intent);

			}
		});
	}
	
	/**
	 * 返回首页
	 * 
	 * */
	private void backHome(int index,Conditions conditions) {
		Intent intent = new Intent(this, HomeActivity.class);
		Bundle bundle = new Bundle();
		
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		bundle.putSerializable(BundleArgsConstants.CONDITIONS, conditions);
		bundle.putInt("index", index);
		intent.putExtras(bundle);
		startActivity(intent);
	}

	/**
	 * 第一次进入时走onCreate，不会走onNewIntent
	 * */
	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);

		getIntentData();
		getData(0, UrlConstants.SECOND_MAJOR_CATEGORY + "&id=" + MajorId);
		onItemClickListener();
	}

	/**
	 * 回调接口
	 * 得到三级 分类的Conditions
	 * @param position  
	 * */
	@Override
	public void dialogItemClickedListener(Conditions c) {
		//三级分类Conditions set 在 list
		Conditions conditions = c;
		//如果点击的是全部 返回二级分类的ename
		if (conditions.ename.equals("全部")) {
			conditions.ename = secondTitleString;
		}
		YiKaoApplication.getConditionsList().set(9, conditions);
		
		backHome(1,conditions);
	}

}
