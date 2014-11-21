package com.kongfuzi.student.ui.usercenter;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.reflect.TypeToken;
import com.kongfuzi.lib.volley.Response.Listener;
import com.kongfuzi.student.R;
import com.kongfuzi.student.bean.User;
import com.kongfuzi.student.support.MD5;
import com.kongfuzi.student.support.YiKaoApplication;
import com.kongfuzi.student.support.network.ObjectRequest;
import com.kongfuzi.student.support.utils.BundleArgsConstants;
import com.kongfuzi.student.support.utils.UrlConstants;
import com.kongfuzi.student.ui.global.BaseActivity;

public class RegisterActivity extends BaseActivity {

	private EditText pwd_et;
	private EditText subpwd_et;
	private Button register_btn;

	private String actionString;

	public static final String ACTION_REGISTER = "com.kongfuzi.student:register";
	public static final String ACTION_FIND_PWD = "com.kongfuzi.student.find_pwd";

	// private RequestQueue queue;

	public static Intent newIntent(String phone, String code, String action) {

		Intent intent = new Intent(YiKaoApplication.getInstance(), RegisterActivity.class);
		intent.putExtra(BundleArgsConstants.PHONE, phone);
		intent.putExtra(BundleArgsConstants.CODE, code);
		intent.setAction(action);

		return intent;
	}

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_register);

		pwd_et = (EditText) findViewById(R.id.pwd_register_et);
		subpwd_et = (EditText) findViewById(R.id.subpwd_register_et);
		register_btn = (Button) findViewById(R.id.register_register_btn);

		register_btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				register();
			}
		});
	}

	private void register() {

		String url = null;
		String pwdString = pwd_et.getText().toString();
		String subpwdString = subpwd_et.getText().toString();

		Intent intent = getIntent();
		actionString = intent.getAction();
		String phoneString = intent.getStringExtra(BundleArgsConstants.PHONE);
		String codeString = intent.getStringExtra(BundleArgsConstants.CODE);

		if (actionString.equals(ACTION_FIND_PWD)) {
			url = UrlConstants.FIND_PWD;
		} else if (actionString.equals(ACTION_REGISTER)) {
			url = UrlConstants.REGISTER;
		}

		if (url == null) {
			return;
		}
		url = url + "&phone=" + phoneString + "&vcode=" + codeString + "&pwd=" + new MD5().getMD5ofStr(pwdString);

		if (pwdString.equals(subpwdString) && !TextUtils.isEmpty(pwdString)) {

			ObjectRequest<User> request = new ObjectRequest<User>(url, new Listener<User>() {

				@Override
				public void onResponse(User response) {
					if (response != null) {
						toast("操作成功");
					}else {
						toast("操作失败");
					}
				}
			}, new TypeToken<User>() {
			}.getType());

			queue.add(request);
			queue.start();
		} else {
			toast("两次密码输入不一致");
		}

	}

}
