package com.kongfuzi.student.ui.global;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.kongfuzi.student.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;
import com.umeng.analytics.MobclickAgent;

/**
 * @author LBDL
 * @create 2014/8/15
 * @desc 大图 (多张图片)
 */
public class LargeImagesActivity extends BaseActivity implements OnClickListener,OnPageChangeListener {
	
	private ViewPager image_vp;
	private TextView index_tv;
	private FrameLayout image_fl;
	
	private ArrayList<String> url_array;
	public static final String TAG = "LargeImagesActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_large_images);
		
		image_vp = (ViewPager) findViewById(R.id.image_large_images_vp);
		index_tv = (TextView) findViewById(R.id.index_large_images_tv);
		image_fl = (FrameLayout) findViewById(R.id.large_images_fl);
		
		image_fl.setOnClickListener(this);
		
		Intent intent = getIntent();
		url_array = intent.getStringArrayListExtra("url_array");
		
		
		int page = intent.getIntExtra("page", 0);
		index_tv.setText( page + 1 + "/" + url_array.size());
		Log.i(TAG, "page = " + page);
		//设置要显示的页面
		image_vp.setCurrentItem(intent.getIntExtra("page", 0));
		image_vp.setAdapter(new ImageAdapter());
		
		
		image_vp.setOnPageChangeListener(this);
		image_fl.setOnClickListener(this);
		
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		MobclickAgent.onPageStart(TAG);
	}


	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		//点击图片 finish Activity
		case R.id.large_images_fl:
			finish();
			break;

		default:
			break;
		}
	}
	
	
	@Override
	protected void onPause() {
		MobclickAgent.onPageEnd(TAG);
		super.onPause();
	}
	
	private class ImageAdapter extends PagerAdapter{
		
		private LayoutInflater inflater;
		
		public ImageAdapter() {
			inflater = LayoutInflater.from(LargeImagesActivity.this);
		}
		

		@Override
		public int getCount() {
			return url_array.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0.equals(arg1);
		}
		
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			
			//当atttachToRoot为true 的时候, 就是将layout外层嵌套一层container,返回view
			//当attachToRoot 为FALSE 的时候  返回的view 就是layout对象,root也就失去作用了
			View view = inflater.inflate(R.layout.item_large_images_pager, container, false);
			assert view != null;
			final ImageView image_iv = (ImageView)view.findViewById(R.id.image_item_large_images_iv);
			final ProgressBar progress_pb = (ProgressBar)view.findViewById(R.id.progress_item_large_images_pb);
			Log.i(TAG, "url = " + url_array.get(position));
//			mImageLoader.displayImage(url_array.get(position).pic, image_iv);
			imageLoader.loadImage(url_array.get(position), new ImageLoadingListener() {
				
				@Override
				public void onLoadingStarted(String imageUri, View view) {
					image_iv.setVisibility(View.GONE);
					progress_pb.setVisibility(View.VISIBLE);
				}
				
				@Override
				public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
					String message = null;
					switch (failReason.getType()) {
					case IO_ERROR:
						message = "Input/Output error";
						break;
					case DECODING_ERROR:
						message = "Image can't be decoded";
						break;
					case NETWORK_DENIED:
						message = "Downloads are denied";
						break;
					case OUT_OF_MEMORY:
						message = "Out Of Memory error";
						break;
					case UNKNOWN:
						message = "Unknown error";
						break;

					default:
						break;
					}
					Toast.makeText(LargeImagesActivity.this, message, Toast.LENGTH_SHORT).show();
					progress_pb.setVisibility(View.GONE);
					image_iv.setImageDrawable(getResources().getDrawable(R.drawable.default_top));
				}
				
				@Override
				public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
					progress_pb.setVisibility(View.GONE);
					image_iv.setVisibility(View.VISIBLE);
					image_iv.setImageBitmap(loadedImage);
					
				}
				
				@Override
				public void onLoadingCancelled(String imageUri, View view) {
					Toast.makeText(LargeImagesActivity.this, "图片加载取消", Toast.LENGTH_SHORT).show();
					
				}
			});
//			mImageLoader.displayImage(url_array.get(position).pic, image_iv, new ImageLoadingListener() {
//				
//				@Override
//				public void onLoadingStarted(String imageUri, View view) {
//					image_iv.setVisibility(View.GONE);
//					progress_pb.setVisibility(View.VISIBLE);
//				}
//				
//				@Override
//				public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
//					String message = null;
//					switch (failReason.getType()) {
//					case IO_ERROR:
//						message = "Input/Output error";
//						break;
//					case DECODING_ERROR:
//						message = "Image can't be decoded";
//						break;
//					case NETWORK_DENIED:
//						message = "Downloads are denied";
//						break;
//					case OUT_OF_MEMORY:
//						message = "Out Of Memory error";
//						break;
//					case UNKNOWN:
//						message = "Unknown error";
//						break;
//
//					default:
//						break;
//					}
//					Toast.makeText(LargeImagesActivity.this, message, Toast.LENGTH_SHORT).show();
//					progress_pb.setVisibility(View.GONE);
//				}
//				
//				@Override
//				public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
//					progress_pb.setVisibility(View.GONE);
//					
//				}
//				
//				@Override
//				public void onLoadingCancelled(String imageUri, View view) {
//					
//				}
//			});
			// container view 地址相同  死循环
			container.addView(view,0);
			
			return view;
		}
		
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			Log.i(TAG, "remove position = " + position);
//			(container).removeView(mGestureImageViews[position]);
			container.removeView((View)object);
		}
		
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageSelected(int position) {
		
		index_tv.setText(position + 1 + "/" + url_array.size());
	}

}
