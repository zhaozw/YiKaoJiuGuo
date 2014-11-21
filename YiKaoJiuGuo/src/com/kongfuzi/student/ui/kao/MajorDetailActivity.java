package com.kongfuzi.student.ui.kao;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.kongfuzi.lib.volley.RequestQueue;
import com.kongfuzi.lib.volley.Response.Listener;
import com.kongfuzi.student.R;
import com.kongfuzi.student.bean.Major;
import com.kongfuzi.student.support.YiKaoApplication;
import com.kongfuzi.student.support.network.ObjectRequest;
import com.kongfuzi.student.support.utils.BundleArgsConstants;
import com.kongfuzi.student.support.utils.UrlConstants;
import com.kongfuzi.student.ui.global.BaseActivity;
import com.kongfuzi.student.ui.kao.major.ExaminationFragment;
import com.kongfuzi.student.ui.kao.major.IntroductionFragment;
import com.kongfuzi.student.ui.kao.major.RecruitFragment;
import com.kongfuzi.student.ui.kao.major.ScoreFragment;

/**
 * @author LBDL
 * @desc רҵ����
 * 
 */
public class MajorDetailActivity extends BaseActivity implements OnClickListener {

	private ImageView logo_iv;
	private TextView major_tv;
	private TextView college_tv;
	private TextView batch_tv;
	private TextView recruit_tv;
	private Button college_detail_btn;
	private Button join_btn;

	private int majorId;
	private int collegeId;

	private RequestQueue queue;
	private FragmentManager fragmentManager;
	private List<Fragment> fragmentsList = new ArrayList<Fragment>();
	private List<TextView> tabList = new ArrayList<TextView>();

	/**
	 * @param major_id
	 *            ,college_id
	 * 
	 * */
	public static Intent newIntent(int majorId, int collegeId) {
		Intent intent = new Intent(YiKaoApplication.getInstance(), MajorDetailActivity.class);
		intent.putExtra(BundleArgsConstants.MAJOR_ID, majorId);
		intent.putExtra(BundleArgsConstants.COLLEGE_ID, collegeId);

		return intent;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_major_detail);

		queue = YiKaoApplication.getQueueInstance();
		Intent intent = getIntent();
		majorId = intent.getIntExtra(BundleArgsConstants.MAJOR_ID, 0);
		collegeId = intent.getIntExtra(BundleArgsConstants.COLLEGE_ID, 0);

		initView();
		getData();
	}

	private void initView() {

		logo_iv = (ImageView) findViewById(R.id.logo_major_detail_iv);
		major_tv = (TextView) findViewById(R.id.major_major_detail_tv);
		college_tv = (TextView) findViewById(R.id.college_major_detail_tv);
		batch_tv = (TextView) findViewById(R.id.batch_major_detail_tv);
		recruit_tv = (TextView) findViewById(R.id.recruit_major_detail_tv);
		college_detail_btn = (Button) findViewById(R.id.college_major_detail_btn);
		join_btn = (Button) findViewById(R.id.join_image_detail_btn);

		tabList.add((TextView) findViewById(R.id.detail_major_detail_tv));
		tabList.add((TextView) findViewById(R.id.exam_major_detail_tv));
		tabList.add((TextView) findViewById(R.id.score_major_detail_tv));
		tabList.add((TextView) findViewById(R.id.introduce_major_detail_tv));

		college_detail_btn.setOnClickListener(this);
		join_btn.setOnClickListener(this);

		for (int i = 0; i < tabList.size(); i++) {
			tabList.get(i).setOnClickListener(this);
		}

		fragmentManager = getSupportFragmentManager();
		FragmentTransaction transaction = fragmentManager.beginTransaction();

		// TODO ��Ҫ�޸�
		fragmentsList.add(RecruitFragment.getInstance(1));
		fragmentsList.add(ExaminationFragment.getInstance(1, ""));
		fragmentsList.add(ScoreFragment.getInstance(1));
		fragmentsList.add(IntroductionFragment.getInstance(""));

		for (int i = 0; i < fragmentsList.size(); i++) {
			transaction.add(R.id.content_major_detail_fl, fragmentsList.get(i));
		}

		transaction.commit();
	}

	/**
	 * @param i
	 * @desc 0:�������� 1:���꿼��2:¼ȡ���� 3: רҵ����
	 * */
	private void setTabSelection(int i) {

		// ����һ��Fragment����
		FragmentTransaction transaction = fragmentManager.beginTransaction();

		for (int j = 0; j < fragmentsList.size(); j++) {

			Resources resources = getResources();
			TextView textView = tabList.get(j);

			if (i == j) {
				// ѡ�е�tab �ı���������ɫ �ͱ���ɫ

				textView.setBackgroundColor(resources.getColor(R.color.green));
				textView.setTextColor(resources.getColor(R.color.white));

				// ��ʾ��fragment
				transaction.show(fragmentsList.get(j));

			} else {
				// δѡ�е�tab �ָ���Ĭ��

				textView.setBackgroundColor(resources.getColor(R.color.light_grey));
				textView.setTextColor(resources.getColor(R.color.black));

				// ����δѡ�е�fragment
				transaction.hide(fragmentsList.get(j));
			}
		}

		transaction.commit();
	}

	private void getData() {

		showLoadingDialog();
		Intent intent = getIntent();

		ObjectRequest<Major> request = new ObjectRequest<Major>(UrlConstants.MAJOR_DETAIL + "&mid="
				+ YiKaoApplication.getStudentId() + "&id=" + intent.getStringExtra(BundleArgsConstants.MAJOR_ID),
				new Listener<Major>() {

					@Override
					public void onResponse(Major major) {
						dismissLoadingDialog();
					}
				}, new TypeToken<Major>() {
				}.getType());

		queue.add(request);
		queue.start();
	}

	@Override
	public void onClick(View v) {

		for (int i = 0; i < tabList.size(); i++) {

			if (v.getId() == tabList.get(i).getId()) {
				setTabSelection(i);
			}
		}
	}

}
