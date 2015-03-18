package com.juhe.pockettools.weather;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import android.annotation.SuppressLint;
import android.text.format.DateFormat;

public class WeatherTools {
	public static String getWeatherIcon(String text) {
		String str = "";
		if (text.contains("雪")) {
			str = "snow";
		} else if (text.contains("雾")) {
			str = "fog";
		} else if (text.contains("霾")) {
			str = "hazy";
		} else if (text.contains("雷")) {
			str = "tstorms";
		} else if (text.contains("雨")) {
			str = "rain";
		} else if (text.contains("云")) {
			str = "cloudy";
		} else if (text.contains("阴")) {
			str = "mostlycloudy";
		} else if (text.contains("晴")) {
			str = "clear";
		}
		return String.format("weather/%s.png", new Object[] { str });
	}
	
	public static String getTemp(String temp) {
		return temp + "°";
	}

	@SuppressLint({ "SimpleDateFormat" })
	public static Date getDate(String hour, String format) {
		if ((hour != null) && (format != null)) {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			try {
				Date date = sdf.parse(hour);
				return date;
			} catch (Exception e) {
				return null;
			}
		    
		}
		return null;
	}

	public static String getDateStr(String hour) {
		if (hour != null && hour.length() > 0) {
			Date date = getDate(hour, "yyyyMMddHHmmss");
			if (date != null) {
				String str1 = getDateForStr(date, "yyyyMMdd");
				String str2 = DateFormat.format("yyyyMMdd",
						new GregorianCalendar().getTime()).toString();
				String str3 = new SimpleDateFormat("ah点", Locale.CHINESE)
						.format(date);
				String str4 = new SimpleDateFormat("M.dd", Locale.CHINESE)
						.format(date);
				if (str1.equalsIgnoreCase(str2)) {
					return str3;
				}
				return str3 + "\n" + "(" + str4 + ")";
			}
		}
		return "";
	}

	public static String getDateForStr(Date date, String format) {
		if (date == null) {
			return "";
		}
		try {
			String str = new SimpleDateFormat(format, Locale.getDefault())
					.format(date);
			return str;
		} catch (Exception localException) {
		}
		return "";
	}
	
	public static String a(String week, String weathericon) {
		GregorianCalendar gregoriancalendar = new GregorianCalendar();
		String str1 = DateFormat.format("yyyyMMdd",
				gregoriancalendar.getTime()).toString();
		gregoriancalendar.add(5, 1);
		String str2 = DateFormat.format("yyyyMMdd",
				gregoriancalendar.getTime()).toString();
		gregoriancalendar.add(5, 1);
		String str3 = DateFormat.format("yyyyMMdd",
				gregoriancalendar.getTime()).toString();
		if (weathericon.equalsIgnoreCase(str1)) {
			Object[] arrayOfObject3 = new Object[1];
			arrayOfObject3[0] = week;
			return String.format("今天 (%s)", arrayOfObject3);
		}
		if (weathericon.equalsIgnoreCase(str2)) {
			Object[] arrayOfObject2 = new Object[1];
			arrayOfObject2[0] = week;
			return String.format("明天 (%s)", arrayOfObject2);
		}
		if (weathericon.equalsIgnoreCase(str3)) {
			Object[] arrayOfObject1 = new Object[1];
			arrayOfObject1[0] = week;
			return String.format("后天 (%s)", arrayOfObject1);
		}
		return week;
	}
}
