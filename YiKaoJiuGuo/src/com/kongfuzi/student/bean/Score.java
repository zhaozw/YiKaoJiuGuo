package com.kongfuzi.student.bean;

import com.google.gson.annotations.SerializedName;

public class Score {
	
	@SerializedName("province")
	public String province;
	
	@SerializedName("hscore")
	public String hscore;
	
	@SerializedName("lscore")
	public String lscore;
	
	@SerializedName("course")
	public String course;

}
