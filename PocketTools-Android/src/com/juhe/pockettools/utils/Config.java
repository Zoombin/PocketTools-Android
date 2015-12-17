package com.juhe.pockettools.utils;

import java.io.File;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.juhe.pockettools.HelprApplication;
import com.zoombin.koudai.R;

public class Config {

	private static String USER_INFO = "USER_INFO";
	public static final String BG_CONFIG = "BG_CONFIG";

	public static SharedPreferences UserInfoPreferences = HelprApplication
			.getContext().getSharedPreferences(USER_INFO, 0);

	public static int getBgIndex() {
		return UserInfoPreferences.getInt(BG_CONFIG, 0);
	}

	public static void setBgIndex(int index) {
		UserInfoPreferences.edit().putInt(BG_CONFIG, index).commit();
	}

	// public static int getBgDrawableResId() {
	// switch (getBgIndex()) {
	// case 0:
	// return R.drawable.bg1;
	// case 1:
	// return R.drawable.bg2;
	// case 2:
	// return R.drawable.bg3;
	// default:
	// return R.drawable.bg1;
	// }
	// }

//	public static Drawable getBgDrawable() {
//
//		switch (getBgIndex()) {
//		case 0:
//			return HelprApplication.getContext().getResources()
//					.getDrawable(R.drawable.bg1);
//		case 1:
//			return HelprApplication.getContext().getResources()
//					.getDrawable(R.drawable.bg2);
//		case 2:
//			return HelprApplication.getContext().getResources()
//					.getDrawable(R.drawable.bg3);
//		case 3:
//			File f = new File("/sdcard/myFolder/temp_cropped.jpg");
//			if (f.exists()) {
//				Bitmap bit = BitmapFactory
//						.decodeFile("/sdcard/myFolder/temp_cropped.jpg");
//				Drawable dw = new BitmapDrawable(bit);
//				return dw;
//			} else {
//				setBgIndex(0);
//				return getBgDrawable();
//			}
//
//		default:
//			return HelprApplication.getContext().getResources()
//					.getDrawable(R.drawable.bg1);
//		}
//	}

	public static Integer getColor() {
		switch (getBgIndex()) {
		case 0:
			return R.color.bg_color_1;
		case 1:
			return R.color.bg_color_4;
		case 2:
			return R.color.bg_color_6;
		default:
			return R.color.bg_color_1;
		}
	}

	// 首页天气数据
	private static String WEATHER_INFO = "WEATHER_INFO";

	public static String getWeatherInfo() {
		return UserInfoPreferences.getString(WEATHER_INFO, "");
	}

	public static void setWeatherInfo(String weather) {
		UserInfoPreferences.edit().putString(WEATHER_INFO, weather).commit();
	}

	// public static void setWeatherInfo(Weather entity) {
	// SharedPreferences preferences = HelprApplication.getContext()
	// .getSharedPreferences("base64", Context.MODE_PRIVATE);
	// // 创建字节输出流
	// ByteArrayOutputStream baos = new ByteArrayOutputStream();
	// try {
	// // 创建对象输出流，并封装字节流
	// ObjectOutputStream oos = new ObjectOutputStream(baos);
	// // 将对象写入字节流
	// oos.writeObject(entity);
	// // 将字节流编码成base64的字符窜
	// String oAuth_Base64 = new String(Base64.encodeBase64(baos
	// .toByteArray()));
	// Editor editor = preferences.edit();
	// editor.putString(WEATHER_INFO, oAuth_Base64);
	//
	// editor.commit();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }
	//
	// public static Weather getWeatherInfo() {
	// Weather entity = null;
	// SharedPreferences preferences = HelprApplication.getContext()
	// .getSharedPreferences("base64", Context.MODE_PRIVATE);
	// String productBase64 = preferences.getString(WEATHER_INFO, "");
	//
	// // 读取字节
	// byte[] base64 = Base64.decodeBase64(productBase64.getBytes());
	//
	// // 封装到字节流
	// ByteArrayInputStream bais = new ByteArrayInputStream(base64);
	// try {
	// // 再次封装
	// ObjectInputStream bis = new ObjectInputStream(bais);
	// try {
	// // 读取对象
	// entity = (Weather) bis.readObject();
	// } catch (ClassNotFoundException e) {
	// e.printStackTrace();
	// }
	// } catch (StreamCorruptedException e) {
	// e.printStackTrace();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// return entity;
	// }
	//
	// 三小时天气数据
	private static String HOUR_INFO = "HOUR_INFO";

