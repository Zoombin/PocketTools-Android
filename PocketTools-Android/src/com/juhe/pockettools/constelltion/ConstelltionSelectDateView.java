package com.juhe.pockettools.constelltion;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.zoombin.koudai.R;

public class ConstelltionSelectDateView extends LinearLayout {
	private List<String> datelist;
	private ConstelltionDateScrollView constelltion_scroll_select;
	private ArrayList<Integer> dateintlist;
	private OnSelectListener listener;

	public ConstelltionSelectDateView(Context context) {
		super(context);
		initView();
	}

	public ConstelltionSelectDateView(Context context,
			AttributeSet attributeSet) {
		super(context, attributeSet);
		initView();
	}

	private void initData() {
		dateintlist = new ArrayList<Integer>();
		dateintlist.add(Integer.valueOf(R.string.constelltion_today));
		dateintlist.add(Integer.valueOf(R.string.constelltion_tomorrow));
		dateintlist.add(Integer.valueOf(R.string.constelltion_week));
		dateintlist.add(Integer.valueOf(R.string.constelltion_month));
		dateintlist.add(Integer.valueOf(R.string.constelltion_year));
		
		datelist = Arrays.asList(new String[] { "今日", "明日", "本周", "本月", "今年" });
	}

	public void initView() {
		((LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE))
				.inflate(R.layout.view_constelltion_select_container, this, true);
		constelltion_scroll_select = ((ConstelltionDateScrollView) findViewById(R.id.constelltion_scroll_select));
		initData();
		constelltion_scroll_select.setSelectItem(dateintlist, 0);
		constelltion_scroll_select.setLinstener(new ConstelltionDateScrollView.OnSelectListener() {
			
			@Override
			public void setDateIndex(int index) {
				listener.setDateStr(datelist.get(index));
			}
		});
	}

	public void a(int paramInt) {
		constelltion_scroll_select.setItemSelceted(paramInt);
	}

	public void setListener(OnSelectListener listener) {
		this.listener = listener;
	}

	public static abstract interface OnSelectListener {
		public abstract void setDateStr(String datestr);
	}
}
