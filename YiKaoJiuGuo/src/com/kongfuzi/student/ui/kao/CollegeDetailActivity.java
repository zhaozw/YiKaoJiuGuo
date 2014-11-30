package com.kongfuzi.student.ui.kao;

import java.lang.reflect.Type;

import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.gson.reflect.TypeToken;
import com.kongfuzi.lib.volley.Request.Method;
import com.kongfuzi.lib.volley.Response.Listener;
import com.kongfuzi.lib.volley.toolbox.JsonObjectRequest;
import com.kongfuzi.student.R;
import com.kongfuzi.student.app.YiKaoApplication;
import com.kongfuzi.student.bean.CollegeDetails;
import com.kongfuzi.student.support.network.ObjectRequest;
import com.kongfuzi.student.support.utils.BundleArgsConstants;
import com.kongfuzi.student.support.utils.SQLiteOperate;
import com.kongfuzi.student.support.utils.UrlConstants;
import com.kongfuzi.student.support.utils.Util;
import com.kongfuzi.student.ui.adapter.CollegeDetailsListViewAdapter;
import com.kongfuzi.student.ui.global.BaseActivity;

/**
 * @author ZL
 * @desc 大学详情
 * 
 */
/**
 * @author Administrator
 * 
 * @desc 大学详情
 * 
 */
public class CollegeDetailActivity extends BaseActivity implements OnClickListener {

	private Button examQues, enrollmentPlay, enrollment_guide;
	private ToggleButton collect_tbtn;
	private ImageView imageView;
	private ListView listView;
	private String url;
	private CollegeDetails collegeDetails;
	private TextView college, type, enrollNumber, totalMajor;
	private SQLiteOperate operate;

	protected static final String TAG = "CollegeDetailActivity";

