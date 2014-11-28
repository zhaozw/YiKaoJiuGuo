package com.kongfuzi.student.bean;

import java.util.List;

import com.google.gson.annotations.SerializedName;

/**
 * @author LBDL
 * @desc 考试日程
 *
 */

public class ExaminationSchedule {
	
	/**
	 * 大学标题
	 * */
	@SerializedName("title")
	public String school;
	/**
	 * 专业id
	 * */
	@SerializedName("id")
	public int major_id;
	/**
	 * 专业标题
	 * */
	@SerializedName("ptitle")
	public String major;
	/**
	 * 网上报名
	 * */
	@SerializedName("registr")
	public String online;
	/**
	 * 考点
	 * */
	@SerializedName("address")
	public String site;
	/**
	 * 现场确认
	 * */
	@SerializedName("confirmtime")
	public String confirm;
	/**
	 * 考试时间
	 * */
	@SerializedName("examtime")
	public String date;
	/**
	 * 考试范围
	 * */
	@SerializedName("examscope")
	public String range;
	
	@SerializedName("content")
	public Schedule schedules;
	
//	/**
//	 *	考点列表
//	 */
//	@SerializedName("add")
//	public List<ExamSite> examSites; 
}
