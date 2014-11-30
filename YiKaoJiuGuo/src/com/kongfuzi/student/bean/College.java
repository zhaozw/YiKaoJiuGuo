package com.kongfuzi.student.bean;

import java.util.List;

import com.google.gson.annotations.SerializedName;

/**
 * @author LBDL
 * @desc ��ѧ
 *
 */
public class College {
	
	/**
	 * ��ѧ����
	 * */
	@SerializedName("title")
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
	@SerializedName("id")
	public String collegeId;
	
	/**
	 * �Ƿ��ѱ���
	 * */
	@SerializedName("isReg")
	public Boolean isReg;
	
	/**
	 * ��������
	 * */
	@SerializedName("reg")
	public Boolean reg;
	
	/**
	 * �Ƿ��ղ�
	 * */
	@SerializedName("isCollect")
	public Boolean  isCollect;
	
	/**
	 * רҵ�б�
	 * 
	 * */
	@SerializedName("proList")
	public List<Major> proList;
	
	/**
	 * �ô�ѧ���Ƿ���רҵ����־Ը
	 * */
	@SerializedName("ishidden")
	public Boolean isHidden;
	
	

}
