package com.kongfuzi.student.ui.setting;
//package com.kongfuzi.student.ui.usercenter;
//
//import org.json.JSONObject;
//
//import android.content.Context;
//import android.os.Bundle;
//import android.text.TextUtils;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.widget.Button;
//import android.widget.EditText;
//
//import com.kongfuzi.student.R;
//
///**
// * @author LBDL
// * @desc 修改密码
// * 
// */
//public class ModifyPasswordActivity extends BaseActivity implements OnClickListener{
//
//	private EditText code_et;
//	private EditText pwd_et;
//	private Button code_btn;
//	private Button modify_btn;
//
//	private Context mContext;
//	private RequestQueue mQueue;
//	private LoadingDialog dialog;
//
//	public static final String TAG = "ModifyPasswordActivity";
//
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_modifypwd);
//
//		mContext = this;
//		dialog = new LoadingDialog(mContext);
//		mQueue = MainApplication.getRequestQueueInstance();
//
//		pwd_et = (EditText) findViewById(R.id.pwd_modifypwd_et);
//		code_et = (EditText) findViewById(R.id.code_modifypwd_et);
//		code_btn = (Button) findViewById(R.id.code_modifypwd_btn);
//		modify_btn = (Button) findViewById(R.id.submit_modifypwd_btn);
//		
//		code_btn.setOnClickListener(this);
//		modify_btn.setOnClickListener(this);
//	}
//
//	@Override
//	protected void onResume() {
//		super.onResume();
//		MobclickAgent.onPageStart(TAG);
//	}
//
//	/** 获取注册码 */
//	public void getCode() {
//		String phone = LoginUtil.getPhone();
//		if (TextUtils.isEmpty(phone)) {
//			return;
//		}
//		dialog.show();
//		JsonObjectRequest request = new JsonObjectRequest(Method.GET, Constants.REGISTERCODE + "?phone=" + phone, null,
//				new Listener<JSONObject>() {
//
//					@Override
//					public void onResponse(JSONObject response) {
//						dialog.cancel();
//						if (response.optBoolean("success")) {
//							ToastDialog.showToast(mContext, "验证码已经成功发送到您的手机上,请注意查收!!!");
//						} else {
//							ToastDialog.showToast(mContext, response.optString("message"));
//						}
//					}
//				}, null);
//		mQueue.add(request);
//		mQueue.start();
//	}
//	
//	private void submit(){
//		
//		String phone = LoginUtil.getPhone();
//		String pwd = pwd_et.getText().toString();
//		String code = code_et.getText().toString();
//		
//		dialog.show();
//		if (!TextUtils.isEmpty(pwd) && !TextUtils.isEmpty(code) && !TextUtils.isEmpty(phone)) {
//			JsonObjectRequest request = new JsonObjectRequest(Method.GET, Constants.RESETPWD + "?phone=" 
//					+ phone + "&password=" + new MD5().getMD5ofStr(pwd) + "&vcode=" + code, null, new Listener<JSONObject>() {
//
//						@Override
//						public void onResponse(JSONObject response) {
//							dialog.cancel();
//							if (response.optBoolean("success")) {
//								ToastDialog.showToast(mContext, "密码修改成功");
//								ModifyPasswordActivity.this.finish();
//							} else {
//								ToastDialog.showToast(mContext, response.optString("message"));
//							}
//						}
//					}, null);
//			
//			mQueue.add(request);
//			mQueue.start();
//		}
//		
//		
//	}
//
//	@Override
//	protected void onPause() {
//		MobclickAgent.onPageEnd(TAG);
//		super.onPause();
//	}
//
//	@Override
//	public void onClick(View v) {
//		
//		switch (v.getId()) {
//		
//		case R.id.code_modifypwd_btn:
//			getCode();
//			break;
//			
//		case R.id.submit_modifypwd_btn:
//			submit();
//			break;
//
//		default:
//			break;
//		}
//	}
//
//}
