package com.juhe.pockettools.pm;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.google.gson.Gson;
import com.juhe.pockettools.R;
import com.juhe.pockettools.city.CityActivity;
import com.juhe.pockettools.commonview.CircleProgressBar;
import com.juhe.pockettools.home.FullscreenActivity;
import com.thinkland.sdk.android.DataCallBack;
import com.thinkland.sdk.android.JuheData;
import com.thinkland.sdk.android.Parameters;

public class PMMainActivity extends FullscreenActivity {
	private final String TAG = "PMMainActivity";
	private ListView week_pm_listveiw;
	private TextView txtTitle;
	private TextView lbl_pm25;
	private TextView lbl_pm10;
	private TextView lbl_SO;
	private TextView lbl_NO;
	private TextView txt_pm_state;
	private TextView txt_pm_value;
	private FrameLayout pm_state_container;
	private Button btn_back;
	private View btn_save;
	private View view_bg;
	private ImageView img_bg;
	private PMHeaderView pmheaderview;
	private CityListViewAdapter citylistviewadapter;
	private BarChart chartbarpm;
	private CircleProgressBar circle_progress_bar;

	private String cityname = "苏州";

	// private void a(double[] paramArrayOfDouble, String[] paramArrayOfString,
	// String paramString) {
	// chartbarpm.setDrawYValues(true);
	// chartbarpm.setDescription(" ");
	// chartbarpm.setDrawValuesForWholeStack(true);
	// chartbarpm.setValueTextColor(-1);
	// chartbarpm.setDrawBarShadow(false);
	// chartbarpm.setDrawVerticalGrid(true);
	// chartbarpm.setDrawHorizontalGrid(false);
	// chartbarpm.setDrawGridBackground(false);
	// o localo = chartbarpm.getXLabels();
	// localo.a(-1);
	// localo.a(o.a.b);
	// chartbarpm.setDrawGridBackground(false);
	// localo.a(true);
	// localo.b(0);
	// chartbarpm.setDrawYLabels(true);
	// p localp = chartbarpm.getYLabels();
	// localp.a(-1);
	// localp.a(p.a.a);
	// localp.c(true);
	// localp.b(true);
	// chartbarpm.setDrawYAxisEnabled(true);
	// chartbarpm.setDrawBorder(true);
	// chartbarpm.setStartAtZero(true);
	// chartbarpm.setDrawLegend(false);
	// chartbarpm.setDrawLegend(false);
	// chartbarpm.setDoubleTapToZoomEnabled(false);
	// chartbarpm.D();
	// chartbarpm.setScaleEnabled(false);
	// chartbarpm.setClickHightEnabled(false);
	// chartbarpm.setDrawPMLastValueBarEnabled(true);
	// chartbarpm.c(2500);
	// a(paramArrayOfDouble, paramArrayOfString);
	// }

	private void setPMData(PMEntity.Result entity, String pm25) {
		txtTitle.setText(entity.getCity());
		txt_pm_state.setText(PMTools.getQualityStr(Integer.parseInt(entity.getAQI())));
		txt_pm_state.setBackgroundColor(PMTools.getQualityBackground(Integer.parseInt(entity.getAQI())));
		txt_pm_value.setText(entity.getAQI());
		lbl_pm25.setText(pm25);
		lbl_pm10.setText(entity.getPM10());
		lbl_SO.setText(entity.getSO2());
		lbl_NO.setText(entity.getNO2());
	}

	private void setAirData(CityAirEntity.PM entity, List<CityAirEntity.PM> pmlist, List<CityAirEntity.Moni> monilist) {
		citylistviewadapter.setData(monilist);
		
	}
	
