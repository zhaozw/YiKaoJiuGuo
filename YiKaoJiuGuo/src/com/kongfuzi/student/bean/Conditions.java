package com.kongfuzi.student.bean;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

/**
 * @author LBDL
 * @desc ɸѡ����(���е�)
 *
 */
public class Conditions implements Serializable{

	@SerializedName("id")
	public int id;
	
	@SerializedName("ename")
	public String ename;
/*	//û�õ�
	@SerializedName("evalue")
	public int evalue;
	//û�õ�
	@SerializedName("disorder")
	public int disorder;*/
}
