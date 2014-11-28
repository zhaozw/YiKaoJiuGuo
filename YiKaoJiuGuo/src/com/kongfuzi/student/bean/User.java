package com.kongfuzi.student.bean;

import com.google.gson.annotations.SerializedName;

public class User {

	@SerializedName("secretkey")
	public String secretkey;
	
	@SerializedName("studentid")
	public int id;
	
	/**
	 * 昵称
	 * */
	@SerializedName("username")
	public String userName;
	
	/**
	 * 性别
	 * */
	@SerializedName("sex")
	public String gender;
	
	
	@SerializedName("message")
	public String message;
	
	/**
	 * 头像
	 * */
	@SerializedName("face")
	public Avatar avatar;
	/**
	 * 课程数
	 * */
	@SerializedName("course")
	public String courseNum;
	/**
	 * 志愿数
	 * */
	@SerializedName("volunteer")
	public String volunteerNum;
	/**
	 * 日程数
	 * */
	@SerializedName("trip")
	public String scheduleNum;
	/**
	 * 收藏数
	 * */
	@SerializedName("collect")
	public String collectionNum;
	/**
	 * 生源地
	 * */
	@SerializedName("provincetitle")
	public String origin;
	/**
	 * 考试类型
	 * */
	@SerializedName("category")
	public String type;
	/**
	 * 考试类型标题
	 * */
	@SerializedName("categorytitle")
	public String typeTitle;
	/**
	 * 文理科
	 * */
	@SerializedName("course1")
	public String property;
	/**
	 * 文理科标题
	 * */
	@SerializedName("coursetitle")
	public String propertyTitle;
}
