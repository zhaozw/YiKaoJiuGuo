package com.kongfuzi.student.support.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * @author LBDL
 * @desc 工具类
 * 
 */
public class Util {
	
	public static void setListViewHeightBasedOnChildren(ListView listView) {  
	        ListAdapter listAdapter = listView.getAdapter();   
	        if (listAdapter == null) {  
	            // pre-condition  
	            return;  
	        }  
	  
	        int totalHeight = 0;  
	        for (int i = 0; i < listAdapter.getCount(); i++) {  
	            View listItem = listAdapter.getView(i, null, listView);  
	            listItem.measure(0, 0);  
	            totalHeight += listItem.getMeasuredHeight();  
	        }  
	  
	        ViewGroup.LayoutParams params = listView.getLayoutParams();  
	        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount())) + 5;  
	        listView.setLayoutParams(params);  
	    }  

	public static int Dp2Px(Context context, float dp) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dp * scale + 0.5f);
	}

	/**
	 * 获取当前时间
	 * */
	public static String getCurrentDate() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日  HH:mm:ss", Locale.CHINA);
		Date currentDate = new Date(System.currentTimeMillis());
		return format.format(currentDate);

	}

	/**
	 * 判断字符串的格式是否是电子邮箱
	 * 
	 * @param 邮箱地址
	 * */
	public static boolean isEmail(String strEmail) {
		String strPattern = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
		Pattern p = Pattern.compile(strPattern);
		Matcher m = p.matcher(strEmail);
		if (m.matches()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断字符串
	 * 
	 * @param 手机号
	 * 
	 * */
	public static boolean isCellphone(String str) {
		Pattern pattern = Pattern.compile("1[0-9]{10}");
		Matcher matcher = pattern.matcher(str);
		if (matcher.matches()) {
			return true;
		} else {
			return false;
		}
	}
//	/**
//	 * 输入法的显示
//	 * 
//	 * */
//	public static void showInputMethod(Context context,View view){
//		InputMethodManager imm = (InputMethodManager) context
//				.getSystemService(Context.INPUT_METHOD_SERVICE);
//		imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
//	}
//	/**
//	 * 输入法的隐藏
//	 * 
//	 * */
//	public static void hideInputMethod(Context context,){
//		InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE); 
//		imm.hideSoftInputFromWindow(, flags)
//	}
}
