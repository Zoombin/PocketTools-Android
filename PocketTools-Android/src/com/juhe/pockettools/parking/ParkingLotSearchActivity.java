package com.juhe.pockettools.parking;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TextView.OnEditorActionListener;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import com.juhe.pockettools.R;
import com.juhe.pockettools.home.FullscreenActivity;
import com.juhe.pockettools.utils.Config;
import com.thinkland.sdk.android.DataCallBack;
import com.thinkland.sdk.android.JuheData;

public class ParkingLotSearchActivity extends FullscreenActivity implements
		OnClickListener {

	private Context mContext;

	private LocationClient mLocationClient;

	private LinearLayout loadingLayout;
	private ProgressBar loadingProgressBar;
	private Spinner citySpinner, classSpinner, typeSpinner;
	private EditText etKeyWord;
	private Button btSearch;

	private TextView tvTitle, tvNearBy, tvReason;
	private ImageView ivBack;

	private int ccfl;
	private int cclx;
	private String[] classArr = { "全部", "占道停车场", "路外露天停车场", "非露天地上停车场",
			"非露天地下停车场" };

	private String[] typeArr = { "全部", "平面自走式", "机械式", "立体自动车库" };

	private ArrayList<String> cities;// = new ArrayList<String>();

	private String city;
	private String qycs;

	private final int CALLBACK_OK = 0x01;
	private final int CALLBACK_ERROR = 0x02;

	private double lat;
	private double lon;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_parkinglot_search);
		mContext = this;
		getCities();
		initViews();
	}

	@SuppressLint("NewApi")
	private void initViews() {

		((ImageView) findViewById(R.id.img_bg)).setBackground(Config
				.getBgDrawable());

		// title
		tvTitle = (TextView) findViewById(R.id.tv_title);
		tvTitle.setText(R.string.parking_lot);
		ivBack = (ImageView) findViewById(R.id.iv_title_back);
		ivBack.setVisibility(View.VISIBLE);
		ivBack.setOnClickListener(this);
		tvNearBy = (TextView) findViewById(R.id.tv_title_right);
		tvNearBy.setText(R.string.near_by);
		tvNearBy.setVisibility(View.VISIBLE);
		tvNearBy.setOnClickListener(this);

		// 界面
		tvReason = (TextView) findViewById(R.id.tv_reason);
		loadingLayout = (LinearLayout) findViewById(R.id.loading_layout);
		loadingProgressBar = (ProgressBar) findViewById(R.id.loading_progressBar);

		etKeyWord = (EditText) findViewById(R.id.et_keyword);
		etKeyWord.setOnEditorActionListener(new OnEditorActionListener() {

			@Override
			public boolean onEditorAction(TextView v, int actionId,
					KeyEvent event) {
				if (actionId == EditorInfo.IME_ACTION_SEARCH) {
					// 先隐藏键盘
					((InputMethodManager) etKeyWord.getContext()
							.getSystemService(Context.INPUT_METHOD_SERVICE))
							.hideSoftInputFromWindow(getCurrentFocus()
									.getWindowToken(),
									InputMethodManager.HIDE_NOT_ALWAYS);
					Intent intent = new Intent(mContext,
							ParkingLotListActivity.class);
					if (ccfl != 0) {
						intent.putExtra("ccfl", ccfl);
					}
					if (cclx != 0) {
						intent.putExtra("cclx", cclx);
					}
					String keyWord = etKeyWord.getText().toString();
					if (keyWord != null && !keyWord.equals("")) {
						intent.putExtra("kw", keyWord);
					}
					intent.putExtra("distance", 20);
					startActivity(intent);
					return true;
				}
				return false;
			}
		});
		citySpinner = (Spinner) findViewById(R.id.sp_city);
		classSpinner = (Spinner) findViewById(R.id.sp_class);
		typeSpinner = (Spinner) findViewById(R.id.sp_type);

		classSpinner.setAdapter(new ArrayAdapter<String>(mContext,
				R.layout.spinner, classArr) {

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				// TODO Auto-generated method stub
				if (convertView == null) {
					convertView = getLayoutInflater().inflate(
							R.layout.item_spinner, null);
				}
				TextView tvLabel = (TextView) convertView
						.findViewById(R.id.tv_label);
				tvLabel.setText(getItem(position));
				return convertView;
			}

		});

		classSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				ccfl = position;
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});

		typeSpinner.setAdapter(new ArrayAdapter<String>(mContext,
				R.layout.spinner, typeArr) {

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				// TODO Auto-generated method stub
				if (convertView == null) {
					convertView = getLayoutInflater().inflate(
							R.layout.item_spinner, null);
				}
				TextView tvLabel = (TextView) convertView
						.findViewById(R.id.tv_label);
				tvLabel.setText(getItem(position));
				return convertView;
			}

		});

		typeSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				cclx = position;
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});

		btSearch = (Button) findViewById(R.id.bt_search);
		btSearch.setOnClickListener(this);
	}

	private void getCities() {

		final CountDownLatch countDownLatch = new CountDownLatch(2);

		// 定位
		mLocationClient = new LocationClient(getApplicationContext());
		LocationClientOption option = new LocationClientOption();
		option.setLocationMode(LocationMode.Hight_Accuracy);// 高精度;
		option.setCoorType("bd09ll"); // 返回国测局经纬度坐标系：gcj02 返回百度墨卡托坐标系 ：bd09
		option.setScanSpan(0);// 设置扫描间隔，单位毫秒，当<1000(1s)时，定时定位无效
		// option.setIsNeedAddress(true);// 设置是否需要地址信息，默认为无地址
		// option.setNeedDeviceDirect(true);// 在网络定位时，是否需要设备方向
		mLocationClient.setLocOption(option);
		mLocationClient.start();
		mLocationClient.registerLocationListener(new BDLocationListener() {

			@Override
			public void onReceiveLocation(BDLocation location) {
				// TODO Auto-generated method stub
				if (location != null) {
					mLocationClient.stop();
					city = location.getCity();
					lat = location.getLatitude();
					lon = location.getLongitude();
					countDownLatch.countDown();
				} else {
					mLocationClient.requestLocation();
				}
			}
		});

		// 获取城市数据
		JuheData.executeWithAPI(133, "http://api2.juheapi.com/park/city",
				"GET", null, new DataCallBack() {

					@Override
					public void resultLoaded(int arg0, String arg1, String arg2) {
						// TODO Auto-generated method stub
						if (arg0 == 0) {
							try {
								JSONObject json = new JSONObject(arg2);
								JSONArray resultJson = json
										.getJSONArray("result");
								cities = new ArrayList<String>();
								for (int i = 0; i < resultJson.length(); i++) {
									JSONObject cityJson = resultJson
											.getJSONObject(i);
									cities.add(cityJson.getString("QYCS"));
								}

							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							countDownLatch.countDown();
						}
					}
				});

		new Thread() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					countDownLatch.await();
					mHandler.sendEmptyMessage(CALLBACK_OK);
				} catch (InterruptedException ex) {
					mHandler.sendEmptyMessage(CALLBACK_ERROR);
					return;
				}
			}

		}.start();
	}

	Handler mHandler = new Handler(new Handler.Callback() {

		@Override
		public boolean handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch (msg.what) {
			case CALLBACK_OK:
				showCityView();
				break;
			case CALLBACK_ERROR:
				Toast.makeText(getApplicationContext(), "loading error",
						Toast.LENGTH_SHORT).show();
				break;

			default:
				break;
			}
			return false;
		}
	});

	private void showCityView() {
		// if (city == null || city.equals("")) {
		// loadingProgressBar.setVisibility(View.GONE);
		// tvReason.setText("定位城市失败!");
		// return;
		// }

		if (cities == null || cities.size() == 0) {
			loadingProgressBar.setVisibility(View.GONE);
			tvReason.setText("获取城市列表失败!");
			return;
		}

		loadingLayout.setVisibility(View.GONE);
		citySpinner.setVisibility(View.VISIBLE);
		citySpinner.setAdapter(new ArrayAdapter<String>(mContext,
				R.layout.spinner, cities) {

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				// TODO Auto-generated method stub
				if (convertView == null) {
					convertView = getLayoutInflater().inflate(
							R.layout.item_spinner, null);
				}
				TextView tvLabel = (TextView) convertView
						.findViewById(R.id.tv_label);
				tvLabel.setText(getItem(position));
				return convertView;
			}

		});
		classSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				ccfl = position;
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});

		classSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				city = cities.get(position);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});
		if (city == null || city.equals("")) {
			citySpinner.setSelection(0, true);
			Toast.makeText(mContext, "定位城市失败.", Toast.LENGTH_SHORT).show();
		} else if (cities.contains(city)) {
			citySpinner.setSelection(cities.indexOf(city), true);
		} else {
			citySpinner.setSelection(0, true);
			Toast.makeText(mContext, "当前城市没有停车场数据.", Toast.LENGTH_SHORT).show();
		}
		btSearch.setEnabled(true);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.bt_search:
			Intent intent = new Intent(mContext, ParkingLotListActivity.class);
			intent.putExtra("type", 0);// 城市
			if (ccfl != 0) {
				intent.putExtra("ccfl", ccfl);
			}
			if (cclx != 0) {
				intent.putExtra("cclx", cclx);
			}
			if (city != null && !city.equals("")) {
				intent.putExtra("qycs", city);
			}
			String keyWord = etKeyWord.getText().toString();
			if (keyWord != null && !keyWord.equals("")) {
				intent.putExtra("kw", keyWord);
			}
			startActivity(intent);
			break;
		case R.id.iv_title_back:
			finish();
			break;
		case R.id.tv_title_right:
			Intent intentNearby = new Intent(mContext,
					ParkingLotListActivity.class);
			intentNearby.putExtra("type", 1);// 周边
			intentNearby.putExtra("lat", lat);
			intentNearby.putExtra("lon", lon);
			startActivity(intentNearby);
			break;
		default:
			break;
		}
	}
}
