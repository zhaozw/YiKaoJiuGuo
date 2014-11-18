package com.kongfuzi.student.ui.kao;

import com.kongfuzi.student.R;
import com.kongfuzi.student.R.id;
import com.kongfuzi.student.R.layout;
import com.kongfuzi.student.ui.interfaces.BaseActivity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MajorDetailActivity extends BaseActivity implements OnClickListener{
	
	private ImageView logo_iv;
	private TextView major_tv;
	private TextView college_tv;
	private TextView batch_tv;
	private TextView recruit_tv;
	private Button college_detail_btn;
	private Button join_btn;
	private TextView detail_tv;
	private TextView exam_tv;
	private TextView score_tv;
	private TextView introduce_tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_major_detail);
		
		initView();
	}

	private void initView() {
		
		logo_iv = (ImageView) findViewById(R.id.logo_major_detail_iv);
		major_tv = (TextView) findViewById(R.id.major_major_detail_tv);
		college_tv = (TextView) findViewById(R.id.college_major_detail_tv);
		batch_tv = (TextView) findViewById(R.id.batch_major_detail_tv);
		recruit_tv = (TextView) findViewById(R.id.recruit_major_detail_tv);
		college_detail_btn = (Button) findViewById(R.id.college_major_detail_btn);
		join_btn = (Button) findViewById(R.id.join_image_detail_btn);
		
		detail_tv = (TextView) findViewById(R.id.detail_major_detail_tv);
		exam_tv = (TextView) findViewById(R.id.exam_major_detail_tv);
		score_tv = (TextView) findViewById(R.id.score_major_detail_tv);
		introduce_tv = (TextView) findViewById(R.id.introduce_major_detail_tv);
		
		college_detail_btn.setOnClickListener(this);
		join_btn.setOnClickListener(this);
		detail_tv.setOnClickListener(this);
		exam_tv.setOnClickListener(this);
		score_tv.setOnClickListener(this);
		introduce_tv.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		
		switch (v.getId()) {
		//大学详情
		case R.id.college_major_detail_btn:
			
			break;
		//加入专业
		case R.id.join_image_detail_btn:
			
			break;
		//招生详情
		case R.id.detail_major_detail_tv:
			
			break;
		//历年考题
		case R.id.exam_major_detail_tv:
			
			break;
		//录取分数
		case R.id.score_major_detail_tv:
			
			break;
		//专业介绍
		case R.id.introduce_major_detail_tv:
			
			break;

		default:
			break;
		}
	}


}
