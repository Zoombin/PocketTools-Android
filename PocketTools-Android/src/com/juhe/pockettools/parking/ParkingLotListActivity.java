package com.juhe.pockettools.parking;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.juhe.pockettools.R;
import com.juhe.pockettools.utils.Config;
import com.thinkland.sdk.android.DataCallBack;
import com.thinkland.sdk.android.JuheData;
import com.thinkland.sdk.android.Parameters;

public class ParkingLotListActivity extends Activity {

	private static final String TAG = "ParkingLotListActivity";

	private Context mContext;
	private PullToRefreshListView mPullRefreshListView;
	private ListView lv;
	private List<ParkingLot> parkingLots;
	private ParkingLotListAdapter adapter;
	private int total;
	private int pageNum = 0;
	private final int LIMIT = 20;
	private final int DEFAULT_DISTANCE = 2000;
	private int fl, lx;
	private String kw, qycs;
	private TextView tvTitle;
	private ImageView ivBack;
	private ProgressBar loadProgressBar;
	private double lat, lon;

	private int type;

	// private LocationClient mLocationClient;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_parkinglot_list);
		mContext = this;
		((ImageView) findViewById(R.id.img_bg)).setBackground(Config
				.getBgDrawable());
		tvTitle = (TextView) findViewById(R.id.tv_title);
		tvTitle.setText(R.string.real_time_parking_lot);
		loadProgressBar = (ProgressBar) findViewById(R.id.loading_progressBar);
		ivBack = (ImageView) findViewById(R.id.iv_title_back);
		ivBack.setVisibility(View.VISIBLE);
		ivBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});

		mPullRefreshListView = (PullToRefreshListView) findViewById(R.id.lv_parkinglot);
		mPullRefreshListView.setMode(Mode.PULL_FROM_END);
		mPullRefreshListView
				.setOnRefreshListener(new OnRefreshListener<ListView>() {

					@Override
					public void onRefresh(
							PullToRefreshBase<ListView> refreshView) {
						// TODO Auto-generated method stub
						if (type == 0) {
							getCityData(fl, lx, qycs, kw);
						} else if (type == 1) {
							getLocData(lat, lon);
						}
					}
				});
		lv = mPullRefreshListView.getRefreshableView();
		parkingLots = new ArrayList<ParkingLot>();
		adapter = new ParkingLotListAdapter(mContext, parkingLots);
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(mContext, ParkingLotActivity.class);
				intent.putExtra("parkingLot", parkingLots.get(position - 1));
				startActivity(intent);
			}
		});

		Intent intent = getIntent();
		type = intent.getIntExtra("type", -1);// 0城市,1周边
		if (type == 0) {
			fl = intent.getIntExtra("ccfl", 0);
			lx = intent.getIntExtra("cclx", 0);
			kw = intent.getStringExtra("kw");
			qycs = intent.getStringExtra("qycs");
			getCityData(fl, lx, qycs, kw);
		} else if (type == 1) {
			lat = intent.getDoubleExtra("lat", 0);
			lon = intent.getDoubleExtra("lon", 0);
			getLocData(lat, lon);
		}
		// getLocation();

	}

	// private void getLocation() {
	// mLocationClient = new LocationClient(getApplicationContext());
	// LocationClientOption option = new LocationClientOption();
	// option.setLocationMode(LocationMode.Hight_Accuracy);// 高精度;
	// option.setCoorType("bd09ll"); // 返回国测局经纬度坐标系：gcj02 返回百度墨卡托坐标系 ：bd09
	// option.setScanSpan(0);// 设置扫描间隔，单位毫秒，当<1000(1s)时，定时定位无效
	// // option.setIsNeedAddress(true);// 设置是否需要地址信息，默认为无地址
	// // option.setNeedDeviceDirect(true);// 在网络定位时，是否需要设备方向
	// mLocationClient.setLocOption(option);
	// mLocationClient.start();
	// mLocationClient.registerLocationListener(new BDLocationListener() {
	//
	// @Override
	// public void onReceiveLocation(BDLocation location) {
	// // TODO Auto-generated method stub
	// if (location != null) {
	// getData(fl, lx, kw, location.getLatitude(),
	// location.getLongitude(), distance);
	// mLocationClient.stop();
	// } else {
	// mLocationClient.requestLocation();
	// }
	// }
	// });
	// }

	private void getCityData(int ccfl, int cclx, String qycs, String kw) {
		getData(ccfl, cclx, qycs, kw, 0, 0, 0);
	}

	private void getLocData(double lat, double lon) {
		getData(0, 0, null, null, lat, lon, DEFAULT_DISTANCE);
	}

	private void getData(int ccfl, int cclx, String qycs, String kw,
			double lat, double lon, int distance) {
		Parameters params = new Parameters();
		if (ccfl != 0) {
			params.add("ccfl", ccfl);
		}
		if (cclx != 0) {
			params.add("cclx", cclx);
		}
		if (qycs != null) {
			params.add("qycs", qycs);
		}
		if (kw != null) {
			params.add("kw", kw);
		}
		if (lat != 0) {

			params.add("lat", lat);
		}
		if (lon != 0) {
			params.add("lon", lon);
		}
		params.add("wlyc", "true");
		params.add("limit", LIMIT);
		params.add("skip", pageNum * LIMIT);
		if (distance != 0) {
			params.add("distance", distance);
		}
		JuheData.executeWithAPI(133, "http://api2.juheapi.com/park/query",
				JuheData.GET, params, new DataCallBack() {

					@Override
					public void resultLoaded(int arg0, String arg1, String arg2) {
						// TODO Auto-generated method stub
						loadProgressBar.setVisibility(View.GONE);
						if (arg0 == 0) {
							List<ParkingLot> list = new ArrayList<ParkingLot>();
							try {
								JSONObject json = new JSONObject(arg2);
								total = json.getInt("total");
								JSONArray array = json.getJSONArray("result");
								for (int i = 0; i < array.length(); i++) {
									JSONObject parkingLotJSON = array
											.getJSONObject(i);
									ParkingLot parkingLot = new ParkingLot();
									parkingLot.setCCID(parkingLotJSON
											.getInt("CCID"));
									parkingLot.setCCMC(parkingLotJSON
											.getString("CCMC"));
									parkingLot.setCCDZ(parkingLotJSON
											.getString("CCDZ"));
									parkingLot.setCCTP(parkingLotJSON
											.getString("CCTP"));
									parkingLot.setBTTCJG(parkingLotJSON
											.getString("BTTCJG"));
									parkingLot.setWSTCJG(parkingLotJSON
											.getString("WSTCJG"));
									parkingLot.setYYKSSJ(parkingLotJSON
											.getString("YYKSSJ"));
									parkingLot.setYYJSSJ(parkingLotJSON
											.getString("YYJSSJ"));
									parkingLot.setCCFL(parkingLotJSON
											.getInt("CCFL"));
									parkingLot.setCCLX(parkingLotJSON
											.getInt("CCLX"));
									parkingLot.setSFKF(parkingLotJSON
											.getInt("SFKF"));
									parkingLot.setJCSFA(parkingLotJSON
											.getString("JCSFA"));
									parkingLot.setJCSFB(parkingLotJSON
											.getString("JCSFB"));
									parkingLot.setKCW(parkingLotJSON
											.getInt("KCW"));
									parkingLot.setZCW(parkingLotJSON
											.getInt("ZCW"));
									JSONObject locJson = parkingLotJSON
											.getJSONObject("LOC");
									parkingLot.setLat(locJson.getDouble("lat"));
									parkingLot.setLon(locJson.getDouble("lon"));
									parkingLot.setQYCS(parkingLotJSON
											.getString("QYCS"));
									// 出入口
									JSONArray entranceArray = parkingLotJSON
											.optJSONArray("CRK");
									if (entranceArray != null) {
										ArrayList<ParkingEntrance> entranceList = new ArrayList<ParkingEntrance>();

										for (int j = 0; j < entranceArray
												.length(); j++) {
											JSONObject enJson = entranceArray
													.getJSONObject(j);
											ParkingEntrance entrance = new ParkingEntrance();
											entrance.setFX(enJson.getInt("FX"));
											entrance.setLX(enJson.getInt("LX"));
											JSONObject entranceLocJson = enJson
													.getJSONObject("LOC");
											entrance.setLat(entranceLocJson
													.getDouble("lat"));
											entrance.setLon(entranceLocJson
													.getDouble("lon"));
											entranceList.add(entrance);
										}
										parkingLot
												.setEntranceList(entranceList);
									}
									// 预测
									JSONArray ycArray = parkingLotJSON
											.optJSONArray("WLYC");
									if (ycArray != null) {
										ArrayList<ParkingYC> ycList = new ArrayList<ParkingYC>();
										for (int j = 0; j < ycArray.length(); j++) {
											JSONObject ycJson = ycArray
													.getJSONObject(j);
											ParkingYC yc = new ParkingYC();
											yc.setKCWZT(ycJson.getInt("KCWZT"));
											yc.setYCSJ(ycJson.getString("YCSJ"));
											ycList.add(yc);
										}
										parkingLot.setYcList(ycList);
									}
									list.add(parkingLot);
								}
								parkingLots.addAll(list);
								adapter.notifyDataSetChanged();
								mPullRefreshListView.onRefreshComplete();
								pageNum++;
								if (pageNum * LIMIT > total) {
									mPullRefreshListView.setMode(Mode.DISABLED);
								}
								Toast.makeText(mContext, "第" + pageNum + "页",
										Toast.LENGTH_SHORT).show();
								
								Log.v(TAG, "size:" + list.size());
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				});

	}

}
