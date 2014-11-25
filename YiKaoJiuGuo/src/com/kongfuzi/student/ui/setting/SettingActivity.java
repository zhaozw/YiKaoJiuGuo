package com.kongfuzi.student.ui.setting;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.kongfuzi.lib.volley.RequestQueue;
import com.kongfuzi.lib.volley.VolleyError;
import com.kongfuzi.lib.volley.Request.Method;
import com.kongfuzi.lib.volley.Response.ErrorListener;
import com.kongfuzi.lib.volley.Response.Listener;
import com.kongfuzi.lib.volley.toolbox.JsonObjectRequest;
import com.kongfuzi.student.R;
import com.kongfuzi.student.support.utils.UrlConstants;
import com.kongfuzi.student.ui.global.BaseActivity;
import com.umeng.analytics.MobclickAgent;
import com.umeng.fb.FeedbackAgent;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.bean.SocializeConfig;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.media.UMImage;

public class SettingActivity extends BaseActivity implements OnClickListener{

	private LinearLayout userinfo_ll;
	private LinearLayout modifyPwd_ll;
	private LinearLayout about_ll;
	private LinearLayout feedBack_ll;
	private LinearLayout clear_ll;
	private LinearLayout share_ll;
	private LinearLayout version_ll;
	private LinearLayout logout_ll;

	private Context mContext;
	public static final String TAG = "SettingActivity";
	private UMSocialService mController;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);

		mContext = this;

		userinfo_ll = (LinearLayout) findViewById(R.id.user_info_setting_ll);
		modifyPwd_ll = (LinearLayout) findViewById(R.id.modify_pwd_setting_ll);
		about_ll = (LinearLayout) findViewById(R.id.about_setting_ll);
		feedBack_ll = (LinearLayout) findViewById(R.id.feed_back_setting_ll);
		clear_ll = (LinearLayout) findViewById(R.id.clear_cache_setting_ll);
		logout_ll = (LinearLayout) findViewById(R.id.logout_setting_ll);
		share_ll = (LinearLayout) findViewById(R.id.share_setting_ll);
		version_ll = (LinearLayout) findViewById(R.id.version_setting_ll);

		userinfo_ll.setOnClickListener(this);
		modifyPwd_ll.setOnClickListener(this);
		about_ll.setOnClickListener(this);
		feedBack_ll.setOnClickListener(this);
		logout_ll.setOnClickListener(this);
		clear_ll.setOnClickListener(this);
		share_ll.setOnClickListener(this);
		version_ll.setOnClickListener(this);

//		initUMShare();
	}

	@Override
	protected void onResume() {
		super.onResume();
		MobclickAgent.onPageStart(TAG);
	}

//	private void logoutDialog() {
//		new AlertDialog.Builder(this).setTitle("注销").setMessage("确定注销吗?")
//				.setPositiveButton("确定", new android.content.DialogInterface.OnClickListener() {
//
//					@Override
//					public void onClick(DialogInterface dialog, int which) {
//						logout();
//					}
//				}).setNegativeButton("取消", null).show();
//
//	}

//	private void logout() {
//		
//		if (!isLogin()) {
//			return;
//		}
//
//			JsonObjectRequest request = new JsonObjectRequest(Method.GET, UrlConstants.LOGOUT + "?secretkey=" + secretkey,
//					null, new Listener<JSONObject>() {
//
//						@Override
//						public void onResponse(JSONObject response) {
//							Log.i("Logout", "logout = " + response);
//							try {
//								if (response != null && response.getBoolean("success")) {
//									// LoginUtil.putStudentId("", "", "", "",
//									// "","");
////									LoginUtil.clearSharedPreferences();
//									SettingActivity.this.finish();
//									startActivity(new Intent(SettingActivity.this, LoginActivity.class));
//								}
//							} catch (JSONException e) {
//								// TODO Auto-generated catch block
//								e.printStackTrace();
//							}
//						}
//					}, null);
//			queue.add(request);
//			queue.start();
//		}

//	}

