package com.juhe.pockettools.ruler;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

public class RulerViewMm extends View {
	static private float pxmm;
	float width, height;
	float density = 1.0F;
	float xdpi = 1.0F;
	float ydpi = 1.0F;
//	int yperheight = 0;
//	int xperheight = 0;
	private double inch_heightPixels = 1.0D;
//	private double inch_widthPixels = 1.0D;
	
	public RulerViewMm(Context context, AttributeSet foo) {
		super(context, foo);
		DisplayMetrics metrics = context.getResources()
				.getDisplayMetrics();
		density = metrics.density;
		ydpi = metrics.ydpi;
		xdpi = metrics.xdpi;
		inch_heightPixels = (metrics.heightPixels / (25.399999999999999D * (metrics.heightPixels / ydpi)));
//		inch_widthPixels = (metrics.widthPixels / (25.399999999999999D * (metrics.widthPixels / xdpi)));
		
		pxmm = (float)inch_heightPixels;
	}

	public void onSizeChanged(int w, int h, int oldW, int oldH) {
		width = w;
		height = h;
//		yperheight = ((int) Math.round(2.54D * (height / ydpi)));
//		xperheight = ((int) Math.round(2.54D * (width / xdpi)));
	}

	public void onDraw(Canvas canvas) {
		canvas.drawColor(0xffffffff);
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
			canvas.drawText("" + i, i * pxmm * 10 - 10, -17,
					RulerMainActivity.textPaint);
		}
	}
}
