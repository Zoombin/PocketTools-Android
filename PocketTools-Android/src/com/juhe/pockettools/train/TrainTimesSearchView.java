package com.juhe.pockettools.train;

import com.juhe.pockettools.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

public class TrainTimesSearchView extends FrameLayout {
	private Context context;

	public TrainTimesSearchView(Context context) {
		super(context);
		this.context = context;
		initView();
	}

	public TrainTimesSearchView(Context context, AttributeSet attributeSet) {
		super(context, attributeSet);
		this.context = context;
		initView();
	}

	private void initView() {
		((LayoutInflater) getContext().getSystemService(
				Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.view_train_main_item1, this,
				true);
	}
}