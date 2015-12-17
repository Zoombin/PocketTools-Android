package com.juhe.pockettools.home;

import java.util.ArrayList;
import java.util.List;

import com.zoombin.koudai.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

public class ItemPageTabView extends Fragment {
	private String a = "ToolItemPageTabView";
	private GridView tool_view_page_item;
	private ItemPageTabViewAdapter adapter;
	private List<ModuleInfo> list = new ArrayList<ModuleInfo>();

	public static ItemPageTabView getInstance() {
		return new ItemPageTabView();
	}

	public void setData(List<ModuleInfo> list) {
		if (list == null) {
			return;
		}
		this.list = list;
//		adapter.notifyDataSetChanged();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View localView = inflater.inflate(R.layout.viewpager_pageitem_grid,
				container, false);
		tool_view_page_item = ((GridView) localView.findViewById(R.id.tool_view_page_item));
		adapter = new ItemPageTabViewAdapter(getActivity());
		adapter.setData(list);
//		this.c.a(new m(this));
//		int i = k.i();
		tool_view_page_item.setAdapter(adapter);
		tool_view_page_item.setNumColumns(4);
		tool_view_page_item.setColumnWidth((int) (getResources().getDisplayMetrics().widthPixels / 4));
//		adapter.a(this.f);
		return localView;
	}
}
