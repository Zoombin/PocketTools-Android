package com.juhe.pockettools.violation;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;

import com.juhe.pockettools.utils.SizeTool;

public class ViolationHistoryAdapter extends BaseAdapter {
	public static String VIOLATION_CARNUM = "violation_carnum";
	public static String VIOLATION_URL = "violation_url";
	public static String VIOLATION_CITY = "violation_city";
	public static String VIOLATION_ENGINENO = "violation_engineno";
	ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
	Context context;

	public ViolationHistoryAdapter(Context context) {
		this.context = context;
	}

	public void setData(ArrayList<HashMap<String, String>> list) {
		this.list = list;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int index) {
		return list.get(index);
	}

	@Override
	public long getItemId(int index) {
		return index;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (list.size() <= 0) {
			return convertView;
		}
		TextView localTextView = new TextView(context);
		localTextView.setText(list.get(position).get(VIOLATION_CARNUM));
		localTextView.setTextColor(Color.WHITE);
		AbsListView.LayoutParams params = new AbsListView.LayoutParams(-1,
				SizeTool.getLength(context, 40.0F));
		localTextView.setPadding(SizeTool.getLength(context, 10.0F), 0,
				SizeTool.getLength(context, 10.0F), 0);
		localTextView.setLayoutParams(params);
		localTextView.setGravity(19);
		return localTextView;
	}
}
