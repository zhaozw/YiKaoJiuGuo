package com.kongfuzi.student.bean;

import com.google.gson.annotations.SerializedName;

/**
 * @author Administrator
 *@goal ÕÐÉúÏêÇéµÄbean
 */
public class EnrollDetail {
	
	@SerializedName("id")
	public int id;
	
	@SerializedName("people")
	public String people;
	
	@SerializedName("subject")
	public String subject;
	
	@SerializedName("body")
	public String body;
	
	@SerializedName("province")
	public String province;
}
