package com.juhe.pockettools.home;

import java.util.Iterator;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;

import com.juhe.pockettools.HelprApplication;
import com.juhe.pockettools.utils.HelprCommUtil;
//import com.helpr.application.HelprApplication;

/**
 * 全屏activity
 * @author daiye
 *
 */
public class FullscreenActivity extends FragmentActivity {
	private static final String TAG = "FullscreenActivity";

	public boolean isAppRunning() {
		ActivityManager activitymanager = (ActivityManager) getApplicationContext()
				.getSystemService(Context.ACTIVITY_SERVICE);
		String packagename = getApplicationContext().getPackageName();
		List<RunningAppProcessInfo> appList  = activitymanager.getRunningAppProcesses();
		if (appList  == null) {
			return false;
		}
		Iterator<RunningAppProcessInfo> iterator = appList .iterator();
		ActivityManager.RunningAppProcessInfo runningappprocessinfo;
		while (iterator.hasNext()) {
			runningappprocessinfo = (ActivityManager.RunningAppProcessInfo) iterator.next();
			if ((runningappprocessinfo.processName.equals(packagename))
				&& (runningappprocessinfo.importance != RunningAppProcessInfo.IMPORTANCE_FOREGROUND)) { 
				return true;
			}
		}
		return false;
	}

	@SuppressLint("NewApi")
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		if (HelprCommUtil.hasNavigationBar(this)) {
			getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
		}
	}

	protected void onDestroy() {
		super.onDestroy();
	}

	protected void onResume() {
		super.onResume();
		if (!HelprApplication.isAppForeground) {
			HelprApplication.isAppForeground = true;
			Log.v("FullscreenActivity", "FullscreenActivity Helpr App 进入前台");
			sendBroadcast(new Intent("ApplicationWillEnterForegroundNoti"));
		}
	}

	protected void onStart() {
		super.onStart();
	}

	protected void onStop() {
		if (!isAppRunning()) {
			HelprApplication.isAppForeground = false;
			Log.v("FullscreenActivity", "FullscreenActivity Helpr App 进入后台");
			sendBroadcast(new Intent("ApplicationDidEnterBackgroundNoti"));
		}
		super.onStop();
	}
}
