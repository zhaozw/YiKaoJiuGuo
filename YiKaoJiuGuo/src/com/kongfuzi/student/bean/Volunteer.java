package com.kongfuzi.student.bean;

import java.util.List;

import com.google.gson.annotations.SerializedName;

/**
 * @author LBDL
 * @desc �ҵ�־Ը
 *
 */
public class Volunteer {
	
	@SerializedName("three")
	public String majorId;
	
	@SerializedName("ename")
	public String majorName;
	
	@SerializedName("content")
	public List<Major> majorList;

}
