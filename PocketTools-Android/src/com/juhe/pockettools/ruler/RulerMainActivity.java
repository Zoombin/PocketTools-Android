package com.juhe.pockettools.ruler;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.zoombin.koudai.R;
import com.juhe.pockettools.utils.Config;

public class RulerMainActivity extends Activity {

	Button btn_close, btn_swith;
	static Paint paint = new Paint(), textPaint = new Paint();
	boolean inchstate = false;
	
	RulerViewInchLeft left1;
	RulerViewInchRight right1;
	RulerViewMmLeft left2;
	RulerViewMmRight right2;
	
	@SuppressLint("NewApi")
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ruler_mian);
		((ImageView) findViewById(R.id.img_bg)).setBackgroundColor(getResources().getColor(Config.getColor()));
		btn_close = (Button) findViewById(R.id.btn_close);
		btn_close.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});

		left1 = (RulerViewInchLeft) findViewById(R.id.left1);
		right1 = (RulerViewInchRight) findViewById(R.id.right1);
		
		left2 = (RulerViewMmLeft)  findViewById(R.id.left2);
		right2 = (RulerViewMmRight)  findViewById(R.id.right2);
		
		btn_swith = (Button) findViewById(R.id.btn_swith);
		btn_swith.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				inchstate = !inchstate;
				btn_swith.setSelected(inchstate);
				if (inchstate) {
					left1.setVisibility(View.VISIBLE);
					right1.setVisibility(View.VISIBLE);
					left2.setVisibility(View.GONE);
					right2.setVisibility(View.GONE);
				} else {
					left1.setVisibility(View.GONE);
					right1.setVisibility(View.GONE);
					left2.setVisibility(View.VISIBLE);
					right2.setVisibility(View.VISIBLE);
				}
			}
		});
		
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeWidth(0);
		paint.setAntiAlias(false);
		paint.setColor(0xffffffff);

		textPaint.setStyle(Paint.Style.STROKE);
		textPaint.setTextSize(20);
		textPaint.setStrokeWidth(0);
		textPaint.setAntiAlias(true);
		textPaint.setColor(0xffffffff);
	}
}
