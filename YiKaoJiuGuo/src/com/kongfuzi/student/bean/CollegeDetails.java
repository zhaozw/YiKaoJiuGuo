package com.kongfuzi.student.bean;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class CollegeDetails {
	
	@SerializedName("id")
	public String id;
	
	@SerializedName("title")
	public String title;
	
	@SerializedName("flag")
	public String flag;
	
	@SerializedName("litpic")
	public String litpic;
	
	@SerializedName("people")
	public int people;
	
	@SerializedName("reg")
	public String reg;
	
	@SerializedName("isCollect")
	public Boolean  isCollect;
	
	@SerializedName("proList")
	public List<Major> proList;
	
	
}
