package com.juhe.pockettools.moive;

import java.util.List;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Im;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import com.google.gson.Gson;
import com.juhe.pockettools.R;
import com.juhe.pockettools.home.FullscreenActivity;
import com.juhe.pockettools.utils.Config;
import com.thinkland.sdk.android.DataCallBack;
import com.thinkland.sdk.android.JuheData;
import com.thinkland.sdk.android.Parameters;

public class MovieMainActivity extends FullscreenActivity {

	private static final String TAG = "MovieMainActivity";
	private Button bar_cancel;
	private FrameLayout progress_contaienr;
	private MovieOnlineView movie_online;
	private CinemaView movie_cinema;
	private ListView cinema_listveiw;
	private ListView movie_listveiw;
	private Button btn_search_cinema;
	private Button btn_search_moive;

	private Button btn_search_cn;
	private Button btn_search_us;
	private Button btn_search_hk;
	private String area = "CN";
	private CinemaAdapter cinemaadapter;
	private OnlineAdapter onlineadapter;
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		getWindow().setSoftInputMode(2);
		setContentView(R.layout.activity_movie_main);
		((ImageView) findViewById(R.id.img_bg)).setBackground(Config.getBgDrawable());
		bar_cancel = ((Button) findViewById(R.id.bar_cancel));
		progress_contaienr = ((FrameLayout) findViewById(R.id.progress_contaienr));
		bar_cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
		btn_search_cinema = ((Button) findViewById(R.id.btn_search_cinema));
		btn_search_cinema.setSelected(true);
		btn_search_moive = ((Button) findViewById(R.id.btn_search_moive));
		btn_search_cinema.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				onclick((Button) v);
			}
		});
		btn_search_moive.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				onclick((Button) v);
			}
		});
		movie_cinema = (CinemaView) findViewById(R.id.movie_cinema);
		cinema_listveiw = (ListView) movie_cinema
				.findViewById(R.id.cinema_listveiw);
		cinemaadapter = new CinemaAdapter(this);
		cinema_listveiw.setAdapter(cinemaadapter);
		
		btn_search_cn = (Button) movie_cinema
				.findViewById(R.id.btn_search_cn);
		btn_search_cn.setSelected(true);
		btn_search_us = (Button) movie_cinema
				.findViewById(R.id.btn_search_us);
		btn_search_hk = (Button) movie_cinema
				.findViewById(R.id.btn_search_hk);
		btn_search_cn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				onclicksub((Button)v);
			}
		});
		btn_search_us.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				onclicksub((Button)v);
			}
		});
		btn_search_hk.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				onclicksub((Button)v);
			}
		});
		
		movie_online = ((MovieOnlineView) findViewById(R.id.movie_online));
		movie_listveiw = ((ListView) movie_online
				.findViewById(R.id.movie_listveiw));
		onlineadapter = new OnlineAdapter(this);
		movie_listveiw.setAdapter(onlineadapter);
		
		getCinemaData();
	}

	private void onclick(Button b) {
		if (!b.isSelected()) {
			progress_contaienr.setVisibility(View.VISIBLE);
			if (b == btn_search_cinema) {
				btn_search_cinema.setSelected(true);
				btn_search_moive.setSelected(false);
				movie_cinema.setVisibility(View.VISIBLE);
				movie_online.setVisibility(View.GONE);
				getCinemaData();
			} else {
				btn_search_cinema.setSelected(false);
				btn_search_moive.setSelected(true);
				movie_cinema.setVisibility(View.GONE);
				movie_online.setVisibility(View.VISIBLE);
				getOnlineData();
			}
		}
	}
	
	private void onclicksub(Button b) {
		if (!b.isSelected()) {
			progress_contaienr.setVisibility(View.VISIBLE);
			if (b == btn_search_cn) {
				btn_search_cn.setSelected(true);
				btn_search_us.setSelected(false);
				btn_search_hk.setSelected(false);
				area = "CN";
			} else if (b == btn_search_us) {
				btn_search_cn.setSelected(false);
				btn_search_us.setSelected(true);
				btn_search_hk.setSelected(false);
				area = "US";
			} else {
				btn_search_cn.setSelected(false);
				btn_search_us.setSelected(false);
				btn_search_hk.setSelected(true);
				area = "HK";
			}
			getCinemaData();
		}
	}
	
	private void getCinemaData() {
		Parameters params = new Parameters();
		params.add("area", area);
		params.add("key", "77ec98fa45a52fc1385c29c80e841f9b");
		JuheData.executeWithAPI(44,
				"http://v.juhe.cn/boxoffice/rank",
				JuheData.GET, params, new DataCallBack() {

					@Override
					public void resultLoaded(int err, String reason,
							String result) {
						progress_contaienr.setVisibility(View.INVISIBLE);
						
						if (err == 0) {
							CinemaEntity entity = new Gson().fromJson(result, CinemaEntity.class);
							if (entity.getError_code() != 0 && entity.getError_code() != 200) {
								Toast.makeText(getApplicationContext(), entity.getReason(),
										Toast.LENGTH_SHORT).show();
								return;
							}
							List<CinemaEntity.Result> list = entity.getResult();
							cinemaadapter.setData(list);
						} else {
							Toast.makeText(getApplicationContext(), reason,
									Toast.LENGTH_SHORT).show();
						}
					}
				});
	}
	
	private void getOnlineData() {
		Parameters params = new Parameters();
		params.add("key", "77ec98fa45a52fc1385c29c80e841f9b");
		JuheData.executeWithAPI(44,
				"http://v.juhe.cn/boxoffice/wp",
				JuheData.GET, params, new DataCallBack() {

					@Override
					public void resultLoaded(int err, String reason,
							String result) {
						progress_contaienr.setVisibility(View.INVISIBLE);
						
						if (err == 0) {
							OnlineEntity entity = new Gson().fromJson(result, OnlineEntity.class);
							if (entity.getError_code() != 0 && entity.getError_code() != 200) {
								Toast.makeText(getApplicationContext(), entity.getReason(),
										Toast.LENGTH_SHORT).show();
								return;
							}
							List<OnlineEntity.Result> list = entity.getResult();
							onlineadapter.setData(list);
						} else {
							Toast.makeText(getApplicationContext(), reason,
									Toast.LENGTH_SHORT).show();
						}
					}
				});
	}
}
