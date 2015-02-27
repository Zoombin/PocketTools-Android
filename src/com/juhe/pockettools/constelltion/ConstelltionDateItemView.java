package com.juhe.pockettools.constelltion;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.juhe.pockettools.R;

public class ConstelltionDateItemView extends FrameLayout {
	TextView item_text;
	FrameLayout constelltion_layout;

	public ConstelltionDateItemView(Context context, AttributeSet attributeSet) {
		super(context, attributeSet);
		((LayoutInflater) getContext().getSystemService(
				Context.LAYOUT_INFLATER_SERVICE)).inflate(
				R.layout.constelltion_select_date_item, this, true);
		item_text = ((TextView) findViewById(R.id.item_text));
		constelltion_layout = ((FrameLayout) findViewById(R.id.constelltion_layout));
	}

	public void setItemText(int paramInt) {
		item_text.setText(paramInt);
	}

	public void setSelected(boolean isselected) {
		super.setSelected(isselected);
	}
}
