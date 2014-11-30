package com.kongfuzi.student.bean;

import java.util.List;

import com.google.gson.annotations.SerializedName;

/**
 * @author LBDL
 * @desc 大学
 *
 */
public class College {
	
	/**
	 * 大学名称
	 * */
	@SerializedName("title")
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
	@SerializedName("id")
	public String collegeId;
	
	/**
	 * 是否已报考
	 * */
	@SerializedName("isReg")
	public Boolean isReg;
	
	/**
	 * 报考人数
	 * */
	@SerializedName("reg")
	public Boolean reg;
	
	/**
	 * 是否收藏
	 * */
	@SerializedName("isCollect")
	public Boolean  isCollect;
	
	/**
	 * 专业列表
	 * 
	 * */
	@SerializedName("proList")
	public List<Major> proList;
	
	/**
	 * 该大学下是否有专业加入志愿
	 * */
	@SerializedName("ishidden")
	public Boolean isHidden;
	
	

}
