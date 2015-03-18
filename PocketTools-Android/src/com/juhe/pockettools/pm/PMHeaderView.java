package com.juhe.pockettools.pm;

import com.juhe.pockettools.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

public class PMHeaderView extends LinearLayout {
	public PMHeaderView(Context context) {
		super(context);
		initView();
	}

	public PMHeaderView(Context context, AttributeSet attributeSet) {
		super(context, attributeSet);
		initView();
	}

	private void initView() {
		((LayoutInflater) getContext().getSystemService(
				Context.LAYOUT_INFLATER_SERVICE)).inflate(
				R.layout.view_pm_header, this, true);
	}
}