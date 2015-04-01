package com.juhe.pockettools.pm;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
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
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.XAxis.XAxisPosition;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.gson.Gson;
import com.juhe.pockettools.R;
import com.juhe.pockettools.city.CityActivity;
import com.juhe.pockettools.commonview.CircleProgressBar;
import com.juhe.pockettools.home.FullscreenActivity;
import com.juhe.pockettools.utils.Config;
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
//	private ImageView img_bg;
	private PMHeaderView pmheaderview;
	private CityListViewAdapter citylistviewadapter;
	private BarChart mChart;
	private CircleProgressBar circle_progress_bar;

	private String cityname = "苏州";

	private void showPmChart(List<CityAirEntity.PM> weekpmlist) {
		mChart.setDescription("");

		// if more than 60 entries are displayed in the chart, no values will be
		// drawn
		mChart.setMaxVisibleValueCount(60);

		// scaling can now only be done on x- and y-axis separately
		mChart.setPinchZoom(false);

		mChart.setDrawBarShadow(false);
		mChart.setDrawGridBackground(false);

		XAxis xAxis = mChart.getXAxis();
		xAxis.setPosition(XAxisPosition.BOTTOM);
		xAxis.setTextColor(Color.WHITE);
		xAxis.setSpaceBetweenLabels(0);
		xAxis.setDrawGridLines(false);
		
		YAxis leftAxis = mChart.getAxisLeft();
		leftAxis.setTextColor(Color.WHITE);
		mChart.getAxisRight().setEnabled(false);
		
		mChart.getAxisLeft().setDrawGridLines(false);

		ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();

		for (int i = 0; i < 7; i++) {
			yVals1.add(new BarEntry(Integer.parseInt(weekpmlist.get(i).getAQI()), i));
		}

		ArrayList<String> xVals = new ArrayList<String>();

		for (int i = weekpmlist.size() - 1; i > weekpmlist.size() - 8; i--) {
			xVals.add(weekpmlist.get(i).getDate().substring(5));
		}

		BarDataSet set1 = new BarDataSet(yVals1, "Data Set");
		set1.setColors(ColorTemplate.VORDIPLOM_COLORS);
		set1.setDrawValues(false);

		ArrayList<BarDataSet> dataSets = new ArrayList<BarDataSet>();
		dataSets.add(set1);

		BarData data = new BarData(xVals, dataSets);

		mChart.setData(data);
		mChart.invalidate();

		// add a nice and smooth animation
		mChart.animateY(2500);

		mChart.getLegend().setEnabled(false);
		

	}

	private void setPMData(PMEntity.Result entity, String pm25) {
		txtTitle.setText(entity.getCity());
		txt_pm_state.setText(PMTools.getQualityStr(Integer.parseInt(entity
				.getAQI())));
		txt_pm_state.setBackgroundColor(PMTools.getQualityBackground(Integer
				.parseInt(entity.getAQI())));
		txt_pm_value.setText(entity.getAQI());
		lbl_pm25.setText(pm25);
		lbl_pm10.setText(entity.getPM10());
		lbl_SO.setText(entity.getSO2());
		lbl_NO.setText(entity.getNO2());
	}

	private void setAirData(CityAirEntity.PM entity,
			List<CityAirEntity.PM> pmlist, List<CityAirEntity.Moni> monilist) {
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
								// {"19":{"date":"2015-03-13","quality":"轻度污染","AQI":"145","city":"苏州"},"17":{"date":"2015-03-11","quality":"良","AQI":"97","city":"苏州"},"18":{"date":"2015-03-12","quality":"中度污染","AQI":"187","city":"苏州"},"15":{"date":"2015-03-09","quality":"良","AQI":"94","city":"苏州"},"16":{"date":"2015-03-10","quality":"良","AQI":"55","city":"苏州"},"13":{"date":"2015-03-07","quality":"轻度污染","AQI":"109","city":"苏州"},"14":{"date":"2015-03-08","quality":"良","AQI":"67","city":"苏州"},"11":{"date":"2015-03-05","quality":"良","AQI":"56","city":"苏州"},"12":{"date":"2015-03-06","quality":"良","AQI":"61","city":"苏州"},"21":{"date":"2015-03-15","quality":"良","AQI":"97","city":"苏州"},"20":{"date":"2015-03-14","quality":"轻度污染","AQI":"123","city":"苏州"},"22":{"date":"2015-03-16","quality":"良","AQI":"60","city":"苏州"},"23":{"date":"2015-03-17","quality":"良","AQI":"84","city":"苏州"},"24":{"date":"2015-03-18","quality":"良","AQI":"58","city":"苏州"},"25":{"date":"2015-03-19","quality":"良","AQI":"97","city":"苏州"},"26":{"date":"2015-03-20","quality":"良","AQI":"96","city":"苏州"},"27":{"date":"2015-03-21","quality":"轻度污染","AQI":"109","city":"苏州"},"28":{"date":"2015-03-22","quality":"良","AQI":"84","city":"苏州"},"3":{"date":"2015-02-25","quality":"良","AQI":"54","city":"苏州"},"2":{"date":"2015-02-24","quality":"良","AQI":"84","city":"苏州"},"10":{"date":"2015-03-04","quality":"优","AQI":"50","city":"苏州"},"1":{"date":"2015-02-23","quality":"良","AQI":"92","city":"苏州"},"7":{"date":"2015-03-01","quality":"轻度污染","AQI":"126","city":"苏州"},"6":{"date":"2015-02-28","quality":"良","AQI":"69","city":"苏州"},"5":{"date":"2015-02-27","quality":"优","AQI":"34","city":"苏州"},"4":{"date":"2015-02-26","quality":"良","AQI":"58","city":"苏州"},"9":{"date":"2015-03-03","quality":"良","AQI":"84","city":"苏州"},"8":{"date":"2015-03-02","quality":"轻度污染","AQI":"128","city":"苏州"}}
								List<CityAirEntity.PM> weekpmlist = new ArrayList<CityAirEntity.PM>();
								for (int i = 1; i < 31; i++) {
									try {
										String d = lastTwoWeeks.getJSONObject(
												i + "").toString();
										CityAirEntity.PM pm = new Gson()
												.fromJson(d,
														CityAirEntity.PM.class);
										weekpmlist.add(pm);
									} catch (Exception e) {
										continue;
									}

								}
								if (weekpmlist.size() != 0) {
									showPmChart(weekpmlist);
								}

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

								setAirData(entity.getResult().get(0)
										.getCitynow(), pmlist, monilist);
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
		((ImageView) findViewById(R.id.img_bg)).setBackground(Config.getBgDrawable());
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
		mChart = ((BarChart) pmheaderview.findViewById(R.id.chartbarpm));
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
