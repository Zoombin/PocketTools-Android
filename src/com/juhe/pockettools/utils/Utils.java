package com.juhe.pockettools.utils;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import org.apache.http.conn.util.InetAddressUtils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.telephony.TelephonyManager;

/**
 * 工具类
 * 
 * @author daiye
 * 
 */
public class Utils {

	/**
	 * 获取系统版本号
	 * 
	 * @param context
	 */
	public static String getDeviceOSVersion() {

		// 系统版本号
		String deviceOSVersion = android.os.Build.VERSION.RELEASE;

		return deviceOSVersion;
	}

	
	/**
	 * 获取版本名字
	 * 
	 * @param context
	 * @return version name
	 */
	public static int getClientVersionCode(Context context) {

		PackageManager manager = context.getPackageManager();

		try {
			PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);

			int appVersionCode = info.versionCode; // 版本名

			return appVersionCode; // 版本号

		} catch (NameNotFoundException e) {

			e.printStackTrace();
		}

		return 0;
	}
	
	/**
	 * 获取Android设备唯一编号
	 * 
	 * @param context
	 * @return deviceId
	 */
	public static String getDeviceId(Context context) {
		// 设备唯一序列号(IMEI)
		TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		String deviceId = tm.getDeviceId();
		return deviceId;
	}

	/**
	 * 获取版本名字
	 * 
	 * @param context
	 * @return version name
	 */
	public static String getClientVersionName(Context context) {

		PackageManager manager = context.getPackageManager();

		try {
			PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);

			String appVersion = info.versionName; // 版本名

			return appVersion; // 版本号

		} catch (NameNotFoundException e) {

			e.printStackTrace();
		}

		return "魔力网";
	}

	/**
	 * 判断网络是否连接
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isNetworkConnected(Context context) {
		if (context != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
			if (mNetworkInfo != null) {
				return mNetworkInfo.isAvailable();
			}
		}
		return false;
	}

	/**
	 * 网络IP地址获得
	 * 
	 * @return
	 */
	public static String getLocalIpAddress() {
		String ipaddress = "0.0.0.0";
		try {
			Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
			// 遍历所用的网络接口
			while (en.hasMoreElements()) {
				NetworkInterface nif = en.nextElement();// 得到每一个网络接口绑定的所有ip
				Enumeration<InetAddress> inet = nif.getInetAddresses();
				// 遍历每一个接口绑定的所有ip
				while (inet.hasMoreElements()) {
					InetAddress ip = inet.nextElement();
					if (!ip.isLoopbackAddress() && InetAddressUtils.isIPv4Address(ip.getHostAddress())) {
						return ip.getHostAddress().toString();
					}
				}
			}
		} catch (SocketException e) {
			return ipaddress;
		}
		return ipaddress;
	}

	/**
	 * 获取当前时间
	 * 
	 * @return
	 */
	public static String getNowTime() {
		SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String now = "";
		try {
			now = URLEncoder.encode(sDateFormat.format(new Date()), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return now;
	}

	/**
	 * 获取格式化时间
	 * 
	 * @param unix时间戳
	 * @return 格式化时间
	 */
	public static String getDatebyTimestamp(String timestamp) {

		Date date = new Date(Long.valueOf(timestamp));

		SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String now = "";

		try {
			now = URLEncoder.encode(sDateFormat.format(date), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return now;
	}

	/**
	 * 获取当前Unix时间
	 * 
	 * @return
	 */
	public static long getUnixTime() {
		return System.currentTimeMillis() / 1000;
	}

	/*
	 * 创建imageloader缓存文件夹
	 */
	public static File getCacheFile(Context context) {
		File cacheFile = context.getCacheDir();
		if (sdacrdExist()) {
			String dirPath = Environment.getExternalStorageDirectory() + "/Moli/";
			;
			if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
				dirPath = String.format("/%s/%s/longjx/%s/", "data/data", context.getPackageName(), QMD5.encryptMD5("cache"));
			}
			cacheFile = new File(dirPath);
			if (!cacheFile.exists())
				FileUtils.getInstance().createDirectory(dirPath);
		}
		return cacheFile;
	}

	/**
	 * 判断sdcard是否存在
	 * 
	 * @return true存在可读写 false不存在不可读写
	 */
	public static boolean sdacrdExist() {
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			// Log.d(TAG, "sdcard is exist,read and write ok");
			return true;
		}
		// Log.d(TAG, "sdcard is not exist or can not read/write");
		return false;
	}
}
