package com.kongfuzi.student.bean;

import com.google.gson.annotations.SerializedName;

public class User {

	@SerializedName("secretkey")
	public String secretkey;
	
	@SerializedName("studentid")
	public int id;
	
	/**
	 * �ǳ�
	 * */
	@SerializedName("username")
	public String userName;
	
	/**
	 * �Ա�
	 * */
	@SerializedName("sex")
	public String gender;
	
	
	@SerializedName("message")
	public String message;
	
	/**
	 * ͷ��
	 * */
	@SerializedName("face")
	public Avatar avatar;
	/**
	 * �γ���
	 * */
	@SerializedName("course")
	public String courseNum;
	/**
	 * ־Ը��
	 * */
	@SerializedName("volunteer")
	public String volunteerNum;
	/**
	 * �ճ���
	 * */
	@SerializedName("trip")
	public String scheduleNum;
	/**
	 * �ղ���
	 * */
	@SerializedName("collect")
	public String collectionNum;
	/**
	 * ��Դ��
	 * */
	@SerializedName("provincetitle")
	public String origin;
	/**
	 * ��������
	 * */
	@SerializedName("category")
	public String type;
	/**
	 * �������ͱ���
	 * */
	@SerializedName("categorytitle")
	public String typeTitle;
	/**
	 * �����
	 * */
	@SerializedName("course1")
	public String property;
	/**
	 * ����Ʊ���
	 * */
	@SerializedName("coursetitle")
	public String propertyTitle;
}
