package com.kongfuzi.student.bean;

import java.util.List;

import com.google.gson.annotations.SerializedName;

/**
 * @author LBDL
 * @desc ���꿼��
 *
 */
public class Examination {
	
	@SerializedName("title")
	public String title;
	
	@SerializedName("exam")
	public List<Exam> exam_list;
}
