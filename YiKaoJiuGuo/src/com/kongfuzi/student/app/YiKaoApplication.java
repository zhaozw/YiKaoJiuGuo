package com.kongfuzi.student.app;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.util.Log;

import com.kongfuzi.lib.volley.RequestQueue;
import com.kongfuzi.lib.volley.toolbox.Volley;
import com.kongfuzi.student.R;
import com.kongfuzi.student.bean.Conditions;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.utils.StorageUtils;

/**
 * @author LBDL
 *
 */

public class YiKaoApplication extends Application {
	
	/**
	 * 筛选条件
	 * */
	private static List<Conditions> list = new ArrayList<Conditions>();
	private static ImageLoader imageLoader;
	private static RequestQueue queue = null;
	
	public static SharedPreferences.Editor editor;
	private static SharedPreferences sharedPreferences = null;
	
	private static YiKaoApplication globalContext = null;
	
	private static final String FIRST_START = "first_start";
	private static final String ORIGIN_ZONE = "origin_zone";
	private static final String MAJOR = "major";
	private static final String STUDENT_ID = "studentid";
	private static final String PHONE = "phone";
	private static final String SECRETKEY ="secretkey";
	
	private static final String USER_INFO = "user_info";
	
	private static String FILE_CACHE;
	private static final String TAG = "YiKaoApplication";
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		globalContext = this;
		imageLoader = ImageLoader.getInstance();
		queue = Volley.newRequestQueue(globalContext);
		sharedPreferences = globalContext.getSharedPreferences(USER_INFO, Context.MODE_PRIVATE);
		editor = sharedPreferences.edit();
		FILE_CACHE = Environment.getExternalStorageDirectory().getPath() + "yikaojiuguo/cache";
		
		initImageLoader();
		
		for (int i = 0; i < 10; i++) {
			Conditions conditions = new Conditions();
			list.add(conditions);
		}
	}
	
	private void initImageLoader() {
		File cacheDir = StorageUtils.getOwnCacheDirectory(globalContext, FILE_CACHE);

		DisplayImageOptions defaultOption = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisc(true)
				.considerExifParams(false).imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)
				.displayer(new FadeInBitmapDisplayer(300)).resetViewBeforeLoading(true)
				.showImageOnLoading(R.drawable.default_top).showImageForEmptyUri(R.drawable.default_top)
				.showImageOnFail(R.drawable.default_top).build();

		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(globalContext)
				.memoryCacheExtraOptions(480, 800).threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory().discCache(new UnlimitedDiscCache(cacheDir))
				.discCacheFileNameGenerator(new HashCodeFileNameGenerator()).defaultDisplayImageOptions(defaultOption)
				.tasksProcessingOrder(QueueProcessingType.LIFO).build();
		imageLoader.init(config);
	}
	
	public static YiKaoApplication getInstance() {
		
		return globalContext;
	}
	
	public static synchronized ImageLoader getImageLoaderInstance() {
		return imageLoader;
	}
	
	public static RequestQueue getQueueInstance() {
		return queue;
	}
	
	/**保存studentid*/
	public static void putStudentId(int id,String phone,String secretkey) {
		
		Log.i(TAG, "studentId = " + id);
		editor.putInt(STUDENT_ID, id);
		editor.putString(SECRETKEY,secretkey);
		editor.putString(PHONE, phone);
		
		editor.commit();

	}
	
	/**得到 studentId*/
	public static int getStudentId() {
		
		int studentId = sharedPreferences.getInt(STUDENT_ID,0);
		Log.i(TAG, "studentId = " + studentId);
		return studentId;
		
	}
	
	/**得到 phone*/
	public static String getPhone() {
		
		String phone = sharedPreferences.getString(PHONE, "");
		Log.i(TAG, "secretkey = " + phone);
		return phone;
		
	}

	public static List<Conditions> getConditionsList(){
		return list;
	}
	
//	/**
//	 * 得到数据库的写入权限
//	 * */
//	public static synchronized SQLiteDatabase getDataBaseWritable() {
//		return dbHelper.getWritableDatabase();
//	}
//	/**
//	 * 得到数据库的读权限
//	 * 
//	 * */
//	public static synchronized SQLiteDatabase getDataBaseReadable() {
//		return dbHelper.getReadableDatabase();
//	}
}
