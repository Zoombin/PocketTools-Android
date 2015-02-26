package com.juhe.pockettools.violation;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.juhe.pockettools.R;

public class ViolationDetailHeadView extends FrameLayout {
	private LinearLayout root_container;
	private TextView detail_total_code;
	private TextView detail_total_money;

	public ViolationDetailHeadView(Context context) {
		super(context);
		initView(context);
	}

	public ViolationDetailHeadView(Context context,
			AttributeSet attributeSet) {
		super(context, attributeSet);
		initView(context);
	}

	private void initView(Context context) {
		((LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE))
				.inflate(R.layout.view_violation_detail_header, this, true);
		root_container = ((LinearLayout) findViewById(R.id.root_container));
		detail_total_money = ((TextView) findViewById(R.id.detail_total_money));
		detail_total_code = ((TextView) findViewById(R.id.detail_total_code));
		root_container.setVisibility(View.INVISIBLE);
		int widthPixels = context.getResources().getDisplayMetrics().widthPixels;
		FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) root_container
				.getLayoutParams();
		params.width = widthPixels;
		root_container.setLayoutParams(params);
	}

	public void setData(String totalcode, String totalmoney) {
		root_container.setVisibility(View.VISIBLE);
		detail_total_money.setText(totalmoney);
		detail_total_code.setText(totalcode);
	}
}