	public static String getHourInfo() {
		return UserInfoPreferences.getString(HOUR_INFO, "");
	}

	public static void setHourInfo(String hour) {
		UserInfoPreferences.edit().putString(HOUR_INFO, hour).commit();
	}

	// public static void setHourInfo(Hour entity) {
	// SharedPreferences preferences = HelprApplication.getContext()
	// .getSharedPreferences("base64", Context.MODE_PRIVATE);
	// // 创建字节输出流
	// ByteArrayOutputStream baos = new ByteArrayOutputStream();
	// try {
	// // 创建对象输出流，并封装字节流
	// ObjectOutputStream oos = new ObjectOutputStream(baos);
	// // 将对象写入字节流
	// oos.writeObject(entity);
	// // 将字节流编码成base64的字符窜
	// String oAuth_Base64 = new String(Base64.encodeBase64(baos
	// .toByteArray()));
	// Editor editor = preferences.edit();
	// editor.putString(HOUR_INFO, oAuth_Base64);
	//
	// editor.commit();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }
	//
	// public static Hour getHourInfo() {
	// Hour entity = null;
	// SharedPreferences preferences = HelprApplication.getContext()
	// .getSharedPreferences("base64", Context.MODE_PRIVATE);
	// String productBase64 = preferences.getString(HOUR_INFO, "");
	//
	// // 读取字节
	// byte[] base64 = Base64.decodeBase64(productBase64.getBytes());
	//
	// // 封装到字节流
	// ByteArrayInputStream bais = new ByteArrayInputStream(base64);
	// try {
	// // 再次封装
	// ObjectInputStream bis = new ObjectInputStream(bais);
	// try {
	// // 读取对象
	// entity = (Hour) bis.readObject();
	// } catch (ClassNotFoundException e) {
	// e.printStackTrace();
	// }
	// } catch (StreamCorruptedException e) {
	// e.printStackTrace();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// return entity;
	// }
	//
	// 三小时天气数据
	private static String PM_INFO = "PM_INFO";

	public static String getPMInfo() {
		return UserInfoPreferences.getString(PM_INFO, "");
	}

	public static void setPMInfo(String pm) {
		UserInfoPreferences.edit().putString(PM_INFO, pm).commit();
	}

	// public static void setPMInfo(PMEntity entity) {
	// SharedPreferences preferences = HelprApplication.getContext()
	// .getSharedPreferences("base64", Context.MODE_PRIVATE);
	// // 创建字节输出流
	// ByteArrayOutputStream baos = new ByteArrayOutputStream();
	// try {
	// // 创建对象输出流，并封装字节流
	// ObjectOutputStream oos = new ObjectOutputStream(baos);
	// // 将对象写入字节流
	// oos.writeObject(entity);
	// // 将字节流编码成base64的字符窜
	// String oAuth_Base64 = new String(Base64.encodeBase64(baos
	// .toByteArray()));
	// Editor editor = preferences.edit();
	// editor.putString(PM_INFO, oAuth_Base64);
	//
	// editor.commit();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }
	//
	// public static PMEntity getPMInfo() {
	// PMEntity entity = null;
	// SharedPreferences preferences = HelprApplication.getContext()
	// .getSharedPreferences("base64", Context.MODE_PRIVATE);
	// String productBase64 = preferences.getString(PM_INFO, "");
	//
	// // 读取字节
	// byte[] base64 = Base64.decodeBase64(productBase64.getBytes());
	//
	// // 封装到字节流
	// ByteArrayInputStream bais = new ByteArrayInputStream(base64);
	// try {
	// // 再次封装
	// ObjectInputStream bis = new ObjectInputStream(bais);
	// try {
	// // 读取对象
	// entity = (PMEntity) bis.readObject();
	// } catch (ClassNotFoundException e) {
	// e.printStackTrace();
	// }
	// } catch (StreamCorruptedException e) {
	// e.printStackTrace();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// return entity;
	// }
}
