package com.kongfuzi.student.support.network;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.UUID;

import junit.framework.Test;

import org.json.JSONException;
import org.json.JSONObject;

import com.kongfuzi.student.app.YiKaoApplication;

import android.util.Log;

/**
 * @author LBDL
 * @Desc 上传图片
 * 
 */
public class ImageHttp {

	private static final int TIME_OUT = 10 * 1000; // 超时时间
	private static final String CHARSET = "UTF-8"; // 设置编码
	private static final String TAG = "ImageHttp";
	String BOUNDARY = UUID.randomUUID().toString(); // 边界标识 随机生成
	String PREFIX = "--", LINE_END = "\r\n";
	String CONTENT_TYPE = "multipart/form-data"; // 内容类型
	
	//普通字符串数据  
    private void writeStringParam(String key, String value, DataOutputStream dos) throws IOException {  
            dos.writeBytes(PREFIX + BOUNDARY + LINE_END);  
            dos.writeBytes("Content-Disposition: form-data; name=\"" + key  
                    + "\"" + LINE_END);  
            dos.writeBytes(LINE_END);  
            dos.writeBytes(URLEncoder.encode(value, CHARSET) + LINE_END);  
    } 

	/**
	 * android上传文件到服务器
	 * 
	 * @param file
	 *            要上传的文件
	 * @param RequestURL
	 *            请求的rul
	 * @return 返回响应的
	 */
	public JSONObject uploadFile(File file, String RequestURL) {
		String result = null;
		

		try {
			Log.i("UploadFile", "requestUrl:"+ RequestURL);
			URL url = new URL(RequestURL);
			// URL url = new URL(RequestURL + "?" + "secretkey=" +
			// this.secretKey);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setReadTimeout(TIME_OUT);
			conn.setConnectTimeout(TIME_OUT);
			conn.setDoInput(true); // 允许输入
			conn.setDoOutput(true); // 允许输出
			conn.setUseCaches(false); // 不允许使用缓存
			conn.setRequestMethod("POST"); // 请求方式
			conn.setRequestProperty("Charset", CHARSET); // 设置编码
			conn.setRequestProperty("connection", "keep-alive");
			conn.setRequestProperty("Content-Type", CONTENT_TYPE + ";boundary=" + BOUNDARY);
			conn.setRequestProperty("name", "file");
			

			if (file != null) {
				/**
				 * 当文件不为空，把文件包装并且上传
				 */
				DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
				
				writeStringParam("mid", ""+YiKaoApplication.getStudentId(), dos);
				
				StringBuffer sb = new StringBuffer();

				sb.append(PREFIX);
				sb.append(BOUNDARY);
				sb.append(LINE_END);
				/**
				 * 这里重点注意name里面的为服务器端要key 只有这个key 才可以得到对应的文件
				 * filename是文件的名字，包含后名的 比如:abc.png
				 */

				sb.append("Content-Disposition: form-data; name=\"file\";" + "filename=\"" + "test.jpg" + "\""
						+ LINE_END);
				sb.append("Content-Type: application/octet-stream; charset=" + CHARSET + LINE_END);
				sb.append(LINE_END);
				dos.write(sb.toString().getBytes());
				InputStream is = new FileInputStream(file);
				byte[] bytes = new byte[1024];
				int len = 0;
				while ((len = is.read(bytes)) != -1) {
					dos.write(bytes, 0, len);
				}
				is.close();
				dos.write(LINE_END.getBytes());
				byte[] end_data = (PREFIX + BOUNDARY + PREFIX + LINE_END).getBytes();
				dos.write(end_data);
				dos.flush();
				/**
				 * 获取响应 200=成功 当响应成功，获取响应的流
				 */
				int res = conn.getResponseCode();
				Log.i(TAG, "response code:" + res);
				 if(res==200)
				 {
					 Log.i(TAG, "request success");
					 InputStream input = conn.getInputStream();
					 StringBuffer sb1 = new StringBuffer();
					 int ss;
					 while ((ss = input.read()) != -1) {
						 sb1.append((char) ss);
					 }
					 result = sb1.toString();
					 Log.i(TAG, "result : " + result);
					 return new JSONObject(result);
				} else {
					Log.e(TAG, "request error");
				}
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
		}
		return null;
	}

}
