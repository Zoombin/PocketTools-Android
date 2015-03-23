package com.juhe.pockettools.setting;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;

public class StatusImageView extends RecyclingImageView {
	Paint a = new Paint();
	RectF b = new RectF();
	Bitmap c;
	int d = 6;
	private int e = -1;
	private boolean f = false;

	public StatusImageView(Context paramContext) {
		super(paramContext);
	}

	public StatusImageView(Context paramContext, AttributeSet paramAttributeSet) {
		super(paramContext, paramAttributeSet);
	}

	public StatusImageView(Context paramContext,
			AttributeSet paramAttributeSet, int paramInt) {
		super(paramContext, paramAttributeSet, paramInt);
	}

	public void a() {
		setImageBitmap(null);
		if ((this.c != null) && (!this.c.isRecycled())) {
			this.c.recycle();
		}
	}

	public boolean isSelected() {
		return this.f;
	}

	protected void onDraw(Canvas paramCanvas) {
		super.onDraw(paramCanvas);
		if (this.f) {
			this.b.left = (this.d / 2);
			this.b.top = (this.d / 2);
			this.b.bottom = (getHeight() - this.d / 2);
			this.b.right = (getWidth() - this.d / 2);
			if (getResources().getDisplayMetrics().density > 2.5D) {
				this.d = 8;
			}
			this.a.setColor(this.e);
			this.a.setStrokeWidth(this.d);
			this.a.setStyle(Paint.Style.STROKE);
			paramCanvas.drawRect(this.b, this.a);
		}
	}

	public void setImageBitmap(Bitmap paramBitmap) {
		this.c = paramBitmap;
		super.setImageBitmap(paramBitmap);
	}

	public void setIsSelected(boolean paramBoolean) {
		this.f = paramBoolean;
		invalidate();
	}
}
