package com.juhe.pockettools.constelltion;

import com.juhe.pockettools.utils.SizeTool;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.TextView;

class SelectedAdapter extends BaseAdapter {
	private Context context;

	public SelectedAdapter(Context context) {
		this.context = context;
	}

	@Override
	public int getCount() {
		return 12;
	}

	@Override
	public Object getItem(int position) {
		if (position < 12) {
			return ConstalltionConstants.names[position] + "(" + ConstalltionConstants.dates[position] + ")";
		}
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		String str = "";
		if (position < 12) {
			str = ConstalltionConstants.names[position] + "(" + ConstalltionConstants.dates[position] + ")";
		}
		TextView tv = (TextView) convertView;
		if (tv == null) {
			tv = new TextView(context, null);
			tv.setGravity(19);
			tv.setPadding(SizeTool.getLength(context, 10.0F), 0, 0, 0);
			tv.setHeight(SizeTool.getLength(context, 50.0F));
			tv.setTextSize(18.0F);
			tv.setTextColor(Color.WHITE);
		}
		tv.setText(str);
		tv.setLayoutParams(new AbsListView.LayoutParams(-1, -2));
		return tv;
	}
}
