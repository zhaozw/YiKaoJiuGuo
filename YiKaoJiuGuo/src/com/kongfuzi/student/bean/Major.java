package com.kongfuzi.student.bean;

import com.google.gson.annotations.SerializedName;

/**
 * @author LBDL
 * @desc רҵ
 *
 */
public class Major {
	
	/**
	 * ͼƬ
	 * */
	@SerializedName("litpic")
	public String avatar;
	
	/**
	 * רҵ����
	 * */
	@SerializedName("title")
	public String major;
	
	/**
	 * ��ѧ����
	 * */
	@SerializedName("collegeName")
	public String collegeName;
	
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
	 * ��������
	 * */
	@SerializedName("reg")
	public Boolean reg;
	
	/**
	 * �ô�ѧ���Ƿ���רҵ����־Ը
	 * */
	@SerializedName("ishidden")
	public Boolean isHidden;
	
	/**
	 * רҵ����
	 * */
	@SerializedName("body")
	public String body;
	
	
	@SerializedName("id")
	public String id;

	
}
