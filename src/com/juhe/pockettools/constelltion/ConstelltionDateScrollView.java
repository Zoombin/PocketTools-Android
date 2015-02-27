package com.juhe.pockettools.constelltion;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import java.io.PrintStream;
import java.util.ArrayList;

public class ConstelltionDateScrollView extends HorizontalScrollView {
	OnSelectListener listener;
	private View view;
	private LinearLayout layout;

	public ConstelltionDateScrollView(Context context) {
		super(context);
		initView();
	}

	public ConstelltionDateScrollView(Context context,
			AttributeSet attributeSet) {
		super(context, attributeSet);
		initView();
	}

	private View a(int paramInt1, int paramInt2) {
		ConstelltionDateItemView localConstelltionDateItemView = new ConstelltionDateItemView(
				getContext(), null);
		localConstelltionDateItemView.setItemText(paramInt1);
		localConstelltionDateItemView.setTag(Integer.valueOf(paramInt2));
//		localConstelltionDateItemView.setOnClickListener(new b(this));
		layout.addView(localConstelltionDateItemView);
		return localConstelltionDateItemView;
	}

	private void setStartScroll(View paramView) {
		int i = getContext().getResources().getDisplayMetrics().widthPixels;
		int j = paramView.getLeft();
		int k = paramView.getWidth();
		int m = getScrollX();
		if ((j - m > i - k * 2) && (j - m < i + k)) {
			smoothScrollTo(j - i + k * 2, paramView.getTop());
		}
		if ((j - m < k) && (j - m >= -paramView.getWidth())) {
			smoothScrollTo(j - k, paramView.getTop());
		}
	}

	public void initView() {
		layout = new LinearLayout(getContext());
		layout.setLayoutParams(new LinearLayout.LayoutParams(-2, -1));
		layout.setOrientation(0);
		setSmoothScrollingEnabled(true);
		setScrollbarFadingEnabled(true);
		setHorizontalScrollBarEnabled(false);
		setOverScrollMode(2);
		addView(layout);
	}

	public void a(ArrayList<Integer> paramArrayList, int paramInt) {
//		int i = 0;
//		if (i >= paramArrayList.size()) {
//			return;
//		}
//		if (i == paramInt) {
//			view = a(((Integer) paramArrayList.get(i)).intValue(), i);
//		}
//		for (;;) {
//			view.setSelected(true);
//			i++;
//			break;
//			a(((Integer) paramArrayList.get(i)).intValue(), i);
//		}
	}

	public void setItemSelceted(int paramInt) {
		if (view != null) {
			view.setSelected(false);
		}
		for (int i = 0;; i++) {
			if (i >= layout.getChildCount()) {
				return;
			}
			System.out.println("allTag:"
					+ ((ConstelltionDateItemView) layout.getChildAt(i))
							.getTag());
			if (paramInt == ((Integer) ((ConstelltionDateItemView) layout
					.getChildAt(i)).getTag()).intValue()) {
				view.setSelected(false);
				view = layout.getChildAt(i);
				view.setSelected(true);
//				c localc = new c(this);
//				new Handler().post(localc);
			}
		}
	}

	public void setLinstener(OnSelectListener listener) {
		this.listener = listener;
	}

	public static abstract interface OnSelectListener {
		public abstract void a(int paramInt);
	}
}
