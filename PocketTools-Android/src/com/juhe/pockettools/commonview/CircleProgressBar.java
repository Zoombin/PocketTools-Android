package com.juhe.pockettools.commonview;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

public class CircleProgressBar extends View {
	private int a = 20;
	private float b;
	private float c;
	private float d;
	private float e;
	private float f = 1.0F;
	private float g = 1.0F;
	private boolean h;
	private int i = 250;
	private int j = 250;
	private volatile boolean k = false;
	private Paint l;
	private Paint m;
	private boolean n = true;

	public CircleProgressBar(Context paramContext) {
		super(paramContext);
		d();
	}

	public CircleProgressBar(Context paramContext,
			AttributeSet paramAttributeSet) {
		super(paramContext, paramAttributeSet);
		d();
	}

	public CircleProgressBar(Context paramContext,
			AttributeSet paramAttributeSet, int paramInt) {
		super(paramContext, paramAttributeSet, paramInt);
		d();
	}

	public static boolean a(Resources paramResources) {
		int i1 = paramResources.getDisplayMetrics().densityDpi;
		switch (i1) {
		case 120:
		case 160:
			return false;
		case 240:
			return true;
		case 320:
			return true;
		case 480:
			return true;

		default:
			return true;
		}
	}

	private void c() {
		this.b = (getWidth() / 2);
		this.c = (getHeight() / 2);
		this.d = (getWidth() / 2);
		this.e = (getHeight() / 2);
		this.j = this.i;
	}

	private void d() {
		this.l = new Paint(1);
		this.l.setStyle(Paint.Style.FILL);
		this.l.setARGB(this.i, 255, 255, 255);
		this.m = new Paint(1);
		this.m.setStyle(Paint.Style.FILL);
		this.m.setARGB(this.i, 166, 166, 166);
		this.j = this.i;
		this.h = a(getResources());
		if (!this.h) {
			this.f /= 2.0F;
			this.g /= 2.0F;
			this.a /= 2;
			this.a /= 2;
		}
	}

	private void e() {
		this.b -= this.f;
		this.c -= this.g;
		this.d += this.f;
		this.e += this.g;
		this.j = (-1 + this.j);
		if (this.j < 10) {
			this.j = 10;
		}
		this.l.setAlpha(this.j);
		this.m.setAlpha(this.j);
		this.n = true;
	}

	private void f() {
		this.b += this.f;
		this.c += this.g;
		this.d -= this.f;
		this.e -= this.g;
		this.j = (1 + this.j);
		if (this.j > 250) {
			this.j = 250;
		}
		this.l.setAlpha(this.j);
		this.m.setAlpha(this.j);
		this.n = false;
	}

	private void g() {
		if (this.n) {
			if (this.e + this.f + this.a > getWidth()) {
				f();
				return;
			}
			e();
			return;
		}
		if (this.e < getHeight() / 2) {
			e();
			return;
		}
		f();
	}

	public void a() {
		this.k = true;
		c();
		invalidate();
	}

	public void b() {
		this.k = false;
		invalidate();
	}

	protected void onDraw(Canvas paramCanvas) {
		super.onDraw(paramCanvas);
		if (this.k) {
			paramCanvas.drawOval(new RectF(this.b, this.c, this.d, this.e),
					this.l);
			paramCanvas.drawOval(new RectF(this.b - this.a, this.c - this.a,
					this.d + this.a, this.e + this.a), this.m);
			g();
			invalidate();
			return;
		}
		c();
	}

	protected void onMeasure(int paramInt1, int paramInt2) {
		super.onMeasure(paramInt1, paramInt2);
		c();
	}

	public void onSizeChanged(int paramInt1, int paramInt2, int paramInt3,
			int paramInt4) {
		super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
		c();
	}
}
