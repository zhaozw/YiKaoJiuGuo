package com.kongfuzi.student.bean;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Course {

	@SerializedName("id")
	public int id;

	@SerializedName("first")
	public String attr;
	@SerializedName("second")
	public String name;
	
	@SerializedName("body")
	public List<CourseMajor> body;

}
