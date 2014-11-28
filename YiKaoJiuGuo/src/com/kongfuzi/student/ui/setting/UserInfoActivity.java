package com.kongfuzi.student.ui.setting;

import java.io.File;
import java.util.List;

import org.json.JSONObject;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.kongfuzi.lib.volley.Request.Method;
import com.kongfuzi.lib.volley.Response.Listener;
import com.kongfuzi.lib.volley.toolbox.JsonObjectRequest;
import com.kongfuzi.student.R;
import com.kongfuzi.student.app.YiKaoApplication;
import com.kongfuzi.student.bean.Conditions;
import com.kongfuzi.student.bean.User;
import com.kongfuzi.student.support.BitmapImage;
import com.kongfuzi.student.support.ImageCrop;
import com.kongfuzi.student.support.network.ArrayRequest;
import com.kongfuzi.student.support.network.ImageHttp;
import com.kongfuzi.student.support.network.ObjectRequest;
import com.kongfuzi.student.support.utils.UrlConstants;
import com.kongfuzi.student.ui.global.BaseActivity;
import com.kongfuzi.student.ui.global.ListDialogFragment;

/**
 * @author LBDL
 * @desc 个人信息
 * 
 */
public class UserInfoActivity extends BaseActivity implements OnClickListener, ListDialogFragment.DialogItemClick {

	private LinearLayout avatar_ll;
	private ImageView avatar_iv;
	private EditText nick_et;
	private RadioGroup gender_rg;
	private TextView type_tv;
	private TextView origin_tv;
	private TextView property_tv;
	private Button modify_btn;

	// id
	private int typeInt;
	private int originInt;
	private int propertyInt;
	private int genderId = 0;

