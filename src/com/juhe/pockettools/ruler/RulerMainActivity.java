package com.juhe.pockettools.ruler;

import org.xmlpull.v1.XmlPullParser;

import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Xml;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;

import com.juhe.pockettools.R;
import com.juhe.pockettools.home.FullscreenActivity;
//import com.fotoable.helpr.wallpaper.w;

public class RulerMainActivity extends FullscreenActivity {
	private static final String TAG = "Ruler";
	private RulerViewInch rulerviewinch;
	private RulerViewMm rulerviewmm;
	private Button btn_swith;
	private Button btn_close;
	private FrameLayout ly_rulers;

	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.activity_ruler_mian);
		ly_rulers = ((FrameLayout) findViewById(R.id.ly_rulers));
//		((ImageView) findViewById(R.id.img_bg)).setImageBitmap(w.a().d());
		rulerviewinch = new RulerViewInch(this);
		rulerviewinch.setVisibility(View.INVISIBLE);
		rulerviewmm = new RulerViewMm(this);
		ly_rulers.addView(rulerviewinch, 0);
		ly_rulers.addView(rulerviewmm, 1);
		btn_swith = ((Button) findViewById(R.id.btn_swith));
		btn_close = ((Button) findViewById(R.id.btn_close));
//		btn_swith.setOnClickListener(new a(this));
		btn_close.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}
}
