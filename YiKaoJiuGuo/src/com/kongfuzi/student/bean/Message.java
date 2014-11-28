package com.kongfuzi.student.bean;

import com.google.gson.annotations.SerializedName;

public class Message {

	@SerializedName("id")
	public int id;
	
	@SerializedName("title")
	public String title;
}
