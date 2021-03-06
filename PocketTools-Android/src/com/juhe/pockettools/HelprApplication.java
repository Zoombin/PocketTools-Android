package com.juhe.pockettools;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;

import com.baidu.location.GeofenceClient;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
//import com.google.android.gms.analytics.GoogleAnalytics;
//import com.google.android.gms.analytics.Tracker;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.zoombin.koudai.R;

/**
 * 应用入口
 * 
 * @author daiye
 * 
 */
public class HelprApplication extends Application {
	public static final String a = "ApplicationWillEnterForegroundNoti";
	public static final String b = "ApplicationDidEnterBackgroundNoti";
	public static final String c = "6JGHNKSTMS27QRV7NK8M";
	public static boolean d = false;
	public static boolean e = false;
	public static boolean f = false;
	public static boolean g = false;
	public static int memoryclass = 0;
	public static boolean isAppForeground = true;
	public static boolean k = true;
	private static Context context;
	public LocationClient mLocationClient;
	public GeofenceClient mGeofenceClient;
	public static HelprApplication self;

	public static HelprApplication getInstance() {
		return self;
	}

	public static Context getContext() {
		return context;
	}

	/**
	 * Enum used to identify the tracker that needs to be used for tracking.
	 *
	 * A single tracker is usually enough for most purposes. In case you do need
	 * multiple trackers, storing them all in Application object helps ensure
	 * that they are created only once per application instance.
	 */
	// public enum TrackerName {
	// APP_TRACKER, // Tracker used only in this app.
	// GLOBAL_TRACKER, // Tracker used by all the apps from a company. eg:
	// // roll-up tracking.
	// ECOMMERCE_TRACKER, // Tracker used by all ecommerce transactions from a
	// // company.
	// }
	//
	//
	//
	// HashMap<TrackerName, Tracker> mTrackers = new HashMap<TrackerName,
	// Tracker>();
	//
	// synchronized Tracker getTracker(TrackerName trackerId) {
	// if (!mTrackers.containsKey(trackerId)) {
	//
	// GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
	// Tracker t = (trackerId == TrackerName.APP_TRACKER) ? analytics
	// .newTracker(PROPERTY_ID)
	// : (trackerId == TrackerName.GLOBAL_TRACKER) ? analytics
	// .newTracker(R.xml.global_tracker) : analytics
	// .newTracker(R.xml.ecommerce_tracker);
	// mTrackers.put(trackerId, t);
	//
	// }
	// return mTrackers.get(trackerId);
	// }
//	private static final String PROPERTY_ID = "UA-61207131-1";
//	private Tracker mTracker;
//
//	public synchronized Tracker getTracker() {
//		if (mTracker == null) {
//			GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
//			mTracker = analytics.newTracker(PROPERTY_ID);
//		}
//		return mTracker;
//	}

	private void initImageLoader() {
		DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
				// .showImageOnLoading(R.drawable.ic_loading)
				.showImageForEmptyUri(R.drawable.ic_empty)
				// empty URI时显示的图片
				.showImageOnFail(R.drawable.ic_error)
				// 不是图片文件 显示图片
				.cacheInMemory(true).bitmapConfig(Bitmap.Config.RGB_565)
				// 图片压缩质量
				.cacheOnDisc(true)
				.imageScaleType(ImageScaleType.EXACTLY_STRETCHED).build();

		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				getApplicationContext())
				.defaultDisplayImageOptions(defaultOptions)
				.discCacheFileCount(10000).threadPoolSize(5).build();

		ImageLoader.getInstance().init(config);
	}

	public boolean isSmallDisplay(Activity activity) {
		DisplayMetrics displaymetrics = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay()
				.getMetrics(displaymetrics);
		return displaymetrics.heightPixels + displaymetrics.widthPixels < 1280;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		context = getApplicationContext();
		self = this;
		// 百度地图
		SDKInitializer.initialize(getApplicationContext());
		// 聚合api
		com.thinkland.sdk.android.SDKInitializer
				.initialize(getApplicationContext());
		initImageLoader();

		mLocationClient = new LocationClient(this.getApplicationContext());
		LocationClientOption option = new LocationClientOption();
		option.setIsNeedAddress(true);
		mLocationClient.setLocOption(option);

		// try {
		// context = getApplicationContext();
		// ActivityManager activitymanager = (ActivityManager)
		// getApplicationContext()
		// .getSystemService(Context.ACTIVITY_SERVICE);
		// memoryclass = activitymanager.getMemoryClass();
		// boolean bool1;
		// if (memoryclass <= 32) {
		// bool1 = true;
		// d = bool1;
		// if (memoryclass < 64) {
		// break label80;
		// }
		// }
		// label80: for (boolean bool2 = true;; bool2 = false) {
		// e = bool2;
		// System.setProperty("java.util.Arrays.useLegacyMergeSort",
		// "true");
		// a();
		// return;
		// bool1 = false;
		// break;
		// }
		// return;
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
	}

	// private File cacheFile = null;
	// private static Context mContext;
	// private static HelprApplication self;
	//
	// private boolean isAppLogin = false;
	//
	// public boolean isLogin() {
	// return isAppLogin;
	// }
	//
	// public void setLogin(boolean isLogin) {
	// this.isAppLogin = isLogin;
	// }
	//
	// @Override
	// public void onCreate() {
	// super.onCreate();
	// SDKInitializer.initialize(getApplicationContext());
	//
	// self = this;
	// mContext = getApplicationContext();
	// UIAdapter.setSize(480, 800);
	//
	// cacheFile = Utils.getCacheFile(this);
	//
	// initImageLoader();
	// }
	//
	// public static HelprApplication getInstance() {
	// return self;
	// }
	//
	// public static Context getContext() {
	// return mContext;
	// }
	//

	// @Override
	// public void onTerminate() {
	// this.isAppLogin = false;
	// super.onTerminate();
	// }
}
