package com.juhe.pockettools.train;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.zoombin.koudai.R;
import com.juhe.pockettools.home.FullscreenActivity;
import com.juhe.pockettools.utils.Config;

//import com.fotoable.helpr.wallpaper.w;

public class TrainMainActivity extends FullscreenActivity {
	private TrainMainItem1View train_main_item1;
	private TrainMainItem2View train_main_item2;
	private Button bar_cancel;
	private Button btn_search_city;
	private Button btn_search_times;
	private TrainTimesResultView traintimesresultview;
	private FrameLayout progress_contaienr;

	private void closeResultView() {
		if (traintimesresultview != null) {
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
					// TODO Auto-generated method stub
					
				}
			});
			traintimesresultview.startAnimation(animation);
		}
	}

	public void onBackPressed() {
		if (traintimesresultview != null) {
			closeResultView();
			return;
		}
//		k.a(this);
		finish();
	}

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.activity_train_main);
		((ImageView) findViewById(R.id.img_bg)).setBackgroundColor(getResources().getColor(Config.getColor()));
		bar_cancel = ((Button) findViewById(R.id.bar_cancel));
		btn_search_city = ((Button) findViewById(R.id.btn_search_city));
		btn_search_times = ((Button) findViewById(R.id.btn_search_times));
		train_main_item1 = ((TrainMainItem1View) findViewById(R.id.train_main_item1));
		train_main_item2 = ((TrainMainItem2View) findViewById(R.id.train_main_item2));
		progress_contaienr = ((FrameLayout) findViewById(R.id.progress_contaienr));
		progress_contaienr.setVisibility(View.INVISIBLE);
		btn_search_city.setSelected(true);
		btn_search_times.setSelected(false);
		train_main_item1.setVisibility(View.VISIBLE);
		train_main_item2.setVisibility(View.INVISIBLE);
		bar_cancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		btn_search_city.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				train_main_item1.setVisibility(View.VISIBLE);
				train_main_item2.setVisibility(View.INVISIBLE);
				btn_search_city.setSelected(true);
				btn_search_times.setSelected(false);
			}
		});
		btn_search_times.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				train_main_item1.setVisibility(View.INVISIBLE);
				train_main_item2.setVisibility(View.VISIBLE);
				btn_search_city.setSelected(false);
				btn_search_times.setSelected(true);
			}
		});
//		train_main_item1.setListener(new e(this));
//		train_main_item2.setListener(new g(this));
	} 
}
