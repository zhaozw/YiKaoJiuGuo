package com.kongfuzi.student.bean;

import java.util.List;

import com.google.gson.annotations.SerializedName;

/**
 * @author LBDL
 * @desc 报考界面的专业列表
 *
 */
public class KaoMajor {
	
	/**
	 * 搜索结果数量
	 * */
	@SerializedName("count")
	public String countString;
	
	/**
	 * 专业列表
	 * */
	@SerializedName("data")
	public List<Major> majorList;

}
