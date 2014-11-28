package com.kongfuzi.student.support;

import java.io.File;
import java.io.FileOutputStream;

import android.graphics.Bitmap;
import android.os.Environment;

/**
 * @author LBDL
 * @create 2014/8/20
 * @desc 将bitmap图片保存到本地
 */
public class BitmapImage {
	
	private static final String FILE_NAME = "photo.png";
	public static final String FILE_PATH = Environment.getExternalStorageDirectory()+"/kongfuzi/photo"+FILE_NAME;
	public static File saveBitmap(Bitmap bitmap) {
		File file = new File(Environment.getExternalStorageDirectory() + "/kongfuzi/photo",FILE_NAME);
		
		if (file.exists()) {
			file.delete();
		}
		
		try {
			FileOutputStream stream = new FileOutputStream(file);
			bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream);
			stream.flush();
			stream.close();
			
		} catch (Exception e) {
		}
		return file;
	}
}
