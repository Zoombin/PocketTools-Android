package com.juhe.pockettools.pm;

import java.io.Serializable;
import java.util.List;

public class PMEntity implements Serializable {
	// {"resultcode":"200","reason":"SUCCESSED!","result":
	// [{"city":"苏州","PM2.5":"60","AQI":"81","quality":"良","PM10":"59","CO":"0.7","NO2":"39","O3":"65","SO2":"15","time":"2015-03-09 15:46:58"}],"error_code":0}
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

	public class Result implements Serializable {

		private static final long serialVersionUID = 1L;
		// {"city":"苏州","PM2.5":"60","AQI":"81","quality":"良","PM10":"59","CO":"0.7","NO2":"39","O3":"65","SO2":"15","time":"2015-03-09 15:46:58"}
		private String city;
		// private String PM2.5;
		private String AQI;
		private String quality;
		private String PM10;
		private String CO;
		private String NO2;
		private String O3;
		private String SO2;
		private String time;

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getAQI() {
			return AQI;
		}

		public void setAQI(String aQI) {
			AQI = aQI;
		}

		public String getQuality() {
			return quality;
		}

		public void setQuality(String quality) {
			this.quality = quality;
		}

		public String getPM10() {
			return PM10;
		}

		public void setPM10(String pM10) {
			PM10 = pM10;
		}

		public String getCO() {
			return CO;
		}

		public void setCO(String cO) {
			CO = cO;
		}

		public String getNO2() {
			return NO2;
		}

		public void setNO2(String nO2) {
			NO2 = nO2;
		}

		public String getO3() {
			return O3;
		}

		public void setO3(String o3) {
			O3 = o3;
		}

		public String getSO2() {
			return SO2;
		}

		public void setSO2(String sO2) {
			SO2 = sO2;
		}

		public String getTime() {
			return time;
		}

		public void setTime(String time) {
			this.time = time;
		}

	}
}
