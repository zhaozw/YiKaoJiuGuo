package com.kongfuzi.student.bean;

import com.google.gson.annotations.SerializedName;

/**
 * @author LBDL
 * @desc �����ճ�����
 *
 */
public class Schedule {
	
	
	/**
	 * �ճ�id
	 */
	@SerializedName("id")
	public int id;
	
	/**
	 * ����
	 * */
	@SerializedName("address")
	public String site;
	/**
	 * ����ʱ��
	 * */
	@SerializedName("examtime")
	public String date;
}
