package com.juhe.pockettools.weather;

import java.io.Serializable;
import java.util.List;

public class Weather implements Serializable {
	// {"resultcode":"200","reason":"successed!","result":
	// {"sk":{"temp":"8","wind_direction":"西风","wind_strength":"2级","humidity":"96%","time":"19:00"},
	// "today":{"temperature":"5℃~10℃","weather":"小雨转阴","weather_id":{"fa":"07","fb":"02"},"wind":"东南风3-4 级","week":"星期日","city":"苏州","date_y":"2015年03月08日",
	// "dressing_index":"较冷","dressing_advice":"建议着厚外套加毛衣等服装。年老体弱者宜着大衣、呢外套加羊毛衫。","uv_index":"最弱","comfort_index":"","wash_index":"较不宜","travel_index":"",
	// "exercise_index":"较适宜","drying_index":""},"future":{"day_20150308":{"temperature":"5℃~10℃","weather":"小雨转阴","weather_id":{"fa":"07","fb":"02"},
	// "wind":"东南风3-4 级","week":"星期日","date":"20150308"},"day_20150309":{"temperature":"2℃~11℃","weather":"阴转多云","weather_id":{"fa":"02","fb":"01"},
	// "wind":"北风4-5 级","week":"星期一","date":"20150309"},"day_20150310":{"temperature":"1℃~8℃","weather":"晴转多云","weather_id":{"fa":"00","fb":"01"},
	// "wind":"北风微风","week":"星期二","date":"20150310"},"day_20150311":{"temperature":"3℃~10℃","weather":"多云","weather_id":{"fa":"01","fb":"01"},
	// "wind":"西风微风","week":"星期三","date":"20150311"},"day_20150312":{"temperature":"7℃~14℃","weather":"晴转多云","weather_id":{"fa":"00","fb":"01"},
	// "wind":"西南风微风","week":"星期四","date":"20150312"},"day_20150313":{"temperature":"10℃~18℃","weather":"多云","weather_id":{"fa":"01","fb":"01"},
	// "wind":"南风微风","week":"星期五","date":"20150313"},"day_20150314":{"temperature":"10℃~19℃","weather":"多云转小雨","weather_id":{"fa":"01","fb":"07"},
	// "wind":"东南风微风","week":"星期六","date":"20150314"}}},"error_code":0}
	private static final long serialVersionUID = 1L;

	// 返回说明
	private String reason;

	// 返回码
	private int error_code;

	private String resultcode;

	// 返回结果集
	private Result result;

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

	public String getResultcode() {
		return resultcode;
	}

