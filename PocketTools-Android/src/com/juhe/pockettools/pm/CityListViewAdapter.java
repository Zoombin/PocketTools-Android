package com.juhe.pockettools.pm;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;

import com.juhe.pockettools.R;

public class CityListViewAdapter extends BaseAdapter {
	private LayoutInflater layoutinflater;
	private Context context;
	private List<CityAirEntity.Moni> list;

	public CityListViewAdapter(Context context) {
		this.context = context;
		layoutinflater = LayoutInflater.from(context);
	}

	public void setData(List<CityAirEntity.Moni> list) {
		this.list = list;
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		if (list != null) {
			return list.size();
		}
		return 0;
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
		CityAirEntity.Moni entity = (CityAirEntity.Moni) list.get(position);
		ViewHolder holder;

		if (convertView == null) {
			convertView = layoutinflater.inflate(
					R.layout.view_pm_citylistview_item, parent, false);
			holder = new ViewHolder();
			holder.city_label = ((TextView) convertView
					.findViewById(R.id.city_label));
			holder.aqi_label = ((TextView) convertView
					.findViewById(R.id.aqi_label));
			holder.level_label = ((TextView) convertView
					.findViewById(R.id.level_label));
			Typeface localTypeface = Typeface.createFromAsset(
					context.getAssets(), "fonts/HelveticaNeueLTPro-MdCn.otf");
			holder.aqi_label.setTypeface(localTypeface);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.city_label.setText(entity.getCity());
		holder.aqi_label.setText(entity.getAQI());
		holder.level_label.setText(PMTools.getQualityStr(Integer.parseInt(entity.getAQI())));
		holder.aqi_label.setTextColor(PMTools.getQualityBackground(Integer.parseInt(entity.getAQI())));
		holder.level_label.setTextColor(PMTools.getQualityBackground(Integer.parseInt(entity.getAQI())));

		return convertView;
	}

	private class ViewHolder {
		public TextView city_label;
		public TextView level_label;
		public TextView aqi_label;
	}
}
