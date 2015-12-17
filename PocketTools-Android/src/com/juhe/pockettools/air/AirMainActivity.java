package com.juhe.pockettools.air;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zoombin.koudai.R;
import com.juhe.pockettools.commonview.TopActiveBarView;
import com.juhe.pockettools.commonview.TopActiveBarView.InterfaceTopActiveBar;
import com.juhe.pockettools.home.FullscreenActivity;
import com.juhe.pockettools.utils.Config;
import com.thinkland.sdk.android.DataCallBack;
import com.thinkland.sdk.android.JuheData;
import com.thinkland.sdk.android.Parameters;

public class AirMainActivity extends FullscreenActivity {

	private ImageView img_bg;
	private EditText air_edit_start;
	private EditText air_edit_end;
	private Button air_btn_search;
	private ListView air_list;
	private AirAdapter adapter;
	private TopActiveBarView action_bar;
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.activity_air_main);
		
		action_bar = (TopActiveBarView) findViewById(R.id.action_bar);
		action_bar.setTiltleText("航班查询");
		
		action_bar.setListener(new InterfaceTopActiveBar() {

			@Override
			public void cancel() {
				finish();
			}

			@Override
			public void query() {

			}
		});
		
		img_bg = (ImageView) findViewById(R.id.img_bg);
		img_bg.setBackgroundColor(getResources().getColor(Config.getColor()));
		air_edit_start = (EditText) findViewById(R.id.air_edit_start);
		air_edit_end = (EditText) findViewById(R.id.air_edit_end);
		air_list = (ListView) findViewById(R.id.air_list); 
		air_btn_search = (Button) findViewById(R.id.air_btn_search);
		air_btn_search.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				getData();
			}
		});
		adapter = new AirAdapter(this);
		air_list.setAdapter(adapter);
	}
	
	private String start;
	private String end;
	
	private void getData() {
		action_bar.setProgressVisiable(View.VISIBLE);
		
		Parameters params = new Parameters();
		start = air_edit_start.getText().toString();
		end = air_edit_end.getText().toString();
		if (TextUtils.isEmpty(start) || TextUtils.isEmpty(end)) {
			return;
		}
		params.add("start", start);
		params.add("end", end);
		params.add("key", "f546d38da5136ac53f67b4056e3f2a1c");
		JuheData.executeWithAPI(20, "http://apis.juhe.cn/plan/bc",
				JuheData.GET, params, new DataCallBack() {
					
					@Override
					public void resultLoaded(int err, String reason,
							String result) {

						action_bar.setProgressVisiable(View.INVISIBLE);
						
						if (err == 0) {
							AirEntity entity = new Gson().fromJson(result,
									AirEntity.class);
							if (entity.getError_code() != 0
									&& entity.getError_code() != 200) {
								Toast.makeText(AirMainActivity.this, entity.getReason(),
										Toast.LENGTH_SHORT).show();
								return;
							}
							
							adapter.setData(entity.getResult());
						} else {
							Toast.makeText(AirMainActivity.this, reason, Toast.LENGTH_SHORT)
									.show();
						}
					}
				});
	}
}
