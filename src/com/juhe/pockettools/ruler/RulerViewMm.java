package com.juhe.pockettools.ruler;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

public class RulerViewMm extends View {

	public static Paint mmpaint = new Paint();
	public static Paint textpaint = new Paint();
	public static Paint cmpaint = new Paint();
	private static final String TAG = "RulerViewMm";
	float density = 1.0F;
	float xdpi = 1.0F;
	float ydpi = 1.0F;
	float width = 1.0F;
	float height = 1.0F;
	int yperheight = 0;
	int xperheight = 0;
	int density_30 = 1;
	int density_18 = 1;
	int density_10 = 1;
	private double inch_heightPixels = 1.0D;
	private double inch_widthPixels = 1.0D;

	public RulerViewMm(Context context) {
		super(context);
		initView(context);
	}

	public RulerViewMm(Context context, AttributeSet attributeset) {
		super(context, attributeset);
		initView(context);
	}

	private void initView(Context paramContext) {
		DisplayMetrics metrics = paramContext.getResources()
				.getDisplayMetrics();
		density = metrics.density;
		ydpi = metrics.ydpi;
		xdpi = metrics.xdpi;
		inch_heightPixels = (metrics.heightPixels / (25.399999999999999D * (metrics.heightPixels / ydpi)));
		inch_widthPixels = (metrics.widthPixels / (25.399999999999999D * (metrics.widthPixels / xdpi)));
		density_10 = ((int) (10.0F * density));
		density_18 = ((int) (18.0F * density));
		density_30 = ((int) (30.0F * density));
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

//	private void a(Canvas paramCanvas)
//  {
//    int i1 = 1;
//    paramCanvas.save();
//    int i2 = i1;
//    float f1 = (float)(i2 * inch_widthPixels);
//    int i3;
//    float f2;
//    int i4;
//    float f3;
//    float f4;
//    if (f1 > width)
//    {
//      i3 = i1;
//      f2 = (float)(i3 * inch_heightPixels);
//      if (f2 > height)
//      {
//        i4 = i1;
//        f3 = (float)(i4 * inch_heightPixels);
//        if (f3 <= height) {
//          break label248;
//        }
//        f4 = (float)(i1 * inch_widthPixels);
//        if (f4 <= width) {
//          break label331;
//        }
//        paramCanvas.restore();
//      }
//    }
//    else
//    {
//      if (i2 % 5 == 0) {
//        m.setColor(Color.argb(50, 255, 255, 255));
//      }
//      for (;;)
//      {
//        paramCanvas.drawLine(f1, 0.0F, f1, height, m);
//        i2++;
//        break;
//        m.setColor(Color.argb(15, 255, 255, 255));
//      }
//    }
//    if (i3 % 5 == 0) {
//      m.setColor(Color.argb(50, 255, 255, 255));
//    }
//    for (;;)
//    {
//      paramCanvas.drawLine(0.0F, f2, width, f2, m);
//      i3++;
//      break;
//      m.setColor(Color.argb(15, 255, 255, 255));
//    }
//    label248:
//    int i5;
//    if (i4 % 10 == 0) {
//      i5 = density_30;
//    }
//    for (;;)
//    {
//      paramCanvas.drawLine(width - i5, f3, width, f3, k);
//      paramCanvas.drawLine(0.0F, f3, i5, f3, k);
//      i4++;
//      break;
//      if (i4 % 5 == 0) {
//        i5 = density_18;
//      } else {
//        i5 = density_10;
//      }
//    }
//    label331:
//    int i6;
//    if (i1 % 10 == 0) {
//      i6 = density_30;
//    }
//    for (;;)
//    {
//      paramCanvas.drawLine(f4, 0.0F, f4, i6, k);
//      i1++;
//      break;
//      if (i1 % 5 == 0) {
//        i6 = density_18;
//      } else {
//        i6 = density_10;
//      }
//    }
//  }
	
	private void drawRightText(Canvas canvas) {
		canvas.save();
		canvas.translate(width - density_30, 0.0F);
		canvas.rotate(90.0F);
		for (int i = 1; i <= yperheight; i++) {
			canvas.drawText(i + "",
					(float) (10.0D * (i * inch_heightPixels) - textpaint
							.getTextSize() / 4.0F), textpaint.getTextSize(),
					textpaint);
		}
		canvas.restore();
	}
	
	
	private void drawLeftText(Canvas canvas) {
		canvas.save();
		canvas.translate(density_30, 0.0F);
		canvas.rotate(90.0F);
		for (int i = 1; i <= yperheight; i++) {
			canvas.drawText(i + "",
					(float) (10.0D * (i * inch_heightPixels) - textpaint
							.getTextSize() / 4.0F),
					-textpaint.getTextSize() / 4.0F, textpaint);
		}
		canvas.restore();
	}

	private void drawTopText(Canvas canvas) {
		canvas.save();
		for (int i = 1; i <= xperheight; i++) {
			canvas.drawText(i + "",
					(float) (10.0D * (i * inch_widthPixels) - textpaint
							.getTextSize() / 4.0F),
							density_30 + textpaint.getTextSize(), textpaint);
		}
		canvas.restore();
	}
	
	@Override
	public void onDraw(Canvas canvas) {
//		drawGraduation(canvas);
		drawRightText(canvas);
		drawLeftText(canvas);
		drawTopText(canvas);
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		width = w;
		height = h;
		yperheight = ((int) Math.round(2.54D * (height / ydpi)));
		xperheight = ((int) Math.round(2.54D * (width / xdpi)));
	}
}
