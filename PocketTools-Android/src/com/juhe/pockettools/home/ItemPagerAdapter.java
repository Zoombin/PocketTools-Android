package com.juhe.pockettools.home;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.juhe.pockettools.R;
import com.viewpagerindicator.IconPagerAdapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

public class ItemPagerAdapter extends FragmentPagerAdapter implements
		IconPagerAdapter {
	Context context;
	List<ModuleInfo> list;
	private int pagesize = 0;
	protected static final int[] ICONS = new int[] {
			R.color.vpi__bright_foreground_disabled_holo_dark, R.color.vpi__bright_foreground_disabled_holo_light };
	private int position;

	public ItemPagerAdapter(Context context, FragmentManager fragmentmanager,
			List<ModuleInfo> list) {
		super(fragmentmanager);
		this.context = context;
		this.list = list;
		pagesize = (list.size() + 8 - 1) / 8;
	}

	public int getCount() {
		return pagesize;
	}

	public Fragment getItem(int position) {
		this.position = position;
		ItemPageTabView itempagetabview = ItemPageTabView.getInstance();
		List<ModuleInfo> listtemp = new ArrayList<ModuleInfo>();
		for (int j = 0; j < 8; j++) {
			try {
				listtemp.add(list.get(j + (position * 8)));
			} catch (Exception e) {
			}
		}
		itempagetabview.setData(listtemp);
		return itempagetabview;
	}

	@Override
	public int getItemPosition(Object paramObject) {
		return -2;
	}

	@Override
	public Object instantiateItem(ViewGroup paramViewGroup, int paramInt) {
		ItemPageTabView itempagetabview = (ItemPageTabView) super
				.instantiateItem(paramViewGroup, paramInt);
		return itempagetabview;
	}

	@Override
	public int getIconResId(int index) {
		return R.drawable.home_page1_icon;
	}
}
