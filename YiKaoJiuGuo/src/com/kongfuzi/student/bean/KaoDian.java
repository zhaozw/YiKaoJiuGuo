package com.kongfuzi.student.bean;

import com.google.gson.annotations.SerializedName;

/**
 * @author LBDL
 * @desc 考点
 */
public class KaoDian {
	
	/**
	 * 考点id
	 * */
	@SerializedName("id")
	public String id;
	
	/**
	 * 考点名称
	 * */
	@SerializedName("address")
	public String address;
	
	/**
	 * 网上报名时间
	 * */
	@SerializedName("registr")
	public String onlineTime;
	
	/**
	 * 
	 * 现场确认时间
	 * */
	@SerializedName("confirmtime")
	public String confirmTime;
	
	/**
	 * 
	 * 考试时间
	 * */
	@SerializedName("examtime")
	public String examTime;

}
