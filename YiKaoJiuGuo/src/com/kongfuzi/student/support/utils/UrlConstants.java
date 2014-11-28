package com.kongfuzi.student.support.utils;

import java.util.List;

import com.kongfuzi.student.app.YiKaoApplication;
import com.kongfuzi.student.bean.Conditions;

import android.text.TextUtils;

/**
 * @author LBDL
 * @desc �ӿ�
 *
 */
public class UrlConstants {
	
	//����רҵһ������
	public static final String FIRST_MAJOR_CATEGORY = "http://www.kongfuzi.com/v1.php?m=Data&a=getclassify&id=2";
	//����רҵ��������
	public static final String SECOND_MAJOR_CATEGORY = "http://www.kongfuzi.com/v1.php?m=Data&a=getclassify";
	//����רҵ��������
	public static final String THIRD_MAJOR_CATEGORY = "http://www.kongfuzi.com/v1.php?m=Data&a=getThreeCategory";
	//¼ȡ��ʽ
	public static final String MATRICULATE_MODE = "http://www.kongfuzi.com/v1.php?m=Data&a=getclassify&id=15";
	//��������
	public static final String BATCH = "http://www.kongfuzi.com/v1.php?m=Data&a=getBatch";
	//�������
	public static final String PROPERTY = "http://www.kongfuzi.com/v1.php?m=Data&a=getclassify&id=1";
	//��Դ��  ��ѧ���ڵ�
	public static final String PROVINCE = "http://www.kongfuzi.com/v1.php?m=Data&a=getclassify&id=30";
	//��������
	public static final String TYPE = "http://www.kongfuzi.com/v1.php?m=Data&a=getclassify&id=668";
	//רҵ�б�
	public static final String MAJOR_LIST = "http://www.kongfuzi.com/v2.php?m=College&a=search";
	//רҵ����
	public static final String MAJOR_DETAIL = "http://www.kongfuzi.com/v1.php?m=College&a=view";
	//��������
	public static final String RECRUIT_DETAIL = "http://www.kongfuzi.com/v1.php?m=College&a=recruitView";
	//���꿼��
	public static final String EXAMINATION_LIST = "http://www.kongfuzi.com/v1.php?m=College&a=examList";
	//¼ȡ����
	public static final String ADMISSION_SCORE= "http://www.kongfuzi.com/v1.php?m=College&a=admissionScore";
	//�������
	public static final String MORE_SCORE = "http://www.kongfuzi.com/v1.php?m=College&a=moreScore";
	//�����ճ�
	public static final String EXAMINATION_SCHEDULE = "http://www.kongfuzi.com/v1.php?m=Member&a=myTrips";
	//�����ճ�����
	public static final String SCHEDULE_DETAIL = "http://www.kongfuzi.com/v1.php?m=Member&a=tripsContent";
	//�ճ̵�ַ�б�
	public static final String SCHEDULE_ADDRESS = "http://www.kongfuzi.com/v1.php?m=Member&a=getExamAddress";
	//��¼
	public static final String LOGIN = "http://www.kongfuzi.com/v1.php?m=Public&a=login";
	//ע��
	public static final String LOGOUT = "http://www.kongfuzi.com/v1.php?m=Public&a=logout";
	//ע��
	public static final String REGISTER = "http://www.kongfuzi.com/v1.php?m=Public&a=reg";
	//��ȡ��֤��
	public static final String GET_CODE = "http://www.kongfuzi.com/v1.php?m=Public&a=getCode";
	//�һ�����
	public static final String FIND_PWD = "http://www.kongfuzi.com/v1.php?m=Public&a=findPwd";
	//�޸�����
	public static final String MODIFY_PWD = "http://www.kongfuzi.com/v1.php?m=Member&a=changePwd";
	//����־Ը
	public static final String JOIN_VOLUNTEER = "http://www.kongfuzi.com/v1.php?m=College&a=reg";
	//�ҵ�־Ը
	public static final String MY_VOLUNTEER = "http://www.kongfuzi.com/v1.php?m=Member&a=myReg";
	//ɾ��־Ը
	public static final String DELETE_VOLUNTEER = "http://www.kongfuzi.com/v1.php?m=Member&a=delReg";
	//��ȡ������Ϣ
	public static final String GET_USER_INFO = "http://www.kongfuzi.com/v1.php?m=Member&a=getMessage";
	//����ͷ��
	public static final String MODIFY_AVATAR = "http://www.kongfuzi.com/v1.php?m=Member&a=uploadsFace";
	//���ĸ�����Ϣ
	public static final String MODIFY_USER_INFO = "http://www.kongfuzi.com/v1.php?m=Member&a=changeMessage";
	//�ҵ�
	public static final String MY_INFO = "http://www.kongfuzi.com/v2.php?m=Member&a=myMessage1";
	//�ҵĿγ�
	public static final String MY_COURSE = "http://www.kongfuzi.com/v1.php?m=Member&a=myCourse";
	//�ҵ��ղ�
	public static final String MY_COLLECTION = "http://www.kongfuzi.com/v2.php?m=Member&a=myCollect";
	//�汾����
	public static final String VERSION = "http://fir.im/api/v2/app/version/544277c9221e12596d000019?token=ll5PdpHugzlfglcRKiRnQZ7bOouHjvdndKysAQo7";
	//��Ϣ�����б�
	public static final String MESSAGE_LIST = "http://www.kongfuzi.com/mobile.php?m=Index&a=index";
	//��Ϣ��������
	public static final String MESSAGE_DETAIL = "http://www.kongfuzi.com/mobile.php?m=Index&a=view";
	//����ҳ��ȡ����
	public static final String HOME_CATEGORY = "http://www.kongfuzi.com/mobile.php?m=Data&a=getCategory";
	
	/**
	 * רҵ�б�url
	 * @param content
	 * �����Ĵ�ѧ���� ���Ϊ���ַ���  ˵������ͷ������ɸѡ
	 * 
	 * */
	public static String getMajorListUrl(String content){
		
		String urlString = null;
		
		if (TextUtils.isEmpty(content)) {
			//ɸѡ����
			List<Conditions> filterList = YiKaoApplication.getConditionsList();
			
			urlString = MAJOR_LIST + "&score=" + filterList.get(0).id + "&category=" + filterList.get(1).id + "&cid="
					+ filterList.get(8).id + "&three=" + filterList.get(9).id + "&methods=" + filterList.get(2).id
					+ "&batch=" + filterList.get(3).id + "&city=" + filterList.get(4).id + "&course="
					+ filterList.get(5).id + "&pid=" + filterList.get(6).id + "&other=" + filterList.get(7).id;
		}else {
			//ͷ��ɸѡ
			urlString = UrlConstants.MAJOR_LIST + "&title=" + content;
		}
		return urlString;
	}
	
}
