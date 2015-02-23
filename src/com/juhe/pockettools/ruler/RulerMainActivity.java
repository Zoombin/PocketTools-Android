package com.juhe.pockettools.ruler;

import android.app.Activity;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.juhe.pockettools.R;

public class RulerMainActivity extends Activity {

	Button btn_close;
	static Paint paint = new Paint(), textPaint = new Paint();

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ruler_mian);
		btn_close = (Button) findViewById(R.id.btn_close);
		btn_close.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});

		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeWidth(0);
		paint.setAntiAlias(false);
		paint.setColor(0xff000000);

		textPaint.setStyle(Paint.Style.STROKE);
		textPaint.setStrokeWidth(0);
		textPaint.setAntiAlias(true);
		textPaint.setColor(0xff000000);
	}
}
