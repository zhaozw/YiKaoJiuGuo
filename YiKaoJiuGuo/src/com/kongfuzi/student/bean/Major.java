package com.kongfuzi.student.bean;

import com.google.gson.annotations.SerializedName;

public class Major {
	
	/**
	 * 专业名称
	 * */
	@SerializedName("id")
	public int majorId;
	
	/**
	 * 专业名称
	 * 
	 * */
	@SerializedName("title")
	public String major;
	
	/**
	 * 大学名称
	 * */
	@SerializedName("collegeName")
	public String college;
	
	/**
	 * 图片
	 * */
	@SerializedName("litpic")
	public String avatar;
	
	/**
	 * 本科批次
	 * */
	@SerializedName("flag")
	public String batch;
	
	/**
	 * 招生人数
	 * */
	@SerializedName("people")
	public int recruit_count;
	
	
	/**
	 * 大学id
	 * */
	@SerializedName("aid")
	public int collegeId;
	
	/**
	 * 是否已报考
	 * */
	@SerializedName("isReg")
	public Boolean isReg;
	
	/**
	 * 该大学下是否有专业加入志愿
	 * */
	@SerializedName("ishidden")
	public Boolean isHidden;
	/**
	 * 报考数
	 * */
	@SerializedName("reg")
	public int kao_count;
	
	/**
	 * 专业介绍
	 * */
	@SerializedName("body")
	public String body;
	

}
