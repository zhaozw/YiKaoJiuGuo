package com.kongfuzi.student.bean;

import java.util.List;

import com.google.gson.annotations.SerializedName;

/**
 * @author LBDL
 * @desc ���������רҵ�б�
 *
 */
public class KaoMajor {
	
	/**
	 * �����������
	 * */
	@SerializedName("count")
	public String countString;
	
	/**
	 * רҵ�б�
	 * */
	@SerializedName("data")
	public List<Major> majorList;

}
