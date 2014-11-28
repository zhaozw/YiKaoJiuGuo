package com.kongfuzi.student.ui.view;

import com.kongfuzi.student.R;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

/**
 * @author LBDL
 * @create 2014/8/15
 * @desc 请求网络时的等待dialog
 *
 */
public class LoadingDialog extends Dialog{
	
	private static int default_width = 160; // 默认宽度  
    private static int default_height = 120;// 默认高度  
    private static int theme = R.style.loadingDialog;
    
    private static LoadingDialog dialog = null;
    
    public static LoadingDialog getInstance(Context context){
    	dialog = new LoadingDialog(context);
    	return dialog;
    	
    }
	
	private LoadingDialog(Context context){
//		super(context);
		this(context, default_width, default_height);  
	}
//	
//	public LoadingDialog(Context context, int layout, int style) {  
//    }
	
	public LoadingDialog(Context context, int width, int height) { 
		super(context, theme); 
		setContentView(R.layout.loading_dialog);
		// 设置窗口属性  
        Window window = getWindow();  
        WindowManager.LayoutParams params = window.getAttributes();  
        // 设置宽度、高度、密度、对齐方式  
        float density = getDensity(context);  
        params.width = (int) (width * density);  
        params.height = (int) (height * density);  
        params.gravity = Gravity.CENTER;  
        window.setAttributes(params);
		
	}	
	
	/** 
     * 获取显示密度 
     *  
     * @param context 
     * @return 
     */  
    public float getDensity(Context context) {  
        Resources res = context.getResources();  
        DisplayMetrics dm = res.getDisplayMetrics();  
        return dm.density;  
    }
	
	public Boolean isShow() {
		return isShowing() ? true :false;
	}

}
