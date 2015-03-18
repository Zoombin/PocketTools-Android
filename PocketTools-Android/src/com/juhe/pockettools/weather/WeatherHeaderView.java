package com.juhe.pockettools.weather;

import com.juhe.pockettools.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

public class WeatherHeaderView extends LinearLayout {
	public WeatherHeaderView(Context context) {
		super(context);
		initView();
	}

	public WeatherHeaderView(Context context, AttributeSet attributeSet) {
		super(context, attributeSet);
		initView();
	}

	private void initView() {
		((LayoutInflater) getContext().getSystemService(
				Context.LAYOUT_INFLATER_SERVICE)).inflate(
				R.layout.view_weather_header, this, true);
	}
}
