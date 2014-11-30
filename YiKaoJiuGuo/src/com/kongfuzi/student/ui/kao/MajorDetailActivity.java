package com.kongfuzi.student.ui.kao;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.gson.reflect.TypeToken;
import com.kongfuzi.lib.volley.Request.Method;
import com.kongfuzi.lib.volley.RequestQueue;
import com.kongfuzi.lib.volley.Response.Listener;
import com.kongfuzi.lib.volley.toolbox.JsonObjectRequest;
import com.kongfuzi.student.R;
import com.kongfuzi.student.app.YiKaoApplication;
import com.kongfuzi.student.bean.Major;
import com.kongfuzi.student.support.network.ObjectRequest;
import com.kongfuzi.student.support.utils.BundleArgsConstants;
import com.kongfuzi.student.support.utils.UrlConstants;
import com.kongfuzi.student.support.utils.Util;
import com.kongfuzi.student.ui.global.BaseActivity;
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
	private TextView major_tv,titleTextView,baokaoTextView;
	private TextView college_tv;
	private TextView batch_tv;
	private TextView recruit_tv;
	private Button college_detail_btn,backButton,baokaoButton;
	private ToggleButton join_tbtn;
	private  static int selectNumber=0;
	private String majorId;
	private String collegeId;
	private String majorTitle;
	private Major major;
	// רҵ����
	private String body;

	private FragmentManager fragmentManager;
	private List<Fragment> fragmentsList = new ArrayList<Fragment>();
	private List<TextView> tabList = new ArrayList<TextView>();
	
	/**
	 * @param major_id
	 * 
	 * 
	 * */
	public static Intent newIntent(String majorId, String collegeId,String majorTitle) {
		Intent intent = new Intent(YiKaoApplication.getInstance(), MajorDetailActivity.class);
		intent.putExtra(BundleArgsConstants.MAJOR_ID, majorId);
		intent.putExtra(BundleArgsConstants.COLLEGE_ID, collegeId);
		intent.putExtra(BundleArgsConstants.MARJORDETAIL_TITLE, majorTitle);
		return intent;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_major_detail);
		
		Intent intent = getIntent();
		majorId = intent.getStringExtra(BundleArgsConstants.MAJOR_ID);
		collegeId = intent.getStringExtra(BundleArgsConstants.COLLEGE_ID);
		majorTitle=intent.getStringExtra(BundleArgsConstants.MARJORDETAIL_TITLE);
		initView();
		getData();

