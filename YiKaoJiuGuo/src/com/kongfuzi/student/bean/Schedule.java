package com.kongfuzi.student.bean;

import com.google.gson.annotations.SerializedName;

/**
 * @author LBDL
 * @desc 考试日程内容
 *
 */
public class Schedule {
	
	
	/**
	 * 日程id
	 */
	@SerializedName("id")
	public int id;
	
	/**
	 * 考点
	 * */
	@SerializedName("address")
	public String site;
	/**
	 * 考试时间
	 * */
	@SerializedName("examtime")
	public String date;
}
