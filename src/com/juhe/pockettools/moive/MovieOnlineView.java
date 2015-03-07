package com.juhe.pockettools.moive;

import com.juhe.pockettools.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

public class MovieOnlineView extends FrameLayout {
	public MovieOnlineView(Context context) {
		super(context);
		initView();
	}

	public MovieOnlineView(Context context, AttributeSet attributeSet) {
		super(context, attributeSet);
		initView();
	}

	private void initView() {
		((LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE))
				.inflate(R.layout.view_movie_online, this, true);
	}
}
