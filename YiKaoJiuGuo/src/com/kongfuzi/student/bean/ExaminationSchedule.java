package com.kongfuzi.student.bean;

import com.google.gson.annotations.SerializedName;

/**
 * @author LBDL
 * @desc �����ճ�
 *
 */
public class ExaminationSchedule{
	
	@SerializedName("title")
	public String college;
	
	@SerializedName("id")
	public int majorId;
	
	@SerializedName("ptitle")
	public String major;
	

}
