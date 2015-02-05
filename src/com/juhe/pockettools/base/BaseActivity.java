package com.juhe.pockettools.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.util.Log;

import com.juhe.pockettools.adaptive.UIAdapter;
import com.juhe.pockettools.tool.ActivityManager;
import com.nostra13.universalimageloader.core.ImageLoader;

public abstract class BaseActivity extends FragmentActivity {
	protected ActivityManager activityManager;
	protected ImageLoader imageLoader = ImageLoader.getInstance();

	protected BaseActivity self;
	protected UIAdapter uiAdapter;
	protected int width, height;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		self = this;
		activityManager = ActivityManager.getScreenManager();
		activityManager.pushActivity(this);
		
		uiAdapter = UIAdapter.getInstance(self);

		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		width = dm.widthPixels;
		height = dm.heightPixels;
	}

	@Override
	protected void onDestroy() {
		imageLoader.clearMemoryCache();
		super.onDestroy();
	}

	public UIAdapter getUiAdapter() {
		return uiAdapter;
	}

	public void setUiAdapter(UIAdapter uiAdapter) {
		this.uiAdapter = uiAdapter;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public void logActivityName(Activity activity) {
		
		Log.d("activity name", "this activity name = " + this.getClass().getName());
	}

}
