package com.kongfuzi.student.bean;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

/**
 * @author LBDL
 * @desc 筛选条件(所有的)
 *
 */
public class Conditions implements Serializable{

	@SerializedName("id")
	public int id;
	
	@SerializedName("ename")
	public String ename;
/*	//没用的
	@SerializedName("evalue")
	public int evalue;
	//没用的
	@SerializedName("disorder")
	public int disorder;*/
}
