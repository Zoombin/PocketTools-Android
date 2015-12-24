package com.juhe.pockettools.home;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.GeofenceClient;
import com.google.gson.Gson;
import com.juhe.pockettools.HelprApplication;
import com.zoombin.koudai.R;
import com.juhe.pockettools.commonview.HorizontalListView;
import com.juhe.pockettools.pm.PMEntity;
import com.juhe.pockettools.pm.PMMainActivity;
import com.juhe.pockettools.pm.PMTools;
import com.juhe.pockettools.utils.Config;
import com.juhe.pockettools.utils.HelprCommUtil;
import com.juhe.pockettools.weather.Hour;
import com.juhe.pockettools.weather.Weather;
import com.juhe.pockettools.weather.WeatherHourAdapter;
import com.juhe.pockettools.weather.WeatherHourInfo;
import com.juhe.pockettools.weather.WeatherMainActivity;
import com.juhe.pockettools.weather.WeatherTools;
import com.thinkland.sdk.android.DataCallBack;
import com.thinkland.sdk.android.JuheData;
import com.thinkland.sdk.android.Parameters;

public class WeatherFragment extends Fragment {

	private Context context;
	private TextView tv_city;
	private TextView tv_updatetime;
	private ImageView image_weather;
	private TextView txt_weather;
	private HorizontalListView weather_hour_listview;
	private RelativeLayout ly_weather_centigrade;
	private LinearLayout ly_pm;
	private TextView txt_weather_centigrade;
	private ImageView iv_weather_up;
	private TextView txt_weather_up;
	private ImageView iv_weather_dn;
	private TextView txt_weather_dn;
	private TextView tv_pm_lable;
	private TextView txt_pm_value;
	private TextView txt_pm_state;
	
	private String cityname = "苏州";
	private MyLocationListener mMyLocationListener;
	
	/**
	 * 实现实位回调监听
	 */
	public class MyLocationListener implements BDLocationListener {

