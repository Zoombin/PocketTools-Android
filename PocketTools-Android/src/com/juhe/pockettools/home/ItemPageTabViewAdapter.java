package com.juhe.pockettools.home;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class ItemPageTabViewAdapter extends BaseAdapter {
	List<ModuleInfo> list = new ArrayList<ModuleInfo>();
	private Context context;

	public ItemPageTabViewAdapter(Context context) {
		this.context = context;
	}

	public void setData(List<ModuleInfo> list) {
		if (list != null) {
			this.list = list;
			notifyDataSetChanged();
		}
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return list == null ? 0 : position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ModuleInfo entity = (ModuleInfo) list.get(position);
		itemButton itembutton = (itemButton) convertView;
		if (itembutton == null) {
			itembutton = new itemButton(context, null);
		}
		itembutton.setData(entity.getId(), entity.getIcon(), entity.getName());
		itembutton.setClickable(true);
//		itembutton.setOnClickListener(new n(this, localToolModel));
//		itembutton.setOnTouchListener(new o(this));
		return itembutton;
	}
}
