package com.juhe.pockettools.ruler;

import com.juhe.pockettools.R;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

public class RulerViewMmRight extends View {
	static private float pxmm;
	float width, height;
	float density = 1.0F;
	float xdpi = 1.0F;
	float ydpi = 1.0F;
	int yperheight = 0;
	int xperheight = 0;
	private double inch_heightPixels = 1.0D;
	private double inch_widthPixels = 1.0D;
	
	public RulerViewMmRight(Context context, AttributeSet foo) {
		super(context, foo);
		setBackgroundColor(context.getResources().getColor(R.color.transparent));
		DisplayMetrics metrics = context.getResources().getDisplayMetrics();
		density = metrics.density;
		ydpi = metrics.ydpi;
		xdpi = metrics.xdpi;
		inch_heightPixels = (metrics.heightPixels / (25.399999999999999D * (metrics.heightPixels / ydpi)));
//		inch_widthPixels = (metrics.widthPixels / (16.0F * (metrics.widthPixels / xdpi)));
		
		pxmm = (float)inch_heightPixels;
	}

	public void onSizeChanged(int w, int h, int oldW, int oldH) {
		width = w;
		height = h;
		yperheight = Math.round(height / ydpi);
		xperheight = Math.round(width / xdpi);
	}

	public void onDraw(Canvas canvas) {
		canvas.drawColor(0x00000000);
		for (int i = 1;; ++i) {
			float y = i * pxmm;
			if (y > height) {
				break;
			}
			int size = (i % 10 == 0) ? 33 : (i % 5 == 0) ? 17 : 10;
			canvas.drawLine(width - size, y, width, y, RulerMainActivity.paint);
		}
		canvas.rotate(90);
		for (int i = 1; i <= 20; ++i) {
			canvas.drawText("" + i, i * pxmm * 10 - 10, -100,
					RulerMainActivity.textPaint);
		}
	}
}