//		fragmentManager = getSupportFragmentManager();
//		FragmentTransaction transaction = fragmentManager.beginTransaction();
//		
//		fragmentsList.add(RecruitFragment.getInstance(majorId));
////		fragmentsList.add(ExaminationFragment.getInstance(Integer.parseInt(majorId), ""));
//		fragmentsList.add(ScoreFragment.getInstance(majorId));
//		fragmentsList.add(IntroductionFragment.getInstance(body));
//		System.out.println("-------body----"+body);
//		
//		for (int i = 0; i < fragmentsList.size(); i++) {
//			transaction.add(R.id.content_major_detail_fl, fragmentsList.get(i));
//		}
//
//		transaction.commit();
//		setTabSelection(0);
	}

	private void initView() {
		backButton=(Button) findViewById(R.id.major_detail_back_btn);
		titleTextView=(TextView) findViewById(R.id.major_detail_title_tv);
		baokaoTextView=(TextView) findViewById(R.id.major_detail_baokao_tv);
		baokaoButton=(Button) findViewById(R.id.major_detail_join);
		
		logo_iv = (ImageView) findViewById(R.id.logo_major_detail_iv);
		major_tv = (TextView) findViewById(R.id.major_detail_major_tv);
		college_tv = (TextView) findViewById(R.id.major_detail_college_tv);
		batch_tv = (TextView) findViewById(R.id.major_detail_batch_tv);
		recruit_tv = (TextView) findViewById(R.id.major_detail_recruit_tv);
//		college_detail_btn = (Button) findViewById(R.id.college_major_detail_btn);
//		join_tbtn = (ToggleButton) findViewById(R.id.join_image_detail_tbtn);

		tabList.add((TextView) findViewById(R.id.detail_major_detail_tv));
//		tabList.add((TextView) findViewById(R.id.exam_major_detail_tv));
		tabList.add((TextView) findViewById(R.id.score_major_detail_tv));
		tabList.add((TextView) findViewById(R.id.introduce_major_detail_tv));

//		college_detail_btn.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
////				Intent intent = CollegeDetailActivity.newIntent(collegeId);
////				startActivity(intent);
//			}
//		});
//		baokaoButton.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				
//				
//			}
//		});
		backButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		titleTextView.setText(majorTitle);
		
//		baokaoButton.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				
//				Intent intent = getIntent();
//				int studentId = YiKaoApplication.getStudentId();
//				int majorId = intent.getIntExtra(BundleArgsConstants.MAJOR_ID, 0);
//				int collegeId = intent.getIntExtra(BundleArgsConstants.COLLEGE_ID, 0);
//				
//				
//				if (selectNumber%2==0) {
//					// ����־Ը
//					join(0, UrlConstants.JOIN_VOLUNTEER + "&mid=" + studentId + "&id=" + majorId + "&aid=" + collegeId);
//					baokaoButton.setBackgroundResource(R.drawable.major_detail_join_selected);
//					int num=Integer.parseInt(baokaoTextView.getText().toString().trim().substring(0,baokaoTextView.getText().toString().trim().indexOf("��")));
//					System.out.println("-----�����˶�����"+num);
//					baokaoTextView.setText(++num+"�˱���");
//				} else {
//					// ȡ������־Ը
//					join(1, UrlConstants.DELETE_VOLUNTEER + "&mid=" + studentId + "&id=" + majorId);
//					baokaoButton.setBackgroundResource(R.drawable.major_detail_join_unselected);
//					int num=Integer.parseInt(baokaoTextView.getText().toString().trim().substring(0,baokaoTextView.getText().toString().trim().indexOf("��")));
//					baokaoTextView.setText(--num+"�˱���");
//				}
//				selectNumber++;
//			}
//		});

		for (int i = 0; i < tabList.size(); i++) {
			tabList.get(i).setOnClickListener(this);
		}

//		fragmentManager = getSupportFragmentManager();
//		FragmentTransaction transaction = fragmentManager.beginTransaction();
//		
//		fragmentsList.add(RecruitFragment.getInstance(majorId));
////		fragmentsList.add(ExaminationFragment.getInstance(Integer.parseInt(majorId), ""));
//		fragmentsList.add(ScoreFragment.getInstance(majorId));
//		fragmentsList.add(IntroductionFragment.getInstance(body));
//		System.out.println("-------body----"+body);
//		
//		for (int i = 0; i < fragmentsList.size(); i++) {
//			transaction.add(R.id.content_major_detail_fl, fragmentsList.get(i));
//		}
//
//		transaction.commit();
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

	/** ����(ɾ��)־Ը */
	private void join(final int index, final String url) {

		if (!Util.isLogin()) {
			return;
		}

		JsonObjectRequest request = new JsonObjectRequest(Method.GET, url, null, new Listener<JSONObject>() {
			
			@Override
			public void onResponse(JSONObject response) {
				
				if (response.optBoolean("success")) {
					// ����־Ը
					if (index == 0) {
						Toast.makeText(MajorDetailActivity.this, "����־Ը�ɹ�", Toast.LENGTH_SHORT).show();
						// kao_count_tv.setText(++kao_count + "��Ҫ��");

					}// ȡ������־Ը
					else if (index == 1) {
						Toast.makeText(MajorDetailActivity.this, "ȡ������ɹ�", Toast.LENGTH_SHORT).show();
						// kao_count_tv.setText(--kao_count + "��Ҫ��");
					}
				} else {
					Toast.makeText(MajorDetailActivity.this, response.optString("message"), Toast.LENGTH_SHORT).show();
//					join_tbtn.toggle();
				}
			}
		}, null);

		queue.add(request);
		queue.start();
	}

	/** ��ȡרҵ�������� */
	private void getData() {

		showLoadingDialog();
		Intent intent = getIntent();
		
		ObjectRequest<Major> request = new ObjectRequest<Major>(UrlConstants.MAJOR_DETAIL + "&mid="
				+ YiKaoApplication.getStudentId() + "&id=" + intent.getStringExtra(BundleArgsConstants.MAJOR_ID),
				new Listener<Major>() {
			
					@Override
					public void onResponse(Major m) {
						dismissLoadingDialog();
						major=m;
						if (major != null) {
							imageLoader.displayImage(major.avatar, logo_iv);
							major_tv.setText(major.major);
							college_tv.setText("(" + major.collegeName + ")");
							batch_tv.setText(major.batch);
							recruit_tv.setText("��������:" + major.recruit_count);
							baokaoTextView.setText(major.reg+"�˱���");
							
							baokaoButton.setOnClickListener(new OnClickListener() {
								
								@Override
								public void onClick(View v) {
									
									Intent intent = getIntent();
									int studentId = YiKaoApplication.getStudentId();
									int majorId = intent.getIntExtra(BundleArgsConstants.MAJOR_ID, 0);
									int collegeId = intent.getIntExtra(BundleArgsConstants.COLLEGE_ID, 0);
									
									if (selectNumber%2==0) {
										// ����־Ը
										join(0, UrlConstants.JOIN_VOLUNTEER + "&mid=" + studentId + "&id=" + majorId + "&aid=" + collegeId);
										
										int num=Integer.parseInt(baokaoTextView.getText().toString().trim().substring(0,baokaoTextView.getText().toString().trim().indexOf("��")));
										System.out.println("-----�����˶�����"+num);
										if(major.isHidden){
											baokaoTextView.setText(++num+"�˱���");
											baokaoButton.setBackgroundResource(R.drawable.major_detail_join_selected);
										}else {
											Toast.makeText(YiKaoApplication.getInstance(), "�ף�һ����ѧֻ�ܼ���һ��־Ը", Toast.LENGTH_SHORT).show();
										}
									} else {
										// ȡ������־Ը
										join(1, UrlConstants.DELETE_VOLUNTEER + "&mid=" + studentId + "&id=" + majorId);
										int num=Integer.parseInt(baokaoTextView.getText().toString().trim().substring(0,baokaoTextView.getText().toString().trim().indexOf("��")));
										if(major.isHidden){
										baokaoTextView.setText(--num+"�˱���");
										baokaoButton.setBackgroundResource(R.drawable.major_detail_join_unselected);
										}else {
											Toast.makeText(YiKaoApplication.getInstance(), "�ף�һ����ѧֻ�ܼ���һ��־Ը", Toast.LENGTH_SHORT).show();
										}
									}
									selectNumber++;
								}
							});
							
							body = major.body;
//							System.out.println("-----body------"+body);
							fragmentManager = getSupportFragmentManager();
							FragmentTransaction transaction = fragmentManager.beginTransaction();
							
							fragmentsList.add(RecruitFragment.getInstance(majorId));
//							fragmentsList.add(ExaminationFragment.getInstance(Integer.parseInt(majorId), ""));
							fragmentsList.add(ScoreFragment.getInstance(majorId));
							fragmentsList.add(IntroductionFragment.getInstance(body));
							System.out.println("-------body----"+body);
							
							for (int i = 0; i < fragmentsList.size(); i++) {
								transaction.add(R.id.content_major_detail_fl, fragmentsList.get(i));
							}

							transaction.commit();
							setTabSelection(0);
						}

					}
				}, new TypeToken<Major>() {}.getType());

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
