package com.juhe.pockettools.constelltion;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.google.gson.Gson;
import com.juhe.pockettools.R;
import com.juhe.pockettools.commonview.RightPublishTextView;
import com.juhe.pockettools.commonview.TopActiveBarView;
import com.juhe.pockettools.commonview.TopActiveBarView.InterfaceTopActiveBar;
import com.juhe.pockettools.home.FullscreenActivity;
import com.juhe.pockettools.utils.Config;
import com.thinkland.sdk.android.DataCallBack;
import com.thinkland.sdk.android.JuheData;
import com.thinkland.sdk.android.Parameters;
//import android.graphics.Bitmap.Config;
//import com.fotoable.helpr.wallpaper.w;

@SuppressLint("NewApi")
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
	private int consindex = 0;
	private int typeindex = 0;
	private String type = "today";

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

	private int getIndex(String date) {
		if (date.equals("今日")) {
			return 0;
		}
		if (date.equals("明日")) {
			return 1;
		}
		if (date.equals("本周")) {
			return 2;
		}
		if (date.equals("本月")) {
			return 3;
		}
		if (date.equals("今年")) {
			return 4;
		}
		return 0;
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
		if (constelltionselectview == null) {

			constelltionselectview = new ConstelltionSelectView(this, null);
			constelltionselectview
					.setListener(new ConstelltionSelectView.OnSelectListener() {

						@Override
						public void setPosition(int index) {
							consindex = index;
							closeSelectView();
						}

						@Override
						public void finish() {
							closeSelectView();
						}
					});
			ViewGroup viewgroup = (ViewGroup) findViewById(android.R.id.content);
			viewgroup.addView(constelltionselectview);
			Animation animation = AnimationUtils.loadAnimation(this,
					R.anim.fragment_slide_left_enter);
			constelltionselectview.setAnimation(animation);
		}

	}

	private void closeSelectView() {
		if (constelltionselectview != null) {
			Animation animation = AnimationUtils.loadAnimation(this,
					R.anim.fragment_slide_right_exit);
			animation.setAnimationListener(new AnimationListener() {

				@Override
				public void onAnimationStart(Animation arg0) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onAnimationRepeat(Animation arg0) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onAnimationEnd(Animation arg0) {
					ViewGroup viewgroup = (ViewGroup) findViewById(android.R.id.content);
					viewgroup.removeView(constelltionselectview);
					constelltionselectview = null;
					initData(typeindex);
				}
			});
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
		((ImageView) findViewById(R.id.img_bg)).setBackgroundColor(getResources().getColor(Config.getColor()));
		action_bar = ((TopActiveBarView) findViewById(R.id.action_bar));
		constelltion_basicview = ((ConstelltionBasicView) findViewById(R.id.constelltion_basicview));
		constelltion_content = ((RightPublishTextView) findViewById(R.id.constelltion_content));
		constelltion_detail_info = ((ConstelltionDetailInfoView) findViewById(R.id.constelltion_detail_info));
		detail_info_today = ((LinearLayout) findViewById(R.id.detail_info_today));
		detail_info_week = ((LinearLayout) findViewById(R.id.detail_info_week));
		detail_info_week.setVisibility(View.GONE);
		waitbar = ((ProgressBar) findViewById(R.id.waitbar));
		constelltion_date = ((ConstelltionSelectDateView) findViewById(R.id.constelltion_date));
		constelltion_date
				.setListener(new ConstelltionSelectDateView.OnSelectListener() {

					@Override
					public void setDateStr(String datestr) {
						typeindex = getIndex(datestr);
						type = getType(datestr);
						initData(typeindex);
					}
				});
		action_bar.setTiltleText("星座运势");
		action_bar.setSureTextColor(Color.WHITE);
		action_bar.b();
		action_bar.a();
		action_bar.setSureText("所有星座");
		action_bar.setListener(new InterfaceTopActiveBar() {

			@Override
			public void query() {
				showSelectView();
			}

			@Override
			public void cancel() {
				finish();
			}
		});
		Typeface typeface = Typeface.createFromAsset(getAssets(),
				"fonts/FZKTJT.ttf");
		constelltion_content.setTypeface(typeface);
		constelltion_basicview.setSelectedConstelltion(consindex);

		initData(typeindex);
	}

	private void initData(final int typeindex) {
		waitbar.setVisibility(View.VISIBLE);
		Parameters params = new Parameters();
		params.add("consName", ConstalltionConstants.names[consindex]);
		params.add("type", type);
		params.add("key", "4d928c345f98533ccad7e86942bd36f8");
		JuheData.executeWithAPI(58,
				"http://web.juhe.cn:8080/constellation/getAll", JuheData.GET,
				params, new DataCallBack() {

					@Override
					public void resultLoaded(int err, String reason,
							String result) {

						waitbar.setVisibility(View.GONE);
						if (err == 0) {
							if (typeindex == 0 || typeindex == 1) {
								ConstalltionDayEntity entity = new Gson()
										.fromJson(result,
												ConstalltionDayEntity.class);
								if (entity.getError_code() != 0
										&& entity.getError_code() != 200) {
									Toast.makeText(
											getApplicationContext(),
											Integer.toString(entity
													.getError_code()),
											Toast.LENGTH_SHORT).show();
									return;
								}

								
								detail_info_today.setVisibility(View.VISIBLE);
								detail_info_week.setVisibility(View.VISIBLE);
								constelltion_detail_info.setDayData(entity);
								constelltion_detail_info.disableTitleLayout();
								constelltion_basicview.setSelectedConstelltion(consindex);
								constelltion_basicview.setData(entity);
							} else if (typeindex == 2 || typeindex == 3) {
								ConstalltionWeekEntity entity = new Gson()
										.fromJson(result,
												ConstalltionWeekEntity.class);
								if (entity.getError_code() != 0
										&& entity.getError_code() != 200) {
									Toast.makeText(
											getApplicationContext(),
											Integer.toString(entity
													.getError_code()),
											Toast.LENGTH_SHORT).show();
									return;
								}
								
								detail_info_today.setVisibility(View.GONE);
								detail_info_week.setVisibility(View.VISIBLE);
								if (typeindex == 2) {
									constelltion_detail_info.setWeekData(entity);
								} else {
									constelltion_detail_info.setMonthData(entity);
								}
								
								constelltion_detail_info
										.setSelectedDetailConstelltion(consindex);
							} else {
								ConstalltionYearEntity entity = new Gson()
										.fromJson(result,
												ConstalltionYearEntity.class);
								if (entity.getError_code() != 0
										&& entity.getError_code() != 200) {
									Toast.makeText(
											getApplicationContext(),
											Integer.toString(entity
													.getError_code()),
											Toast.LENGTH_SHORT).show();
									return;
								}

								detail_info_today.setVisibility(View.GONE);
								detail_info_week.setVisibility(View.VISIBLE);
								constelltion_detail_info.setYearData(entity);
								constelltion_detail_info
										.setSelectedDetailConstelltion(consindex);
							}
						} else {
							Toast.makeText(getApplicationContext(), reason,
									Toast.LENGTH_SHORT).show();
						}
					}
				});
	}
}
