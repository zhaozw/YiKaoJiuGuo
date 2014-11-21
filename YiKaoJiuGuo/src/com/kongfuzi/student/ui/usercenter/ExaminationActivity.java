package com.kongfuzi.student.ui.usercenter;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.reflect.TypeToken;
import com.kongfuzi.lib.volley.Request.Method;
import com.kongfuzi.lib.volley.RequestQueue;
import com.kongfuzi.lib.volley.Response.ErrorListener;
import com.kongfuzi.lib.volley.Response.Listener;
import com.kongfuzi.lib.volley.VolleyError;
import com.kongfuzi.student.R;
import com.kongfuzi.student.bean.ExaminationSchedule;
import com.kongfuzi.student.ui.global.BaseActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * @author LBDL
 * @desc øº ‘»’≥Ã
 * 
 */
public class ExaminationActivity extends BaseActivity{

	private TextView count_tv;

	private Boolean isLoading = false;

	private Context mContext;
	private RequestQueue mQueue;

	private List<ExaminationSchedule> list = new ArrayList<ExaminationSchedule>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_examination);

	}


}
