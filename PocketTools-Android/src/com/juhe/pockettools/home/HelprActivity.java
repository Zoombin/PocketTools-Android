package com.juhe.pockettools.home;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.GeofenceClient;
import com.google.gson.Gson;
import com.juhe.pockettools.HelprApplication;
import com.juhe.pockettools.R;
import com.juhe.pockettools.commonview.HorizontalListView;
import com.juhe.pockettools.pm.PMEntity;
import com.juhe.pockettools.pm.PMTools;
import com.juhe.pockettools.setting.SettingActivity;
import com.juhe.pockettools.utils.Config;
import com.juhe.pockettools.utils.HelprCommUtil;
import com.juhe.pockettools.weather.Hour;
import com.juhe.pockettools.weather.Weather;
import com.juhe.pockettools.weather.WeatherHourAdapter;
import com.juhe.pockettools.weather.WeatherHourInfo;
import com.juhe.pockettools.weather.WeatherTools;
import com.thinkland.sdk.android.DataCallBack;
import com.thinkland.sdk.android.JuheData;
import com.thinkland.sdk.android.Parameters;
import com.viewpagerindicator.CirclePageIndicator;

@SuppressLint("NewApi")
public class HelprActivity extends FullscreenActivity {

	View view;
	private ImageView img_bg;
	private Button btn_setting;
	private TextView tv_city;
	private TextView tv_updatetime;
	private ImageView image_weather;
	private TextView txt_weather;
	private HorizontalListView weather_hour_listview;
	private TextView txt_weather_centigrade;
	private ImageView iv_weather_up;
	private TextView txt_weather_up;
	private ImageView iv_weather_dn;
	private TextView txt_weather_dn;
	private TextView tv_pm_lable;
	private TextView txt_pm_value;
	private TextView txt_pm_state;
	List<ModuleInfo> toolinfos;
	List<ModuleInfo> lifeinfos;
	List<ModuleInfo> serviceinfos;
	List<ModuleInfo> storeinfos;
	private LinearLayout tool_container;
	private ViewPager tool_page;
	private CirclePageIndicator infoview_indicator_tool;
	private LinearLayout life_container;
	private ViewPager life_page;
	private CirclePageIndicator infoview_indicator_life;
	private LinearLayout service_container;
	private ViewPager service_page;
	private CirclePageIndicator infoview_indicator_service;
	private LinearLayout store_container;
	private ViewPager store_page;
	private CirclePageIndicator infoview_indicator_store;
	private int index;
	private int currentTabIndex;
	private Button[] mTabs;

	private String cityname = "苏州";
	private MyLocationListener mMyLocationListener;

	/**
	 * 实现实位回调监听
	 */
	public class MyLocationListener implements BDLocationListener {

