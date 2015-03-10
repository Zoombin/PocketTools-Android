package com.juhe.pockettools.qrcode;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.juhe.pockettools.R;
import com.juhe.pockettools.applesn.AppleSnEntity;
import com.juhe.pockettools.commonview.TopActiveBarView;
import com.juhe.pockettools.commonview.TopActiveBarView.InterfaceTopActiveBar;
import com.juhe.pockettools.home.FullscreenActivity;
import com.thinkland.sdk.android.DataCallBack;
import com.thinkland.sdk.android.JuheData;
import com.thinkland.sdk.android.Parameters;

public class QRScannerActivity extends FullscreenActivity {

	private static final String TAG = "ZBarScannerActivity";

	private QRCodeGoodsInfoView view_goods_info;
	private QRCodeGoodsPriceListAdapter adapter;
	private QRCodeGoodsPriceListView listview;
	private TextView view_txt;
	private FrameLayout qrcode_result_container;
	private ImageView goods_init_state;
	private TopActiveBarView action_bar;
	private String barcode;

	@Override
	@SuppressLint("NewApi")
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		
		setContentView(R.layout.activity_qr_scan);
//		((ImageView) findViewById(R.id.img_bg)).setImageBitmap(w.a().d());
		view_goods_info = ((QRCodeGoodsInfoView) findViewById(R.id.view_goods_info));
		view_txt = ((TextView) findViewById(R.id.view_txt));
		action_bar = ((TopActiveBarView) findViewById(R.id.action_bar));
		action_bar.setListener(new InterfaceTopActiveBar() {
			
			@Override
			public void query() {
				
			}
			
			@Override
			public void cancel() {
				finish();
			}
		});
		action_bar.setTiltleText("扫码比价");
		listview = new QRCodeGoodsPriceListView(this);

		qrcode_result_container = ((FrameLayout) findViewById(R.id.qrcode_result_container));
		qrcode_result_container.requestLayout();
		
		adapter = new QRCodeGoodsPriceListAdapter(this);
		listview.setAdapter(adapter);
		
		barcode = getIntent().getStringExtra("barcode");
		getData();
	}
	
	private void getData() {
		Parameters params = new Parameters();
		params.add("barcode", barcode);
		params.add("cityid", 1);
		params.add("pkg", "com.koudai");
		JuheData.executeWithAPI(52,
				"http://api.juheapi.com/jhbar/bar",
				JuheData.GET, params, new DataCallBack() {

					@Override
					public void resultLoaded(int err, String reason,
							String result) {
						action_bar.setProgressVisiable(View.INVISIBLE);
						
						if (err == 0) {
							QRCodeEntity entity = new Gson().fromJson(result, QRCodeEntity.class);
							if (entity.getError_code() != 0 && entity.getError_code() != 200) {
								Toast.makeText(getApplicationContext(), entity.getReason(),
										Toast.LENGTH_SHORT).show();
								return;
							}
							
							view_goods_info.setGoodsInfo(entity.getResult().getSummary());
							adapter.setData(entity.getResult().getShop());
						} else {
							Toast.makeText(getApplicationContext(), reason,
									Toast.LENGTH_SHORT).show();
						}
					}
				});
	}
}