	public void setResultcode(String resultcode) {
		this.resultcode = resultcode;
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	public class Result {
		private SK sk;
		private Today today;

		public SK getSk() {
			return sk;
		}

		public void setSk(SK sk) {
			this.sk = sk;
		}

		public Today getToday() {
			return today;
		}

		public void setToday(Today today) {
			this.today = today;
		}
	}

	public class SK {
		// "sk": { /*当前实况天气*/
		// "temp": "21", /*当前温度*/
		// "wind_direction": "西风", /*当前风向*/
		// "wind_strength": "2级", /*当前风力*/
		// "humidity": "4%", /*当前湿度*/
		// "time": "14:25" /*更新时间*/
		private String temp;
		private String wind_direction;
		private String wind_strength;
		private String humidity;
		private String time;

		public String getTemp() {
			return temp;
		}

		public void setTemp(String temp) {
			this.temp = temp;
		}

		public String getWind_direction() {
			return wind_direction;
		}

		public void setWind_direction(String wind_direction) {
			this.wind_direction = wind_direction;
		}

		public String getWind_strength() {
			return wind_strength;
		}

		public void setWind_strength(String wind_strength) {
			this.wind_strength = wind_strength;
		}

		public String getHumidity() {
			return humidity;
		}

		public void setHumidity(String humidity) {
			this.humidity = humidity;
		}

		public String getTime() {
			return time;
		}

		public void setTime(String time) {
			this.time = time;
		}

	}

	public class Today {
		// "today": {
		// "city": "天津",
		// "date_y": "2014年03月21日",
		// "week": "星期五",
		// "temperature": "8℃~20℃", /*今日温度*/
		// "weather": "晴转霾", /*今日天气*/
		// "weather_id": { /*天气唯一标识*/
		// "fa": "00", /*天气标识00：晴*/
		// "fb": "53" /*天气标识53：霾 如果fa不等于fb，说明是组合天气*/
		// },
		// "wind": "西南风微风",
		// "dressing_index": "较冷", /*穿衣指数*/
		// "dressing_advice": "建议着大衣、呢外套加毛衣、卫衣等服装。", /*穿衣建议*/
		// "uv_index": "中等", /*紫外线强度*/
		// "comfort_index": "",/*舒适度指数*/
		// "wash_index": "较适宜", /*洗车指数*/
		// "travel_index": "适宜", /*旅游指数*/
		// "exercise_index": "较适宜", /*晨练指数*/
		// "drying_index": ""/*干燥指数*/

		private String city;
		private String date_y;
		private String week;
		private String temperature;
		private String weather;
		private WeatherId weather_id;
		private String wind;
		private String dressing_index;
		private String dressing_advice;
		private String uv_index;
		private String comfort_index;
		private String wash_index;
		private String travel_index;
		private String exercise_index;
		private String drying_index;

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getDate_y() {
			return date_y;
		}

		public void setDate_y(String date_y) {
			this.date_y = date_y;
		}

		public String getWeek() {
			return week;
		}

		public void setWeek(String week) {
			this.week = week;
		}

		public String getTemperature() {
			return temperature;
		}

		public void setTemperature(String temperature) {
			this.temperature = temperature;
		}

		public String getWeather() {
			return weather;
		}

		public void setWeather(String weather) {
			this.weather = weather;
		}

		public WeatherId getWeather_id() {
			return weather_id;
		}

		public void setWeather_id(WeatherId weather_id) {
			this.weather_id = weather_id;
		}

		public String getWind() {
			return wind;
		}

		public void setWind(String wind) {
			this.wind = wind;
		}

		public String getDressing_index() {
			return dressing_index;
		}

		public void setDressing_index(String dressing_index) {
			this.dressing_index = dressing_index;
		}

		public String getDressing_advice() {
			return dressing_advice;
		}

		public void setDressing_advice(String dressing_advice) {
			this.dressing_advice = dressing_advice;
		}

		public String getUv_index() {
			return uv_index;
		}

		public void setUv_index(String uv_index) {
			this.uv_index = uv_index;
		}

		public String getComfort_index() {
			return comfort_index;
		}

		public void setComfort_index(String comfort_index) {
			this.comfort_index = comfort_index;
		}

		public String getWash_index() {
			return wash_index;
		}

		public void setWash_index(String wash_index) {
			this.wash_index = wash_index;
		}

		public String getTravel_index() {
			return travel_index;
		}

		public void setTravel_index(String travel_index) {
			this.travel_index = travel_index;
		}

		public String getExercise_index() {
			return exercise_index;
		}

		public void setExercise_index(String exercise_index) {
			this.exercise_index = exercise_index;
		}

		public String getDrying_index() {
			return drying_index;
		}

		public void setDrying_index(String drying_index) {
			this.drying_index = drying_index;
		}

	}

	public class WeatherId {
		private String fa;
		private String fb;

		public String getFa() {
			return fa;
		}

		public void setFa(String fa) {
			this.fa = fa;
		}

		public String getFb() {
			return fb;
		}

		public void setFb(String fb) {
			this.fb = fb;
		}

	}

	public class Future {
//		 "temperature": "28℃~36℃",
//         "weather": "晴转多云",
//         "weather_id": {
//             "fa": "00",
//             "fb": "01"
//         },
//         "wind": "南风3-4级",
//         "week": "星期一",
//         "date": "20140804"

		private String temperature;
		private String weather;
		private WeatherId weather_id;
		private String wind;
		private String week;
		private String date;

		public String getTemperature() {
			return temperature;
		}

		public void setTemperature(String temperature) {
			this.temperature = temperature;
		}

		public String getWeather() {
			return weather;
		}

		public void setWeather(String weather) {
			this.weather = weather;
		}

		public WeatherId getWeather_id() {
			return weather_id;
		}

		public void setWeather_id(WeatherId weather_id) {
			this.weather_id = weather_id;
		}

		public String getWind() {
			return wind;
		}

		public void setWind(String wind) {
			this.wind = wind;
		}

		public String getWeek() {
			return week;
		}

		public void setWeek(String week) {
			this.week = week;
		}

		public String getDate() {
			return date;
		}

		public void setDate(String date) {
			this.date = date;
		}

	}
}