		@Override
		public void onReceiveLocation(BDLocation location) {
			if (location != null) {
				cityname = location.getCity().replace("市", "");
				getData();
			}
		}
	}

	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		view = View.inflate(this, R.layout.activity_helpr_activity, null);
		if (HelprCommUtil.hasNavigationBar(this)) {
			getWindow().getDecorView().setSystemUiVisibility(
					View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
		}
		setContentView(view);

		mMyLocationListener = new MyLocationListener();
		HelprApplication.getInstance().mLocationClient
				.registerLocationListener(mMyLocationListener);
		HelprApplication.getInstance().mGeofenceClient = new GeofenceClient(
				getApplicationContext());
		HelprApplication.getInstance().mLocationClient.start();

		img_bg = (ImageView) findViewById(R.id.img_bg);
		img_bg.setBackgroundResource(Config.getBgDrawableResId());

		btn_setting = (Button) findViewById(R.id.btn_setting);
		btn_setting.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(HelprActivity.this,
						SettingActivity.class);
				startActivityForResult(intent, 0);
			}
		});
		tv_city = (TextView) findViewById(R.id.tv_city);
		tv_updatetime = (TextView) findViewById(R.id.tv_updatetime);
		image_weather = (ImageView) findViewById(R.id.image_weather);
		txt_weather = (TextView) findViewById(R.id.txt_weather);
		weather_hour_listview = (HorizontalListView) findViewById(R.id.weather_hour_listview);
		txt_weather_centigrade = (TextView) findViewById(R.id.txt_weather_centigrade);
		iv_weather_up = (ImageView) findViewById(R.id.iv_weather_up);
		iv_weather_dn = (ImageView) findViewById(R.id.iv_weather_dn);
		txt_weather_up = (TextView) findViewById(R.id.txt_weather_up);
		txt_weather_dn = (TextView) findViewById(R.id.txt_weather_dn);
		tv_pm_lable = (TextView) findViewById(R.id.tv_pm_lable);
		txt_pm_value = (TextView) findViewById(R.id.txt_pm_value);
		txt_pm_state = (TextView) findViewById(R.id.txt_pm_state);

		initModuleData();

		tool_container = (LinearLayout) findViewById(R.id.tool_container);
		tool_page = (ViewPager) findViewById(R.id.tool_page);
		tool_page.setAdapter(new ItemPagerAdapter(this,
				getSupportFragmentManager(), toolinfos));
		infoview_indicator_tool = (CirclePageIndicator) findViewById(R.id.infoview_indicator_tool);
		infoview_indicator_tool.setViewPager(tool_page);

		life_container = (LinearLayout) findViewById(R.id.life_container);
		life_page = (ViewPager) findViewById(R.id.life_page);
		life_page.setAdapter(new ItemPagerAdapter(this,
				getSupportFragmentManager(), lifeinfos));
		infoview_indicator_life = (CirclePageIndicator) findViewById(R.id.infoview_indicator_life);
		infoview_indicator_life.setViewPager(life_page);

		service_container = (LinearLayout) findViewById(R.id.service_container);
		service_page = (ViewPager) findViewById(R.id.service_page);
		service_page.setAdapter(new ItemPagerAdapter(this,
				getSupportFragmentManager(), serviceinfos));
		infoview_indicator_service = (CirclePageIndicator) findViewById(R.id.infoview_indicator_service);
		infoview_indicator_service.setViewPager(service_page);

		store_container = (LinearLayout) findViewById(R.id.store_container);
		store_page = (ViewPager) findViewById(R.id.store_page);
		store_page.setAdapter(new ItemPagerAdapter(this,
				getSupportFragmentManager(), storeinfos));
		infoview_indicator_store = (CirclePageIndicator) findViewById(R.id.infoview_indicator_store);
		infoview_indicator_store.setViewPager(store_page);

		this.mTabs = new Button[4];
		this.mTabs[0] = ((Button) findViewById(R.id.btn_tool));
		this.mTabs[1] = ((Button) findViewById(R.id.btn_life));
		this.mTabs[2] = ((Button) findViewById(R.id.btn_service));
		this.mTabs[3] = ((Button) findViewById(R.id.btn_store));
		this.mTabs[0].setSelected(true);
		
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

	@Override
	protected void onActivityResult(int arg0, int arg1, Intent arg2) {
		super.onActivityResult(arg0, arg1, arg2);

		img_bg.setBackgroundResource(Config.getBgDrawableResId());
	}

	public void onTabClicked(View v) {
		tabSelect(v.getId());
	}

	public void tabSelect(int viewid) {
		switch (viewid) {
		case R.id.btn_tool:
			index = 0;
			selectTab();
			break;
		case R.id.btn_life:
			index = 1;
			selectTab();
			break;
		case R.id.btn_service:
			index = 2;
			selectTab();
			break;
		case R.id.btn_store:
			index = 3;
			selectTab();
			break;
		default:
			break;
		}
	}

	private void selectTab() {
		if (currentTabIndex != index) {
			switch (index) {
			case 0:
				tool_container.setVisibility(View.VISIBLE);
				life_container.setVisibility(View.INVISIBLE);
				service_container.setVisibility(View.INVISIBLE);
				store_container.setVisibility(View.INVISIBLE);
				break;
			case 1:
				tool_container.setVisibility(View.INVISIBLE);
				life_container.setVisibility(View.VISIBLE);
				service_container.setVisibility(View.INVISIBLE);
				store_container.setVisibility(View.INVISIBLE);
				break;
			case 2:
				tool_container.setVisibility(View.INVISIBLE);
				life_container.setVisibility(View.INVISIBLE);
				service_container.setVisibility(View.VISIBLE);
				store_container.setVisibility(View.INVISIBLE);
				break;
			case 3:
				tool_container.setVisibility(View.INVISIBLE);
				life_container.setVisibility(View.INVISIBLE);
				service_container.setVisibility(View.INVISIBLE);
				store_container.setVisibility(View.VISIBLE);
				break;
			default:
				break;
			}
		}
		mTabs[currentTabIndex].setSelected(false);
		mTabs[index].setSelected(true);
		currentTabIndex = index;
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
							getApplicationContext(), infos));
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
									Toast.makeText(getApplicationContext(),
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

								Config.setHourInfo(result);

								initHourInfo(result);
							} catch (Exception e) {
								e.printStackTrace();
							}
						} else {
							Toast.makeText(getApplicationContext(), reason,
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
									Toast.makeText(getApplicationContext(),
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
							Toast.makeText(getApplicationContext(), reason,
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

	private void initModuleData() {
		// 日常工具
		toolinfos = new ArrayList<ModuleInfo>();
		ModuleInfo info1 = new ModuleInfo();
		info1.setName("新闻");
		info1.setIcon(R.drawable.gr_new);
		info1.setId(1);
		toolinfos.add(info1);

		ModuleInfo info2 = new ModuleInfo();
		info2.setName("空气质量");
		info2.setIcon(R.drawable.gr_pm25);
		info2.setId(2);
		toolinfos.add(info2);

		ModuleInfo info3 = new ModuleInfo();
		info3.setName("天气预报");
		info3.setIcon(R.drawable.gr_weather);
		info3.setId(3);
		toolinfos.add(info3);

		ModuleInfo info4 = new ModuleInfo();
		info4.setName("车辆违章");
		info4.setIcon(R.drawable.gr_violation);
		info4.setId(4);
		toolinfos.add(info4);

		ModuleInfo info5 = new ModuleInfo();
		info5.setName("来电查询");
		info5.setIcon(R.drawable.gr_mobilelocale);
		info5.setId(5);
		toolinfos.add(info5);

		ModuleInfo info6 = new ModuleInfo();
		info6.setName("聊天机器人");
		info6.setIcon(R.drawable.gr_tuling);
		info6.setId(6);
		toolinfos.add(info6);

		ModuleInfo info7 = new ModuleInfo();
		info7.setName("苹果序列号");
		info7.setIcon(R.drawable.gr_applesn);
		info7.setId(7);
		toolinfos.add(info7);

		// 生活服务
		lifeinfos = new ArrayList<ModuleInfo>();
		ModuleInfo info8 = new ModuleInfo();
		info8.setName("星座");
		info8.setIcon(R.drawable.gr_constelltion);
		info8.setId(8);
		lifeinfos.add(info8);

		ModuleInfo info9 = new ModuleInfo();
		info9.setName("老黄历");
		info9.setIcon(R.drawable.gr_calendar);
		info9.setId(9);
		lifeinfos.add(info9);

		ModuleInfo info10 = new ModuleInfo();
		info10.setName("周公解梦");
		info10.setIcon(R.drawable.gr_dream);
		info10.setId(10);
		lifeinfos.add(info10);

		ModuleInfo info11 = new ModuleInfo();
		info11.setName("秘密相册");
		info11.setIcon(R.drawable.gr_secret);
		info11.setId(11);
		lifeinfos.add(info11);

		ModuleInfo info12 = new ModuleInfo();
		info12.setName("火车订票");
		info12.setIcon(R.drawable.gr_train);
		info12.setId(12);
		lifeinfos.add(info12);

		ModuleInfo info13 = new ModuleInfo();
		info13.setName("加油站");
		info13.setIcon(R.drawable.gr_oil);
		info13.setId(13);
		lifeinfos.add(info13);

		ModuleInfo info14 = new ModuleInfo();
		info14.setName("快递");
		info14.setIcon(R.drawable.gr_courier);
		info14.setId(14);
		lifeinfos.add(info14);

		// 阅读发现
		serviceinfos = new ArrayList<ModuleInfo>();
		ModuleInfo info15 = new ModuleInfo();
		info15.setName("电影");
		info15.setIcon(R.drawable.gr_moive);
		info15.setId(15);
		serviceinfos.add(info15);

		ModuleInfo info16 = new ModuleInfo();
		info16.setName("航班动态");
		info16.setIcon(R.drawable.gr_air);
		info16.setId(16);
		serviceinfos.add(info16);

		ModuleInfo info17 = new ModuleInfo();
		info17.setName("停车场");
		info17.setIcon(R.drawable.gr_stop);
		info17.setId(17);
		serviceinfos.add(info17);

		ModuleInfo info18 = new ModuleInfo();
		info18.setName("POI");
		info18.setIcon(R.drawable.gr_poi);
		info18.setId(18);
		serviceinfos.add(info18);

		ModuleInfo info19 = new ModuleInfo();
		info19.setName("条码比价");
		info19.setIcon(R.drawable.gr_qrcode);
		info19.setId(19);
		serviceinfos.add(info19);

		ModuleInfo info20 = new ModuleInfo();
		info20.setName("话费充值");
		info20.setIcon(R.drawable.gr_phone);
		info20.setId(20);
		serviceinfos.add(info20);

		ModuleInfo info21 = new ModuleInfo();
		info21.setName("游戏充值");
		info21.setIcon(R.drawable.gr_game);
		info21.setId(21);
		serviceinfos.add(info21);

		ModuleInfo info22 = new ModuleInfo();
		info22.setName("彩票购买");
		info22.setIcon(R.drawable.gr_caipiao);
		info22.setId(22);
		serviceinfos.add(info22);

		ModuleInfo info23 = new ModuleInfo();
		info23.setName("流量直充");
		info23.setIcon(R.drawable.gr_liuliang);
		info23.setId(23);
		serviceinfos.add(info23);

		storeinfos = new ArrayList<ModuleInfo>();
		ModuleInfo info24 = new ModuleInfo();
		info24.setName("尺子");
		info24.setIcon(R.drawable.gr_ruler);
		info24.setId(24);
		storeinfos.add(info24);

		ModuleInfo info25 = new ModuleInfo();
		info25.setName("镜子");
		info25.setIcon(R.drawable.gr_mirror);
		info25.setId(25);
		storeinfos.add(info25);

		ModuleInfo info26 = new ModuleInfo();
		info26.setName("科学计算器");
		info26.setIcon(R.drawable.gr_calculator);
		info26.setId(26);
		storeinfos.add(info26);

		ModuleInfo info27 = new ModuleInfo();
		info27.setName("尺码对照表");
		info27.setIcon(R.drawable.gr_sizatable);
		info27.setId(27);
		storeinfos.add(info27);

		ModuleInfo info28 = new ModuleInfo();
		info28.setName("单位换算");
		info28.setIcon(R.drawable.gr_unit);
		info28.setId(28);
		storeinfos.add(info28);

		ModuleInfo info29 = new ModuleInfo();
		info29.setName("汇率换算");
		info29.setIcon(R.drawable.gr_exchange);
		info29.setId(29);
		storeinfos.add(info29);

		ModuleInfo info30 = new ModuleInfo();
		info30.setName("手电筒");
		info30.setId(30);
		info30.setIcon(R.drawable.gr_flashlight);
		storeinfos.add(info30);
	}
}
