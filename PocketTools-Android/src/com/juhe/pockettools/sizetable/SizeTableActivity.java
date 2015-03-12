package com.juhe.pockettools.sizetable;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.juhe.pockettools.R;
import com.juhe.pockettools.commonview.TopActiveBarView;
import com.juhe.pockettools.commonview.TopActiveBarView.InterfaceTopActiveBar;
import com.juhe.pockettools.home.FullscreenActivity;
import com.juhe.pockettools.utils.HelprCommUtil;

//import com.fotoable.helpr.wallpaper.w;

public class SizeTableActivity extends FullscreenActivity {
	private static final String TAG = "SizeTableAcitivity";
	TopActiveBarView action_bar;
	private LinearLayout ly_women_sizetype;
	private LinearLayout ly_men_sizetype;
	private LinearLayout ly_children_sizetype;
	private LinearLayout ly_bigtypes;
	private Button btn_mensize;
	private Button btn_womensize;
	private Button btn_childrensize;
	private Button btn_child_shoose;
	private ImageView imag_size;
	private Bitmap bitmap = null;
	private Handler handler = new Handler();
	private ScrollView scroll_pic;

	private Button getTypeButton(LinearLayout linearlayout) {
		for (int i = 0; i < linearlayout.getChildCount(); i++) {
			if (linearlayout == ly_children_sizetype) {
				return btn_child_shoose;
			}
			Button button = (Button) linearlayout.getChildAt(i);
			return button;
		}
		return null;
	}

	private void setImage(Bitmap bitmap) {
		if (bitmap == null) {
			return;
		}
		int widthPixels = getResources().getDisplayMetrics().widthPixels;
		int heightPixels = (int) (widthPixels / bitmap.getWidth() * bitmap
				.getHeight());
		Log.v("SizeTableAcitivity",
				"SizeTableAcitivity bitmap width: " + bitmap.getWidth()
						+ "bitmap height:" + bitmap.getHeight());
		Log.v("SizeTableAcitivity", "SizeTableAcitivity pic width: "
				+ widthPixels + "pic height:" + heightPixels);
		FrameLayout localFrameLayout = (FrameLayout) findViewById(R.id.ly_pic);
		ViewGroup.LayoutParams params = localFrameLayout
				.getLayoutParams();
		params.width = widthPixels;
		params.height = heightPixels;
		localFrameLayout.setLayoutParams(params);
		imag_size.setImageBitmap(null);
//		if ((bitmap != null) && (!bitmap.isRecycled())) {
//			bitmap.recycle();
//		}
		this.bitmap = bitmap;
		imag_size.setImageBitmap(bitmap);
		scroll_pic.scrollTo(0, 0);
	}

	private void showBigTypes(Button button) {
		unclickbutton(ly_bigtypes);
		button.setSelected(true);
		ly_women_sizetype.setVisibility(View.INVISIBLE);
		ly_men_sizetype.setVisibility(View.INVISIBLE);
		ly_children_sizetype.setVisibility(View.INVISIBLE);
		
		LinearLayout linearlayout;
		if (button == btn_womensize) {
			ly_women_sizetype.setVisibility(View.VISIBLE);
			linearlayout = ly_women_sizetype;
		} else if (button == btn_mensize) {
			ly_men_sizetype.setVisibility(View.VISIBLE);
			linearlayout = ly_men_sizetype;
		} else {
			ly_children_sizetype.setVisibility(View.VISIBLE);
			linearlayout = ly_children_sizetype;
		}
		unclickbutton(linearlayout);
		Button subbutton = getTypeButton(linearlayout);
		showTypeContent((Button) subbutton, 0);
	}

	private void showTypeContent(Button button, int delayMillis) {
		button.setSelected(true);
		String[] strings = new String[2];
		strings[0] = "sizes";
		strings[1] = ((String) button.getTag());
		final String str = String.format("%s/%s.png", strings);
		if ((str != null) && (str.length() > 0)) {
			handler.postDelayed(new Runnable() {

				@Override
				public void run() {
					final Bitmap bitmap = HelprCommUtil.getImage(str);
					if (bitmap != null) {
						SizeTableActivity.this.runOnUiThread(new Runnable() {
							
							@Override
							public void run() {
								setImage(bitmap);
							}
						});
					}
				}
			}, delayMillis);
		}
	}

	private void unclickbutton(LinearLayout linearlayout) {
		for (int i = 0; i < linearlayout.getChildCount(); i++) {
			Button button = (Button) linearlayout.getChildAt(i);
			button.setSelected(false);
		}
	}

	private void initBigTypes(LinearLayout linearlayout) {
		if (linearlayout != null) {
			for (int i = 0; i < linearlayout.getChildCount(); i++) {
				Button button = (Button) linearlayout.getChildAt(i);
				button.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						showBigTypes((Button) v);
					}
				});
			}
		}
	}

	private void initLinearLayout(LinearLayout linearlayout) {
		if (linearlayout != null) {
			for (int i = 0; i < linearlayout.getChildCount(); i++) {
				Button button = (Button) linearlayout.getChildAt(i);
				button.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						if (v.isSelected()) {
							return;
						}
						LinearLayout linearlayout = (LinearLayout) v.getParent();
						unclickbutton(linearlayout);
						v.setSelected(true);
						showTypeContent((Button) v, 0);
					}
				});
			}
		}
	}

	@Override
	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.activity_sizetable);
		// ((ImageView) findViewById(R.id.img_bg)).setImageBitmap(w.a().d());
		btn_womensize = ((Button) findViewById(R.id.btn_womensize));
		btn_mensize = ((Button) findViewById(R.id.btn_mensize));
		btn_childrensize = ((Button) findViewById(R.id.btn_childrensize));
		ly_women_sizetype = ((LinearLayout) findViewById(R.id.ly_women_sizetype));
		ly_men_sizetype = ((LinearLayout) findViewById(R.id.ly_men_sizetype));
		ly_children_sizetype = ((LinearLayout) findViewById(R.id.ly_children_sizetype));
		imag_size = ((ImageView) findViewById(R.id.imag_size));
		ly_bigtypes = ((LinearLayout) findViewById(R.id.ly_bigtypes));
		scroll_pic = ((ScrollView) findViewById(R.id.scroll_pic));
		btn_child_shoose = ((Button) findViewById(R.id.btn_child_shoose));
		initBigTypes(ly_bigtypes);
		initLinearLayout(ly_women_sizetype);
		initLinearLayout(ly_men_sizetype);
		initLinearLayout(ly_children_sizetype);
		action_bar = ((TopActiveBarView) findViewById(R.id.action_bar));
		action_bar.setTiltleText("尺码对照表");
		action_bar.setSplitLineVisible(false);
		action_bar.setListener(new InterfaceTopActiveBar() {

			@Override
			public void cancel() {
				finish();
			}

			@Override
			public void query() {

			}
		});
		btn_womensize.setSelected(true);
		Button button = getTypeButton(ly_women_sizetype);
		showTypeContent((Button) button, 500);
	}

	@Override
	protected void onDestroy() {
		if ((bitmap != null) && (!bitmap.isRecycled())) {
			bitmap.recycle();
		}
		super.onDestroy();
	}
}
