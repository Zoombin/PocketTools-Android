package com.juhe.pockettools.oil;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.GeofenceClient;
import com.google.gson.Gson;
import com.juhe.pockettools.HelprApplication;
import com.juhe.pockettools.R;
import com.juhe.pockettools.commonview.TopActiveBarView;
import com.juhe.pockettools.commonview.TopActiveBarView.InterfaceTopActiveBar;
import com.juhe.pockettools.home.FullscreenActivity;
import com.juhe.pockettools.utils.Config;
import com.thinkland.sdk.android.DataCallBack;
import com.thinkland.sdk.android.JuheData;
import com.thinkland.sdk.android.Parameters;
//import com.fotoable.helpr.wallpaper.w;

public class OilMainActivity extends FullscreenActivity {
	private static final String TAG = "OilMainActivity";
	private ListView oil_listveiw;
	private OilListViewAdapter adapter = null;
	private TopActiveBarView action_bar;
	private MyLocationListener mMyLocationListener;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.activity_oil_main);
		((ImageView) findViewById(R.id.img_bg)).setBackgroundColor(getResources().getColor(Config.getColor()));
		oil_listveiw = ((ListView) findViewById(R.id.oil_listveiw));
		action_bar = ((TopActiveBarView) findViewById(R.id.action_bar));
		adapter = new OilListViewAdapter(this);
		oil_listveiw.setAdapter(adapter);
		action_bar.setProgressVisiable(View.VISIBLE);
		action_bar.setTiltleText("附近油价");
		action_bar.setListener(new InterfaceTopActiveBar() {
			
			@Override
			public void query() {
				
			}
			
			@Override
			public void cancel() {
				finish();
			}
		});

		mMyLocationListener = new MyLocationListener();
		HelprApplication.getInstance().mLocationClient.registerLocationListener(mMyLocationListener);
		HelprApplication.getInstance().mGeofenceClient = new GeofenceClient(getApplicationContext());
		HelprApplication.getInstance().mLocationClient.start();
	}
	
	private String lon = "0.0";
	private String lat = "0.0";
	
	/**
	 * 实现实位回调监听
	 */
	public class MyLocationListener implements BDLocationListener {

		@Override
		public void onReceiveLocation(BDLocation location) {
			if (location != null) {
				// 百度定位命令反了
				lon = Double.toString(location.getLongitude());
				lat = Double.toString(location.getLatitude());
				getData();
			}
		}
	}
	
	private void getData() {
		Parameters params = new Parameters();
		params.add("lon", lon);
		params.add("lat", lat);
		params.add("key", "d6733f4e3f60dbd00de2cfb6feb6e28d");
		JuheData.executeWithAPI(7, "http://apis.juhe.cn/oil/local",
				JuheData.GET, params, new DataCallBack() {

					@Override
					public void resultLoaded(int err, String reason,
							String result) {

						action_bar.setProgressVisiable(View.INVISIBLE);
						if (err == 0) {
							try {
								OilEntity entity = new Gson().fromJson(result,
										OilEntity.class);
								if (entity.getError_code() != 0
										&& entity.getError_code() != 200) {
									Toast.makeText(getApplicationContext(),
											entity.getReason(),
											Toast.LENGTH_SHORT).show();
									return;
								}
								
								adapter.setData(entity.getResult().getData());
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
