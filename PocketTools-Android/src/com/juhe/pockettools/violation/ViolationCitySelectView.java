package com.juhe.pockettools.violation;

import java.util.ArrayList;

import kankan.wheel.widget.WheelAdapter;
import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.zoombin.koudai.R;
import com.juhe.pockettools.calendar.datepicker.OnWheelChangedListener;
import com.juhe.pockettools.calendar.datepicker.WheelView;

public class ViolationCitySelectView extends LinearLayout {
	ArrayList<String> list;
	ArrayList<CityEntity> citylist;
	private Button surebutton;
	private WheelView provincewheel;
	private WheelView citywheel;
	private OnCityListener citylistener;

	public ViolationCitySelectView(Context context) {
		super(context);
		initView();
	}

	public ViolationCitySelectView(Context context, AttributeSet attributeSet) {
		super(context, attributeSet);
		initView();
	}

	private void initView() {
		((LayoutInflater) getContext().getSystemService(
				Context.LAYOUT_INFLATER_SERVICE)).inflate(
				R.layout.view_violation_city_select, this, true);
		surebutton = ((Button) findViewById(R.id.surebutton));
		provincewheel = ((WheelView) findViewById(R.id.province));
		citywheel = ((WheelView) findViewById(R.id.city));
		surebutton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				int provinceitem = provincewheel.getCurrentItem();
				int cityitem = citywheel.getCurrentItem();
				
				CityEntity entity = ViolationMainActivity.citymap.get(ViolationMainActivity.provincelist.get(provinceitem)).get(cityitem);
				citylistener.getCity(entity.getProvince_code(), entity);
			}
		});
		provincewheel.addChangingListener(new OnWheelChangedListener() {

			@Override
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				String str = (String) ViolationMainActivity.provincelist
						.get(newValue);
				selectWheel(str, ViolationMainActivity.citymap.get(str).get(0));
			}
		});
		list = new ArrayList<String>();
		citylist = new ArrayList<CityEntity>();
	}

	private void selectWheel(String paramString, CityEntity entity) {
		citylist = ViolationMainActivity.citymap.get(paramString);
		for (int i = 0; i < citylist.size(); i++) {
			if (citylist.get(i).getCity_code().equals(entity.getCity_code())) {
				citywheel.setAdapter(new WheelAdapter() {
	
					@Override
					public int getMaximumLength() {
						return -1;
					}
	
					@Override
					public int getItemsCount() {
						return citylist.size();
					}
	
					@Override
					public String getItem(int index) {
						return citylist.get(index).getCity_name();
					}
				}, null);
				citywheel.setCyclic(false);
				citywheel.setCurrentItem(i);
				return;
			}
		}
	}

	public void initWheel() {
		DisplayMetrics localDisplayMetrics = getContext().getResources()
				.getDisplayMetrics();
		int heightPixels = localDisplayMetrics.heightPixels;
		int widthPixels = localDisplayMetrics.widthPixels;
		int textsize = 2 * (heightPixels / 60);

		provincewheel.textsize = textsize;
		citywheel.textsize = textsize;

		LinearLayout.LayoutParams params1 = (LinearLayout.LayoutParams) provincewheel
				.getLayoutParams();
		params1.width = (1 + widthPixels / 2);
		provincewheel.setLayoutParams(params1);

		LinearLayout.LayoutParams params2 = (LinearLayout.LayoutParams) citywheel
				.getLayoutParams();
		params2.width = (1 + widthPixels / 2);
		citywheel.setLayoutParams(params2);
	}

	public void setProvinceWheelData(String paramString, CityEntity paramy) {
		list = ViolationMainActivity.provincelist;
		provincewheel.setAdapter(new WheelAdapter() {

			@Override
			public int getMaximumLength() {
				return -1;
			}

			@Override
			public int getItemsCount() {
				return list.size();
			}

			@Override
			public String getItem(int index) {
				return list.get(index);
			}
		}, null);
		provincewheel.setCyclic(false);
		for (int i = 0; i < list.size(); i++) {
			if (paramString.equals(list.get(i))) {
				provincewheel.setCurrentItem(i);
				selectWheel(paramString, paramy);
				initWheel();
			}
		}
	}

	public void setListener(OnCityListener citylistener) {
		this.citylistener = citylistener;
	}

	public static abstract interface OnCityListener {
		public abstract void getCity(String province_code, CityEntity cityentity);
	}
}
