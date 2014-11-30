package com.kongfuzi.student.bean;

import com.google.gson.annotations.SerializedName;

/**
 * @author LBDL
 * @desc 专业
 *
 */
public class Major {
	
	/**
	 * 图片
	 * */
	@SerializedName("litpic")
	public String avatar;
	
	/**
	 * 专业名称
	 * */
	@SerializedName("title")
	public String major;
	
	/**
	 * 大学名称
	 * */
	@SerializedName("collegeName")
	public String collegeName;
	
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
	 * 报考人数
	 * */
	@SerializedName("reg")
	public Boolean reg;
	
	/**
	 * 该大学下是否有专业加入志愿
	 * */
	@SerializedName("ishidden")
	public Boolean isHidden;
	
	/**
	 * 专业介绍
	 * */
	@SerializedName("body")
	public String body;
	
	
	@SerializedName("id")
	public String id;

	
}
