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
 * @desc ��������ʱ�ĵȴ�dialog
 *
 */
public class LoadingDialog extends Dialog{
	
	private static int default_width = 160; // Ĭ�Ͽ��  
    private static int default_height = 120;// Ĭ�ϸ߶�  
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
		// ���ô�������  
        Window window = getWindow();  
        WindowManager.LayoutParams params = window.getAttributes();  
        // ���ÿ�ȡ��߶ȡ��ܶȡ����뷽ʽ  
        float density = getDensity(context);  
        params.width = (int) (width * density);  
        params.height = (int) (height * density);  
        params.gravity = Gravity.CENTER;  
        window.setAttributes(params);
		
	}	
	
	/** 
     * ��ȡ��ʾ�ܶ� 
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
