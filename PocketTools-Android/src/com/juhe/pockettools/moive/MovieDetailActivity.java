package com.juhe.pockettools.moive;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.juhe.pockettools.R;
import com.juhe.pockettools.applesn.AppleSnEntity;
import com.juhe.pockettools.commonview.TopActiveBarView;
import com.juhe.pockettools.commonview.TopActiveBarView.InterfaceTopActiveBar;
import com.thinkland.sdk.android.DataCallBack;
import com.thinkland.sdk.android.JuheData;
import com.thinkland.sdk.android.Parameters;

public class MovieDetailActivity extends Activity {
	private TopActiveBarView action_bar;
	private OnlineEntity.Result onlineentity;
	private CinemaEntity.Result cinemaentity;
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
		onlineentity = (OnlineEntity.Result) getIntent().getSerializableExtra(
				"onlineentity");
		if (onlineentity != null) {
			action_bar.setTiltleText(onlineentity.getName());
			tv_calendar.setText("总场次：" + onlineentity.getTotals() + "\n"
					+ "统计场次：" + onlineentity.getStatistics() + "\n" + "场均："
					+ onlineentity.getAveraging() + "\n" + "上座率："
					+ onlineentity.getAttendance() + "\n" + "人次："
					+ onlineentity.getPeople() + "\n" + "票价（元）："
					+ onlineentity.getFare() + "\n" + "票房（元）："
					+ onlineentity.getBoxoffice());
		} else {
			cinemaentity = (CinemaEntity.Result) getIntent()
					.getSerializableExtra("cinemaentity");
			action_bar.setTiltleText(cinemaentity.getName());
			tv_calendar.setText("榜单周数：" + cinemaentity.getWk() + "\n" + "周末票房："
					+ cinemaentity.getWboxoffice() + "\n" + "累计票房："
					+ cinemaentity.getTboxoffice());
		}

		action_bar.setProgressVisiable(View.INVISIBLE);
	}
}