		@Override
		public void onReceiveLocation(BDLocation location) {
			if (location != null) {
				if (location.getCity() == null) {
					return;
				}
				cityname = location.getCity().replace("市", "");
				getData();
			}
		}
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_weather, container, false);
		this.context = HelprApplication.getContext();
		createView(v);
		initData();
		return v;
	}
	
	private void createView(View v) {
		ly_weather_centigrade = (RelativeLayout) v.findViewById(R.id.ly_weather_centigrade);
		ly_weather_centigrade.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context, WeatherMainActivity.class);
				startActivity(intent);
			}
		});
		ly_pm = (LinearLayout) v.findViewById(R.id.ly_pm);
		ly_pm.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context, PMMainActivity.class);
				startActivity(intent);
			}
		});
		
		tv_city = (TextView) v.findViewById(R.id.tv_city);
		tv_updatetime = (TextView) v.findViewById(R.id.tv_updatetime);
		image_weather = (ImageView) v.findViewById(R.id.image_weather);
		txt_weather = (TextView) v.findViewById(R.id.txt_weather);
		weather_hour_listview = (HorizontalListView) v.findViewById(R.id.weather_hour_listview);
		txt_weather_centigrade = (TextView) v.findViewById(R.id.txt_weather_centigrade);
		iv_weather_up = (ImageView) v.findViewById(R.id.iv_weather_up);
		iv_weather_dn = (ImageView) v.findViewById(R.id.iv_weather_dn);
		txt_weather_up = (TextView) v.findViewById(R.id.txt_weather_up);
		txt_weather_dn = (TextView) v.findViewById(R.id.txt_weather_dn);
		tv_pm_lable = (TextView) v.findViewById(R.id.tv_pm_lable);
		txt_pm_value = (TextView) v.findViewById(R.id.txt_pm_value);
		txt_pm_state = (TextView) v.findViewById(R.id.txt_pm_state);
	}
	
	private void initData() {
		mMyLocationListener = new MyLocationListener();
		HelprApplication.getInstance().mLocationClient
				.registerLocationListener(mMyLocationListener);
		HelprApplication.getInstance().mGeofenceClient = new GeofenceClient(
				context);
		HelprApplication.getInstance().mLocationClient.start();
		
		// 初始化天气数据
		String weatherinfo = Config.getWeatherInfo();
		if (!TextUtils.isEmpty(weatherinfo)) {
			initWeatherInfo(weatherinfo);
		}
		
		String hourinfo = Config.getHourInfo();
		if (!TextUtils.isEmpty(hourinfo)) {
			initHourInfo(hourinfo);
		}
		
		String pminfo = Config.getPMInfo();
		if (!TextUtils.isEmpty(pminfo)) {
			initPMInfo(pminfo);
		}
	}
	
	public void initWeatherInfo(String result) {
		try {
			Weather entity = new Gson().fromJson(result,
					Weather.class);
			JSONObject future = new JSONObject(result).getJSONObject("result")
					.getJSONObject("future");
	
			List<Weather.Future> list = new ArrayList<Weather.Future>();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			for (int i = 0; i < 7; i++) {
				Calendar calendar = Calendar.getInstance();
				calendar.add(Calendar.DAY_OF_YEAR, i);
				Date date = calendar.getTime();
				JSONObject day = future.getJSONObject("day_" + sdf.format(date));
				Weather.Future futureentity = new Gson().fromJson(day.toString(),
						Weather.Future.class);
				list.add(futureentity);
			}
	
			setData(entity.getResult(), list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void initHourInfo(String result) {
		try {
//			{"resultcode":"200","reason":"successed!","result":{"sk":{"temp":"13","wind_direction":"东风","wind_strength":"3级","humidity":"35%","time":"19:13"},"today":{"temperature":"8℃~16℃","weather":"晴","weather_id":{"fa":"00","fb":"00"},"wind":"东南风3-4 级","week":"星期三","city":"苏州","date_y":"2015年03月25日","dressing_index":"较冷","dressing_advice":"建议着厚外套加毛衣等服装。年老体弱者宜着大衣、呢外套加羊毛衫。","uv_index":"最弱","comfort_index":"","wash_index":"不宜","travel_index":"","exercise_index":"较不宜","drying_index":""},"future":{"day_20150325":{"temperature":"8℃~16℃","weather":"晴","weather_id":{"fa":"00","fb":"00"},"wind":"东南风3-4 级","week":"星期三","date":"20150325"},"day_20150326":{"temperature":"10℃~16℃","weather":"阵雨转小雨","weather_id":{"fa":"03","fb":"07"},"wind":"东风微风","week":"星期四","date":"20150326"},"day_20150327":{"temperature":"11℃~16℃","weather":"阴转多云","weather_id":{"fa":"02","fb":"01"},"wind":"东南风微风","week":"星期五","date":"20150327"},"day_20150328":{"temperature":"12℃~20℃","weather":"多云","weather_id":{"fa":"01","fb":"01"},"wind":"南风微风","week":"星期六","date":"20150328"},"day_20150329":{"temperature":"13℃~22℃","weather":"多云","weather_id":{"fa":"01","fb":"01"},"wind":"东南风微风","week":"星期日","date":"20150329"},"day_20150330":{"temperature":"15℃~23℃","weather":"阴转小雨","weather_id":{"fa":"02","fb":"07"},"wind":"南风微风","week":"星期一","date":"20150330"},"day_20150331":{"temperature":"15℃~26℃","weather":"雷阵雨转小雨","weather_id":{"fa":"04","fb":"07"},"wind":"南风微风","week":"星期二","date":"20150331"}}},"error_code":0}
//			{"resultcode":"200","reason":"successed!","result":[{"weatherid":"00","weather":"晴","temp1":"8","temp2":"15","sh":"05","eh":"08","date":"20150325","sfdate":"20150325050000","efdate":"20150325080000"},{"weatherid":"00","weather":"晴","temp1":"15","temp2":"16","sh":"08","eh":"11","date":"20150325","sfdate":"20150325080000","efdate":"20150325110000"},{"weatherid":"00","weather":"晴","temp1":"16","temp2":"16","sh":"11","eh":"14","date":"20150325","sfdate":"20150325110000","efdate":"20150325140000"},{"weatherid":"01","weather":"多云","temp1":"16","temp2":"10","sh":"14","eh":"17","date":"20150325","sfdate":"20150325140000","efdate":"20150325170000"},{"weatherid":"01","weather":"多云","temp1":"10","temp2":"9","sh":"17","eh":"20","date":"20150325","sfdate":"20150325170000","efdate":"20150325200000"},{"weatherid":"01","weather":"多云","temp1":"9","temp2":"8","sh":"20","eh":"23","date":"20150325","sfdate":"20150325200000","efdate":"20150325230000"},{"weatherid":"01","weather":"多云","temp1":"8","temp2":"8","sh":"23","eh":"02","date":"20150325","sfdate":"20150325230000","efdate":"20150326020000"},{"weatherid":"01","weather":"多云","temp1":"8","temp2":"10","sh":"02","eh":"05","date":"20150326","sfdate":"20150326020000","efdate":"20150326050000"},{"weatherid":"01","weather":"多云","temp1":"10","temp2":"15","sh":"05","eh":"08","date":"20150326","sfdate":"20150326050000","efdate":"20150326080000"},{"weatherid":"01","weather":"多云","temp1":"15","temp2":"15","sh":"08","eh":"11","date":"20150326","sfdate":"20150326080000","efdate":"20150326110000"},{"weatherid":"01","weather":"多云","temp1":"15","temp2":"15","sh":"11","eh":"14","date":"20150326","sfdate":"20150326110000","efdate":"20150326140000"},{"weatherid":"01","weather":"多云","temp1":"15","temp2":"13","sh":"14","eh":"17","date":"20150326","sfdate":"20150326140000","efdate":"20150326170000"},{"weatherid":"03","weather":"阵雨","temp1":"13","temp2":"12","sh":"17","eh":"20","date":"20150326","sfdate":"20150326170000","efdate":"20150326200000"},{"weatherid":"03","weather":"阵雨","temp1":"12","temp2":"11","sh":"20","eh":"23","date":"20150326","sfdate":"20150326200000","efdate":"20150326230000"},{"weatherid":"03","weather":"阵雨","temp1":"11","temp2":"11","sh":"23","eh":"02","date":"20150326","sfdate":"20150326230000","efdate":"20150327020000"},{"weatherid":"03","weather":"阵雨","temp1":"11","temp2":"12","sh":"02","eh":"05","date":"20150327","sfdate":"20150327020000","efdate":"20150327050000"},{"weatherid":"03","weather":"阵雨","temp1":"12","temp2":"16","sh":"05","eh":"08","date":"20150327","sfdate":"20150327050000","efdate":"20150327080000"},{"weatherid":"01","weather":"多云","temp1":"16","temp2":"16","sh":"08","eh":"11","date":"20150327","sfdate":"20150327080000","efdate":"20150327110000"},{"weatherid":"02","weather":"阴","temp1":"16","temp2":"16","sh":"11","eh":"14","date":"20150327","sfdate":"20150327110000","efdate":"20150327140000"},{"weatherid":"02","weather":"阴","temp1":"16","temp2":"14","sh":"14","eh":"17","date":"20150327","sfdate":"20150327140000","efdate":"20150327170000"},{"weatherid":"01","weather":"多云","temp1":"14","temp2":"12","sh":"17","eh":"20","date":"20150327","sfdate":"20150327170000","efdate":"20150327200000"},{"weatherid":"01","weather":"多云","temp1":"12","temp2":"11","sh":"20","eh":"23","date":"20150327","sfdate":"20150327200000","efdate":"20150327230000"},{"weatherid":"01","weather":"多云","temp1":"11","temp2":"11","sh":"23","eh":"02","date":"20150327","sfdate":"20150327230000","efdate":"20150328020000"},{"weatherid":"01","weather":"多云","temp1":"11","temp2":"13","sh":"02","eh":"05","date":"20150328","sfdate":"20150328020000","efdate":"20150328050000"},{"weatherid":"01","weather":"多云","temp1":"13","temp2":"19","sh":"05","eh":"11","date":"20150328","sfdate":"20150328050000","efdate":"20150328110000"},{"weatherid":"01","weather":"多云","temp1":"19","temp2":"14","sh":"11","eh":"17","date":"20150328","sfdate":"20150328110000","efdate":"20150328170000"},{"weatherid":"01","weather":"多云","temp1":"14","temp2":"13","sh":"17","eh":"23","date":"20150328","sfdate":"20150328170000","efdate":"20150328230000"},{"weatherid":"02","weather":"阴","temp1":"13","temp2":"15","sh":"23","eh":"05","date":"20150328","sfdate":"20150328230000","efdate":"20150329050000"},{"weatherid":"02","weather":"阴","temp1":"15","temp2":"21","sh":"05","eh":"11","date":"20150329","sfdate":"20150329050000","efdate":"20150329110000"},{"weatherid":"01","weather":"多云","temp1":"21","temp2":"17","sh":"11","eh":"17","date":"20150329","sfdate":"20150329110000","efdate":"20150329170000"},{"weatherid":"01","weather":"多云","temp1":"17","temp2":"15","sh":"17","eh":"23","date":"20150329","sfdate":"20150329170000","efdate":"20150329230000"},{"weatherid":"01","weather":"多云","temp1":"15","temp2":"16","sh":"23","eh":"05","date":"20150329","sfdate":"20150329230000","efdate":"20150330050000"},{"weatherid":"01","weather":"多云","temp1":"16","temp2":"22","sh":"05","eh":"11","date":"20150330","sfdate":"20150330050000","efdate":"20150330110000"},{"weatherid":"01","weather":"多云","temp1":"22","temp2":"19","sh":"11","eh":"17","date":"20150330","sfdate":"20150330110000","efdate":"20150330170000"},{"weatherid":"00","weather":"晴","temp1":"19","temp2":"17","sh":"17","eh":"23","date":"20150330","sfdate":"20150330170000","efdate":"20150330230000"},{"weatherid":"01","weather":"多云","temp1":"17","temp2":"20","sh":"23","eh":"05","date":"20150330","sfdate":"20150330230000","efdate":"20150331050000"},{"weatherid":"01","weather":"多云","temp1":"20","temp2":"22","sh":"05","eh":"11","date":"20150331","sfdate":"20150331050000","efdate":"20150331110000"},{"weatherid":"02","weather":"阴","temp1":"22","temp2":"22","sh":"11","eh":"17","date":"20150331","sfdate":"20150331110000","efdate":"20150331170000"},{"weatherid":"02","weather":"阴","temp1":"22","temp2":"20","sh":"17","eh":"23","date":"20150331","sfdate":"20150331170000","efdate":"20150331230000"},{"weatherid":"02","weather":"阴","temp1":"20","temp2":"19","sh":"23","eh":"05","date":"20150331","sfdate":"20150331230000","efdate":"20150401050000"}],"error_code":0}
			Hour entity = new Gson().fromJson(result,
					Hour.class);
			List<Hour.Result> list = entity.getResult();
			WeatherHourInfo[] infos = new WeatherHourInfo[list
					.size()];
			for (int i = 0; i < list.size(); i++) {
				infos[i] = new WeatherHourInfo();
				infos[i].weather = list.get(i).getWeather();
				infos[i].hour = WeatherTools
						.getDateStr(list.get(i).getSfdate());
				infos[i].temp = WeatherTools.getTemp(list
						.get(i).getTemp1());
			}
			weather_hour_listview
					.setAdapter(new WeatherHourAdapter(
							context, infos, true));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void initPMInfo(String result) {
		try {
			PMEntity entity = new Gson().fromJson(result,
					PMEntity.class);
			JSONObject r = ((JSONObject) new JSONObject(
					result).getJSONArray("result").get(0));
			String pm25 = r.getString("PM2.5");

			tv_pm_lable.setVisibility(View.VISIBLE);
			txt_pm_value.setText(pm25);

			PMEntity.Result entity1 = entity.getResult()
					.get(0);
			txt_pm_state.setText(PMTools
					.getQualityStr(Integer.parseInt(entity1
							.getAQI())));
			txt_pm_state.setBackgroundColor(PMTools
					.getQualityBackground(Integer
							.parseInt(entity1.getAQI())));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void getData() {
		Parameters params = new Parameters();
		params.add("cityname", cityname);
		params.add("key", "832463dcc76e758433b22244f4568ba1");
		JuheData.executeWithAPI(39, "http://v.juhe.cn/weather/index",
				JuheData.GET, params, new DataCallBack() {

					@Override
					public void resultLoaded(int err, String reason,
							String result) {

						if (err == 0) {
							try {
								Weather entity = new Gson().fromJson(result,
										Weather.class);
								if (entity.getError_code() != 0
										&& entity.getError_code() != 200) {
									Toast.makeText(context,
											entity.getReason(),
											Toast.LENGTH_SHORT).show();
									return;
								}

								Config.setWeatherInfo(result);

								initWeatherInfo(result);
							} catch (Exception e) {
								e.printStackTrace();
							}
						} else {
							Toast.makeText(context, reason,
									Toast.LENGTH_SHORT).show();
						}
					}
				});

		JuheData.executeWithAPI(39, "http://v.juhe.cn/weather/forecast3h",
				JuheData.GET, params, new DataCallBack() {

					@Override
					public void resultLoaded(int err, String reason,
							String result) {

						if (err == 0) {
							try {
								Hour entity = new Gson().fromJson(result,
										Hour.class);

								if (entity.getError_code() != 0
										&& entity.getError_code() != 200) {
									Toast.makeText(context,
											entity.getReason(),
											Toast.LENGTH_SHORT).show();
									return;
								}

								Config.setHourInfo(result);

								initHourInfo(result);
							} catch (Exception e) {
								e.printStackTrace();
							}
						} else {
							Toast.makeText(context, reason,
									Toast.LENGTH_SHORT).show();
						}
					}
				});

		Parameters params1 = new Parameters();
		params1.add("city", cityname);
		params1.add("key", "e27f776074aaa9df759bf76bded2fc14");
		JuheData.executeWithAPI(33,
				"http://web.juhe.cn:8080/environment/air/pm", JuheData.GET,
				params1, new DataCallBack() {

					@Override
					public void resultLoaded(int err, String reason,
							String result) {

						if (err == 0) {
							try {
								PMEntity entity = new Gson().fromJson(result,
										PMEntity.class);
								if (entity.getError_code() != 0
										&& entity.getError_code() != 200) {
									Toast.makeText(context,
											entity.getReason(),
											Toast.LENGTH_SHORT).show();
									return;
								}

								Config.setPMInfo(result);

								initPMInfo(result);
							} catch (Exception e) {
								e.printStackTrace();
							}
						} else {
							Toast.makeText(context, reason,
									Toast.LENGTH_SHORT).show();
						}
					}
				});
	}

	private void setData(Weather.Result result, List<Weather.Future> list) {
		Weather.SK sk = result.getSk();
		Weather.Today today = result.getToday();

		tv_updatetime.setText("更新" + sk.getTime());
		tv_city.setText(today.getCity());
		txt_weather_centigrade.setText(sk.getTemp() + "°");
		String temperature = today.getTemperature();
		String[] temp = temperature.split("~");
		iv_weather_up.setVisibility(View.VISIBLE);
		txt_weather_up.setText(temp[1].replace("℃", "") + "°");
		iv_weather_dn.setVisibility(View.VISIBLE);
		txt_weather_dn.setText(temp[0].replace("℃", "") + "°");
		txt_weather.setText(today.getWeather());
		image_weather.setImageBitmap(HelprCommUtil.getImage(WeatherTools
				.getWeatherIcon(today.getWeather())));
	}
}