//	private void initUMShare() {
//		// 首先在您的Activity中添加如下成员变量
//		mController = UMServiceFactory.getUMSocialService("com.umeng.share");
//		// 设置分享内容
//		mController.setShareContent("艺考就过，艺考生报考神器 （国内首款艺考生报考管理、考试行程规划与辅导的手机APP软件）");
//		mController.setShareMedia(new UMImage(mContext, R.drawable.kongfuzi_logo)); // 设置分享图片内容
//
//		SocializeConfig config = mController.getConfig();
//		// 开通短信
//		config.setShareSms(true);
//		config.setShareMail(true);
//
//		config.setPlatforms(SHARE_MEDIA.QZONE, SHARE_MEDIA.TENCENT, SHARE_MEDIA.QQ, SHARE_MEDIA.RENREN,
//				SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.SINA);
//		// wx967daebe835fbeac是你在微信开发平台注册应用的AppID, 这里需要替换成你注册的AppID
//		String appID = "wxfc7bdd52daa288f9";
//		// 添加微信平台
//		UMWXHandler wxHandler = new UMWXHandler(this, appID);
//		wxHandler.addToSocialSDK();
//		// 支持微信朋友圈
//		UMWXHandler wxCircleHandler = new UMWXHandler(this, appID);
//		wxCircleHandler.setToCircle(true);
//		wxCircleHandler.addToSocialSDK();
//
//		// QQ:参数1为当前Activity，参数2为开发者在QQ互联申请的APP ID，参数3为开发者在QQ互联申请的APP kEY.
//		UMQQSsoHandler qqSsoHandler = new UMQQSsoHandler(this, "1103280485", "amMoWTWTHN13So9C");
//		qqSsoHandler.addToSocialSDK();
//
//		// QZone:参数1为当前Activity，参数2为开发者在QQ互联申请的APP ID，参数3为开发者在QQ互联申请的APP kEY.
//		QZoneSsoHandler qZoneSsoHandler = new QZoneSsoHandler(this, "1103280485", "amMoWTWTHN13So9C");
//		qZoneSsoHandler.addToSocialSDK();
//
//		// 设置新浪SSO handler
//		mController.getConfig().setSsoHandler(new SinaSsoHandler());
//
//		// 设置腾讯微博SSO handler
//		mController.getConfig().setSsoHandler(new TencentWBSsoHandler());
//
//		// 添加人人网SSO授权功能
//		// APPID:201874
//		// API Key:28401c0964f04a72a14c812d6132fcef
//		// Secret:3bf66e42db1e4fa9829b955cc300b737
//		RenrenSsoHandler renrenSsoHandler = new RenrenSsoHandler(this, "271959", "162080886de44b17ac44ce5bfeb0085e",
//				"bed407c6eede4c4b8c193401da163e3a");
//		mController.getConfig().setSsoHandler(renrenSsoHandler);
//	}

	/**
	 * 友盟社会化分享
	 * */
	private void share() {
		// 设置分享的内容
		mController.setShareContent("艺考就过，艺考生报考神器 （国内首款艺考生报考管理、考试行程规划与辅导的手机APP软件）");
		// 设置分享的图片
		mController.setShareMedia(new UMImage(this, R.drawable.ic_launcher));
		mController.openShare(this, false);

	}

	/**
	 * 版本更新
	 * */
//	class VersionThread extends Thread {
//
//		private void getVersionInfo() {
//			Looper.prepare();
//
//			JsonObjectRequest request = new JsonObjectRequest(Method.GET, UrlConstants.VERSION, null,
//					new Listener<JSONObject>() {
//
//						@Override
//						public void onResponse(final JSONObject response) {
//							runOnUiThread(new Runnable() {
//
//								@Override
//								public void run() {
//									// TODO Auto-generated method stub
//									loadingDialog.cancel();
//								}
//							});
//
//							// 服务器版本号
//							String version_latest = response.optString("versionShort");
//							// 本地版本号
//							String version_now = MainApplication.getInstance().getVersion();
//							Log.i(TAG, "version_latest = " + version_latest);
//							Log.i(TAG, "version_now = " + version_now);
//							// 版本对比
//							if (!version_latest.equals(version_now)) {
//
//								AlertDialog.Builder dialog = new Builder(SettingActivity.this);
//								dialog.setTitle("版本更新");
//								dialog.setMessage("艺考就过有新的版本,是否要进行更新");
//
//								dialog.setPositiveButton("更新", new android.content.DialogInterface.OnClickListener() {
//
//									@Override
//									public void onClick(DialogInterface dialog, int which) {
//										Uri uri = Uri.parse(response.optString("update_url"));
//										Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//										startActivity(intent);
//									}
//								});
//
//								dialog.setNegativeButton("取消", new android.content.DialogInterface.OnClickListener() {
//
//									@Override
//									public void onClick(DialogInterface dialog, int which) {
//										Toast.makeText(SettingActivity.this, "取消更新", Toast.LENGTH_SHORT).show();
//									}
//								});
//								dialog.show();
//							} else {
//								Toast.makeText(SettingActivity.this, "您的版本已经是最新版了,无需更新!", Toast.LENGTH_SHORT).show();
//							}
//						}
//					}, new ErrorListener() {
//
//						@Override
//						public void onErrorResponse(VolleyError error) {
//
//						}
//					});
//
//			queue.add(request);
//			queue.start();
//
//		}
//
//		@Override
//		public void run() {
//			getVersionInfo();
//		}
//	}

	@Override
	public void onClick(View v) {

		Class<?> cls = null;

		switch (v.getId()) {
		// 个人设置
		case R.id.user_info_setting_ll:
			cls = UserInfoActivity.class;
			break;

		// 修改密码
		case R.id.modify_pwd_setting_ll:
//			cls = ModifyPasswordActivity.class;
			break;
		// 关于我们
		case R.id.about_setting_ll:
			cls = AboutActivity.class;
			break;
		// 信息反馈
		case R.id.feed_back_setting_ll:
			FeedbackAgent agent = new FeedbackAgent(this);
			agent.startFeedbackActivity();
			break;
		// 注销
		case R.id.logout_setting_ll:
//			logoutDialog();
			break;
		// 清除缓存 (数据库数据)
//		case R.id.clear_cache_setting_ll:
//			new AlertDialog.Builder(mContext).setMessage("确定要清除缓存?")
//					.setPositiveButton("确定", new android.content.DialogInterface.OnClickListener() {
//
//						@Override
//						public void onClick(DialogInterface dialog, int which) {
//							new ConditionsDBTask(mContext).delete();
//							new ProvinceDBTask(mContext).delete();
//							ToastDialog.showToast(mContext, "清理成功");
//
//						}
//					}).setNegativeButton("取消", null).create().show();
//			break;
		// 分享
		case R.id.share_setting_ll:
			share();
			break;
		// 版本更新
		case R.id.version_setting_ll:
//			new VersionThread().start();
			break;
		default:
			break;
		}

		if (cls != null) {
			startActivity(new Intent(this, cls));
		}
	}

	@Override
	protected void onPause() {
		MobclickAgent.onPageEnd(TAG);
		super.onPause();
	}

}
