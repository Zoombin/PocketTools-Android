package com.juhe.pockettools.weather;

import java.io.Serializable;
import java.util.List;

public class Hour implements Serializable {

	private static final long serialVersionUID = 1L;

	// 返回说明
	private String reason;

	// 返回码
	private int error_code;

	// 返回结果集
	private List<Result> result;

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public int getError_code() {
		return error_code;
	}

	public void setError_code(int error_code) {
		this.error_code = error_code;
	}

	public List<Result> getResult() {
		return result;
	}

	public void setResult(List<Result> result) {
		this.result = result;
	}

	public class Result {
		// "weatherid": "00",/*天气标识ID*/
		// "weather": "晴", /*天气*/
		// "temp1": "27", /*低温*/
		// "temp2": "31", /*高温*/
		// "sh": "08", /*开始小时*/
		// "eh": "11", /*结束小时*/
		// "date": "20140530", /*日期*/
		// "sfdate": "20140530080000", /*完整开始时间*/
		// "efdate": "20140530110000" /*完整结束时间*/
		private String weatherid;
		private String weather;
		private String temp1;
		private String temp2;
		private String sh;
		private String eh;
		private String date;
		private String sfdate;
		private String efdate;

		public String getWeatherid() {
			return weatherid;
		}

		public void setWeatherid(String weatherid) {
			this.weatherid = weatherid;
		}

		public String getWeather() {
			return weather;
		}

		public void setWeather(String weather) {
			this.weather = weather;
		}

		public String getTemp1() {
			return temp1;
		}

		public void setTemp1(String temp1) {
			this.temp1 = temp1;
		}

		public String getTemp2() {
			return temp2;
		}

		public void setTemp2(String temp2) {
			this.temp2 = temp2;
		}

		public String getSh() {
			return sh;
		}

		public void setSh(String sh) {
			this.sh = sh;
		}

		public String getEh() {
			return eh;
		}

		public void setEh(String eh) {
			this.eh = eh;
		}

		public String getDate() {
			return date;
		}

		public void setDate(String date) {
			this.date = date;
		}

		public String getSfdate() {
			return sfdate;
		}

		public void setSfdate(String sfdate) {
			this.sfdate = sfdate;
		}

		public String getEfdate() {
			return efdate;
		}

		public void setEfdate(String efdate) {
			this.efdate = efdate;
		}

	}
}
