package com.juhe.pockettools;

import java.io.File;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;

import com.juhe.pockettools.adaptive.UIAdapter;
import com.juhe.pockettools.utils.Utils;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.thinkland.sdk.android.SDKInitializer;

/**
 * 应用入口
 * @author daiye
 *
 */
public class JuheApplication extends Application {
	private File cacheFile = null;
	private static Context mContext;
	private static JuheApplication self;
	
	private boolean isAppLogin = false;
	
	public boolean isLogin() {
		return isAppLogin;
	}

	public void setLogin(boolean isLogin) {
		this.isAppLogin = isLogin;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		SDKInitializer.initialize(getApplicationContext());
		
		self = this;
		mContext = getApplicationContext();
		UIAdapter.setSize(480, 800);

		cacheFile = Utils.getCacheFile(this);
		
		initImageLoader();
	}

	public static JuheApplication getInstance() {
		return self;
	}
	
	public static Context getContext() {
		return mContext;
	}

	private void initImageLoader() {
		DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
//			.showImageOnLoading(R.drawable.ic_loading)
//		    .showImageForEmptyUri(R.drawable.ic_error)  // empty URI时显示的图片  
//		    .showImageOnFail(R.drawable.ic_error)      // 不是图片文件 显示图片  
			.cacheInMemory(true)
			.bitmapConfig(Bitmap.Config.RGB_565)  // 图片压缩质量
			.cacheOnDisc(true)			
			.imageScaleType(ImageScaleType.EXACTLY_STRETCHED)
			.build();

		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				getApplicationContext())
				.defaultDisplayImageOptions(defaultOptions)
				.discCache(new UnlimitedDiscCache(cacheFile))
				.discCacheFileCount(10000).threadPoolSize(5)
				.build();
		
		ImageLoader.getInstance().init(config);
	}
	
	@Override
	public void onTerminate() {
		this.isAppLogin = false;
		super.onTerminate();
	}
}
