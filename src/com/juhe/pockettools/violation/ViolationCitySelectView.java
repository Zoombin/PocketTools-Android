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

import com.juhe.pockettools.R;
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
				// if ((ViolationCitySelectView.a(this.a) != null)
				// && (ViolationCitySelectView.b(this.a).getCurrentItem() <
				// this.a.a
				// .size())
				// && (ViolationCitySelectView.c(this.a).getCurrentItem() <
				// this.a.b
				// .size())) {
				// ViolationCitySelectView.a(this.a).a(
				// (String) this.a.a.get(ViolationCitySelectView.b(
				// this.a).getCurrentItem()),
				// (y) this.a.b.get(ViolationCitySelectView.c(this.a)
				// .getCurrentItem()));
				// }
			}
		});
		provincewheel.addChangingListener(new OnWheelChangedListener() {

			@Override
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				String str = (String) z.b.get(newValue);
				selectWheel(str, null);
			}
		});
		list = new ArrayList<String>();
		citylist = new ArrayList<CityEntity>();
	}

	private void selectWheel(String paramString, CityEntity entity) {
		// citylist = ((ArrayList) z.a.get(paramString));
		// ArrayList localArrayList = new ArrayList();
		// int i = 0;
		// int j = 0;
		// for (;;) {
		// if (i >= citylist.size()) {
		// citywheel.a(new com.fotoable.helpr.calendar.datepicker.c(
		// localArrayList), null);
		// citywheel.setCyclic(false);
		// citywheel.setCurrentItem(j);
		// return;
		// }
		// localArrayList.add(((CityEntity) citylist.get(i)).k);
		// if ((entity != null)
		// && (((CityEntity) citylist.get(i)).k.equals(entity.k))) {
		// j = i;
		// }
		// i++;
		// }
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

	public void a(String paramString, CityEntity paramy) {
		list = z.b;
		provincewheel.setAdapter(new WheelAdapter() {

			@Override
			public int getMaximumLength() {
				return 0;
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
		public abstract void getCity(String city, CityEntity paramy);
	}
}
