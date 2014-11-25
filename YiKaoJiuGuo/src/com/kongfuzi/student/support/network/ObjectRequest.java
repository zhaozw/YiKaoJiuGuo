package com.kongfuzi.student.support.network;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.kongfuzi.lib.volley.NetworkResponse;
import com.kongfuzi.lib.volley.ParseError;
import com.kongfuzi.lib.volley.Response;
import com.kongfuzi.lib.volley.Response.Listener;
import com.kongfuzi.lib.volley.toolbox.HttpHeaderParser;
import com.kongfuzi.lib.volley.toolbox.JsonRequest;

/**
 * @author LBDL
 * @desc «Î«ÛÕ¯¬Á
 * @param <T>
 */
public class ObjectRequest<T> extends JsonRequest<T> {

	private Type mType = null;
	private static final String TAG = "NetworkRequest";

	/**
	 * GET«Î«Û
	 * */
	public ObjectRequest(String url, Listener<T> listener, Type type) {
		super(Method.GET, url, null, listener, new Error());
		this.mType = type;
	}

	/**
	 * POST«Î«Û
	 * */
	public ObjectRequest(String url, Map<String, String> body, Listener<T> listener, Type type) {
		super(Method.POST, url, body, listener, new Error());
		this.mType = type;
	}

	protected Response<T> parseNetworkResponse(NetworkResponse response) {
		try {
			return (Response<T>) Response.success(
					parseStrToNoteArray(new String(response.data, HttpHeaderParser.parseCharset(response.headers)),
							mType), HttpHeaderParser.parseCacheHeaders(response));
		} catch (UnsupportedEncodingException e) {
			return Response.error(new ParseError(e));
		}
	}

	private T parseStrToNoteArray(String resultStr, Type mType) {
		Gson gson = new Gson();
		JsonParser jsonParse = new JsonParser();
		JsonObject rootObj = jsonParse.parse(resultStr).getAsJsonObject();
		Log.i(TAG, "response = " + resultStr);
		T t = null;

		JSONObject jsonObject;
		try {
			jsonObject = new JSONObject(resultStr);

			if (jsonObject.optBoolean("success")) {

				String json = gson.toJson(rootObj.getAsJsonObject("data"));
				t = gson.fromJson(json, mType);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return t;
	}

}
