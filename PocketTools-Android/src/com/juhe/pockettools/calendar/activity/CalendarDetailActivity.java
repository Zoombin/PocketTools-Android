package com.juhe.pockettools.calendar.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zoombin.koudai.R;
import com.juhe.pockettools.applesn.AppleSnEntity;
import com.juhe.pockettools.commonview.TopActiveBarView;
import com.juhe.pockettools.commonview.TopActiveBarView.InterfaceTopActiveBar;
import com.thinkland.sdk.android.DataCallBack;
import com.thinkland.sdk.android.JuheData;
import com.thinkland.sdk.android.Parameters;

public class CalendarDetailActivity extends Activity {
	private TopActiveBarView action_bar;
	private String scheduleDate;
	private TextView tv_calendar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calendar_detail);
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
		
		tv_calendar = (TextView) findViewById(R.id.tv_calendar);
		scheduleDate = getIntent().getStringExtra("scheduleDate");
		action_bar.setTiltleText(scheduleDate);
		
		action_bar.setProgressVisiable(View.VISIBLE);
		
		Parameters params = new Parameters();
		params.add("date", scheduleDate);
		params.add("key", "63487bc72b886ddd01a04de68559b3b9");
		JuheData.executeWithAPI(65,
				"http://v.juhe.cn/laohuangli/d",
				JuheData.GET, params, new DataCallBack() {

					@Override
					public void resultLoaded(int err, String reason,
							String result) {
						action_bar.setProgressVisiable(View.INVISIBLE);
						
						if (err == 0) {
							CalendarEntity entity = new Gson().fromJson(result, CalendarEntity.class);
							if (entity.getError_code() != 0 && entity.getError_code() != 200) {
								Toast.makeText(getApplicationContext(), entity.getReason(),
										Toast.LENGTH_SHORT).show();
								finish();
								return;
							}
							CalendarEntity.Result r = entity.getResult();
							String yingli = "阴历：" + r.getYinli() + "\n";
							String wuxing = "五行：" + r.getWuxing() + "\n";
							String chongsha = "冲杀：" + r.getChongsha() + "\n";
							String baiji = "拜祭：" + r.getBaiji() + "\n";
							String jishen = "忌神：" + r.getJishen() + "\n";
							String yi = "宜：" + r.getYi() + "\n";
							String xiongshen = "凶神：" + r.getXiongshen() + "\n";
							String ji = "忌：" + r.getJi() + "\n";
							tv_calendar.setText(yingli + wuxing + chongsha + baiji + jishen + yi + xiongshen + ji);
						} else {
							Toast.makeText(getApplicationContext(), reason,
									Toast.LENGTH_SHORT).show();
							finish();
						}
					}
				});
	}
}
