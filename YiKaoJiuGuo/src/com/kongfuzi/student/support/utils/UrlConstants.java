package com.kongfuzi.student.support.utils;

import java.util.List;

import com.kongfuzi.student.app.YiKaoApplication;
import com.kongfuzi.student.bean.Conditions;

import android.text.TextUtils;

/**
 * @author LBDL
 * @desc 接口
 *
 */
public class UrlConstants {
	
	//招生专业一级分类
	public static final String FIRST_MAJOR_CATEGORY = "http://www.kongfuzi.com/v1.php?m=Data&a=getclassify&id=2";
	//招生专业二级分类
	public static final String SECOND_MAJOR_CATEGORY = "http://www.kongfuzi.com/v1.php?m=Data&a=getclassify";
	//招生专业三级分类
	public static final String THIRD_MAJOR_CATEGORY = "http://www.kongfuzi.com/v1.php?m=Data&a=getThreeCategory";
	//录取方式
	public static final String MATRICULATE_MODE = "http://www.kongfuzi.com/v1.php?m=Data&a=getclassify&id=15";
	//本科批次
	public static final String BATCH = "http://www.kongfuzi.com/v1.php?m=Data&a=getBatch";
	//文理分类
	public static final String PROPERTY = "http://www.kongfuzi.com/v1.php?m=Data&a=getclassify&id=1";
	//生源地  大学所在地
	public static final String PROVINCE = "http://www.kongfuzi.com/v1.php?m=Data&a=getclassify&id=30";
	//考试类型
	public static final String TYPE = "http://www.kongfuzi.com/v1.php?m=Data&a=getclassify&id=668";
	//专业列表
	public static final String MAJOR_LIST = "http://www.kongfuzi.com/v2.php?m=College&a=search";
	//专业详情
	public static final String MAJOR_DETAIL = "http://www.kongfuzi.com/v1.php?m=College&a=view";
	//招生详情
	public static final String RECRUIT_DETAIL = "http://www.kongfuzi.com/v1.php?m=College&a=recruitView";
	//历年考题
	public static final String EXAMINATION_LIST = "http://www.kongfuzi.com/v1.php?m=College&a=examList";
	//录取分数
	public static final String ADMISSION_SCORE= "http://www.kongfuzi.com/v1.php?m=College&a=admissionScore";
	//更多分数
	public static final String MORE_SCORE = "http://www.kongfuzi.com/v1.php?m=College&a=moreScore";
	//考试日程
	public static final String EXAMINATION_SCHEDULE = "http://www.kongfuzi.com/v1.php?m=Member&a=myTrips";
	//考试日程详情
	public static final String SCHEDULE_DETAIL = "http://www.kongfuzi.com/v1.php?m=Member&a=tripsContent";
	//日程地址列表
	public static final String SCHEDULE_ADDRESS = "http://www.kongfuzi.com/v1.php?m=Member&a=getExamAddress";
	//登录
	public static final String LOGIN = "http://www.kongfuzi.com/v1.php?m=Public&a=login";
	//注销
	public static final String LOGOUT = "http://www.kongfuzi.com/v1.php?m=Public&a=logout";
	//注册
	public static final String REGISTER = "http://www.kongfuzi.com/v1.php?m=Public&a=reg";
	//获取验证码
	public static final String GET_CODE = "http://www.kongfuzi.com/v1.php?m=Public&a=getCode";
	//找回密码
	public static final String FIND_PWD = "http://www.kongfuzi.com/v1.php?m=Public&a=findPwd";
	//修改密码
	public static final String MODIFY_PWD = "http://www.kongfuzi.com/v1.php?m=Member&a=changePwd";
	//加入志愿
	public static final String JOIN_VOLUNTEER = "http://www.kongfuzi.com/v1.php?m=College&a=reg";
	//我的志愿
	public static final String MY_VOLUNTEER = "http://www.kongfuzi.com/v1.php?m=Member&a=myReg";
	//删除志愿
	public static final String DELETE_VOLUNTEER = "http://www.kongfuzi.com/v1.php?m=Member&a=delReg";
	//获取个人信息
	public static final String GET_USER_INFO = "http://www.kongfuzi.com/v1.php?m=Member&a=getMessage";
	//更改头像
	public static final String MODIFY_AVATAR = "http://www.kongfuzi.com/v1.php?m=Member&a=uploadsFace";
	//更改个人信息
	public static final String MODIFY_USER_INFO = "http://www.kongfuzi.com/v1.php?m=Member&a=changeMessage";
	//我的
	public static final String MY_INFO = "http://www.kongfuzi.com/v2.php?m=Member&a=myMessage1";
	//我的课程
	public static final String MY_COURSE = "http://www.kongfuzi.com/v1.php?m=Member&a=myCourse";
	//我的收藏
	public static final String MY_COLLECTION = "http://www.kongfuzi.com/v2.php?m=Member&a=myCollect";
	//版本更新
	public static final String VERSION = "http://fir.im/api/v2/app/version/544277c9221e12596d000019?token=ll5PdpHugzlfglcRKiRnQZ7bOouHjvdndKysAQo7";
	//消息推送列表
	public static final String MESSAGE_LIST = "http://www.kongfuzi.com/mobile.php?m=Index&a=index";
	//消息推送详情
	public static final String MESSAGE_DETAIL = "http://www.kongfuzi.com/mobile.php?m=Index&a=view";
	//引导页获取分类
	public static final String HOME_CATEGORY = "http://www.kongfuzi.com/mobile.php?m=Data&a=getCategory";
	
	/**
	 * 专业列表url
	 * @param content
	 * 搜索的大学名称 如果为空字符串  说明不是头部条件筛选
	 * 
	 * */
	public static String getMajorListUrl(String content){
		
		String urlString = null;
		
		if (TextUtils.isEmpty(content)) {
			//筛选条件
			List<Conditions> filterList = YiKaoApplication.getConditionsList();
			
			urlString = MAJOR_LIST + "&score=" + filterList.get(0).id + "&category=" + filterList.get(1).id + "&cid="
					+ filterList.get(8).id + "&three=" + filterList.get(9).id + "&methods=" + filterList.get(2).id
					+ "&batch=" + filterList.get(3).id + "&city=" + filterList.get(4).id + "&course="
					+ filterList.get(5).id + "&pid=" + filterList.get(6).id + "&other=" + filterList.get(7).id;
		}else {
			//头部筛选
			urlString = UrlConstants.MAJOR_LIST + "&title=" + content;
		}
		return urlString;
	}
	
}
