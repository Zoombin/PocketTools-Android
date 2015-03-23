package com.juhe.pockettools.ruler;

import android.app.Activity;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

import com.juhe.pockettools.R;
import com.juhe.pockettools.utils.Config;

public class RulerMainActivity extends Activity {

	LinearLayout ly_ruler;
	Button btn_close;
	static Paint paint = new Paint(), textPaint = new Paint();

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ruler_mian);
		ly_ruler = (LinearLayout) findViewById(R.id.ly_ruler);
		ly_ruler.setBackgroundResource(Config.getBgDrawableResId());
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
		paint.setColor(0xffffffff);

		textPaint.setStyle(Paint.Style.STROKE);
		textPaint.setStrokeWidth(0);
		textPaint.setAntiAlias(true);
		textPaint.setColor(0xffffffff);
	}
}
