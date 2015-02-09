package com.juhe.pockettools.ruler;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

public class RulerViewInch extends View {

	public static Paint mmpaint = new Paint();
	public static Paint textpaint = new Paint();
	public static Paint cmpaint = new Paint();
	private static final String o = "RulerViewInch";
	float xdpi = 1.0F;
	float ydpi = 1.0F;
	float width = 1.0F;
	float height = 1.0F;
	int yperheight = 0;
	int xperheight = 0;
	int density_45 = 1;
	int density_30 = 1;
	int density_24 = 1;
	int density_15 = 1;
	int density_10 = 1;
	private double inch_heightPixels = 1.0D;
	private double inch_widthPixels = 1.0D;

	public RulerViewInch(Context context) {
		super(context);
		initView(context);
	}

	public RulerViewInch(Context context, AttributeSet attributeset) {
		super(context, attributeset);
		initView(context);
	}

	private void initView(Context context) {
		DisplayMetrics metrics = context.getResources().getDisplayMetrics();
		float density = metrics.density;
		ydpi = metrics.ydpi;
		xdpi = metrics.xdpi;
		inch_heightPixels = (metrics.heightPixels / (16.0F * (metrics.heightPixels / ydpi)));
		inch_widthPixels = (metrics.widthPixels / (16.0F * (metrics.widthPixels / xdpi)));
		density_45 = ((int) (45.0F * density));
		density_30 = ((int) (30.0F * density));
		density_30 = ((int) (24.0F * density));
		density_15 = ((int) (15.0F * density));
		density_10 = ((int) (10.0F * density));
		mmpaint.setStyle(Paint.Style.STROKE);
		mmpaint.setStrokeWidth(0.0F);
		mmpaint.setAntiAlias(false);
		mmpaint.setColor(Color.WHITE);
		cmpaint.setStyle(Paint.Style.STROKE);
		cmpaint.setStrokeWidth(0.0F);
		cmpaint.setAntiAlias(false);
		cmpaint.setColor(Color.argb(100, 0, 0, 0));
		cmpaint.setAlpha(0);
		textpaint.setStyle(Paint.Style.STROKE);
		textpaint.setStrokeWidth(0.0F);
		textpaint.setAntiAlias(true);
		textpaint.setColor(Color.WHITE);
		textpaint.setTextSize(density * 16.0F);
	}

	private void drawGraduation(Canvas canvas) {
		int i1 = 1;
		int i2 = i1;
		float f1 = (float) (i2 * inch_widthPixels);
		int i3 = 0;
		float f2 = 0f;
		int i4;
		float f3;
		float f4;
		if (f1 > width) {
			i3 = i1;
			f2 = (float) (i3 * inch_heightPixels);
			if (f2 > height) {
				canvas.save();
				i4 = i1;
				f3 = (float) (i4 * inch_heightPixels);
				if (f3 <= height) {
					int i5 = 0;
					if (i4 % 16 == 0) {
						i5 = density_45;
					}
					for (;;) {
						canvas.drawLine(width - i5, f3, width, f3, mmpaint);
						canvas.drawLine(0.0F, f3, i5, f3, mmpaint);
						i4++;
						
						if (i4 % 8 == 0) {
							i5 = density_30;
						} else if (i4 % 4 == 0) {
							i5 = density_30;
						} else if (i4 % 2 == 0) {
							i5 = density_15;
						} else {
							i5 = density_10;
						}
						break;
					}
				}
				f4 = (float) (i1 * inch_widthPixels);
				if (f4 <= width) {
					int i6 = 0;
					if (i1 % 16 == 0) {
						i6 = density_45;
					}
					for (;;) {
						canvas.drawLine(f4, 0.0F, f4, i6, mmpaint);
						i1++;
						
						if (i1 % 8 == 0) {
							i6 = density_30;
						} else if (i1 % 4 == 0) {
							i6 = density_30;
						} else if (i1 % 2 == 0) {
							i6 = density_15;
						} else {
							i6 = density_10;
						}
						break;
					}
				}
			}
		} else {
			if (i2 % 8 == 0) {
				cmpaint.setColor(Color.argb(50, 255, 255, 255));
				canvas.drawLine(f1, 0.0F, f1, height, cmpaint);
			}
			for (;;) {
				i2++;
				
				if (i2 % 2 == 0) {
					cmpaint.setColor(Color.argb(15, 255, 255, 255));
					canvas.drawLine(f1, 0.0F, f1, height, cmpaint);
				}
				break;
			}
		}
		if (i3 % 8 == 0) {
			cmpaint.setColor(Color.argb(50, 255, 255, 255));
			canvas.drawLine(0.0F, f2, width, f2, cmpaint);
		}
		for (;;) {
			i3++;
			
			if (i3 % 2 == 0) {
				cmpaint.setColor(Color.argb(15, 255, 255, 255));
				canvas.drawLine(0.0F, f2, width, f2, cmpaint);
			}
			break;
		}
	}

	private void drawRightText(Canvas canvas) {
		canvas.save();
		canvas.translate(width - density_45, 0.0F);
		canvas.rotate(90.0F);
		for (int i = 1; i <= yperheight; i++) {
			canvas.drawText(i + "",
					(float) (16.0D * (i * inch_heightPixels) - textpaint
							.getTextSize() / 4.0F), textpaint.getTextSize(),
					textpaint);
		}
		canvas.restore();
	}

	private void drawLeftText(Canvas canvas) {
		canvas.save();
		canvas.translate(density_45, 0.0F);
		canvas.rotate(90.0F);
		for (int i = 1; i <= yperheight; i++) {
			canvas.drawText(i + "",
					(float) (16.0D * (i * inch_heightPixels) - textpaint
							.getTextSize() / 4.0F),
					-textpaint.getTextSize() / 4.0F, textpaint);
		}
		canvas.restore();
	}

	private void drawTopText(Canvas canvas) {
		canvas.save();
		for (int i = 1; i <= xperheight; i++) {
			canvas.drawText(i + "",
					(float) (16.0D * (i * inch_widthPixels) - textpaint
							.getTextSize() / 4.0F),
					density_45 + textpaint.getTextSize(), textpaint);
		}
		canvas.restore();
	}

	@Override
	public void onDraw(Canvas canvas) {
		drawGraduation(canvas);
		drawRightText(canvas);
		drawLeftText(canvas);
		drawTopText(canvas);
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		width = w;
		height = h;
		yperheight = Math.round(height / ydpi);
		xperheight = Math.round(width / xdpi);
	}
}
