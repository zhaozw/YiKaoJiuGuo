package com.kongfuzi.student.support.utils;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.kongfuzi.student.bean.CollegeDetails;
import com.kongfuzi.student.sqlite.MySqliteHelper;
import com.umeng.analytics.c;

public class SQLiteOperate {
	private static final String TAG = "SQLiteOperate";
	private Context context;
	public void setContext(Context context){
		 this.context=context;
	}
	private MySqliteHelper helper;
	
	/**
	 * �������ݿ�
	 */
	public void createDB(){
		helper=new MySqliteHelper(context);
		SQLiteDatabase db=helper.getWritableDatabase();
		db.close();
	}
	/**
	 * 增加数据
	 * @return
	 */
	public long addData(CollegeDetails details){
		SQLiteDatabase db=helper.getWritableDatabase();
		ContentValues values=new ContentValues();
			values.put("id",details.id);
			values.put("title", details.title);
			values.put("flag", details.flag);
			values.put("litpic", details.litpic);
			values.put("people", details.people);
			values.put("reg", details.reg);
			values.put("isCollect", details.isCollect);
		long l=db.insert("collect", null, values);
		if(l>0){
			Log.i(TAG, "����ɹ�");
			Toast.makeText(context, "�ղسɹ�", Toast.LENGTH_SHORT).show();
		}else {
			Log.i(TAG, "����ʧ��");
		}
		return l;
	}
	
	public void delete(){
		SQLiteDatabase db=helper.getWritableDatabase();
		db.delete("news", null,null);
	}
	/**
	 * ��ѯ���ݿ�
	 */
	public List<String> quary(){
		List<String>list=new ArrayList<String>();
		SQLiteDatabase db=helper.getWritableDatabase();
		Cursor cursor=db.query("collect", null, null, null, null, null,"id desc");
		while (cursor.moveToNext()) {
		 
			int id=cursor.getInt(cursor.getColumnIndex("id"));
			String content=cursor.getString(cursor.getColumnIndex("content"));
			list.add(content);
		}
		db.close();
		return list;
	}
	
	/**
	 * �������ݿ�
	 */
	public void updateDate(){
		SQLiteDatabase db=helper.getWritableDatabase();
		ContentValues values=new ContentValues();
		int i=db.update("collect", values, "id=?", new String[]{"3"});
		if(i>0){
			Log.i(TAG, "���³ɹ�");
		}else {
			System.out.println("����ʧ��");
		}
		db.close();
	}
	
	
}
