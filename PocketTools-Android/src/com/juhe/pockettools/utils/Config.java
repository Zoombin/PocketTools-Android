package com.juhe.pockettools.utils;

import android.content.SharedPreferences;

import com.juhe.pockettools.HelprApplication;
import com.juhe.pockettools.R;

public class Config {

	private static String USER_INFO = "USER_INFO";
	public static final String BG_CONFIG = "BG_CONFIG";
	
	public static SharedPreferences UserInfoPreferences = HelprApplication.getContext().getSharedPreferences(USER_INFO, 0);

	public static int getBgIndex() {
		return UserInfoPreferences.getInt(BG_CONFIG, 0);
	}
	
	public static void setBgIndex(int index) {
		UserInfoPreferences.edit().putInt(BG_CONFIG, index).commit();
	}
	
	public static int getBgDrawableResId() {
		switch (getBgIndex()) {
		case 0:
			return R.drawable.bg1;
		case 1:
			return R.drawable.bg2;
		case 2:
			return R.drawable.bg3;
		default:
			return R.drawable.bg1;
		}
		
	}
}
