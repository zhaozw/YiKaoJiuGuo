package com.kongfuzi.student.ui.kao.filter;

import java.util.ArrayList;
import java.util.List;

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
 * @desc ��������
 * 
 */
public class SecondCategoryActivity extends BaseActivity implements DialogItemClick {

	private int MajorId;
	private ListView list_lv;

	// ��������
	private Conditions conditions = new Conditions();
	private List<Conditions> list = new ArrayList<Conditions>();

	// private List<Conditions> thirdList = new ArrayList<Conditions>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first_category);

		getIntentData();
		getData(0, UrlConstants.SECOND_MAJOR_CATEGORY + "&id=" + MajorId);
		onItemClickListener();

	}

	private void getIntentData() {
		Intent intent = getIntent();
		MajorId = intent.getIntExtra(BundleArgsConstants.CATEGORY_ID, 0);
		setTitle(intent.getStringExtra(BundleArgsConstants.TITLE));
	}

	private void getData(final int index, String urlString) {
		showLoadingDialog();
		ArrayRequest<List<Conditions>> request = new ArrayRequest<List<Conditions>>(urlString,
				new Listener<List<Conditions>>() {

					@Override
					public void onResponse(List<Conditions> response) {
						dismissLoadingDialog();
						list = response;
						if (index == 0) {
							list_lv.setAdapter(new CategoryAdapter(SecondCategoryActivity.this, response));

						} else if (index == 1) {
							ListDialogFragment fragment = ListDialogFragment.getInstance(response);
							fragment.show(getSupportFragmentManager(), "dialog");
						}

					}
				}, new TypeToken<List<Conditions>>() {
				}.getType());

		queue.add(request);
		queue.start();
	}

	private void onItemClickListener() {

		list_lv = (ListView) findViewById(R.id.list_first_category_lv);

		list_lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

				conditions = list.get(position);

				// ��������Conditions set �� list
				YiKaoApplication.getConditionsList().set(7, conditions);

				getData(1,UrlConstants.THIRD_MAJOR_CATEGORY + "&id=" + conditions.id);

				// Intent intent = new Intent(SecondCategoryActivity.this,
				// PopupWindowActivity.class);
				// intent.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
				// startActivity(intent);

			}
		});
	}

	/**
	 * ��һ�ν���ʱ��onCreate��������onNewIntent
	 * */
	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);

		getIntentData();
		getData(0, UrlConstants.SECOND_MAJOR_CATEGORY + "&id=" + MajorId);
		onItemClickListener();
	}

	/**
	 * �ص��ӿ�
	 * �õ����� �����Conditions
	 * @param position  
	 * */
	@Override
	public void dialogItemClickedListener(int position) {
		//��������Conditions set �� list
		Conditions conditions = list.get(position);
		YiKaoApplication.getConditionsList().set(8, conditions);
		
		Intent intent = new Intent(this, HomeActivity.class);
		Bundle bundle = new Bundle();
		
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		bundle.putSerializable(BundleArgsConstants.CONDITIONS, conditions);
		intent.putExtras(bundle);
		startActivity(intent);
	}

}
