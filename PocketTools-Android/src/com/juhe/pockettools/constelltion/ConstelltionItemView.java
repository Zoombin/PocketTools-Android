package com.juhe.pockettools.constelltion;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.zoombin.koudai.R;

public class ConstelltionItemView extends ImageView {
	private int widthSize = 0;
	private int heightSize = 0;
	private Rect rect = new Rect();
	private RectF rectf = new RectF();
	private Drawable drawableBall;
	private Paint paint_ball;
	private Paint paint_text;
	private int index = 0;

	public ConstelltionItemView(Context context,
			AttributeSet attributeSet) {
		super(context, attributeSet);
		drawableBall = context.getResources().getDrawable(R.drawable.gr_zodiac_ball);
		paint_ball = new Paint();
		paint_ball.setColor(Color.YELLOW);
		paint_ball.setAntiAlias(true);
		paint_ball.setStrokeJoin(Paint.Join.ROUND);
		paint_ball.setStrokeCap(Paint.Cap.ROUND);
		paint_ball.setStrokeWidth(3.0F);
		setIndex(50);
		paint_text = new Paint();
		paint_text.setColor(Color.WHITE);
		paint_text.setAntiAlias(true);
		paint_text.setStrokeJoin(Paint.Join.ROUND);
		paint_text.setStrokeCap(Paint.Cap.ROUND);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		float f1 = 90.0F * (float) Math.asin((50 - index) / 50.0D);
		canvas.drawArc(rectf, f1, 180.0F - 2.0F * f1, false, paint_ball);
		drawableBall.setBounds(rect);
		drawableBall.draw(canvas);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		widthSize = MeasureSpec.getSize(widthMeasureSpec);
		heightSize = MeasureSpec.getSize(heightMeasureSpec);
		rect.set(0, 0, widthSize, heightSize);
		rectf.set(rect);
	}

	public void setIndex(int index) {
		this.index = index;
		invalidate();
	}

	public void setViewColor(int color) {
		paint_ball.setColor(color);
		invalidate();
	}
}
