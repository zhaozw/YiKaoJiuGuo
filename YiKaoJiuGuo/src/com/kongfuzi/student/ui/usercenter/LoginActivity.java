package com.kongfuzi.student.ui.usercenter;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.kongfuzi.lib.volley.RequestQueue;
import com.kongfuzi.lib.volley.Response.Listener;
import com.kongfuzi.student.R;
import com.kongfuzi.student.bean.User;
import com.kongfuzi.student.support.MD5;
import com.kongfuzi.student.support.YiKaoApplication;
import com.kongfuzi.student.support.network.ObjectRequest;
import com.kongfuzi.student.support.utils.UrlConstants;
import com.kongfuzi.student.support.utils.Util;
import com.kongfuzi.student.ui.global.BaseActivity;
import com.kongfuzi.student.ui.kao.HomeActivity;
import com.umeng.analytics.MobclickAgent;

/**
 * @author LBDL
 * @desc µÇÂ¼Ò³Ãæ
 * @create 2014-8-5
 * 
 */
public class LoginActivity extends BaseActivity implements OnClickListener {

	private EditText phone_et;
	private EditText pwd_et;
	private Button login_btn;
	private TextView register_tv;
	private TextView resetpwd_tv;

	private String phone;
	private String password;
	private RequestQueue queue;

	public static final String TAG = "LoginActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		initView();
	}

	@Override
	protected void onResume() {
		super.onResume();
		MobclickAgent.onPageStart(TAG);
	}

	private void initView() {

		queue = YiKaoApplication.getQueueInstance();

		register_tv = (TextView) findViewById(R.id.register_login_tv);
		phone_et = (EditText) findViewById(R.id.phone_login_et);
		pwd_et = (EditText) findViewById(R.id.pwd_login_et);
		login_btn = (Button) findViewById(R.id.login_login_btn);
		resetpwd_tv = (TextView) findViewById(R.id.foget_login_tv);

		String phone = YiKaoApplication.getPhone();
		if (TextUtils.isEmpty(phone)) {
			phone_et.setText(String.valueOf(phone));
		}

		login_btn.setOnClickListener(this);
		register_tv.setOnClickListener(this);
		resetpwd_tv.setOnClickListener(this);

	}

	private void confirmLoginInfo() {

		phone = phone_et.getText().toString();
		password = pwd_et.getText().toString();

		if ((!TextUtils.isEmpty(phone)) && (!TextUtils.isEmpty(password)) && (Util.isCellphone(phone))) {
			login();

		} else {
			toast("ÊÖ»úºÅ»òÃÜÂëÎª¿Õ");
		}
	}

	private void login() {
		showLoadingDialog();
		ObjectRequest<User> request = new ObjectRequest<User>(UrlConstants.LOGIN + "&username=" + phone + "&pwd="
				+ new MD5().getMD5ofStr(password), new Listener<User>() {

			@Override
			public void onResponse(User user) {
				dismissLoadingDialog();
				
				if (user != null) {
					YiKaoApplication.putStudentId(user.id, phone, user.secretkey);
					startActivity(new Intent(LoginActivity.this, HomeActivity.class));
					finish();
				}else {
//					toast(user.message);
				}
			}
		}, new TypeToken<User>() {}.getType());

		queue.add(request);
		queue.start();
	}

	@Override
	public void onClick(View view) {

		String action = null;

		switch (view.getId()) {
		case R.id.login_login_btn:
			confirmLoginInfo();
			break;
		// ×¢²á
		case R.id.register_login_tv:
			action = RegisterActivity.ACTION_REGISTER;
			break;
		// ÕÒ»ØÃÜÂë
		case R.id.foget_login_tv:
			action = RegisterActivity.ACTION_FIND_PWD;
			break;
		default:
			break;
		}

		if (action != null) {
			Intent intent = CodeActivity.newIntent(action);
			startActivity(intent);
		}

	}

	@Override
	protected void onPause() {
		MobclickAgent.onPageEnd(TAG);
		super.onPause();
	}

}