	private ImageCrop imageCrop;
	private ListDialogFragment fragment = null;
	private List<Conditions> list;
	private static final String TYPE = "type";
	private static final String ORIGIN = "origin";
	private static final String PROPERTY = "property";
	private static final String TAG = "UserInfoActivity";

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_user_info);

		imageCrop = new ImageCrop(this);

		avatar_ll = (LinearLayout) findViewById(R.id.avatar_user_info_ll);
		avatar_iv = (ImageView) findViewById(R.id.avatar_user_info_iv);
		nick_et = (EditText) findViewById(R.id.nick_user_info_et);
		gender_rg = (RadioGroup) findViewById(R.id.gender_user_info_rg);
		type_tv = (TextView) findViewById(R.id.type_user_info_tv);
		origin_tv = (TextView) findViewById(R.id.origin_user_info_tv);
		property_tv = (TextView) findViewById(R.id.property_user_info_tv);
		modify_btn = (Button) findViewById(R.id.submit_user_info_btn);

		avatar_ll.setOnClickListener(this);
		type_tv.setOnClickListener(this);
		origin_tv.setOnClickListener(this);
		property_tv.setOnClickListener(this);
		modify_btn.setOnClickListener(this);

		gender_rg.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO 性别 究竟传什么值
				switch (checkedId) {
				case R.id.man_user_info_rb:
					genderId = 0;
					break;
				case R.id.woman_user_info_rb:
					genderId = 1;

					break;

				default:
					break;
				}
			}
		});

		getData();

	}

	private void getData() {

		if (!isLogin()) {
			return;
		}
		showLoadingDialog();
		ObjectRequest<User> request = new ObjectRequest<User>(UrlConstants.GET_USER_INFO + "&mid="
				+ YiKaoApplication.getStudentId(), new Listener<User>() {

			@Override
			public void onResponse(User user) {
				dismissLoadingDialog();
				// TODO 这个地方face是object 而不是String
				if (user != null) {
					imageLoader.displayImage(user.avatar.pic, avatar_iv);
					nick_et.setText(user.userName);
					type_tv.setText(user.typeTitle);
					origin_tv.setText(user.origin);
					property_tv.setText(user.propertyTitle);
				}
			}
		}, new TypeToken<User>() {
		}.getType());

		queue.add(request);
		queue.start();
	}

	/**
	 * 提交修改信息
	 * */
	private void uploadInfo() {
		if (!isLogin()) {
			return;
		}

		String urlString = UrlConstants.MODIFY_USER_INFO + "&mid=" + YiKaoApplication.getStudentId() + "&username="
				+ nick_et.getText().toString() + "&sex=" + genderId + "&province=" + originInt + "&category=" + typeInt
				+ "&course=" + propertyInt;
		Log.i(TAG, "url = " + urlString);
		showLoadingDialog();
		JsonObjectRequest request = new JsonObjectRequest(Method.GET, urlString, null, new Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				Log.i(TAG, "response = " + response);
				dismissLoadingDialog();
				if (response != null && response.optBoolean("success")) {
					toast("个人信息修改成功");
					finish();
				}
			}
		}, null);

		queue.add(request);
		queue.start();
	}

	/**
	 * 获取 考试类型 生源地 文/理科生
	 * 
	 * @param index
	 *            0:考试类型 1: 生源地 2: 文/理科
	 * @param urlString
	 * 
	 * */
	private void getConditionData(final int index, String urlString) {

		showLoadingDialog();
		ArrayRequest<List<Conditions>> request = new ArrayRequest<List<Conditions>>(urlString,
				new Listener<List<Conditions>>() {

					@Override
					public void onResponse(List<Conditions> response) {
						dismissLoadingDialog();
						if (response != null) {

							list = response;
							fragment = ListDialogFragment.getInstance(list);
							switch (index) {
							// 考试类型
							case 0:
								fragment.show(getSupportFragmentManager(), TYPE);

								break;
							// 生源地
							case 1:
								fragment.show(getSupportFragmentManager(), ORIGIN);

								break;
							// 文/理科
							case 2:
								fragment.show(getSupportFragmentManager(), PROPERTY);

								break;

							default:
								break;
							}
						}

					}
				}, new TypeToken<List<Conditions>>() {
				}.getType());

		queue.add(request);
		queue.start();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		Bitmap bitmap = null;
		// 上传的图片
		File file = null;

		if (data == null) {
			return;
		}

		switch (requestCode) {
		// 拍照获取图片
		case ImageCrop.PICK_FROM_CAMERA:
			// 裁剪图片
			imageCrop.doCrop(Uri.fromFile(imageCrop.file_path));
			break;
		// 从相册中获取
		case ImageCrop.PICK_FROM_FILE:
			// uri = data.getData();
			Log.i(TAG, "file url = " + data.getData());
			// 裁剪图片
			imageCrop.doCrop(data.getData());
			break;
		// 得到裁剪之后的图片
		case ImageCrop.PICK_FORM_CROP:
			if (data != null) {
				bitmap = data.getParcelableExtra("data");
				Log.i(TAG, "Bitmap = " + bitmap);

				if (bitmap != null) {
					file = BitmapImage.saveBitmap(bitmap);
					avatar_iv.setImageBitmap(bitmap);
				}

				if (file != null) {
					new UploadFile().execute(file);
				}
			}
			break;
		}

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {

		case R.id.avatar_user_info_ll:
			imageCrop.showDialog();
			break;
		case R.id.type_user_info_tv:
			getConditionData(0, UrlConstants.TYPE);
			break;
		case R.id.origin_user_info_tv:
			getConditionData(1, UrlConstants.PROVINCE);
			break;
		case R.id.property_user_info_tv:
			getConditionData(2, UrlConstants.PROPERTY);
			break;
		case R.id.submit_user_info_btn:
			uploadInfo();
			break;

		default:
			break;
		}

	}

	@Override
	public void dialogItemClickedListener(Conditions c) {

		Conditions conditions = c;
		String tagString = fragment.getTag();
		Log.i(TAG, "tag = " + tagString);

		if (conditions != null) {

			if (tagString.equals(TYPE)) {
				// 考试类型
				typeInt = conditions.id;
				type_tv.setText(conditions.ename);

			} else if (tagString.equals(ORIGIN)) {
				// 生源地
				origin_tv.setText(conditions.ename);
				originInt = conditions.id;

			} else if (tagString.equals(PROPERTY)) {
				// 招生属性 (文/理科)
				property_tv.setText(conditions.ename);
				propertyInt = conditions.id;
			}
		}

	}

	private class UploadFile extends AsyncTask<File, Integer, JSONObject> {

		String url = UrlConstants.MODIFY_AVATAR + "&mid=" + YiKaoApplication.getStudentId() + "&id="
				+ YiKaoApplication.getAvatarId();

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			showLoadingDialog();
		}

		@Override
		protected JSONObject doInBackground(File... params) {
			JSONObject result = new ImageHttp().uploadFile(params[0], url);
			Log.i(TAG, "upload = " + result);
			return result;
		}

		@Override
		protected void onPostExecute(JSONObject result) {
			super.onPostExecute(result);
			dismissLoadingDialog();
			Log.i(TAG, "result = " + result.toString());
			if (result != null && result.optBoolean("success")) {
				toast("头像上传成功");
				YiKaoApplication.putAvatarId(result.optString("fileID"));
			} else {
				toast("头像上传失败");
			}
		}

	}

}
