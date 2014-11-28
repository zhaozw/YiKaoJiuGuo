package com.kongfuzi.student.bean;

import java.util.List;

import com.google.gson.annotations.SerializedName;

/**
 * @author LBDL
 * @desc �����ճ�
 *
 */

public class ExaminationSchedule {
	
	/**
	 * ��ѧ����
	 * */
	@SerializedName("title")
	public String school;
	/**
	 * רҵid
	 * */
	@SerializedName("id")
	public int major_id;
	/**
	 * רҵ����
	 * */
	@SerializedName("ptitle")
	public String major;
	/**
	 * ���ϱ���
	 * */
	@SerializedName("registr")
	public String online;
	/**
	 * ����
	 * */
	@SerializedName("address")
	public String site;
	/**
	 * �ֳ�ȷ��
	 * */
	@SerializedName("confirmtime")
	public String confirm;
	/**
	 * ����ʱ��
	 * */
	@SerializedName("examtime")
	public String date;
	/**
	 * ���Է�Χ
	 * */
	@SerializedName("examscope")
	public String range;
	
	@SerializedName("content")
	public Schedule schedules;
	
//	/**
//	 *	�����б�
//	 */
//	@SerializedName("add")
//	public List<ExamSite> examSites; 
}
