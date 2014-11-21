package com.kongfuzi.student.support.network;

import com.kongfuzi.lib.volley.Response.ErrorListener;
import com.kongfuzi.lib.volley.VolleyError;

public class Error implements ErrorListener{

	@Override
	public void onErrorResponse(VolleyError error) {
		
		ErrorDialog dialog = ErrorDialog.getInstance();
		
//		String message = (error.getMessage() == null) ? errorMessage:error.getMessage();
		
//		dialog.show(get, tag)
	}

}
 