	/**
	 * @param id
	 *            大学id
	 * */
	public static Intent newIntent(String id) {
		Intent intent = new Intent(YiKaoApplication.getInstance(), CollegeDetailActivity.class);
		intent.putExtra(BundleArgsConstants.COLLEGE_ID, id);
		return intent;
	}

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_college_detail);
		getArgument();
		initView();
		loadData();
		eventManager();
	}

	/**
	 * 得到传过来的参数
	 */
	private void getArgument() {
		Intent intent = getIntent();
		String id = intent.getStringExtra(BundleArgsConstants.COLLEGE_ID);
		// mode
		int method = YiKaoApplication.getConditionsList().get(BundleArgsConstants.MODE).id;
		// first
		int firstInt = YiKaoApplication.getConditionsList().get(BundleArgsConstants.MAJOR).id;
		// second
		int secondInt = YiKaoApplication.getConditionsList().get(8).id;
		// third
		int thirdInt = YiKaoApplication.getConditionsList().get(9).id;
		//
		int category = 0;
		if (thirdInt != 0) {
			category = thirdInt;
		} else if (secondInt != 0) {
			category = secondInt;
		} else {
			category = firstInt;
		}
		url = UrlConstants.COLLEGE_DETAILS + id + "&methods=" + method + "&category=" + category;

	}

	/**
	 * 加载数据
	 * 
	 * @param <T>
	 */
	private <T> void loadData() {
		Type type = new TypeToken<CollegeDetails>() {
		}.getType();
		showLoadingDialog();
		ObjectRequest<CollegeDetails> request = new ObjectRequest<CollegeDetails>(url, new Listener<CollegeDetails>() {

			@Override
			public void onResponse(CollegeDetails response) {
				dismissLoadingDialog();
				if (response != null) {

					collegeDetails = response;
					Log.i(TAG, "avatar = " + response.litpic);
					imageLoader.displayImage(response.litpic, imageView);
					college.setText(response.title);
					CollegeDetailActivity.this.type.setText(response.flag);
					enrollNumber.setText("招生人数" + response.people + "人");
					totalMajor.setText("总共" + response.proList.size() + "个专业");
					collect_tbtn.setChecked(response.isCollect);

					Log.i(TAG, "prolist = " + response.proList.size());
					CollegeDetailsListViewAdapter adapter = new CollegeDetailsListViewAdapter(response.proList,
							CollegeDetailActivity.this);
					listView.setAdapter(adapter);

				}

			}
		}, type);
		queue.add(request);
		queue.start();
	}

	/**
	 * 事件管理
	 */
	private void eventManager() {
		collect_tbtn.setOnClickListener(this);
		examQues.setOnClickListener(this);
		enrollmentPlay.setOnClickListener(this);
		enrollment_guide.setOnClickListener(this);

		imageView.setOnClickListener(this);
		// listView 的点击事件
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {// collegeDetails.proList.get(position).id
				Intent intent = MajorDetailActivity.newIntent(collegeDetails.proList.get(position).id,
						collegeDetails.id, collegeDetails.proList.get(position).major);
				startActivity(intent);
			}
		});
	}

	/**
	 * 初始化View
	 */
	private void initView() {
		operate = new SQLiteOperate();
		operate.setContext(CollegeDetailActivity.this);
		operate.createDB();
		// button
		collect_tbtn = (ToggleButton) findViewById(R.id.collect_college_category_tbtn);
		examQues = (Button) findViewById(R.id.exam_Ques_college_category_bt);
		enrollmentPlay = (Button) findViewById(R.id.enrollment_play_college_category_bt);
		enrollment_guide = (Button) findViewById(R.id.enrollment_guide_college_category_bt);
		// ImageView(college_category_imageView)
		imageView = (ImageView) findViewById(R.id.college_category_imageView);
		// listView
		listView = (ListView) findViewById(R.id.listView_college_category_lv);
		// TextView(number_college_category_tv)
		college = (TextView) findViewById(R.id.college_college_category_tv);
		type = (TextView) findViewById(R.id.type_college_category_tv);
		enrollNumber = (TextView) findViewById(R.id.enroll_number_college_category_tv);
		totalMajor = (TextView) findViewById(R.id.total_major_college_category_tv);
	}

	@Override
	public void onClick(View v) {
		Intent intent = null;
		switch (v.getId()) {
		case R.id.collect_college_category_tbtn:
			// 收藏
			operate.addData(collegeDetails);
			if (collegeDetails.isCollect) {
				join(0, UrlConstants.COLLECT_COLLEGE + "&mid=" + YiKaoApplication.getStudentId() + "&id="
						+ collegeDetails.id);
			} else {
				join(1, UrlConstants.COLLECT_COLLEGE + "&mid=" + YiKaoApplication.getStudentId() + "&id="
						+ collegeDetails.id);
			}

			break;
		case R.id.exam_Ques_college_category_bt:
			// 历年考题
			Log.i(TAG, "college id = " + collegeDetails.id);
			intent = PastExaminationPaper.newIntent(collegeDetails.id);
			startActivity(intent);
			break;
		case R.id.enrollment_play_college_category_bt:
			// 招生计划
			intent = EnrollPlay.newIntent(collegeDetails.id, collegeDetails.title);
			startActivity(intent);
			break;
		case R.id.enrollment_guide_college_category_bt:
			// 招生简章
			// intent=MajorDetailActivity.newIntent(collegeDetails.id,
			// collegeId, majorTitle);
			intent = EnrollGuideActivity.newIntent(collegeDetails.id);
			startActivity(intent);
			// intent.putExtra("", );
			break;
		case R.id.college_category_imageView:
			// imageView的点击事件
			break;
		}
	}

	private void join(final int index, final String url) {

		if (!Util.isLogin()) {
			return;
		}

		JsonObjectRequest request = new JsonObjectRequest(Method.GET, url, null, new Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {

				if (response.optBoolean("success")) {
					// 收藏
					if (index == 0) {
						Toast.makeText(YiKaoApplication.getInstance(), "收藏成功", Toast.LENGTH_SHORT).show();
						// kao_count_tv.setText(++kao_count + "人要考");

					}// 取消收藏
					else if (index == 1) {
						Toast.makeText(YiKaoApplication.getInstance(), "收藏失败", Toast.LENGTH_SHORT).show();
						// kao_count_tv.setText(--kao_count + "人要考");
						collect_tbtn.toggle();

					}
				} else {
					Toast.makeText(YiKaoApplication.getInstance(), response.optString("message"), Toast.LENGTH_SHORT)
							.show();
					// join_tbtn.toggle();
				}
			}
		}, null);

		queue.add(request);
		queue.start();
	}

}
