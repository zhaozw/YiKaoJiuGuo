package com.kongfuzi.student.bean;

import com.google.gson.annotations.SerializedName;

/**
 * @author LBDL
 * @desc רҵ-->�ҵĿγ�
 *
 */
public class CourseMajor {

	@SerializedName("id")
	public int id;
	
	@SerializedName("title")
	public String college_name;
	
	@SerializedName("ptitle")
	public String major_name;
	
}