	private void getData() {
		Parameters params = new Parameters();
		params.add("city", cityname);
		params.add("key", "e27f776074aaa9df759bf76bded2fc14");
		JuheData.executeWithAPI(33,
				"http://web.juhe.cn:8080/environment/air/pm", JuheData.GET,
				params, new DataCallBack() {

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
								JSONObject r = ((JSONObject) new JSONObject(
										result).getJSONArray("result").get(0));
								String pm25 = r.getString("PM2.5");

								setPMData(entity.getResult().get(0), pm25);
							} catch (Exception e) {
								e.printStackTrace();
							}
						} else {
							Toast.makeText(getApplicationContext(), reason,
									Toast.LENGTH_SHORT).show();
						}
					}
				});

		JuheData.executeWithAPI(33,
				"http://web.juhe.cn:8080/environment/air/cityair",
				JuheData.GET, params, new DataCallBack() {

					@Override
					public void resultLoaded(int err, String reason,
							String result) {

						if (err == 0) {
							try {
								CityAirEntity entity = new Gson().fromJson(
										result, CityAirEntity.class);
								if (entity.getError_code() != 0
										&& entity.getError_code() != 200) {
									Toast.makeText(getApplicationContext(),
											entity.getReason(),
											Toast.LENGTH_SHORT).show();
									return;
								}

								// 前2星期空气质量
								JSONObject lastTwoWeeks = ((JSONObject) new JSONObject(
										result).getJSONArray("result").get(0))
										.getJSONObject("lastTwoWeeks");

								List<CityAirEntity.PM> pmlist = new ArrayList<CityAirEntity.PM>();
								try {
									for (int i = 1; i < 50; i++) {
										CityAirEntity.PM pm = new CityAirEntity.PM();
										JSONObject json = lastTwoWeeks
												.getJSONObject("" + i);
										pm.setCity(json.getString("city"));
										pm.setAQI(json.getString("AQI"));
										pm.setQuality(json.getString("quality"));
										pm.setDate(json.getString("date"));
										pmlist.add(pm);
									}
								} catch (Exception e) {
								}

								// 区域空气质量
								JSONObject lastMoniData = ((JSONObject) new JSONObject(
										result).getJSONArray("result").get(0))
										.getJSONObject("lastMoniData");

								List<CityAirEntity.Moni> monilist = new ArrayList<CityAirEntity.Moni>();
								try {
									for (int i = 1; i < 30; i++) {
										CityAirEntity.Moni moni = new CityAirEntity.Moni();
										JSONObject json = lastMoniData
												.getJSONObject("" + i);
										moni.setCity(json.getString("city"));
										moni.setAQI(json.getString("AQI"));
										moni.setAmerica_AQI(json
												.getString("America_AQI"));
										moni.setQuality(json
												.getString("quality"));
										moni.setPM25Hour(json
												.getString("PM2.5Hour"));
										moni.setPM25Day(json
												.getString("PM2.5Day"));
										moni.setPM10Hour(json
												.getString("PM10Hour"));
										moni.setLat(json.getString("lat"));
										moni.setLon(json.getString("lon"));
										monilist.add(moni);
									}
								} catch (Exception e) {
								}

								setAirData(entity.getResult().get(0).getCitynow(), pmlist, monilist);
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

	@SuppressLint({ "NewApi" })
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.activity_pm_main);
		txtTitle = ((TextView) findViewById(R.id.txtTitle));
		pmheaderview = new PMHeaderView(this);
		view_bg = findViewById(R.id.view_bg);
		img_bg = ((ImageView) findViewById(R.id.img_bg));
		txt_pm_state = ((TextView) pmheaderview.findViewById(R.id.txt_pm_state));
		txt_pm_value = ((TextView) pmheaderview.findViewById(R.id.txt_pm_value));
		lbl_pm25 = ((TextView) pmheaderview.findViewById(R.id.lbl_pm25));
		lbl_pm10 = ((TextView) pmheaderview.findViewById(R.id.lbl_pm10));
		lbl_SO = ((TextView) pmheaderview.findViewById(R.id.lbl_SO));
		lbl_NO = ((TextView) pmheaderview.findViewById(R.id.lbl_NO));
		pm_state_container = ((FrameLayout) pmheaderview
				.findViewById(R.id.pm_state_container));
		circle_progress_bar = ((CircleProgressBar) pmheaderview
				.findViewById(R.id.circle_progress_bar));
		chartbarpm = ((BarChart) pmheaderview.findViewById(R.id.chartbarpm));
		week_pm_listveiw = ((ListView) findViewById(R.id.week_pm_listveiw));
		week_pm_listveiw.addHeaderView(pmheaderview);
		citylistviewadapter = new CityListViewAdapter(this);
		week_pm_listveiw.setAdapter(citylistviewadapter);
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
				Intent intent = new Intent(PMMainActivity.this,
						CityActivity.class);
				startActivityForResult(intent,
						CityActivity.REQUEST_CODE_CITYNAME);
			}
		});
		Typeface localTypeface = Typeface.createFromAsset(getAssets(),
				"fonts/Helvetica Neue LT Std.otf");
		txt_pm_value.setTypeface(localTypeface);
		lbl_pm25.setTypeface(localTypeface);
		lbl_pm10.setTypeface(localTypeface);
		lbl_SO.setTypeface(localTypeface);
		lbl_NO.setTypeface(localTypeface);

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
}
