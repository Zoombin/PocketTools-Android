package com.juhe.pockettools.map;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.RelativeLayout.LayoutParams;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.utils.CoordinateConverter;
import com.baidu.mapapi.utils.CoordinateConverter.CoordType;
import com.zoombin.koudai.R;
import com.juhe.pockettools.home.FullscreenActivity;
import com.juhe.pockettools.map.MapCanstant;
import com.juhe.pockettools.parking.ParkingEntrance;

public class MarkerBDMapActivity extends FullscreenActivity {

	private Context mContext;

	private MapView mMapView = null;
	private BaiduMap mBaiduMap = null;

	private TextView tvTitle;
	private ImageView ivBack;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bd_map);
		mContext = this;
		initView();
		tvTitle = (TextView) findViewById(R.id.tv_title);
		tvTitle.setText("地图");
		ivBack = (ImageView) findViewById(R.id.iv_title_back);
		ivBack.setVisibility(View.VISIBLE);
		ivBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		Intent intent = getIntent();
		int markerType = intent.getIntExtra(MapCanstant.KEY_MARKER_TYPE, 0);
		switch (markerType) {
		case MapCanstant.PARKING_ADDR_LOC:
			double lat = intent.getDoubleExtra("lat", 0);
			double lon = intent.getDoubleExtra("lon", 0);
			setMarker(lat, lon);
			break;
		case MapCanstant.PARKING_ENTRANCE_LOC:
			ArrayList<ParkingEntrance> list = intent
					.getParcelableArrayListExtra("list");
			if (list != null) {
				setMarker(list);
			}
			break;
		default:
			break;
		}

	}

	private void initView() {
		mMapView = (MapView) findViewById(R.id.bmapView);
		mMapView.showScaleControl(false);
		mMapView.showZoomControls(false);
		mBaiduMap = mMapView.getMap();
	}

	private void setMarker(double lat, double lon) {
		LatLng gLatLng = new LatLng(lat, lon);
		CoordinateConverter converter = new CoordinateConverter();
		converter.from(CoordType.COMMON);
		converter.coord(gLatLng);
		LatLng point = converter.convert();
		MapStatusUpdate statusUpdate = MapStatusUpdateFactory
				.newMapStatus(new MapStatus.Builder().target(point).zoom(17)
						.build());
		mBaiduMap.animateMapStatus(statusUpdate);
		BitmapDescriptor bitmap = BitmapDescriptorFactory
				.fromResource(R.drawable.marker);
		OverlayOptions option = new MarkerOptions().position(point)
				.icon(bitmap);
		mBaiduMap.addOverlay(option);

	}

	public void setMarker(ArrayList<ParkingEntrance> list) {
		CoordinateConverter converter = new CoordinateConverter();
		converter.from(CoordType.COMMON);
		// View view =
		// LayoutInflater.from(mContext).inflate(R.layout.view_marker,
		// null);
		// TextView tv = (TextView) view.findViewById(R.id.tv_marker);

		for (int i = 0; i < list.size(); i++) {
			ParkingEntrance p = list.get(i);
			TextView tv = new TextView(mContext);
			tv.setGravity(Gravity.CENTER);
			tv.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT));
			tv.setTextColor(getResources().getColor(R.color.white));
			tv.setText(p.getFX() + p.getLXString());
			if (p.getLX() == 0) {
				tv.setBackgroundResource(R.drawable.marker_entrance);
			} else if (p.getLX() == 1) {
				tv.setBackgroundResource(R.drawable.marker_exit);
			} else if (p.getLX() == 2) {
				tv.setBackgroundResource(R.drawable.marker_entrance_exit);
			}
			BitmapDescriptor bitmap;
			bitmap = BitmapDescriptorFactory.fromView(tv);
			converter.coord(new LatLng(p.getLat(), p.getLon()));
			LatLng point = converter.convert();
			OverlayOptions option = new MarkerOptions().position(point).icon(
					bitmap);
			mBaiduMap.addOverlay(option);
			if (i == 0) {
				MapStatusUpdate statusUpdate = MapStatusUpdateFactory
						.newMapStatus(new MapStatus.Builder().target(point)
								.zoom(19).build());
				mBaiduMap.animateMapStatus(statusUpdate);
			}
		}
	}

	// private void setMarker(ArrayList<ParkingEntrance> list) {
	// CoordinateConverter converter = new CoordinateConverter();
	// converter.from(CoordType.COMMON);
	// View view = LayoutInflater.from(mContext).inflate(R.layout.view_marker,
	// null);
	// TextView tv = (TextView) view.findViewById(R.id.tv_marker);
	// for (int i = 0; i < list.size(); i++) {
	// ParkingEntrance p = list.get(i);
	// tv.setText(p.getFX()+p.getLXString());
	// if (p.getLX() == 0) {
	// tv.setBackgroundResource(R.drawable.marker_entrance);
	// } else if (p.getLX() == 1) {
	// tv.setBackgroundResource(R.drawable.marker_exit);
	// } else if (p.getLX() == 2) {
	// tv.setBackgroundResource(R.drawable.marker_entrance_exit);
	// }
	// BitmapDescriptor bitmap = BitmapDescriptorFactory.fromView(tv);
	// converter.coord(new LatLng(p.getLat(), p.getLon()));
	// LatLng point = converter.convert();
	// OverlayOptions option = new MarkerOptions().position(point).icon(
	// bitmap);
	// mBaiduMap.addOverlay(option);
	// if (i == 0) {
	// MapStatusUpdate statusUpdate = MapStatusUpdateFactory
	// .newMapStatus(new MapStatus.Builder().target(point)
	// .zoom(17).build());
	// mBaiduMap.animateMapStatus(statusUpdate);
	// }
	// }
	//
	// }

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		mMapView.onResume();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		mMapView.onPause();

	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		mMapView.onDestroy();
	}

}
