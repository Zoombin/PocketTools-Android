package com.juhe.pockettools.tabrootbutton;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.juhe.pockettools.R;

public class mainTabScrollView extends HorizontalScrollView {
	public View view_tab1;
	public View view_tab2;
	public View view_tab3;
	public View view_tab4;
	private TabL tabl;
	private LinearLayout linearlayout;

	public mainTabScrollView(Context context) {
		super(context);
		initView();
	}

	public mainTabScrollView(Context context, AttributeSet attributeSet) {
		super(context, attributeSet);
		initView();
	}

	@SuppressLint("NewApi")
	private final void initView() {
		linearlayout = new LinearLayout(getContext());
		linearlayout.setLayoutParams(new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT));
		linearlayout.setOrientation(LinearLayout.HORIZONTAL);
		setHorizontalScrollBarEnabled(false);
		setOverScrollMode(View.OVER_SCROLL_NEVER);
		addView(linearlayout);
		initTab();
	}

	public View getMainTabItemView(int imagebackgroundresource, int textresource) {
		try {
			mainTabItemView maintabitemview = new mainTabItemView(
					getContext(), null);
			maintabitemview.setItemWidth((int) (getContext()
					.getResources().getDisplayMetrics().widthPixels / 4.0F));
			maintabitemview.setResource(imagebackgroundresource, textresource);
			maintabitemview.setTag(Integer.valueOf(textresource));
			maintabitemview.setOnClickListener(new ClickListener(mainTabScrollView.this));
			linearlayout.addView(maintabitemview);
			return maintabitemview;
		} catch (Resources.NotFoundException localNotFoundException) {
		}
		return null;
	}

	public void initTab() {
		view_tab1 = getMainTabItemView(R.drawable.btn_tab1, R.string.daily_tool);
		view_tab1.setSelected(true);
		view_tab2 = getMainTabItemView(R.drawable.btn_tab2, R.string.Life_query);
		view_tab3 = getMainTabItemView(R.drawable.btn_tab3, R.string.phone_housekeeper);
		view_tab4 = getMainTabItemView(R.drawable.btn_tab4, R.string.pocket_market);
	}

//	public void setItemSelect(int item) {
//		if (view.isSelected()) {
//			view.setSelected(false);
//		}
//		view = ((mainTabItemView) linearlayout.getChildAt(item));
//		view.setSelected(true);
//	}

	public void setListener(TabL tabl) {
		this.tabl = tabl;
	}

	public static abstract interface TabL {
		public abstract void setTab(int textresource);
	}

	class ClickListener implements View.OnClickListener {
		
		mainTabScrollView maintabscrollview;
		
		public ClickListener(mainTabScrollView maintabscrollview) {
			this.maintabscrollview = maintabscrollview;
		}

		public void onClick(View v) {
			if (v.isSelected()) {
				return;
			}
			if (tabl != null) {
				v.setSelected(true);
				int tag = ((Integer) v.getTag()).intValue();
				tabl.setTab(tag);
				
				if (tag == R.string.daily_tool) {
					view_tab1.setSelected(true);
					view_tab2.setSelected(false);
					view_tab3.setSelected(false);
					view_tab4.setSelected(false);
				} else if (tag == R.string.Life_query) {
					view_tab1.setSelected(false);
					view_tab2.setSelected(true);
					view_tab3.setSelected(false);
					view_tab4.setSelected(false);
				} else if (tag == R.string.phone_housekeeper) {
					view_tab1.setSelected(false);
					view_tab2.setSelected(false);
					view_tab3.setSelected(true);
					view_tab4.setSelected(false);
				} else {
					view_tab1.setSelected(false);
					view_tab2.setSelected(false);
					view_tab3.setSelected(false);
					view_tab4.setSelected(true);
				}
			}
		}
	}
}
