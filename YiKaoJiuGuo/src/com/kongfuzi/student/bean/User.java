package com.kongfuzi.student.bean;

import com.google.gson.annotations.SerializedName;

public class User {

	@SerializedName("secretkey")
	public String secretkey;
	
	@SerializedName("id")
	public int id;
	
	@SerializedName("username")
	public String userName;
	
	@SerializedName("message")
	public String message;
}
