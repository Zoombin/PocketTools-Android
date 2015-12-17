package com.juhe.pockettools.ruler;

import com.zoombin.koudai.R;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

public class RulerViewInchLeft extends View {
	static private float pxinch;
	float width, height;
	float density = 1.0F;
	float xdpi = 1.0F;
	float ydpi = 1.0F;
	int yperheight = 0;
	int xperheight = 0;
	private double inch_heightPixels = 1.0D;
	private double inch_widthPixels = 1.0D;
	
	public RulerViewInchLeft(Context context, AttributeSet foo) {
		super(context, foo);
		setBackgroundColor(context.getResources().getColor(R.color.transparent));
		DisplayMetrics metrics = context.getResources().getDisplayMetrics();
		density = metrics.density;
		ydpi = metrics.ydpi;
		xdpi = metrics.xdpi;
		inch_heightPixels = (metrics.heightPixels / (16.0F * (metrics.heightPixels / ydpi)));
		inch_widthPixels = (metrics.widthPixels / (16.0F * (metrics.widthPixels / xdpi)));
		
		pxinch = (float)inch_heightPixels;
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
			float y = height - i * pxinch;
			if (y < 0) {
				break;
			}
			int size = (i % 16 == 0) ? 45 : (i % 8 == 0) ? 30
					: (i % 4 == 0) ? 22 : (i % 2 == 0) ? 15 : 10;
			canvas.drawLine(0, y, size, y, RulerMainActivity.paint);
		}
		canvas.rotate(-90);
		for (int i = 1; i <= 10; ++i) {
			canvas.drawText("" + i, - height - 10 + pxinch * 16 * i, 40,
					RulerMainActivity.textPaint);
		}
	}
}
