package com.kongfuzi.student.bean;

import com.google.gson.annotations.SerializedName;

/**
 * @author LBDL
 * @desc ����
 */
public class KaoDian {
	
	/**
	 * ����id
	 * */
	@SerializedName("id")
	public String id;
	
	/**
	 * ��������
	 * */
	@SerializedName("address")
	public String address;
	
	/**
	 * ���ϱ���ʱ��
	 * */
	@SerializedName("registr")
	public String onlineTime;
	
	/**
	 * 
	 * �ֳ�ȷ��ʱ��
	 * */
	@SerializedName("confirmtime")
	public String confirmTime;
	
	/**
	 * 
	 * ����ʱ��
	 * */
	@SerializedName("examtime")
	public String examTime;

}
