package com.kongfuzi.student.sqlite;

import com.umeng.analytics.c;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author ZL
 * Êý¾Ý¿â°ïÖúÀà
 *
 */
public class MySqliteHelper extends SQLiteOpenHelper{
	private static  final String DB_COLLECT="collect_db";
	private static final int VERSION=1;
	
	public MySqliteHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		
	}
	public MySqliteHelper(Context context){
		super(context, DB_COLLECT,null, VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql="create table collect(id integer primary key,title varchar(32),flag varchar(32),"
				+ "litpic varchar(128),people integer ,reg varchar(32),isCollect varchar(32))";
		db.execSQL(sql);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		if(newVersion!=oldVersion){
			
		}
	}
	@Override
	public void onOpen(SQLiteDatabase db) {
		super.onOpen(db);
	}
}
