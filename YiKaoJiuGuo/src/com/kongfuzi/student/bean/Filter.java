package com.kongfuzi.student.bean;

import android.util.Pair;

import com.kongfuzi.lib.volley.Response.Listener;



public class Filter {

	private int score;
	
	// 专业分类 一级
	private int firstId;
	private String firstCategory;

	// 专业分类 二级
	private int secondId;
	private String secondCategory;

	// 专业分类 三级
	private int thirdId;
	private String thirdCategory;

	// 录取方式
	private int modeId;
	private String mode;

	// 本科批次
	private int batchId;
	private String batch;

	// 大学所在地
	private int collegeSiteId;
	private String collegeSite;

	// 文理分科
	private int propertyId;
	private String property;

	// 生源地
	private int originSiteId;
	private String originSite;

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getFirstId() {
		return firstId;
	}

	public void setFirstId(int firstId) {
		this.firstId = firstId;
	}

	public String getFirstCategory() {
		return firstCategory;
	}

	public void setFirstCategory(String firstCategory) {
		this.firstCategory = firstCategory;
	}

	public int getSecondId() {
		return secondId;
	}

	public void setSecondId(int secondId) {
		this.secondId = secondId;
	}

	public String getSecondCategory() {
		return secondCategory;
	}

	public void setSecondCategory(String secondCategory) {
		this.secondCategory = secondCategory;
	}

	public int getThirdId() {
		return thirdId;
	}

	public void setThirdId(int thirdId) {
		this.thirdId = thirdId;
	}

	public String getThirdCategory() {
		return thirdCategory;
	}

	public void setThirdCategory(String thirdCategory) {
		this.thirdCategory = thirdCategory;
	}

	public int getModeId() {
		return modeId;
	}

	public void setModeId(int modeId) {
		this.modeId = modeId;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public int getBatchId() {
		return batchId;
	}

	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}

	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

	public int getCollegeSiteId() {
		return collegeSiteId;
	}

	public void setCollegeSiteId(int collegeSiteId) {
		this.collegeSiteId = collegeSiteId;
	}

	public String getCollegeSite() {
		return collegeSite;
	}

	public void setCollegeSite(String collegeSite) {
		this.collegeSite = collegeSite;
	}

	public int getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(int propertyId) {
		this.propertyId = propertyId;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public int getOriginSiteId() {
		return originSiteId;
	}

	public void setOriginSiteId(int originSiteId) {
		this.originSiteId = originSiteId;
	}

	public String getOriginSite() {
		return originSite;
	}

	public void setOriginSite(String originSite) {
		this.originSite = originSite;
	}

}
