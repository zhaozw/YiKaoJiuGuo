package com.kongfuzi.student.support.network;

import me.maxwin.view.XListView;
import android.app.FragmentManager;
import android.os.Handler;

import com.kongfuzi.lib.volley.Response.ErrorListener;
import com.kongfuzi.lib.volley.VolleyError;
import com.kongfuzi.student.app.YiKaoApplication;

public class Error implements ErrorListener{
	private XListView mListView = null;
	
	public Error(XListView mListView){
		this.mListView = mListView;
	}
	public Error(){
		mListView = null;
	}
	@Override
	public void onErrorResponse(VolleyError error) {
		if(mListView != null){
			mListView.stopLoadMore();
			mListView.stopRefresh();
		}
		
		String message = (error.getMessage() == null) ? "ÍøÂçÇëÇó´íÎó,Çë¼ì²éÍøÂç!":error.getLocalizedMessage();
		
		ErrorDialog dialog = ErrorDialog.getInstance(message);
		
		
		if(dialog != null){
			dialog.show(YiKaoApplication.fragmentManager, "´íÎó");
		}
		
	}

}
 