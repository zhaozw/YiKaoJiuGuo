package com.kongfuzi.student.ui.usercenter;

import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.kongfuzi.lib.volley.Request.Method;
import com.kongfuzi.lib.volley.RequestQueue;
import com.kongfuzi.lib.volley.Response.Listener;
import com.kongfuzi.lib.volley.toolbox.JsonObjectRequest;
import com.kongfuzi.student.R;
import com.kongfuzi.student.app.YiKaoApplication;
import com.kongfuzi.student.support.utils.UrlConstants;
import com.kongfuzi.student.support.utils.Util;
import com.kongfuzi.student.ui.global.BaseActivity;
import com.umeng.analytics.MobclickAgent;

/**
 * @author LBDL
 * @desc 获取验证码
 * @create 2014-8-13
 * 
 */
public class CodeActivity extends BaseActivity implements OnClickListener {

	private EditText phone_et;
	private EditText code_et;
	private Button code_btn;
	private Button register_btn;

	private String phone;
	private String code;
	private RequestQueue queue;
	
	public static Intent newIntent(String action) {
		Intent intent = new Intent(YiKaoApplication.getInstance(), CodeActivity.class);
		intent.setAction(action);
		return intent;
	}

	private static final String TAG = "RegisterActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_code);

		queue = YiKaoApplication.getQueueInstance();

		initView();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		MobclickAgent.onPageStart(TAG);
	}

	private void initView() {

		phone_et = (EditText) findViewById(R.id.phone_register_et);
		code_et = (EditText) findViewById(R.id.code_register_et);
		code_btn = (Button) findViewById(R.id.code_register_btn);
		register_btn = (Button) findViewById(R.id.submit_register_btn);

		register_btn.setOnClickListener(this);
		code_btn.setOnClickListener(this);

	}

	private void confirmRegisterInfo() {

		code = code_et.getText().toString();

		if ((!TextUtils.isEmpty(phone) && (!TextUtils.isEmpty(code)))) {

			if (Util.isCellphone(phone)) {
				getCode();
			} else {
				toast("手机号格式不对");
			}
		} else {
			toast("手机号或者验证码为空");
		}
	}

	/** 获取注册码 */
	public void getCode() {
		JsonObjectRequest request = new JsonObjectRequest(Method.GET, UrlConstants.GET_CODE + "?phone=" + phone, null,
				new Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
						if (response.optBoolean("success")) {
							toast("验证码已经成功发送到您的手机上,请注意查收!!!");
						} else {
							toast(response.optString("message"));
						}
					}
				}, null);
		queue.add(request);
		queue.start();
	}

	@Override
	public void onClick(View view) {

		switch (view.getId()) {

		// 获取手机验证码
		case R.id.code_register_btn:
			phone = phone_et.getText().toString();
			confirmRegisterInfo();
			break;

		// 下一步
		case R.id.submit_register_btn:
			
			String actionString = getIntent().getAction();
			String codeString = code_et.getText().toString();
			
			if (!TextUtils.isEmpty(codeString)) {
				
				Intent intent = RegisterActivity.newIntent(phone, codeString,actionString);
				startActivity(intent);
			}else {
				toast("验证码为空");
			}
			break;

		default:
			break;
		}

	}

	@Override
	protected void onPause() {
		MobclickAgent.onPageEnd(TAG);
		super.onPause();
	}

}
