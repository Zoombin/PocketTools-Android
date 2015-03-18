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

	private ConstelltionDateItemView getConstelltionDateItemView(int text, int index) {
		ConstelltionDateItemView constelltiondateitemview = new ConstelltionDateItemView(
				getContext(), null);
		constelltiondateitemview.setItemText(text);
		constelltiondateitemview.setTag(Integer.valueOf(index));
		constelltiondateitemview.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (!v.isSelected()) {
					if (layout != null) {
						for (int i=0; i<layout.getChildCount(); i++) {
							layout.getChildAt(i).setSelected(false);
						}
					}
					setStartScroll(v);
					listener.setDateIndex((int) v.getTag());
					v.setSelected(true);
				}
			}
		});
		layout.addView(constelltiondateitemview);
		return constelltiondateitemview;
	}

	private void setStartScroll(View v) {
		int i = getContext().getResources().getDisplayMetrics().widthPixels;
		int j = v.getLeft();
		int k = v.getWidth();
		int m = getScrollX();
		if ((j - m > i - k * 2) && (j - m < i + k)) {
			smoothScrollTo(j - i + k * 2, v.getTop());
		}
		if ((j - m < k) && (j - m >= -v.getWidth())) {
			smoothScrollTo(j - k, v.getTop());
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

	public void setSelectItem(ArrayList<Integer> dateintlist, int index) {
		for (int i=0; i<dateintlist.size(); i++) {
			ConstelltionDateItemView v =  getConstelltionDateItemView(((Integer) dateintlist.get(i)).intValue(), i);
			if (i == index) {
				v.setSelected(true);
			} else {
				v.setSelected(false);
			}
		}
	}

	public void setItemSelceted(int index) {
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
			if (index == ((Integer) ((ConstelltionDateItemView) layout
					.getChildAt(i)).getTag()).intValue()) {
				view.setSelected(false);
				view = layout.getChildAt(i);
				view.setSelected(true);
			}
		}
	}

	public void setLinstener(OnSelectListener listener) {
		this.listener = listener;
	}

	public static abstract interface OnSelectListener {
		public abstract void setDateIndex(int index);
	}
}
