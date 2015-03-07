package com.juhe.pockettools.moive;

import com.juhe.pockettools.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

public class CinemaView extends FrameLayout {
	public CinemaView(Context context) {
		super(context);
		initView();
	}

	public CinemaView(Context context, AttributeSet attributeSet) {
		super(context, attributeSet);
		initView();
	}

	private void initView() {
		((LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE))
				.inflate(R.layout.view_movie_cinema, this, true);
	}
}
