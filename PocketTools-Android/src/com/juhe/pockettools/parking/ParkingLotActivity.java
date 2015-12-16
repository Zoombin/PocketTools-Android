package com.juhe.pockettools.parking;

import java.util.ArrayList;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.juhe.pockettools.R;
import com.juhe.pockettools.home.FullscreenActivity;
import com.juhe.pockettools.map.MapCanstant;
import com.juhe.pockettools.map.MarkerBDMapActivity;
import com.juhe.pockettools.utils.Config;
import com.nostra13.universalimageloader.core.ImageLoader;

public class ParkingLotActivity extends FullscreenActivity implements
		OnClickListener {

	private Context mContext;

	private ImageView ivImage, ivBack;
	private TextView tvName, tvClass, tvType, tvAddress, tvEntrance,
			tvDayPrice, tvNightPrice, tvKCW, tvZCW, tvOpenTime, tvTitle;
	private Button btForecast;

	private ParkingLot parkingLot;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_parkinglot);
		mContext = this;
		parkingLot = getIntent().getParcelableExtra("parkingLot");
		initViews();
	}

	@SuppressLint("NewApi")
	private void initViews() {
		((ImageView) findViewById(R.id.img_bg)).setBackgroundColor(getResources().getColor(Config.getColor()));
		ivBack = (ImageView) findViewById(R.id.iv_title_back);
		ivBack.setVisibility(View.VISIBLE);
		ivBack.setOnClickListener(this);
		tvTitle = (TextView) findViewById(R.id.tv_title);
		tvTitle.setText(R.string.parking_lot_info);
		ivImage = (ImageView) findViewById(R.id.iv_image);
		tvName = (TextView) findViewById(R.id.tv_name);
		tvClass = (TextView) findViewById(R.id.tv_class);
		tvType = (TextView) findViewById(R.id.tv_type);
		tvAddress = (TextView) findViewById(R.id.tv_addr);
		tvAddress.setOnClickListener(this);
		tvEntrance = (TextView) findViewById(R.id.tv_entrance);
		tvEntrance.setOnClickListener(this);
		tvDayPrice = (TextView) findViewById(R.id.tv_price_day);
		tvNightPrice = (TextView) findViewById(R.id.tv_price_night);
		tvKCW = (TextView) findViewById(R.id.tv_kcw);
		tvZCW = (TextView) findViewById(R.id.tv_zcw);
		tvOpenTime = (TextView) findViewById(R.id.tv_open_time);
		btForecast = (Button) findViewById(R.id.bt_forecast);
		btForecast.setOnClickListener(this);
		ArrayList<ParkingYC> list = parkingLot.getYcList();
		if (list == null || list.size() == 0) {
			btForecast.setClickable(false);
		}
		ImageLoader.getInstance().displayImage(parkingLot.getCCTP(), ivImage);
		tvName.setText(parkingLot.getCCMC());
		tvClass.setText(parkingLot.getCCFL());
		tvType.setText(parkingLot.getCCLX());
		tvAddress.setText(parkingLot.getCCDZ());
		tvDayPrice.setText(parkingLot.getBTTCJG());
		tvNightPrice.setText(parkingLot.getWSTCJG());
		tvKCW.setText(parkingLot.getKCW() + "");
		tvZCW.setText(parkingLot.getZCW() + "");
		tvOpenTime.setText(parkingLot.getYYKSSJ() + " ~ "
				+ parkingLot.getYYJSSJ());

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.bt_forecast:

			ArrayList<ParkingYC> list = parkingLot.getYcList();
			if (list != null && list.size() > 0) {
				Intent intent = new Intent(mContext, ParkingYCActivity.class);
				intent.putParcelableArrayListExtra("parkingYC", list);
				startActivity(intent);
			}
			break;
		case R.id.iv_title_back:
			finish();
			break;

		case R.id.tv_addr:
			Intent addrIntent = new Intent(mContext, MarkerBDMapActivity.class);
			addrIntent.putExtra(MapCanstant.KEY_MARKER_TYPE,
					MapCanstant.PARKING_ADDR_LOC);
			addrIntent.putExtra("lat", parkingLot.getLat());
			addrIntent.putExtra("lon", parkingLot.getLon());
			startActivity(addrIntent);
			break;
		case R.id.tv_entrance:
			Intent entranceIntent = new Intent(mContext,
					MarkerBDMapActivity.class);
			entranceIntent.putExtra(MapCanstant.KEY_MARKER_TYPE,
					MapCanstant.PARKING_ENTRANCE_LOC);
			ArrayList<ParkingEntrance> entranceList = parkingLot
					.getEntranceList();
			if (entranceList != null && entranceList.size() > 0) {
				entranceIntent
						.putParcelableArrayListExtra("list", entranceList);
				startActivity(entranceIntent);
			}
			break;

		default:
			break;
		}
	}

}
