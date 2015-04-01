package com.juhe.pockettools.parking;

import java.util.List;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.juhe.pockettools.R;
import com.juhe.pockettools.home.FullscreenActivity;
import com.juhe.pockettools.utils.Config;

public class ParkingYCActivity extends FullscreenActivity {

	private Context context;
	private ListView lv;
	private TextView tvTitle;
	private ImageView ivBack;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_parkingyc_list);
		context = this;
		((ImageView) findViewById(R.id.img_bg)).setBackground(Config
				.getBgDrawable());
		tvTitle = (TextView) findViewById(R.id.tv_title);
		tvTitle.setText(R.string.parking_lot_yc);

		ivBack = (ImageView) findViewById(R.id.iv_title_back);
		ivBack.setVisibility(View.VISIBLE);
		ivBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		lv = (ListView) findViewById(R.id.lv_parkingyc);
		List<ParkingYC> list = getIntent().getParcelableArrayListExtra(
				"parkingYC");
		ParkingYCAdapter adapter = new ParkingYCAdapter(context, list);
		lv.setAdapter(adapter);
	}

}
