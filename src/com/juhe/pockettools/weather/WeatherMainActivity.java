package com.juhe.pockettools.weather;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.juhe.pockettools.R;
import com.juhe.pockettools.applesn.AppleSnEntity;
//import com.juhe.pockettools.helpr.Utils.k;
import com.juhe.pockettools.city.CityActivity;
import com.juhe.pockettools.commonview.CircleProgressBar;
import com.juhe.pockettools.commonview.HorizontalListView;
import com.juhe.pockettools.exchange.ExChangeEntity;
import com.juhe.pockettools.home.FullscreenActivity;
import com.juhe.pockettools.home.SplashAcitivity;
import com.juhe.pockettools.utils.HelprCommUtil;
import com.thinkland.sdk.android.DataCallBack;
import com.thinkland.sdk.android.JuheData;
import com.thinkland.sdk.android.Parameters;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WeatherMainActivity extends FullscreenActivity {
	private WeatherHeaderView weatherheaderview;
	WeatherDayAdapter weatherdayadapter = null;
	private final String TAG = "WeatherMainActivity";
	private TextView txtTitle;
	private TextView txt_weather;
	private TextView txt_weather_centigrade;
	private TextView txt_weather_up;
	private TextView txt_weather_dn;
	private TextView txt_weather_wind_level;
	private TextView txt_weather_wind;
	private TextView txt_weather_humidity_level;
	private ImageView image_weather;
	private TextView dressingAdviceLabel;
	private TextView uvLabel;
	private TextView travelLabel;
	private TextView washLabel;
	private TextView execriseLabel;
	private View view_bg;
	private ImageView img_bg;
	private FrameLayout ly_hour_weather;
	private CircleProgressBar circle_progress_bar;
	private HorizontalListView weather_hour_listview;
	private ListView week_weather_listveiw;
	private Button btn_back;
	private View btn_save;

	private String cityname = "苏州";

	private void setData(Weather.Result result, List<Weather.Future> list) {
		Weather.SK sk = result.getSk();
		Weather.Today today = result.getToday();

		txtTitle.setText(today.getCity());
		txt_weather_centigrade.setText(sk.getTemp() + "°");
		String temperature = today.getTemperature();
		String[] temp = temperature.split("~");
		txt_weather_up.setText(temp[1].replace("℃", "") + "°");
		txt_weather_dn.setText(temp[0].replace("℃", "") + "°");
		txt_weather.setText(today.getWeather());
		image_weather.setImageBitmap(HelprCommUtil.getImage(WeatherTools
				.getWeatherIcon(today.getWeather())));
		txt_weather_wind_level.setText(sk.getWind_strength());
		txt_weather_wind.setText(sk.getWind_direction());
		txt_weather_humidity_level.setText(sk.getHumidity());

		dressingAdviceLabel.setText(today.getDressing_advice());
		uvLabel.setText(today.getUv_index());
		washLabel.setText(today.getWash_index());
		travelLabel.setText(today.getTravel_index());
		execriseLabel.setText(today.getExercise_index());
		
		weatherdayadapter.setData(list);
	}

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.activity_weather_main);
		txtTitle = ((TextView) findViewById(R.id.txtTitle));
		view_bg = findViewById(R.id.view_bg);
		img_bg = ((ImageView) findViewById(R.id.img_bg));
		view_bg.setAlpha(0.15F);
		weatherheaderview = new WeatherHeaderView(this);
		txt_weather_centigrade = ((TextView) weatherheaderview
				.findViewById(R.id.txt_weather_centigrade));
		txt_weather_up = ((TextView) weatherheaderview
				.findViewById(R.id.txt_weather_up));
		txt_weather_dn = ((TextView) weatherheaderview
				.findViewById(R.id.txt_weather_dn));
		txt_weather_wind = ((TextView) weatherheaderview
				.findViewById(R.id.txt_weather_wind));
		txt_weather_wind_level = ((TextView) weatherheaderview
				.findViewById(R.id.txt_weather_wind_level));
		txt_weather_humidity_level = ((TextView) weatherheaderview
				.findViewById(R.id.txt_weather_humidity_level));
		txt_weather = ((TextView) weatherheaderview
				.findViewById(R.id.txt_weather));
		image_weather = ((ImageView) weatherheaderview
				.findViewById(R.id.image_weather));
		dressingAdviceLabel = ((TextView) weatherheaderview
				.findViewById(R.id.dressingAdviceLabel));
		uvLabel = ((TextView) weatherheaderview.findViewById(R.id.uvLabel));
		travelLabel = ((TextView) weatherheaderview
				.findViewById(R.id.travelLabel));
		washLabel = ((TextView) weatherheaderview.findViewById(R.id.washLabel));
		execriseLabel = ((TextView) weatherheaderview
				.findViewById(R.id.execriseLabel));
		txt_weather_dn.setTextColor(Color.rgb(159, 227, 254));
		Typeface localTypeface1 = Typeface.createFromAsset(getAssets(),
				"fonts/HelveticaNeueLTPro-Lt.otf");
		Typeface localTypeface2 = Typeface.createFromAsset(getAssets(),
				"fonts/Helvetica Neue LT Std.otf");
		txt_weather_centigrade.setTypeface(localTypeface2);
		txt_weather_up.setTypeface(localTypeface1);
		txt_weather_dn.setTypeface(localTypeface1);
		txt_weather_wind_level.setTypeface(localTypeface2);
		txt_weather_humidity_level.setTypeface(localTypeface2);
		circle_progress_bar = ((CircleProgressBar) weatherheaderview
				.findViewById(R.id.circle_progress_bar));
		ly_hour_weather = ((FrameLayout) weatherheaderview
				.findViewById(R.id.ly_hour_weather));
		weather_hour_listview = ((HorizontalListView) weatherheaderview
				.findViewById(R.id.weather_hour_listview));

		week_weather_listveiw = ((ListView) findViewById(R.id.week_weather_listveiw));
		week_weather_listveiw.addHeaderView(weatherheaderview);
		weatherdayadapter = new WeatherDayAdapter(this);
		week_weather_listveiw.setAdapter(weatherdayadapter);
		btn_back = ((Button) findViewById(R.id.btn_back));
		btn_back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
		btn_save = findViewById(R.id.btn_save);
		btn_save.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(WeatherMainActivity.this,
						CityActivity.class);
				 startActivityForResult(intent, CityActivity.REQUEST_CODE_CITYNAME);
			}
		});

		getData();
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == CityActivity.REQUEST_CODE_CITYNAME) {
			if (resultCode == RESULT_OK) {
				cityname = data.getStringExtra(CityActivity.EXTRA_CITYNAME);
				getData();
			}
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
									Toast.makeText(getApplicationContext(),
											entity.getReason(),
											Toast.LENGTH_SHORT).show();
									return;
								}
								JSONObject future = new JSONObject(result)
										.getJSONObject("result").getJSONObject(
												"future");

								List<Weather.Future> list = new ArrayList<Weather.Future>();
								SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
								for (int i = 0; i<7; i++) {
									Calendar calendar = Calendar.getInstance();
									calendar.add(Calendar.DAY_OF_YEAR, i);
									Date date = calendar.getTime();
									JSONObject day = future
											.getJSONObject("day_" + sdf.format(date));
									Weather.Future futureentity = new Gson()
											.fromJson(day.toString(),
													Weather.Future.class);
									list.add(futureentity);
								}
								
								setData(entity.getResult(), list);
							} catch (Exception e) {
								e.printStackTrace();
							}
						} else {
							Toast.makeText(getApplicationContext(), reason,
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
									Toast.makeText(getApplicationContext(),
											entity.getReason(),
											Toast.LENGTH_SHORT).show();
									return;
								}
								List<Hour.Result> list = entity.getResult();
								WeatherHourInfo[] infos = new WeatherHourInfo[list.size()];
								for (int i=0; i<list.size(); i++) {
									infos[i] = new WeatherHourInfo();
									infos[i].weather = list.get(i).getWeather();
									infos[i].hour = WeatherTools.getDateStr(list.get(i).getSfdate());
									infos[i].temp =  WeatherTools.getTemp(list.get(i).getTemp1());
								}
								weather_hour_listview.setAdapter(new WeatherHourAdapter(getApplicationContext(), infos));
							} catch (Exception e) {
								e.printStackTrace();
							}
						} else {
							Toast.makeText(getApplicationContext(), reason,
									Toast.LENGTH_SHORT).show();
						}
					}
				});
	}
}
