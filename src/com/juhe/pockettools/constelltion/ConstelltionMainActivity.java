package com.juhe.pockettools.constelltion;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;
import com.juhe.pockettools.R;
import com.juhe.pockettools.applesn.AppleSnEntity;
import com.juhe.pockettools.commonview.RightPublishTextView;
import com.juhe.pockettools.commonview.TopActiveBarView;
import com.juhe.pockettools.commonview.TopActiveBarView.InterfaceTopActiveBar;
import com.juhe.pockettools.home.FullscreenActivity;
//import com.fotoable.helpr.wallpaper.w;
import com.thinkland.sdk.android.DataCallBack;
import com.thinkland.sdk.android.JuheData;
import com.thinkland.sdk.android.Parameters;

public class ConstelltionMainActivity extends FullscreenActivity {
	private ConstelltionSelectDateView constelltion_date;
	private ConstelltionSelectView constelltionselectview;
	private ConstelltionDetailInfoView constelltion_detail_info;
	private ProgressBar waitbar;
	private TopActiveBarView action_bar;
	private ConstelltionBasicView constelltion_basicview;
	private RightPublishTextView constelltion_content;
	private LinearLayout detail_info_today;
	private LinearLayout detail_info_week;
	private int index = 0;
	private String type = "today";
//	private o n;

	private String getType(String date) {
		if (date.equals("今日")) {
			return "today";
		}
		if (date.equals("明日")) {
			return "tomorrow";
		}
		if (date.equals("本周")) {
			return "week";
		}
		if (date.equals("本月")) {
			return "month";
		}
		if (date.equals("今年")) {
			return "year";
		}
		return null;
	}

	private void showToday() {
		constelltion_content.setText("");
		detail_info_today.setVisibility(View.VISIBLE);
		detail_info_week.setVisibility(View.GONE);
		constelltion_content.setVisibility(View.VISIBLE);
		waitbar.setVisibility(View.VISIBLE);
	}

	private void showWeek() {
		constelltion_content.setText("");
		detail_info_today.setVisibility(View.GONE);
		detail_info_week.setVisibility(View.VISIBLE);
		waitbar.setVisibility(View.VISIBLE);
	}

	private void showSelectView() {
		if (constelltionselectview != null) {
			return;
		}
		ViewGroup localViewGroup = (ViewGroup) findViewById(android.R.id.content);
		constelltionselectview = new ConstelltionSelectView(this, null);
		constelltionselectview.setListener(new ConstelltionSelectView.OnSelectListener() {
			
			@Override
			public void setPosition(int paramInt) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void finish() {
				// TODO Auto-generated method stub
				
			}
		});
		localViewGroup.addView(constelltionselectview);
		Animation animation = AnimationUtils.loadAnimation(this,
				R.anim.fragment_slide_left_enter);
		constelltionselectview.setAnimation(animation);
	}

	private void closeSelectView() {
		if (constelltionselectview != null) {
			ViewGroup localViewGroup = (ViewGroup) findViewById(android.R.id.content);
			Animation animation = AnimationUtils.loadAnimation(this,
					R.anim.fragment_slide_right_exit);
//			animation.setAnimationListener(new n(this, localViewGroup));
			constelltionselectview.startAnimation(animation);
		}
	}

	@Override
	public void onBackPressed() {
		if (constelltionselectview != null) {
			closeSelectView();
			return;
		}
		setResult(RESULT_OK);
		finish();
	}

	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.activity_constelltion_main);
//		((ImageView) findViewById(R.id.img_bg)).setImageBitmap(w.a().d());
		action_bar = ((TopActiveBarView) findViewById(R.id.action_bar));
		constelltion_basicview = ((ConstelltionBasicView) findViewById(R.id.constelltion_basicview));
		constelltion_content = ((RightPublishTextView) findViewById(R.id.constelltion_content));
		constelltion_detail_info = ((ConstelltionDetailInfoView) findViewById(R.id.constelltion_detail_info));
		detail_info_today = ((LinearLayout) findViewById(R.id.detail_info_today));
		detail_info_week = ((LinearLayout) findViewById(R.id.detail_info_week));
		detail_info_week.setVisibility(View.GONE);
		waitbar = ((ProgressBar) findViewById(R.id.waitbar));
		constelltion_date = ((ConstelltionSelectDateView) findViewById(R.id.constelltion_date));
		constelltion_date.setListener(new ConstelltionSelectDateView.OnSelectListener() {
			
			@Override
			public void a(String paramString) {
				// TODO Auto-generated method stub
				
			}
		});
		action_bar.setTiltleText("所有星座");
		action_bar.setSureTextColor(Color.WHITE);
		action_bar.b();
		action_bar.a();
		action_bar.setSureText("所有星座");
		action_bar.setListener(new InterfaceTopActiveBar() {
			
			@Override
			public void query() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void cancel() {
				finish();
			}
		});
		Typeface localTypeface = Typeface.createFromAsset(getAssets(),
				"fonts/FZKTJT.ttf");
		constelltion_content.setTypeface(localTypeface);
		index = 0;
		constelltion_basicview.setSelectedConstelltion(index);
		waitbar.setVisibility(View.VISIBLE);
		
		initData();
	}
	
	private void initData() {
		Parameters params = new Parameters();
		params.add("consName", ConstalltionConstants.names[index]);
		params.add("type", type);
		params.add("key", "4d928c345f98533ccad7e86942bd36f8");
		JuheData.executeWithAPI(58,
				"http://web.juhe.cn:8080/constellation/getAll",
				JuheData.GET, params, new DataCallBack() {
	
					@Override
					public void resultLoaded(int err, String reason,
							String result) {

						if (err == 0) {
							ConstalltionEntity entity = new Gson().fromJson(result, ConstalltionEntity.class);
							if (entity.getError_code() != 0 && entity.getError_code() != 200) {
								Toast.makeText(getApplicationContext(), Integer.toString(entity.getError_code()),
										Toast.LENGTH_SHORT).show();
								return;
							}
							
							String date = entity.getDate();
						} else {
							Toast.makeText(getApplicationContext(), reason,
									Toast.LENGTH_SHORT).show();
						}
					}
				});
	}
}
