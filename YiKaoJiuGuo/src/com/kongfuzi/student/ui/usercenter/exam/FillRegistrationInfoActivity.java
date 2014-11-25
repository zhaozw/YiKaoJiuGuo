//package com.kongfuzi.student.ui.usercenter.exam;
//
//import java.net.URLEncoder;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Message;
//import android.util.Log;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.RadioButton;
//import android.widget.RadioGroup;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.alipay.android.app.sdk.AliPay;
//import com.kongfuzi.lib.volley.RequestQueue;
//import com.kongfuzi.yikaojiuguo.R;
//import com.kongfuzi.yikaojiuguo.app.MainApplication;
//import com.kongfuzi.yikaojiuguo.ui.BaseActivity;
//import com.kongfuzi.yikaojiuguo.ui.view.LoadingDialog;
//import com.kongfuzi.yikaojiuguo.util.Keys;
//import com.kongfuzi.yikaojiuguo.util.Result;
//import com.kongfuzi.yikaojiuguo.util.Rsa;
//
///**
// * @author LBDL
// * @desc 报名填写信息
// *
// */
//public class FillRegistrationInfoActivity extends BaseActivity implements OnClickListener{
//	
//	private EditText name_et;
//	private RadioGroup gender_rg;
//	private RadioButton boy_rb,girl_rb;
//	private EditText idCard_et;
//	private TextView origin_tv;
//	private EditText highSchool_et;
//	private EditText stdio_et;
//	private EditText phone_et;
//	private EditText qq_et;
//	private EditText wechat_et;
//	private EditText urgent_name_et;
//	private EditText urgent_phone_et;
//	private RadioGroup mode_rg;
//	private RadioButton scene_rb,remote_rb;
//	private Button edit_btn,pay_btn;
//	
//	private LoadingDialog dialog;
//	private RequestQueue mQueue;
//	
//	private static final int RQF_PAY = 1;
//
//	private static final int RQF_LOGIN = 2;
//	
//	public static final String TAG = "FillRegistrationInfoActivity";
//
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_fill_registration_info);
//		
//		mQueue = MainApplication.getRequestQueueInstance();
//		dialog = new LoadingDialog(this);
//		
//		initView();
//	}
//
//	private void initView() {
//		
//		name_et = (EditText) findViewById(R.id.name_fill_registration_info_et);
//		gender_rg = (RadioGroup) findViewById(R.id.gender_fill_registration_info_rg);
//		boy_rb= (RadioButton) findViewById(R.id.boy_fill_registration_info_rb);
//		girl_rb = (RadioButton) findViewById(R.id.girl_fill_registration_info_rb);
//		idCard_et = (EditText) findViewById(R.id.idcard_fill_registration_info_et);
//		origin_tv = (TextView) findViewById(R.id.origin_fill_registration_info_tv);
//		highSchool_et = (EditText) findViewById(R.id.highschool_fill_registration_info_et);
//		phone_et = (EditText) findViewById(R.id.phone_fill_registration_info_et);
//		qq_et = (EditText) findViewById(R.id.qq_fill_registration_info_et);
//		wechat_et = (EditText) findViewById(R.id.wechat_fill_registration_info_et);
//		urgent_name_et = (EditText) findViewById(R.id.urgent_name_fill_registration_info_et);
//		urgent_phone_et = (EditText) findViewById(R.id.urgent_phone_fill_registration_info_et);
//		mode_rg = (RadioGroup) findViewById(R.id.mode_fill_registration_info_rg);
//		scene_rb = (RadioButton) findViewById(R.id.scene_fill_registration_info_tv);
//		remote_rb = (RadioButton) findViewById(R.id.remote_fill_registration_info_tv);
//		edit_btn = (Button) findViewById(R.id.edit_fill_registration_info_btn);
//		pay_btn = (Button) findViewById(R.id.pay_fill_registration_info_btn);
//		
//		origin_tv.setOnClickListener(this);
//		edit_btn.setOnClickListener(this);
//		pay_btn.setOnClickListener(this);
//	}
//	
//	private void submitInfo(){
//		String name = name_et.getText().toString();
//		String idCard = idCard_et.getText().toString();
//		String origin = origin_tv.getText().toString();
//		String highSchool = highSchool_et.getText().toString();
//		String phone = phone_et.getText().toString();
//		String qq = qq_et.getText().toString();
//		String wechat = wechat_et.getText().toString();
//		String urgent_name = urgent_name_et.getText().toString();
//		String urgent_phone = urgent_phone_et.getText().toString();
//	}
//	
//	
//	private String getSignType() {
//		return "sign_type=\"RSA\"";
//	}
//	/**
//	 * 生成订单号
//	 * */
//	private String getOutTradeNo() {
//		SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss");
//		Date date = new Date();
//		String key = format.format(date);
//
//		java.util.Random r = new java.util.Random();
//		key += r.nextInt();
//		key = key.substring(0, 15);
//		Log.d(TAG, "outTradeNo: " + key);
//		return key;
//	}
//	
//	private String getNewOrderInfo() {
//		StringBuilder sb = new StringBuilder();
//		sb.append("partner=\"");
//		sb.append(Keys.DEFAULT_PARTNER);
//		sb.append("\"&out_trade_no=\"");
//		sb.append(getOutTradeNo());
//		sb.append("\"&subject=\"");
//		sb.append("拉到健身房");
//		sb.append("\"&body=\"");
//		sb.append("alkdALKDSasadsa");
//		sb.append("\"&total_fee=\"");
//		sb.append(160);
//		sb.append("\"&notify_url=\"");
//
//		// 网址需要做URL编码
//		sb.append(URLEncoder.encode("http://notify.java.jpxx.org/index.jsp"));
//		sb.append("\"&service=\"mobile.securitypay.pay");
//		sb.append("\"&_input_charset=\"UTF-8");
//		sb.append("\"&return_url=\"");
//		sb.append(URLEncoder.encode("http://m.alipay.com"));
//		sb.append("\"&payment_type=\"1");
//		sb.append("\"&seller_id=\"");
//		sb.append(Keys.DEFAULT_SELLER);
//
//		// 如果show_url值为空，可不传
//		// sb.append("\"&show_url=\"");
//		sb.append("\"&it_b_pay=\"1m");
//		sb.append("\"");
//
//		return new String(sb);
//	}
//	
//	private void alipay() {
//		try {
//			Log.i("ExternalPartner", "onItemClick");
//			String info = getNewOrderInfo();
//			String sign = Rsa.sign(info, Keys.PRIVATE);
//			sign = URLEncoder.encode(sign);
//			info += "&sign=\"" + sign + "\"&" + getSignType();
//			Log.i(TAG, "start pay");
//			// start the pay.
//			Log.i(TAG, "info = " + info);
//
//			final String orderInfo = info;
//			new Thread() {
//				public void run() {
//					AliPay alipay = new AliPay(FillRegistrationInfoActivity.this, mHandler);
//
//					// 设置为沙箱模式，不设置默认为线上环境
//					// alipay.setSandBox(true);
//
//					String result = alipay.pay(orderInfo);
//
//					Log.i(TAG, "result = " + result);
//					Message msg = new Message();
//					msg.what = RQF_PAY;
//					msg.obj = result;
//					mHandler.sendMessage(msg);
//				}
//			}.start();
//
//		} catch (Exception ex) {
//			ex.printStackTrace();
//			Toast.makeText(this, R.string.remote_call_failed, Toast.LENGTH_SHORT).show();
//		}
//	}
//	
//	
//	Handler mHandler = new Handler() {
//		public void handleMessage(android.os.Message msg) {
//			Result result = new Result((String) msg.obj);
//
//			switch (msg.what) {
//			case RQF_PAY:
//			case RQF_LOGIN: {
//				Toast.makeText(MainApplication.getInstance(), result.getResult(), Toast.LENGTH_SHORT).show();
//
//			}
//				break;
//			default:
//				break;
//			}
//		};
//	};
//
//	@Override
//	public void onClick(View v) {
//		
//		switch (v.getId()) {
//		case R.id.origin_fill_registration_info_et:
//			
//			break;
//		case R.id.edit_fill_registration_info_btn:
//			
//			break;
//		case R.id.pay_fill_registration_info_btn:
//			alipay();
//			break;
//
//		default:
//			break;
//		}
//	}
//
//}
