package com.kongfuzi.student.ui.setting;

import org.json.JSONObject;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.kongfuzi.lib.volley.Request.Method;
import com.kongfuzi.lib.volley.Response.Listener;
import com.kongfuzi.lib.volley.toolbox.JsonObjectRequest;
import com.kongfuzi.student.R;
import com.kongfuzi.student.app.YiKaoApplication;
import com.kongfuzi.student.support.MD5;
import com.kongfuzi.student.support.utils.UrlConstants;
import com.kongfuzi.student.ui.global.BaseActivity;
import com.umeng.analytics.MobclickAgent;

/**
 * @author LBDL
 * @desc 修改密码
 * 
 */
public class ModifyPasswordActivity extends BaseActivity implements OnClickListener {

	private EditText old_pwd_et;
	private EditText new_pwd_et;
	private EditText sub_pwd_et;
	private Button modify_btn;

	public static final String TAG = "ModifyPasswordActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_modifypwd);

		old_pwd_et = (EditText) findViewById(R.id.old_pwd_modifypwd_et);
		new_pwd_et = (EditText) findViewById(R.id.new_pwd_modifypwd_et);
		sub_pwd_et = (EditText) findViewById(R.id.sub_pwd_modifypwd_et);
		modify_btn = (Button) findViewById(R.id.modify_modifpwd_btn);

		modify_btn.setOnClickListener(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
		MobclickAgent.onPageStart(TAG);
	}

	private void submit() {

		if (!isLogin()) {
			return;
		}

		MD5 md5 = new MD5();
		String oldpwdString = old_pwd_et.getText().toString();
		String newpwdString = new_pwd_et.getText().toString();
		String subpwdString = sub_pwd_et.getText().toString(); 
		Log.i(TAG, "old = " + oldpwdString);

		if (!TextUtils.isEmpty(oldpwdString) && !TextUtils.isEmpty(newpwdString) && !TextUtils.isEmpty(subpwdString)) {

			if (!newpwdString.equals(subpwdString)) {
				toast("两次新密码输入不一致");
				return;
			} else {
				showLoadingDialog();
				JsonObjectRequest request = new JsonObjectRequest(Method.GET, UrlConstants.MODIFY_PWD + "&mid="
						+ YiKaoApplication.getStudentId() + "&oldpwd=" + md5.getMD5ofStr(oldpwdString) + "&newpwd="
						+ md5.getMD5ofStr(newpwdString) + "&repeatpwd=" + md5.getMD5ofStr(subpwdString), null,
						new Listener<JSONObject>() {

							@Override
							public void onResponse(JSONObject response) {
								dismissLoadingDialog();
								if (response.optBoolean("success")) {
									toast("密码修改成功");
									ModifyPasswordActivity.this.finish();
								} else {
									toast(response.optString("message"));
								}
							}
						}, null);

				queue.add(request);
				queue.start();
			}

		} else {
			toast("请完善信息");
		}

	}

	@Override
	protected void onPause() {
		MobclickAgent.onPageEnd(TAG);
		super.onPause();
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {

		case R.id.modify_modifpwd_btn:
			submit();
			break;

		default:
			break;
		}
	}

}
