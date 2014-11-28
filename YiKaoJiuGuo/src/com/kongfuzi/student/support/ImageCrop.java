package com.kongfuzi.student.support;

import java.io.File;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

/**
 * @author SLJM
 * @create 2014-4-9
 * @desc ��ȡͼƬ�Ĺ�����
 * 
 */
public class ImageCrop {

	// private Uri photo_location;
	public static final int PICK_FROM_CAMERA = 1;// ����
	public static final int PICK_FROM_FILE = 2;// �������
	public static final int PICK_FORM_CROP = 3;// �ü������Ƭ
	 private int OUT_PUT_X = 200, OUT_PUT_Y = 200;
	private AlertDialog dialog;
	private String[] items = new String[] { "�����ѡ��", "����", "ȡ��" };
	// �ļ�·��
	public File file_path = null;
	private Activity activity;

	public ImageCrop(Activity activity) {
		this.activity = activity;
		File dirFile = new File(Environment.getExternalStorageDirectory() + "/kongfuzi/photo/");
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}
		file_path = new File(Environment.getExternalStorageDirectory() + "/kongfuzi/photo/", "camera.jpg");
	}

	public void showDialog() {
		if (dialog == null) {
			dialog = new AlertDialog.Builder(activity).setTitle("ѡ��ͼƬ��Դ").setItems(items, new OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int position) {
					switch (position) {
					case 0:
						locationPhoto();
						break;
					case 1:
						cameraPhoto();
						break;

					default:
						break;
					}
				}

			}).create();
		}
		dialog.show();
	}

	/** ���ػ�ȡͼƬ */
	private void locationPhoto() {
		Intent intent = new Intent(Intent.ACTION_PICK);
		intent.setType("image/*");// ��Ƭ����
		
		// cropΪtrue�������ڿ�����intent��������ʾ��view���Լ���
		// intent.putExtra("crop", "true");
		activity.startActivityForResult(intent, PICK_FROM_FILE);

	}

	/** ���ջ�ȡͼƬ */
	private void cameraPhoto() {

		Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
		intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file_path));
		activity.startActivityForResult(intent, PICK_FROM_CAMERA);

	}

//	public Bitmap onActivityResult(int requestCode, int resultCode, Intent data) {
//
//		Bitmap bitmap = null;
//		Uri uri = data.getData();
//
//		switch (requestCode) {
//		case PICK_FROM_CAMERA:
//			doCrop(uri);
//			break;
//		case PICK_FROM_FILE:
//			doCrop(uri);
//			break;
//		case PICK_FORM_CROP:
//			bitmap = data.getParcelableExtra("data");
//			break;
//		}
//		return bitmap;
//	}

	public void doCrop(Uri uri) {
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");
		intent.putExtra("crop", "true");
		intent.putExtra("outputX", OUT_PUT_X);   
        intent.putExtra("outputY", OUT_PUT_Y); 
		intent.putExtra("outputFormat", "JPEG");
		intent.putExtra("return-data", true);
		activity.startActivityForResult(intent, PICK_FORM_CROP);
	}

}
