package com.juhe.pockettools.pm;

import java.io.Serializable;
import java.util.List;

public class CityAirEntity implements Serializable {
	// {"resultcode":"200","reason":"SUCCESSED!","error_code":0,
	// "result":[{"citynow":{"city":"苏州","AQI":"65","quality":"良","date":"2015-03-09 15:00"},
	// "lastTwoWeeks":{"1":{"city":"苏州","AQI":"73","quality":"良","date":"2015-02-09"},"2":{"city":"苏州","AQI":"89","quality":"良","date":"2015-02-10"},
	// "3":{"city":"苏州","AQI":"123","quality":"轻度污染","date":"2015-02-11"},"4":{"city":"苏州","AQI":"261","quality":"重度污染","date":"2015-02-12"},
	// "5":{"city":"苏州","AQI":"109","quality":"轻度污染","date":"2015-02-13"},"6":{"city":"苏州","AQI":"119","quality":"轻度污染","date":"2015-02-14"},
	// "7":{"city":"苏州","AQI":"85","quality":"良","date":"2015-02-15"},"8":{"city":"苏州","AQI":"144","quality":"轻度污染","date":"2015-02-16"},
	// "9":{"city":"苏州","AQI":"117","quality":"轻度污染","date":"2015-02-17"},"10":{"city":"苏州","AQI":"71","quality":"良","date":"2015-02-18"},
	// "11":{"city":"苏州","AQI":"35","quality":"优","date":"2015-02-19"},"12":{"city":"苏州","AQI":"35","quality":"优","date":"2015-02-20"},
	// "13":{"city":"苏州","AQI":"82","quality":"良","date":"2015-02-21"},"14":{"city":"苏州","AQI":"93","quality":"良","date":"2015-02-22"},
	// "15":{"city":"苏州","AQI":"92","quality":"良","date":"2015-02-23"},"16":{"city":"苏州","AQI":"84","quality":"良","date":"2015-02-24"},
	// "17":{"city":"苏州","AQI":"54","quality":"良","date":"2015-02-25"},"18":{"city":"苏州","AQI":"58","quality":"良","date":"2015-02-26"},
	// "19":{"city":"苏州","AQI":"34","quality":"优","date":"2015-02-27"},"20":{"city":"苏州","AQI":"69","quality":"良","date":"2015-02-28"},
	// "21":{"city":"苏州","AQI":"126","quality":"轻度污染","date":"2015-03-01"},"22":{"city":"苏州","AQI":"128","quality":"轻度污染","date":"2015-03-02"},
	// "23":{"city":"苏州","AQI":"84","quality":"良","date":"2015-03-03"},"24":{"city":"苏州","AQI":"50","quality":"优","date":"2015-03-04"},
	// "25":{"city":"苏州","AQI":"56","quality":"良","date":"2015-03-05"},"26":{"city":"苏州","AQI":"61","quality":"良","date":"2015-03-06"},
	// "27":{"city":"苏州","AQI":"109","quality":"轻度污染","date":"2015-03-07"},"28":{"city":"苏州","AQI":"67","quality":"良","date":"2015-03-08"}},
	// "lastMoniData":{"1":{"city":"上方山","AQI":"72","America_AQI":"124","quality":"良","PM2.5Hour":"52 ","PM2.5Day":"52 ","PM10Hour":"— ","lat":"31.247222","lon":"120.561389"},
	// "2":{"city":"南门","AQI":"63","America_AQI":"110","quality":"良","PM2.5Hour":"45 ","PM2.5Day":"45 ","PM10Hour":"46 ","lat":"31.286389","lon":"120.6275"},
	// "3":{"city":"彩香","AQI":"67","America_AQI":"116","quality":"良","PM2.5Hour":"48 ","PM2.5Day":"48 ","PM10Hour":"50 ","lat":"31.301944","lon":"120.590833"},
	// "4":{"city":"吴中区","AQI":"60","America_AQI":"106","quality":"良","PM2.5Hour":"43 ","PM2.5Day":"43 ","PM10Hour":"51 ","lat":"31.270278","lon":"120.612778"},
	// "5":{"city":"苏州新区","AQI":"75","America_AQI":"130","quality":"良","PM2.5Hour":"55 ","PM2.5Day":"55 ","PM10Hour":"— ","lat":"31.299444","lon":"120.543333"},
	// "6":{"city":"苏州工业园区","AQI":"57","America_AQI":"100","quality":"良","PM2.5Hour":"40 ","PM2.5Day":"40 ","PM10Hour":"46 ","lat":"31.309722","lon":"120.669167"},
	// "7":{"city":"相城区","AQI":"74","America_AQI":"128","quality":"良","PM2.5Hour":"54 ","PM2.5Day":"54 ","PM10Hour":"— ","lat":"31.370833","lon":"120.640556"},
	// "8":{"city":"轧钢厂","AQI":"56","America_AQI":"98","quality":"良","PM2.5Hour":"39 ","PM2.5Day":"39 ","PM10Hour":"62 ","lat":"31.326389","lon":"120.595556"}}}]}

	private static final long serialVersionUID = 1L;

	// 返回说明
	private String reason;

	// 返回码
	private int error_code;

	// 返回结果集
	private List<Result> result;

	public class Result {
		private PM citynow;

		public PM getCitynow() {
			return citynow;
		}

		public void setCitynow(PM citynow) {
			this.citynow = citynow;
		}

	}

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

	public static class PM {
		// {"city":"苏州","AQI":"65","quality":"良","date":"2015-03-09 15:00"}
		private String city;
		private String AQI;
		private String quality;
		private String date;

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

		public String getDate() {
			return date;
		}

		public void setDate(String date) {
			this.date = date;
		}

	}

	public static class Moni {
		// "city":"轧钢厂","AQI":"56","America_AQI":"98","quality":"良","PM2.5Hour":"39 ","PM2.5Day":"39 ","PM10Hour":"62 ","lat":"31.326389","lon":"120.595556"
		private String city;
		private String AQI;
		private String America_AQI;
		private String quality;
		private String PM25Hour;
		private String PM25Day;
		private String PM10Hour;
		private String lat;
		private String lon;

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

		public String getAmerica_AQI() {
			return America_AQI;
		}

		public void setAmerica_AQI(String america_AQI) {
			America_AQI = america_AQI;
		}

		public String getQuality() {
			return quality;
		}

		public void setQuality(String quality) {
			this.quality = quality;
		}

		public String getPM25Hour() {
			return PM25Hour;
		}

		public void setPM25Hour(String pM25Hour) {
			PM25Hour = pM25Hour;
		}

		public String getPM25Day() {
			return PM25Day;
		}

		public void setPM25Day(String pM25Day) {
			PM25Day = pM25Day;
		}

		public String getPM10Hour() {
			return PM10Hour;
		}

		public void setPM10Hour(String pM10Hour) {
			PM10Hour = pM10Hour;
		}

		public String getLat() {
			return lat;
		}

		public void setLat(String lat) {
			this.lat = lat;
		}

		public String getLon() {
			return lon;
		}

		public void setLon(String lon) {
			this.lon = lon;
		}

	}
}
