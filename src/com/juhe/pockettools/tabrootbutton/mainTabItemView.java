package com.juhe.pockettools.tabrootbutton;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.juhe.pockettools.R;
import com.juhe.pockettools.commonview.FZKTTextView;

public class mainTabItemView extends FrameLayout {
	ImageView tab_item_image;
	FZKTTextView tab_item_txt;
	FrameLayout item_container;
	int imagebackgroundresource;
	int textresource;
	private int itemheight = 0;

	public mainTabItemView(Context context) {
		super(context);
		initView();
	}

	public mainTabItemView(Context context, AttributeSet attributeset) {
		super(context, attributeset);
		initView();
	}

	private void initView() {
		((LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE))
				.inflate(R.layout.view_tabrootbutton_item, this, true);
		tab_item_image = ((ImageView) findViewById(R.id.tab_item_image));
		tab_item_txt = ((FZKTTextView) findViewById(R.id.tab_item_txt));
		item_container = ((FrameLayout) findViewById(R.id.item_container));
	}

	private void selectTab(boolean selected) {
		LayoutParams layoutparams = (LayoutParams) tab_item_image.getLayoutParams();
		if (selected) {
			layoutparams.height = ((int) (0.15F * itemheight));
			tab_item_txt.setTextColor(Color.WHITE);
		} else {
			layoutparams.height = itemheight;
			tab_item_txt.setTextColor(Color.BLACK);
		}
		tab_item_image.setLayoutParams(layoutparams);
		tab_item_image.requestLayout();
	}

	public void setResource(int imagebackgroundresource, int textresource) {
		this.textresource = textresource;
		this.imagebackgroundresource = imagebackgroundresource;
		tab_item_txt.setText(textresource);
		setSelected(false);
	}

	public void setItemWidth(int itemwidth) {
		int i = getContext().getResources().getDisplayMetrics().widthPixels;
		itemheight = ((int) (i / 9.0F));
		LayoutParams layoutparams = (LayoutParams) item_container
				.getLayoutParams();
		layoutparams.width = itemwidth;
		layoutparams.height = ((int) (i / 9.0F));
		item_container.setLayoutParams(layoutparams);
	}

	public void setSelected(boolean selected) {
		super.setSelected(selected);
		tab_item_image.setBackgroundResource(imagebackgroundresource);
		selectTab(selected);
	}
}
