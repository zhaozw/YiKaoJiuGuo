package com.kongfuzi.student.bean;

import com.google.gson.annotations.SerializedName;

public class Major {
	
	/**
	 * רҵ����
	 * */
	@SerializedName("id")
	public int majorId;
	
	/**
	 * רҵ����
	 * 
	 * */
	@SerializedName("title")
	public String major;
	
	/**
	 * ��ѧ����
	 * */
	@SerializedName("collegeName")
	public String college;
	
	/**
	 * ͼƬ
	 * */
	@SerializedName("litpic")
	public String avatar;
	
	/**
	 * ��������
	 * */
	@SerializedName("flag")
	public String batch;
	
	/**
	 * ��������
	 * */
	@SerializedName("people")
	public int recruit_count;
	
	
	/**
	 * ��ѧid
	 * */
	@SerializedName("aid")
	public int collegeId;
	
	/**
	 * �Ƿ��ѱ���
	 * */
	@SerializedName("isReg")
	public Boolean isReg;
	
	/**
	 * �ô�ѧ���Ƿ���רҵ����־Ը
	 * */
	@SerializedName("ishidden")
	public Boolean isHidden;
	/**
	 * ������
	 * */
	@SerializedName("reg")
	public int kao_count;
	
	/**
	 * רҵ����
	 * */
	@SerializedName("body")
	public String body;
	

}
